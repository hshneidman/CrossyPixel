import java.awt.*;

public class CharSelect {
    public int height;
    public int width;
    public int xpos;
    public int ypos;
    public Rectangle rec;
    public int levelunlock;
    public boolean unlocked;

    public CharSelect(int mxpos, int mypos, int mwidth, int mheight, int mlevelunlock, boolean munlocked){
        height = mheight;
        width = mwidth;

        xpos = mxpos;
        ypos = mypos;
        rec = new Rectangle(xpos,ypos,width,height);
        levelunlock = mlevelunlock;
        unlocked = munlocked;

    }
}
