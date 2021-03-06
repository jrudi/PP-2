package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import game.GameController;

public abstract class GameObject extends Thread implements Explosive {

	/** Beendet bei 'false' die Aktivitaeten des Objekts, gibt es zur Entfernung aus der objectList durch den gameManagementThread frei  
	 * und fuehrt damit zur Loeschung durch den Garbage Collector. */
	protected boolean active;
	/** True, falls Existenz des Objekts relevant fuer die Weiterfuehrung des Spiels. */
	protected boolean gameRelevant;
	/** Polygon des Objekts. Wird zur Darstellung und Kollisionsermittlung verwendet. Beinhaltet Positionsinformationen (Polygon jeweils um 
	 * aktuelle Position verschoben). Falls null, sind keine Kollisionen moeglich. */
	protected Polygon polygon;
	/** Farbe des Polygons */
	protected Color color;
	/** Position des linken, unteren Eckpunkts des Objekts auf dem GamePanel */
	protected Point2D.Double position;
	/** Breite des Objekts */
	protected int width;
	/** Hoehe des Objekts */
	protected int height;
	/** Maximaler Schaden */
	protected int maximumDamage;
	/** Aktueller Schaden */
	protected int damage;
	/** Threadpause nach jeder Iteration */
	protected int pause;
	/** Positionsverschiebung in X- und Y-Richtung bei jeder Bewegung */
	protected double dX, dY;
	
	protected Image picture;
	
	
	public GameObject () {
		this.damage = 0;
		this.active = true;
		this.gameRelevant = false;
	}
	
	/**
	 * Ermittelt Kollision zweier GameObjects
	 * @param gameObjectToCheck GameObject
	 * @return boolean Kollision
	 * */
	public abstract boolean collide (GameObject gameObjectToCheck); 
	
	/**
	 * Spiegelt Polygon horizontal.
	 * */
	public abstract void flipPolygonHorizontally (); 
	
	/**
	 * Ermittelt, ob das gegebene Objekt noch im sichtbaren Bereich des GamePanels liegt.
	 * @return boolean true, falls Objekt außerhalb des sichtbaren Bereichs
	 * */
	public abstract  boolean outOfView();
	
	/**
	 * Zeichnet Polygon des Objekts auf dem GamePanel (bzw. dessen Graphics Context)
	 * @param graphics2D Graphics2D
	 * */
	public void draw(Graphics2D graphics2D) {
		graphics2D.setColor(this.color);
		graphics2D.fill(this.polygon);
		graphics2D.setColor(Color.black);
		graphics2D.draw(this.polygon);
		
		if(!GameController.getInstance().getGameState().getPolygonOnly()){
			if(this instanceof Player &&!((Player)this).isRight()){
				graphics2D.drawImage(picture, (int)position.getX()-width, (int)position.getY()+height, width, -height, null);
			}else{
				graphics2D.drawImage(picture, (int)position.getX(), (int)position.getY()+height, width, -height, null);
				if(this instanceof Explosion){
					Explosion e = (Explosion)this;
					System.out.println("Explosion gefunden bei" + e.position + " Gr��e: " + height + " x " + width +"  " + e.count);
					//e.decreaseCount();
					//if(e.getCount()<0){
					//	GameController.getInstance().getGameState().removeObject(e);
				//	}
					//if(((Explosion) this).count<1){
						//System.out.println(((Explosion)this).count + " kleiner 0");
						//this.setActive(false);
						//GameController.getInstance().getGameState().removeObject(this);
					//}

					
				}
			}
		}
	}
	
	/**
	 * Setzt Aktivitaet des Objekts. Einmal deaktivierte Objekte werden vom gameManagementThread aus der objectList entfernt und durch den 
	 * Garbage Collector geloescht.
	 * @param active boolean
	 * */
	synchronized public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public Polygon getPolygon() {
		return this.polygon;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Point2D.Double getPosition() {
		return this.position;
	}
	
	public int getLifePoints() {
		return (this.maximumDamage - this.damage + 1);
	}
	
	public int getMaximumDamage() {
		return this.maximumDamage;
	}
	
	public boolean isGameRelevant() {
		return this.gameRelevant;
	}
	
	
		
}
