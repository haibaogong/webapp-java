package com.rigol.website.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 返回数据处理
 * @author :luolm
 */
@Slf4j
public class ReturnCodeUtils {
    /**
     * code
     */
    public static final String RSP_CODE_KEY="rspCode";
    /**
     * responseMsg
     */
    public static final String RSP_MSG_KEY="rspMsg";
    /**
     * ShowMsg
     */
    public static final String SHOW_MSG_KEY="showMsg";
    /**
     * data
     */
    public static final String DATA_KEY="data";

    /**
     * 服务端返回码前缀
     */
    public  static  final  String SERVER_RETURN_CODE_PREFIX="2";



    /**
     * success200:(成功)
     *
     * @param data 查询返回数据
     * @return
     * @author luolm
     */
    public static Map<String, Object> success200(Map<String, Object> map,Object data) {
        map.put(RSP_CODE_KEY, ReturnCode.SUCCESS.getCode());
        map.put(RSP_MSG_KEY, ReturnCode.SUCCESS.getResponseMsg());
        map.put(SHOW_MSG_KEY, ReturnCode.SUCCESS.getShowMsg());
        if (data != null) {
            map.put(DATA_KEY, data);
        } else {
            map.put(DATA_KEY, null);
        }

        return map;
    }

    /**
     * initParms:(初始化返回参数)
     *
     * @return
     * @Author airufei
     */
    public static Map<String, Object> initParms() {
        Map<String, Object> map = new HashMap<String, Object>();
        ReturnCode success = ReturnCode.SUCCESS;
        map.put(RSP_CODE_KEY, success.getCode());
        map.put(RSP_MSG_KEY, success.getResponseMsg());
        map.put(SHOW_MSG_KEY, success.getShowMsg());
        map.put(DATA_KEY, null);
        return map;
    }

    /**
     * success200:(成功)
     *
     * @param data    查询返回数据
     * @param showMsg 展示的消息
     * @return
     * @Author airufei
     */
    public static Map<String, Object> success200(Map<String, Object> map,Object data, String showMsg) {
        ReturnCodeUtils.success200(map, data);
        if (StringUtils.isNotBlank(showMsg)) {
            map.put(SHOW_MSG_KEY, showMsg);
        }
        return map;
    }

    /**
     * 错误 500
     *
     * @return
     */
    public static Map<String, Object> error(Map<String, Object> map, ReturnCode returnCode) {
        return error(map,returnCode,null,null,null);
    }

    /**
     * 错误 自定义错误提示
     *
     * @param showMsg
     * @param rsMsg
     * @param returnCode
     * @return
     */
    public static Map<String, Object> error(Map<String,Object> map, ReturnCode returnCode,String showMsg, String rsMsg ) {
        return error(map,returnCode,showMsg,rsMsg,null);
    }

    /**
     * 错误 自定义错误提示，
     * @param returnCode
     * @param showMsg
     * @param rsMsg
     * @param data 如果有返回数据，填充到这里
     * @return
     */
    public static Map<String, Object> error(Map<String,Object> map,ReturnCode returnCode,String showMsg, String rsMsg, Object data) {
        map.clear();
        if (returnCode != null) {
            map.put(RSP_CODE_KEY, returnCode.getCode());
            map.put(RSP_MSG_KEY, returnCode.getResponseMsg());
            map.put(SHOW_MSG_KEY, returnCode.getShowMsg());
        }
        if (StringUtils.isNotBlank(showMsg)) {
            map.put(SHOW_MSG_KEY, showMsg);
        }
        if (StringUtils.isNotBlank(rsMsg)) {
            map.put(RSP_MSG_KEY, rsMsg);
        }
        if (data!=null){
            map.put(DATA_KEY, data);
        }
        //增加返回码展示
        map.put(SHOW_MSG_KEY, MessageFormat.format("{0}({1}{2})",map.get(SHOW_MSG_KEY),SERVER_RETURN_CODE_PREFIX,map.get(RSP_CODE_KEY)));
        return map;
    }


    /**
     * 是否成功
     * @param resultJsonStr
     * @return
     */
    public static boolean isSucess(String resultJsonStr){
        try {
            JSONObject jsonObject = JSONObject.parseObject(resultJsonStr);
            if (jsonObject!=null&&jsonObject.getInteger(RSP_CODE_KEY).equals(ReturnCode.SUCCESS.getCode())){
                return true;
            }
            return false;
        } catch (Exception e) {
            log.error("isSucess catch error,resultJsonStr-{},msg-{}",resultJsonStr,e.getMessage(),e);
            return false;
        }
    }

    /**
     * 返回data里的json对象
     * @param resultJsonStr
     * @return
     */
    public static JSONObject getDataJson(String resultJsonStr){
        try {
            JSONObject jsonObject = JSONObject.parseObject(resultJsonStr);
            if (jsonObject!=null&&jsonObject.getInteger(RSP_CODE_KEY).equals(ReturnCode.SUCCESS.getCode())){
                return jsonObject.getJSONObject(DATA_KEY);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDataJson catch error,resultJsonStr-{},msg-{}",resultJsonStr,e.getMessage(),e);
            return null;
        }

    }


    /**
     * 返回data里的字符串
     * @param resultJsonStr
     * @return
     */
    public static String getDataStr(String resultJsonStr){
        try {
            JSONObject jsonObject = JSONObject.parseObject(resultJsonStr);
            if (jsonObject!=null&&jsonObject.getInteger(RSP_CODE_KEY).equals(ReturnCode.SUCCESS.getCode())){
                return jsonObject.getString(DATA_KEY);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDataJson catch error,resultJsonStr-{},msg-{}",resultJsonStr,e.getMessage(),e);
            return null;
        }

    }

}
