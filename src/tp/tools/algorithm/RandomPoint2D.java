package tp.tools.algorithm;

import tp.tools.Form2D.Point2D;
import tp.tools.others.RolePoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by jimmy on 25/04/15.
 */
public class RandomPoint2D implements Algorithm <Integer, List<Point2D>> {

    private int _wight;
    private int _heigth;

    public RandomPoint2D(int wight, int heigth) {
        _wight = wight;
        _heigth = heigth;
    }

    @Override
    public List<Point2D> run(Integer numberPoint2D) {

        List<Point2D> points = new ArrayList<Point2D>();

        Random rand = new Random();

        while(points.size() <= numberPoint2D) {

            Point2D p = new Point2D(rand.nextInt(_wight+25)-25, rand.nextInt(_heigth+25)-25);

            double centerX = _wight / 2;
            double centerY = _heigth / 2;

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
}
