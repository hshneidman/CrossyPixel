import java.awt.*;

public class Obstacle {
    public int xpos;
    public int ypos;
    public int height;
    public int width;
    public Rectangle rec;
    public int r,g,b;

    public Obstacle(int mxpos, int mypos, int mwidth, int mheight, int mr, int mg, int mb){
        xpos = mxpos;
        ypos = mypos;
        height = mheight;
        width = mwidth;
        r = mr;
        g = mg;
        b = mb;
        rec = new Rectangle(xpos, ypos, width, height);


    }


}
