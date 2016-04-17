package objects;

import java.awt.Polygon;
import java.awt.geom.Point2D;

import game.GameController;
import io.ImageLoader;

//import java.awt.Image;

public class Explosion extends GameObject{
	public final static int SMALL = 1;
	public final static int BIG = 2;
	protected int count;
		
	public Explosion(int size,int height,int width, Point2D.Double pos){
		int[]xco = {(int)pos.getX(),(int)pos.getX(),(int)pos.getX()+2,(int)pos.getX()+2};
		int[]yco = {(int)pos.getY(),(int)pos.getY()+2,(int)pos.getY()+2,(int)pos.getY()+2};
		this.polygon = new Polygon(xco,yco,4);
		this.height = height;
		this.width = height;
		this.setActive(true);
		switch(size){
		case Explosion.SMALL: 
			this.picture = ImageLoader.getExplosionImage(width, height);
			break;
		case Explosion.BIG:
			this.picture = ImageLoader.getBigExplosionImage(width, height);
			break;
		}
		this.position = pos;
		this.count = 500;
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
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void decreaseCount() {
		if(count>0){
			count--;
		}else 
			this.setActive(false);
			GameController.getInstance().getGameState().removeObject(this);
		
	}

}
