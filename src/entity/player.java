package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.keyHandler;

public class Player extends Entity {
	GamePanel gp;
	keyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, keyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle(8,16,(int)(gp.tileSize/1.5),(int)(gp.tileSize/1.5));

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = (int) (gp.scale / .75);
		direction = "down";
	}

	public void getPlayerImage() {
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true
				|| keyH.leftPressed == true) {
			if (keyH.upPressed) {
				direction = "up";
				worldY -= speed;
			}
			if (keyH.downPressed) {
				direction = "down";
				worldY += speed;
			}
			if (keyH.leftPressed) {
				direction = "left";
				worldX -= speed;
			}
			if (keyH.rightPressed) {
				direction = "right";
				worldX += speed;
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 2;
				}
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
			spriteCounter = 11;
		}
	}

	public void draw(Graphics2D g2) {
		// super.paintComponent(g);

		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;

		switch (direction) {
		case "up": {
			if (spriteNum == 1) {
				image = up1;
			} else if (spriteNum == 2) {
				image = up2;
			} else if (spriteNum == 3) {
				image = up3;
			}
			break;
		}
		case "down": {
			if (spriteNum == 1) {
				image = down1;
			} else if (spriteNum == 2) {
				image = down2;
			} else if (spriteNum == 3) {
				image = down3;
			}
			break;
		}
		case "left": {
			if (spriteNum == 1) {
				image = left1;
			} else if (spriteNum == 2) {
				image = left2;
			} else if (spriteNum == 3) {
				image = left3;
			}
			break;
		}
		case "right": {
			if (spriteNum == 1) {
				image = right1;
			} else if (spriteNum == 2) {
				image = right2;
			} else if (spriteNum == 3) {
				image = right3;
			}
			break;
		}
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
