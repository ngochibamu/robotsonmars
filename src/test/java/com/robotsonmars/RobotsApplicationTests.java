package com.robotsonmars;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import com.robotsonmars.service.Robot;
import com.robotsonmars.service.RobotsManager;
import com.robotsonmars.exceptions.UnSupportedCommandException;


public class RobotsApplicationTests {

	private RobotsManager robotsManager = new RobotsManager();

	private List<Robot> robots;

	@Rule 
	public ExpectedException expectedException = ExpectedException.none();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldInitilizeRobotsFromFileTest() throws IOException{

		List<Robot> robots = new ArrayList<>();

		String lines = "5 5\n"+
						"1 2 N\n"+
						"LMLMLMLMM\n"+
						"3 3 E\n"+
						"MMRMMRMRRM";
		File file = tempFolder.newFile("input.txt");

		assertThat(file.exists(), is(equalTo(true)));
		Path path = file.toPath();
		Files.write(path,lines.getBytes("UTF-8"));
		assertThat("File size",Files.size(path), greaterThan(0L));
		robots = robotsManager.init(path).processAction().getRobots();
		assertThat("Robots list is empty",robots, is(not(empty())));
		assertThat("Robots list size is 2", robots, hasSize(equalTo(2)));
		Robot robot1 = robots.get(0);
		Robot robot2 = robots.get(1);
		assertThat(robot1, is(notNullValue()));
		assertThat(robot2, is(notNullValue()));
		assertThat(robot1.getXpos(), is(equalTo(1)));
		assertThat(robot1.getYpos(), is(equalTo(3)));
		assertThat(robot1.getCardinal(), is(equalTo("N")));
		assertThat(robot2.getXpos(), is(equalTo(5)));
		assertThat(robot2.getYpos(), is(equalTo(1)));
		assertThat(robot2.getCardinal(), is(equalTo("E")));
	}

	@Test
	public void shouldThrowUnSupportedCommandExceptionInvalidCammandTest() throws Exception{
		expectedException.expect(UnSupportedCommandException.class);
		expectedException.expectMessage(is(equalTo("Unknown robot instruction: K, only L (LEFT TURN), R (RIGHT TURN) and M (MOVE) are supported.")));
		Robot robot = new Robot(0,0,"N","MMK".toCharArray());
		robotsManager.navigate(robot);
    }

    @Test
    public void shouldThrowUnSupportedCommandExceptionInvalidCardinalPointTest() throws Exception {
    	expectedException.expect(UnSupportedCommandException.class);
    	Robot robot = new Robot(0,0,"J","MMRM".toCharArray());
    	expectedException.expectMessage(is(equalTo("Unknown cardinal point: "+robot.getCardinal()+", only N (NORTH), S(SOUTH), E(EAST) and W(WEST) are allowed.")));
    	robotsManager.navigate(robot);
    }
}
