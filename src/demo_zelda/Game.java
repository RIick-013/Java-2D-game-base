package demo_zelda;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	
	public static Player player;
	
	public World world;
	
	public List<Enemy> enemy = new ArrayList<Enemy>();
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	
		new Spritesheet();
		
		player = new Player(32,32);
		
		world = new World();
		
		enemy.add(new Enemy(200, 40));
		enemy.add(new Enemy(60, 40));
	}
	
	public void tick() {
		player.tick();
		
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) { 
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0, 0, 13));
		g.fillRect(0,  0, WIDTH*SCALE, HEIGHT*SCALE);
		
		player.render(g);
		
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).render(g);
		}
		
		world.render(g);
		
		bs.show();
	}
	
	public static void main(String[] args) { 
		Game game = new Game();
		
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("demo_zelda");

		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	@Override
	public void run() {
		
		while (true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
		// RUN
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.spd = 3;
		}
	
		// BULLET
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			player.shoot = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} 
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			player.spd = 2;
		}
	}
}
