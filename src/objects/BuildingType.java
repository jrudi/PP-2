package objects;

import java.awt.Polygon;
import game.GameSettings;

public enum BuildingType {
	REDHOUSE(GameSettings.houseRedPolygonXValues,GameSettings.houseRedPolygonYValues,GameSettings.houseRedHeight,GameSettings.houseRedWidth),
	BLUEHOUSE(GameSettings.houseBluePolygonXValues,GameSettings.houseBluePolygonYValues,GameSettings.houseBlueHeight,GameSettings.houseBlueWidth), 
	YELLOWHOUSE(GameSettings.houseYellowPolygonXValues,GameSettings.houseYellowPolygonYValues,GameSettings.houseYellowHeight,GameSettings.houseYellowWidth),
	CHURCH(GameSettings.churchPolygonXValues,GameSettings.churchPolygonYValues,GameSettings.churchHeight,GameSettings.churchWidth);

	Polygon polygon;
	
	BuildingType(double[]x, double[]y, int height, int width){
		
		int[] xC = new int[x.length];
		int[] yC = new int[y.length];
		for(int i=0;i<x.length; i++){
			xC[i] = (int)( x[i] * width);
			yC[i] = (int)( y[i] * -height);
		}
		System.out.println("buildingtype " + this.toString());

		polygon = new Polygon(xC, yC, xC.length);
		System.out.println(polygon.getBounds().getWidth() + " " + polygon.getBounds().getHeight());
	}





}
