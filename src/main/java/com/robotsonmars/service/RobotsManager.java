package com.robotsonmars.service;

import java.util.Scanner;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList; 
import java.util.stream.Stream;

import com.robotsonmars.util.CardinalPoint;
import com.robotsonmars.util.Command;
import com.robotsonmars.util.LookupEnumUtil;
import com.robotsonmars.service.Robot;
import com.robotsonmars.exceptions.UnSupportedCommandException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
	Manages the movement of robots on the plateau. If the robot's next instruction makes it go over the 
	plataeu, the RobotsManager ignores that instruction. 
*/

@Component
public class RobotsManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(RobotsManager.class);

	private static final int ORIGIN_X=0;
	private static final int ORIGIN_Y=0;

	private int plateauX;
	private int plateauY;

	private List<Robot> robots = new ArrayList<>();
	
	/**
		Reads robots data from the path supplied, and use it to initialize robots
		@param source path to file with robot data.
		@return this returns RobotsManager instance
	*/
	public RobotsManager init(Path source) throws IOException{
		Robot robot;
		Scanner scanner = new Scanner(source);
		plateauX = Integer.parseInt(scanner.next());
		plateauY = Integer.parseInt(scanner.next());

		while(scanner.hasNext()){
			int x = Integer.parseInt(scanner.next());
			int y = Integer.parseInt(scanner.next());
			String direction = scanner.next();
			scanner.nextLine();
			String sequence = scanner.next();
			char[] actionSeq = sequence.toCharArray();
			robot = new Robot(x, y, direction, actionSeq);
			robots.add(robot);
		}
		return this;
	}
	/**
		Iterates over the list of robots processing each robot's sequence of instructions
		@return this current RobotsManager instance
	*/
	public RobotsManager processAction(){
		for(Robot robot: robots){
			try{
				navigate(robot);
			}catch(UnSupportedCommandException u){
				LOGGER.error(u.getMessage(), u);
				//skip offending robot and continue to next one.
				continue;
			}
		}
		return this;
	}

	/**
		Displays each robot's final position.
	*/
	public void displayRobotPosition(){
		robots.forEach(
						robot -> {
									System.out.println(robot.getXpos()+" "+ robot.getYpos()+" "+robot.getCardinal());
								}
					  );
	}
	/**
		Interprets each robot's sequence of instructions and instruct the robot toact accordingly.
		@param robot with instructions that must be interpreted
	*/
	public void navigate(Robot robot) throws UnSupportedCommandException{
		
			for(Character seq : robot.getSequenceActions()){
				String excepMsg = "Unknown robot instruction: "+seq+", only L (LEFT TURN), R (RIGHT TURN) and M (MOVE) are supported.";
				Command action = LookupEnumUtil.lookupCommand(Command.class, String.valueOf(seq),excepMsg);

				switch(action) {
					case L:
						if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.EAST.value()))
							robot.setCardinal(CardinalPoint.NORTH.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.NORTH.value()))
							robot.setCardinal(CardinalPoint.WEST.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.WEST.value()))
							robot.setCardinal(CardinalPoint.SOUTH.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.SOUTH.value()))
							robot.setCardinal(CardinalPoint.EAST.value());
						else
							throw new UnSupportedCommandException("Unknown cardinal point: "+robot.getCardinal()+", only N (NORTH), S(SOUTH), E(EAST) and W(WEST) are allowed.");
						break;
					case R:
						if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.EAST.value()))
							robot.setCardinal(CardinalPoint.SOUTH.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.NORTH.value()))
							robot.setCardinal(CardinalPoint.EAST.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.WEST.value()))
							robot.setCardinal(CardinalPoint.NORTH.value());
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.SOUTH.value()))
							robot.setCardinal(CardinalPoint.WEST.value());
						else
							throw new UnSupportedCommandException("Unknown cardinal point: "+robot.getCardinal()+", only N (NORTH), S(SOUTH), E(EAST) and W(WEST) are allowed.");
						break;
					case M:
						if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.EAST.value())){
							if(robot.getXpos() + 1 <= plateauX)					
								robot.setXpos(robot.getXpos() + 1);
						}
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.NORTH.value())){
							if(robot.getYpos() + 1 <= plateauY)
								robot.setYpos(robot.getYpos() + 1);
						}
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.WEST.value())){
							if(robot.getXpos() - 1 >= ORIGIN_X)
								robot.setXpos(robot.getXpos() - 1);
						}
						else if(StringUtils.equalsIgnoreCase(robot.getCardinal(), CardinalPoint.SOUTH.value())){
							if(robot.getYpos() - 1 >= ORIGIN_Y)
								robot.setYpos(robot.getYpos() - 1);
						}else {
							throw new UnSupportedCommandException("Unknown cardinal point: "+robot.getCardinal()+", only N (NORTH), S(SOUTH), E(EAST) and W(WEST) are allowed.");
						}
						break;
				}
			}
	}

	public List<Robot> getRobots() {
		return this.robots;
	}

}