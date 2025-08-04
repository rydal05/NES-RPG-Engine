package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.parentObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final public int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768x576 px
	public final int screenHeight = tileSize * maxScreenRow;

	// world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	int fps = 60;

	TileManager tileM = new TileManager(this);
	keyHandler keyH = new keyHandler();
	Sound sound = new Sound();
	Thread gameThread;
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	
	
	public Player Player = new Player(this, keyH);
	public parentObject obj[] = new parentObject[10];

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObject();
		
		playMusic(3);
	}

	public void StartGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				// 1: update
				update();
				// 2: render
				repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {

				drawCount = 0;
				timer = 0;
			}
			
		}
	}

	public void update() {
		Player.update();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		//tile drawing
		tileM.draw(g2);
		//object
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//player drawing
		Player.draw(g2);

		g2.dispose();
	}
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
}