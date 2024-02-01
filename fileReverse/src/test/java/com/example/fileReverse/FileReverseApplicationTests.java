package com.example.fileReverse;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class FileReverseApplicationTests {

	@Test
	public void contextLoads() {
		String hello = "Hello";
		FileReverseApplication.main(new String[]{hello});


		File logFile = new File("src/main/resources/InputFile-Reverse");
		assertTrue( logFile.length() > 0);


	}

}
