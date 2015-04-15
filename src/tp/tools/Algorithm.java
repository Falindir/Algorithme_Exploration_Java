package tp.tools;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Algorithm {
	
	public static List<Point2D> generateRandomPoint(int nbPoint, int w, int h, RolePoint role) {
		
		List<Point2D> points = new ArrayList<Point2D>();
		
		Random rand = new Random();
		
		while(points.size() <= nbPoint) {
							
			Point2D p = new Point2D(rand.nextInt(w+25)-25, rand.nextInt(h+25)-25);
			
			double centerX = w / 2;
			double centerY = h / 2;
			
			Point2D center = new Point2D((int)centerX, (int)centerY);
			
			if(center.distance(p) < 280) {	 
				
				int minDisteance = 25;
				boolean pointOk = true;
				
				for(Point2D point : points) {
					if(p.distance(point) < minDisteance) {
						pointOk = false;
						break;
					}
				}
				
				if(pointOk) {
					points.add(p);
					p.setName(""+ points.indexOf(p));
				}

			}
		}		
		
		Collections.sort(points);
		
		return points;
	}
	
	public static List<Point2D> Jarvis(List<Point2D> liste) {

		List<Point2D> l = new ArrayList<Point2D>();

		int i=0;
		Random rand=new Random();
		Point2D minPoint = liste.get(0);
		Point2D currentPoint = minPoint;
		l.add(currentPoint);
		do
		{
			// GET DIFFERENT POINT  FROM CURRENT POINT
			do {
				i = rand.nextInt(liste.size());
				minPoint = liste.get(i);
			}while(Point2D.equals(currentPoint, liste.get(i)));


			for(int j=0;j<liste.size();j++)//NextPoint
			{
				if(!Point2D.equals(liste.get(j), currentPoint) && Point2D.isLeft(currentPoint, minPoint, liste.get(j)) )
					minPoint = liste.get(j);
			}
			l.add(minPoint);
			currentPoint=minPoint;
		}
		while(!Point2D.equals(currentPoint, l.get(0)) );

		return l;
	}

    public static List<Point2D> Graham(List<Point2D> liste) {
    	// liste deja triée
    	
        List<Point2D> lUpper = new ArrayList<Point2D>();
        List<Point2D> lLower = new ArrayList<Point2D>();
        int n = liste.size();


        // BEGIN UPPER
            lUpper.add(liste.get(0));
            lUpper.add(liste.get(1));

            for(int i=2 ; i<n ; i++) {
                lUpper.add(liste.get(i));

                while(lUpper.size()>2 && Point2D.isLeft(lUpper.get(lUpper.size()-3), lUpper.get(lUpper.size()-2), lUpper.get(lUpper.size()-1))) {
                    lUpper.remove(lUpper.get(lUpper.size()-2));
                }
            }
        // END UPPER


        // BEGIN LOWER
            lLower.add(liste.get(n-1));
            lLower.add(liste.get(n-2));

            for(int i=n-1 ; i>=0 ; i--) {
                lLower.add(liste.get(i));

                while(lLower.size()>2 && Point2D.isLeft(lLower.get(lLower.size()-3), lLower.get(lLower.size()-2), lLower.get(lLower.size()-1))) {
                    lLower.remove(lLower.get(lLower.size()-2));
                }
            }

            lLower.remove(0);
            lLower.remove(lLower.size()-1);
        // END LOWER

        
        // Fusion des 2 listes (haut et bas)
        lUpper.addAll(lLower);

        return lUpper;
    }

	public static List<Point2D> Voronoi (List<Triangle2D> triangles) {

		List<Point2D> points = new ArrayList<Point2D>();

		List<Triangle2D> triangulation = new ArrayList<Triangle2D>();
		triangulation.addAll(triangles);

		for(Triangle2D triangle : triangulation) {
			Point2D point = triangle.getCenter();
			points.add(point);
		}

		return points;
	}
    
    
    public static List<Triangle2D> Delaunay (List<Triangle2D> triangles) {

		System.out.println("In Delaunay" + triangles.size());

		List<Triangle2D> triangulation = new ArrayList<Triangle2D>();
		triangulation.addAll(triangles);

		boolean flip = true;
		int cpt = 0;
		while(flip && cpt < 100) {
			flip = false;

			for(int i=0;i<triangulation.size();++i) {
				for(Triangle2D t : triangulation) {

					if(triangulation.get(i) != t && triangulation.get(i).isVoisin(t) && triangulation.get(i).isFlippable(t)) {

						triangulation.get(i).flip(t);
						flip = true;
					}

				}
			}
			cpt++;
		}
    	return triangulation;
    }
    
    





    
	public static List<Triangle2D> triangulationIncrementale(List<Point2D> points) {
		
		List<Triangle2D> triangles = new ArrayList<Triangle2D>();
		
		LinkedList<Point2D> ec = new LinkedList<Point2D>();

		if(!Point2D.isLeft(points.get(0),points.get(1),points.get(2)))
		{
			ec.add(points.get(2));
			ec.add(points.get(1));
			ec.add(points.get(0));
			ec.add(points.get(2));
		}
		else
		{
			ec.add(points.get(2));
			ec.add(points.get(0));
			ec.add(points.get(1));
			ec.add(points.get(2));
		}
		triangles.add(new Triangle2D(ec.get(0), ec.get(1), ec.get(2)));
		
		for(int i=2;i<points.size()-1;++i)
		{
			int j=0;
			
			Vector2D line=new Vector2D(points.get(i+1),ec.get(j));
			Vector2D line2=new Vector2D(points.get(i+1),ec.get(j+1));
			
			while(line.determinant(line2)<0)
			{
				triangles.add(new Triangle2D(ec.get(j),ec.get(j+1),points.get(i+1)));
				j++;
				line=new Vector2D(points.get(i+1),ec.get(j));
				line2=new Vector2D(points.get(i+1),ec.get(j+1));
			}
			
			int kRight = j;
			j=ec.size()-1;
			line=new Vector2D(points.get(i+1),ec.get(j));
			line2=new Vector2D(points.get(i+1),ec.get(j-1));
			
			while(line.determinant(line2)>0)
			{
				triangles.add(new Triangle2D(ec.get(j),ec.get(j-1),points.get(i+1)));
				j--;
				line=new Vector2D(points.get(i+1),ec.get(j));
				line2=new Vector2D(points.get(i+1),ec.get(j-1));
			}
			int kLeft=j;

			int size = ec.size();
			for(int z=0;z<kRight;z++)
				ec.removeFirst();
			for(int z=(kLeft+1);z<size;z++)
				ec.removeLast();
			ec.addFirst(points.get(i+1));
			ec.addLast(points.get(i+1));
		}
		
		
		
		return triangles;
		
	}
    
    public static List<Segment2D> generateListSegmentWithPoint(List<Point2D> points) {
    
    	List<Segment2D> s = new ArrayList<Segment2D>();
    	for(int k=0;k<points.size()-1;++k)
    	{
    	    s.add(new Segment2D(points.get(k),points.get(k+1)));
    	}
    	
    	s.add(new Segment2D(points.get(0), points.get(points.size()-1)));
    	return s;
    }
    
    public static List<Point2D> generateFormePoint(int w, int h) {
		
		List<Point2D> points = new ArrayList<Point2D>();
		
		double centerX = w / 2;
		double centerY = h / 2;
		
		Point2D center = new Point2D((int)centerX, (int)centerY);
		center.setName("0");
		points.add(center);
		int ecart = 30;
		int firstD = 10;
		int secondD = 15;
		int number = 1;
		Point2D p = new Point2D(center.getX() + ecart, center.getY());
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() + ecart, p.getY());
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX(), center.getY() - ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX(), p.getY() - ecart);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() - ecart, center.getY());
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() - ecart, p.getY());
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX(), center.getY() + ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX(), p.getY()  + ecart);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() - ecart, center.getY() - ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() - ecart + firstD, p.getY()  - ecart + firstD);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() - ecart, center.getY() + ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() - ecart + firstD, p.getY()  + ecart - firstD);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() + ecart, center.getY() - ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() + ecart - firstD, p.getY()  - ecart + firstD);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() + ecart, center.getY() + ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() + ecart - firstD, p.getY()  + ecart - firstD);
			p.setName("" + number);
			points.add(p);
		}
		
		number++;
		p = new Point2D(center.getX() + 3 * ecart, center.getY() + ecart);
		p.setName("" + number);
		points.add(p);
		
		while(p.distance(center) < 280 - ecart){
			number++;
			p = new Point2D(p.getX() + 2 * ecart - 2 * secondD, p.getY()  + ecart - secondD);
			p.setName("" + number);
			points.add(p);
		}
		
		Collections.sort(points);
		
		return points; 
	}


}
