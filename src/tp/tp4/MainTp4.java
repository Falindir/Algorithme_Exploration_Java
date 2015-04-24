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

		ViewAlpha vrRP1B = new ViewAlpha(width, height);
		vrRP1B.drawTriangulationIncrementale(FormOfPoint.getForm1());
		frame.addView(vrRP1B, "Form 1 - Incrementale");

		ViewAlpha vRP2 = new ViewAlpha(width, height);
		vRP2.drawForm1();
		vRP2.drawTriangulationDelaunay(FormOfPoint.getForm1(), 2);
		frame.addView(vRP2, "Form 1 - Delaunay");

		ViewAlpha vRP3 = new ViewAlpha(width, height);
		vRP3.drawForm1();
		vRP3.drawTriangulationDelaunay(FormOfPoint.getForm1(), 2);
		vRP3.drawTriangulationAlphaComplexe(50);
		frame.addView(vRP3, "Form 1 - Alpha complexe");

		ViewAlpha vRP4 = new ViewAlpha(width, height);
		vRP4.drawForm1();
		vRP4.drawTriangulationDelaunay(FormOfPoint.getForm1(), 2);
		vRP4.drawTriangulationAlphaShape(50);
		frame.addView(vRP4, "Form 1 - Alpha shape");

		/*
		ViewAlpha vRP5 = new ViewAlpha(width, height);
		vRP5.drawForm2();
		frame.addView(vRP5, "Form 2 - points");

		ViewAlpha vRP5B = new ViewAlpha(width, height);
		vRP5B.drawTriangulationIncrementale(FormOfPoint.getForm2());
		frame.addView(vRP5B, "Form 2 - Incrementale");

		ViewAlpha vRP6 = new ViewAlpha(width, height);
		vRP6.drawTriangulationDelaunay(FormOfPoint.getForm2(), 2);
		frame.addView(vRP6, "Form 2 - Delaunay");
*/
		frame.setFrame();

	}
}
