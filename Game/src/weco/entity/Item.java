package weco.entity;

import weco.main.Main;

public class Item {
	
	public String type;
	public int x;
	public int y;
	public String moveDir;
	public int speed = 1;
	public int size = 20;
	
	public boolean revert = false;
	
	public Item(String type, int x, int y, String moveDir){
		this.type = type;
		this.x = x;
		this.y = y;
		this.moveDir = moveDir;
	}
	
	public void updateMove(){
		String[] moveDirs = moveDir.split("--");
		for(int i = 0; i < moveDirs.length; i++){
			if(!revert){
		if(moveDirs[i].equalsIgnoreCase("down")){
			y = y + speed;
		}
		if(moveDirs[i].equalsIgnoreCase("up")){
			y = y - speed;

		}
		if(moveDirs[i].equalsIgnoreCase("right")){
			x = x + speed;

		}
		if(moveDirs[i].equalsIgnoreCase("left")){
			x = x - speed;

		}
			}else{
				if(moveDirs[i].equalsIgnoreCase("down")){
					y = y - speed;
				}
				if(moveDirs[i].equalsIgnoreCase("up")){
					y = y + speed;

				}
				if(moveDirs[i].equalsIgnoreCase("right")){
					x = x - speed;

				}
				if(moveDirs[i].equalsIgnoreCase("left")){
					x = x + speed;

				}
			}

		}
		
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
