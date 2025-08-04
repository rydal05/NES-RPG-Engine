package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends parentObject{
	public OBJ_Boots() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/itemSheet.png")).getSubimage(48, 0, 16, 16);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		solidArea.x = 5;
	}
}
