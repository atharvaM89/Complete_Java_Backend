package org.cfs.Boot_P01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootP01Application {

	private Notification notification;

	public BootP01Application(){

	}

	public BootP01Application(Notification notification){
		this.notification=notification;
	}

	public static void main(String[] args) {

		SpringApplication.run(BootP01Application.class, args);
	}

	public void run(String... args){
		notification.notifyUser();
	}
}
