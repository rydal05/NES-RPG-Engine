package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	BufferedImage tileset;
	
	

	public TileManager(GamePanel gp) {
		this.gp = gp;

		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}

	public void getTileImage() {
		try {
			
			tileset = ImageIO.read(getClass().getResourceAsStream("/tiles/tileset.png"));

			tile[0] = new Tile();
			tile[0].image = tileset.getSubimage(0, 0, 16, 16);

			tile[1] = new Tile();
			tile[1].image = tileset.getSubimage(16, 0, 16, 16);
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].image = tileset.getSubimage(32, 0, 16, 16);
			tile[2].collision = true;

			tile[3] = new Tile();
			tile[3].image = tileset.getSubimage(64, 0, 16, 16);

			tile[4] = new Tile();
			tile[4].image = tileset.getSubimage(80, 0, 16, 16);
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].image = tileset.getSubimage(96, 0, 16, 16);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String str) {
		try {
			InputStream is = getClass().getResourceAsStream(str);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				while (col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int worldX;
		int worldY;
		int screenX;
		int screenY;
		
			
		
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			worldX = col * gp.tileSize;
			worldY = row * gp.tileSize;
			screenX = worldX - gp.Player.worldX + gp.Player.screenX;
			screenY = worldY - gp.Player.worldY + gp.Player.screenY;
			int tileNum = mapTileNum[col][row];
			// if(screenX < 0) { //weird texture duplicating effect that i think looks
			// really cool but i dont know how to utilize so im just gonna keep it here for
			// now lol
			// screenX = 0;
			// }
			// if(screenY < 0) {
			// screenY = 0;
			// }
			if (worldX + gp.tileSize > gp.Player.worldX - gp.Player.screenX
					&& worldX - gp.tileSize < gp.Player.worldX + gp.Player.screenX
					&& worldY + gp.tileSize > gp.Player.worldY - gp.Player.screenY
					&& worldY - gp.tileSize < gp.Player.worldY + gp.Player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				
			}
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
}
