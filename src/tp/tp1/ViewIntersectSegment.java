package tp.tp1;

import tp.tools.ColorTools;
import tp.tools.SweepLine;
import tp.tools.RolePoint;
import tp.tools.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class ViewIntersectSegment extends View implements MouseWheelListener, MouseListener, KeyListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	
	private Color bgColor = ColorTools.BACKGROUND;
	private Color fgColor = ColorTools.POINT_ZONE;
	
	private int width; 
	private int height;
	private List<Segment> segments;
	private SweepLine sweepLine;
	
	// Pour placer les point à la main
	private PointSegment p1;
	private PointSegment p2;

	private boolean startDrawSegment = false;

	private Segment segmentCourant;

	public ViewIntersectSegment(int width, int height) {
		super(width, height);
		this.segments = new ArrayList<Segment>();
		this.width = width;
		this.height = height;
		this.sweepLine = new SweepLine(this);
		
		this.p1 = new PointSegment();
		this.p2 = new PointSegment();

		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor);
		addMouseListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		requestFocus();
	}
	
	public void drawListRandomSegment (int numberSegments) {
		segments = Segment.randomSegment(numberSegments);
	}

	public void drawListIntersectRandomSegment() {
		List<Segment> segmentsIntersect = new ArrayList<Segment>(); 
			
        for(Segment ss1: segments) {
        	
        	for(Segment ss2: segments) {
        		
        		if(!ss1.equals(ss2)) {
        			
        			if(ss1.intersectent(ss2)) {
        				
        				if(!segmentsIntersect.contains(ss1))
        					segmentsIntersect.add(ss1);
        				
        				if(!segmentsIntersect.contains(ss2))
        					segmentsIntersect.add(ss2);
        			}
        		}
        	}
        }
        
		segments = segmentsIntersect;
		System.out.println("Numbre intersect : " + segments.size());
	}
	
	public void drawListIntersectRandomSegmentBentleyOttman() {
		
		List<Segment> segmentsIntersect = new ArrayList<Segment>(); 
		
		List<PointSegment> ech = new ArrayList<PointSegment>();
		
		Stack<PointSegment> ech2 = new Stack<PointSegment>();
		
		List<PointSegment> leftPointSegment = new ArrayList<PointSegment>();
		List<PointSegment> rightPointSegment = new ArrayList<PointSegment>();
		
		for(Segment s : segments) {
			ech.add(s.getLeftPoint());
			ech.add(s.getRightPoint());
			leftPointSegment.add(s.getLeftPoint());
			rightPointSegment.add(s.getRightPoint());
		}
		
		Collections.sort(ech, new Comparator<PointSegment>() {
		    @Override
		    public int compare(PointSegment o1, PointSegment o2) {
			if (o1.getX() < o2.getX())
			    return -1;
			else if (o1.getX() == o2.getX())
			    return 0;
			else
			    return 1;
		    }
		});
		
		Collections.reverse(ech); 
		
		ech2.addAll(ech);
					
		TreeSet<Segment> verticalOrder = new TreeSet<Segment>(
				new Comparator<Segment>() {
				    @Override
				    public int compare(Segment o1, Segment o2) {
					if (o1.getLeftPoint().getY() < o2.getRightPoint().getY())
					    return -1;
					else if (o1 == o2)
					    return 0;
					else if (o1.getLeftPoint().getY() == o2.getLeftPoint().getY()
						&& o1.getRightPoint().getY() < o2.getRightPoint().getY())
					    return -1;
					else if (o1.getLeftPoint().getY() == o2.getLeftPoint().getY()
						&& o1.getRightPoint().getY() > o2.getRightPoint().getY())
					    return 1;
					else
					    return 1;
				    }
		});
		
		int position = 0;
		//for(PointSegment p : ech){
			while(!ech2.isEmpty()) {
				PointSegment p = ech2.pop();
				
				if(leftPointSegment.contains(p)) {
					position = leftPointSegment.indexOf(p);
					Segment s1 = leftPointSegment.get(position).getSegmentPoint();
					verticalOrder.add(s1);
					Segment s2 = verticalOrder.higher(s1);
					
					if (s2 != null) {
					    if (s2.intersectent(s1)) {
					    	
					    	if(!segmentsIntersect.contains(s1))
	        					segmentsIntersect.add(s1);
	        				
	        				if(!segmentsIntersect.contains(s2))
	        					segmentsIntersect.add(s2);

	        				PointSegment intersect = s2.getIntersectedPoint(s1);
	        				ech2.add(intersect);				
					    }
				}
				
				s2 = verticalOrder.lower(s1);
				if (s2 != null) {
				    if (s2.intersectent(s1)) {
				    	if(!segmentsIntersect.contains(s1))
        					segmentsIntersect.add(s1);
        				
        				if(!segmentsIntersect.contains(s2))
        					segmentsIntersect.add(s2);
        				
        				PointSegment intersect = s2.getIntersectedPoint(s1);
        				ech2.add(intersect);
				    }
				}
			}
			
			if(rightPointSegment.contains(p)) {
				position = rightPointSegment.indexOf(p);
				Segment s1 = rightPointSegment.get(position).getSegmentPoint();
				Segment upper = verticalOrder.higher(s1);
				Segment downer = verticalOrder.lower(s1);
				
				if (upper != null && downer != null) {
					if (upper.intersectent(downer)) {
						if(!segmentsIntersect.contains(upper))
        					segmentsIntersect.add(upper);
        				
        				if(!segmentsIntersect.contains(downer))
        					segmentsIntersect.add(downer);
        				
        				PointSegment intersect = upper.getIntersectedPoint(downer);
        				ech2.add(intersect);
					}	
				}
				verticalOrder.remove(s1);
			}
		}
		
		segments = segmentsIntersect;
		System.out.println("Numbre intersect : " + segments.size());
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaintMode(); 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);	

		g2d.setColor(fgColor);

		if(segmentCourant != null && !segments.contains(segmentCourant))
			segmentCourant.drawSegment(g2d);


		for (Segment lg: segments) {
			lg.drawSegment(g2d);
			lg.getRightPoint().drawPointSegment(g2d);
			lg.getLeftPoint().drawPointSegment(g2d);
			//g2d.drawString("Hello world", 150, 150);
			g2d.setColor(ColorTools.POINT_NONE);
			g2d.drawString(lg.getNameSegment(), (int) lg.getLeftPoint().getX()-5, (int) lg.getLeftPoint().getY()-5);
		}

		sweepLine.setG2d(g2d);
		setIntersectedPoint();
		drawIntersectedPoint(g2d);
		sweepLine.dessine(g2d);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			if(!startDrawSegment) {
				p1 = new PointSegment ((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.BEGIN);
				p2 = new PointSegment ((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.END);
				startDrawSegment = true;
				segmentCourant = new Segment(p1, p2);
				repaint();
			}
			else {
				startDrawSegment = false;
				segments.add(segmentCourant);
				segmentCourant.setNameSegment("S" + (segments.size() - 1));
				repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int scrollUnit = e.getWheelRotation();
		if (scrollUnit > 0)
			sweepLine.next(2);
		repaint();
	}

	public List<Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}

	@Override
	public void keyPressed(KeyEvent key) {		
		if(key.getKeyCode() == 107)  // key : +
			sweepLine.next(2);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent key) {
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setIntersectedPoint() {
		sweepLine.addListPointSegment(segments);
	}
	
	public void drawIntersectedPoint(Graphics2D g2d) {
		sweepLine.drawListPointSegment(g2d);
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(startDrawSegment) {
			p2 = new PointSegment((int) e.getPoint().getX(), (int) e.getPoint().getY(), RolePoint.END);
			segmentCourant = new Segment(p1, p2);
			repaint();
		}
	}
}
	

