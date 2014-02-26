package testing;

public class Settings {

	public enum KEYS {
		KEY_ENABLED("enabled_"), KEY_AUTOMATIC("automatic_"), KEY_DISPLAY_NAME("displayName_");

		private String encodedString = "";

		KEYS(String s) {
			this.encodedString = s;
		}

		public String getEncString() {
			return this.encodedString;

		}

		public static KEYS fromString(String s) {
			if (s != null) {
				for (KEYS sv : KEYS.values()) {
					if (s.equals(sv.getEncString())) {
						return sv;
					}
				}
			}
			return null;
		}
	}
}
