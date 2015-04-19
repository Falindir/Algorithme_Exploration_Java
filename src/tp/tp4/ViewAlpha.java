package tp.tp4;

import tp.tools.*;
import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2DWithTriangle2D;
import tp.tools.Form2D.Triangle2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewAlpha extends View {

    private int width;
    private int height;

    private List<Point2D> points;
    private List<Triangle2D> triangles;

    private List<Segment2DWithTriangle2D> segments;

    public ViewAlpha(int width, int height) {
        super(width, height);

        this.width = width;
        this.height = height;

        this.points = new ArrayList<Point2D>();
        this.triangles = new ArrayList<Triangle2D>();
        this.segments = new ArrayList<Segment2DWithTriangle2D>();

        setFocusable(true);
        requestFocus();
    }

    public void drawForm1() {
        points = FormOfPoint.getForm1();
    }

    public void drawForm2() {
        points = FormOfPoint.getForm2();
    }

    public void drawForm3() {
        points = FormOfPoint.getForm3();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaintMode();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);

        //double radius = 300;
        //double centerX = width / 2;
        //double centerY = height / 2;
        //Shape theCircle = new Ellipse2D.Double(centerX - radius, centerY - radius, 2.0 * radius, 2.0 * radius);
        //g2d.setColor(Color.RED);
        //g2d.draw(theCircle);

        for (Point2D p : points) {
            g2d.setColor(p.getColor());
            p.draw(g2d);
            p.drawName(g2d);
        }

        int i = 1;

        System.out.println("Paint " + triangles.size());

        if(segments.size() == 0) {

            for (Triangle2D triangle : triangles) {
                triangle.draw(g2d);
                //triangle.getCenter().setName("O" + i);
                //triangle.getCenter().draw(g2d);
                i++;
            }

        }

        for(Segment2DWithTriangle2D segment : segments) {
            segment.draw(g2d);
        }


    }

    public void drawTriangulationAlphaComplexe(int alpha){
        triangles = Algorithm.AlphaComplex(triangles, alpha);

    }

    public void drawTriangulationDelaunay (List<Point2D> point, int type) {

        List<Point2D> p = new ArrayList<Point2D>();
        p.addAll(point);
        Collections.sort(p);
        triangles = Algorithm.triangulationIncrementale(p);
        List<Triangle2D> temp = new ArrayList<Triangle2D>();
        temp.addAll(Algorithm.Delaunay(triangles));
        triangles.clear();
        triangles.addAll(temp);
/*

/*
        if(type == 1) {
            triangles.clear();
            triangles.addAll(FormOfPoint.getForm1Triangle());

        }
        else {
            triangles.clear();
            triangles.addAll(FormOfPoint.getForm1Triangle());
            List<Triangle2D> temp = new ArrayList<Triangle2D>();
            temp.addAll(Algorithm.Delaunay(triangles));
            triangles.clear();
            triangles.addAll(temp);

        }*/

    }

    public List<Point2D> getPoints() {
        return points;
    }

    public List<Triangle2D> getTriangles() {
        return triangles;
    }

    public void drawTriangulationAlphaShape(int alpha) {
        segments.addAll(Algorithm.AlphaShape(triangles, alpha, points));
    }
}
