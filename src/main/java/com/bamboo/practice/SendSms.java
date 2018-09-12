package com.bamboo.practice;

import com.bamboo.utils.HttpSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class SendSms {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "szbnyd_btcj";
    // 用户平台API密码(非登录密码)
    public static String pswd = "Btcj#2018";
    // 需要发送的手机号
    public static String mobile = "13888888888";
    // 需要发送的内容
    public static String msg = "13888888888";
    //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
    public static String url = "http://sapi.253.com/msg/HttpBatchSendSM";// 应用地址
    public static boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
    public static String extno = null;// 扩展码
    public static String messageTemplate="";//短信发送前缀

    public void sendSms(HashMap<String,String> map) {

        logger.info("消息队列返回手机号"+mobile+"和验证码"+map.get("code"));
        try {

            String returnString = HttpSender.batchSend(url,account,pswd,mobile,msg,needstatus,extno);
            logger.info("短信发送返回信息"+returnString);
            // TODO 处理返回值,参见HTTP协议文档
        } catch (Exception e) {
            // TODO 处理异常
            e.printStackTrace();
        }
    }
}
