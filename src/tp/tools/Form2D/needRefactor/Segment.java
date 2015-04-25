package tp.tools.Form2D.needRefactor;


import tp.tools.others.ColorTools;
import tp.tools.Form2D.Vector2D;
import tp.tools.others.RolePoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Segment {
	private String name;
	private PointSegment left;
	private PointSegment right;
	private Color lineColor;
	
	//Precondition: p1.x < p2.x par convention et pour les besoins de l'algorithme de balayage gauche droite
	public Segment(PointSegment p1, PointSegment p2) {
		this.left = p1;
		this.right = p2;
		this.lineColor =  ColorTools.SEGMENT;
		this.left.setSegmentPoint(this);
		this.right.setSegmentPoint(this);	
	}

	public void drawSegment(Graphics2D g2d) {
		g2d.setColor(lineColor);
		g2d.drawLine((int) left.x , (int) left.y , (int) right.x, (int) right.y);
	}
	
	public String getNameSegment() {
		return name;
	}

	public void setNameSegment(String name) {
		this.name = name;
	}

	public PointSegment getLeftPoint() {
		return left;
	}

	public void setLeftPoint(PointSegment left) {
		this.left = left;
	}

	public PointSegment getRightPoint() {
		return right;
	}

	public void setRightPoint(PointSegment right) {
		this.right = right;
	}
	
	public static List<Segment> randomSegment(int nombreSegment) {
		
		List<Segment> segments = new ArrayList<Segment>();
		
		Random rand = new Random();
		
		for (int i = 0; i < nombreSegment; i++) {
			PointSegment p1 = new PointSegment(rand.nextInt(500-20)+20, rand.nextInt(700-100)+100, RolePoint.BEGIN);
			
			PointSegment p2 = new PointSegment((int) (rand.nextInt(100)+p1.getX()+1), (int) (rand.nextInt(100)+p1.getY()-50), RolePoint.END);
	
			Segment s = new Segment(p1, p2);
			
			s.setNameSegment("S" + i);
			
			segments.add(s);
		}
		
		return segments;
	}
	
	public boolean intersectent(Segment s) {

		Vector2D VP1P2 = new Vector2D(this.left, this.right);
		Vector2D VP3P4 = new Vector2D(s.left, s.right);

		Vector2D VP1P3 = new Vector2D(this.left, s.left);
		Vector2D VP1P4 = new Vector2D(this.left, s.right);

		Vector2D VP3P1 = new Vector2D(s.left, this.left);
		Vector2D VP3P2 = new Vector2D(s.left, this.right);

		float detS1 = VP1P2.determinant(VP1P3);
		float detS2 = VP1P2.determinant(VP1P4);
		float detS3 = VP3P4.determinant(VP3P1);
		float detS4 = VP3P4.determinant(VP3P2);

		if ((detS1 * detS2) < 0  && (detS3 * detS4) < 0)
			return true;
	
		return false;
	}
	
	public PointSegment getIntersectedPoint(Segment s) {

		double m1 = this.calculCoefSegment();
		double p1 = this.calculConstSegment(m1);
		
		double m2 = s.calculCoefSegment();
		double p2 = s.calculConstSegment(m2);
		
		double coordX = PointSegment.calculCoordX(m1, p1, m2, p2);
		double coordY = PointSegment.calculCoordY(m1, p1, coordX);


		PointSegment ps = new PointSegment((int) coordX , (int) coordY, RolePoint.INTERSECT);
	
		return ps;
	}
	
	public double calculCoefSegment() {
		if((right.getX() - left.getX()) == 0)
			return 0;
		else 
			return (right.getY()-left.getY()) / (right.getX() - left.getX());
	}
	
	public double calculConstSegment(double m) {
		return left.getY() - (m * left.getX()); 
	}
	

}
