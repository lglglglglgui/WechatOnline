package com.chat.online;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@EnableWebSocket
@MapperScan("com.chat.online.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class BootApplication extends SpringBootServletInitializer{
	public static void main(String... args) {
		SpringApplication springApplication=new SpringApplication(BootApplication.class);
		springApplication.run(args);
	}

}

