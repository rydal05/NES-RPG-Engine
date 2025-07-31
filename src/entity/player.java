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

		solidArea = new Rectangle(8, 16, 32, 32);

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = (int) (gp.scale / .75);;
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
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png")); //TODO: Migrate to sourcing src/dst from spritesheets instead of pngs per sprite
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
			}
			if (keyH.downPressed) {
				direction = "down";
			}
			if (keyH.leftPressed) {
				direction = "left";
			}
			if (keyH.rightPressed) {
				direction = "right";
			}
			spriteCounter++;
			collisionOn = false;
			gp.cChecker.checkTile(this);

			if (!collisionOn) {
				if (direction == "up") { // switch case here only good for if->elseif->...->else bridges, nested ifs
					worldY -= speed; // allow for diagonal movement TODO: Fix this. it does not do that lol
				}
				if (direction == "down") {
					worldY += speed;
				}
				if (direction == "left") {
					worldX -= speed;
				}
				if (direction == "right") {
					worldX += speed;
				}
			}
		} else {
			spriteCounter = 11;
			spriteNum = 1;
		}

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
	}

	public void draw(Graphics2D g2) {
		// super.paintComponent(g);

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
		//g2.setColor(Color.red);
		//g2.fillRect(screenX + 8, screenY + 16, 32, 32);
	}
}
