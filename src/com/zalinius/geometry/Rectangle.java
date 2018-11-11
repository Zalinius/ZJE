package com.zalinius.geometry;

import java.util.Iterator;

import com.zalinius.geometry.oneDimensional.Segment;
import com.zalinius.physics.Point2D;
import com.zalinius.physics.Vector2D;
import com.zalinius.utilities.ZMath;

public class Rectangle extends Shape{
	private Point2D position;
	private double width, height;
	 	
	public Rectangle(double width, double height) {
		this(new Point2D(), width, height);
	}
	
	public Rectangle(Point2D position, double width, double height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public void moveTo(Point2D newPosition) {
		position = newPosition;
	}

	public double diagonal() {
		return Math.sqrt(width*width + height*height);
	}
	
	public double area() {
		return width*height;
	}
	
	public Point2D center() {
		return Point2D.add(position, new Vector2D(width/2, height/2));
	}

	@Override
	public boolean contains(Point2D p) {
		return ZMath.isBetween(position.x, position.x + width, p.x)
			&& ZMath.isBetween(position.y, position.y + height, p.y);
	}
	
	public Iterable<Segment> edges(){
		return new Iterable<Segment>() {			
			@Override
			public Iterator<Segment> iterator() {
				return new SegmentIterator();
			}
		};
		
	}
	
	public Point2D topLeft() {
		return position;
	}
	
	public Point2D topRight() {
		return new Point2D(position.x + width, position.y );
	}
	
	public Point2D botLeft() {
		return new Point2D(position.x, position.y + height);
	}
	public Point2D botRight() {
		return new Point2D(position.x + width, position.y + height);
	}
	
	private class SegmentIterator implements Iterator<Segment>{

		int i = 0;
		
		public SegmentIterator() {
		}

		@Override
		public boolean hasNext() {
			return i < 4;
		}

		@Override
		public Segment next() {
			int switchIndex = i;
			++i;
			switch(switchIndex) {
			case 0:
				return new Segment(topLeft(), topRight());
			case 1:
				return new Segment(topRight(), botRight());
			case 2:
				return new Segment(botRight(), botLeft());
			case 3:
				return new Segment(botLeft(), topLeft());
			default:
				throw new IndexOutOfBoundsException(": " + i);
			}
			
		}
		
	}
}
