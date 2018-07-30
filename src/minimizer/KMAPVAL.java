package minimizer;

public enum KMAPVAL {
	FALSE, TRUE, DONTCARE;

	@Override
	public String toString() {
		switch(this) {
			case TRUE:
				return "1";
			case FALSE:
				return "0";
			case DONTCARE:
				// groﬂes Phi
				return "\u03A6";
		}
		return "";
	}
	
	public static KMAPVAL[] toKMAPVALArray(String str) {
		KMAPVAL[] vals = new KMAPVAL[str.length()];
		for(int i = 0; i < str.length(); ++i) {
			switch(str.charAt(i)) {
				case '0':
					vals[i] = KMAPVAL.FALSE;
					break;
				case '1':
					vals[i] = KMAPVAL.TRUE;
					break;
				default:
					vals[i] = KMAPVAL.DONTCARE;
					break;
			}
		}
		return vals;	}
	}