package com.backend.v1;

import com.backend.v1.common.utils.EmailDetails;
import com.backend.v1.common.utils.OtpUtils;
import com.backend.v1.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class V1Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(V1Application.class, args);
	}

}
