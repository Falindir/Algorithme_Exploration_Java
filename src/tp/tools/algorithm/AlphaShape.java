package tp.tools.algorithm;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2DWithTriangle2D;
import tp.tools.Form2D.Triangle2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jimmy on 25/04/15.
 */
public class AlphaShape implements Algorithm<List<Triangle2D>, List<Segment2DWithTriangle2D>> {

    private int _alpha = 0;

    private List<Point2D> _points = new ArrayList<Point2D>();

    @Override
    public List<Segment2DWithTriangle2D> run(List<Triangle2D> triangles) {
        List<Segment2DWithTriangle2D> alphaTriangulation = new ArrayList<Segment2DWithTriangle2D>();

        Set<Segment2DWithTriangle2D> segments = new HashSet<Segment2DWithTriangle2D>();

        for(Triangle2D triangle : triangles) {
            Segment2DWithTriangle2D s1 = new Segment2DWithTriangle2D(triangle.getA(), triangle.getB(), _points, _alpha);
            Segment2DWithTriangle2D s2 = new Segment2DWithTriangle2D(triangle.getA(), triangle.getB(), _points, _alpha);
            Segment2DWithTriangle2D s3 = new Segment2DWithTriangle2D(triangle.getA(), triangle.getB(), _points, _alpha);

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
