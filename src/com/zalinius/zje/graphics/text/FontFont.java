package com.zalinius.zje.graphics.text;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FontFont implements Iterator<Font>{

	private final List<Font> availableFonts;
	private final Random random;

	public FontFont() {
		this(getSystemFonts(), new Random());
	}

	public FontFont(Collection<Font> fonts) {
		this(fonts, new Random());
	}
	public FontFont(Random random) {
		this(getSystemFonts(), random);
	}

	public FontFont(Collection<Font> fonts, Random random) {
		this.availableFonts = new ArrayList<>(fonts);
		this.random = random;
	}

	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public Font next() {
		return availableFonts.get(random.nextInt(availableFonts.size()));
	}
	
	
	public static List<Font> getSystemFonts(){
		return getSystemFonts(new ArrayList<>());
	}
	
	public static List<Font> getSystemFonts(Collection<Character> charactersRequired){
		return Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).
				stream().
				map(name -> new Font(name, Font.BOLD, 1)).
				filter(font -> charactersRequired.stream().allMatch(font::canDisplay)).
				collect(Collectors.toList());
	}
}
