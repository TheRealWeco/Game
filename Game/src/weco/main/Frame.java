package weco.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import weco.entity.Asteroid;
import weco.entity.Crater;
import weco.entity.Item;
import weco.entity.Player;
import weco.entity.Shot;

@SuppressWarnings("serial")
public class Frame extends JFrame implements MouseListener{
	paint zeichnen;
	Player player;
	
	public ArrayList<Item> Items = new ArrayList<Item>();
	public ArrayList<Crater> Craters = new ArrayList<Crater>();
	public ArrayList<Asteroid> Asteroids = new ArrayList<Asteroid>();
	
	ArrayList<Asteroid> AsteroidsOld = new ArrayList<Asteroid>();
		
	public Frame(Player playerInput){
		super(Main.name + " V." + Main.Version);
		player = playerInput;
		zeichnen = new paint();
		zeichnen.setBounds(0, 0, Main.width, Main.height);
		
		addMouseListener(this);
		addKeyListener(new keyCheck());
		add(zeichnen);
		
		
		Items.add(new Item("gummi--", (int)player.x + 100, (int)player.y + 100, "left--"));
		
	}
	public void repaintScreen(){
		zeichnen.repaint();
	}
	private class paint extends JLabel{
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			
			@SuppressWarnings("rawtypes")
			ArrayList bullets = player.getShots();
			if(bullets != null && !(bullets.size() == 0)){
			for(int w = 0; w < bullets.size(); w++){
				Shot m = (Shot) bullets.get(w);
				//g.drawImage(m.bulletImg, m.x, m.y, null);
				g.drawRect((int)m.x, (int)m.y, m.size, m.size);
			}
			}
			
			
			if(player.isDead){
				g.drawString("YOU DIED", Main.width/2, Main.height/2);
				g.drawString("Press \"R\" to restart", Main.width/2, Main.height/2 + 30);
			}
			
			if(player.playDieAnimation){
				Craters.add(new Crater((int)player.x, (int)player.y));
				player.playDieAnimation = false;
			}
			
			for(Crater crater : Craters){
				g.drawImage(Main.images.getCrater(), (int)crater.x, (int)crater.y, null);
			}
			
