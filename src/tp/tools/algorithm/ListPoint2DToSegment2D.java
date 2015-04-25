package tp.tools.algorithm;


import tp.tools.Form2D.*;
import tp.tools.others.RolePoint;

import java.util.*;

public class ListPoint2DToSegment2D {

    public static List<Segment2D> transform(List<Point2D> points) {
    
    	List<Segment2D> s = new ArrayList<Segment2D>();
    	for(int k=0;k<points.size()-1;++k)
    	{
    	    s.add(new Segment2D(points.get(k),points.get(k+1)));
    	}
    	
    	s.add(new Segment2D(points.get(0), points.get(points.size()-1)));
    	return s;
    }

}
