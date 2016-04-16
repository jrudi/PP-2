package objects;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.Vector;

import game.GameController;
import game.GameSettings;

public class Player extends GameObject implements Damageable {
	boolean right;

	public Player(Point2D.Double double1) {
		this.position = double1;
		this.right = true;
		this.color = Color.BLACK;
		this.dX = 10;
		this.dY = 10;
		initPolygon(true);


	}

	public void initPolygon(boolean right) {
		int[] xC = new int[GameSettings.playerPolygonXValues.length];
		int[] yC = new int[GameSettings.playerPolygonYValues.length];
		int z = right ? 1 : -1;
		for (int i = 0; i < GameSettings.playerPolygonXValues.length; i++) {
			xC[i] = (int) (((GameSettings.playerPolygonXValues[i] * GameSettings.playerWidth) * z) + position.getX());
			yC[i] = (int) ((GameSettings.playerPolygonYValues[i] * -GameSettings.playerHeight) + position.getY());
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

		right = !right;
		initPolygon(right);

	}

	@Override
	public boolean outOfView() {
		double x = position.getX();
		return x>450.0 || x<0;	
		}


	public void moveLeft() {
		System.out.print("LINKS: von " + position.getX());

		if (right) {
			flipPolygonHorizontally();

			position.setLocation(position.getX() + polygon.getBounds().getWidth(), position.getY());
		}
		if (position.getX()>60) {

			position = new Point2D.Double(position.getX() - dX, position.getY());
			System.out.println(" nach " + position.getX());
		}else{ System.out.println(" Am linken Rand");
		}
		initPolygon(right);

	}

	public void moveRight() {
		System.out.print("RECHTS: von " + position.getX());
		if (!right) {
			flipPolygonHorizontally();
			position.setLocation(position.getX() - polygon.getBounds().getWidth(), position.getY());
		}
		if (position.getX()<390) {

			position = new Point2D.Double(position.getX() + dX, position.getY());
			System.out.println(" nach " + position.getX());
		}else{
			System.out.println(" Am rechten Rand");
		}
		initPolygon(right);

	}

	public void shoot() {
		Vector<GameObject> go = (Vector<GameObject>) GameController.getInstance().getGameState().getObjectList().clone();
		go.add(new Bullet(new Point2D.Double(position.getX(), position.getY())));
		GameController.getInstance().getGameState().setObjectList(go);
	}

	public void run() {

	}

}
