package melikoyun.entities;

import java.awt.Color;

import javax.swing.JPanel;

public class Target extends JPanel{

	private static final int HIZ = 10;

	public Target() {
		setOpaque(true);
		setBackground(Color.GREEN);
		setBounds((int) (Math.random() * 1900), 0, 60, 60);
	}

	public void moveForward() {
        setLocation(getLocation().x, getLocation().y + HIZ);
	}
}
