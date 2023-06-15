package com.bookcart.dto;

import java.util.Comparator;

public class MediaCoverageComparator implements Comparator<MediaCoverage> {

	String input;

	public MediaCoverageComparator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MediaCoverageComparator(String input) {
//		super();
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	@Override
	public int compare(MediaCoverage o1, MediaCoverage o2) {
		int objectOnePriority = 1;
		int objectTwoPriority = 1;
		if ((o1.title.contains(input) && o1.body.contains(input))) {
			objectOnePriority = 3;
		}
		if (o1.title.contains(input) && !o1.body.contains(input)) {
			objectOnePriority = 2;
		}
		if ((o2.title.contains(input) && o2.body.contains(input))) {
			objectTwoPriority = 3;
		}
		if ((o2.title.contains(input) && !o2.body.contains(input))) {
			objectTwoPriority = 2;
		}
		if (objectOnePriority > objectTwoPriority)
			return -1;
		else if (objectOnePriority < objectTwoPriority)
			return 1;
		else
			return 0;
	}

}
