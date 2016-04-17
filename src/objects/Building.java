package objects;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import game.GameController;
import game.GameSettings;
import io.ImageLoader;

public class Building extends GameObject implements Damageable, Scoreable {
	double[] xCoord, yCoord;
	int score;
	

	public Building(BuildingType type, Point2D.Double p2d, boolean b) {
		this.position = p2d;
		this.maximumDamage = 4;

		switch (type) {
		case BLUEHOUSE:
			this.color = Color.BLUE;
			this.gameRelevant = b;
			xCoord = GameSettings.houseBluePolygonXValues;
			yCoord = GameSettings.houseBluePolygonYValues;
			width = GameSettings.houseBlueWidth;
			height = GameSettings.houseBlueHeight;
			picture = ImageLoader.getBlueHouseImage(width, height);
			score = 20;
			maximumDamage = GameSettings.houseBlueMaxDamage;


			break;
		case REDHOUSE:
			this.color = Color.RED;
			xCoord = GameSettings.houseRedPolygonXValues;
			yCoord = GameSettings.houseRedPolygonYValues;
			width = GameSettings.houseRedWidth;
			height = GameSettings.houseRedHeight;
			picture = ImageLoader.getRedHouseImage(width, height);
			score = 40;
			maximumDamage = GameSettings.houseRedMaxDamage;

			
			break;
		case YELLOWHOUSE:
			this.color = Color.YELLOW;
			xCoord = GameSettings.houseYellowPolygonXValues;
			yCoord = GameSettings.houseYellowPolygonYValues;
			width = GameSettings.houseYellowWidth;
			height = GameSettings.houseYellowHeight;
			picture = ImageLoader.getYellowHouseImage(width, height);
			score = 35;
			maximumDamage = GameSettings.houseYellowMaxDamage;

			break;
		case CHURCH:
			this.color = Color.GREEN;
			xCoord = GameSettings.churchPolygonXValues;
			yCoord = GameSettings.churchPolygonYValues;
			width = GameSettings.churchWidth;
			height = GameSettings.churchHeight;
			picture = ImageLoader.getChurchImage(width, height);
			score = 100;
			maximumDamage = GameSettings.churchMaxDamage;
			
			break;
		

		}
		initPolygon();
	}
	
	public void initPolygon(){
		int[] xC = new int[xCoord.length];
		int[] yC = new int[yCoord.length];
		for (int i = 0; i < xCoord.length; i++) {
			xC[i] = (int) (((xCoord[i] * width)) + position.getX());
			yC[i] = (int) ((yCoord[i] * -height) + position.getY());
		}
		polygon = new Polygon(xC, yC, xC.length);
	
	}


	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void generateExplosion() {
		Explosion e = new Explosion(Explosion.BIG, this.height, this.width,new Point2D.Double(position.getX(),position.getY()));
		e.setActive(true);
		GameController.getInstance().getGameState().addObject(e);
		
	}

	@Override
	public void setSilent(boolean silent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseDamage(int damage) {
		this.damage+=damage;
		if(damage>=this.maximumDamage){
			generateExplosion();
			this.setActive(false);
			GameController.getInstance().getGameState().removeObject(this);
		}
	}

	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		
		return false;

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
