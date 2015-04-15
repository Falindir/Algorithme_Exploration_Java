package tp.tp2;

import tp.tools.FrameGeometric;

public class MainTp2 {

	public static void main(String[] args) {
		
		
		FrameGeometric frame = new FrameGeometric ("TP2 - Géométrie algorithmique");
		
		int width = 612;
		int height = 792;
		
		ViewEnveloppeConvexe vRP1 = new ViewEnveloppeConvexe(width, height);
		vRP1.drawListRandomPoint(25);

		frame.addView(vRP1, "Random Point Jarvis");
		
		ViewEnveloppeConvexe vRP2 = new ViewEnveloppeConvexe(width, height);
		vRP2.drawListRandomPoint(25);
		vRP2.drawEnvConv();
		frame.addView(vRP2, "Random Point Graham");
		
		ViewEnveloppeConvexe vFP = new ViewEnveloppeConvexe(width, height);
		vFP.drawListFormPoint();
		vFP.drawEnvConv();
		frame.addView(vFP, "Form Point");
		
		frame.setFrame();

	}

}
