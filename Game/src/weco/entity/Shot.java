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
	public boolean visible = true;
	String driection = "";
	boolean revert = false;
	
	boolean gummi = false;
	int bounds = 0;
	int MaxBounds = 3;

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
		
		if(bounds < MaxBounds){
		
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
			if(driection.equalsIgnoreCase("left")){
			if(x > Main.width){
				bounds++;
				revert = false;
			}
			if(x < 0){
				bounds++;
				revert = true;
			}
			}
			
			if(driection.equalsIgnoreCase("right")){
			if(x > Main.width){
				bounds++;
				revert = true;
			}
			if(x < 0){
				bounds++;
				revert = false;
			}
			}
			if(driection.equalsIgnoreCase("up")){
			if(y > Main.height){
				bounds++;
				revert = false;
			}
			if(y < 0){
				bounds++;
				revert = true;
			}
			}
			if(driection.equalsIgnoreCase("down")){
			if(y > Main.height){
				bounds++;
				revert = true;
			}
			if(y < 0){
				bounds++;
				revert = false;
			}
			}
			
			
		}
		}else{
			visible = false;
		}
	}
}
