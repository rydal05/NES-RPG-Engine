package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends parentObject {
	public OBJ_Key() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/itemSheet.png")).getSubimage(32, 0, 16, 16);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
