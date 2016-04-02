package objects;

public class Bomb extends GameObject implements Damaging{

	@Override
	public int getCausingDamage() {
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
