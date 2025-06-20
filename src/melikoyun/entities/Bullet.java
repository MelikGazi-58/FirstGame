package melikoyun.entities;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Bullet extends JLabel{
	private static final int HIZ = 15;

	public Bullet(Point point) {
         setOpaque(true);
         setBackground(Color.RED);
         setSize(10, 20);
         setLocation(point);
	}

	public void moveForward() {
        setLocation(getLocation().x, getLocation().y - HIZ);
	}

	public boolean intersects(Rectangle bounds) {
		return getBounds().intersects(bounds);
	}
}
