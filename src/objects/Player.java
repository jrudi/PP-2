package objects;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import game.GameSettings;

public class Player extends GameObject implements Damageable{
	
	public  Player(Point2D.Double double1){
		position = double1;
		initPolygon();
	
	}
	
	public void initPolygon(){
		int[] xC = new int[GameSettings.playerPolygonXValues.length];
		int[] yC = new int[GameSettings.playerPolygonYValues.length];

	for (int i = 0; i < GameSettings.playerPolygonXValues.length; i++) {
			xC[i] = (int)(GameSettings.playerPolygonXValues[i]*GameSettings.playerWidth);
			yC[i] = (int)(GameSettings.playerPolygonYValues[i]*-GameSettings.playerHeight);
		}
	polygon = new Polygon(xC, yC, xC.length);
	}
	
	@Override
	public void increaseDamage(int damage) {
		
	}

	@Override
	public void generateExplosion() {
		
	}

	@Override
	public void setSilent(boolean silent) {
		
	}

	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void flipPolygonHorizontally() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean outOfView() {
		// TODO Auto-generated method stub
		return false;
	}

}
