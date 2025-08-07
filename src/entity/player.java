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
	public int hasKey = 0;

	public Player(GamePanel gp, keyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(8, 16, 32, 32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = (int) (gp.scale / .75);
		;
		direction = "down";
	}

	public void getPlayerImage() {
		try {
			spriteSheet = ImageIO.read(getClass().getResourceAsStream("/player/player.png"));
			down1 = spriteSheet.getSubimage(0, 0, 16, 16);
			down2 = spriteSheet.getSubimage(16, 0, 16, 16);
			down3 = spriteSheet.getSubimage(32, 0, 16, 16);
			up1 = spriteSheet.getSubimage(0, 16, 16, 16);
			up2 = spriteSheet.getSubimage(16, 16, 16, 16);
			up3 = spriteSheet.getSubimage(32, 16, 16, 16);
			right1 = spriteSheet.getSubimage(0, 32, 16, 16);
			right2 = spriteSheet.getSubimage(16, 32, 16, 16);
			right3 = spriteSheet.getSubimage(32, 32, 16, 16);
			left1 = spriteSheet.getSubimage(0, 48, 16, 16);
			left2 = spriteSheet.getSubimage(16, 48, 16, 16);
			left3 = spriteSheet.getSubimage(32, 48, 16, 16);
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
			// check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			// check obj collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

				if (direction == "up" && !collisionOn) { // switch case here only good for if->elseif->...->else bridges, nested ifs
					worldY -= speed; // allow for diagonal movement TODO: Fix this. it does not do that lol
				}
				if (direction == "down" && !collisionOn) {
					worldY += speed;
				}
				if (direction == "left" && !collisionOn) {
					worldX -= speed;
				}
				if (direction == "right" && !collisionOn) {
					worldX += speed;
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

	public void pickUpObject(int index) {
		if (index != 999) {
			String objName = gp.obj[index].name;

			switch (objName) {
			case "Key":
				hasKey++;
				gp.obj[index] = null;
				gp.playSE(2);
				gp.ui.showMessage("You obtained a key.");
				break;
			case "Door":
				if (hasKey > 0) {
					gp.obj[index] = null;
					hasKey--;
					gp.playSE(0);
				}
				System.out.println(hasKey);
				break;
			case "Boots":
				speed += 2;
				gp.obj[index] = null;
				gp.playSE(1);
				break;
			}
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
		// g2.setColor(Color.red);
		// g2.fillRect(screenX + 8, screenY + 16, 32, 32);
	}
}
