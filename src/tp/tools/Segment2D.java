package tp.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Segment2D extends StructureGeometrique {

	private Point2D p1;
	private Point2D p2;
	private Color lineColor;
	
	public Segment2D(Point2D p1, Point2D p2) {
		super("");
		if(p1.isInFirstX(p2)) {
			this.p1 = p1;
			this.p2 = p2;
		}
		else {
			this.p1 = p2;
			this.p2 = p1;
		}
		
		this.lineColor = ColorTools.SEGMENT;
	}

	public Segment2D(String name, Point2D p1, Point2D p2) {
		super(name);
		this.p1 = p1;
		this.p2 = p2;
		this.lineColor = ColorTools.SEGMENT;
	}
	
	public Point2D getP1() {
		return p1;
	}

	public void setP1(Point2D p1) {
		this.p1 = p1;
	}

	public Point2D getP2() {
		return p2;
	}

	public void setP2(Point2D p2) {
		this.p2 = p2;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(lineColor);
		g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	public Color getLineColor() {
		return lineColor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Segment2D> constructSegment (List<Point2D> points) {
		
		List<Segment2D> segments = new ArrayList<Segment2D>();
		
		
		
		
		return segments;
	}
}
