package tp.tp1;

import tp.tools.ColorTools;
import tp.tools.RolePoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PointSegment extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public static int size = 3;
	private Color color;
	private RolePoint role;
	private Segment segment;

	public PointSegment() {
		
	}
	
	public PointSegment(int x, int y, RolePoint r) {
		super(x ,y, 2 * PointSegment.size,2 * PointSegment.size);
		
		this.role = r;
		
		switch(role){
			case BEGIN:
				color = ColorTools.POINT_BEGIN;
				break;
			case INTERSECT:
				color = ColorTools.POINT_INTERSECT;
				break;
			case END:
				color = ColorTools.POINT_END;
				break;
		}
	}

	public void drawPointSegment(Graphics2D g2d) {
		g2d.setColor(color);
		this.translate(-PointSegment.size, -PointSegment.size); 
		g2d.fill(this);	
		this.translate(PointSegment.size, PointSegment.size);
	}

	public void print() {
		System.out.println("x = " + x + " y = " + y+" w = " + width + " h = " + height + " role = "+role);
	}

	public Color getColorPoint() {
		return color;
	}

	public void setColorPoint(Color color) {
		this.color = color;
	}

	public RolePoint getRolePoint() {
		return role;
	}

	public void setRolePoint(RolePoint role) {
		this.role = role;
	}

	public Segment getSegmentPoint() {
		return segment;
	}

	public void setSegmentPoint(Segment segment) {
		this.segment = segment;
	}	
	
	public boolean isLeftOf(PointSegment p2) {
		if(this.getX() < p2.getY())
			return true;
		
		return false;
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

	
	public static List<PointSegment> randomPoint(int nbPoint, int w, int h) {
	
		List<PointSegment> points = new ArrayList<PointSegment>();
		
		Random rand = new Random();
		
		
		while(points.size() <= nbPoint) {
			
			PointSegment p = new PointSegment(rand.nextInt(500), rand.nextInt(700), RolePoint.BEGIN);
			
		
			double centerX = w / 2;
			double centerY = h / 2;
			
			PointSegment center = new PointSegment((int)centerX, (int)centerY, RolePoint.END);
			
			if(center.distance(p) < 200 && center.distance(p) > 100) {
				System.out.println(center.distance(p));
				
				points.add(p);
				
			}
			
		}
		
		
		return points;
	}
	
	public int distance (PointSegment p) {
		
		int X = (int) ((p.getX() - this.getX()) * (p.getX() - this.getX()));
		
		int Y = (int) ((p.getY() - this.getY()) * (p.getY() - this.getY()));
		
		return (int) Math.sqrt(X + Y);
				
	}
	
	
}

