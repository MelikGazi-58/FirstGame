package melikoyun;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import melikoyun.entities.AirCraft;
import melikoyun.entities.Bullet;
import melikoyun.entities.ColorChangingBullet;
import melikoyun.entities.Target;

import javax.imageio.ImageIO;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game extends JFrame {
	private static final String BACKGROUNG_IMAGE = "C:\\Users\\burak\\Desktop\\resim\\images2.png";
	private static final int FPS = 50;
	private static final int TARGET_CREATION_PERIOD_SEC = 2000;

	static int puan = 0;
	protected static final int HIZ = 50;
	private static AirCraft aircraft;
	private ConcurrentLinkedQueue<Bullet> bullets = new ConcurrentLinkedQueue();
	private ConcurrentLinkedQueue<Target> targets = new ConcurrentLinkedQueue();
	private  JPanel panel;
	private Timer gameTimer;
	private long tick;

	public Game() {
		setTitle("Melik Oyunu");
		setSize(2000, 1800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();

		try {
			BufferedImage backgroundImage = ImageIO.read(new File(BACKGROUNG_IMAGE));
			panel = new JPanel() {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
				}
			};

			panel.setLayout(null);
			setContentPane(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}


		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_LEFT) {
					aircraft.moveLeft();
				} else if (key == KeyEvent.VK_RIGHT) {
					aircraft.moveRight();
				} else if (key == KeyEvent.VK_SPACE) {
					fire();
				}
			}
		});

		gameTimer = new Timer(FPS, e -> gameLoop());
	}


	private void gameLoop() {
		for (Bullet bullet : bullets) {
			bullet.moveForward();
		}
		
		for (Target target : targets) {
			target.moveForward();
		}
		
		checkIntersection();

		if(tick % (TARGET_CREATION_PERIOD_SEC / FPS) == 0) {
			createTarget();
		}

		panel.revalidate();
		panel.repaint();
		tick++;
	}


	private void checkIntersection() {
		for (Bullet bullet : bullets) {
			for (Target target : targets) {
				if(bullet.intersects(target.getBounds())) {
					panel.remove(target);
					panel.remove(bullet);
					bullets.remove(target);
					targets.remove(bullet);
					puan++;
				}
			}
			
			if (bullet.getY() < 0) {
				panel.remove(bullet);
				bullets.remove(bullet);
			}
		}
	}


	private void createTarget() {
		Target target = new Target(); 
		panel.add(target);
		targets.add(target);
	}


	private void fire() {
		
		Point pos = aircraft.getLocation();
		pos.x += aircraft.getWidth()/2;
		
		Bullet bullet = new ColorChangingBullet(pos);
		bullets.add(bullet);
		panel.add(bullet);
	}

	public void start() {
		gameTimer.start();

		aircraft = new  AirCraft();
		aircraft.moveTo(new Point(500, 500));
		panel.add(aircraft);
	}
}
