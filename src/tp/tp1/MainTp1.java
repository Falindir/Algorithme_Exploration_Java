package tp.tp1;

import tp.tools.visualisation.Controller;
import tp.tools.visualisation.FrameGeometric;

public class MainTp1 {

	public static void main(String[] args) {

		FrameGeometric frame = new FrameGeometric ("TP1 - Intersection de segments");

		int width = 612;
		int height = 792;

		ControllerTP1 controllerTP1 = new ControllerTP1();

		ViewIntersectSegment vRP1 = new ViewIntersectSegment(width, height, controllerTP1);
		controllerTP1.addView(vRP1);
		vRP1.drawListRandomSegment(50);
		frame.addView(vRP1, "Random Point");
		frame.setFrame();

	}

}
