package weco.main;

import weco.entity.Item;
import weco.entity.Player;
import weco.physic.Collisions;

public class Main implements Runnable{

	public static Player player;
	public static Frame frame;
	public static Main instance;
	public static Collisions collision;
	
	public static int height = 600;
	public static int width = 800;
	public static String name = "Game";
	public static String Version = "1.0.0";
	
	public static int oneSecTick = 0;
	public static int HundredTick = 0;
	public static int TenTick = 0;

	public static boolean Running = false;
	public static void main(String[] args){
		player = new Player();
		frame = new Frame(player);
		collision = new Collisions();

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
			player.update();
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
			player.rotation++;
			if(player.rotation == 360){
				player.shouldRotate = false;
				player.rotation = 0;
			}
		}
		for(Item item : frame.Items){
			item.updateMove();
		}

	}


}
