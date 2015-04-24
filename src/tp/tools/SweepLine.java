package tp.tools;

import tp.tools.View;
import tp.tp1.PointSegment;
import tp.tp1.Segment;

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
	private List<PointSegment> intersectedPointSegment;
	private View view;
	private Graphics2D g2d;
	
	public SweepLine(View v){
		this.intersectedPointSegment = new ArrayList<PointSegment>();
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
		
		for(PointSegment point : intersectedPointSegment) {
			
			if(position > point.getCenterX()) {
				
				point.setColorPoint(Color.RED);
			}
		}
		
	}
	
	public void removeListPoint() {
		intersectedPointSegment.clear();
	}

	public void removeListPoint(PointSegment point) {
		intersectedPointSegment.remove(point);
	}
	
	public void addPoingSegment(PointSegment point) {
		intersectedPointSegment.add(point);
	}
	
	public void drawListPointSegment(Graphics2D g2d) {
		for(PointSegment point : intersectedPointSegment) {
			point.drawPointSegment(g2d);
		}
	}
	
	public Graphics2D getG2d() {
		return g2d;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public List<PointSegment> getIntersectedPointSegment() {
		return intersectedPointSegment;
	}
	
	public void addListPointSegment(List<Segment> segments) {
        for(Segment ss1: segments) {
        	
        	for(Segment ss2: segments) {
        		
        		if(!ss1.equals(ss2)) {
        			
        			if(ss1.intersectent(ss2)) {
        				
        				PointSegment point = ss1.getIntersectedPoint(ss2);
        				
        				if(!getIntersectedPointSegment().contains(point))
        					addPoingSegment(point);
        			}
        		}
        	}
        }
	}
	
}
