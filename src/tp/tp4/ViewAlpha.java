package tp.tp4;

import tp.tools.Form2D.Point2D;
import tp.tools.Form2D.Segment2D;
import tp.tools.Form2D.Segment2DWithTriangle2D;
import tp.tools.Form2D.Triangle2D;
import tp.tools.others.FormOfPoint;
import tp.tools.visualisation.View;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewAlpha extends View implements MouseListener {

    private ControllerTP4 _controller;

    private boolean _isIncremental = false;
    private boolean _isDelaunay = false;
    private boolean _isAlphaComplex = false;
    private boolean _isAlphaShape = false;

    public ViewAlpha(int width, int height, ControllerTP4 controller) {
        super(width, height, controller);
        _controller = controller;
        setFocusable(true);
        requestFocus();
        addMouseListener(this);
    }

    public void drawForm1() {
        _controller.getPoints().addAll(FormOfPoint.getForm1());
    }

    public void drawForm2() {
        _controller.getPoints().addAll(FormOfPoint.getForm2());
    }

    public void drawForm3() {
        _controller.getPoints().addAll(FormOfPoint.getForm3());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaintMode();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	RenderingHints.VALUE_ANTIALIAS_ON);

        for (Point2D p : _controller.getPoints()) {
            g2d.setColor(p.getColor());
            p.draw(g2d);
            p.drawName(g2d);
        }

        if(_isIncremental) {
            for(Triangle2D triangle : _controller.getIncrementalTriangulation()) {
                triangle.draw(g2d);
            }
        }

        if(_isDelaunay) {
            for(Triangle2D triangle : _controller.getDelaunayTriangulation()) {
                triangle.draw(g2d);
            }
        }

        if(_isAlphaComplex) {
            for(Triangle2D triangle : _controller.getComplexTriangulation()) {
                triangle.draw(g2d);
            }
        }

        if(_isAlphaShape) {
            for(Segment2D segment2D : _controller.getSegmentsAlphaShape()) {
                segment2D.setLineColor(Color.CYAN);
                segment2D.draw(g2d);
            }
        }


    }

    public void drawTriangulationAlphaComplexe(int alpha){
        _isAlphaComplex = true;
        _controller.getAlphaComplex().setAlpha(alpha);
        _controller.getComplexTriangulation().addAll(_controller.getAlphaComplex().run(_controller.getDelaunayTriangulation()));
    }

    public void drawTriangulationIncrementale() {
        _isIncremental = true;
        Collections.sort(_controller.getPoints());
        _controller.getIncrementalTriangulation().addAll(_controller.getIncTriang().run(_controller.getPoints()));
    }

    public void drawTriangulationDelaunay () {
        _isDelaunay = true;
        List<Triangle2D> temp = new ArrayList<Triangle2D>();
        temp.addAll(_controller.getDelaunay().run(_controller.getIncTriang().run(_controller.getPoints())));
        _controller.getDelaunayTriangulation().addAll(_controller.getDelaunay().run(temp));
    }

    public void drawTriangulationAlphaShape(int alpha) {
        _isAlphaShape = true;
        _controller.getAlphaShape().setAlpha(alpha);
        _controller.getAlphaShape().setPoints(_controller.getPoints());
        _controller.getSegmentsAlphaShape().addAll(_controller.getAlphaShape().run(_controller.getDelaunayTriangulation()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(_isAlphaComplex) {
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                _controller.getAlphaComplex().setAlpha(_controller.getAlphaComplex().getAlpha() - 5);
                _controller.getComplexTriangulation().clear();
                _controller.getComplexTriangulation().addAll(_controller.getAlphaComplex().run(_controller.getDelaunayTriangulation()));
            }
            else if(e.getButton() == MouseEvent.BUTTON3)
            {
                _controller.getAlphaComplex().setAlpha(_controller.getAlphaComplex().getAlpha() + 5);
                _controller.getComplexTriangulation().clear();
                _controller.getComplexTriangulation().addAll(_controller.getAlphaComplex().run(_controller.getDelaunayTriangulation()));
            }

            repaint();

            System.out.println("Alpha complex : " + _controller.getAlphaComplex().getAlpha());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
