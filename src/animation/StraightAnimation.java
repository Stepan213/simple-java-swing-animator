package animation;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JComponent;
import javax.swing.Timer;


/**
 * Class for animating <code>JComponents</code>.
 * <p>
 * As name says, <code>straightAnimation</code> can only animate objects in one dimension (x or y).
 * 
 * @author Stepan214
 */
public class StraightAnimation {

	
	private int ddelay;
	
	
	private int counter;
	
	
	private int startingLoc;
	
	
	private int currentLocS;
	
	
	private int finalLocS;
	
	
	private Timer timer;
	
	
	private Point fLocation;
	
	
	/**
	 * 
	 * @param comp JComponent you want to animate.
	 * @param delay Delay in miliseconds between each step. Usually 5-100.
	 * @param finalLocation Final location of the JComponent. One of the parameters
	 * (x or y) HAS to be the same.
	 * @param smooth If the end is going to be smooth.
	 */
	public StraightAnimation(JComponent comp, int delay, Point finalLocation, boolean smooth) {
	
		
		//where the JComponent is going to be after the animation
		fLocation = finalLocation;
		
		
		//has to switch because of the anonymous inner class
		ddelay = delay;
		
		
		//current location 
		Point currentLocation = comp.getLocation();
		
		
		//determines if the object is going to move in x or in y
		final boolean xIsSame;
		if(currentLocation.x == fLocation.x) {
			xIsSame = true;
			currentLocS = currentLocation.y;
			finalLocS = finalLocation.y;
			startingLoc = comp.getLocation().y;
		}
		else {
			xIsSame = false;
			currentLocS = currentLocation.x;
			finalLocS = finalLocation.x;
			startingLoc = comp.getLocation().x;
		}
		
		
		//swing timer
		timer = new Timer(delay, null);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				if(currentLocation.x < finalLocation.x || currentLocation.y < finalLocation.y) {
					if(xIsSame) {
						comp.setLocation(currentLocation.x, currentLocS++);
					}
					else {
						comp.setLocation(currentLocS++, currentLocation.y);
					}
				}
				else {
					if(xIsSame) {
						comp.setLocation(currentLocation.x, currentLocS--);
					}
					else {
						comp.setLocation(currentLocS--, currentLocation.y);
					}
				}
				
				
				//checks if we're in the final location
				if(currentLocS == finalLocS) {
					timer.stop();
				}
				
				
				//makes smooth ending
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 80) {
					ddelay++;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 90) {
					ddelay += 2;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 95) {
					ddelay += 4;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 98) {
					ddelay += 6;
				}
				
				
				timer.setDelay(ddelay);
			}
		});
	}	
	
	
	//starts the animation
	public void start() {
		timer.start();
	}
	
	
	//starts the animation with new final location
	public void start(Point newLocation) {
		fLocation = newLocation;
		timer.start();
	}
}
