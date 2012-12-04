package com.github.geekarist.blaz;

import java.util.Arrays;
import java.util.List;

public class ListSplitter {

	public <T> List<List<T>> split(List<T> l) {
		int splitAtIndex = (int) Math.ceil((double) l.size() / 2);
		List<T> l1 = l.subList(0, splitAtIndex);
		List<T> l2 = l.subList(splitAtIndex, l.size());
		return Arrays.asList(l1, l2);
	}

}
