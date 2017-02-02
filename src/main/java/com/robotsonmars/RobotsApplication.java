package com.robotsonmars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.robotsonmars.service.RobotsManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootApplication
public class RobotsApplication implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotsApplication.class);

	@Autowired
	private RobotsManager robotsManager;

	@Override
	public void run(String... args){
		if(args.length < 1){
			System.out.println("Usage: java -jar robotsonmars.0.0.1.jar [file]");
			System.exit(0);
		}

		if(!Files.exists(Paths.get(args[0]))){
			System.out.println("File "+args[0]+" not found");
			System.exit(0);			
		}
		try{
			robotsManager.init(Paths.get(args[0])).processAction().displayRobotPosition();			
		}catch(IOException e){
			LOGGER.error("Unable to read instruction file: {}", args[0],e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RobotsApplication.class, args);
	}
}
