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

import tp.tools.algorithm.ListPoint2DToSegment2D;
import tp.tools.algorithm.RandomPoint2D;
import tp.tools.others.ColorTools;
import tp.tools.Form2D.Point2D;
import tp.tools.others.RolePoint;
import tp.tools.Form2D.Segment2D;
import tp.tools.visualisation.View;
import tp.tools.algorithm.Graham;
import tp.tools.algorithm.Jarvis;

public class ViewEnveloppeConvexe extends View implements MouseWheelListener, MouseListener{

	private int width; 
	private int height;
	
	private List<Point2D> points;
	private List<Point2D> pointsEnvConvInit;
	private List<Segment2D> segmentEnvConvexe;

	private List<List<Point2D>> onionSkin = new ArrayList<List<Point2D>>();
	private List<List<Segment2D>> onionSkinConvexe = new ArrayList<List<Segment2D>>();

	private RandomPoint2D _randomPoint;
	private Jarvis _jarvis = new Jarvis();
	private Graham _graham = new Graham();

	private boolean _Jarvis = false;
	private boolean _Graham = false;

	private boolean onion = false;

	public ViewEnveloppeConvexe(int width, int height) {
		super(width, height);
		
		this.points = new ArrayList<Point2D>();
		this.segmentEnvConvexe = new ArrayList<Segment2D>();
		_randomPoint = new RandomPoint2D(width, height);
		
		this.width = width;
		this.height = height;
		
		addMouseListener(this);
		addMouseWheelListener(this);

		setFocusable(true);
		requestFocus();
	}

	public void drawListRandomPoint (int numberPoints) {
		points = _randomPoint.run(numberPoints);
	}

	public void drawEnvConvJarvis() {

		_Jarvis = true;

		pointsEnvConvInit = _jarvis.run(points);

		segmentEnvConvexe = ListPoint2DToSegment2D.transform(pointsEnvConvInit);
	}
	public void drawEnvConvGraham() {

		_Graham = true;

		pointsEnvConvInit = _graham.run(points);

		segmentEnvConvexe = ListPoint2DToSegment2D.transform(pointsEnvConvInit);
	}

	public void drawEnvConvOnionSkin() {
		onion = true;

		List<Point2D> pointsInitOnion = new ArrayList<Point2D>();
		pointsInitOnion.addAll(points);

		while(pointsInitOnion.size() >= 3) {
			Collections.sort(pointsInitOnion);

			List<Point2D> points = _jarvis.run(pointsInitOnion);

			onionSkin.add(points);

			onionSkinConvexe.add(ListPoint2DToSegment2D.transform(points));

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
			if(_Jarvis) {
				s.setLineColor(Color.MAGENTA);
				s.draw(g2d);
			}
			if(_Graham) {
				s.setLineColor(Color.GREEN);
				s.draw(g2d);
			}
		}

		for(int i = 0; i < onionSkinConvexe.size(); i++) {

			Color c = ColorTools.getRandomColor();

			for(Segment2D s : onionSkinConvexe.get(i)) {
				s.setLineColor(c);
				s.draw(g2d);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(!onion) {
			double centerX = width / 2;
			double centerY = height / 2;

			Point2D center = new Point2D((int) centerX, (int) centerY);

			Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);

			if (center.distance(p) < 300) {
				p.setName("" + points.size());

				points.add(p);
				Collections.sort(points);

				pointsEnvConvInit.add(p);
				Collections.sort(pointsEnvConvInit);

				List<Point2D> pointsEnvConv = _graham.run(pointsEnvConvInit);

				segmentEnvConvexe = ListPoint2DToSegment2D.transform(pointsEnvConv);

				repaint();
			}
		}
		else {
			Point2D p = new Point2D((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.NONE);
			p.setName(""+points.size());
			points.add(p);
			List<Point2D> pointsInitOnion = new ArrayList<Point2D>();
			pointsInitOnion.addAll(points);

			onionSkin.clear();
			onionSkinConvexe.clear();

			while(pointsInitOnion.size() >= 3) {
				Collections.sort(pointsInitOnion);

				List<Point2D> points = _jarvis.run(pointsInitOnion);

				onionSkin.add(points);

				onionSkinConvexe.add(ListPoint2DToSegment2D.transform(points));

				pointsInitOnion.removeAll(points);
			}

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
