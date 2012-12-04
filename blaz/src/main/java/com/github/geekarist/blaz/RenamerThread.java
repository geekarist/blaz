package com.github.geekarist.blaz;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RenamerThread extends Thread {

	private List<File> list;
	private String from;
	private String to;

	public RenamerThread(List<File> list, String from, String to) {
		this.list = list;
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		System.out.printf("Renaming %d files using [%s]...\n", list.size(), toString());
		for (File f : list) {
			String newPath = f.getPath().replaceAll(from, to);
			try {
				FileUtils.moveFile(f, new File(newPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.printf("Done for [%s]...\n", toString());
	}
}
