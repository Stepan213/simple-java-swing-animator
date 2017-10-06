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

	
	//delay between steps
	private int delay;
	
	
	//current x or y location of the JComponent (changes as the animation moves)
	private int currentLocS;
	
	
	//x or y location where the JComponent will be
	private int finalLocS;
	
	
	private Timer timer;
	
	
	//location where the JComponent will be
	private Point finalLoc;
	
	
	/**
	 * @param comp JComponent you want to animate.
	 * @param delay Delay in miliseconds between each step. Usually 5-100.
	 * @param finalLocation Final location of the JComponent. One of the parameters
	 * (x or y) HAS to be the same, but the other one HAS to be DIFFERENT than the current
	 * location.
	 * @param smooth If the end is going to be smooth.
	 */
	public StraightAnimation(JComponent comp, int delayBetweenSteps, Point finalLocation, boolean smooth) {
	
		
		//where the JComponent is going to be after the animation
		finalLoc = finalLocation;
		
		
		//has to switch because of the anonymous inner class
		delay = delayBetweenSteps;
		
		
		//current location 
		Point currentLoc = comp.getLocation();
		
		
		//determines if the object is going to move in x or in y
		final boolean xIsSame;
		if(currentLoc.x == finalLoc.x) {
			xIsSame = true;
			currentLocS = currentLoc.y;
			finalLocS = finalLoc.y;
		}
		else {
			xIsSame = false;
			currentLocS = currentLoc.x;
			finalLocS = finalLoc.x;
		}
		
		
		//swing timer
		timer = new Timer(delay, null);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				if(currentLoc.x < finalLoc.x || currentLoc.y < finalLoc.y) {
					if(xIsSame) {
						comp.setLocation(currentLoc.x, currentLocS++);
					}
					else {
						comp.setLocation(currentLocS++, currentLoc.y);
					}
				}
				else {
					if(xIsSame) {
						comp.setLocation(currentLoc.x, currentLocS--);
					}
					else {
						comp.setLocation(currentLocS--, currentLoc.y);
					}
				}
				
				
				//checks if we're in the final location
				if(currentLocS == finalLocS) {
					timer.stop();
				}
				
				
				//makes smooth ending
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 80) {
					delay++;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 90) {
					delay += 2;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 95) {
					delay += 4;
				}
				if(smooth && Math.abs((currentLocS * 100.0f) / finalLocS) == 98) {
					delay += 6;
				}
				
				
				timer.setDelay(delay);
			}
		});
	}	
	
	
	//starts the animation
	public void start() {
		timer.start();
	}
}
