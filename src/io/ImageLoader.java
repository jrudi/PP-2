package io;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasse zum Laden und Skalieren von Bildern
 * */

public class ImageLoader {

	private static final int SCALE_SMOOTH = 0;
	private static Image playerLookingToRightImage;
	private static Image playerLookingToLeftImage;
	private static Image bombImage;
	private static Image bigExplosionImage;
	private static Image explosionImage;
	//sprivate static Image cloudImage;
	private static Image blueHouseImage;
	private static Image redHouseImage;
	private static Image yellowHouseImage;
	private static Image churchImage;
	//private static Image fireImage;
	
	
	public static Image getPlayerLookingToRightImage (int width, int height) {
		if(playerLookingToRightImage == null) {
			try {
				playerLookingToRightImage = ImageIO.read(new File("resources/img/planeLookingToRight.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerLookingToRightImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getPlayerLookingToLeftImage (int width, int height) {
		if(playerLookingToLeftImage == null) {
			try {
				playerLookingToLeftImage = ImageIO.read(new File("resources/img/planeLookingToLeft.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerLookingToLeftImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getBombImage (int width, int height) {
		if(bombImage == null) {
			try {
				bombImage = ImageIO.read(new File("resources/img/bomb.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bombImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getBigExplosionImage (int width, int height) {
		if(bigExplosionImage == null) {
			try {
				bigExplosionImage = ImageIO.read(new File("resources/img/bigExplosion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bigExplosionImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getExplosionImage (int width, int height) {
		if(explosionImage == null) {
			try {
				explosionImage = ImageIO.read(new File("resources/img/explosion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return explosionImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getBlueHouseImage (int width, int height) {
		if(blueHouseImage == null) {
			try {
				blueHouseImage = ImageIO.read(new File("resources/img/blueHouse.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return blueHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getRedHouseImage (int width, int height) {
		if(redHouseImage == null) {
			try {
				redHouseImage = ImageIO.read(new File("resources/img/redHouse.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return redHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getYellowHouseImage (int width, int height) {
		if(yellowHouseImage == null) {
			try {
				yellowHouseImage = ImageIO.read(new File("resources/img/yellowHouse.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return yellowHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	public static Image getChurchImage (int width, int height) {
		if(churchImage == null) {
			try {
				churchImage = ImageIO.read(new File("resources/img/church.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return churchImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

}
