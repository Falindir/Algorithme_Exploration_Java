package tp.tools.algorithm;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Triangle2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 25/04/15.
 */
public class Voronoi implements Algorithm<List<Triangle2D>, List<Point2D>> {

    @Override
    public List<Point2D> run(List<Triangle2D> triangles) {
        List<Point2D> points = new ArrayList<Point2D>();

        List<Triangle2D> triangulation = new ArrayList<Triangle2D>();
        triangulation.addAll(triangles);

        for(Triangle2D triangle : triangulation) {
            Point2D point = triangle.getCenter();
            points.add(point);
        }

        return points;
    }

}
