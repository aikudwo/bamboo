package com.bamboo.practice;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/pay")
public class AliPay {
    /**
     * 生成APP支付订单信息，跳转支付宝支付页面
     *
     * @param outtradeno
     * @param APP_ID
     * @param APP_PRIVATE_KEY
     * @param CHARSET
     * @param ALIPAY_PUBLIC_KEY
     */
    @RequestMapping(value = "/payinfo", method = RequestMethod.POST)
    public void payInfo(String outtradeno, String APP_ID, String APP_PRIVATE_KEY, String CHARSET, String ALIPAY_PUBLIC_KEY) {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(outtradeno);
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 接收支付结果通知
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/payNotify", method = RequestMethod.POST)
    public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        //alipaypublicKey
        boolean flag = AlipaySignature.rsaCheckV1(params, "alipaypublicKey", "utf-8", "RSA2");
        if(flag){
            //交易状态
            String tradeStatus = params.get("trade_status");
            //订单编号
            String orderNo = params.get("out_trade_no");
            //支付单号
            String payNo = params.get("trade_no");
            //支付账号
            String payAccount = params.get("buyer_email");
            //支付金额
            String totalFee = params.get("total_fee");
            //收款支付宝账号
            String sellerId = params.get("seller_id");

            response.getWriter().print("success");
            return;
        }else{
            response.getWriter().print("failure");
            return;
        }
    }
}
