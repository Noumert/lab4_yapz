package com.example.fileUpload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.fileUpload", "com/example/fileUpload/controller"})
public class FileUploadApplication {
	public static void main(String[] args) {
		//new File(FileUploadController.uploadDirectory).mkdir();
		SpringApplication.run(FileUploadApplication.class, args);
	}

}
