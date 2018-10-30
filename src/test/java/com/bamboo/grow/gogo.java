package com.bamboo.grow;

import org.junit.Test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wls
 * @version v1.0
 * @date 2018/10/22
 */
public class gogo {
    /**
     * 合法E-mail地址：
     *     1. 必须包含一个并且只有一个符号“@”
     *     2. 第一个字符不得是“@”或者“.”
     *     3. 不允许出现“@.”或者.@
     *     4. 结尾不得是字符“@”或者“.”
     *     5. 允许“@”前的字符中出现“＋”
     *     6. 不允许“＋”在最前面，或者“＋@”
     *
     *     正则表达式如下：
     *     -----------------------------------------------------------------------
     *     ^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$
     *     -----------------------------------------------------------------------
     *
     *     字符描述：
     *     ^ ：匹配输入的开始位置。
     *     \：将下一个字符标记为特殊字符或字面值。
     *     * ：匹配前一个字符零次或几次。
     *     + ：匹配前一个字符一次或多次。
     *     (pattern) 与模式匹配并记住匹配。
     *     x|y：匹配 x 或 y。
     *     [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。
     *     \w ：与任何单词字符匹配，包括下划线。
     *     $ ：匹配输入的结尾。
     */
    @Test
    public void youXiangCheck(){
        //电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher("dffdfdf@qq.com");
        boolean isMatched = matcher.matches();
        System.out.println(isMatched);
    }

    public void sendSocket(){
        // 创建发送端Socket对象
        // DatagramSocket()
        try {
            DatagramSocket ds = new DatagramSocket();
            byte[] bytes = "测试UDP发送socket".getBytes();
            int length = bytes.length;
            InetAddress byName = InetAddress.getByName("192.168.1.11");
            int port = 123465;
            DatagramPacket datagramPacket = new DatagramPacket(bytes,length,byName,port);
            ds.send(datagramPacket);
            ds.close();
        }catch (Exception e){

        }
    }

    public void reviewSocket(){
        try {
            DatagramSocket ds = new DatagramSocket(12356);
            byte[] bytes = new byte[1024];
            int length = bytes.length;
            DatagramPacket datagramPacket = new DatagramPacket(bytes, length);
            ds.receive(datagramPacket);
            InetAddress address = datagramPacket.getAddress();
            String hostAddress = address.getHostAddress();
            byte[] data = datagramPacket.getData();
            String s = new String(data, 0, data.length);
            System.out.println("接收到UDP传输的数据 ：" + s);
        }catch (Exception e){

        }
    }

    public void TestIntegerMethon(){
        Integer a = 1;
        Integer b = 2;
        String  c = "2";
        String  d = "1";
        a.equals(b);
        c.equals(d);

    }
}
