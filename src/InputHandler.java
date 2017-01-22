import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{
	
	public InputHandler(Game game) {
		game.addKeyListener(this);
		
	}

	
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		//Player 1 controls
		if(keyCode == KeyEvent.VK_W) {
			Game.player.goingDown = false;
			Game.player.goingUp = true;
		}
		if(keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = true;
			Game.player.goingUp = false;
		}
		// Player 2 controls
		if(keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = true;
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = true;
		}
		
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (keyCode == KeyEvent.VK_M) {
			Launcher.main(null);
			Game.windowOpen = false;
		}
	}



	public void keyTyped(KeyEvent e) {
		
		
	}



	public void keyReleased(KeyEvent e) {
		
		//Player 1 controls
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_W) {
			Game.player.goingDown = false;
			Game.player.goingUp = false;
		}
		if(keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = false;
			Game.player.goingUp = false;
		}
		
		// Player 2 controls
		if(keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = false;
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = false;
		}
		
	}
	
}
