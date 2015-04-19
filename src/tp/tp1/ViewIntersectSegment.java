package tp.tp1;

import tp.tools.*;
import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ViewIntersectSegment extends View implements MouseWheelListener, MouseListener, KeyListener {

	private int width;
	private int height;

	private List<Point2D> _points;
	private List<Point2D> _pointsIntersect;
	private List<Segment2D> _segments;
	private List<Segment2D> _segmentsIntersect;

	private SweepLine _sweepLine;

	// Pour placer les point à la main
	private Point2D p1;
	private Point2D p2;
	private boolean firstPoint;
	private boolean secondPoint;
	private Segment2D s;

	public ViewIntersectSegment(int width, int height) {
		super(width, height);

		this._points = new ArrayList<Point2D>();
		this._pointsIntersect = new ArrayList<Point2D>();
		this._segments = new ArrayList<Segment2D>();
		this._sweepLine = new SweepLine(this);
		this._segmentsIntersect = new ArrayList<Segment2D>();

		this.p1 = new Point2D(0, 0);
		this.p2 = new Point2D(0, 0);
		this.firstPoint = true;
		this.secondPoint = false;
		this.s = new Segment2D(p1, p2);

		addMouseListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}

	public void drawListRandomPoint (int numberPoints) {
		_points = Algorithm.generateRandomPointForSegment(numberPoints, width, height, RolePoint.BEGIN);
	}

	public void drawListRandomSegment () {
		_segments = Segment2D.constructSegment(_points);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);

		drawListIntersectRandomSegment();

		for (Point2D p : _points) {
			g2d.setColor(p.getColor());
			p.draw2(g2d);
		}

		for (Segment2D s: _segments) {
			g2d.setColor(Color.black);
			s.draw(g2d);
		}

		for (Point2D p : _pointsIntersect) {
			g2d.setColor(Color.RED);
			p.draw(g2d);
		}


	}

	public void drawListIntersectRandomSegment() {
		List<Segment2D> segmentsIntersect = new ArrayList<Segment2D>();

		for(Segment2D ss1: _segments) {

			for(Segment2D ss2: _segments) {

				if(!ss1.equals(ss2)) {

					if(ss1.intersectent(ss2)) {

						if(!segmentsIntersect.contains(ss1))
							segmentsIntersect.add(ss1);

						if(!segmentsIntersect.contains(ss2))
							segmentsIntersect.add(ss2);

						Point2D intersect = ss1.getIntersectedPoint(ss2);
						_pointsIntersect.add(intersect);
						intersect.setColor(ColorTools.POINT_BEGIN);
						intersect.setRole(RolePoint.INTERSECT);
					}
				}
			}
		}

		_segmentsIntersect.clear();
		_segmentsIntersect.addAll(segmentsIntersect);
		System.out.println("Numbre intersect : " + _segmentsIntersect.size());
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 1){

			if(firstPoint) {
				p1 = new Point2D ((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.BEGIN);
				firstPoint = false;
				secondPoint = true;
			}
			else {
				if(secondPoint && !firstPoint) {
					p2 = new Point2D ((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.END);
					secondPoint = false;
					firstPoint = true;

					Segment2D s = new Segment2D(p1, p2);
					s.setName("S" + (_segments.size() - 1));
					_segments.add(s);
					_points.add(p1);
					_points.add(p2);
					repaint();
				}
			}
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

	@Override
	public void keyTyped(KeyEvent keyEvent) {

	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {

	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

	public void setIntersectedPoint() {
		_sweepLine.addListPointSegment(_segments);
	}

	public void drawIntersectedPoint(Graphics2D g2d) {
		_sweepLine.drawListPointSegment(g2d);
	}

}
