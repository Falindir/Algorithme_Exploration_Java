package tp.tp2;

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

import tp.tools.Algorithm;
import tp.tools.Point2D;
import tp.tools.RolePoint;
import tp.tools.Segment2D;
import tp.tools.View;

public class ViewEnveloppeConvexe extends View implements MouseWheelListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	private int width; 
	private int height;
	
	private List<Point2D> points;
	private List<Point2D> pointsEnvConvInit;
	private List<Segment2D> segmentEnvConvexe;

	private List<List<Point2D>> onionSkin = new ArrayList<List<Point2D>>();
	private List<List<Segment2D>> onionSkinConvexe = new ArrayList<List<Segment2D>>();

	public ViewEnveloppeConvexe(int width, int height) {
		super(width, height);
		
		this.points = new ArrayList<Point2D>();
		this.segmentEnvConvexe = new ArrayList<Segment2D>();
	
		
		this.width = width;
		this.height = height;
		
		addMouseListener(this);
		addMouseWheelListener(this);

		setFocusable(true);
		requestFocus();
	}

	public void drawListRandomPoint (int numberPoints) {
		points = Algorithm.generateRandomPoint(numberPoints, width, height, RolePoint.BEGIN);
	}
	
	public void drawListFormPoint () {
		points = Algorithm.generateFormePoint(width, height);
	}

	public void drawEnvConvJarvis() {

		pointsEnvConvInit = Algorithm.Jarvis(points);

		segmentEnvConvexe = Algorithm.generateListSegmentWithPoint(pointsEnvConvInit);
	}
	public void drawEnvConvGraham() {

		pointsEnvConvInit = Algorithm.Graham(points);
		
		//for(Point2D point : pointsEnvConv)
			//System.out.println("Points : " + point.getName());
		
		segmentEnvConvexe = Algorithm.generateListSegmentWithPoint(pointsEnvConvInit);
	}

	public void drawEnvConvOnionSkin() {
		List<Point2D> pointsInitOnion = new ArrayList<Point2D>();
		pointsInitOnion.addAll(points);

		while(pointsInitOnion.size() >= 3) {
			Collections.sort(pointsInitOnion);

			List<Point2D> points = Algorithm.Jarvis(pointsInitOnion);

			onionSkin.add(points);

			onionSkinConvexe.add(Algorithm.generateListSegmentWithPoint(points));

			pointsInitOnion.removeAll(points);
		}

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
		}
	
		for (Segment2D s: segmentEnvConvexe) {
			g2d.setColor(Color.black);
			s.draw(g2d);
		}

		for(int i = 0; i < onionSkinConvexe.size(); i++) {
			for(Segment2D s : onionSkinConvexe.get(i)) {
				g2d.setColor(Color.black);
				s.draw(g2d);
			}
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		double centerX = width / 2;
		double centerY = height / 2;

		Point2D center = new Point2D((int)centerX, (int)centerY);

		Point2D p = new Point2D ((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);

		if(center.distance(p) < 300) {
			p.setName("" + points.size());

			points.add(p);
			Collections.sort(points);

			pointsEnvConvInit.add(p);
			Collections.sort(pointsEnvConvInit);

			List<Point2D> pointsEnvConv = Algorithm.Graham(pointsEnvConvInit);

			segmentEnvConvexe = Algorithm.generateListSegmentWithPoint(pointsEnvConv);

			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
