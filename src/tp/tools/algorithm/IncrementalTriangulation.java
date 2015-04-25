package tp.tools.algorithm;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Triangle2D;
import tp.tools.Form2D.Vector2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jimmy on 25/04/15.
 */
public class IncrementalTriangulation implements Algorithm<List<Point2D>, List<Triangle2D>> {

    @Override
    public List<Triangle2D> run(List<Point2D> points) {

        List<Triangle2D> triangles = new ArrayList<Triangle2D>();

        LinkedList<Point2D> ec = new LinkedList<Point2D>();

        if(!Point2D.isLeft(points.get(0),points.get(2),points.get(1)))
        {
            ec.add(points.get(2));
            ec.add(points.get(0));
            ec.add(points.get(1));
            ec.add(points.get(2));
        }
        else
        {
            ec.add(points.get(2));
            ec.add(points.get(1));
            ec.add(points.get(0));
            ec.add(points.get(2));
        }
        triangles.add(new Triangle2D(ec.get(0), ec.get(1), ec.get(2)));

        for(int i=2;i<points.size()-1;++i)
        {
            int j=0;

            Vector2D line=new Vector2D(points.get(i+1),ec.get(j));
            Vector2D line2=new Vector2D(points.get(i+1),ec.get(j+1));

            while(line.determinant(line2)<=0)
            {
                triangles.add(new Triangle2D(ec.get(j),ec.get(j+1),points.get(i+1)));
                j++;
                line=new Vector2D(points.get(i+1),ec.get(j));
                line2=new Vector2D(points.get(i+1),ec.get(j+1));
            }

            int kRight = j;
            j=ec.size()-1;
            line=new Vector2D(points.get(i+1),ec.get(j));
            line2=new Vector2D(points.get(i+1),ec.get(j-1));

            while(line.determinant(line2)>=0)
            {
                triangles.add(new Triangle2D(ec.get(j),ec.get(j-1),points.get(i+1)));
                j--;
                line=new Vector2D(points.get(i+1),ec.get(j));
                line2=new Vector2D(points.get(i+1),ec.get(j-1));
            }
            int kLeft=j;

            int size = ec.size();
            for(int z=0;z<kRight;z++)
                ec.removeFirst();
            for(int z=(kLeft+1);z<size;z++)
                ec.removeLast();
            ec.addFirst(points.get(i+1));
            ec.addLast(points.get(i+1));
        }

        return triangles;
    }
}
