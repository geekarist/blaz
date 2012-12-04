package com.github.geekarist.blaz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ListSplitterTest {

	@Test
	public void testSplit() {
		List<String> l = Arrays.asList("1", "2", "3", "4", "5");
		ListSplitter s = new ListSplitter();
		List<List<String>> split = s.split(l);
		Assert.assertEquals(Arrays.asList("1", "2", "3"), split.get(0));
		Assert.assertEquals(Arrays.asList("4", "5"), split.get(1));
	}

	@Test
	public void testSplitListOfFiles() throws IOException {
		File f1 = null;
		File f2 = null;
		File f3 = null;
		File f4 = null;
		File f5 = null;
		File f6 = null;
		try {
			f1 = File.createTempFile("prefix", "suffix");
			f2 = File.createTempFile("prefix", "suffix");
			f3 = File.createTempFile("prefix", "suffix");
			f4 = File.createTempFile("prefix", "suffix");
			f5 = File.createTempFile("prefix", "suffix");
			f6 = File.createTempFile("prefix", "suffix");

			List<File> l = Arrays.asList(f1, f2, f3, f4, f5, f6);
			ListSplitter s = new ListSplitter();
			List<List<File>> split = s.split(l);

			Assert.assertEquals(Arrays.asList(f1, f2, f3), split.get(0));
			Assert.assertEquals(Arrays.asList(f4, f5, f6), split.get(1));
		} finally {
			delete(f1);
			delete(f2);
			delete(f3);
			delete(f4);
			delete(f5);
			delete(f6);
		}
	}

	private void delete(File f1) {
		if (f1 != null) {
			f1.delete();
		}
	}

}
