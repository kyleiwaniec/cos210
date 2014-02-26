package testing;

public class MetaSet {

	String returnSet = null;

	MetaSet() {
		returnSet = "";
	}

	private String genSeqSet(Character from, Character to) {
		String result = "";
		if (from < to) {
			for (int i = from; i <= to; i++) {
				result += String.valueOf((char) i);
			}
		}
		return result;
	}

	private Boolean isSetSequence(Character from, Character to) {
		return (to - from == 1);
	}

	private Boolean isInSet(Character c, Character from, Character to) {
		return (to - from == 1);
	}

	public String toSet(String s) {
		returnSet = genSeqSet(s.charAt(0), s.charAt(1));
		return returnSet;
	}
}
