import static java.lang.Math.floor;

public class Main {
    public static void main(String[] args) {
        BasicGameApp x = new BasicGameApp();
        new Thread(x).start();                 //creates a threads & starts up the code in the run( ) method

    }
    public Main(){
        //nothing happens here

    }
}

//"And because when I construct a pig, it will always have feet, ...or if I want to make it customizable..." - hales, 11/6