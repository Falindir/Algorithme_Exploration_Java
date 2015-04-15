package tp.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Triangle2D extends StructureGeometrique {

	private Point2D t1;
	private Point2D t2;
	private Point2D t3;
	
	private double a;
	private double b;
	private double c;
	
	private Point2D center; // cercle circonscrit
	private double rayon; // cercle circonscrit
	
	private List<Triangle2D> voisin = new ArrayList<Triangle2D>();
	
	private Color lineColor;
	
	public Triangle2D(Point2D t1, Point2D t2, Point2D t3) {
		super("");
		System.out.println("contructeur");
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.center = calculCenter();
		this.rayon = calculRayon();		
		this.lineColor = ColorTools.SEGMENT;
		
		double t1t2 = t1.distance(t2);
		double t2t3 = t2.distance(t3);
		double t3t1 = t3.distance(t1);
		
		System.out.println("1 : " + t1t2);
		System.out.println("2 : " + t2t3);
		System.out.println("3 : " + t3t1);
		
		System.out.println("a11 " + ((t1t2*t1t2)+(t3t1*t3t1)-(t2t3*t2t3)));
		System.out.println("a12 " + 2*(t1t2*t3t1));
		System.out.println("a13 " + ((t1t2*t1t2)+(t3t1*t3t1)-(t2t3*t2t3))/2*(t1t2*t3t1));
		
		a = Math.acos(((t1t2*t1t2)+(t3t1*t3t1)-(t2t3*t2t3))/2*(t1t2*t3t1));
		b = Math.acos(((t1t2*t1t2)+(t2t3*t2t3)-(t3t1*t3t1))/2*(t1t2*t2t3));
		c = Math.acos(((t3t1*t3t1)+(t2t3*t2t3)-(t1t2*t1t2))/2*(t3t1*t2t3));
		
		System.out.println("A : " + a);
		System.out.println("B : " + b);
		System.out.println("C : " + c);
	}
	
	public Triangle2D(String name, Point2D t1, Point2D t2, Point2D t3) {
		super(name);
		System.out.println("contructeur");
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.rayon = calculRayon();		
		this.center = calculCenter();
		this.lineColor = ColorTools.SEGMENT;
		
		double t1t2 = t1.distance(t2);
		double t2t3 = t2.distance(t3);
		double t3t1 = t3.distance(t1);
		
		a = Math.acos(((t1t2*t1t2)+(t3t1*t3t1)-(t2t3*t2t3))/(t1t2*t3t1));
		b = Math.acos(((t1t2*t1t2)+(t2t3*t2t3)-(t3t1*t3t1))/(t1t2*t2t3));
		c = Math.acos(((t3t1*t3t1)+(t2t3*t2t3)-(t1t2*t1t2))/(t3t1*t2t3));
	}

	public Point2D getT1() {
		return t1;
	}

	public Point2D getT2() {
		return t2;
	}

	public Point2D getT3() {
		return t3;
	}
	
	public Point2D getCenter() {
		return center;
	}

	public void setT1(Point2D t1) {
		this.t1 = t1;
	}

	public void setT2(Point2D t2) {
		this.t2 = t2;
	}

	public void setT3(Point2D t3) {
		this.t3 = t3;
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
	
	
	
	public Point2D calculCenter() {
		int x1 = t1.getX();
		int y1 = t1.getY();
		
		int x2 = t2.getX();
		int y2 = t2.getY();
		
		int x3 = t3.getX();
		int y3 = t3.getY();
		
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

		double pab = t1.distance(t2);
		double pbc = t2.distance(t3);
		double pac = t1.distance(t3);
		
		Vector2D vab = new Vector2D(t1, t2);
		Vector2D vac = new Vector2D(t1, t3);
		
		double r = (pab * pbc * pac) / (2 * vab.determinant(vac));
		
		if(r < 0)
			r *= -1;

		return r;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.CYAN);
		g2d.drawLine(t1.getX(), t1.getY(), t2.getX(), t2.getY());
		g2d.drawLine(t2.getX(), t2.getY(), t3.getX(), t3.getY());
		g2d.drawLine(t1.getX(), t1.getY(), t3.getX(), t3.getY());
		
		//Shape theCircle = new Ellipse2D.Double(getCenter().getX() - getRayon(), getCenter().getY() - getRayon(), 2.0 * getRayon(), 2.0 * getRayon());
		//g2d.setColor(Color.BLUE);
		//g2d.draw(theCircle);
	}

	public double getRayon() {
		return rayon;
	}
	
	
	public List<Point2D> getSommets()  {
		
		List<Point2D> points = new ArrayList<Point2D>();
		
		points.add(t1);
		points.add(t2);
		points.add(t3);
		
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
		sommets.removeAll(t2.getSommets());
		if(sommets.size() == 1)
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
			
			this.setT1(tempT1.getT1());
			this.setT2(tempT1.getT2());
			this.setT3(tempT1.getT3());
			
			t2.setT1(tempT2.getT1());
			t2.setT2(tempT2.getT2());
			t2.setT3(tempT2.getT3());
		}
	}
	
	public List<Triangle2D> getVoisin() {
		return voisin;
	}
	
	public boolean isFlippable(Triangle2D t2) {
		
		List<Double> anglesT1 = this.getAngles();
		List<Double> anglesT2 = t2.getAngles();
		
		List<Double> A1 = new ArrayList<Double>();
		A1.addAll(anglesT1);
		A1.addAll(anglesT2);
		
		Collections.sort(A1);
		
		System.out.println("A1");
		for(Double d: A1) 
			System.out.println(d);
		
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
		
		
		return false;
	}
}
