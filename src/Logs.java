import java.awt.*;

public class Logs {
    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width=156;
    public int height = 60;
    public Rectangle rec;

    public Logs(int mxpos, int mypos, int mdx, int mdy) {
        xpos = mxpos;
        ypos = mypos;
        dx = mdx;
        dy = mdy;
        rec = new Rectangle(xpos,ypos-50,width,100);
    }



public void methodPrintInfo(){
    System.out.println("Log:"+xpos+" "+ypos);
}

public void methodDrive(){
    if (xpos > 1000-width/2) {
        xpos = 0-width/2;
    }
        if (xpos < 0-width/2) {
            xpos = 1000+width/2;
        }
        if (ypos > 800) {
            ypos = 0;
        }
        if (ypos < 0) {
            ypos = 800;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;

        //methodPrintInfo();
    rec = new Rectangle(xpos,ypos-25,width,100);
    }
}




