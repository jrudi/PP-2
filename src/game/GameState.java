package game;

import java.awt.geom.Point2D;
import java.util.Vector;

import objects.GameObject;
import objects.Player;


public class GameState {
	
	/** Bestimmt Aktivitaet aller Thread-Objekte. Wenn 'false', ist das Spiel voruebergehend pausiert.
	 * Anmerkung: Im Gegensatz zum 'active'-Attribut der gameObjects selbst, fuehrt gameActive auf 'false' zu keinen Loeschungen. */
	private boolean gameActive;
	/** Liste aller GameObjects, die in jedem Frame vom RepainterThread auf dem GamePanel gezeichnet werden sollen. */
	private Vector<GameObject> objectList = new Vector<GameObject>();
	/** Bisheriger Punktestand, der vom Spieler erzielt wurde. Der Punktestand wird am Ende jedes Levels vom gameManagementThread um die Summe
	 * der Rueckgabewerte aller getScore()-Methoden, die Objekte mit dem Interface Scoreable implementieren, erhoeht. */
	private long score;
	/** Aktuelles Level. Je hoeher das Level, desto weiter fortgeschritten das Spiel und desto kuerzer die Pausenzeiten zwischen der Generierung 
	 * neuer Bomb-Objekte des bombCreatorThreads. */
	private int level;
	private Player player;
	
	public GameState () {
		this.objectList = new Vector<GameObject>();
		this.gameActive = false;
		this.score = 0;
		this.level = 1;
	
	}

	public boolean isGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	synchronized public Vector<GameObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(Vector<GameObject> objectList) {
		this.objectList = objectList;
	}
	
	public void addObject(GameObject go){
		if(go instanceof Player) this.objectList.add(0,go);
		else this.objectList.addElement(go);
	}
	
	public void removeObject(GameObject go){
		this.objectList.remove(go);
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Player getPlayer() {
					
		return (Player) objectList.get(0);
	}
	
}
