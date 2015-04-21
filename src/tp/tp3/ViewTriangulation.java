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

	private List<Point2D> _points = new ArrayList<Point2D>();

	private List<Triangle2D> _trianglesIncrementale = new ArrayList<Triangle2D>();
	private List<Triangle2D> _trianglesDelaunay = new ArrayList<Triangle2D>();

	private List<Point2D> _voronoi = new ArrayList<Point2D>();

	private boolean _incremental = false;
	private boolean _delaunay = false;
	private boolean _voronay = false;

	public ViewTriangulation(int width, int height) {
		super(width, height);

		addMouseListener(this);
		addMouseWheelListener(this);

		setFocusable(true);
		requestFocus();
	}
	
	public void drawListRandomPoint (int numberPoints) {
		_points.clear();
		_points.addAll(Algorithm.generateRandomPoint(numberPoints, getWidth(), getHeight(), RolePoint.NONE));
	}
	
	public void drawSameRandomPoint(List<Point2D> points) {
		_points.clear();
		_points.addAll(points);
	}
	
	public void drawTriangulationIncrementale () {
		_incremental = true;
		_trianglesIncrementale.addAll(Algorithm.triangulationIncrementale(_points));
		System.out.println("Incrementale triangle : " + _trianglesIncrementale.size());
	}
	
	public void drawTriangulationDelaunay () {
		_delaunay = true;
		_trianglesDelaunay.clear();
		_trianglesDelaunay.addAll(Algorithm.Delaunay(_trianglesIncrementale));
		System.out.println("Delaunay triangle" + _trianglesDelaunay.size());
	}

	public void drawTriangulationVoronoi () {
		_voronay = true;
		_voronoi.addAll(Algorithm.Voronoi(_trianglesDelaunay));
		System.out.println("Voronoi triangle" + _trianglesDelaunay.size());
	}

	public Shape getCenterCircle(double radius) {
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		Shape theCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
		return theCircle;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);
						
		Shape theCircle = getCenterCircle(300);
		g2d.setColor(Color.RED);
		g2d.draw(theCircle);
		
		for (Point2D p : _points) {
			g2d.setColor(p.getColor());
			p.draw(g2d);
			p.drawName(g2d);
		}
/*
		for(Triangle2D triangle : triangles) {
			triangle.draw(g2d);
		}
*/


		for (Point2D p : _voronoi) {
			p.setColor(ColorTools.POINT_INTERSECT);
			g2d.setColor(p.getColor());
			p.draw(g2d);
		}

		if(_voronoi.size() > 0) {
			for (Triangle2D triangle : _trianglesDelaunay) {
				for (Triangle2D triangle2 : _trianglesDelaunay) {
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


	@Override
	public void mouseClicked(MouseEvent e) {
		Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);
		p.setName("" + _points.size());
		_points.add(p);
		Collections.sort(_points);
/*
		if(_incremental) {
			triangles = Algorithm.triangulationIncrementale(_points);
			repaint();
		}
		if(_delaunay) {
			triangles = Algorithm.triangulationIncrementale(_points);
			List<Triangle2D> temp = new ArrayList<Triangle2D>();
			temp.addAll(Algorithm.Delaunay(triangles));
			triangles.clear();
			triangles.addAll(temp);
			repaint();
		}
		if(_voronay) {
			triangles = Algorithm.triangulationIncrementale(_points);
			List<Triangle2D> temp = new ArrayList<Triangle2D>();
			temp.addAll(Algorithm.Delaunay(triangles));
			triangles.clear();
			triangles.addAll(temp);
			_voronoi.addAll(Algorithm.Voronoi(temp));
			repaint();
		}
*/

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
