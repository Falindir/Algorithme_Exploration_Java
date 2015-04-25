package tp.tools.Form2D;

import tp.tools.others.ColorTools;
import tp.tools.others.RolePoint;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Point2D extends StructureGeometrique implements Comparable {

	private int _coordX;
	private int y;
	
	private RolePoint role;
	private Color color;
	
	public Point2D(int x, int y) {
		super("");
		_coordX = x;
		this.y = y;
		setRole(RolePoint.NONE);
		this.color = ColorTools.POINT_NONE;
	}
	
	public Point2D(int x, int y, RolePoint role) {
		super("");
		_coordX = x;
		this.y = y;
		setRole(role);
	}
	
	public int getX() {
		return _coordX;
	}

	public int getY() {
		return y;
	}

	public RolePoint getRole() {
		return role;
	}
	
	public void setRole(RolePoint role) {
		this.role = role;
		
		switch(role){
			case BEGIN: 
				color = ColorTools.POINT_BEGIN;
			case INTERSECT:
				color = ColorTools.POINT_INTERSECT;
			case END:
				color = ColorTools.POINT_END;
			case ZONE:
				color = ColorTools.POINT_ZONE;
			default :
				color = ColorTools.POINT_NONE;
		}
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		Rectangle r = new Rectangle(_coordX, y, 3, 3);
		g2d.fill(r);
		drawName(g2d);
	}

	public void draw2(Graphics2D g2d) {
		g2d.setColor(color);
		Rectangle r = new Rectangle(_coordX, y, 4, 4);
		//r.translate(-2, -2);
		g2d.fill(r);
		drawName(g2d);
		//r.translate(2, 2);
	}

	public void drawName(Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		int centeredText = (int) (_coordX - fm.stringWidth(getName())/2 + fm.stringWidth("_"));
		g2d.drawString(getName(), centeredText, (int) (y-2));		
	}
	
    public static boolean isLeft(Point2D a, Point2D b, Point2D c) {
        return ( (b._coordX-a._coordX)*(c.y-a.y) - (b.y-a.y)*(c._coordX-a._coordX) ) >= 0;
    }

	public int distance (Point2D p) {
		
		int X = (int) ((p.getX() - this.getX()) * (p.getX() - this.getX()));
		int Y = (int) ((p.getY() - this.getY()) * (p.getY() - this.getY()));
		
		return (int) Math.sqrt(X + Y);
	}

	public double getDistance(Point2D p) {

		double x = ((p.getX() - this.getX()) * (p.getX() - this.getX()));
		double y = ((p.getY() - this.getY()) * (p.getY() - this.getY()));

		return Math.sqrt(x + y);
	}
	
	public boolean isInFirstX (Point2D p) {
		return this.getX() < p.getX();
	}

	@Override
	public int compareTo(Object o) {
        Point2D other = (Point2D) o;
        int otherX = other.getX();
        if (_coordX < otherX || (_coordX == otherX && this.y < other.getY()))  return -1;
        else if(_coordX == otherX ) return 0;
        else return 1;
	}

	public static boolean equals(Point2D p, Point2D p2)
	{
		if(p==p2)
			return true;
		return p.getX()==p2.getX() && p.getY() == p2.getY();
	}


	@Override
	public String toString() {
		return "Point2D{" +
				"y=" + y +
				", x=" + _coordX +
				'}';
	}

	public static double calculCoordX(double m1, double p1, double m2, double p2) {

		if((m1 - m2) == 0)
			return 0;
		else
			return ((p2 - p1) / (m1 - m2));
	}

	public static double calculCoordY(double m, double p, double x) {
		return (m * x) + p;
	}
}
