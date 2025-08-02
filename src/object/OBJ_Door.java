package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends parentObject {
	public OBJ_Door() {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/itemSheet.png")).getSubimage(16, 0, 16, 16);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
