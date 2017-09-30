package animation;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JComponent;
import javax.swing.Timer;


/**
 * 
 * Class for animating JComponents.
 * 
 * As name says, crossAnimation can only animate objects in one dimension (x or y).
 * 
 * @author Stepan214
 */
public class CrossAnimation {

	
	int counter = 1;
	
	
	boolean ending = false;
	
	
	Point fLocation;
	
	
	Timer timer;
	
	
	/**
	 * @param comp Component you want to animate
	 * @param delay Speed of the animation (1-3) (1-slow, 3-fast)
	 * @param finalLocation Point, where to animate the JComponent
	 */
	public CrossAnimation(JComponent comp, int delay, Point finalLocation) {
	
		
		Point currentLocation = comp.getLocation();
		
	
		int differential;
	
		
		final boolean xIsBigger;
		
		
		Point distance = new Point(Math.abs(currentLocation.x - finalLocation.x), Math.abs(currentLocation.y - finalLocation.y));
		
		
		if(distance.x < distance.y) {
			differential = distance.y/distance.x;
			xIsBigger = false;
		}
		else {
			differential = distance.x/distance.y;
			xIsBigger = true;
		}
		
		timer = new Timer(delay, null);
		timer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Before animation ends
				if(!ending) {
					//both dimensions in final are bigger, will animate to bottom right
					if(currentLocation.x < finalLocation.x && currentLocation.y < finalLocation.y) {
						if(xIsBigger) {
							if(differential == counter) {
								comp.setLocation(currentLocation.x++, currentLocation.y++);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x++, currentLocation.y);
								counter++;
							}
						}
						
						
						else {
							if(differential == counter) {
								comp.setLocation(currentLocation.x++, currentLocation.y++);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x, currentLocation.y++);
								counter++;
							}
						}
					}
					
					
					//both dimensions in final are smaller, will animate to top left
					if(currentLocation.x > finalLocation.x && currentLocation.y > finalLocation.y) {
						if(xIsBigger) {
							if(differential == counter) {
								comp.setLocation(currentLocation.x--, currentLocation.y--);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x--, currentLocation.y);
								counter++;
							}
						}
						
						
						else {
							if(differential == counter) {
								comp.setLocation(currentLocation.x--, currentLocation.y--);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x, currentLocation.y--);
								counter++;
							}
						}	
					}	
					
					
					//x is bigger in final, y is smaller in final, will animate to top right
					if(currentLocation.x < finalLocation.x && currentLocation.y > finalLocation.y) {
						if(xIsBigger) {
							if(differential == counter) {
								comp.setLocation(currentLocation.x++, currentLocation.y--);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x++, currentLocation.y);
								counter++;
							}
						}
						
						
						else {
							if(differential == counter) {
								comp.setLocation(currentLocation.x++, currentLocation.y--);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x, currentLocation.y--);
								counter++;
							}
						}	
					}
					
					
					//y is bigger in final, x is smaller in final, will animate to bottom left
					if(currentLocation.x > finalLocation.x && currentLocation.y < finalLocation.y) {
						if(xIsBigger) {
							if(differential == counter) {
								comp.setLocation(currentLocation.x--, currentLocation.y++);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x--, currentLocation.y);
								counter++;
							}
						}
						
						
						else {
							if(differential == counter) {
								comp.setLocation(currentLocation.x--, currentLocation.y++);
								counter = 1;
							}
							else{
								comp.setLocation(currentLocation.x, currentLocation.y++);
								counter++;
							}
						}	
					}
				}
				
				//almost in final location, but y has to be slightly moved
				if(comp.getLocation().getX() == finalLocation.x && comp.getLocation().getY() != finalLocation.y) {
					comp.setLocation(currentLocation.x, currentLocation.y++);
					ending = true;
				}
				
				//almost in final location, but x has to be slightly moved
				if(comp.getLocation().getX() != finalLocation.x && comp.getLocation().getY() == finalLocation.y) {
					comp.setLocation(currentLocation.x++, currentLocation.y);
					ending = true;
				}
				
				//in final location, animation stops
				if(comp.getLocation().getX() == finalLocation.x && comp.getLocation().getY() == finalLocation.y) {
					timer.stop();
				}
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
