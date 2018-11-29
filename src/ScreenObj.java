import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/* this is the parent class for
 * all objects in the flappy Lion project
 */
public class ScreenObj {
	//data  I practice data hiding
	private int xLoc, yLoc;
	private int boundW, boundH;
	private Image [ ] myPics = null;
	private int maxWidth;  //maxPictureWidth
	private int origW, origH;
	private int currentPic;
	private GameWindow gameW;
	
	//Add coordinates for a collision box  for the game
	private int gameBBXExtra = 0, gameBBYExtra = 0;
	private int gameBBW = 0, gameBBH = 0;
	private boolean showGameBB = false;

	
	//constructor(s)
	public ScreenObj(
	        int x, 
			int y, 
			String fileName,
			String ext,
			int mW, 
			GameWindow gw, 
			int numOfPics) 
	{
	    
		xLoc = x;
		yLoc = y;
		gameW = gw;
		maxWidth = mW;

		readyPics(numOfPics, fileName, ext);
	}
	
	public void readyPics(int n, String fn, String ext) {	
		myPics = new Image[n];
		for(int i = 0; i < myPics.length; i++) {
			
			String fullString = "";
			if(i < 10) {
				fullString += fn + 0 + Integer.toString(i) + ext;
			} else {
			    fullString += fn + Integer.toString(i) + ext;
			}
			//System.out.println("Trying to open: " + fullString);
			
			boolean iLoaded = loadImage(fullString, i);
			if(iLoaded) 
			{
			    //System.out.println("Hi in SO-ReadyPics -- pic Loaded!");
        			setBW(myPics[i].getWidth(null));
        			setBH(myPics[i].getHeight(null));
        			origW = myPics[i].getWidth(null);
        			origH = myPics[i].getHeight(null); 
        			//System.out.println("Pic Width is " + myPics[i].getWidth(null));
        			//System.out.println("Pic Height is " + myPics[i].getHeight(null));
        			//do resizing if we need to....
        			if(getBW() > maxWidth) 
        			{
        			    int newH = (int)(
        			            maxWidth * ((getBH() * 1.0) / (getBW() * 1.0))
						);
				 //update Box Width and Height
				 setBW(maxWidth);
				 setBH(newH);
        			}
			}
		}
	} // end of the readyPics Method
	
	// METHODS  *****
	private boolean loadImage(String f, int whichPic) 
	{
	    // System.out.println("In loadImage whichPic is " + whichPic);
	    // System.out.println("In loadImage filename is " + f);
		boolean ok = false;
		try {
			ImageIcon ii = new ImageIcon(f);
			myPics[whichPic] = ii.getImage();
			ok = true;
		} catch(Exception e) {
			// System.out.println("Problem in loading " 
			//		+ f + " in loadImage...");
		}

		return ok;
	}
	
	
	public void drawMe(Graphics g) {
		
	    g.drawImage(myPics[currentPic],
                getXLoc()-(int)(getBW()/2), 
                getYLoc()-(int)(getBH()/2), 
                getXLoc()+(int)(getBW()/2), 
                getYLoc()+(int)(getBH()/2), 
                0, 
                0, 
                origW, 
                origH, 
                null);
		
	    //If you hate bounding boxes
		//g.setColor(Color.BLACK);
		//g.drawRect(xLoc-(int)(boundW/2), yLoc-(int)(boundH/2), boundW, boundH);
	    
	    if(showGameBB == true) {
	      g.setColor(Color.MAGENTA);
	      g.drawRect(
	              xLoc-(int)(boundW/2) + gameBBXExtra, 
	              yLoc-(int)(boundH/2) + gameBBYExtra, 
	              gameBBW, 
	              gameBBH 
	              ); 
	    }
		
		animate();
	}
	
	public void animate() {
		currentPic ++;
		if(currentPic >= myPics.length) {
			currentPic = 0;
		}
	}
	

	
	
	//accessor methods
	//accessor methods for xLoc and yLoc
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	
	public void setXLoc(int newX)
	{
		xLoc = newX;
	}
	
	public void setYLoc(int newY)
	{
		yLoc = newY;
	}
	// accessors for boundW and boundH
	public int getBW() {
		return boundW;
	}
	
	
	public int getBH() {
		return boundH;
	}
	
	public void setBW(int newBW) {
		boundW = newBW;
	}
	
	public void setBH(int newBH) {
		boundH = newBH;
	}
	
	//new Accessor methods
	public GameWindow getGameWindow() {
	    return gameW;
	}
	
	public int getMaxWidth() {
	    return maxWidth;
	}
	
	// New Accessor Methods for the Vac Class 
	public int getCurPic() {
	    return currentPic;
	}
	
	public void setCurPic(int newPicNum) {
	    currentPic = newPicNum;
	}
	
	public int getMyPicsLength() {
        return myPics.length;
    }
	// Special accessors-mutators for the gameBB
	
	public void showGameBB(boolean showIt) {
	    showGameBB = showIt;
	}
	
	public void setGameBBXExtra(int newEX) {
	    gameBBXExtra = newEX;
	}
	
	public void setGameBBYExtra(int newEY) {
	    gameBBYExtra = newEY;
	}
	
	public void setGameBBW(int newBBW) {
	        gameBBW = newBBW;
	}
	
	public void setGameBBH(int newBBH) {
           gameBBH = newBBH;
   }
	
	public int getGameBBXExtra() {
	    return gameBBXExtra;
	}
	
	public int getGameBBYExtra() {
	    return gameBBYExtra;
	 }
	
	 public int getGameBBW() {
	     return gameBBW;
	  }
	
	  public int getGameBBH() {
	      return gameBBH;
	  }
}









