package objects;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

public class Building extends GameObject implements Damageable, Scoreable {


	public Building(BuildingType type, Point2D.Double p2d) {
		this.polygon = type.polygon;
		this.position = p2d;
		switch (type) {
		case BLUEHOUSE:
			this.color = Color.BLUE;
			break;
		case REDHOUSE:
			this.color = Color.RED;
			break;
		case YELLOWHOUSE:
			this.color = Color.YELLOW;
			break;
		case CHURCH:
			this.color = Color.GREEN;
			break;
		

		}
	}
	
	/*private void setChurch() {
		
	}

	private void setYellowHouse() {

	}

	private void setRedHouse() {

	}

	private void setBlueHouse() {

	}*/

	@Override
	public int getScore() {
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
		Area a = new Area(this.polygon);
		Area b = new Area(gameObjectToCheck.getPolygon());
		return a.isEmpty();

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

}
