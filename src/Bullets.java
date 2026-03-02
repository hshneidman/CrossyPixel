import java.awt.*;

public class Bullets {
    public int xpos;
    public int ypos;
    public double dx;
    public double dy;
    public Rectangle rec;
    public int locationX;
    public int locationY;
    public Rectangle aimbox;

    public Bullets(int mxpos, int mypos, double mdx, double mdy) {
        xpos = mxpos;
        ypos = mypos;
        dx = mdx;
        dy = mdy;
        rec = new Rectangle(xpos-25, ypos-25, 50,50);
        aimbox = new Rectangle(xpos-50, ypos-50, 100,100);

    }
    public void move(){
        xpos = (int)(xpos + dx);
        ypos = (int)(ypos + dy);
        rec = new Rectangle(xpos-25, ypos-25, 50,50);

    }
    public void calcDxDy(){
        dx = (locationX-xpos);
        dy = (locationY - ypos);
        dy = dy/5;
        dx = dx/5;
    }
}
