package discussion_group;

public class JavaHashing {
	public int partA(Integer s, int range) {
		return Math.abs(s.hashCode()) % range;
	}

	public int partB(String s) {
		int hash = 0;
		int skip = Math.max(1, s.length() / 8);
		for (int i = 0; i < s.length(); i += skip)
			hash = hash * 37 + s.charAt(i);
		return hash;
	}
}
