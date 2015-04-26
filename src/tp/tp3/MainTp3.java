package tp.tp3;

import tp.tools.Form2D.Point2D;
import tp.tools.visualisation.FrameGeometric;

import java.util.ArrayList;

public class MainTp3 {
	
	public static void main(String[] args) {
		FrameGeometric frame = new FrameGeometric ("TP3 - Géométrie algorithmique");
	
		int width = 612;
		int height = 792;

		ControllerTP3 controllerTP3 = new ControllerTP3();

		ViewTriangulation vRP1 = new ViewTriangulation(width, height, controllerTP3);
		vRP1.drawListRandomPoint(20);
		vRP1.drawTriangulationIncrementale();
		frame.addView(vRP1, "Triangulation Incrementale");
		controllerTP3.addView(vRP1);

		ViewTriangulation vRP2 = new ViewTriangulation(width, height, controllerTP3);
		vRP2.drawTriangulationDelaunay();
		frame.addView(vRP2, "Triangulation Delaunay");
		controllerTP3.addView(vRP2);

		ViewTriangulation vRP3 = new ViewTriangulation(width, height, controllerTP3);
		controllerTP3.addView(vRP3);
		vRP3.drawTriangulationVoronoi();
		frame.addView(vRP3, "Triangulation Voronoi");

		frame.setFrame();
		
	}

}
