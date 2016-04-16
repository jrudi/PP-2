package game;

import java.awt.geom.Point2D;
import java.util.Vector;
import objects.*;

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
	private long start = System.currentTimeMillis();

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
		this.gameFrame = new GameFrame();
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
		gameState.addObject(new Building(BuildingType.BLUEHOUSE, new Point2D.Double(0, 0), true));
		gameState.addObject(
				new Building(BuildingType.REDHOUSE, new Point2D.Double(GameSettings.houseBlueWidth, 0), false));
		gameState.addObject(new Building(BuildingType.YELLOWHOUSE,
				new Point2D.Double(GameSettings.houseBlueWidth + GameSettings.houseRedWidth, 0), false));
		gameState.addObject(new Building(BuildingType.CHURCH,
				new Point2D.Double(
						GameSettings.houseBlueWidth + GameSettings.houseRedWidth + GameSettings.houseYellowWidth, 0),
				false));
		gameState.addObject(new Building(BuildingType.BLUEHOUSE, new Point2D.Double(GameSettings.houseBlueWidth
				+ GameSettings.houseRedWidth + GameSettings.houseYellowWidth + GameSettings.churchWidth, 0), true));
	}

	/** Startet Spiel zum ersten Mal. */
	public void startGame() {
		this.gameState = new GameState();
		createTown();
		gameState.setGameActive(true);
		gameState.setLevelTime(System.currentTimeMillis());
		gameManagementThread = new GameManagementThread();
		bombCreatorThread = new BombCreatorThread();
		gameManagementThread.start();
		bombCreatorThread.start();
		gameState.addObject(new Player(new Point2D.Double(250, 150)));

	}

	/** Startet das Spiel neu. */
	public void restartGame() {
		gameManagementThread.interrupt();
		bombCreatorThread.interrupt();
		gameState = new GameState();

		startGame();

	}

	/** Pausiert das Spiel und ruft den GameOver-Frame auf. */
	public void endGame() {
		gameState.setGameActive(false);
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

		public void run() {

			while (gameState.isGameActive()) {

				int wait = GameSettings.bombCreatorStartPause;
				
				switch(gameState.getLevel()){
				case 1: 
					wait = GameSettings.bombCreatorStartPause;
					break;
				case 2:
					wait = 1750;
					break;
				case 3: 
					wait = 1500;
					break;
				case 4: 
					wait = 1250;
					break;
				case 5: 
					wait = 1000;
					break;
				case 6: 
					wait = 750;
					break;
				case 7: 
					wait = 500;
					break;
				default:
					wait = 1500;
					break;
				}

				try {
					Thread.sleep(2*(long) wait);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				int pos = (int) (Math.random() * GameSettings.gamePanelWidth);
				long now = System.currentTimeMillis();

				Bomb bomb = new Bomb(new Point2D.Double(pos, 500));
				System.out.println("bomb dropped lvl " + gameState.getLevel() + " " + wait);
				System.out.println("Diff: " + (now - start));
				System.out.println(java.lang.Thread.activeCount());
				start = now;
				gameState.addObject(bomb);
				bomb.start();
			}
		}
	}

	private class GameManagementThread extends Thread {
		/*
		 * TODO Kollision Schaeden Deaktivierte Objects entfernen GameOver
		 * Levels Scores Summe aller getScore()
		 */
		public void run() {

			while (gameState.isGameActive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				@SuppressWarnings("unchecked")
				Vector<GameObject> copy = (Vector<GameObject>) gameState.getObjectList().clone();
				Vector<Bomb> bombs = new Vector<Bomb>();
				int buildings = 0;

				// Bombenliste erstellen
				for (GameObject x : gameState.getObjectList()) {
					if (x instanceof Bomb) {
						bombs.add((Bomb) x);
						copy.remove(x);
					} else if (x instanceof Building) {
						buildings += ((Building) x).getScore();
					}else if(x instanceof Bullet){
						if(x.outOfView()){
							x.setActive(false);
							copy.remove(x);
						}
					}
				}
				// Auf Kollision enprüfen
				for (Bomb x : bombs) {
					for (GameObject y : copy) {
						x.collide(y);

					}
				}

				copy.addAll(bombs);
				gameState.setObjectList(copy);
				// level
				if (System.currentTimeMillis() - gameState.getLevelTime() > 20000) {
					gameState.setLevel(gameState.getLevel() + 1);
					gameState.setScore(gameState.getScore() + buildings);
					gameState.setLevelTime(System.currentTimeMillis());
					System.out.println("levelhop zu level " + gameState.getLevel());
				}
			}

		}
	}

}