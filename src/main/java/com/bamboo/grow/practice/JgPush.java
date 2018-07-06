package com.bamboo.grow.practice;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.report.MessageStatus;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.model.CheckMessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * java后台极光推送方式二：使用Java SDK
 */
@SuppressWarnings({ "deprecation", "restriction" })
public class JgPush {
    private static final Logger log = LoggerFactory.getLogger(JgPush.class);
    private static String masterSecret = "xxxxxxxxxxxxxxxxx";
    private static String appKey = "xxxxxxxxxxxxxxxx";
    private static final String ALERT = "推送信息";
    /**
     * 极光推送
     */
    public void jiguangPush(){
        String alias = "123456";//声明别名
        log.info("对别名" + alias + "的用户推送信息");
        PushResult result = push(String.valueOf(alias),ALERT);
        if(result != null && result.isResultOK()){
            log.info("针对别名" + alias + "的信息推送成功！");
        }else{
            log.info("针对别名" + alias + "的信息推送失败！");
        }
    }

    /**
     * 生成极光推送对象PushPayload（采用java SDK）
     * @param alias
     * @param alert
     * @return PushPayload
     */
    public static PushPayload buildPushObject_android_ios_alias_alert(String alias, String alert){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(alert)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("type", "infomation")
                                .setAlert(alert)
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        //.setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setTimeToLive(90)//消息在JPush服务器的失效时间（测试使用参数）
                        .build())
                .build();
    }
    /**
     * 极光推送方法(采用java SDK)
     * @param alias
     * @param alert
     * @return PushResult
     */
    public static PushResult push(String alias,String alert){
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        PushPayload payload = buildPushObject_android_ios_alias_alert(alias,alert);
        try {
            return jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }

    /**
     * 送达统计获取样例
     * @param alias
     * @param alert
     * @param msg_id msg_ids 推送API返回的 msg_id 列表，多个 msg_id 用逗号隔开，最多支持100个msg_id。
     * Received API 以 msg_id 作为参数，去获取该 msg_id 的送达统计数据。
     * 如果一次 API 调用推送有很多对象（比如广播推送），则此 API 返回的统计数据会因为持续有客户端送达而持续增加。
     * 每条推送消息的送达统计数据最多保留一个月。
     * 即发起推送请求后从最后一个推送送达记录时间点开始保留一个月，如果保留期间有新的送达，将在这个新送达的时间点起再往后保留一个月。
     * @return
     */
    public ReceivedsResult getExample(String alias,String alert,String msg_id){
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds(msg_id);
            log.debug("Got result - " + result);
            return result;

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);
            return null;

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            return null;
        }
    }

    /**
     * 查询已推送的一条消息在一组设备上的送达状态。
     * @param msg_id  必传。消息 id，一次调用仅支持一个消息 id 查询。
     * @param registration_ids 必传。JSON Array 类型，多个registration id 用逗号隔开，一次调用最多支持1000个。
     * @param date 可选。查询的指定日期，格式为yyyy-mm-dd，默认为当天。
     * @return
     */
    public Map<String, MessageStatus> getState(Long msg_id, List<String> registration_ids, String date){
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        try {
            CheckMessagePayload checkMessagePayload = new CheckMessagePayload(msg_id,registration_ids,date);
            Map<String, MessageStatus> result = jpushClient.getMessageStatus(checkMessagePayload);
            return result;

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);
            return null;

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            return null;
        }
    }

    /**
     * 获取当前设备的所有属性，包含tags, alias，手机号码mobile。
     * @param registration_ids
     * @return
     */
    public TagAliasResult getDeviceTagAlias(String registration_ids){
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        try {
            TagAliasResult deviceTagAlias = jpushClient.getDeviceTagAlias(registration_ids);
            return  deviceTagAlias;
        }catch (APIConnectionException e){
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);
            return null;

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            return null;
        }
    }
}
