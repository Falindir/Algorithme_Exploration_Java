package tp.tp3;

import tp.tools.FrameGeometric;

public class MainTp3 {
	
	public static void main(String[] args) {
		FrameGeometric frame = new FrameGeometric ("TP3 - Géométrie algorithmique");
	
		int width = 612;
		int height = 792;
	
		ViewTriangulation vRP1 = new ViewTriangulation(width, height);
		vRP1.drawListRandomPoint(25);
		vRP1.drawTriangulationIncrementale();
		frame.addView(vRP1, "Random Point trianguler incrementale");
		
		ViewTriangulation vRP2 = new ViewTriangulation(width, height);
		vRP2.drawSameRandomPoint(vRP1.get_points());
		vRP2.drawTriangulationDelaunay(vRP1.getTriangles());
		frame.addView(vRP2, "Random Point trianguler delaunay");

		ViewTriangulation vRP3 = new ViewTriangulation(width, height);
		vRP3.drawSameRandomPoint(vRP1.get_points());
		vRP3.drawTriangulationDelaunay(vRP1.getTriangles());
		vRP3.drawTriangulationVoronoi();
		frame.addView(vRP3, "Random Point trianguler voronoi");
		
		
		frame.setFrame();
		
	}

}
