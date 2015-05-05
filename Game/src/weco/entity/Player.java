package weco.entity;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import weco.data.Size;
import weco.main.Main;
import weco.main.keyCheck;

public class Player {

	public Rectangle bounding;
	
	public int hp = 100;
	public int ammo = 20;
	public int Maxammo = 100;
	public int specialAmmo = 0;

	public float x;
	public float y;
	public float Defx;
	public float Defy;
	public float speed;
	public Size size;
	private Size oldSize;
	public int rotation = 0;
	
	public int shotSize = 10;
	
	public int shootDelay = 10;
	public int leftToShoot = 0;
	
	public boolean shouldRotate = false;
	public boolean isDead = false;
	public boolean playDieAnimation = false;
	
	public String shotType = "";
	
	@SuppressWarnings("rawtypes")
	public ArrayList Shots;

	public BufferedImage image;

	public Spaceship spaceship;
	
	public String rotateDir = "up";
	
	public Player(Spaceship spaceship){
		Shots = new ArrayList<>();
		
		this.spaceship = spaceship;
		this.size = spaceship.size;
		this.hp = spaceship.hp;
		this.image = spaceship.image;
		this.ammo = spaceship.ammoOnBuy;
		this.Maxammo = spaceship.maxAmmo;
		this.speed = spaceship.speed;
		
		x = Main.width/2 - size.x/2;
		y = Main.height/2 - size.y/2;
		Defx = x;
		Defy = y;
		oldSize = size;
		
		bounding = new Rectangle((int)x, (int)y, size.x, size.y);
	}
	
	
	@SuppressWarnings({ "rawtypes", "null" })
	public void update(){
		
		if(!isDead){
		
		/*if(keyCheck.keysCheck(KeyEvent.VK_R)){
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
			
			
		}*/
			
		/*if(keyCheck.keysCheck(KeyEvent.VK_UP)){
			shot(x + size.x / 2 - shotSize/2, y + size.y/2 - shotSize, "up");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_DOWN)){
			shot(x + size.x / 2 - shotSize/2, y + size.y/2, "down");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_LEFT)){
			shot(x + size.x / 2 - shotSize, y + size.y/2 - shotSize/2, "left");
		}
		if(keyCheck.keysCheck(KeyEvent.VK_RIGHT)){
			shot(x + size.x / 2 - shotSize/2, y + size.y/2 - shotSize/2, "right");
		}*/
			
	    if(keyCheck.keysCheck(KeyEvent.VK_SPACE)){
			shot(x + size.x / 2 - shotSize/2, y + size.y/2 - shotSize/2, rotateDir);
	    }

		bounding.x = (int) x;
		bounding.y = (int) y;

		}
		
		
		
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
	
	
	public void kill(){
		isDead = true;
		playDieAnimation = true;
	}
	
	
	@SuppressWarnings("unchecked")
	public void shot(float x, float y, String dir){
		if(!isDead){
		
		if(leftToShoot == 0 && ammo > 0){
		if(specialAmmo > 0){
			specialAmmo--;
			ammo--;
		Shots.add(new Shot(x, y, 2, dir, shotType));
		leftToShoot = shootDelay;
		}else{
			ammo--;
		Shots.add(new Shot(x, y, 2, dir, ""));
		leftToShoot = shootDelay;
		}
		}
		}
	}


	public void reset() {
		
		hp = 100;
		ammo = 20;
		Maxammo = 100;
		specialAmmo = 0;
		x = Defx;
		y = Defy;
		size = oldSize;
		rotation = 0;
		shotSize = 10;
		shootDelay = 10;
		shouldRotate = false;
		isDead = false;
		playDieAnimation = false;
		
	}
}
