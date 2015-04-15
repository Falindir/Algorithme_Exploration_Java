package tp.tp4;

import tp.tools.FormOfPoint;
import tp.tools.FrameGeometric;
import tp.tools.View;
import tp.tp3.ViewTriangulation;

public class MainTp4 {
	public static void main(String[] args) {

		FrameGeometric frame = new FrameGeometric ("TP4 - Géométrie algorithmique");

		int width = 612;
		int height = 792;

		ViewAlpha vRP1 = new ViewAlpha(width, height);
		vRP1.drawForm1();
		frame.addView(vRP1, "Form 1 - points");

		ViewAlpha vRP2 = new ViewAlpha(width, height);
		vRP2.drawForm1();
		vRP2.drawTriangulationDelaunay(FormOfPoint.getForm1());
		frame.addView(vRP2, "Form 1 - Delaunay");

		ViewAlpha vRP3 = new ViewAlpha(width, height);
		vRP3.drawForm2();
		frame.addView(vRP3, "Form 2 - points");

		ViewAlpha vRP4 = new ViewAlpha(width, height);
		vRP4.drawForm2();
		vRP4.drawTriangulationDelaunay(FormOfPoint.getForm2());
		frame.addView(vRP4, "Form 2 - Delaunay");

		ViewAlpha vRP5 = new ViewAlpha(width, height);
		vRP5.drawForm3();
		frame.addView(vRP5, "Form 3 - points");

		ViewAlpha vRP6 = new ViewAlpha(width, height);
		vRP6.drawForm3();
		vRP6.drawTriangulationDelaunay(FormOfPoint.getForm3());
		frame.addView(vRP6, "Form 3 - Delaunay");

		frame.setFrame();
	}
}
