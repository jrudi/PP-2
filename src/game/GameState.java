package game;

import java.util.Vector;

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
	
	public GameState () {
		this.objectList = new Vector<GameObject>();
		this.gameActive = false;
		this.score = 0;
		this.level = 1;
	}
	
}
