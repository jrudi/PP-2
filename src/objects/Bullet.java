package objects;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D.Double;

import game.GameController;

public class Bullet extends GameObject implements Damageable, Damaging{

	public Bullet(Double position) {
		this.position = position;
		initPolygon();
		this.color = Color.DARK_GRAY;
		this.dY = 5;
		this.start();
		System.out.println("new Bullet at: " + this.position.getX());
		GameController.getInstance().getGameState().addObject(this);
	}

	private void initPolygon() {
		polygon = new Polygon();
		polygon.addPoint(0, 0);
		polygon.addPoint(5, 0);
		polygon.addPoint(5, 5);
		polygon.addPoint(0, 5);
	}

	@Override
	public int getCausingDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseDamage(int damage) {
		// TODO Auto-generated method stub
		
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
	public void run(){
		while(this.isActive()){
		try{
			sleep(50);
			
		}catch (InterruptedException ie){
			ie.printStackTrace();
		}
		this.travel();
		}
	}

	private void travel() {
		this.position.setLocation(position.getX(), position.getY()+dY);
	}

}
