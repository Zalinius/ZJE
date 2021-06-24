package com.zalinius.zje.music;

public class Length {
	
	public final double length; //in beats
	
	public Length(double length) {
		this.length = length;
	}
	
	public Length(Length ... lengths) {
		double totalLength = 0;
		for (int i = 0; i < lengths.length; i++) {
			totalLength += lengths.length;
		}
		
		this.length = totalLength;
	}
	
	
	public static final Length WHOLE = new Length(4.0);
	public static final Length HALF = new Length(2.0);
	public static final Length QUARTER = new Length(1.0);
	public static final Length EIGHTH = new Length(1.0/2.0);
	public static final Length SIXTEENTH = new Length(1.0/4.0);
	

}
