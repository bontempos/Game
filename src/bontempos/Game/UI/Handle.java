package bontempos.Game.UI;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import processing.event.MouseEvent;


public class Handle{
	
	public float x;
	public float y;
	Rectangle area;
	boolean selected;
	
	
	
	final private static Dimension areaSize = new Dimension(15,15);

	public Handle(Point p) {
		
	}
	public Handle() {
		
	}

	public void mouseEvent(MouseEvent event) {
		  int x = event.getX();
		  int y = event.getY();

		  switch (event.getAction()) {
		    case MouseEvent.PRESS:
		      // do something for the mouse being pressed
		      break;
		    case MouseEvent.RELEASE:
		      // do something for mouse released
		      break;
		    case MouseEvent.CLICK:
		      // do something for mouse clicked
		      break;
		    case MouseEvent.DRAG:
		      // do something for mouse dragged
		      break;
		    case MouseEvent.MOVE:
		      // do something for mouse moved
		      break;
		  }
		}
	
	
}


//getBounds()

//contains(Point p)

//contains(int X, int Y, int W, int H)

//setFrameFromCenter
//get center