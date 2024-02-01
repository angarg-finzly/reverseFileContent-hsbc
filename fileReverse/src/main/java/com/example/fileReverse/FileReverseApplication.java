package com.example.fileReverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class FileReverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileReverseApplication.class, args);
		String fileName = "InputFile";

		try {
			reverseFile(fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void reverseFile(String fileName) throws IOException {
		List<String> result;
		List<String> reversedResult = new ArrayList<>();
		ClassLoader classLoader = FileReverseApplication.class.getClassLoader();

		URL resource = classLoader.getResource(fileName);
		try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
			result = lines.collect(Collectors.toList());
		} catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
		Collections.reverse(result);
		for(String entry : result) {
			StringBuilder reversedString = new StringBuilder();
			reversedString.append(entry);
			reversedString = reversedString.reverse();
			reversedResult.add(reversedString.toString());
		}
		writeIntoFile(reversedResult, fileName);
    }

	private static void writeIntoFile(List<String> reversedResult, String fileName) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/"+fileName+"-Reverse"));

		for (String str : reversedResult) {
			writer.write(str + System.lineSeparator());
		}
		writer.flush();
		writer.close();
	}

}
