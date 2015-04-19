package tp.tp1;

import tp.tools.FrameGeometric;
import tp.tp2.ViewEnveloppeConvexe;

public class MainTp1 {

	public static void main(String[] args) {

		FrameGeometric frame = new FrameGeometric ("TP1 - Intersection de segments");

		int width = 612;
		int height = 792;

		ViewIntersectSegment vRP1 = new ViewIntersectSegment(width, height);
		vRP1.drawListRandomPoint(9);
		vRP1.drawListRandomSegment();
		vRP1.drawListIntersectRandomSegment();
		frame.addView(vRP1, "Random Point");
		frame.setFrame();
	}

}
