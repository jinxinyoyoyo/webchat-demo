package com.jjkj;

import com.jjkj.udp.UdpServer;
import com.jjkj.webchat.WebChatServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jinxin on 2018/6/5.
 */
@SpringBootApplication
public class WebchatServer8888Application {

	public static void main(String[] args) {
		SpringApplication.run(WebchatServer8888Application.class, args);
//		new WebChatServer(9999).start();
		new UdpServer().run(6789);
	}

}
