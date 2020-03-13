package com.zalinius.plugins;

import com.zalinius.architecture.Graphical;
import com.zalinius.architecture.Logical;

public abstract class Plugin implements Graphical, Logical {
	
	public enum Type{BEFORE, AFTER};
	
	
	public abstract Type getType();
}
