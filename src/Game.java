import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	
	JFrame frame; // window of game
	public final int WIDTH = 400; // Width of game
	public final int HEIGHT = WIDTH / 16 * 9; // height of game
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT); // makes WIDTH and HEIGHT into a single variable
	static boolean gameRunning = false; // whether the game is running or not
	public final String TITLE = "Pong";
	
	public int p1score;
	public int p2score;
	static boolean windowOpen = true;
	
	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;
	InputHandler IH;

	public void run() {

		while (gameRunning) { // if gameRunning == true....
			
			tick();
			render();		
			
			try {
				Thread.sleep(7);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}

	}
	
	public synchronized void start() {
		gameRunning = true;
		new Thread(this).start();
	}
	
	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}

	public Game() {
		


		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		
		player = new PlayerPaddle(10, 80);
		ai = new AIPaddle(getWidth() - 25, 80);
		ball = new Ball(getWidth() / 2, getHeight() / 2);

		IH = new InputHandler(this);

	}
	
	public void tick() {
		player.tick(this);
		ai.tick(this);
		ball.tick(this);
	}
	

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.white);
		
		g.drawString("Player 1: " + p1score, 0, 10);
		g.drawString("Player 2: " + p2score, getWidth() - 60, 10);
		
		player.render(g);
		ai.render(g);
		ball.render(g, this);
		
		g.dispose();
		bs.show();
	}

}
