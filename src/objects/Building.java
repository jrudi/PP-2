package objects;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import game.GameSettings;

public class Building extends GameObject implements Damageable, Scoreable {
	double[] xCoord, yCoord;
	int houseW, houseH;
	

	public Building(BuildingType type, Point2D.Double p2d) {
		this.position = p2d;

		switch (type) {
		case BLUEHOUSE:
			this.color = Color.BLUE;
			xCoord = GameSettings.houseBluePolygonXValues;
			yCoord = GameSettings.houseBluePolygonYValues;
			houseW = GameSettings.houseBlueWidth;
			houseH = GameSettings.houseBlueHeight;

			break;
		case REDHOUSE:
			this.color = Color.RED;
			xCoord = GameSettings.houseRedPolygonXValues;
			yCoord = GameSettings.houseRedPolygonYValues;
			houseW = GameSettings.houseRedWidth;
			houseH = GameSettings.houseRedHeight;
			
			break;
		case YELLOWHOUSE:
			this.color = Color.YELLOW;
			xCoord = GameSettings.houseYellowPolygonXValues;
			yCoord = GameSettings.houseYellowPolygonYValues;
			houseW = GameSettings.houseYellowWidth;
			houseH = GameSettings.houseYellowHeight;
			break;
		case CHURCH:
			this.color = Color.GREEN;
			xCoord = GameSettings.churchPolygonXValues;
			yCoord = GameSettings.churchPolygonYValues;
			houseW = GameSettings.churchWidth;
			houseH = GameSettings.churchHeight;
			
			break;
		

		}
		initPolygon();
	}
	
	public void initPolygon(){
		int[] xC = new int[xCoord.length];
		int[] yC = new int[yCoord.length];
		for (int i = 0; i < xCoord.length; i++) {
			xC[i] = (int) (((xCoord[i] * houseW)) + position.getX());
			yC[i] = (int) ((yCoord[i] * -houseH) + position.getY());
		}
		polygon = new Polygon(xC, yC, xC.length);
	
	}


	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void generateExplosion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSilent(boolean silent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseDamage(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a = new Area(this.polygon);
		Area b = new Area(gameObjectToCheck.getPolygon());
		return a.isEmpty();

	}

	@Override
	public void flipPolygonHorizontally() {
		 AffineTransform flip =	new AffineTransform(1, 0, 0, -1, 0, 500);
		 flip.createTransformedShape(polygon);

	}

	@Override
	public boolean outOfView() {
		return false;
	}
	
	public void run(){
		
	}

}
