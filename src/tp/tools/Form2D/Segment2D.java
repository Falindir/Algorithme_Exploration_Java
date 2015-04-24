package tp.tools.Form2D;

import tp.tools.ColorTools;
import tp.tools.RolePoint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Segment2D extends StructureGeometrique {

	private Point2D _p1;
	private Point2D _p2;
	private Color lineColor;
	
	public Segment2D(Point2D p1, Point2D p2) {
		super("");
		if(p1.isInFirstX(p2)) {
			_p1 = p1;
			_p2 = p2;
		}
		else {
			_p1 = p2;
			_p2 = p1;
		}
		
		this.lineColor = ColorTools.SEGMENT;
	}

	public Segment2D(String name, Point2D p1, Point2D p2) {
		super(name);
		_p1 = p1;
		_p2 = p2;
		this.lineColor = ColorTools.SEGMENT;
	}
	
	public Point2D getP1() {
		return _p1;
	}

	public void setP1(Point2D p1) {
		_p1 = p1;
	}

	public Point2D getP2() {
		return _p2;
	}

	public void setP2(Point2D p2) {
		_p2 = p2;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(lineColor);
		g2d.drawLine(_p1.getX(), _p1.getY(), _p2.getX(), _p2.getY());
	}

	public Color getLineColor() {
		return lineColor;
	}

	public boolean intersectent(Segment2D s) {

		Vector2D VP1P2 = new Vector2D(this.getP1(), this.getP2());
		Vector2D VP3P4 = new Vector2D(s.getP1(), s.getP2());

		Vector2D VP1P3 = new Vector2D(this.getP1(), s.getP1());
		Vector2D VP1P4 = new Vector2D(this.getP1(), s.getP2());

		Vector2D VP3P1 = new Vector2D(s.getP1(), this.getP1());
		Vector2D VP3P2 = new Vector2D(s.getP1(), this.getP2());

		float detS1 = VP1P2.determinant(VP1P3);
		float detS2 = VP1P2.determinant(VP1P4);
		float detS3 = VP3P4.determinant(VP3P1);
		float detS4 = VP3P4.determinant(VP3P2);

		if ((detS1 * detS2) < 0  && (detS3 * detS4) < 0)
			return true;

		return false;
	}

	public Point2D getIntersectedPoint(Segment2D s) {

		double m1 = this.calculCoefSegment();
		double p1 = this.calculConstSegment(m1);

		double m2 = s.calculCoefSegment();
		double p2 = s.calculConstSegment(m2);

		double coordX = Point2D.calculCoordX(m1, p1, m2, p2);
		double coordY = Point2D.calculCoordY(m1, p1, coordX);

		Point2D ps = new Point2D((int) coordX , (int) coordY, RolePoint.INTERSECT);

		return ps;
	}

	public double calculCoefSegment() {
		if((_p2.getX() - _p1.getX()) == 0)
			return 0;
		else
			return (_p2.getY()-_p1.getY()) / (_p2.getX() - _p1.getX());
	}

	public double calculConstSegment(double m) {
		return _p1.getY() - (m * _p1.getX());
	}

	public static List<Segment2D> constructSegment (List<Point2D> points) {
		
		List<Segment2D> segments = new ArrayList<Segment2D>();
		
		for(int i = 0; i < points.size() - 1; i = i + 2)
			segments.add(new Segment2D(points.get(i), points.get(i + 1)));

		return segments;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
}
