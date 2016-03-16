package game;

import java.awt.Color;

/** Klasse, die alle Einstellungsmoeglichkeiten und weitere Konstanten beinhaltet. */

public class GameSettings {
			
	// bombCreator
	public static final int bombCreatorStartPause = 2000; // Pause, zwischen Generierung einzelner Bomben zu Beginn/Level 1 (in ms)
	public static final int bombCreatorDecreaseNumber = 250; // Wert, um den die Pausenzeit pro Levelanstieg reduziert wird (in ms)
	public static final int bombCreatorMinPause = 500; // Pausenzeitwert, der nicht unterschritten werden darf (in ms)
	public static final int bombSize = 25; // Hoehe und Breite der generierten Bomben
	
	
	// player
	public static final int playerWidth = 60; // Breite der Spielfigur
	public static final int playerHeight = 30; // Hoehe der Spielfigur
	public static final int playerMaximumDamage = 4; // Maximaler Schaden, den die Spielfigur einstecken kann
	public static final double playerDX = 10; // X-Wert, um die Position der Spielfigur bei Bewegung pro Nutzereingabe verschoben wird  
	
	
	// player bullet
	public static final int bulletSize = 5; // Hoehe und Breite eines Bullet-Objekts
	
	
	// buildings - Maximalschaden, Breite, Hoehe und Anzahl
	
	public static final int houseBlueMaxDamage = 2;
	public static final int houseYellowMaxDamage = 2;
	public static final int houseRedMaxDamage = 1;
	public static final int churchMaxDamage = 3;
	
	public static final int houseBlueWidth = 90;
	public static final int houseYellowWidth = 85;
	public static final int houseRedWidth = 85;
	public static final int churchWidth = 100;
	
	public static final int houseBlueHeight = 80;
	public static final int houseYellowHeight = 80;
	public static final int houseRedHeight = 80;
	public static final int churchHeight = 140;
	
	public static final int houseBlueNumber = 2;
	public static final int houseYellowNumber = 1;
	public static final int houseRedNumber = 1;
	public static final int churchNumber = 1;
	
	public static final int relevantBuildingsNumber = 2; /* Anzahl spielrelevanter Gebaeude, die jeweils ganz links bzw. ganz rechts platziert 
	werden, aus der Menge aller Gebauede */
	
	
	// GameOverFrame (vorgegeben)
	public static final int gameOverFrameWidth = 310;
	public static final int gameOverFrameHeight = 170;
	public static final int gameOverLabelSize = 45;
	public static final int scoreLabelSize = 15;

	
	// gameManager
	public static final int levelPause = 20000; // Dauer eines Levels (in ms)
	public static final int numberRelevantGameObjects = relevantBuildingsNumber + 1; /* Anzahl spielrelevanter Objekte (relevante Gebauede + 
	Player). Wenn eines der Objekte geloescht wird, gilt das Spiel als verloren. */
	
	
	// GameFrame
	public static final int frameAdditionalWidth = 6; // zusaetzliche Breite gegenueber dem GamePanel
	public static final int frameAdditionalHeight = 77; // zusaetzliche Hoehe gegenueber dem GamePanel
	
	
	// GamePanel
	public static final int gamePanelWidth = houseBlueWidth * houseBlueNumber + houseYellowWidth * houseYellowNumber + houseRedWidth * houseRedNumber
			+ churchWidth * churchNumber; // Breite in Abhaengigkeit der vorhandenen Gebauede
	public static final int gamePanelHeight = 500; // Hoehe des GamePanel
	public static final Color gamePanelBackgroundColor = new Color(0,204,255); // Hintergrundfarbe des GamePanels
	
	
	/* Polygone: X- und Y-Werte von Polygonen, die zu den bereitgestellten PNG-Bildern passen. Um die Polygone auf die gewollte Groeße zu 
	 * skalieren, muessen die X-Werte mit der Breite und die Y-Werte mit der gewuenschten Hoehe multipliziert werden. Es gilt dabei, dass 
	 * Seitenverhaeltnis der Bilddateien zu beachten. */
	public static final double[] playerPolygonXValues = {0.0, 0.03334, 0.53334, 0.5667, 0.7334, 0.7167, 0.7334, 0.7334, 0.8, 0.8334, 0.8167, 
		0.8, 0.8167, 0.8334, 1.0, 1.0, 0.9667, 0.7667, 0.7834, 0.5333, 0.6167, 0.1834, 0.1334, 0.0665, 0.05};
	public static final double[] playerPolygonYValues = {-0.6, -0.5666, -0.35, -0.3064, -0.3033, -0.2334, -0.0667, -0.0334, -0.0667, -0.1667, 
		-0.2, -0.2667, -0.1667, -0.3667, -0.5, -0.6, -0.6634, -0.6667, -0.9434, -0.94, -0.6667, -0.6334, -0.8667, -0.8934, -0.6};
	
	public static final double[] houseBluePolygonXValues = {0.088, 0.088, 0.0111, 0.1, 0.1556, 0.15556, 0.277, 0.277, 0.3222, 0.3222, 0.267, 
		0.3333, 0.6766, 0.7211, 0.6666, 0.6666, 0.7333, 0.7333, 0.844, 0.844, 0.8988, 0.9977, 0.9115, 0.9115};
	public static final double[] houseBluePolygonYValues = {0.0, -0.4375, -0.45, -0.7375, -0.7375, -0.8, -0.8, -0.75, -0.75, -0.83, -0.83,
		-1.0, -1.0, -0.83, -0.825, -0.7375, -0.7375, -0.8, -0.8, -0.7375, -0.7375, -0.4675, -0.4625, 0.0};
	
	public static final double[] houseYellowPolygonXValues = {0.0585, 0.0585, 0.0, 0.0588, 0.1594, 0.1594, 0.2788, 0.2788, 0.7258, 0.7258, 0.8435, 
		0.8435, 0.9258, 1.0, 0.9511, 0.9511};
	public static final double[] houseYellowPolygonYValues = {0.0, -0.65, -0.65, -0.9125, -0.9125, -1.0, -1.0, -0.9125, -0.9125, -1.0, -1.0, -0.9125,
		-0.9125, -0.65, -0.65, 0.0};
	
	public static final double[] houseRedPolygonXValues = {0.0941, 0.0941, 0.0235, 0.0, 0.5058, 0.9885, 0.9885, 0.9059, 0.9059};
	public static final double[] houseRedPolygonYValues = {0.0, -0.625, -0.6, -0.675, -0.9625, -0.6625, -0.6125, -0.625, 0.0};
	
	public static final double[] churchPolygonXValues = {0.0, 0.0, 0.72, 0.72, 0.68, 0.86, 1.0, 1.0, 1.0};
	public static final double[] churchPolygonYValues = {0.0, -0.5438, -0.5438, -0.7714, -0.7714, -0.9857, -0.8142, -0.7714, 0.0};
	
	public static final double[] bombPolygonXValues = {0.25, 0.65, 0.8, 0.8, 0.7, 0.55, 0.45, 0.35, 0.25, 0.1, 0.0, 0.05};
	public static final double[] bombPolygonYValues = {0.0, 0.0, -0.15, -0.4, -0.6, -0.65, -0.85, -0.85, -0.65, -0.6, -0.4, -0.15};

}