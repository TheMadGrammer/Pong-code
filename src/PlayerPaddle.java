import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerPaddle {
	
	int x;
	int y;
	int width = 15;
	int height = 40;
	int speed = 1;
	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingDown = false;

	public PlayerPaddle(int x, int y) {
		this.x = x;
		this.y = y;
		
		boundingBox = new Rectangle(x, y, width, height);
		boundingBox.setBounds(x, y, width, height);
	}
	
	public void tick(Game game) {
		boundingBox.setBounds(x, y, width, height);
		
		if(goingUp && y >= 0) {
			y -= speed;
		}
	
	if(goingDown && y < game.getHeight() - height) {
		y += speed;
	}
	}
	public void render(Graphics g) {
		g.setColor(Color.WHITE); 
		g.fillRect(x, y, width, height);
	}

}
