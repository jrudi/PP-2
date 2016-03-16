package game;


public class GameController {
	
	/** Singleton */
	private static GameController instance;
	/** Haelt Informationen ueber den Zustand des Spiels */
	private GameState gameState;
	/** Zustaendig fuer die Darstellung und Entgegennahme von Benutzerinteraktionen */
	private GameFrame gameFrame;
	/** Generiert Bomb-Objekte am oberen Rand des GamePanels */
	private BombCreatorThread bombCreatorThread;
	/** Enthaelt die eigentliche Logik (Kollisions-, Score- und Schadensberechnung sowie Levelverwaltung) */
	private GameManagementThread gameManagementThread;
	
	public static GameController getInstance() {
		if(instance == null) {
			instance = new GameController();
		}
		return instance;
	}
	
	/**
	 * Initialisiert das Spiel. Legt GameState sowie GameFrame an, fuellt und zeichnet die objectList mit saemtlichen Objekten, die zum 
	 * Spielstart noetig sind.
	 * */
	public void initiate () {
		this.gameState = new GameState();
		this.gameFrame = new GameFrame();
		// TODO
	}
	
	/**
	 * Erstellt vorgegebene Anzahl an Gebaeuden von jedem Buildingtype. Diese werden lueckenlos am unteren Rand des GamePanels positioniert. 
	 * Spielrelevante Gebaeude werden jeweils am Anfang und am Ende platziert. Dazwischen werden die restlichen Gebauede in zufaelliger Reihenfolge 
	 * gesetzt.    
	 * */
	public void createTown() {
		// TODO 
	}	
	
	/** Startet Spiel zum ersten Mal. */
	public void startGame() {
		// TODO 
	}
	
	/** Startet das Spiel neu. */
	public void restartGame() {
		// TODO
	}
	
	/** Pausiert das Spiel und ruft den GameOver-Frame auf. */
	public void endGame() {
		// TODO
	}
	
	public GameState getGameState () {
		return this.gameState;
	}
	
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}
	
	private class BombCreatorThread extends Thread {
		/* TODO Die Run-Methode der Klasse soll Bomb-Objekte an einer zufaelligen Stelle am oberen Rand des Gamepanels in einem vorgegebenen 
		 * Zeitintervall abhaengig vom aktuellen Level generieren. Mit ansteigendem Level sollen die Pausenintervalle in konstanten Schritten 
		 * bis zu einer vorgegeben Mindestgrenze abnehmen (siehe Klasse GameSettings).
		 * */
	}
	
	private class GameManagementThread extends Thread {
		/* TODO Die Run-Methode soll die zentrale Logik des Spiels enthalten. 
		 * 1. In jeder Iteration sollen alle Objekte auf Kollisionen untersucht und die entstehenden Schaeden zugewiesen werden. 
		 * GameObjects, die sich zuvor selbst deaktiviert (active = 'false') haben, werden vom GameManagementThread aus der gameObjectList entfernt.
		 * Zudem soll das Spiel beendet werden (GameOver), wenn die vorgegebene Anzahl an spielrelevanten Objekten in der objectList unterschritten 
		 * wird.  
		 * 2. Nach Verstreichen eines vorgegebenen Zeitintervalls (levelTime) soll zusaetzlich das aktuelle Level hochgezaehlt und der Score um die 
		 * Summe aller Rueckgabewerte der getScore()-Methoden, die Objekte mit dem Interface Scoreable implementieren, erhoeht werden. Das Pausieren 
		 * des Spiels soll darauf keinen Einfluss haben.   
		 * */
	}
	
}