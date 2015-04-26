package tp.tp4;

import tp.tools.others.FormOfPoint;
import tp.tools.visualisation.FrameGeometric;

public class MainTp4 {
	public static void main(String[] args) {

		FrameGeometric frame = new FrameGeometric ("TP4 - Géométrie algorithmique");

		int width = 612;
		int height = 792;

		ControllerTP4 controllerTP4 = new ControllerTP4();

		ViewAlpha vRP1 = new ViewAlpha(width, height, controllerTP4);
		controllerTP4.addView(vRP1);
		vRP1.drawForm1();
		//vRP1.drawForm2();
		//vRP1.drawForm3();
		frame.addView(vRP1, "Points");

		ViewAlpha vrRP1B = new ViewAlpha(width, height, controllerTP4);
		controllerTP4.addView(vrRP1B);
		vrRP1B.drawTriangulationIncrementale();
		frame.addView(vrRP1B, "Incrementale");

		ViewAlpha vRP2 = new ViewAlpha(width, height, controllerTP4);
		controllerTP4.addView(vRP2);
		vRP2.drawTriangulationDelaunay();
		frame.addView(vRP2, "Delaunay");

		ViewAlpha vRP3 = new ViewAlpha(width, height, controllerTP4);
		controllerTP4.addView(vRP3);
		vRP3.drawTriangulationAlphaComplexe(50);
		frame.addView(vRP3, "Alpha Complexe");

		ViewAlpha vRP4 = new ViewAlpha(width, height, controllerTP4);
		controllerTP4.addView(vRP4);
		vRP4.drawTriangulationAlphaShape(10);
		frame.addView(vRP4, "Alpha Shape");

		frame.setFrame();
	}
}
