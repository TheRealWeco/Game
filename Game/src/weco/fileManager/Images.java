package weco.fileManager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {
	public BufferedImage getCrater(){
		try {
			return ImageIO.read(getClass().getClassLoader().getResourceAsStream("files\\crater.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public BufferedImage getSpriteSheet(){
		try {
			return ImageIO.read(getClass().getClassLoader().getResourceAsStream("files\\spriteSheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage getImage(String image){
		try {
			return ImageIO.read(getClass().getClassLoader().getResourceAsStream("files\\" + image + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
