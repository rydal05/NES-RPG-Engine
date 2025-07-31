package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends parentObject {
	public OBJ_Chest() {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/itemSheet.png")).getSubimage(0, 0, 16, 16);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
