package com.bamboo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
//@MapperScan("com.bamboo.mapper")
public class GrowApplication {
	//？？？？？？2 7
	//5 13 14 16 26 33 2 7
	public static void main(String[] args) {
		//SpringApplication.run(GrowApplication.class, args);
		/*for (int i = 0; i < 16; i++) {
			money();
		}*/

		Long s = 1L;
		String ss = "1";
		if(s.equals(ss)){
			System.out.println("cao");
		}

















	}

	private static void money(){
		//定义一个数组，存放系统随机生成的6个红色球号码
		Integer[] sysRedBall = new Integer[6];
		//定义一个整数，存放系统随机生成的蓝色球号码
		int sysBlueBall = 0;
		//定义一个数组，存放用户生成自己选择的6个红色球号码
		int[] userRedBall = new int[6];
		//定义一个整数，存放用户生成自己选择的蓝色球号码
		int userBlueBall = 0;
		//定义两个整数变量，一个存放正确的红球的个数，另一个存放正确的蓝色个数
		int redCount = 0;
		int blueCount = 0;
		//定义一个数组，存放红球的池子，1-33
		int[] redBalls = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,
				18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
		//让系统自动生成中奖号码
		//获取随机数
		Random r = new Random();
		//System.out.println(r.nextInt(33)); //从0-32随机产生一个数
		//从redBalls随机产生6个数，并不重复
		for(int i=0;i<sysRedBall.length;i++) {
			int index = 0;
			while(true) {
				index = r.nextInt(33);  //返回一个0-32之间的一个随机数
				if(redBalls[index]!=-1) {  //根据产生随机数（数组下标）取出对应的值
					sysRedBall[i] = redBalls[index];
					redBalls[index] = -1;
					break;
				}
			}
		}
		sysBlueBall = r.nextInt(16)+1;  //随机产生一个蓝色球
		List<Integer> resultList = new ArrayList<>(sysRedBall.length);
		Collections.addAll(resultList,sysRedBall);
		Collections.sort(resultList);
		System.out.println("卧槽 中奖号码竟然是 :  " + resultList.toString() + " " + sysBlueBall);
	}
}
