package weco.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import weco.main.Main;

public class Asteroid {

	public int hp;
	public Item drop;
	public boolean hasDrop = false;
	public float x;
	public float y;
	public float speed;
	int type;
	public int rotation;
	
	public boolean isDead = false;
	public BufferedImage image;
	
	Rectangle bounding;
	
	public Asteroid(float x, float y, float speed, Item drop, int hp, int type, int rotation){
		

		this.hp = hp;
		this.drop = drop;
		if(drop != null){
			hasDrop = true;
		}else{
			hasDrop = false;
		}
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.type = type;
		this.rotation = rotation;
		
		image = Main.images.getSpriteSheet().getSubimage(0 + type*100, 0, 100, 100);
		
		bounding = new Rectangle((int)x, (int)y, image.getWidth(), image.getHeight());
		
		bounding.x = (int) x;
		bounding.y = (int) y;

	}
	
	public Rectangle getBounding(){
		return bounding;
	}
	
	public void update() {
		
		if(!isDead){
		
		if(hp <= 0){
			isDead = true;
		}
		
		if(x < -100){
			isDead = true;
		}
		if(y < -100){
			isDead = true;
		}
		
		if(x > Main.width + 100){
			isDead = true;
		}
		if(y > Main.height + 100){
			isDead = true;
		}
		bounding.x = (int) x;
		bounding.y = (int) y;
		}
	}
	
}
