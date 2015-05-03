package weco.physic;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import weco.entity.Item;
import weco.main.Main;

public class Collisions {

	public void checkCollisions(){
		Rectangle playerBounds = Main.player.getBounding();
		//Rectangle shoot = Main.player.shot.getBounds();	
		/**Rectangle enemy_walk = Main.frame.enemyWalk.getBounds();
		
		if(Main.frm.enemyWalk.isAlive){
		ArrayList bullets = Main.player.getShots();
		if(bullets != null && !(bullets.size() == 0)){
		for(int w = 0; w < bullets.size(); w++){
			Shot m = (Shot) bullets.get(w);
			Rectangle m1 = m.getBounds();
			
			if(enemy_walk.intersects(m1)){
				Main.frame.enemyWalk.kill();
			}
			
		}
		}
		}*/
		
		for(Item item : Main.frame.Items){
			Rectangle item_bounds = item.getBounds();
			if(playerBounds.intersects(item_bounds)){
				item.appendAffect(Main.player);
				item.dead = true;
			}
		}
		
		}
	
	
	
	public static boolean isPixelCollide(double x1, double y1, BufferedImage image1,
            double x2, double y2, BufferedImage image2) {
double width1 = x1 + image1.getWidth() -1,
height1 = y1 + image1.getHeight() -1,
width2 = x2 + image2.getWidth() -1,
height2 = y2 + image2.getHeight() -1;

int xstart = (int) Math.max(x1, x2),
ystart = (int) Math.max(y1, y2),
xend   = (int) Math.min(width1, width2),
yend   = (int) Math.min(height1, height2);

int toty = Math.abs(yend - ystart);
int totx = Math.abs(xend - xstart);

for (int y=1;y < toty-1;y++){
int ny = Math.abs(ystart - (int) y1) + y;
int ny1 = Math.abs(ystart - (int) y2) + y;

for (int x=1;x < totx-1;x++) {
int nx = Math.abs(xstart - (int) x1) + x;
int nx1 = Math.abs(xstart - (int) x2) + x;
try {
if (((image1.getRGB(nx,ny) & 0xFF000000) != 0x00) &&
((image2.getRGB(nx1,ny1) & 0xFF000000) != 0x00)) {
return true;
}
} catch (Exception e) {
//System.out.println("s1 = "+nx+","+ny+"  -  s2 = "+nx1+","+ny1);
}
}
}

return false;
}
}
