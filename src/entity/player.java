package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import main.GamePanel;
import main.keyHandler;

public class player extends entity {
	GamePanel gp;
	keyHandler keyH;
	
	public player(GamePanel gp, keyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
	}
	
	public void getPlayerImage() {
		try {

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed) {
			y -= speed;
		}
		if (keyH.downPressed) {
			y += speed;

		}
		if (keyH.leftPressed) {
			x -= speed;

		}
		if (keyH.rightPressed) {
			x += speed;

		}
	}
	
	public void draw(Graphics2D g2) {
		//super.paintComponent(g);

		

		g2.setColor(Color.white);

		g2.fillRect(x, y,  gp.tileSize, gp.tileSize);

		
	}
}
