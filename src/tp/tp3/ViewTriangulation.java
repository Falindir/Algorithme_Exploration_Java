package tp.tp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import tp.tools.Algorithm;
import tp.tools.Point2D;
import tp.tools.Segment2D;
import tp.tools.Triangle2D;
import tp.tools.View;

public class ViewTriangulation extends View {
	
	private int width; 
	private int height;

	private List<Point2D> points;
	private List<Triangle2D> triangles;
	
	
	public ViewTriangulation(int width, int height) {
		super(width, height);
		
		
		this.width = width;
		this.height = height;
		
		this.points = new ArrayList<Point2D>();
		this.triangles = new ArrayList<Triangle2D>();
		//this.segmentEnvConvexe = new ArrayList<Segment2D>();
		
		
		setFocusable(true);
		requestFocus();
		
		
	}
	
	public void drawListRandomPoint (int numberPoints) {
		points = Algorithm.generateRandomPoint(numberPoints, width, height);
	}
	
	public void drawSameRandomPoint(List<Point2D> points) {
		this.points = points;
	}
	
	public void drawTriangulationIncrementale () {				
		triangles = Algorithm.triangulationIncrementale(points);
	}
	
	public void drawTriangulationDelaunay (List<Triangle2D> triangl) {
		triangles = Algorithm.triangulationIncrementale(points);
		triangles.get(0).flip(triangles.get(1));
		
		System.out.println(triangles.get(0).isFlippable(triangles.get(1)));
		

		
		/*System.out.println("\nT11");
		for(Point2D p : triangles.get(0).getSommets())
			System.out.println(p.getName());
		
		System.out.println("\nT22");
		for(Point2D p : triangles.get(1).getSommets())
			System.out.println(p.getName());*/
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
						
		double radius = 300;
		double centerX = width / 2;
		double centerY = height / 2;
		Shape theCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
		g2d.setColor(Color.RED);
		g2d.draw(theCircle);
		
		for (Point2D p : points) {
			g2d.setColor(p.getColor());
			p.draw(g2d);
			p.drawName(g2d);
		}

		int i = 1;

		for(Triangle2D triangle : triangles) {
			triangle.draw(g2d);
			//triangle.getCenter().setName("O" + i);
			//triangle.getCenter().draw(g2d);
			i++;
		}

			
	}

	public List<Point2D> getPoints() {
		return points;
	}

	public List<Triangle2D> getTriangles() {
		return triangles;
	}
	
	
	
	
	
	
}