			if(!player.isDead){
			
			Graphics2D g2g = (Graphics2D)g.create();
			g2g.setColor(Color.green);
			g2g.setFont(new Font(getFont().getName(), 0, 25));
		    g2g.drawString("HP: " + player.hp + "/" + player.spaceship.hp, 5, 25);
		    g2g.drawString("Ammo: " + player.ammo + "/" + player.Maxammo, 5, 25 + 30);
		    g2g.drawString("Special Uses left: " + player.specialAmmo, 5, 25 + 30 + 30);
		    g2g.dispose();
		    /*gHpBar.drawRect(4, 4, player.hp * 2 + 1, 20 + 1);
		    gHpBar.setColor(Color.red);
		    gHpBar.fillRect(5, 5, player.hp * 2, 20);
		    gHpBar.setColor(Color.black);
		    gHpBar.drawRect(Main.width - player.Maxammo * 2 - 11, 5, player.Maxammo * 2 + 1, 20 + 1);
		    gHpBar.setColor(Color.blue);
		    gHpBar.fillRect(Main.width - player.ammo * 2 - 10, 6, player.ammo * 2, 20);
		    gHpBar.setColor(Color.black);
		    
		    if(player.specialAmmo > 0){
			    g.setColor(Color.orange);
			    g.fillRect(Main.width - player.specialAmmo * 2 - 11, 30, player.specialAmmo * 2 + 1, 20 + 1);
			    g.setColor(Color.black);
		    }
		    */
			for(Item item : Items){
				if(!item.dead){
				g.drawRect((int)item.x, (int)item.y, item.size, item.size);
				}
			}
			
			for(Asteroid asteroid : Asteroids){
				if(!asteroid.isDead){
					asteroid.update();
				Graphics2D g2d = (Graphics2D)g.create();
				AffineTransform transform = new AffineTransform();
				transform.rotate(Math.toRadians(asteroid.rotation), (int) asteroid.x, (int) asteroid.y);
				g2d.transform(transform);
				g2d.drawImage(asteroid.image, (int) asteroid.x, (int) asteroid.y, null);
				g2d.dispose();
				}
				
			}
			
		    /*Graphics2D g2d = (Graphics2D)g;
			AffineTransform transform = new AffineTransform();
			transform.rotate(Math.toRadians(player.rotation), (int)player.x + player.size/2, (int)player.y + player.size/2);
			g2d.transform(transform);
			g2d.drawRect((int)player.x, (int)player.y, player.size, player.size);*/
			Graphics2D g2d = (Graphics2D)g.create();
			AffineTransform transform = new AffineTransform();
			transform.rotate(Math.toRadians(player.rotation), (int) player.x + player.size.x/2, (int) player.y + player.size.y/2);
			g2d.transform(transform);
			g2d.drawImage(player.image, (int) player.x, (int) player.y, null);
			g2d.dispose();
			
			}
			
		}
	}
	public void update() {
		player.update();
				
		
		if(player.isDead){
			if(keyCheck.keysCheck(KeyEvent.VK_R)){
				Main.player.reset();
			}
		}else{
			
			if(keyCheck.keysCheck(KeyEvent.VK_UP)){
				player.shouldRotate = true;
				player.rotateDir = player.rotateDir = "up";
			}
			if(keyCheck.keysCheck(KeyEvent.VK_DOWN)){
				player.shouldRotate = true;
				player.rotateDir = player.rotateDir = "down";
			}
			if(keyCheck.keysCheck(KeyEvent.VK_LEFT)){
				player.shouldRotate = true;
				player.rotateDir = player.rotateDir = "left";
			}
			if(keyCheck.keysCheck(KeyEvent.VK_RIGHT)){
				player.shouldRotate = true;
				player.rotateDir = player.rotateDir = "right";
			}

			if(keyCheck.keysCheck(KeyEvent.VK_W)){
				for(Crater crater : Craters){
					crater.y = crater.y + player.speed;
					onMove();
				}
				
				for(Item item : Items){
					item.y = item.y + player.speed;
				}
				for(Asteroid asteroid : Asteroids){
					asteroid.y = asteroid.y + player.speed;
				}
				onMove();
			}
			if(keyCheck.keysCheck(KeyEvent.VK_A)){
				for(Crater crater : Craters){
					crater.x = crater.x + player.speed;
				}
				for(Item item : Items){
					item.x = item.x + player.speed;
				}
				for(Asteroid asteroid : Asteroids){
					asteroid.x = asteroid.x + player.speed;
				}
				onMove();
			}
			if(keyCheck.keysCheck(KeyEvent.VK_S)){
				for(Crater crater : Craters){
					crater.y = crater.y - player.speed;
				}
				for(Item item : Items){
					item.y = item.y - player.speed;
				}
				for(Asteroid asteroid : Asteroids){
					asteroid.y = asteroid.y - player.speed;
				}
				onMove();
				
			}
			if(keyCheck.keysCheck(KeyEvent.VK_D)){
				for(Crater crater : Craters){
					crater.x = crater.x - player.speed;
				}
				for(Item item : Items){
					item.x = item.x - player.speed;
				}
				for(Asteroid asteroid : Asteroids){
					asteroid.x = asteroid.x - player.speed;
				}
				onMove();
			}
		}
	}
	public void init() {
		
	}
	
	public void onMove(){
		if(Main.methodes.randInt(0, 5000) == 1){
			Asteroids.add(new Asteroid(Main.methodes.randInt(0, Main.width), Main.methodes.randInt(0, 0), 0, null, 200, Main.methodes.randInt(0, 9), Main.methodes.randInt(0, 360)));
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {		
	}
	@Override
	public void mouseExited(MouseEvent e) {		
	}
	@Override
	public void mousePressed(MouseEvent e) {		
	}
	@Override
	public void mouseReleased(MouseEvent e) {		
	}
	
}
