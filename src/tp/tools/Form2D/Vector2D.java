package tp.tools.Form2D;

import tp.tp1.PointSegment;

public class Vector2D {

	private int x;
	private int y;
	
	public Vector2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D(Point2D p1, Point2D p2) {
		x = p2.getX() - p1.getX();
		y = p2.getY() - p1.getY();
	}

	public Vector2D(PointSegment left, PointSegment right) {
		x =  (int) (right.getX() - left.getX());
		y = (int) (right.getY() - left.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int produitScalaire(Vector2D v) {
		return this.x * v.x + this.y * v.y;
	}
	
	public int determinant(Vector2D v) {
		return this.x * v.y - this.y * v.x;
	}

	public double norm() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
}
