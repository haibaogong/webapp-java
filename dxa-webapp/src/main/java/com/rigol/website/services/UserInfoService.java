package com.rigol.website.services;

import com.alibaba.fastjson.JSONObject;
import com.rigol.website.api.IUserInfoService;
import com.rigol.website.entity.UserInfo;
import com.rigol.website.dao.UserInfoDao;
import com.rigol.website.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserInfoService implements IUserInfoService {

    public static final String REDIS_VALIDATE_CODE = "imgValidateCode:website";

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 保存用户信息
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        userInfoDao.save(userInfo);
    }
    /**
     * 查找所有用户信息
     * @return
     */
    @Override
    public List<UserInfo> queryAll() {
        return userInfoDao.queryAll();
    }

    /**
     * 创建图片验证码
     * @param jsonParams
     * @return
     */
    public String getImageValidateCode(String jsonParams) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("name");
        userInfo.setPhone("111");
        userInfoDao.save(userInfo);
        userInfoDao.queryAll().forEach(s ->System.out.println(s));

        // 返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        // 返回Data
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            //创建图片
            String uuid = IdGen.uuid();
            ImageValidateCodeUtil img = new ImageValidateCodeUtil();
            String[] imgbs64 = img.createImage();
            String codeValue = imgbs64[0];
            String img64 = imgbs64[1];
            if (StringUtils.isNotBlank(codeValue) && StringUtils.isNotBlank(img64)) {
                data.put("uuid", uuid);
                data.put("code", img64);
                int secondsTimeout = 60 * 60;
                SingleJedisUtil jedisUtil = SingleJedisUtil.getInstance();
                jedisUtil.STRINGS.setEx(uuid + ":" + REDIS_VALIDATE_CODE, secondsTimeout, codeValue);
                log.debug("ImageValidateCodeServiceImpl#create return data-{}，codeValue-{}", data, codeValue);
                ReturnCodeUtils.success200(result, data);
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
            ReturnCodeUtils.error(result, ReturnCode.SYS_ERROR_500, "创建图形验证失败", "创建图形验证失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     *  验证图片验证码
     * @param jsonParams
     * @return
     */
    public String validateImageCode(String jsonParams){
        // 返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        // 返回Data
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            JSONObject pm = new JSONObject();
            if (StringUtils.isNotBlank(jsonParams)) {
                // 入参不为空
                pm = JSONObject.parseObject(jsonParams);
            } else {
                log.error("ImageValidateCodeServiceImpl#validate 参数错误jsonParams：" + jsonParams);
            }
            String uuid = pm.getString("uuid");
            String code = pm.getString("code").toUpperCase();
            String name = pm.getString("name");
            String phone = pm.getString("phone");
            String key = uuid + ":" + REDIS_VALIDATE_CODE;
            SingleJedisUtil jedisUtil = SingleJedisUtil.getInstance();
            if (jedisUtil.KEYS.exists(key)) {
                log.info("ImageValidateCodeServiceImpl#validate key exists -{}", key);
            } else {
                log.error("ImageValidateCodeServiceImpl#validate key not exists -{},params-{}", key, jsonParams);
            }
            String validateCode = (String) jedisUtil.STRINGS.get(key);
            if (code.equals(validateCode)) {
                data.put("result", "true");
                ReturnCodeUtils.success200(result, data);
            } else {
                data.put("result", "false");
                ReturnCodeUtils.error(result, ReturnCode.SYS_ERROR_504);
            }
        }catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            ReturnCodeUtils.error(result, ReturnCode.SYS_ERROR_500, "验证图形验证失败", "验证图形验证失败");
        }
        return JSONObject.toJSONString(result);
    }
}
