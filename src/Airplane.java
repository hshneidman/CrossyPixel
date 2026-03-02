import java.awt.*;

public class Airplane {
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    boolean onGround;

    public Airplane(int mxpos, int mypos, int mdx, int mdy) {
        xpos = mxpos;
        ypos = mypos;
        dx = mdx;
        dy = mdy;
    }


    public void methodPrintInfo() {
        System.out.println("Airplane: " + xpos + " " + ypos);
    }

    public void methodDrive() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        if (xpos > 500) {
            xpos = 0;
        }
        if (xpos < 0) {
            xpos = 500;

        }
        if (ypos > 500) {
            ypos = 0;
        }
        if (ypos < 0) {
            ypos = 500;
        }

        methodPrintInfo();




    }


}
//hw: spend 35 minutes doing this list (you don't have to do all of it)
// change astro's image, make another astronaut, make it bounce, make an asteroid class that wraps