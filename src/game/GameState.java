package game;

import java.util.Vector;

import objects.Building;
import objects.GameObject;


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
	private int lives;
	private boolean polygonOnly;
	private long levelTime;
	private Vector<Building> houseList;
	
	public GameState () {
		this.objectList = new Vector<GameObject>();
		this.houseList = new Vector<Building>();

		this.gameActive = false;
		this.score = 0;
		this.level = 1;
		this.lives=5;
	
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
		this.objectList.addElement(go);
	}
	
	public void removeObject(GameObject go){
		this.objectList.remove(go);
		if(go instanceof Building){
			this.houseList.remove(go);
		}
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

	public void setPolygonOnly(boolean b) {
		this.polygonOnly = b;
		
	}
	public boolean getPolygonOnly(){
		return polygonOnly;
	}

	public long getLevelTime() {
		return levelTime;
	}

	public void setLevelTime(long levelTime) {
		this.levelTime = levelTime;
	}

	public void setLife(int lifePoints) {
		lives = lifePoints;
	}

	public int getLife() {
		
		return lives;
	}

	public Vector<Building> getHouseList() {
		return houseList;
	}

	public void setHouseList(Vector<Building> houseList) {
		this.houseList = houseList;
	}
	
}
