package tp.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Triangle2D extends StructureGeometrique {

	private Point2D A;
	private Point2D B;
	private Point2D C;
	
	private double a;
	private double b;
	private double c;
	
	private Point2D center; // cercle circonscrit
	private double rayon; // cercle circonscrit
	
	private List<Triangle2D> voisin = new ArrayList<Triangle2D>();
	
	private Color lineColor;
	
	public Triangle2D(Point2D t1, Point2D t2, Point2D t3) {
		super("");
		this.A = t1;
		this.B = t2;
		this.C = t3;
		this.center = calculCenter();
		this.rayon = calculRayon();		
		this.lineColor = ColorTools.SEGMENT;
		
		double sa = B.getDistance(C);
		double sb = A.getDistance(C);
		double sc = A.getDistance(B);
		
		//System.out.println("sa : " + sa);
		//System.out.println("sb : " + sb);
		//System.out.println("sc : " + sc);

		double prea = ((car(sb) + car(sc) - car(sa)) / (2 * sb * sc));
		double preb = ((car(sa) + car(sc) - car(sb)) / (2 * sa * sc));
		double prec = ((car(sa) + car(sb) - car(sc)) / (2 * sa * sb));

		if(prea > 1)
			prea = 1;
		if(prea < -1)
			prea = -1;
		if(preb > 1)
			prea = 1;
		if(preb < -1)
			prea = -1;
		if(prec > 1)
			prea = 1;
		if(prec < -1)
			prea = -1;

		//System.out.println("prea : " + prea);
		//System.out.println("preb : " + preb);
		//System.out.println("prec : " + prec);

		a = (180 * Math.acos(prea)) / Math.PI;
		b = (180 * Math.acos(preb)) / Math.PI;
		c = (180 *  Math.acos(prec)) / Math.PI;
		
		//System.out.println("A : " + a);
		//System.out.println("B : " + b);
		//System.out.println("C : " + c);
	}

	public Point2D getA() {
		return A;
	}

	public Point2D getB() {
		return B;
	}

	public Point2D getC() {
		return C;
	}
	
	public Point2D getCenter() {
		return center;
	}

	public void setA(Point2D a) {
		this.A = a;
	}

	public void setB(Point2D b) {
		this.B = b;
	}

	public void setC(Point2D c) {
		this.C = c;
	}

	public void setCenter(Point2D center) {
		this.center = center;
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
		points.add(A);
		points.add(B);
		points.add(C);

		Collections.sort(points);

		return points;
	}
	
	
	public Point2D calculCenter() {
		int x1 = A.getX();
		int y1 = A.getY();
		
		int x2 = B.getX();
		int y2 = B.getY();
		
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

		double pab = A.distance(B);
		double pbc = B.distance(C);
		double pac = A.distance(C);
		
		Vector2D vab = new Vector2D(A, B);
		Vector2D vac = new Vector2D(A, C);
		
		double r = (pab * pbc * pac) / (2 * vab.determinant(vac));
		
		if(r < 0)
			r *= -1;

		return r;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.drawLine(A.getX(), A.getY(), B.getX(), B.getY());
		g2d.drawLine(B.getX(), B.getY(), C.getX(), C.getY());
		g2d.drawLine(A.getX(), A.getY(), C.getX(), C.getY());
		
		//Shape theCircle = new Ellipse2D.Double(getCenter().getX() - getRayon(), getCenter().getY() - getRayon(), 2.0 * getRayon(), 2.0 * getRayon());
		//g2d.setColor(Color.BLUE);
		//g2d.draw(theCircle);
	}

	public double getRayon() {
		return rayon;
	}
	
	
	public List<Point2D> getSommets()  {
		
		List<Point2D> points = new ArrayList<Point2D>();
		
		points.add(A);
		points.add(B);
		points.add(C);
		
		return points;		
	}
	
	public void addVoisin(List<Triangle2D> triangles) {
		
		for(Triangle2D triangle : triangles) {
	
			if(voisin.size() == 3)
				return;
			
			if(this.isVoisin(triangle))
				voisin.add(triangle);
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

		/*
		
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
			
			this.setA(tempT1.getA());
			this.setB(tempT1.getB());
			this.setC(tempT1.getC());
			
			t2.setA(tempT2.getA());
			t2.setB(tempT2.getB());
			t2.setC(tempT2.getC());
		}*/

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
		t1.add(flipped.get(1));

		List<Point2D> other = new ArrayList<Point2D>();
		other.addAll(flipper);
		other.addAll(flipper2);
		other.add(flipped.get(0));

		System.out.println("tutut");
		System.out.println(t1.get(0).toString());
		System.out.println(t1.get(1).toString());
		System.out.println(t1.get(2).toString());

		System.out.println(other.get(0).toString());
		System.out.println(other.get(1).toString());
		System.out.println(other.get(2).toString());

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
		return voisin;
	}
	
	public boolean isFlippable(Triangle2D t2) {

		/*
		List<Double> anglesT1 = this.getAngles();
		List<Double> anglesT2 = t2.getAngles();

		List<Double> A1 = new ArrayList<Double>();
		A1.addAll(anglesT1);
		A1.addAll(anglesT2);
		
		Collections.sort(A1);
		System.out.println("\nA1");
		for(Double d: A1) 
			System.out.println(d);
		System.out.println("\nFin");

		/*
		List<Triangle2D> flipped = this.getFlip(t2);
		
		if(flipped.size() == 2) {
			anglesT1 = flipped.get(0).getAngles();
			anglesT2 = flipped.get(1).getAngles();
			List<Double> A2 = new ArrayList<Double>();
			A2.addAll(anglesT1);
			A2.addAll(anglesT2);
					
			Collections.sort(A2);
			
			System.out.println("A1");

			for(Double d: A1) 
				System.out.println(d);
			
			for(int i = 0; i < A1.size(); i++) {
				if(A2.get(i) > A1.get(1))
					return true;
				if(A1.get(i) > A2.get(1))
					return false;
			}
		}
		
		*/

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
}
