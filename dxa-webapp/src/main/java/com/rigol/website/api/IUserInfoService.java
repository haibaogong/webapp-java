package com.rigol.website.api;

import com.rigol.website.entity.UserInfo;

import java.util.List;

public interface IUserInfoService {

    /**
     * 保存用户信息
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 查找所有用户信息
     * @return
     */
    List<UserInfo> queryAll();

    /**
     * 创建验证码
     * @param jsonParams
     * @return
     */
    public String getImageValidateCode(String jsonParams);

    /**
     *  验证图片验证码
     * @param jsonParams
     * @return
     */
    String validateImageCode(String jsonParams);
}
