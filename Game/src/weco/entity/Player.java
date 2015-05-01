package weco.entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import weco.main.keyCheck;

public class Player {

	public Rectangle bounding;
	
	public int hp = 100;
	public int ammo = 20;
	public int Maxammo = 100;

	public float x;
	public float y;
	public float speed;
	public int size = 50;
	public int rotation = 0;
	
	public int shotSize = 10;
	
	public int shootDelay = 10;
	public int leftToShoot = 0;
	
	public boolean shouldRotate = false;
	
	@SuppressWarnings("rawtypes")
	public ArrayList Shots;

	public Player(){
		Shots = new ArrayList<>();

		x = 200;
		y = 450;
		speed = 0.5F;
		
		bounding = new Rectangle((int)x, (int)y, 50, 50);
	}
	
	
	@SuppressWarnings({ "rawtypes", "null" })
	public void update(){
		if(keyCheck.keysCheck(KeyEvent.VK_R)){
			shouldRotate = true;
		}
		if(keyCheck.keysCheck(KeyEvent.VK_A)){
			x -= speed;
		}
		if(keyCheck.keysCheck(KeyEvent.VK_D)){
			x += speed;
		}
		if(keyCheck.keysCheck(KeyEvent.VK_W)){
			y -= speed;
		}
		if(keyCheck.keysCheck(KeyEvent.VK_S)){
			y += speed;
			
			
		}
		if(keyCheck.keysCheck(KeyEvent.VK_UP)){
			shot(x + size / 2 - shotSize/2, y + size/2 - shotSize, "up");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_DOWN)){
			shot(x + size / 2 - shotSize/2, y + size/2, "down");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_LEFT)){
			shot(x + size / 2 - shotSize, y + size/2 - shotSize/2, "left");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_RIGHT)){
			shot(x + size / 2 - shotSize/2, y + size/2 - shotSize/2, "right");
		}

		bounding.x = (int) x;
		bounding.y = (int) x;

		
		
		
		
		if(Shots.size() > 0){
  			ArrayList bullets = getShots();
			if(bullets != null || bullets.size() == 0){
			for(int w = 0; w < bullets.size(); w++){
				Shot m = (Shot) bullets.get(w);
				if(m.visible){
				m.move();
				}else{
					bullets.remove(w);
				}
			}
			}
		}


	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList getShots(){
		return Shots;
	}
	
	@SuppressWarnings("unchecked")
	public void shot(float x, float y, String dir){
		if(leftToShoot == 0 && ammo > 0){
			ammo--;
		Shots.add(new Shot(x, y, 2, dir, "gummi--"));
		leftToShoot = shootDelay;
		}
	}

}
