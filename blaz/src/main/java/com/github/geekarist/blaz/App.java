package com.github.geekarist.blaz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
	private File[] filesToRename;

	public void load(String string) {
		System.out.println("Loading [" + string + "]...");
		File f = new File(string);
		filesToRename = f.listFiles();
	}

	public void rename(final String from, final String to) throws IOException, InterruptedException {
		List<File> asList = Arrays.asList(filesToRename);
		List<List<File>> files = new ListSplitter().split(asList);

		RenamerThread t1 = new RenamerThread(files.get(0), from, to);
		RenamerThread t2 = new RenamerThread(files.get(1), from, to);

		t1.start();
		t2.start();

		t1.join();
		t2.join();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String dirPath = args[0];
		String from = args[1];
		String to = args[2];
		App app = new App();
		app.load(dirPath);
		app.rename(from, to);
	}
}
