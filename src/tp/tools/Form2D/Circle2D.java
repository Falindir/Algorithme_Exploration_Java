package tp.tools.Form2D;

/**
 * Created by jimmy on 15/04/15.
 */
public class Circle2D {

    private double _posX;
    private double _posY;
    private double _radius;

    public Circle2D(Point2D center, double radius) {
        _posX = center.getX();
        _posY = center.getY();
        _radius = radius;
    }

    public Circle2D(double x, double y, double radius) {
        _posX = x;
        _posY = y;
        _radius = radius;
    }

    public Circle2D(Triangle2D triangle) {
        int x1 = triangle.getA().getX();
        int y1 = triangle.getA().getY();
        int x2 = triangle.getB().getX();
        int y2 = triangle.getB().getY();
        int x3 = triangle.getC().getX();
        int y3 = triangle.getC().getY();

        double v,m1, m2, m3, n1, n2, n3;
        m1 = 2.*(x3-x2); m2 = -2.*(y2-y3); m3 = (y2-y3)*(y2+y3)-(x3-x2)*(x2+x3);
        n1 = 2.*(x3-x1); n2 = -2.*(y1-y3); n3 = (y1-y3)*(y1+y3)-(x3-x1)*(x1+x3);
        v=1./(n2*m1-n1*m2);

        _posX = v*(n3*m2-n2*m3);
        _posY = v*(n1*m3-n3*m1);

        double r = new Vector2D(new Point2D((int) _posX, (int) _posY), triangle.getA()).norm();

        _radius = r;
    }

    public boolean isInCircle(Point2D point2D) {
        Vector2D vector = new Vector2D(new Point2D((int) _posX, (int) _posY),point2D);
        return Math.abs(vector.norm()) < Math.abs(_radius);
    }

    public double getRadius() {
        return _radius;
    }
}

