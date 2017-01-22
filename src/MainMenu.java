import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	int screenWidth = 200;
	int screenHeight = 295;
	
	int buttonWidth = 100;
	int buttonHeight = 40;
	
	public static int winScore;
	
	JButton Play, Quit;
	JCheckBox twoPlayer, limitFrameRate;
	static JTextField winScoreInput;
	JRadioButton hardMode, normalMode, impossibleMode;
	JLabel scoreLable;
	
	public MainMenu() {
		
		addButtons();
		addActions();
		
		getContentPane().setLayout(null);
		
		Play.setBounds((screenWidth - buttonWidth) / 2, 5, buttonWidth, buttonHeight);
		Quit.setBounds((screenWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight);
		twoPlayer.setBounds(0, 95, buttonWidth, buttonHeight);
		winScoreInput.setBounds(0, 220, buttonWidth * 3, buttonHeight);		
		hardMode.setBounds(0, 160, buttonWidth * 3, buttonHeight - 20);
		normalMode.setBounds(0, 140, buttonWidth * 3, buttonHeight - 20);
		impossibleMode.setBounds(0, 180, buttonWidth * 3, buttonHeight - 22);
		scoreLable.setBounds(0, 200, buttonWidth * 3, 20);
		normalMode.setSelected(true);
		
		ButtonGroup group = new ButtonGroup();
		group.add(normalMode);
		group.add(hardMode);
		group.add(impossibleMode);
		
		getContentPane().add(Play);
		getContentPane().add(Quit);
		getContentPane().add(twoPlayer);
		getContentPane().add(winScoreInput);
		getContentPane().add(hardMode);
		getContentPane().add(normalMode);
		getContentPane().add(impossibleMode);
		getContentPane().add(scoreLable);
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setSize(screenWidth, screenHeight);
		setTitle("Pong");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	private void addButtons() {
		
		Play = new JButton("Play");
		Quit = new JButton("Quit");
		twoPlayer = new JCheckBox("2 Players?");
		winScoreInput = new JTextField();
		hardMode = new JRadioButton("Enable Hard Mode");
		normalMode = new JRadioButton("Enable Normal Mode");
		impossibleMode = new JRadioButton("Enable Impossible Mode");
		scoreLable = new JLabel("Score to Win:");
	}
	
	public void addActions() {
		
		Play.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Game game = new Game();

				
				if(twoPlayer.isSelected()) {
					game.ai.isTwoPlayer = true;
				} else {
					game.ai.isTwoPlayer = false;
				}
				winScore = Integer.parseInt(winScoreInput.getText());
				System.out.println(winScore);
				if((normalMode.isSelected()) && (game.ai.isTwoPlayer == true)) {
					game.ai.speed = 1;
					game.ai.otherAI = false;
				} else if(hardMode.isSelected() && game.ai.isTwoPlayer == false) {
					game.ai.speed = 2;
					game.ai.otherAI = false;
				} else if(impossibleMode.isSelected() && game.ai.isTwoPlayer == false) {
					game.ai.speed = 3;
					game.ai.otherAI = true;
					
				} else {
					game.ai.speed = 1;
					game.ai.otherAI = false;
				}
				
				game.start();
			}
			
		});
		Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
	}
}

