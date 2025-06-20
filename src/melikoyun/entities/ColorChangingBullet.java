package melikoyun.entities;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

public class ColorChangingBullet extends Bullet{
	
    private Random random = new Random();

	
	public ColorChangingBullet(Point point) {
		super(point);
	}

	@Override
	public void moveForward() {
		super.moveForward();

		Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		setBackground(randomColor);
	}
}
