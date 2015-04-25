package tp.tools.algorithm;

import tp.tools.Form2D.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jimmy on 25/04/15.
 */
public class Jarvis implements Algorithm <List<Point2D>, List<Point2D>> {

    @Override
    public List<Point2D> run(List<Point2D> liste) {

        List<Point2D> l = new ArrayList<Point2D>();

        int i=0;
        Random rand=new Random();
        Point2D minPoint = liste.get(0);
        Point2D currentPoint = minPoint;
        l.add(currentPoint);
        do
        {
            // GET DIFFERENT POINT  FROM CURRENT POINT
            do {
                i = rand.nextInt(liste.size());
                minPoint = liste.get(i);
            }while(Point2D.equals(currentPoint, liste.get(i)));


            for(int j=0;j<liste.size();j++)//NextPoint
            {
                if(!Point2D.equals(liste.get(j), currentPoint) && Point2D.isLeft(currentPoint, minPoint, liste.get(j)) )
                    minPoint = liste.get(j);
            }
            l.add(minPoint);
            currentPoint=minPoint;
        }
        while(!Point2D.equals(currentPoint, l.get(0)) );

        return l;
    }
}
