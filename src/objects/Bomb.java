package objects;

import java.awt.geom.Area;

import game.*;


public class Bomb extends GameObject implements Damaging{
	
	int pos;
	
	public Bomb(int pos) {
		if(pos>=0&&pos<=GameSettings.gamePanelWidth){
			this.pos=pos;
		}
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
			this.position.setLocation(position.getX(), position.getY()+i);
		}
		
	}

}
