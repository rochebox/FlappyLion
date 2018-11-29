import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JPanel implements ActionListener, KeyListener {
   
    public static final int SPACE_KEY = 32;
    public static final int L_GRAVITY = 4;  //in pixels per frame
 
    private static final long serialVersionUID = 1L;
    //data e
	private int windowW, windowH;
	private Color bColor = new Color(107, 255, 247);
	private JFrame myFrame = null;
	//private ScreenObj test1;
	//private Cloud c1Test;  //make place for object..
	private Cloud[ ] cList;
	private Lion alexLion;
	private Vac vac;
	
	
	
	private Timer t;
	
	
	
	
	//Constructor(s)
	public GameWindow(int w, int h) {
		windowW = w;
		windowH = h;
		
		this.setSize(windowW, windowH);
		this.setBackground(bColor);
	}
	
	public GameWindow(int w, int h, JFrame f) {
		this(w, h);
		myFrame = f;
		
		makeAllClouds( (int)(Math.random() * 5) + 5);
		//makeAllClouds(1);  <-- for testing
		
		makeObstacles();
		//let's activate the keyListener
		addKeyListener(this);
		setFocusable(true);
		
		t = new Timer(50, this);	
		t.start();	
	}
	
	public void makeObstacles() {
	    makeAlexLion();
	    int theMaxW = (int) (windowW * 0.14);
        int frameNum = 3;
        int vacX = (int)(windowW) - theMaxW;
        int vacY = windowH - (int)(theMaxW * 1.45);
        vac = new Vac (
                vacX,  /* OK */
                vacY,   /* using the new vacY */
                "vac/Vach",
                ".png", 
                theMaxW, 
                this, 
                frameNum); 
        vac.setMoveSpeed(25);
        vac.showGameBB(true);
        vac.setGameBBXExtra( (int) (vac.getBW()  * 0.5) );
        vac.setGameBBYExtra( (int) (vac.getBH()  * 0.05) );
        vac.setGameBBW( (int) (vac.getBW()  * 0.30) );
        vac.setGameBBH( (int) (vac.getBH()  * 0.95) );
        
	}
	
	public void makeAlexLion() 
	{
	    
	    int theMaxW = (int) (windowW * 0.3);
	    int frameNum = 16;
	    alexLion = new Lion (
	            (int)(windowW/2),  /* OK */
	            windowH - (int)(theMaxW/2), 
	            "LionPics/frame_",
	            ".png", 
	            theMaxW, 
	            this, 
	            frameNum);
	    
	    alexLion.showGameBB(true);
	    alexLion.setGameBBXExtra( (int) (alexLion.getBW()  * 0.35) );  //David Ragone's numbers
	    alexLion.setGameBBYExtra( (int) (alexLion.getBH()  * 0.20) );
	    alexLion.setGameBBW( (int) (alexLion.getBW()  * 0.45) );
	    alexLion.setGameBBH( (int) (alexLion.getBH()  * 0.60) );
	}
	
	public void makeAllClouds(int howMany) {
		//allocate memory....
		int topDown = 150;  //how Far Down from the Top of Screen
		cList = new Cloud[howMany];
		//makeClouds
		
		for(int i = 0; i < howMany; i++) {

			cList[i] = new Cloud (
			        windowW + (int)(Math.random() * 200),  /* cloudX */
			        topDown + (int)(Math.random() * 100) - 50, /* cloudY */
			        "cloudHappy",
			        ".png",
			        (int)(Math.random()*150) + 70,
			        this,
			        1 );	        
		}		
	}
	
	//Behaviors-Methods
	public void paintComponent(Graphics g) {
		windowW = myFrame.getWidth();
		windowH = myFrame.getHeight();
		g.setColor(bColor);
		g.fillRect(0, 0, windowW, windowH);

		drawClouds(g);
		alexLion.drawMe(g);
		vac.drawMe(g);
		
	}
	
	public void drawClouds(Graphics g) {
		
		for(int i = 0; i < cList.length; i++) {
			cList[i].drawMe(g);
		}
		
	}
	
	public void checkCollisions() {
	    if(vac.collision(alexLion) == true) 
	    {
	        System.out.println("HEY THERE IS A COLLISION");
	    } else {
	        System.out.println();
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		moveClouds();
		moveAllObstacles();
		checkCollisions();
		repaint();	
	}
	
	public void moveAllObstacles() {
	    vac.moveObstacle();
	}
	
	public void moveClouds() {
		for(int i = 0; i < cList.length; i++) {
			cList[i].moveCloud();
		}
	}

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        //System.out.println("Hey you typed a key");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
        //System.out.println("Hey you pressed a key");
        //System.out.println("Key has keyCode of " +  e.getKeyCode());
        // System.out.println("Key has Character of " +  e.getKeyChar());
        //System.out.println();
        
        if(e.getKeyCode()== this.SPACE_KEY) {
            alexLion.jump();
            repaint();
        }
        //Change 4 move repaint to inside the if...
        // repaint();   
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
       // System.out.println("Hey you released a key");
    }
}
