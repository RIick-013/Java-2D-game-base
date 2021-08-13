package demo_zelda;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	public static List<Blocks> blocks = new ArrayList<Blocks>();
	
	public World() {
		for (int xx = 0; xx < 15*2; xx++) {
			blocks.add(new Blocks(xx*32,0));
		}
		
		for (int xx = 0; xx < 15*2; xx++) {
			blocks.add(new Blocks(xx*32,480-32));
		}
		
		for (int yy = 0; yy < 15*2; yy++) {
			blocks.add(new Blocks(0,yy*32));
		}
		
		for (int yy = 0; yy < 15*2; yy++) {
			blocks.add(new Blocks(640-32,yy*32));
		}
		
		blocks.add(new Blocks(220, 120));
	}
	
	public static boolean isFree(int x, int y) {
		for (int i = 0; i < blocks.size(); i++) {
			Blocks curBlock = blocks.get(i);
			
			if(curBlock.intersects(new Rectangle(x, y, 32, 32))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).render(g);
		}
	}
	
}
