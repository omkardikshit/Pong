package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	private static final long serialVersionUID = 1L;

	public static final int width = 640;
	public static final int height = 480;
	public static String title = "Game";
	
	public static boolean running = false;
	public final int fps = 60;
	private Thread thread;
	private JFrame frame;
	public Puck puck;
	public Paddle left;
	public Paddle right;
	public static int LScore = 0;
	public static int RScore = 0;
	public Game() {
		frame = new JFrame();
		puck = new Puck();
		left = new Paddle(0);
		right = new Paddle(width-25);
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0/fps;
		double delta = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				update();
				delta--;
			}

			render();
		}
	}
	
	public void update() {
		puck.update();
		puck.edges();
		left.update();
		right.update();
		puck.checkPaddleLeft(left);
		puck.checkPaddleRight(right);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(51,51,51));
		g.fillRect(0, 0, getWidth(), getHeight());
		puck.show(g);
		right.show(g);
		left.show(g);
		g.setFont(new Font("Ubuntu",Font.BOLD,32));
		g.drawString(String.valueOf(LScore), 32, 40);
		g.drawString(String.valueOf(RScore), width-64, 40);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.start();
	}

	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_W) left.move(-10); 
		if(code == KeyEvent.VK_S) left.move(10);
		if(code == KeyEvent.VK_O) right.move(-10); 
		if(code == KeyEvent.VK_L) right.move(10);
	}

	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_W) left.move(0); 
		if(code == KeyEvent.VK_S) left.move(0);
		if(code == KeyEvent.VK_O) right.move(0); 
		if(code == KeyEvent.VK_L) right.move(0);
	}

	public void keyTyped(KeyEvent arg0) {}

}
