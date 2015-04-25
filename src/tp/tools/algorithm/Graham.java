package tp.tools.algorithm;

import tp.tools.*;
import tp.tools.Form2D.Point2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jimmy on 25/04/15.
 */
public class Graham implements Algorithm<List<Point2D>, List<Point2D>> {

    @Override
    public List<Point2D> run(List<Point2D> liste) {
        // liste deja triée

        List<Point2D> lUpper = new ArrayList<Point2D>();
        List<Point2D> lLower = new ArrayList<Point2D>();
        int n = liste.size();


        // BEGIN UPPER
        lUpper.add(liste.get(0));
        lUpper.add(liste.get(1));

        for(int i=2 ; i<n ; i++) {
            lUpper.add(liste.get(i));

            while(lUpper.size()>2 && Point2D.isLeft(lUpper.get(lUpper.size()-3), lUpper.get(lUpper.size()-2), lUpper.get(lUpper.size()-1))) {
                lUpper.remove(lUpper.get(lUpper.size()-2));
            }
        }
        // END UPPER


        // BEGIN LOWER
        lLower.add(liste.get(n-1));
        lLower.add(liste.get(n-2));

        for(int i=n-1 ; i>=0 ; i--) {
            lLower.add(liste.get(i));

            while(lLower.size()>2 && Point2D.isLeft(lLower.get(lLower.size()-3), lLower.get(lLower.size()-2), lLower.get(lLower.size()-1))) {
                lLower.remove(lLower.get(lLower.size()-2));
            }
        }

        lLower.remove(0);
        lLower.remove(lLower.size()-1);
        // END LOWER


        // Fusion des 2 listes (haut et bas)
        lUpper.addAll(lLower);

        return lUpper;
    }
}
