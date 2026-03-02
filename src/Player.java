import java.awt.*;

public class Player {
    boolean isAlive;
    boolean right;
    boolean left;
    boolean up;
    boolean down;
    public int width;
    public int height;
    public boolean onLog;
    public boolean PlayerWin;
    public int points;
    public int coinpoints;
    public int totalpoints;
    public boolean facingLeft;
    public boolean facingDown;

    public int xpos;
    public int ypos;
    public double dx;
    public double dy;
    public Rectangle rec;
    public Image pic;
    public double gravityAffect;



    public Player(int mxpos, int mypos,int dxy, Image mpic) {
        isAlive = true;
        dx = dxy;
        dy=dxy;
        xpos = mxpos;
        ypos = mypos;
        width = 100;
        height = 100;
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);
        PlayerWin = false;
        points = 0;
        totalpoints = 0;
        coinpoints = 0;
        pic = mpic;
        facingLeft = false;
        facingDown = false;
        gravityAffect = 0;
    }

    public void gravityAffect(){
        ypos = (int)(ypos + gravityAffect);
    }
    public void level6jump(){
        gravityAffect = -2.5;
    }

    public void methodPlayerUp(){
        dy = 70;
        ypos = (int)(ypos - dy);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);
        dy = 0;


    }
    public void DriftPlayerUp(){
        dy += 1;
        ypos = (int)(ypos - dy);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);


    }

    public void DriftPlayerRight(){
        dx += 1;
        xpos = (int)(xpos + dx);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);


    }

    public void DriftPlayerLeft(){
        dx += 1;
        xpos = (int)(xpos - dx);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);


    }



    public void DriftPlayerDown(){
        dy += 1;
        ypos = (int)(ypos + dy);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);

    }

    public void methodPlayerRight() {
        dx = 70;
        xpos = (int)(xpos + dx);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);
        dx = 0;
    }

    public void methodPlayerLeft() {
        dx = 70;
        xpos = (int)(xpos - dx);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);
        dx = 0;
    }


    public void methodPlayerDown(){
        dy = 70;
    ypos = (int)(ypos + dy);
        rec = new Rectangle(xpos+20,ypos+20,width-40,height-40);
        dy = 0;

}
}
