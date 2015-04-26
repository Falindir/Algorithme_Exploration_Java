package tp.tools.Form2D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle2D extends StructureGeometrique {

	private Point2D _A;
	private Point2D _B;
	private Point2D C;
	
	private double a;
	private double b;
	private double c;
	
	private Point2D _center; // cercle circonscrit
	private double _rayon; // cercle circonscrit
	
	private List<Triangle2D> _neighbors = new ArrayList<Triangle2D>();

	public Triangle2D(Point2D t1, Point2D t2, Point2D t3) {
		super("");
		_A = t1;
		_B = t2;
		this.C = t3;
		_center = calculCenter();
		_rayon = calculRayon();
		
		double sa = _B.getDistance(C);
		double sb = _A.getDistance(C);
		double sc = _A.getDistance(_B);

		double prea = ((car(sb) + car(sc) - car(sa)) / (2 * sb * sc));
		double preb = ((car(sa) + car(sc) - car(sb)) / (2 * sa * sc));
		double prec = ((car(sa) + car(sb) - car(sc)) / (2 * sa * sb));

		if(prea > 1)
			prea = 1;
		if(prea < -1)
			prea = -1;
		if(preb > 1)
			preb = 1;
		if(preb < -1)
			preb = -1;
		if(prec > 1)
			prec = 1;
		if(prec < -1)
			prec = -1;

		a = (180 * Math.acos(prea)) / Math.PI;
		b = (180 * Math.acos(preb)) / Math.PI;
		c = (180 *  Math.acos(prec)) / Math.PI;
	}

	public Point2D getA() {
		return _A;
	}

	public Point2D getB() {
		return _B;
	}

	public Point2D getC() {
		return C;
	}
	
	public Point2D getCenter() {
		return _center;
	}

	public void setA(Point2D a) {
		_A = a;
	}

	public void setB(Point2D b) {
		_B = b;
	}

	public void setC(Point2D c) {
		this.C = c;
	}

	public void setCenter(Point2D center) {
		_center = center;
	}
	
	
	public List<Double> getAngles() {
		List<Double> angles = new ArrayList<Double>();
		
		angles.add(a);
		angles.add(b);
		angles.add(c);
		
		Collections.sort(angles);
		
		return angles;
	}

	public List<Point2D> getPoints() {
		List<Point2D> points = new ArrayList<Point2D>();
		points.add(_A);
		points.add(_B);
		points.add(C);

		Collections.sort(points);

		return points;
	}
	
	
	public Point2D calculCenter() {
		int x1 = _A.getX();
		int y1 = _A.getY();
		
		int x2 = _B.getX();
		int y2 = _B.getY();
		
		int x3 = C.getX();
		int y3 = C.getY();
		
		double v,m1, m2, m3, n1, n2, n3;
		m1 = 2.*(x3-x2); m2 = -2.*(y2-y3); m3 = (y2-y3)*(y2+y3)-(x3-x2)*(x2+x3);
		n1 = 2.*(x3-x1); n2 = -2.*(y1-y3); n3 = (y1-y3)*(y1+y3)-(x3-x1)*(x1+x3);
		v=1./(n2*m1-n1*m2);
		double x = v*(n3*m2-n2*m3);
		double y = v*(n1*m3-n3*m1);
  
	    Point2D center = new Point2D((int) x, (int) y);
	    center.setName("O");
	      
	    return center;	
	}
	
	
	public double calculRayon() {

		double pab = _A.distance(_B);
		double pbc = _B.distance(C);
		double pac = _A.distance(C);
		
		Vector2D vab = new Vector2D(_A, _B);
		Vector2D vac = new Vector2D(_A, C);
		
		double r = (pab * pbc * pac) / (2 * vab.determinant(vac));
		
		if(r < 0)
			r *= -1;

		return r;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.drawLine(_A.getX(), _A.getY(), _B.getX(), _B.getY());
		g2d.drawLine(_B.getX(), _B.getY(), C.getX(), C.getY());
		g2d.drawLine(_A.getX(), _A.getY(), C.getX(), C.getY());
	}

	public double getRayon() {
		return _rayon;
	}
	
	
	public List<Point2D> getSommets()  {
		
		List<Point2D> points = new ArrayList<Point2D>();
		
		points.add(_A);
		points.add(_B);
		points.add(C);
		
		return points;		
	}
	
	public void addVoisin(List<Triangle2D> triangles) {
		
		for(Triangle2D triangle : triangles) {
	
			if(_neighbors.size() == 3)
				return;
			
			if(this.isVoisin(triangle))
				_neighbors.add(triangle);
		}
	}
	
	public Point2D getOtherSommet(Point2D p1, Point2D p2) {
		
		List<Point2D> sommets = new ArrayList<Point2D>();
		
		sommets.addAll(getSommets());
		
		sommets.remove(p1);
		sommets.remove(p2);
		
		return sommets.get(0);
	}
	
	
	
	public boolean isVoisin(Triangle2D t2) {
		List<Point2D> sommets = new ArrayList<Point2D>();
		sommets.addAll(getSommets());
		sommets.retainAll(t2.getSommets());
		if(sommets.size() == 2)
			return true;
		return false;
	}


	public List<Segment2D> getEdges()
	{
		List<Segment2D> l=new ArrayList<Segment2D>();
		l.add(new Segment2D(_A, _B));
		l.add(new Segment2D(_B, C));
		l.add(new Segment2D(C, _A));
		return l;
	}
	
	public List<Triangle2D> getFlip(Triangle2D t2) {
		List<Triangle2D> triangles = new ArrayList<Triangle2D>();
		
		List<Point2D> sommetCommun = new ArrayList<Point2D>();
		
		for(Point2D t1p : getSommets()) {
			for(Point2D t2p : t2.getSommets()) {
				if(t1p.equals(t2p)) {
					sommetCommun.add(t1p);
				}
			}
		}
		
		if(sommetCommun.size() == 2) { 
			
			Point2D c1 = getOtherSommet(sommetCommun.get(0), sommetCommun.get(1));
			Point2D c2 = t2.getOtherSommet(sommetCommun.get(0), sommetCommun.get(1));
			
			Triangle2D tempT1 = new Triangle2D(c1, sommetCommun.get(0), c2);
			Triangle2D tempT2 = new Triangle2D(c1, sommetCommun.get(1), c2);
			
			triangles.add(tempT1); 
			triangles.add(tempT2);
		}
		
		return triangles;
	}
	
	public void flip(Triangle2D t2) {
		//Found not common point in first triangle
		List<Point2D> flipper = new ArrayList<Point2D>();
		flipper.addAll(getPoints());
		flipper.removeAll(t2.getPoints());

		//Found not common point in second triangle
		List<Point2D> flipper2 = new ArrayList<Point2D>();
		flipper2.addAll(t2.getPoints());
		flipper2.removeAll(getPoints());

		//Found common points in triangle
		List<Point2D> flipped = new ArrayList<Point2D>();
		flipped.addAll(getPoints());
		flipped.removeAll(flipper);

		//Create
		List<Point2D> t1 = new ArrayList<Point2D>();
		t1.addAll(flipper);
		t1.addAll(flipper2);
		t1.add(flipped.get(0));

		List<Point2D> other = new ArrayList<Point2D>();
		other.addAll(flipper);
		other.addAll(flipper2);
		other.add(flipped.get(1));

		setA(other.get(0));
		setB(other.get(1));
		setC(other.get(2));

		this.setCenter(this.calculCenter());

		t2.setA(t1.get(0));
		t2.setB(t1.get(1));
		t2.setC(t1.get(2));

		t2.setCenter(t2.calculCenter());
	}
	
	public List<Triangle2D> getVoisin() {
		return _neighbors;
	}
	
	public boolean isFlippable(Triangle2D t2) {

		Circle2D circle = new Circle2D(this);
		List<Point2D> flipper2 = new ArrayList<Point2D>();
		flipper2.add(t2.getA());
		flipper2.add(t2.getB());
		flipper2.add(t2.getC());
		flipper2.remove(getA());
		flipper2.remove(getB());
		flipper2.remove(getC());

		return circle.isInCircle(flipper2.get(0));
	}

	public static double car(double n) {
		return n * n;
	}

	public Circle2D getCircumscribedCircle() {
		return null;
	}

	public double getRadiusCircle() {
		return calculRayon();
	}
}
