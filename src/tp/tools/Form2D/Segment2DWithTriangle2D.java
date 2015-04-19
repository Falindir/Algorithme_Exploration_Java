package tp.tools.Form2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 17/04/15.
 */
public class Segment2DWithTriangle2D extends  Segment2D{

    private Triangle2D triangle = null;
    private Triangle2D triangle2 = null;

    private Circle2D circle;

    private double alpha = 0;

    private List<Point2D> points = new ArrayList<Point2D>();

    private double A = 0;
    private double B = Double.MAX_VALUE - 1;

    public Segment2DWithTriangle2D(Point2D p1, Point2D p2, List<Point2D> points, double alpha) {
        super(p1, p2);

        Point2D center = new Point2D((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
        circle = new Circle2D(center, p1.distance(p2) / 2);

        this.points = points;

        this.alpha = alpha;
    }

    public Triangle2D getTriangle() {
        return triangle;
    }

    public void calculShape() {

        boolean cont = true;

        int j = 0;
        while(j < points.size() && cont) {
            if(circle.isInCircle(points.get(j)))
                cont = false;

            j++;
        }

        if(cont) {
            calculA();
            calculB();
        }
        else {
            calculABis();
            calculB();
        }


    }

    public void setTriangle(Triangle2D triangle) {
        this.triangle = triangle;
    }

    public Triangle2D getTriangle2() {
        return triangle2;
    }

    public void setTriangle2(Triangle2D triangle2) {
        this.triangle2 = triangle2;
    }

    public void calculA() {
        A = getP1().distance(getP2()) / 2;
    }

    public void calculABis() {
        Circle2D circle = new Circle2D(triangle);

        if(triangle2 == null)
           A = circle.getRadius();

        else {
            Circle2D circle2 = new Circle2D(triangle2);
            A = Math.min(circle.getRadius(), circle2.getRadius());
        }

    }

    public void calculB() {
        Circle2D circle = new Circle2D(triangle);

        if(triangle2 == null)
           B = circle.getRadius();

        else {
            Circle2D circle2 = new Circle2D(triangle2);
            B = Math.max(circle.getRadius(), circle2.getRadius());
        }

    }

    public boolean isOk() {
        return A <= alpha && alpha <= B;
    }

}
