package weco.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import weco.entity.Item;
import weco.entity.Player;
import weco.entity.Shot;

@SuppressWarnings("serial")
public class Frame extends JFrame implements MouseListener{
	paint zeichnen;
	Player player;
	
	public ArrayList<Item> Items = new ArrayList<Item>();
	
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
			
			if(!player.isDead){
			
		    Graphics2D gHpBar = (Graphics2D)g;
		    gHpBar.drawRect(4, 4, player.hp * 2 + 1, 20 + 1);
		    gHpBar.setColor(Color.red);
		    gHpBar.fillRect(5, 5, player.hp * 2, 20);
		    gHpBar.setColor(Color.black);
		    gHpBar.drawRect(Main.width - player.Maxammo * 2 - 11, 5, player.Maxammo * 2 + 1, 20 + 1);
		    gHpBar.setColor(Color.blue);
		    gHpBar.fillRect(Main.width - player.ammo * 2 - 10, 6, player.ammo * 2, 20);
		    gHpBar.setColor(Color.black);
		    
		    if(player.specialAmmo > 0){
			    gHpBar.setColor(Color.orange);
			    gHpBar.fillRect(Main.width - player.specialAmmo * 2 - 11, 30, player.specialAmmo * 2 + 1, 20 + 1);
			    gHpBar.setColor(Color.black);
		    }
		    
			for(Item item : Items){
				if(!item.dead){
				g.drawRect(item.x, item.y, item.size, item.size);
				}
			}
			
		    Graphics2D g2d = (Graphics2D)g;
			AffineTransform transform = new AffineTransform();
			transform.rotate(Math.toRadians(player.rotation), (int)player.x + player.size/2, (int)player.y + player.size/2);
			g2d.transform(transform);
			g2d.drawRect((int)player.x, (int)player.y, player.size, player.size);
			}
		}
	}
	public void update() {
		
	}
	public void init() {
		
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
