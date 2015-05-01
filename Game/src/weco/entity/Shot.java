package weco.entity;

import java.awt.Rectangle;

import weco.main.Main;

public class Shot {
	
	public float x;
	public float y;
	int moveSpeed;
	int defSpeed;
	public int size = 10;
	
	public String type;
	boolean visible = true;
	String driection = "";
	boolean revert = false;
	
	boolean gummi = false;
	public Shot(float f, float g, int speed, String dir, String type){
		this.x = f;
		this.y = g;
		this.type = type;
		moveSpeed = speed;
		defSpeed = speed;
		this.driection = dir;
		
		
		if(type.equals("")){
			gummi = false;
		}else{
		String[] types = type.split("--");
		for(int i = 0; i < types.length; i++){
			if(types[i].equalsIgnoreCase("gummi")){
				gummi = true;
			}
		}
		}
		
		
				
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, size, size);
	}
	public void move(){
		
		if(!revert){
		if(driection.equalsIgnoreCase("up")){
			y = y - moveSpeed;
		}
		if(driection.equalsIgnoreCase("down")){
			y = y + moveSpeed;

		}
		if(driection.equalsIgnoreCase("left")){
			x = x - moveSpeed;

		}
		if(driection.equalsIgnoreCase("right")){
			x = x + moveSpeed;

		}
		}else{
			if(driection.equalsIgnoreCase("up")){
				y = y + moveSpeed;
			}
			if(driection.equalsIgnoreCase("down")){
				y = y - moveSpeed;

			}
			if(driection.equalsIgnoreCase("left")){
				x = x + moveSpeed;

			}
			if(driection.equalsIgnoreCase("right")){
				x = x - moveSpeed;

			}
		}
		
		if(!gummi){
		if(x > Main.width){
			visible = false;
		}
		if(x < 0){
			visible = false;
		}
		if(y > Main.height){
			visible = false;
		}
		if(y < 0){
			visible = false;
		}
			
		}else{
			if(x > Main.width){
				revert = true;
			}
			if(x < 0){
				revert = false;
			}
			if(y > Main.height){
				revert = true;
			}
			if(y < 0){
				revert = false;
			}
		}

	}
}
