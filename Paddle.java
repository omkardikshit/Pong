package game;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	int h = 100;
	int w = 25;
	int x;
	int y = Game.height/2-h/2;
	int ychange = 0;
	public Paddle(int x_){
		x = x_;
	}
	
	public void update() {
		y += ychange;
		y = Lib.constrain(y,0,Game.height-h);
	}
	
	public void move(int steps) {
		ychange = steps;
	}
	
	public void show(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, w, h);
	}
	
}
