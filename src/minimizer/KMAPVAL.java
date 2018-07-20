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
				// gro�es Phi
				return "\u03A6";
		}
		return "";
	}
	
}
