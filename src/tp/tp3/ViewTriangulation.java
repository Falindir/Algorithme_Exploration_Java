package tp.tp3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp.tools.*;
import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;
import tp.tools.Form2D.Triangle2D;

public class ViewTriangulation extends View implements MouseWheelListener, MouseListener {
	
	private int width; 
	private int height;

	private List<Point2D> points;
	private List<Triangle2D> triangles;

	private List<Point2D> voronoi;

	private boolean inc = false;
	private boolean delaunay = false;
	private boolean voronay = false;

	public ViewTriangulation(int width, int height) {
		super(width, height);
		
		
		this.width = width;
		this.height = height;
		
		this.points = new ArrayList<Point2D>();
		this.triangles = new ArrayList<Triangle2D>();
		this.voronoi = new ArrayList<Point2D>();

		addMouseListener(this);
		addMouseWheelListener(this);

		setFocusable(true);
		requestFocus();
	}
	
	public void drawListRandomPoint (int numberPoints) {
		points = Algorithm.generateRandomPoint(numberPoints, width, height, RolePoint.NONE);
	}
	
	public void drawSameRandomPoint(List<Point2D> points) {
		this.points = points;
	}
	
	public void drawTriangulationIncrementale () {

		inc = true;

		triangles = Algorithm.triangulationIncrementale(points);

		System.out.println(triangles.size());
	}
	
	public void drawTriangulationDelaunay (List<Triangle2D> triangl) {
		delaunay = true;
		triangles = Algorithm.triangulationIncrementale(points);
		List<Triangle2D> temp = new ArrayList<Triangle2D>();
		temp.addAll(Algorithm.Delaunay(triangles));
		triangles.clear();
		triangles.addAll(temp);
		System.out.println("AftherDelaunay" + triangles.size());
	}

	public void drawTriangulationVoronoi () {
		voronay = true;
		triangles = Algorithm.triangulationIncrementale(points);
		List<Triangle2D> temp = new ArrayList<Triangle2D>();
		temp.addAll(Algorithm.Delaunay(triangles));
		triangles.clear();
		triangles.addAll(temp);

		voronoi.addAll(Algorithm.Voronoi(temp));
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

		System.out.println("Paint " + triangles.size());

		for(Triangle2D triangle : triangles) {
			triangle.draw(g2d);
			//triangle.getCenter().setName("O" + i);
			//triangle.getCenter().draw(g2d);
			//Shape cc = new Ellipse2D.Double(triangle.getCenter().getX() - triangle.getRayon(),
				//	triangle.getCenter().getY() - triangle.getRayon(),
				//	2.0 * triangle.getRayon(),
				//	2.0 * triangle.getRayon());
			//g2d.setColor(Color.RED);
			//g2d.draw(cc);
			i++;
		}

		for (Point2D p : voronoi) {
			p.setColor(ColorTools.POINT_INTERSECT);
			g2d.setColor(p.getColor());
			p.draw(g2d);
		}

		if(voronoi.size() > 0) {
			for (Triangle2D triangle : triangles) {
				for (Triangle2D triangle2 : triangles) {
					if(triangle != triangle2) {
						if(triangle.isVoisin(triangle2)) {
							Segment2D segment = new Segment2D(triangle.getCenter(), triangle2.getCenter());
							g2d.setColor(ColorTools.POINT_ZONE);
							segment.draw(g2d);
						}
					}
				}
			}
		}
			
	}

	public List<Point2D> getPoints() {
		return points;
	}

	public List<Triangle2D> getTriangles() {
		return triangles;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);
		p.setName("" + points.size());
		points.add(p);
		Collections.sort(points);

		if(inc) {
			triangles = Algorithm.triangulationIncrementale(points);
			repaint();
		}
		if(delaunay) {
			triangles = Algorithm.triangulationIncrementale(points);
			List<Triangle2D> temp = new ArrayList<Triangle2D>();
			temp.addAll(Algorithm.Delaunay(triangles));
			triangles.clear();
			triangles.addAll(temp);
			repaint();
		}
		if(voronay) {
			triangles = Algorithm.triangulationIncrementale(points);
			List<Triangle2D> temp = new ArrayList<Triangle2D>();
			temp.addAll(Algorithm.Delaunay(triangles));
			triangles.clear();
			triangles.addAll(temp);
			voronoi.addAll(Algorithm.Voronoi(temp));
			repaint();
		}


	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent mouseEvent) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

	}
}
