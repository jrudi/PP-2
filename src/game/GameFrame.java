package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.*;
import objects.*;
import game.GameSettings;
import io.ImageLoader;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/** Darstellungsflaeche fuer das eigentliche Spielgeschehen */
	private GamePanel gamePanel;

	/** Start- und Restart-Button */
	private JButton startButton;
	/** Pause- und Fortsetzen-Button */
	private JButton pauseButton;
	private JButton switchButton;
	/** Anzeige der verbleibenden Lebenspunkte der Spielfigur */
	private JLabel lifePoints;
	/** Anzeige des aktuellen Levels */
	private JLabel level;
	/** Anzeige der bisher gesammelten Punkte */
	private JLabel score;
	private JLabel threads;
	private Image cloudPicture;

	private RepainterThread repainterThread;
	private GameFrameUpdater gfu;
	private double x=0,y=150,z=350;

	public GameFrame() {

		this.setTitle("GameFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		gamePanel = new GamePanel();
		repainterThread = new RepainterThread();
		gfu = new GameFrameUpdater();
		this.cloudPicture = ImageLoader.getCloudImage(100, 100);
		c.add(gamePanel, BorderLayout.CENTER);
		c.add(createButtonPanel(), BorderLayout.SOUTH);

		this.setSize(GameSettings.gamePanelWidth + GameSettings.frameAdditionalWidth,
				GameSettings.gamePanelHeight + GameSettings.frameAdditionalHeight);

		// Fenster in der Mitte des Bildschirms positionieren
		final Dimension dimension = this.getToolkit().getScreenSize();
		this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2),
				(int) ((dimension.getHeight() - this.getHeight()) / 2));

		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * Erstellung eines Panels mit Start-, Pause-, View-Button und Anzeige von
	 * Level, Leben und Score
	 */
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();
		this.switchButton = new JButton("Polygone");
		this.switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				switch (b.getText()) {
				case "Polygone":
					GameController.getInstance().getGameState().setPolygonOnly(true);
					b.setText("Bilder");
					break;
				case "Bilder":
					GameController.getInstance().getGameState().setPolygonOnly(false);
					b.setText("Polygone");
					break;
				}
			}
		});

		this.startButton = new JButton("Start");
		this.startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().startGame();
				if (!repainterThread.isAlive()) {

					repainterThread.start();
					gfu.start();

				}
			}
		});

		this.pauseButton = new JButton("Anhalten");
		this.pauseButton.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				JButton jButton = (JButton) e.getSource();

				if (jButton.getText().equals("Anhalten")) {
					jButton.setText("Fortsetzen");
					GameController.getInstance().getGameState().setGameActive(false);
					long stoptime = GameController.getInstance().getGameState().getLevelTime()
							- System.currentTimeMillis();
					GameController.getInstance().getGameState().setLevelTime(stoptime);
					// GameController.getInstance().endGame();

				} else if (jButton.getText().equals("Fortsetzen")) {
					jButton.setText("Anhalten");
					GameController.getInstance().getGameState().setGameActive(true);
					long starttime = GameController.getInstance().getGameState().getLevelTime()
							+ System.currentTimeMillis();

					GameController.getInstance().getGameState().setLevelTime(starttime);

				}
			}
		});

		lifePoints = new JLabel();
		lifePoints.setText("Leben: -");
		level = new JLabel();
		level.setText("Level: -");
		score = new JLabel();
		score.setText("Punkte -");
		threads = new JLabel();
		threads.setText("-");

		panel.add(switchButton);
		panel.add(startButton);
		panel.add(pauseButton);
		panel.add(lifePoints);
		panel.add(level);
		panel.add(score);
		panel.add(threads);

		return panel;
	}

	/**
	 * Generiert Frame mit GameOver-Meldung und erreichtem Punktestand
	 * 
	 * @param score
	 *            Endpunktestand nach verlorenem Spiel
	 */
	public void createGameOverFrame(long score) {
		new GameOverFrame(score);
	}

	public int getPanelHeight() {
		return this.gamePanel.getHeight();
	}

	public int getPanelWidth() {
		return this.gamePanel.getWidth();
	}

	public void repaintGamePanel() {
		this.gamePanel.repaint();
	}

	private class GamePanel extends JPanel {

		/** Breite des GamePanels */
		private int width = GameSettings.gamePanelWidth;
		/** Hoehe des GamePanels */
		private int height = GameSettings.gamePanelHeight;

		public GamePanel() {

			setPreferredSize(new Dimension(width, height));

			// Listener zu testzwecken, zeigt mausposition an
			/*
			 * this.addMouseListener(new MouseAdapter() { public void
			 * mouseClicked(MouseEvent e) { System.out.println("X: " + e.getX()
			 * + "Y: " + e.getY()); } });
			 */

			this.setFocusable(true);

			this.addKeyListener(new PlayerMover());

		}

		/**
		 * Zeichnet GamePanel.
		 * 
		 * @param g
		 *            Graphics
		 */
		public void paint(Graphics g) {

			Graphics2D graphics2D = (Graphics2D) g;
			AffineTransform FLIP_X_COORD = new AffineTransform(1, 0, 0, -1, 0, 500);
			graphics2D.setTransform(FLIP_X_COORD);
			graphics2D.setColor(GameSettings.gamePanelBackgroundColor);
			graphics2D.fillRect(0, 0, width, height);
			
			graphics2D.drawImage(cloudPicture,(int) x, 450, 150, -150, null);
			graphics2D.drawImage(cloudPicture,(int)y, 350, 100, -100, null);
			graphics2D.drawImage(cloudPicture,(int)z, 400, 100, -100, null);
			
			x= x>=500 ? -100 : x+0.3;
			y=y>=500?-100:y+0.3;
			z=z>=500?-100:z+0.3;

			
			
			
			
			if (GameController.getInstance().getGameState() != null) {
				@SuppressWarnings("unchecked")
				Vector<GameObject> clone = (Vector<GameObject>) GameController.getInstance().getGameState()
						.getObjectList().clone();
				for (GameObject x : clone) {
					if (x.isActive()) {

						x.draw(graphics2D);
					}
				}

				for (Building b : GameController.getInstance().getGameState().getHouseList()) {
					double percentage = (double) b.getLifePoints();
					percentage /= (double) b.getMaximumDamage()+1;
					LifeBar lB = new LifeBar(new Point2D.Double(b.getPosition().getX(), b.getPosition().getY()),
							percentage, b.getWidth());
					Color c = percentage == 1 ? Color.GREEN
							: percentage >= 0.66 ? Color.YELLOW : percentage >= 0.5 ? Color.ORANGE : Color.RED;
					graphics2D.setColor(c);
					graphics2D.fill(lB.getBar());
					graphics2D.setColor(Color.black);
					graphics2D.draw(lB.getBar());
				}
			}
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}
	}

	private class GameOverFrame extends JFrame {

		/**
		 * Generiert Frame mit GameOver-Meldung und erreichtem Punktestand
		 * 
		 * @param score
		 *            Endpunktestand nach verlorenem Spiel
		 */
		public GameOverFrame(long score) {

			this.setTitle("Game Over!");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			Container c = getContentPane();

			JLabel gameOverLabel = new JLabel("Game Over!");
			gameOverLabel.setFont(new Font("Arial Bold", Font.ITALIC, GameSettings.gameOverLabelSize));
			gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JLabel scoreLabel = new JLabel("Sie haben " + score + " Punkte erreicht.");
			scoreLabel.setFont(new Font("Arial Bold", Font.ITALIC, GameSettings.scoreLabelSize));
			scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameOverFrame.this.dispose();
					GameController.getInstance().restartGame();
				}
			});

			c.add(gameOverLabel, BorderLayout.NORTH);
			c.add(scoreLabel, BorderLayout.CENTER);
			c.add(okButton, BorderLayout.SOUTH);

			this.setSize(GameSettings.gameOverFrameWidth, GameSettings.gameOverFrameHeight);

			final Dimension dimension = this.getToolkit().getScreenSize();
			this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2),
					(int) ((dimension.getHeight() - this.getHeight()) / 2));

			this.setResizable(true);
			this.setVisible(true);

		}

	}

	private class PlayerMover extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			@SuppressWarnings("unchecked")
			Vector<GameObject> oL = (Vector<GameObject>) GameController.getInstance().getGameState().getObjectList()
					.clone();

			if (GameController.getInstance().getGameState().isGameActive()) {
				Player p = new Player(new Point2D.Double(10, 10));
				for (GameObject x : GameController.getInstance().getGameState().getObjectList()) {
					if (x instanceof Player) {
						p = (Player) x;
						oL.remove(x);
					}
				}

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					p.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					p.moveRight();
					break;
				case KeyEvent.VK_UP:
					int x = (int) p.getPosition().getX();
					x = p.isRight() ? x + p.getWidth() / 2 : x - p.getWidth() / 2;
					int y = (int) (p.getPosition().getY() + 10);
					Bullet b = new Bullet(new Point2D.Double(x, y));
					oL.add(b);
					if (!b.isAlive())
						b.start();

				default:
					break;
				}
				oL.add(p);
				GameController.getInstance().getGameState().setObjectList(oL);

			}
		}
	}

	private class RepainterThread extends Thread {
		// TODO Run-Methode soll das GamePanel in kurzen Abstaenden neu
		// zeichen.
		public void run() {

			while (GameController.getInstance().getGameState().isGameActive()) {
				try {
					Thread.sleep(30);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
				repaintGamePanel();
				gamePanel.setFocusable(true);
				gamePanel.requestFocus();
			}
		}

	}

	private class GameFrameUpdater extends Thread {

		public void run() {
			while (GameController.getInstance().getGameState().isGameActive()) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}

				int currentLevel = GameController.getInstance().getGameState().getLevel();
				level.setText("Level: " + currentLevel);

				long currentScore = GameController.getInstance().getGameState().getScore();
				score.setText("Score: " + currentScore);

				int currentLife = GameController.getInstance().getGameState().getLife();
				lifePoints.setText("Leben: " + currentLife);

				threads.setText(Thread.activeCount() + "");

			}
		}
	}

}
