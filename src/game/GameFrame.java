package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import io.ImageLoader;
import objects.*;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/** Darstellungsflaeche fuer das eigentliche Spielgeschehen */
	private GamePanel gamePanel;

	/** Start- und Restart-Button */ 
	private JButton startButton;
	/** Pause- und Fortsetzen-Button */
	private JButton pauseButton;

	/** Anzeige der verbleibenden Lebenspunkte der Spielfigur */
	private JLabel lifePoints;
	/** Anzeige des aktuellen Levels */
	private JLabel level;
	/** Anzeige der bisher gesammelten Punkte */
	private JLabel score;
	

	public GameFrame() {

		this.setTitle("GameFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();

		gamePanel = new GamePanel();

		c.add(gamePanel, BorderLayout.CENTER);
		c.add(createButtonPanel(), BorderLayout.SOUTH);

		this.setSize(GameSettings.gamePanelWidth + GameSettings.frameAdditionalWidth, GameSettings.gamePanelHeight + 
				GameSettings.frameAdditionalHeight);

		// Fenster in der Mitte des Bildschirms positionieren
		final Dimension dimension = this.getToolkit().getScreenSize(); 
		this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2), (int) ((dimension.getHeight() - this.getHeight()) / 2));

		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * Erstellung eines Panels mit Start-, Pause-, View-Button und Anzeige von Level, Leben und Score 
	 * */
	public JPanel createButtonPanel() {
		JPanel panel = new JPanel();

		this.startButton = new JButton ("Start");
		this.startButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().startGame();
			}
		});

		this.pauseButton = new JButton("Anhalten");
		this.pauseButton.addActionListener(new ActionListener() { 
			public synchronized void actionPerformed(ActionEvent e) {
				JButton jButton =(JButton)e.getSource();

				if (jButton.getText().equals("Anhalten")) {
					jButton.setText("Fortsetzen");
					GameController.getInstance().getGameState().setGameActive(false);
					GameController.getInstance().endGame();

				}
				else if (jButton.getText().equals("Fortsetzen")) {
					jButton.setText("Anhalten");
					GameController.getInstance().getGameState().setGameActive(true);
				}
			}
		});


		lifePoints = new JLabel();
		lifePoints.setText("Leben: -"); // TODO 
		level = new JLabel();
		level.setText("Level: -"); // TODO
		score = new JLabel();
		score.setText("Punkte -"); // TODO 

		panel.add(startButton);
		panel.add(pauseButton);
		panel.add(lifePoints);
		panel.add(level);
		panel.add(score);

		return panel;
	}

	/**
	 * Generiert Frame mit GameOver-Meldung und erreichtem Punktestand
	 * @param score Endpunktestand nach verlorenem Spiel
	 * */
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

	private class GamePanel extends JPanel{

		/** Breite des GamePanels */
		private int width = GameSettings.gamePanelWidth;
		/** Hoehe des GamePanels */
		private int height = GameSettings.gamePanelHeight;

		public GamePanel(){

			setPreferredSize(new Dimension(width, height));
			
			//Listener zu testzwecken, zeigt mausposition an
			this.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						System.out.println("X: " + e.getX() + "Y: " + e.getY());
					}
				});
			
		}

		/**
		 * Zeichnet GamePanel.
		 * @param g Graphics
		 * */
		public void paint(Graphics g){

			Graphics2D graphics2D = (Graphics2D)g;
			AffineTransform FLIP_X_COORD = new AffineTransform(1, 0, 0, -1, 0, 500);
			graphics2D.setTransform(FLIP_X_COORD);
			graphics2D.setColor(GameSettings.gamePanelBackgroundColor);
			graphics2D.fillRect(0, 0, width, height);
			
			for(GameObject x:GameController.getInstance().getGameState().getObjectList()){
				if(x.isActive())
					x.draw(graphics2D);
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
	 * @param score Endpunktestand nach verlorenem Spiel
	 * */
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
			}
		});


		c.add(gameOverLabel, BorderLayout.NORTH);
		c.add(scoreLabel, BorderLayout.CENTER);
		c.add(okButton, BorderLayout.SOUTH);

		this.setSize(GameSettings.gameOverFrameWidth, GameSettings.gameOverFrameHeight);

		final Dimension dimension = this.getToolkit().getScreenSize(); 
		this.setLocation((int) ((dimension.getWidth() - this.getWidth()) / 2), (int) ((dimension.getHeight() - this.getHeight()) / 2));

		this.setResizable(true);
		this.setVisible(true);

	}

}

private class RepainterThread extends Thread {
	// TODO Run-Methode soll das GamePanel in kurzen Abstaenden neu zeichen.
	public void run(){
		while(GameController.getInstance().getGameState().isGameActive()){
			
		}
	}


}


private class GameFrameUpdater extends Thread  {

	public void run(){
		try {
			Thread.sleep(50);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}


		int currentLevel = GameController.getInstance().getGameState().getLevel();
		level.setText("Level: "+ currentLevel);

		long currentScore = GameController.getInstance().getGameState().getScore();
		score.setText("Score: "+ currentScore);

		//int currentLife =  GameController.getInstance().getGameState().getLife();
		//lifePoints.setText("Leben: "+ currentLife);

	}
}

}
