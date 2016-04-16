package objects;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D.Double;

import game.GameController;
public class Bullet extends GameObject implements Damageable, Damaging{

	public Bullet(Double position) {
		this.position = position;
		initPolygon();
		this.color = Color.ORANGE;
		this.dY = 5;
		//System.out.println("new Bullet at: " + this.position.getX());
		this.start();
	}

	private void initPolygon() {
		Polygon zpolygon = new Polygon();
		
		zpolygon.addPoint((int)(0 + position.getX()),(int)( 0+ position.getY()));
		zpolygon.addPoint((int)(0 + position.getX()),(int)( 5+ position.getY()));
		zpolygon.addPoint((int)(5 + position.getX()),(int)( 5+ position.getY()));
		zpolygon.addPoint((int)(5 + position.getX()),(int)( 0+ position.getY()));
		this.polygon = zpolygon;
	}

	@Override
	public int getCausingDamage() {
		// TODO Auto-generated method stub
		return 1;
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
		
		return position.getY()>500;
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
		if(outOfView()){
			this.setActive(false);
			GameController.getInstance().getGameState().getObjectList().remove(this);
		}
	}

	private void travel() {
		this.position.setLocation(position.getX(), position.getY()+dY);
		initPolygon();
	}

}
