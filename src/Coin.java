import java.awt.*;

public class Coin {
    int xpos;
    int ypos;
    int sidelength;

    boolean taken;
    public Rectangle rec;


    public Coin(int mxpos, int mypos,int msidelength){
        taken = false;
        xpos = mxpos;
        ypos = mypos;
        sidelength = msidelength;
        rec = new Rectangle(xpos, ypos,sidelength,sidelength);
    }

}
