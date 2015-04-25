package tp.tools.algorithm;


import tp.tools.Form2D.Triangle2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 25/04/15.
 */
public class Delaunay implements Algorithm<List<Triangle2D>, List<Triangle2D>> {

    @Override
    public List<Triangle2D> run(List<Triangle2D> triangles) {

        List<Triangle2D> triangulation = new ArrayList<Triangle2D>();
        triangulation.addAll(triangles);

        boolean flip = true;
        int cpt = 0;
        while(flip && cpt < 100) {
            flip = false;

            for(int i=0;i<triangulation.size();++i) {
                for(Triangle2D t : triangulation) {

                    if(triangulation.get(i) != t && triangulation.get(i).isVoisin(t) && triangulation.get(i).isFlippable(t)) {

                        triangulation.get(i).flip(t);
                        flip = true;
                    }

                }
            }
            cpt++;
        }
        return triangulation;
    }
}
