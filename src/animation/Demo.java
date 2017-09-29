package animation;


import java.awt.Point;


import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Testing & Demo class for straightAnimation and obliqueAnimation
 * 
 * @author Stepan214
 */
public class Demo {

	
	public static void main(String[] args) {
	
		
		//summons a JFrame
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setLocation(20, 20);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//summons a JLabel
		JLabel label = new JLabel("hello");
		label.setLocation(100, 50);
		label.setSize(100, 20);
		frame.add(label);
		label.setVisible(true);
		
		
		//declares single dimension animation (in X or Y)
		StraightAnimation sAnimation = new StraightAnimation(label, 8, new Point(10, 50), true);
		
		//starts the single dimension animation
		sAnimation.start();
		
		
		
		//ObliqueAnimation and StraightAnimation CANNOT RUN TOGETHER AT ONCE
		
		
		
		//does multi (cross) dimension animation (X and Y at once)  --BETA--
		CrossAnimation cAnimation = new CrossAnimation(label, 5, new Point(600, 300));
		
		//starts the multi dimension animation
	//	cAnimation.start();
		
		
		frame.setVisible(true);
	}
}
