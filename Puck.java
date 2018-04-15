package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Puck {
	int x = Game.width/2;
	int y = Game.height/2;
	double xspeed;
	double yspeed;
	int r = 12;
	
	Puck(){
		reset();
	}

	public void update() {
		x += xspeed;
		y += yspeed;
	}
	
	public void show(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, r*2, r*2);
	}

	public void reset() {
		x = Game.width/2;
		y = Game.height/2;
		double angle = Lib.random(-Math.PI/4,Math.PI/4);
		xspeed = 7* Math.cos(angle);
		yspeed = 7* Math.sin(angle);
		if(Math.random() < 0.5) {
			xspeed *= -1;
		}
	}
	
	public void edges() {
		if(y < 0 || y > Game.height) {
			yspeed *= -1;
		}
		if(x < 0) {
			reset();
			Game.RScore++;
		}
		
		if(x > Game.width) {
			reset();
			Game.LScore++;
		}
		
	}

	public void checkPaddleLeft(Paddle p) {
		Rectangle puck = new Rectangle(x,y,r,r);
		Rectangle paddle = new Rectangle(p.x,p.y,p.w,p.h);
		if(puck.intersects(paddle)) {
			if(x > p.x) {
				float diff = y -( p.y - p.h/2);
				float rad = (float)Math.toRadians(45);
				float angle = Map.MapTo(diff, 0, p.h,-rad,rad);
				xspeed = 7*Math.cos(angle);
				yspeed = 7*Math.sin(angle);
				x = p.x + p.w/2 + r;
			}
		}else if(y < p.y + p.h/2 && y > p.y-p.h/2 && x-r < p.x + p.w) {
				if(x > p.x) {
					float diff = y -( p.y - p.h/2);
					float rad = (float)Math.toRadians(45);
					float angle = Map.MapTo(diff, 0, p.h,-rad,rad);
					xspeed = 5*Math.cos(angle);
					yspeed = 5*Math.sin(angle);
					x = p.x + p.w/2 + r;
				}
		}
	}
	
	public void checkPaddleRight(Paddle p) {
		Rectangle puck = new Rectangle(x,y,r,r);
		Rectangle paddle = new Rectangle(p.x,p.y,p.w,p.h);
		if(puck.intersects(paddle)) {
			if(x < p.x) {
				float diff = y -( p.y - p.h/2);
				float rad = (float)Math.toRadians(135);
				float angle = Map.MapTo(diff, 0, p.h,-rad,rad);
				xspeed = 7*Math.cos(angle);
				yspeed = 7*Math.sin(angle);
				x = p.x - p.w/2 - r;
			}
		}else if(y < p.y + p.h/2 && y > p.y-p.h/2 && x+r > p.x - p.w) {
			if(x < p.x) {
				float diff = y -( p.y - p.h/2);
				float rad = (float)Math.toRadians(135);
				float angle = Map.MapTo(diff, 0, p.h,-rad,rad);
				xspeed = 7*Math.cos(angle);
				yspeed = 7*Math.sin(angle);
				x = p.x - p.w/2 - r;
		}
	}
	}
	
}





















