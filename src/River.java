import java.awt.*;

public class River {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Rectangle rec;

    public River(int mxpos, int mypos, int mwidth, int mheight) {
        xpos = mxpos;
        ypos = mypos;
        width = mwidth;
        height = mheight;
        rec = new Rectangle(xpos,ypos,width,height);
        }

    }
