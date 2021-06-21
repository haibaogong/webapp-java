package com.rigol.website.utils;

import java.text.MessageFormat;

/**
 * Created by llm on 17-06-05
 */
public enum ReturnCode {

    /*
    200，正确
    3**，参数错误
    4**，业务逻辑错误
    5**，服务端错误
    */

    SUCCESS(200, "成功", "成功"),
    PARAM_ERROR_100(100, "对外接口关闭", "对外接口关闭，请联系管理员"),
    PARAM_ERROR_301(301, "参数解密错误", "服务器异常，请重试"),
    PARAM_ERROR_302(302, "缺少参数", "服务器异常，请重试"),
    PARAM_ERROR_303(303, "参数长度错误", "服务器异常，请重试"),
    PARAM_ERROR_304(304, "参数格式错误", "服务器异常，请重试"),
    PARAM_ERROR_305(305, "参数整体为空", "服务器异常，请重试"),
    PARAM_ERROR_306(306, "参数校验异常", "服务器异常，请重试"),
    PARAM_ERROR_307(307, "参数校验异常", "版本号必须为数字"),
    PARAM_ERROR_308(308, "参数校验异常", "手机类型必须为数字"),
    PARAM_ERROR_309(309,"参数校验异常","姓名超过15个字"),
    PARAM_ERROR_310(310,"参数校验异常","手机号格式不正确"),
    PARAM_ERROR_311(311,"参数校验异常","申请金额最低10000元"),
    PARAM_ERROR_312(312,"参数校验异常","申请金额不可超过20万元"),
    PARAM_ERROR_313(313,"参数校验异常","跟进内容超过50个字"),
    PARAM_ERROR_314(314,"参数校验异常","申请金额必须为正整数"),
    PARAM_ERROR_315(315,"name不能为空","姓名不能为空"),
    PARAM_ERROR_316(316,"phone不能为空","手机号不能为空"),
    PARAM_ERROR_317(317,"loanAmount不能为空","申请金额不能为空"),
    PARAM_ERROR_318(318,"followProgress不能为空","跟进进度不能为空"),
    PARAM_ERROR_319(319,"comucontent内容不规范","请勿输入特殊字符"),
    PARAM_ERROR_320(320,"参数校验异常","户籍地址不能超过50个字"),
    PARAM_ERROR_321(321,"参数校验异常","身份证已过期"),
    PARAM_ERROR_322(322,"参数校验异常","身份证号输入不正确"),
    PARAM_ERROR_323(323,"参数校验异常","请正确选择申请期限"),
    PARAM_ERROR_324(324,"参数校验异常","抱歉，您选择的营业部暂不支持该产品申请"),
    PARAM_ERROR_325(325,"参数校验异常","抱歉，暂不受理申请该产品"),
    PARAM_ERROR_326(326,"参数校验异常","请正确输入申请金额"),
    PARAM_ERROR_327(327,"参数校验异常","产品已下架，请重新选择"),
    PARAM_ERROR_328(328,"参数校验异常","年龄不符，请选择其他产品"),
    PARAM_ERROR_329(329,"授权失败","授权失败，请联系管理员"),
    PARAM_ERROR_330(329,"操作失败，请重试","操作失败，请重试"),

    SYS_ERROR_403(403,"因为访问频繁，你已经被限制访问，稍后重试","因为访问频繁，你已经被限制访问，稍后重试"),
    SYS_ERROR_404(404,"推送异常","推送异常"),
    SYS_ERROR_405(405,"推送异常","推送数量不得超过1000条"),
    SYS_ERROR_406(406,"修改客户异常","该客户不存在"),
    SYS_ERROR_407(407,"添加客户异常","该手机号已存在请勿重复添加"),
    SYS_ERROR_408(408,"抢客户异常","该客户不存在"),
    SYS_ERROR_409(409,"推送异常","推送设备Id为空"),
    SYS_ERROR_410(410,"资讯异常","修改点击数失败"),
    SYS_ERROR_411(411,"资讯异常","修改分享数失败"),
    SYS_ERROR_412(412,"资讯异常","修改电话数失败"),
    SYS_ERROR_413(413,"资讯异常","修改发布状态失败"),
    SYS_ERROR_414(414,"资讯异常","该资讯不存在"),
    SYS_ERROR_415(415,"资讯异常","该客户经理不存在"),
    SYS_ERROR_416(416,"微信接口异常","定时获取token失败"),
    SYS_ERROR_417(417,"资讯客户异常","相同渠道的手机号已存在！"),

