package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class parentObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY, ID;

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.Player.worldX + gp.Player.screenX;
		int screenY = worldY - gp.Player.worldY + gp.Player.screenY;

		if (worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX
				&& worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX
				&& worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY
				&& worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
