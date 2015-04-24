package tp.tools.Form2D;

import tp.tp1.PointSegment;

public class Vector2D {

	private int _vx;
	private int _vy;

	public Vector2D(Point2D p1, Point2D p2) {
		_vx = p2.getX() - p1.getX();
		_vy = p2.getY() - p1.getY();
	}

	public Vector2D(PointSegment left, PointSegment right) {
		_vx =  (int) (right.getX() - left.getX());
		_vy = (int) (right.getY() - left.getY());
	}

	public int getX() {
		return _vx;
	}

	public void setX(int x) {
		_vx = x;
	}

	public int getY() {
		return _vy;
	}

	public void setY(int y) {
		_vy = y;
	}
	
	public int produitScalaire(Vector2D v) {
		return _vx * v._vx + _vy * v._vy;
	}
	
	public int determinant(Vector2D v) {
		return _vx * v._vy - _vy * v._vx;
	}

	public double norm() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}
}
