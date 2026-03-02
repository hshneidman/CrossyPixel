import java.awt.*;

public class StartButton {
    public int xpos;
    public int ypos;
    public int width;
    public int height;
    public Rectangle rec;

    public StartButton(int mxpos, int mypos, int mwidth, int mheight){
        xpos=mxpos;
        ypos=mypos;
        width=mwidth;
        height=mheight;
        rec = new Rectangle(xpos,ypos,width,height);
    }

}
