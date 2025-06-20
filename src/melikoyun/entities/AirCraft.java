package melikoyun.entities;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AirCraft extends JLabel {
	private static final String IMAGE = "C:\\Users\\burak\\Desktop\\resim\\game.png";
	private static final int HIZ = 30;

	public AirCraft() {
		ImageIcon image = new ImageIcon(IMAGE);
		Image scaledImage = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledImage));
        setSize(100, 100);
	}
	
	public void moveTo(Point p) {
		setLocation(p);
	}

	public void moveLeft() {
		 setLocation(getLocation().x - HIZ, getLocation().y);
	}

	public void moveRight() {
		 setLocation(getLocation().x + HIZ, getLocation().y);
	}

}
