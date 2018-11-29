
public class Obstacle extends ScreenObj {
    
    private int moveSpeed;  //we like data hiding

    public Obstacle(int x, int y, String fileName, String ext, int mW, GameWindow gw, int numOfPics) {
        super(x, y, fileName, ext, mW, gw, numOfPics);
        // TODO Auto-generated constructor stub
        
        moveSpeed = (int) (Math.random() * 10) + 3;
    }
    
    // recycling of old movecloud....
    public void moveObstacle() {
        
        super.setXLoc(super.getXLoc()-moveSpeed); 
        
        if(super.getXLoc() < -1 * (super.getBW() + 200) )
        {
            super.setXLoc(super.getGameWindow().getWidth() 
                    + super.getBW() 
                    + (int)(Math.random() * 200) 
                    );
            //moveSpeed = (int) (Math.random() * 10) + 3;
        }
        //System.out.println("Hi from moveCloud xLoc is " + super.getXLoc());   
    }
    
    public void setMoveSpeed(int nSpeed) {
        moveSpeed = nSpeed;
    }
    
    public boolean collision(Obstacle theLion) {
        boolean didC = false;
        // get all the x coordinate info you need....
        int theLionX0 = theLion.getXLoc() + theLion.getGameBBXExtra();
        int theLionX1 = theLionX0 + theLion.getGameBBW();      
        int myX0 = this.getXLoc() + this.getGameBBXExtra();
        //int myX1 = theLionX0 + this.getGameBBW();  **** THIS IS WRONG
        int myX1 = myX0 + this.getGameBBW();
        
        //now adding y
        int theLionY0 = theLion.getYLoc() + theLion.getGameBBYExtra();
        int theLionY1 = theLionY0 + theLion.getGameBBH();      
        int myY0 = this.getYLoc() + this.getGameBBYExtra();
        //int myY1 = theLionY0 + this.getGameBBH(); **** THIS IS WRONG
        int myY1 = myY0 + this.getGameBBH();
         
        if(
                /* our formulas are backwards...
                theLionX0 > myX0 && myX0 < theLionX1 ||
                theLionX0 > myX1 && myX1 < theLionX1 
                */
                
                theLionX0 < myX0 && myX0 < theLionX1 ||
                theLionX0 < myX1 && myX1 < theLionX1 
                ) 
        {
            if(
                   /* (theLionY0 > myY0 && myY0 < theLionY1 ||
                    theLionY0 > myY1 && myY1 < theLionY1) 
                    ||
                    (myY0 > theLionY0 && theLionY0 < myY1 ||
                     myY0 > theLionY1 && theLionY1 < myY1)  */
                    /* our formula is backwards.... */
                    
                    (theLionY0 < myY0 && myY0 < theLionY1 ||
                    theLionY0 < myY1 && myY1 < theLionY1) 
                    ||
                    (myY0 < theLionY0 && theLionY0 < myY1 ||
                     myY0 < theLionY1 && theLionY1 < myY1) 
                
                    ) 
            {
              
            didC = true;
            //System.out.println("We have an  intersect..." );
        }
       }

        return didC;
    }
    
    
    
    


}
