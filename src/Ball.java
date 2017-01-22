import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {

	int x, y;
	int size = 16;
	int speed = 2;
	int vx, vy;
	
	Rectangle boundingBox;
	
	public Ball(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		vx = speed;
		vy = speed;
		
		boundingBox = new Rectangle(x, y, size, size);
		boundingBox.setBounds(x, y, size, size);
	}
	
	public void tick(Game game) {
		boundingBox.setBounds(x, y, size, size);
		
		if(x <= 0) {
			vx = speed;
			game.p2score++;
		} else if(x + size >= game.getWidth()) {
			game.p1score++;
			vx = -speed;
		}
		if(y <= 0) {
			vy = speed;
		} else if(y + size >= game.getHeight()) {
			vy = -speed;
		}
		
		
		
		x += vx;
		y += vy;
		
		paddleCollide(game);
	}
	
	@SuppressWarnings("static-access")
	private void paddleCollide(Game game) {
		if(boundingBox.intersects(game.player.boundingBox)) {
			vx = speed;
		}else if(boundingBox.intersects(game.ai.boundingBox)) {
			vx = -speed;
		}
	}
	
	public void render(Graphics g, Game game) {
		g.setColor(Color.white);
		g.fillRect(x, y, size, size);
		if(game.p1score == 10) {
			g.drawString("Player 1 won the Game!!", 130, 100);
			vx = 0;
			vy = 0;
		}else if(game.p2score == 10) {
			g.drawString("Player 2 won the Game!!", 130, 100);
			vx = 0;
			vy = 0;
		}
	}
}
