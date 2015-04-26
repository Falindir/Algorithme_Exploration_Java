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

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;
import tp.tools.Form2D.Triangle2D;
import tp.tools.algorithm.Delaunay;
import tp.tools.algorithm.IncrementalTriangulation;
import tp.tools.algorithm.RandomPoint2D;
import tp.tools.algorithm.Voronoi;
import tp.tools.others.ColorTools;
import tp.tools.others.RolePoint;
import tp.tools.visualisation.View;

public class ViewTriangulation extends View implements MouseWheelListener, MouseListener {

	private boolean _isIncremental = false;
	private boolean _isDelaunay = false;
	private boolean _isVoronay = false;

	public ControllerTP3 _controllerTP3;

	public ViewTriangulation(int width, int height, ControllerTP3 controller) {
		super(width, height, controller);
		_controllerTP3 = controller;
		_controllerTP3.setRandomPoint(new RandomPoint2D(width, height));
		addMouseListener(this);
		addMouseWheelListener(this);
		setFocusable(true);
		requestFocus();
	}
	
	public void drawListRandomPoint (int numberPoints) {
		_controllerTP3.getPoints().clear();
		_controllerTP3.getPoints().addAll(_controllerTP3.getRandomPoint().run(numberPoints));
	}

	public void drawTriangulationIncrementale () {
		_isIncremental = true;
		_controllerTP3.getIncrementalTriangulation().clear();
		_controllerTP3.getIncrementalTriangulation().addAll(_controllerTP3.getIncTriangul().run(_controllerTP3.getPoints()));
	}
	
	public void drawTriangulationDelaunay () {
		_isDelaunay = true;
		_controllerTP3.getDelaunayTriangulation().clear();
		List<Triangle2D> triangulation = new ArrayList<Triangle2D>();
		triangulation.addAll(_controllerTP3.getIncTriangul().run(_controllerTP3.getPoints()));
		_controllerTP3.getDelaunayTriangulation().addAll(_controllerTP3.getDelaunay().run(triangulation));
	}

	public void drawTriangulationVoronoi () {
		_isVoronay = true;
		_controllerTP3.getCenterVoronoi().addAll(_controllerTP3.getVoronoi().run(_controllerTP3.getDelaunayTriangulation()));
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
		
		for (Point2D p : _controllerTP3.getPoints()) {
			g2d.setColor(p.getColor());
			p.draw(g2d);
			p.drawName(g2d);
		}

		if(_isIncremental) {
			for(Triangle2D triangle : _controllerTP3.getIncrementalTriangulation()) {
				triangle.draw(g2d);
			}
		}

		if(_isDelaunay || _isVoronay) {
			for(Triangle2D triangle : _controllerTP3.getDelaunayTriangulation()) {
				triangle.draw(g2d);
			}
		}

		if(_isVoronay) {
			for (Point2D p : _controllerTP3.getCenterVoronoi()) {
				p.setColor(ColorTools.POINT_INTERSECT);
				g2d.setColor(p.getColor());
				p.draw(g2d);
			}

			if(_controllerTP3.getCenterVoronoi().size() > 0) {
				for (Triangle2D triangle : _controllerTP3.getDelaunayTriangulation()) {
					for (Triangle2D triangle2 : _controllerTP3.getDelaunayTriangulation()) {
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);
		p.setName("" + _controllerTP3.getPoints().size());
		_controllerTP3.addPoints(p);

		getController().repaintView();
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
