

public class Egg extends Obstacle {

    
    long frameCount = 0;
    int frameSpeed = 5;  // Egg will wait 5 frames before animating.
 
    public Egg(int x, int y, String fileName, String ext, int mW, GameWindow gw, int numOfPics) {
        super(x, y, fileName, ext, mW, gw, numOfPics);
        // TODO Auto-generated constructor stub
    }
    
    
    public void animate() {
        frameCount++;
        
        if(frameCount % frameSpeed == 0) {
            super.setCurPic(super.getCurPic() + 1);
            
            if(super.getCurPic() >= super.getMyPicsLength()) {
                super.setCurPic(0);
            }
        }  
    }

    
}
