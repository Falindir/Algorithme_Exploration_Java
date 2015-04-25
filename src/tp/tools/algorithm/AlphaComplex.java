package tp.tools.algorithm;

import tp.tools.Form2D.Circle2D;
import tp.tools.Form2D.Triangle2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 25/04/15.
 */
public class AlphaComplex implements Algorithm<List<Triangle2D>, List<Triangle2D>>  {

    private int _alpha = 0;

    @Override
    public List<Triangle2D> run(List<Triangle2D> triangles) {
        List<Triangle2D> alphaTriangulation = new ArrayList<Triangle2D>();

        for(Triangle2D triangle : triangles) {
            Circle2D circle = new Circle2D(triangle);

            if(circle.getRadius() <= _alpha) {
                alphaTriangulation.add(triangle);
            }

        }

        return alphaTriangulation;
    }

    public int getAlpha() {
        return _alpha;
    }

    public void setAlpha(int alpha) {
        _alpha = alpha;
    }
}
