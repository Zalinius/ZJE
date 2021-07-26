package com.zalinius.zje.utilities.structures;

public class Coordinate{
	public final int I;
	public final int J;
	
	public Coordinate(int i, int j) {
		I = i;
		J = j;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + I;
		result = prime * result + J;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (I != other.I)
			return false;
		if (J != other.J)
			return false;
		return true;
	}
}