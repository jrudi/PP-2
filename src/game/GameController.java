package game;

import java.awt.geom.Point2D;
import java.util.Vector;

import objects.Bomb;
import objects.Building;
import objects.BuildingType;
import objects.GameObject;
import objects.Player;

public class GameController {

	/** Singleton */
	private static GameController instance;
	/** Haelt Informationen ueber den Zustand des Spiels */
	private GameState gameState;
	/**
	 * Zustaendig fuer die Darstellung und Entgegennahme von
	 * Benutzerinteraktionen
	 */
	private GameFrame gameFrame;
	/** Generiert Bomb-Objekte am oberen Rand des GamePanels */
	private BombCreatorThread bombCreatorThread;
	/**
	 * Enthaelt die eigentliche Logik (Kollisions-, Score- und
	 * Schadensberechnung sowie Levelverwaltung)
	 */
	private GameManagementThread gameManagementThread;

	public static GameController getInstance() {
		if (instance == null) {
			instance = new GameController();
		}
		return instance;
	}

	/**
	 * Initialisiert das Spiel. Legt GameState sowie GameFrame an, fuellt und
	 * zeichnet die objectList mit saemtlichen Objekten, die zum Spielstart
	 * noetig sind.
	 */
	public void initiate() {
		this.gameState = new GameState();
		this.gameFrame = new GameFrame();
		createTown();
		startGame();
	}

	/**
	 * Erstellt vorgegebene Anzahl an Gebaeuden von jedem Buildingtype. Diese
	 * werden lueckenlos am unteren Rand des GamePanels positioniert.
	 * Spielrelevante Gebaeude werden jeweils am Anfang und am Ende platziert.
	 * Dazwischen werden die restlichen Gebauede in zufaelliger Reihenfolge
	 * gesetzt.
	 */
	public void createTown() {
		gameState.addObject(new Building(BuildingType.BLUEHOUSE, new Point2D.Double(0, 0)));
		gameState.addObject(new Building(BuildingType.REDHOUSE, new Point2D.Double(GameSettings.houseBlueWidth, 0)));
		gameState.addObject(new Building(BuildingType.YELLOWHOUSE, new Point2D.Double(GameSettings.houseRedWidth, 0)));
		gameState.addObject(new Building(BuildingType.CHURCH, new Point2D.Double(GameSettings.houseYellowWidth, 0)));
		gameState.addObject(new Building(BuildingType.BLUEHOUSE, new Point2D.Double(GameSettings.churchWidth, 0)));
		gameState.addObject(new Player(new Point2D.Double(0,250)));
	}

	/** Startet Spiel zum ersten Mal. */
	public void startGame() {
		gameManagementThread = new GameManagementThread();
		bombCreatorThread = new BombCreatorThread();
		gameManagementThread.start();
		bombCreatorThread.start();

	}

	/** Startet das Spiel neu. */
	public void restartGame() {
		gameManagementThread.interrupt();
		bombCreatorThread.interrupt();
		startGame();
	}

	/** Pausiert das Spiel und ruft den GameOver-Frame auf. */
	public void endGame() {
		gameManagementThread.interrupt();
		bombCreatorThread.interrupt();
		long score = gameState.getScore();
		gameFrame.createGameOverFrame(score);
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}

	private class BombCreatorThread extends Thread {
		Vector<Bomb> bombList = new Vector<Bomb>();
		public void run() {

			int wait = GameSettings.bombCreatorStartPause
					- ((gameState.getLevel() - 1) * GameSettings.bombCreatorDecreaseNumber);
			wait = wait < GameSettings.bombCreatorMinPause ? GameSettings.bombCreatorMinPause : wait;

			try {
				this.wait(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int pos = (int) (Math.random() * GameSettings.gamePanelWidth);

			Bomb bomb = new Bomb(pos);
			bombList.add(bomb);
			gameState.getObjectList().add(bomb);
			
			for(Bomb b:bombList){
				b.drop(10);
			}
		}

		/*
		 * TODO Die Run-Methode der Klasse soll Bomb-Objekte an einer
		 * zufaelligen Stelle am oberen Rand des Gamepanels in einem
		 * vorgegebenen Zeitintervall abhaengig vom aktuellen Level generieren.
		 * Mit ansteigendem Level sollen die Pausenintervalle in konstanten
		 * Schritten bis zu einer vorgegeben Mindestgrenze abnehmen (siehe
		 * Klasse GameSettings).
		 */
	}

	private class GameManagementThread extends Thread {
		/*
		 * TODO Die Run-Methode soll die zentrale Logik des Spiels enthalten. 1.
		 * In jeder Iteration sollen alle Objekte auf Kollisionen untersucht und
		 * die entstehenden Schaeden zugewiesen werden. GameObjects, die sich
		 * zuvor selbst deaktiviert (active = 'false') haben, werden vom
		 * GameManagementThread aus der gameObjectList entfernt. Zudem soll das
		 * Spiel beendet werden (GameOver), wenn die vorgegebene Anzahl an
		 * spielrelevanten Objekten in der objectList unterschritten wird. 2.
		 * Nach Verstreichen eines vorgegebenen Zeitintervalls (levelTime) soll
		 * zusaetzlich das aktuelle Level hochgezaehlt und der Score um die
		 * Summe aller Rueckgabewerte der getScore()-Methoden, die Objekte mit
		 * dem Interface Scoreable implementieren, erhoeht werden. Das Pausieren
		 * des Spiels soll darauf keinen Einfluss haben.
		 */
		public void run() {

			while (true) {
				for (GameObject x : gameState.getObjectList()) {
					for (GameObject y : gameState.getObjectList()) {
						if (!x.equals(y) && x.collide(y)) {
							System.out.println("halo");
						}
					}
				}
			}

		}
	}

}