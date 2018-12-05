import javax.swing.JFrame;

public class GameRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		JFrame f = new JFrame("Flappy Lion");
		int fW = 1000;
		int fH = 700;
		f.setSize(fW, fH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//LOTS OF CODE HERE>>>>
		GameWindow myGame = new GameWindow(fW, fH-20, f);
		f.add(myGame);
		
		f.setVisible(true);
	}  
}
