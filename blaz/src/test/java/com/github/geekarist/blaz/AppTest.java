package com.github.geekarist.blaz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class AppTest {

	private static final String TO_NAME = "ile";
	private static final String FROM_NAME = "ast";

	@Test
	public void testApp() throws IOException {
		// Setup
		App app = new App();
		File dir = Files.createTempDirectory("tmp").toFile();
		dir.mkdirs();
		createInputFiles(dir, "file1.txt", "file2.txt");

		// Test
		app.load(dir.getPath());
		app.rename(FROM_NAME, TO_NAME);

		// Assert
		assertDoesNotContain(dir, "file1.txt", "file2.txt");
		assertContains(dir, "fast1.txt", "fast2.txt");
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
