package com.rigol.website.controller;

import com.rigol.website.api.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("register")
public class RegisterController {
    public static final String REDIS_VALIDATE_CODE = "imgValidateCode:website";

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 获取图片验证码
     * @param jsonParams
     * @return
     */
    @RequestMapping(value = "getImageCode")
    @ResponseBody
    public String getImageValidateCode(String jsonParams) {

        return userInfoService.getImageValidateCode(jsonParams);
    }

    /**
     * 验证图片验证及保存
     * @param jsonParams
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(String jsonParams) {
        return userInfoService.validateImageCode(jsonParams);
    }
}
