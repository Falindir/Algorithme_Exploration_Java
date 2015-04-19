package tp.tools;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

public class SweepLine {	
	private int width;
	private int height;
	private int position;
	private int stepWidth;
	private List<Point2D> intersectedPointSegment;
	private View view; 
	private Graphics2D g2d;
	
	public SweepLine(View v){
		this.intersectedPointSegment = new ArrayList<Point2D>();
		this.position = 0;
		this.view = v;
		this.width = view.getWidth();
		this.height = view.getHeight();
		this.stepWidth = width/100;
	}
	
	public void dessine(Graphics2D g) {
		g.setColor(Color.RED.darker());
		Stroke s = g.getStroke();
		g.setStroke(new BasicStroke(2));
		g.drawLine(position,0,position,height);
		g.setStroke(s);
	}

	public void next() {
		int temp = position++;

		if(temp <= (width - stepWidth) + 5) {
			position++;
		}
		else {
			position = (width - stepWidth) + 5;
		}
	}
	
	public void next(int speed) {
		int temp = position + speed;

		if(temp <= (width - stepWidth) + 5) {
			position += speed;
		}
		else {
			position = (width - stepWidth) + 5;
		}
		
		for(Point2D point : intersectedPointSegment) {
			
			if(position > point.getX()) {
				
				point.setColor(Color.RED);
			}
		}
		
	}
	
	public void removeListPoint() {
		intersectedPointSegment.clear();
	}

	public void removeListPoint(Point2D point) {
		intersectedPointSegment.remove(point);
	}
	
	public void addPoingSegment(Point2D point) {
		intersectedPointSegment.add(point);
	}
	
	public void drawListPointSegment(Graphics2D g2d) {
		for(Point2D point : intersectedPointSegment) {
			point.draw(g2d);
		}
	}
	
	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public List<Point2D> getIntersectedPointSegment() {
		return intersectedPointSegment;
	}
	
	public void addListPointSegment(List<Segment2D> segments) {
        for(Segment2D ss1: segments) {
        	
        	for(Segment2D ss2: segments) {
        		
        		if(!ss1.equals(ss2)) {
        			
        			if(ss1.intersectent(ss2)) {

						Point2D point = ss1.getIntersectedPoint(ss2);
        				
        				if(!getIntersectedPointSegment().contains(point))
        					addPoingSegment(point);
        			}
        		}
        	}
        }
	}
	
}
