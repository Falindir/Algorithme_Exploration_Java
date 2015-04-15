package tp.tp1;

import tp.tools.FrameGeometric;
import tp.tp2.ViewEnveloppeConvexe;

public class MainTp1 {

	public static void main(String[] args) {

		FrameGeometric frame = new FrameGeometric ("TP1 - Intersection de segments");

		int width = 612;
		int height = 792;

		ViewEnveloppeConvexe vRP1 = new ViewEnveloppeConvexe(width, height);
		vRP1.drawListRandomPoint(25);

		frame.addView(vRP1, "Random Point");

		frame.setFrame();
	}

}
