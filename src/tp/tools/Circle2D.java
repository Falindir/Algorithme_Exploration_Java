package tp.tools;

/**
 * Created by jimmy on 15/04/15.
 */
public class Circle2D {

    private Point2D _center;
    private double _radius;

    public Circle2D(Point2D center, double radius) {
        _center = center;
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
        double x = v*(n3*m2-n2*m3);
        double y = v*(n1*m3-n3*m1);

        Point2D center = new Point2D((int) x, (int) y);
        center.setName("O");

        _center = center;

        double pab = triangle.getA().distance(triangle.getB());
        double pbc = triangle.getB().distance(triangle.getC());
        double pac = triangle.getA().distance(triangle.getC());

        Vector2D vab = new Vector2D(triangle.getA(), triangle.getB());
        Vector2D vac = new Vector2D(triangle.getA(), triangle.getC());

        double r = (pab * pbc * pac) / (2 * vab.determinant(vac));

        if(r < 0)
            r *= -1;

        _radius = r;
    }

    public boolean isInCircle(Point2D point2D) {
        Vector2D vector = new Vector2D(_center,point2D);
        return Math.abs(vector.norm()) < Math.abs(_radius);

       // return _center.distance(point2D) < _radius;
    }


}

