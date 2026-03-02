import java.awt.*;

public class Road {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Rectangle rec;

    public Road(int mxpos, int mypos) {
        xpos = mxpos;
        ypos = mypos+25;
        width = 1000;
        height = 50;
        rec = new Rectangle(xpos,ypos,width,height);
        }

    }
