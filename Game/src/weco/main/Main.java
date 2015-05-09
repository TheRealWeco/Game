package weco.main;

import java.util.HashMap;

import weco.data.LanguageType;
import weco.data.Size;
import weco.entity.Item;
import weco.entity.Player;
import weco.entity.Spaceship;
import weco.fileManager.Audio;
import weco.fileManager.Images;
import weco.fileManager.Language;
import weco.methodes.Methodes;
import weco.physic.Collisions;

public class Main implements Runnable{
	
	public static Player player;
	public static Frame frame;
	public static Main instance;
	public static Collisions collision;
	public static Images images;
	public static Audio audio;
	public static Methodes methodes;
	
	public static String currentLang = "en";
	
	public static int height = 600;
	public static int width = 800;
	public static String name = "Game";
	public static String Version = "1.0.0";
	
	public static HashMap<Integer, Spaceship> Spaceships = new HashMap<Integer, Spaceship>();
	
	public static int oneSecTick = 0;
	public static int HundredTick = 0;
	public static int TenTick = 0;

	public static boolean Running = false;
	public static void main(String[] args){
		images = new Images();
		audio = new Audio();
		
		Spaceships.put(0, new Spaceship(100, 100, 20, Language.string.get(new LanguageType(Main.currentLang, "ship0")), 0, 5, 
				images.getSpriteSheet().getSubimage(0, 100, 100, 100), 0.75F, new Size(100, 100)));
		
		Spaceships.put(1, new Spaceship(100, 50, 10, Language.string.get(new LanguageType(Main.currentLang, "ship1")), 1, 4, 
				images.getSpriteSheet().getSubimage(100, 100, 50, 100), 1.0F, new Size(50, 100)));

		Spaceships.put(2, new Spaceship(250, 250, 100, Language.string.get(new LanguageType(Main.currentLang, "ship2")), 2, 2, 
				images.getSpriteSheet().getSubimage(200, 100, 100, 100), 0.50F, new Size(100, 100)));
		
		Spaceships.put(3, new Spaceship(250, 250, 100, Language.string.get(new LanguageType(Main.currentLang, "ship3")), 2, 2, 
				images.getImage("smiley"), 0.50F, new Size(100, 100)));

		player = new Player(Spaceships.get(0));
		frame = new Frame(player);
		collision = new Collisions();
		methodes = new Methodes();
		
		
		instance = new Main();

		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		instance.start();

	}
	
	
	
	
	public synchronized void start() {
		Running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		Running = false;
	}


	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		int ticks = 0;
		int frames = 0;
		
		long lasTimer = System.currentTimeMillis();
		double delta = 0;

		boolean shouldRender = true;
				
		frame.init();
		while(Running){
						
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			while (delta >= 0){
			ticks++;
			delta -= 1;
			shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender){
			frames++;
			
			frame.repaintScreen();
			frame.update();
			longTwoTick();
			}
			
			if(System.currentTimeMillis() - lasTimer > 1000){
				lasTimer += 1000;
				System.out.println(frames + " : " + ticks);
				frames = 0;
				ticks = 0;
			}
			
			if(oneSecTick*2 == 1000){
				longThousandUpdate();
				oneSecTick = 0;
			}
			
			oneSecTick++;
			
			if(HundredTick*2 == 100){
				longHundredTick();
				HundredTick = 0;
			}
			
			HundredTick++;
			
			if(TenTick*2 == 10){
				longTenTick();
				TenTick = 0;
			}
			
			TenTick++;
		}
	}
	
	public void longThousandUpdate(){	
	}
	
	public void longHundredTick(){
		
		if(player.leftToShoot > 0){
			player.leftToShoot--;
		}
		
		
	}
	
	public void longTenTick(){

	}
	public void longTwoTick(){
		
		
		collision.checkCollisions();
		if(player.shouldRotate){
			
			if(player.rotateDir.equals("up")){	
				int toRotate = 0;
				
				if(player.rotation == toRotate){
					player.shouldRotate = false;
				}
				
				if(player.rotation < toRotate){
				player.rotation++;
				}
				if(player.rotation > toRotate){
				player.rotation--;
				}
			}
			if(player.rotateDir.equals("right")){	
				int toRotate = 90;
				
				if(player.rotation == toRotate){
					player.shouldRotate = false;
				}
				
				if(player.rotation < toRotate){
				player.rotation++;
				}
				if(player.rotation > toRotate){
				player.rotation--;
				}
			}
			if(player.rotateDir.equals("left")){	
				int toRotate = -90;
				
				if(player.rotation == toRotate){
					player.shouldRotate = false;
				}
				
				if(player.rotation < toRotate){
				player.rotation++;
				}
				if(player.rotation > toRotate){
				player.rotation--;
				}
			}
			if(player.rotateDir.equals("down")){	
				int toRotate = 180;
				
				if(player.rotation == toRotate){
					player.shouldRotate = false;
				}
				
				if(player.rotation < toRotate){
				player.rotation++;
				}
				if(player.rotation > toRotate){
				player.rotation--;
				}
			}
			
		}
		for(Item item : frame.Items){
			item.updateMove();
		}
		
	}


}
