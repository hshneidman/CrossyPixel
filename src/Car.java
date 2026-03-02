import java.awt.*; //this may be in the wrong spot ngl

public class Car {
    //ok so i just realized i don't actually have a constructor in here
    //i don't quite understand why i need one? but ill add it


    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width=100;
    public int height = 75;
    public Rectangle rec;


    public Car(int mxpos, int mypos, int mdx, int mdy) {
        xpos = mxpos;
        ypos = mypos;
        dx = mdx;
        dy = mdy;
        rec = new Rectangle(xpos,ypos,width,height);
    }

    public void methodPrintInfo() {
        System.out.println("Car: " + xpos + " " + ypos);
    }

    public void methodDrive() {
            if (xpos > 1000) {
                xpos = 0;
            }
            if (xpos < 0) {
                xpos = 1000;

            }
            if (ypos > 600) {
                ypos = 600;
            }
            if (ypos < 0) {
                ypos = 300;
            }

           // methodPrintInfo();
            xpos = xpos + dx;
            ypos = ypos + dy;

            rec = new Rectangle(xpos,ypos,width,height);





    }


}