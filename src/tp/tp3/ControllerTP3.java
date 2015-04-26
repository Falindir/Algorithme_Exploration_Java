package tp.tp3;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Triangle2D;
import tp.tools.algorithm.Delaunay;
import tp.tools.algorithm.IncrementalTriangulation;
import tp.tools.algorithm.RandomPoint2D;
import tp.tools.algorithm.Voronoi;
import tp.tools.visualisation.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jimmy on 26/04/15.
 */
public class ControllerTP3 extends Controller {

    private RandomPoint2D randomPoint;
    private IncrementalTriangulation incTriangul = new IncrementalTriangulation();
    private Delaunay delaunay = new Delaunay();
    private Voronoi voronoi = new Voronoi();

    private List<Point2D> _points = new ArrayList<Point2D>();
    private List<Triangle2D> _trianglesIncrementale = new ArrayList<Triangle2D>();
    private List<Triangle2D> _triIncreForDelau = new ArrayList<Triangle2D>();
    private List<Triangle2D> _trianglesDelaunay = new ArrayList<Triangle2D>();
    private static List<Point2D> _voronois = new ArrayList<Point2D>();

    public ControllerTP3 () {
    }

    public List<Point2D> getPoints() {
        return _points;
    }

    public void addPoints(Point2D point) {
        _points.add(point);
        Collections.sort(_points);
        _trianglesIncrementale.clear();
        _trianglesIncrementale.addAll(incTriangul.run(_points));
        _triIncreForDelau.clear();
        _triIncreForDelau.addAll(incTriangul.run(_points));
        _trianglesDelaunay.clear();
        _trianglesDelaunay.addAll(delaunay.run(_triIncreForDelau));
        _voronois.clear();
        _voronois.addAll(voronoi.run(_trianglesDelaunay));
    }

    public List<Triangle2D> getIncrementalTriangulation() {
        return _trianglesIncrementale;
    }

    public List<Triangle2D> getDelaunayTriangulation() {
        return _trianglesDelaunay;
    }

    public RandomPoint2D getRandomPoint() {
        return randomPoint;
    }

    public IncrementalTriangulation getIncTriangul() {
        return incTriangul;
    }

    public Delaunay getDelaunay() {
        return delaunay;
    }

    public Voronoi getVoronoi() {
        return voronoi;
    }

    public void setRandomPoint(RandomPoint2D randomPoint) {
        this.randomPoint = randomPoint;
    }

    public List<Point2D> getCenterVoronoi() {
        return _voronois;
    }
}
