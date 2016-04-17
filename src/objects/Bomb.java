package objects;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import game.*;
import io.ImageLoader;

public class Bomb extends GameObject implements Damaging {
	
	public Bomb(Point2D.Double double1) {
		this.position = double1;
		initPolygon();
		this.setActive(true);
		this.height = GameSettings.bombSize;
		this.width = GameSettings.bombSize;
		this.picture = ImageLoader.getBombImage(width, height);
	}

	public void initPolygon() {
		int[] xC = new int[GameSettings.bombPolygonXValues.length];
		int[] yC = new int[GameSettings.bombPolygonYValues.length];

		for (int i = 0; i < GameSettings.bombPolygonXValues.length; i++) {
			xC[i] = (int) ((GameSettings.bombPolygonXValues[i] * GameSettings.bombSize) + position.getX());
			yC[i] = (int) (GameSettings.bombPolygonYValues[i] * (-GameSettings.bombSize) + position.getY());

		}
		this.polygon = new Polygon(xC, yC, xC.length);
	}

	@Override
	public int getCausingDamage() {
		return 1;
	}

	@Override
	public void generateExplosion() {
	
	}

	@Override
	public void setSilent(boolean silent) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a = new Area(this.polygon);
		Area b = new Area(gameObjectToCheck.polygon);
		a.intersect(b);
		if (!a.isEmpty()&&this.isActive()&&gameObjectToCheck.isActive()) {
			this.setActive(false);
			GameController.getInstance().getGameState().getObjectList().remove(this);

			if (gameObjectToCheck instanceof Bullet) {
				
				gameObjectToCheck.setActive(false);
				GameController.getInstance().getGameState().removeObject(gameObjectToCheck);

			} else if (gameObjectToCheck instanceof Building) {
				Building y = (Building) gameObjectToCheck;
				y.increaseDamage(this.getCausingDamage());
				if (y.getLifePoints() == -1) {
					gameObjectToCheck.generateExplosion();
					gameObjectToCheck.setActive(false);
					GameController.getInstance().getGameState().removeObject(gameObjectToCheck);

					if(y.isGameRelevant()){
						GameController.getInstance().endGame();
					}

				}
			} else if (gameObjectToCheck instanceof Player) {
				Player y = (Player) gameObjectToCheck;
				y.increaseDamage(this.getCausingDamage());
				GameController.getInstance().getGameState().setLife(y.getLifePoints());

				if (y.getLifePoints() <= 0) {
					gameObjectToCheck.setActive(false);
					gameObjectToCheck.generateExplosion();
					GameController.getInstance().getGameState().getObjectList().remove(gameObjectToCheck);
					GameController.getInstance().endGame();
					System.out.println(y.getLifePoints());
				}

			}

		}
		return !a.isEmpty();

	}

	@Override
	public void flipPolygonHorizontally() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean outOfView() {
		return (this.position.getY() < 50);

	}

	public void drop(double i) {
		if (!outOfView()) {
			this.position.setLocation(position.getX(), position.getY() - i);
			initPolygon();
		}

	}

	public void run() {
		while (GameController.getInstance().getGameState().isGameActive()&&this.isActive()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			this.drop(2.5);
			if (this.outOfView()) {
				this.setActive(false);
				GameController.getInstance().getGameState().getObjectList().remove(this);
			}
		}
	}

}
