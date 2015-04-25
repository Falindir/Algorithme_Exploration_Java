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
import tp.tools.Form2D.Triangle2D;
import tp.tools.algorithm.Delaunay;
import tp.tools.algorithm.IncrementalTriangulation;
import tp.tools.algorithm.RandomPoint2D;
import tp.tools.algorithm.Voronoi;
import tp.tools.others.RolePoint;
import tp.tools.visualisation.View;

public class ViewTriangulation extends View implements MouseWheelListener, MouseListener {

	private static List<Point2D> _points = new ArrayList<Point2D>();

	private static List<Triangle2D> _trianglesIncrementale = new ArrayList<Triangle2D>();
	private static List<Triangle2D> _trianglesDelaunay = new ArrayList<Triangle2D>();

	private static List<Point2D> _voronois = new ArrayList<Point2D>();

	private RandomPoint2D _randomPoint;
	private IncrementalTriangulation _incTriangul = new IncrementalTriangulation();
	private Delaunay _delaunay = new Delaunay();
	private Voronoi _voronoi = new Voronoi();

	private boolean _isIncremental = false;
	private boolean _isDelaunay = false;
	private boolean _isVoronay = false;

	public ViewTriangulation(int width, int height) {
		super(width, height);

		_randomPoint = new RandomPoint2D(width, height);

		addMouseListener(this);
		addMouseWheelListener(this);

		setFocusable(true);
		requestFocus();
	}
	
	public void drawListRandomPoint (int numberPoints) {
		_points.clear();
		_points.addAll(_randomPoint.run(numberPoints));
	}
	
	public void drawSameRandomPoint(List<Point2D> points) {
		_points.clear();
		_points.addAll(points);
	}
	
	public void drawTriangulationIncrementale () {
		_isIncremental = true;
		_trianglesIncrementale.addAll(_incTriangul.run(_points));
		System.out.println("Incrementale triangle : " + _trianglesIncrementale.size());
	}
	
	public void drawTriangulationDelaunay () {
		_isDelaunay = true;
		_trianglesDelaunay.clear();
		_trianglesDelaunay.addAll(_delaunay.run(_trianglesIncrementale));
		System.out.println("Delaunay triangle : " + _trianglesDelaunay.size());
	}

	public void drawTriangulationVoronoi () {
		_isVoronay = true;
		_voronois.addAll(_voronoi.run(_trianglesDelaunay));
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

		if(_isIncremental) {
			for(Triangle2D triangle : _trianglesIncrementale) {
				triangle.draw(g2d);
			}
			repaint();
		}

		if(_isDelaunay) {
			for(Triangle2D triangle : _trianglesDelaunay) {
				triangle.draw(g2d);
			}
			repaint();
		}
/*
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
			*/
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);
		p.setName("" + _points.size());
		_points.add(p);
		Collections.sort(_points);

		_trianglesIncrementale.clear();
		_trianglesIncrementale.addAll(_incTriangul.run(_points));

		if(_isDelaunay) {
			_trianglesDelaunay.clear();
			_trianglesDelaunay.addAll(_delaunay.run(_trianglesIncrementale));
		}

		repaint();
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
