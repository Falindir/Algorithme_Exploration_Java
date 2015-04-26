package tp.tp4;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;
import tp.tools.Form2D.Segment2DWithTriangle2D;
import tp.tools.Form2D.Triangle2D;
import tp.tools.algorithm.AlphaComplex;
import tp.tools.algorithm.AlphaShape;
import tp.tools.algorithm.Delaunay;
import tp.tools.algorithm.IncrementalTriangulation;
import tp.tools.visualisation.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 26/04/15.
 */
public class ControllerTP4 extends Controller {

    private List<Point2D> _points = new ArrayList<Point2D>();

    private List<Triangle2D> _trianglesIncrementale = new ArrayList<Triangle2D>();
    private List<Triangle2D> _trianglesDelaunay = new ArrayList<Triangle2D>();
    private List<Triangle2D> _trianglesComplex = new ArrayList<Triangle2D>();

    private List<Segment2D> segmentsAlphaShape = new ArrayList<Segment2D>();

    private IncrementalTriangulation incTriang = new IncrementalTriangulation();
    private Delaunay delaunay = new Delaunay();
    private AlphaComplex alphaComplex = new AlphaComplex();
    private AlphaShape alphaShape = new AlphaShape();

    public ControllerTP4 () {
    }

    public List<Point2D> getPoints() {
        return _points;
    }

    public List<Triangle2D> getIncrementalTriangulation() {
        return _trianglesIncrementale;
    }

    public List<Triangle2D> getDelaunayTriangulation() {
        return _trianglesDelaunay;
    }

    public List<Triangle2D> getComplexTriangulation() {
        return _trianglesComplex;
    }

    public List<Segment2D> getSegmentsAlphaShape() {
        return segmentsAlphaShape;
    }

    public AlphaShape getAlphaShape() {
        return alphaShape;
    }

    public AlphaComplex getAlphaComplex() {
        return alphaComplex;
    }

    public Delaunay getDelaunay() {
        return delaunay;
    }

    public IncrementalTriangulation getIncTriang() {
        return incTriang;
    }
}
