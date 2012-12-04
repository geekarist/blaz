package com.github.geekarist.blaz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static final String TO_NAME = "ast";
	private static final String FROM_NAME = "ile";

	private static File dir;

	@Before
	public void createTmpDir() throws IOException {
		dir = Files.createTempDirectory("tmp").toFile();
		System.out.printf("Directory [%s] created\n", dir.getPath());
	}

	@After
	public void deleteTmpDir() throws IOException {
		FileUtils.deleteDirectory(dir);
		System.out.printf("Directory [%s] deleted\n", dir.getPath());
	}

	public void checkAppWithFiles(List<String> inputFiles, List<String> outputFiles) throws IOException,
			InterruptedException {
		// Setup
		App app = new App();
		dir.mkdirs();
		createInputFiles(dir, inputFiles.toArray(new String[] {}));

		// Test
		app.load(dir.getPath());
		app.rename(FROM_NAME, TO_NAME);

		// Assert
		assertDoesNotContain(dir, inputFiles.toArray(new String[] {}));
		assertContains(dir, outputFiles.toArray(new String[] {}));
	}

	@Test
	public void testAppWithSomeFiles() throws IOException, InterruptedException {
		checkAppWithFiles( //
				Arrays.asList("file1.txt", "file2.txt"), //
				Arrays.asList("fast1.txt", "fast2.txt"));
	}

	@Test
	public void testAppWithLotsOfFiles() throws IOException, InterruptedException {
		List<String> input = new ArrayList<String>();
		List<String> output = new ArrayList<String>();
		for (int i = 0; i < 3E4; i++) {
			input.add(String.format("file%03d.txt", i));
			output.add(String.format("fast%03d.txt", i));
		}
		checkAppWithFiles(input, output);
	}

	private void assertContains(File dirPath, String... files) throws IOException {
		for (String f : files) {
			File child = new File(dirPath, f);
			Assert.assertTrue( //
					String.format("Directory [%s] should contain file [%s]", dirPath.getPath(), f), //
					child.exists());
		}
	}

	private void assertDoesNotContain(File dirPath, String... files) throws IOException {
		for (String f : files) {
			File child = new File(dirPath, f);
			Assert.assertFalse( //
					String.format("Directory [%s] should not contain file [%s]", dirPath.getPath(), f), //
					child.exists());
		}
	}

	private void createInputFiles(File dirPath, String... files) throws IOException {
		for (String f : files) {
			new File(dirPath, f).createNewFile();
		}
	}

}