    SYS_ERROR_418(418,"身份认证异常","身份认证异常！"),
    SYS_ERROR_419(419,"人脸识别异常","人脸识别异常！"),
    SYS_ERROR_420(420,"资讯评论异常","已达今日评论上限，明天再来吧~"),
    SYS_ERROR_421(421,"资讯评论异常","当前资讯已关闭评论，提交失败"),
    SYS_ERROR_422(422,"资讯评论异常","当前资讯已删除，提交失败"),
    SYS_ERROR_423(423,"官网客户申请异常","您已经申请过3次请勿在重复申请我们会尽快跟您联系"),
    SYS_ERROR_424(424,"银行卡绑定异常","银行卡绑定异常"),
    SYS_ERROR_425(425,"银行卡解绑异常","银行卡解绑异常"),
    SYS_ERROR_426(426,"银行卡解绑异常","有活动中的订单，不可解绑银行卡"),

    SYS_ERROR_500(500,"服务端异常","服务器繁忙，请稍后再试"),
    SYS_ERROR_501(501,"登录已过期,请重新登录","登录已过期,请重新登录"),
    SYS_ERROR_502(502,"账号不存在","账号不存在"),
    SYS_ERROR_503(503,"账号或者密码不正确","账号或者密码不正确"),
    SYS_ERROR_504(504,"图片验证码错误","图形验证码验证失败"),
    SYS_ERROR_505(505,"短信验证码错误","短信验证码错误"),
    SYS_ERROR_506(506,"人员已离职，退出登录","账号已停用"),
    SYS_ERROR_507(507,"token校验失败","您还未进行身份认证，请先进行身份认证"),
    SYS_ERROR_508(508,"登录已失效,请重新登录","登录已失效,请重新登录"),
    SYS_ERROR_509(509,"您的密码已被修改，请重新登录","您的密码已被修改，请重新登录"),
    SYS_ERROR_510(510,"token校验失败","您还未登录，请先登录"),
    SYS_ERROR_511(511,"您的APP版本不是最新版本，请升级到最新版本","您的APP版本不是最新版本，请升级到最新版本"),


    SMS_ERROR_600(600, "一天只能发30条", "一天只能发30条"),
    SMS_ERROR_601(601, "mobile不能为空", "mobile不能为空"),
    SMS_ERROR_602(602, "smsThemeCode参数错误", "smsThemeCode参数错误"),
    SMS_ERROR_603(603, "sendContent参数错误", "sendContent参数错误"),

    ODR_ERROR_701(701, "order异常", "该订单不存在"),
    ODR_ERROR_702(702, "order异常", "该申请已提交，请勿重复提交。"),
    ODR_ERROR_703(703, "order异常", "身份认证未通过，不能提交"),
    ODR_ERROR_704(704, "order异常", "人脸识别认证未通过，不能提交"),
    ODR_ERROR_705(705, "影像资料异常","已达到上传上限"),
    ODR_ERROR_706(706, "产品异常","产品已下架，请重新选择"),
    ODR_ERROR_707(707, "产品异常","年龄不符，请选择其他产品"),

    CONS_ERROR_801(801,"消费异常","余额不足"),
    CONS_ERROR_802(802,"消费异常","该订单已处理"),
    CONS_ERROR_803(803,"消费异常","金额不正确"),
    CONS_ERROR_804(804,"消费异常","提现失败，有一条正在提现的单子"),
    CONS_ERROR_805(805,"消费异常","商户异常");

    private Integer code;
    private String responseMsg;
    private String showMsg;

    private ReturnCode(Integer code, String responseMsg, String showMsg) {
        this.code = code;
        this.responseMsg = responseMsg;
        this.showMsg = showMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public Integer getCode() {
        return this.code;
    }


    @Override
    public String toString() {
        return MessageFormat.format("name:{0},code:{1},responseMsg:{2},showMsg:{3}", this.name(), this.code, this.responseMsg, this.showMsg);
    }

    public static ReturnCode parseCode(Integer code){
        for (ReturnCode returnCode: ReturnCode.values()) {
            if (returnCode.getCode().compareTo(code)==0){
                return  returnCode;
            }
        }
        return  null;
    }

    public static void main(String[] args) {
        System.out.println(ReturnCode.parseCode(200));
    }
}
