package objects;

import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

import game.*;


public class Bomb extends GameObject implements Damaging{
	
	
	public Bomb(Point2D.Double double1) {
		this.position = double1;
		initPolygon();
		this.setActive(true);
	}

	public void initPolygon(){
		int[] xC = new int[GameSettings.bombPolygonXValues.length];
		int[] yC = new int[GameSettings.bombPolygonYValues.length];

	for (int i = 0; i < GameSettings.bombPolygonXValues.length; i++) {
			xC[i] = (int)((GameSettings.bombPolygonXValues[i]*GameSettings.bombSize)+position.getX());
			yC[i] = (int)(GameSettings.bombPolygonYValues[i]*(-GameSettings.bombSize)+position.getY());
		 
	}
	this.polygon = new Polygon(xC,yC,xC.length);
	}
	@Override
	public int getCausingDamage() {
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
	public boolean collide(GameObject gameObjectToCheck) {
		Area a = new Area(this.polygon);
		Area b = new Area(gameObjectToCheck.polygon);
		a.intersect(b);
		return !a.isEmpty();
	}

	@Override
	public void flipPolygonHorizontally() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean outOfView() {
		return(this.position.getX()>GameSettings.gamePanelWidth)||
				(this.position.getY()>GameSettings.gamePanelHeight);
		
	}

	public void drop(int i) {
		if(!outOfView()){
			this.position.setLocation(position.getX(), position.getY()-i);
			initPolygon();
		}
		
	}
	
	public void run(){
		while(this.isActive()){
			try{
				Thread.sleep(20);
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
			this.drop(1);
		}
	}

}
