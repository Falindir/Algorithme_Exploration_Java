package tp.tp2;

import tp.tools.FrameGeometric;

public class MainTp2 {

	public static void main(String[] args) {
		
		
		FrameGeometric frame = new FrameGeometric ("TP2 - Géométrie algorithmique");
		
		int width = 612;
		int height = 792;
		
		ViewEnveloppeConvexe vRP1 = new ViewEnveloppeConvexe(width, height);
		vRP1.drawListRandomPoint(50);
		vRP1.drawEnvConvJarvis();
		frame.addView(vRP1, "Random Point Jarvis");
		
		ViewEnveloppeConvexe vRP2 = new ViewEnveloppeConvexe(width, height);
		vRP2.drawListRandomPoint(50);
		vRP2.drawEnvConvGraham();
		frame.addView(vRP2, "Random Point Graham");

		ViewEnveloppeConvexe vRP3 = new ViewEnveloppeConvexe(width, height);
		vRP3.drawListRandomPoint(50);
		vRP3.drawEnvConvOnionSkin();
		frame.addView(vRP3, "Random Point Onion");

		frame.setFrame();
	}

}
