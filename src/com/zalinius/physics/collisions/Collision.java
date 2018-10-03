package com.zalinius.physics.collisions;

import java.util.ArrayList;
import java.util.List;

import com.zalinius.architecture.ICollidable;
import com.zalinius.geometry.*;
import com.zalinius.geometry.oneDimensional.Segment;
import com.zalinius.physics.Point2D;
import com.zalinius.utilities.Debug;
import com.zalinius.utilities.Linear;
import com.zalinius.utilities.Tuple;
import com.zalinius.utilities.ZMath;
import com.zalinius.utilities.Linear.Intersection;

public class Collision {

	public static boolean isOverlapping(ICollidable object1, ICollidable object2) {
		return isOverlapping(object1.getCollisionBox(), object2.getCollisionBox());
	}
	
	public static boolean isOverlapping(Shape s1, Shape s2) {
		if (s1 instanceof Circle && s2 instanceof Circle) {
			Circle c1 = (Circle) s1;
			Circle c2 = (Circle) s2;
			return isOverlapping(c1, c2);
		}

		if (s1 instanceof Rectangle && s2 instanceof Rectangle) {
			Rectangle r1 = (Rectangle) s1;
			Rectangle r2 = (Rectangle) s2;
			return isOverlapping(r1, r2);
		}
		
		if(s1 instanceof Segment && s2 instanceof Segment) {
			Segment g1 = (Segment) s1;
			Segment g2 = (Segment) s2;
			return isOverlapping(g1, g2);
		}

		throw new RuntimeException("Overlapping of " + s1.getClass().toString() + " and " + s2.getClass().toString()
				+ " has not been implemented yet");
	}

	private static boolean isOverlapping(Circle c1, Circle c2) {
		double distance = Point2D.distance(c1.center(), c2.center());
		double radii = c1.radius() + c2.radius();

		return distance <= radii;
	}

	private static boolean isOverlapping(Rectangle r1, Rectangle r2) {
		if (r1.contains(r2.center()) || r2.contains(r1.center())) {
			System.err.println("True");
			return true;
		}
		
		List<Tuple<Segment>> edgePairs = new ArrayList<>();
		
		for (Segment edgeR1 : r1.edges()) { //create 16 edge pairs
			for (Segment edgeR2 : r2.edges()) {
				edgePairs.add(new Tuple<>(edgeR1, edgeR2));
			}
		}
		
		for (Tuple<Segment> edges : edgePairs) {
			if(isOverlapping(edges.first(), edges.second())) {
				System.err.println("True " + edges.first() + " : " + edges.second());
				System.err.println(isOverlapping(edges.first(), edges.second()));
				return true;
			}
		}

		return false;
	}
	
	private static boolean isOverlapping(Segment s1, Segment s2) {
		Linear l1 = s1.getFunction();
		Linear l2 = s2.getFunction();
		switch (Linear.hasIntersection(l1, l2)) {
		case ONE:
			double xIntercept = Linear.xIntercept(l1, l2);
			if(l1.isVertical() || l2.isVertical()) {
				if(l1.isVertical() && l2.isVertical()) {
					return ZMath.isBetween(s1.start().y(), s1.end().y(), s2.start().y()) ||
						   ZMath.isBetween(s1.start().y(), s1.end().y(), s2.end().y()) ||
						   ZMath.isBetween(s2.start().y(), s2.end().y(), s1.start().y()) ||
						   ZMath.isBetween(s2.start().y(), s2.end().y(), s1.end().y());
				}
				else {
					if(l1.isVertical()) {
						return ZMath.isBetween(s2.start().x(), s2.end().x(), xIntercept) && ZMath.isBetween(s1.start().y(), s1.end().y(), l2.f(xIntercept));
					}else {
						return ZMath.isBetween(s1.start().x(), s1.end().x(), xIntercept) && ZMath.isBetween(s2.start().y(), s2.end().y(), l1.f(xIntercept));
					}
				}
			}
			else {
				return (ZMath.isBetween(s1.start().x(), s1.end().x(), xIntercept) && ZMath.isBetween(s2.start().x(), s2.end().x(), xIntercept));
			}
		case INFINITE:
			if(l1.isVertical()) {
				return areTwoVerticalLinesOverlapping(s1, s2);
			}
			else {
				return areTwoNonVerticalLinesOverlapping(s1, s2);
			}
		case NONE:
			return false;
		default:
			throw new RuntimeException();
		}
	}
	
	private static boolean areTwoVerticalLinesOverlapping(Segment s1, Segment s2){
		return ZMath.isBetween(s1.start().y(), s1.end().y(), s2.start().y()) ||
			   ZMath.isBetween(s1.start().y(), s1.end().y(), s2.end().y()) ||
			   ZMath.isBetween(s2.start().y(), s2.end().y(), s1.start().y()) ||
			   ZMath.isBetween(s2.start().y(), s2.end().y(), s1.end().y());
	}
	
	private static boolean areTwoNonVerticalLinesOverlapping(Segment s1, Segment s2) {
		return ZMath.isBetween(s1.start().x(), s1.end().x(), s2.start().x()) ||
			   ZMath.isBetween(s1.start().x(), s1.end().x(), s2.end().x()) ||
			   ZMath.isBetween(s2.start().x(), s2.end().x(), s1.start().x()) ||
			   ZMath.isBetween(s2.start().x(), s2.end().x(), s1.end().x());
	}

}
