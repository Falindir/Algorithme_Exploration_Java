package tp.tools.algorithm;

import tp.tools.Form2D.*;
import tp.tools.Form2D.needRefactor.Segment;

import java.util.*;

/**
 * Created by jimmy on 25/04/15.
 */
public class AlphaShape implements Algorithm<List<Triangle2D>, List<Segment2D>> {

    private int _alpha = 0;

    private List<Point2D> _points = new ArrayList<Point2D>();

    @Override
    public List<Segment2D> run(List<Triangle2D> triangles) {

        List<Segment2DWithTriangle2D> segments = new ArrayList<Segment2DWithTriangle2D>();
        List<Triangle2D> sorted = new ArrayList<Triangle2D>();
        sorted.addAll(triangles);

        Set<Point2D> points = new TreeSet<Point2D>();
        for(Triangle2D triangle2D : sorted) {
            points.addAll(triangle2D.getPoints());

            for(Segment2D edge : triangle2D.getEdges()) {
                Segment2DWithTriangle2D segment2D1 = new Segment2DWithTriangle2D(edge.getP1(),edge.getP2(),triangle2D);

                boolean isIn = false;
                int k = 0;
                while(k < segments.size() && !isIn) {
                    if(segments.get(k).isSameSegment(edge))
                        isIn = true;
                    k++;
                }


               // if(segments.contains(segment2D1))
                if(isIn)
                {
                  //  System.out.println("SECOND");
                   /* for(int i=0;i<segments.size();++i)
                    {
                        if(segments.get(i).equals(segment2D1)){
                            segments.get(i).addTriangle(triangle2D);
                        }
                    }
                    */
                    segments.get(k-1).addTriangle(triangle2D);
                }
                else
                    segments.add(segment2D1);
            }
        }

        List<Segment2D> result = new ArrayList<Segment2D>();

        for(Segment2DWithTriangle2D segment : segments)
        {
            Circle2D c = segment.giveCircle();
            boolean found = false;
            for(Point2D point: points)
            {
                if(!segment.havePoint(point) && c.isInCircle(point)) {
                    found = true;
                    break;
                }
            }

            double rT = new Circle2D(segment.getTriangles().get(0)).getRadius();
            double rT2 = Double.MAX_VALUE;
            if(segment.getTriangles().size()>1)
                rT2= new Circle2D(segment.getTriangles().get(1)).getRadius();
            double a,b;
            if(!found) {
                a = segment.length()/2.0;
                b = (segment.getTriangles().size()>1)? Math.max(rT, rT2) : rT;
            }
            else
            {
                a = (segment.getTriangles().size()>1)? Math.min(rT,rT2) : rT;
                b = (segment.getTriangles().size()>1)? Math.max(rT,rT2) : rT;
            }
            if(a<= _alpha && _alpha <= b)
                result.add(segment);
        }

/*
        int i = 0;
        int j = 0;
        for(Segment2DWithTriangle2D segment2DWithTriangle2D : segments) {

            if(segment2DWithTriangle2D.getTriangles().size() == 1) {
                i++;
            }
            else if(segment2DWithTriangle2D.getTriangles().size() == 2) {
                j++;
            }
            else {
                System.out.println("BUG MERDE");
            }
        }

        System.out.println(i);
        System.out.println(j);

        result.addAll(segments);
        System.out.println(triangles.size());
        System.out.println(result.size());
*/
        return result;

    }
/*
    @Override
    public List<Segment2DWithTriangle2D> run(List<Triangle2D> triangles) {
        List<Segment2DWithTriangle2D> alphaTriangulation = new ArrayList<Segment2DWithTriangle2D>();

        Set<Segment2DWithTriangle2D> segments = new HashSet<Segment2DWithTriangle2D>();

        for(Triangle2D triangle : triangles) {
            Segment2DWithTriangle2D s1 = new Segment2DWithTriangle2D(triangle.getA(), triangle.getB(), _points, _alpha);
            Segment2DWithTriangle2D s2 = new Segment2DWithTriangle2D(triangle.getB(), triangle.getC(), _points, _alpha);
            Segment2DWithTriangle2D s3 = new Segment2DWithTriangle2D(triangle.getC(), triangle.getA(), _points, _alpha);

            if(segments.contains(s1))
                s1.setTriangle2(triangle);
            else {
                s1.setTriangle(triangle);
                segments.add(s1);
            }

            if(segments.contains(s2))
                s2.setTriangle2(triangle);
            else {
                s2.setTriangle(triangle);
                segments.add(s2);
            }

            if(segments.contains(s3))
                s3.setTriangle2(triangle);
            else {
                s3.setTriangle(triangle);
                segments.add(s3);
            }

        }


        for(Segment2DWithTriangle2D segment : segments) {
            segment.calculShape();
            if(segment.isOk())
                alphaTriangulation.add(segment);
        }

        return alphaTriangulation;
    }
    */


    public int getAlpha() {
        return _alpha;
    }

    public void setAlpha(int alpha) {
        _alpha = alpha;
    }

    public List<Point2D> getPoints() {
        return _points;
    }

    public void setPoints(List<Point2D> points) {
        _points = points;
    }
}
