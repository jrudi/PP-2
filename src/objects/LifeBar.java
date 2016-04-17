package objects;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class LifeBar {
	private Rectangle bar;
	
	public LifeBar(Point2D.Double pos, double percentage, int width){
		
		setBar(new Rectangle());
		getBar().setBounds((int)pos.getX(), (int)pos.getY(), (int)(width*percentage), 10);
		
	}

	public Rectangle getBar() {
		return bar;
	}

	public void setBar(Rectangle bar) {
		this.bar = bar;
	}
	
}
