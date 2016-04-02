package objects;

public class Building extends GameObject implements Damageable, Explosive, Scoreable{

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
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
