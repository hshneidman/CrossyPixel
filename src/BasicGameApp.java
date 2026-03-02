//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import javax.sound.sampled.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 720;
    int pauseSoundTimer;
    int rocketShipypos = 100;
    int bugXpos = -100;

    int catXpos = 650;
    int catYpos = 400;
    int catHeight = 130;
    int catWidth = 200; //wait theres got to be a better way im doing this wrong aren't i

    int cattailxpos = 950;

    int pixelRainypos = 800;


    public Rectangle mouseBox;
    int x; //helps with the flip sticky note animation and flappy fail animation
    int y;// helps with the rocket ship take off animation
    int r;
    boolean stickyNoteFlipped;
    boolean level6animation;
    boolean cakeEaten;
    boolean rocketTakeOff;
    boolean gameStarted;
    boolean catJumped;
    boolean catRan;

    boolean level6failed;


    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public Clip clip[];
    public Bullets bullet[];
    boolean playsound = false;
    boolean flipSticky;
    boolean eatCake;
    //boolean rocketLaunch;
    boolean musicOff = false;
    boolean turnMusicOn;
    boolean IsRunning = false;
    boolean playerCantMove;
    boolean inSpaceMode;
    boolean driftNow;
    public boolean secretsEnabled = false; //this is false for the shared version only. im making an easter egg but its kind of broken rn


    int z; //these two are for cake animation
    int dialogueBeat = 1;
    public BufferStrategy bufferStrategy;
    public Image laptop;
    public Image bigScreen;
    public Image bug;
    public Image lock;
    public Image startbutton;
    public Image coin;
    public Image Car2pic;
    public Image Logimage;
    public Image Car1pic;
    public Image Background;
    public Image Background2;
    public Image Background3;
    public Image Background4;
    public Image Background5;
    public Image pixelrain;
    int timepause;
    int leveledup = 0;
    int pausetime;
    int savedPlayerCharacter0;
    int savedPlayerCharacter1;

    public Obstacle[] level6Obs;


    public Image Playerimage0;
    public Image Playerimage1;

    //public Image Player;

    boolean transitionvarib;
    boolean stopsong;
    int Timer = 0;
    int numberOfCars;
    Logs theOneThePlayerIsOn;
    int level;
    StartButton startbutton1;
    boolean twoPlayers;
    int playerlength;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    Car[] Carlist;
    Color[] myColor;
    //Car Car2;
    //Logs Log1;
    Logs[] Log2;
    Logs[] Loglevel;
    Player[] Player1;
    Player Player2;
    Road[] Road1;
    //Road Road2;

    River River1;
    River[] River2;
    CharSelect[] CharSelect1;
    Coin Coin1;
    public Font bigFont = new Font("Serif", Font.BOLD, 18);
    public Font biggerFont = new Font("DialogInput", Font.BOLD, 30);

    Rectangle changePlayerRec = new Rectangle(100,150,50,50);
    Rectangle turnSoundOff = new Rectangle(600,300,50,50);

    Image changePlayerImage;
    Image turnSoundOffImage;

    boolean musicOn = true;


    Image stickyNote; //these are all the dev lair props
    Image couch;
    Image cat;
    Image sign;
    boolean signLit;
    Image cake;
    Image rocketShip;
    Image Z;

    Image cautionTape;
    Image Uturn;
    Image stripeSign;
    Image noEntry;

    Image cattail;



    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp myApp = new BasicGameApp();   //creates a new instance of the game
        new Thread(myApp).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {



        level6Obs = new Obstacle[40];
        for(int x = 0; x < level6Obs.length; x++){
            if(x <= 10){
                if(x%2==0){
                    level6Obs[x] = new Obstacle(600*x + 1000, 0,100,20 + (int)(300*Math.random()),50,200,200);
                }else {
                    level6Obs[x] = new Obstacle(600 * x + 1000, 700 - (int) (300 * Math.random()), 100, 1000, 50, 200, 200);
                }
            }else if (x <= 20) {
                if (x % 2 == 0) {
                    level6Obs[x] = new Obstacle(575 * x + 1000, 0, 100, 20 + (int) (400 * Math.random()), 100, 100, 150);
                } else {
                    level6Obs[x] = new Obstacle(575 * x + 1000, 700 - (int) (400 * Math.random()), 100, 1000, 100, 100, 150);
                }
            }else if(x < level6Obs.length-1){
                if (x % 2 == 0) {
                    level6Obs[x] = new Obstacle(550 * x + 1000, 0, 100, 20 + (int) (400 * Math.random()), 200, 100, 150);
                } else {
                    level6Obs[x] = new Obstacle(550 * x + 1000, 700 - (int) (400 * Math.random()), 100, 1000, 200, 100, 150);
                }
            }else{
                level6Obs[x] = new Obstacle(550*x + 1000, 0,100,1000,100,200,150);

            }
        }




        setUpGraphics();
        clip = new Clip[2];
        bullet = new Bullets[100];
        for(int x =0; x < bullet.length; x++){
            bullet[x] = new Bullets(500,300,0,0);
        }
        Player1 = new Player[2]; //[2]
            Player1[0] = new Player(500, 670, 0, Playerimage0);
            if(twoPlayers==true){
               Player1[1] = new Player(600, 670, 0, Playerimage0);
               playerlength = 2;
            }else{
                playerlength = 1;
            }
        try {
            clip[0] = AudioSystem.getClip();
            clip[1] = AudioSystem.getClip();

        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }


        myColor = new Color[6];
        myColor[0] = Color.gray;
        myColor[1] = Color.pink;
        myColor[2] = Color.yellow;
        myColor[3] = Color.orange;
        myColor[4] = Color.white;
        myColor[5] = Color.darkGray;

        startbutton1 = new StartButton(300, 50, 400, 200);



        //variable and objects
        //create (construct) the objects needed for the game and load up
        Car2pic = Toolkit.getDefaultToolkit().getImage("ActualCar2pic.png"); //load the picture
        Logimage = Toolkit.getDefaultToolkit().getImage("LOG REAL!! IMAGE.png"); //load the picture
        Car1pic = Toolkit.getDefaultToolkit().getImage("Car1 pic.png"); //load the picture
        Background = Toolkit.getDefaultToolkit().getImage("backgroundREALimage.png");
        Background2 = Toolkit.getDefaultToolkit().getImage("background levels.png");
        Background3 = Toolkit.getDefaultToolkit().getImage("background3.png");
        Background4 = Toolkit.getDefaultToolkit().getImage("background4.png");
        Background5 = Toolkit.getDefaultToolkit().getImage("background5.png");
        pixelrain = Toolkit.getDefaultToolkit().getImage("pixelrain.png");

        coin = Toolkit.getDefaultToolkit().getImage("coinimage.png");
        lock = Toolkit.getDefaultToolkit().getImage("lockimage.png");
        changePlayerImage = Toolkit.getDefaultToolkit().getImage("1player.png");
        turnSoundOffImage = Toolkit.getDefaultToolkit().getImage("turnSoundOffimage.png");

        //Player = Toolkit.getDefaultToolkit().getImage("2characterselect.png");
        Playerimage0 = Toolkit.getDefaultToolkit().getImage("2characterselect.png");
        Playerimage1 = Toolkit.getDefaultToolkit().getImage("2characterselect.png");

        bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen.png");
        laptop = Toolkit.getDefaultToolkit().getImage("laptop.png");
        bug = Toolkit.getDefaultToolkit().getImage("bug.png");



        sign = Toolkit.getDefaultToolkit().getImage("signImage.png");
        cake = Toolkit.getDefaultToolkit().getImage("cakeImage.png");
        rocketShip = Toolkit.getDefaultToolkit().getImage("rocketShip.png");
        cat = Toolkit.getDefaultToolkit().getImage("catImage.png");
        couch = Toolkit.getDefaultToolkit().getImage("couchImage.png");
        stickyNote = Toolkit.getDefaultToolkit().getImage("stickyNoteImage.png");
        Z = Toolkit.getDefaultToolkit().getImage("zimage.png"); //the z above the cat

        stripeSign = Toolkit.getDefaultToolkit().getImage("stripesign.png");
        Uturn = Toolkit.getDefaultToolkit().getImage("uturn.png");
        noEntry = Toolkit.getDefaultToolkit().getImage("noentry.png");
        cautionTape = Toolkit.getDefaultToolkit().getImage("devsonly.png");

        cattail = Toolkit.getDefaultToolkit().getImage("cattail1.png");

        //System.out.println(Playerimage0);
        //System.out.println(Playerimage1);

        startbutton = Toolkit.getDefaultToolkit().getImage("startbuttonimage.png");

        numberOfCars = 4;
        pausetime = 1;

        //Car1 = new Car(500, ((int)(3 * Math.random()))*200+300, 2, 0);
        //Car2 = new Car(500,  ((int)(3 * Math.random()))*200+300, 2, 0);

        Carlist = new Car[numberOfCars];
        /*for(int x=0;x<Carlist.length;x++) {
            Carlist[x] = new Car((int)(1000*Math.random()), (700*x*(int)(Math.random()*4)/5)+120, 1, 0);
        }//120, 260, 400, 540*/


        for (int x = 0; x < Carlist.length; x++) {

            Carlist[x]= new Car((int) (1000 * Math.random()), (700 * x / 5) + 120, (int) (3 * Math.random()+1), 0);
            //System.out.println(Carlist[1].dy);

        }//



        //Log1 = new Logs(500, (int) (20) + 25, 1, 0);
        Log2 = new Logs[4];
        for (int x = 0; x < Log2.length; x++) {
            Log2[x] = new Logs((700 * x / 4), 45, 1, 0);
            //System.out.println(Log2[1].dy);

        }
        Loglevel = new Logs[4];
        for(int x = 0; x < Loglevel.length; x++){
            Loglevel[x] = new Logs((int)(1000 * Math.random()), (700 * x / 5) + 120, (int) (3 * Math.random()+1), 0);
        }
        //Car1.methodPrintInfo();

        Road1 = new Road[numberOfCars];
        for (int x = 0; x < Carlist.length; x++) {
            Road1[x] = new Road(0, Carlist[x].ypos);
        }

        //Road1 = new Road(0,Car1.ypos);
        //Road2 = new Road(0,Car2.ypos);
        River1 = new River(0, Log2[0].ypos, 1000, 70);

        River2 = new River[Loglevel.length];
        for (int x = 0; x < River2.length; x++) {
            River2[x] = new River(0, Loglevel[x].ypos,1000,70);
        }


        savedPlayerCharacter0 = 2;
        savedPlayerCharacter1 = 2;

        //Car2.methodFillInfo(500,  ((int)(3 * Math.random()))*200+300, 2, 0);
        //Car2.methodDrive(50);
        //Log1.methodFillInfo(500, (int) (20) + 25, 1, 0);
        //Log2.methodFillInfo(700, (int) (20) + 25, 1, 0);

        level = 0;

        CharSelect1 = new CharSelect[6];
        for (int i = 0; i < 4; i++) {
            CharSelect1[i] = new CharSelect(i * 200 + 150, 500, 100, 100,0,true);
        }
        for (int i = 4; i < CharSelect1.length; i++){
            CharSelect1[i] = new CharSelect((i%4*600)+150,300,100,100,i*4,false); //change i*4 to i to lower point requirements for testing purposes
        }
        Coin1 = new Coin(10000,10000,100);





    }// BasicGameApp()


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {
            if(playerCantMove == false) {
                gravity();
            }
            //System.out.println(level6Obs[39].rec.x);
            if(gameStarted==false) {
                //playIntro("BeepBoxlevel1.wav");
                gameStarted = true;


            }else{
                //System.out.println(Timer);
                //System.out.println(totalpoints+" "+points); //THIS IS NOT WORKING AHAHGHGAHGHAGHGH
                //checkSound();
                //System.out.println(level);
                if (flipSticky == true) {
                    if (x == Timer - 30) {
                        if (stickyNoteFlipped == false) {
                            stickyNote = Toolkit.getDefaultToolkit().getImage("fullFlipped.png");
                            stickyNoteFlipped = true;
                        } else {
                            stickyNote = Toolkit.getDefaultToolkit().getImage("stickyNoteImage.png");
                            stickyNoteFlipped = false;
                        }

                    }
                }

                if (catJumped == true) {
                    playerCantMove = true;
                    Player1[0].dx = 0;
                    Player1[0].dy = 0;
                    System.out.println(playerCantMove);
                    if (r == Timer - 100) {
                        catWidth += 20;

                        cat = Toolkit.getDefaultToolkit().getImage("catRun.png");
                        catXpos += 150;
                        catRan = true;
                    }
                }
                if(catRan == true){
                    if(r == Timer - 150){
                        level = 6;
                        Player1[0].ypos = 0;
                        catRan = false;
                        catJumped = false;
                        catreset();
                        playerCantMove = false;


                    }
                }


                if (eatCake == true) {
                    if (z == Timer - 30) {
                        if (cakeEaten == false) {
                            cake = Toolkit.getDefaultToolkit().getImage("cakeEaten.png");
                            cakeEaten = true;
                        } else {
                            cake = Toolkit.getDefaultToolkit().getImage("cakeImage.png");
                            cakeEaten = false;
                        }

                    }


                }

                if (rocketTakeOff == true) {
                    if (y == Timer - 100) {
                        playerReset();
                        level = 0;
                        playerCantMove = false;
                        rocketShipypos = 100;
                        System.out.println("hi why is this not working");
                        driftNow = false;
                        rocketShip = Toolkit.getDefaultToolkit().getImage("rocketShip.png");
                        rocketTakeOff = false;
                    }else{ //this is for the rocket launch animation
                        if(level == 5) {
                            playerCantMove = true;
                            rocketShipypos -= 5;
                            Player1[0].ypos -= 5;
                            Player1[0].xpos = 800;
                        }
                    }

                }
                checkDrift();
                playSound1(0);
                if ((Timer) % 2000 == 0 + (pauseSoundTimer % 2000)) {
                    playSound2(0);
                }


                //System.out.println("play sound is happening");

            /*if (musicOn==false) {
                clip.stop();
                //clip.close();   //start playing the sound
                System.out.println("jeez why");
            }*/

                //checkSoundOff();
                // System.out.println(clip.isOpen());
                //System.out.println(clip.isRunning());


                checkStart();
                checkCrash();

                if (level == 0) {
                    //System.out.println("Timer"+Timer);

                    renderBeginning();
                    checkCharSelect();
                    checkOutsideBeginning();


                }
                if (level == 1) {


                    checkOutside();

                    checkCoin();

                    checkLog();

                    moveAll();  //move all the game objects
                    checkTransition();
                    checkTimer();

                    //System.out.println(Timer);
                    checkWin();

                    renderLevel1();  // paint the graphics
                    checkPoint();

                }
                if (level == 2) {
                    renderCredits(2);
                }
                if (level == 3) {
                    renderCredits(3);
                }
                if (level == 4) {
                    renderCredits(4);

                }
                if (level == 5) {
                    renderCredits(5);
                }if (level == 6){
                    renderCredits(6);
                    obstaclesMove();
                    if(level6failed == true){
                        x = Timer;
                        level6animation = true;
                        level6failed = false;
                    }
                    checkFlappyFail();
                    checkFlappyOutOfBounds();
                    //System.out.println("hello");
                }
                if (level == 7){
                    renderCredits(7);
                    if(dialogueBeat < 1) {
                        checkOutsideBox();
                    }else{
                        checkOutsideLevel7();
                    }
                }

                if (level == 8){
                    renderCredits(8);
                }


                pause(pausetime); // sleep for 10 ms
            }
        }
    }
    public void catreset(){
        catXpos = 650;
        catYpos = 400;
        catHeight = 130;
        catWidth = 200;
        cat = Toolkit.getDefaultToolkit().getImage("catImage.png");
        cattailxpos = 950;



    }

    public void level6reset(){
        for(int x = 0; x < level6Obs.length; x ++){
            if(x < 10){
                level6Obs[x].rec.x = 600*x + 1000;
            }else if (x < 20) {
                level6Obs[x].rec.x = 550 * x + 1000;
            }else if(x < level6Obs.length-1) {
                level6Obs[x].rec.x = 525 * x + 1000;
            }else{
                level6Obs[x].rec.x = 550*x + 1000;

            }
        }
    }

    public void checkFlappyOutOfBounds(){
        if(level == 6 && level6animation == false){
            if(Player1[0].ypos < 0){
                Player1[0].ypos = 0;
            }
            if(Player1[0].ypos > 720){
                level6failed = true;
            }
        }
    }

    public void checkFlappyFail(){
        if(level6animation == true){
            playerCantMove = true;
            if(Timer - 50 > x){
                Player1[0].ypos -= 3;
                pixelRainypos -= 5;

            }
            if(pixelRainypos < 0) {
                playerReset();
                level = 0;
                driftNow = false;
                playerCantMove = false;
                level6animation = false;
                pixelRainypos = 800;
                level6reset();
            }
        }
    }

    public void checkPoint(){
        //System.out.println("gonow");
    }
    public void checkTransition(){
        for(int i = 0; i<1; i++) {
            //System.out.println(timepause);

            for(int x = 0; x<playerlength; x++) {
                if(playerlength>1) {
                    if (Player1[1].isAlive == false && Player1[0].isAlive == false) {
                        //System.out.println("im dead");
                        //this isn't working at all


                        break;

                    } else {

                        timepause = Timer;
                    }
                }else{
                    //System.out.println("one player");
                    if (Player1[0].isAlive == false) {
                        break;

                    } else {
                        timepause = Timer;
                    }


                }
            }


        }

    }
    public void checkOutsideLevel7() {
        for(int x =0; x<playerlength; x++) {
            if (Player1[x].xpos > 1000) {
                level = 8;
            }
            if (Player1[x].xpos < 0) {
                Player1[x].xpos = 0;
                Player1[x].dx = 0;

            }
            if (Player1[x].ypos > 670) {
                Player1[x].ypos = 670;
                Player1[x].dy = 0;
            }

            if (Player1[x].ypos < 0) {
                Player1[x].ypos = 0;
                Player1[x].dy = 0;

            }
        }
    }


    public void checkOutsideBox() {
        for(int x =0; x<playerlength; x++) {
            if (Player1[x].xpos > 950) {
                Player1[x].xpos = 950;
                Player1[x].dx = 0;

            }
            if (Player1[x].xpos < 0) {
                Player1[x].xpos = 0;
                Player1[x].dx = 0;

            }
            if (Player1[x].ypos > 670) {
                Player1[x].ypos = 670;
                Player1[x].dy = 0;
            }

            if (Player1[x].ypos < 0) {
                Player1[x].ypos = 0;
                Player1[x].dy = 0;

            }
        }
    }



    public void checkOutside() {
        for(int x =0; x<playerlength; x++) {
            if (Player1[x].xpos > 1000) {
                Player1[x].isAlive = false;
                Player1[x].xpos = 950;
            }
            if (Player1[x].xpos < -50) {
                Player1[x].isAlive = false;
                Player1[x].xpos = 0;
            }
            if (Player1[x].ypos > 800) {
                Player1[x].isAlive = false;
                Player1[x].ypos = 670;
            }
        }
    }

    public void checkOutsideBeginning() {
        for (int x = 0; x < playerlength; x++) {

            if (Player1[x].xpos > 1000) {
                Player1[x].xpos = 950;
            }
            if (Player1[x].xpos < -50) {
                Player1[x].xpos = 0;
            }
            if (Player1[x].ypos > 800) {
                if(playerlength == 1) {
                    level = 2;
                    playerToCredits();
                    System.out.println("RENDER");
                }else{
                    Player1[x].ypos = 700;
                }
            }

        }
    }

    public void playerToCredits(){
        for(int x = 0; x < playerlength; x++){
            Player1[x].ypos = 20;

        }
        /*for(Player test : Player1){ //this doesn't work bc playerlength isn't actually the length bc the code is trash :(
            test.ypos = 20;
            System.out.println("HELLO");
        }*/
    }


    public void renderCredits(int number) {

        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        if (number == 2){
            g.drawImage(Background2, 0, 0, 1000, 800, null);
            g.drawImage(Uturn,100,300,150,300,null);
            g.drawImage(noEntry,600,200,200,200,null);

        }if (number == 3) {
            g.drawImage(Background2, 0, 0, 1000, 800, null);
            g.drawImage(cautionTape,200,600,600,100,null);
            g.drawImage(stripeSign,700,170,150,270,null);

        }
        if (number == 4){
                g.drawImage(Background3, 0, 0, 1000, 800, null);
                for(int x = 0; x < playerlength; x++) {
                    if (Player1[x].ypos > 500 && Timer % 20 == 0) {
                        playerCantMove = true;
                        for(int y = 0; y < Player1[x].width/2; y ++) {
                            Player1[x].height -= 2;
                            Player1[x].width -= 2;
                            Player1[x].xpos += 1;
                            Player1[x].ypos += 1;

                        }
                        if(Player1[x].height==0){
                            playerToCredits();
                            level += 1;
                            //musicOn = false;
                            Player1[0].width = 100;
                            Player1[0].height = 100;
                            Player1[0].xpos = 400;
                            Player1[0].ypos = 400;


                        }


                        //this isn't working
                    }

                }



        } if (number == 5){
            g.drawImage(Background4, 0, 0, 1000, 800, null);
            if (stickyNote == Toolkit.getDefaultToolkit().getImage("stickyNoteImage.png")) {
                g.drawImage(stickyNote,600,100,100,100,null);
            }else if(stickyNote == Toolkit.getDefaultToolkit().getImage("halfFlip.png")){
                g.drawImage(stickyNote,605,100,90,100,null);
            }else{
                g.drawImage(stickyNote,605,90,100,110,null);

            }
            g.drawImage(couch,275,450,400,300,null);
            g.drawImage(cake,100,50,130,140,null);
            g.drawImage(rocketShip, 800,rocketShipypos,100,200,null);
            g.drawImage(sign,60,210,100,300,null);
            g.drawImage(cat,catXpos,catYpos,catWidth,catHeight,null);
            if(catJumped == false) {
                if (Timer % 90 < 30) {
                    g.drawImage(Z, 700, 360, 50, 50, null);
                } else if (Timer % 90 < 60) {
                    g.drawImage(Z, 720, 340, 50, 50, null);
                } else {
                    g.drawImage(Z, 740, 320, 50, 50, null);

                }
            }

            //g.drawImage(Playerimage0, Player1[0].xpos, Player1[0].ypos, Player1[0].width, Player1[0].height, null);
            playerCantMove = false;
            //System.out.println("hi");
            g.drawImage(Playerimage0, Player1[0].xpos, Player1[0].ypos, Player1[0].width, Player1[0].height, null);
            g.drawRect(Player1[0].rec.x, Player1[0].rec.y, Player1[0].rec.width, Player1[0].rec.height);

            driftNow = true;
            checkOutsideBox();



        }if(number == 6){
            Player1[0].xpos = 200;
            Player1[0].rec.x = Player1[0].xpos;
            Player1[0].rec.y = Player1[0].ypos;
            g.drawImage(Background4, 0, 0, 1000, 800, null);
            g.drawImage(pixelrain, 0, pixelRainypos, 1000,1000, null);
            g.drawRect(Player1[0].rec.x, Player1[0].rec.y, Player1[0].rec.width, Player1[0].rec.height);
            for(int x = 0; x < level6Obs.length; x++){
                g.setColor(new Color(200,0,0));
                g.drawRect(level6Obs[x].rec.x, level6Obs[x].rec.y, level6Obs[x].rec.width, level6Obs[x].rec.height);

            }
            System.out.println("level6");
            for(int x = 0; x < level6Obs.length; x++){
                g.setColor(new Color(level6Obs[x].r,level6Obs[x].g,level6Obs[x].b));

                g.fillRect(level6Obs[x].rec.x, level6Obs[x].rec.y, level6Obs[x].rec.width,level6Obs[x].rec.height);
            }
            if(level6animation == false) {
                if (Timer % 90 < 45) {
                    cattail = Toolkit.getDefaultToolkit().getImage("cattail1.png");
                    g.drawImage(cattail, cattailxpos, 340, 200, 100, null);
                } else {
                    cattail = Toolkit.getDefaultToolkit().getImage("cattail2.png");
                    g.drawImage(cattail, cattailxpos, 340, 200, 100, null);
                }
                if(Timer % 200 == 0){
                    cattailxpos -= 5;

                }
            }


        }if(number == 7){
            g.drawImage(Background5, 0, 0, 1000, 800, null);
            if(bugXpos > 450){
                g.drawImage(laptop,bugXpos,360,100,80,null);

            }else{
                g.drawImage(laptop,450,360,100,80,null);

            }
            g.drawImage(bigScreen,250,20,500,320,null);
            if(dialogueBeat == 9 || dialogueBeat == 10){
                if(Timer % 50 > 25) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen6.png");
                }else{
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen7.png");

                }
            }
            if(dialogueBeat > 1) {
                g.setColor(Color.WHITE);
                g.fillRect(100, 500, 800, 200);
                g.setColor(Color.GREEN);
                g.setFont(biggerFont);
                g.drawImage(bug, bugXpos, 360, 100, 70, null);


                if (dialogueBeat == 2) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen2.png");
                    laptop = Toolkit.getDefaultToolkit().getImage("laptop2.png");
                    g.drawString("Calling from: MY LAPTOP", 120, 550);
                    g.drawString("Calling: DEVPHONE1...", 120, 620);
                    //PLAY SOUND HERE ONCE I HAVE IT
                    //System.out.println("Hi why is this not working");

                }
                if (dialogueBeat == 3) {
                    g.drawString("Hello? Hello? Anyone there?", 120, 550);
                    g.drawString("(Who's calling from my work computer?)", 120, 620);

                }
                if (dialogueBeat == 4) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen3.png");
                    laptop = Toolkit.getDefaultToolkit().getImage("laptop3.png");
                    g.drawString("Here, let me switch it-", 120, 550);
                    g.drawString("Oh, hello!", 120, 620);

                }
                if (dialogueBeat == 5) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen4.png");
                    g.drawString("A player? How did you-", 120, 550);
                    g.drawString("Ohh. Right. I never fixed the wall.", 120, 620);

                }
                if (dialogueBeat == 6) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen5.png");
                    g.drawString("But hey! You ignored the signs, didn't you?", 120, 550);
                    g.drawString("You didn't mess with my stuff, did you?", 120, 620);

                }
                if (dialogueBeat == 7) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen5.png");
                    g.drawString("And you went through my flappy bird game!", 120, 550);
                    g.drawString("That was still in beta!", 120, 620);

                }if (dialogueBeat == 8) {
                    bigScreen = Toolkit.getDefaultToolkit().getImage("bigscreen3.png");
                    g.drawString("Wait. If you could get in, that means...", 120, 550);
                    bugXpos+=10;

                }if (dialogueBeat == 9) {
                    g.drawString("BUGS CAN TOO!", 120, 550);
                    bugXpos = 1200;


                }if (dialogueBeat == 10) {
                    g.drawString("Player! You need to get my computer back!", 120, 550);
                    g.drawString("If the bugs delete the code, ", 120, 620);
                    g.drawString("your world will be ruined!", 120, 660);


                }


            }
        }if (number == 8){
            g.drawImage(Background5,0,0,1000,720,null);
            g.setColor(Color.WHITE);
            g.drawRect(bullet[0].rec.x, bullet[0].rec.y,bullet[0].rec.width, bullet[0].rec.height);
            g.drawRect(bullet[0].aimbox.x, bullet[0].aimbox.y,bullet[0].aimbox.width, bullet[0].aimbox.height);


        }
        if(number == 9){
            g.drawRect(bullet[0].rec.x, bullet[0].rec.y,bullet[0].rec.width, bullet[0].rec.height);
            g.drawRect(bullet[0].aimbox.x, bullet[0].aimbox.y,bullet[0].aimbox.width, bullet[0].aimbox.height);

        }




        for(int x = 0; x<playerlength;x++) {
            if(x == 0){
                g.drawImage(Playerimage0, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);

            }
            else {
                g.drawImage(Playerimage1, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);
            }
            //g.fillRect(200,200,200,200);

        }


        g.dispose();

        bufferStrategy.show();


    }
    public void obstaclesMove(){
        for(int x = 0; x < level6Obs.length; x++){
            level6Obs[x].rec.x -= 5;
            //System.out.println(level6Obs[9].xpos);
        }

    }
    public void playerReset(){
        for(int x = 0; x < playerlength; x++) {
            Player1[x].gravityAffect = 0;
            Player1[x].xpos = 500;
            Player1[x].ypos = 670;
            Player1[x].rec = new Rectangle(500, 670, Player1[x].rec.width, Player1[x].rec.height);
        }


    }

    public void Coin() {
      //  System.out.println(points);
        double x = Math.random();
        //System.out.println(leveledup);

        if(leveledup%2==1){
            Coin1 = new Coin(100+(int)(x*800),(Carlist[(int)(x*3)].ypos),100);
           //System.out.println(Coin1.xpos + " " + Coin1.ypos);
        }else{
            Coin1 = new Coin(10000,10000,100);
        }

    }

    public void checkStart() {
        //System.out.println("checking");
        if (level == 0) {
            for(int x = 0; x<playerlength; x++) {
                if (Player1[x].rec.intersects(startbutton1.rec) || Player1[x].ypos < 0) {
                    playerReset();
                    Coin();
                    level = 1;
                    stopsong = true;
                    leveledup = 0;
                }
            }


        } else if (level == 1){
            if(playerlength>1) {
                if (Player1[1].PlayerWin == true && Player1[0].PlayerWin == true) {
                    // System.out.println("i win");
                    leveledup = leveledup + 1;
                    for (int i = 0; i < Carlist.length; i++) {
                        Carlist[i].dx += 1;
                        //System.out.println("speedup");

                    }
                    for (int i = 0; i < Log2.length; i++) {
                        Log2[i].dx += 1;

                    }
                    Player1[1].PlayerWin = false;
                    Player1[0].PlayerWin = false;


                }
            }else{
                if(Player1[0].PlayerWin == true) {
                    leveledup = leveledup + 1;
                    for (int i = 0; i < Carlist.length; i++) {
                        Carlist[i].dx +=1;
                        //System.out.println("speedup");

                    }
                    for (int i = 0; i < Log2.length; i++) {
                        Log2[i].dx += 1;

                    }
                    Player1[0].PlayerWin = false;
                }

            }

        }


    }



    public void checkWin() {
        for(int x = 0; x<playerlength; x++) {
            if (Player1[x].ypos < -100) {
                //System.out.println("go");
                Player1[x].PlayerWin = true;


            }
        }



    }

    public void checkTimer() {
        for(int x = 0; x<playerlength; x++) {

            if (Player1[x].isAlive == false) {

                if(x==0){
                    Playerimage0 = Toolkit.getDefaultToolkit().getImage("Dead character.png");

                }
                if(x==1){
                    Playerimage1 = Toolkit.getDefaultToolkit().getImage("Dead character.png");
                }

                if (Timer - timepause == 500) {

                   // System.out.println("Player" + x + "is dead");
                    level = 0;
                    playerReset();
                    Coin();
                    for (int i = 0; i < Carlist.length; i++) {
                        Carlist[i].dx = Carlist[i].dx - leveledup;
                        if(Carlist[i].dx < 0){
                            Carlist[i].dx=1;
                        }

                        //System.out.println(Carlist[i].dx);
                    }
                    for (int i = 0; i < Log2.length; i++) {
                        Log2[i].dx = Log2[i].dx - leveledup;
                        if(Log2[i].dx < 0){
                            Log2[i].dx=1;
                        }
                        //System.out.println("speed" + Log2[i].dx);
                    }


                    Player1[x].isAlive = true;
                    //System.out.println("hello");
                    Playerimage0 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter0+"characterselect.png");

                    Playerimage1 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter1+"characterselect.png");

                }

            }
        }
    }

    public void checkLog(){
        for(int i = 0; i <playerlength; i++) {
            for(int x = 0; x < Log2.length; x++) {
                if (Player1[i].rec.intersects(Log2[x].rec)) {
                    Player1[i].onLog = true;
                    //System.out.println("player" + i);
                    theOneThePlayerIsOn = Log2[x];
                    break;

                    //System.out.println("player on log");
                } else {
                    Player1[i].onLog = false;
                    //System.out.println("player NOT on log");
                }
            }

        }
        for(int x = 0; x<playerlength; x++) {
            if (Player1[x].onLog == false) {
                if (Player1[x].rec.intersects(River1.rec)) {
                    Player1[x].isAlive = false;
                    //System.out.println("i intersected with river");

                }
            }
        }
    }
    public void checkCrash() {
        //System.out.println("WHY IS THIS NOT WORKING");
        if(level == 1) {
            for (int i = 0; i < Carlist.length; i++) {
                for (int x = 0; x < Carlist.length; x++) {
                    if (Carlist[x].rec.intersects(Carlist[i].rec)) {
                        Carlist[x].xpos = Carlist[i].xpos + 1;
                    }
                }
            }
            for (int x = 0; x < playerlength; x++) {
                for (int i = 0; i < Carlist.length; i++) {
                    if (Player1[x].rec.intersects(Carlist[i].rec)) {
                        Player1[x].isAlive = false;
                        //System.out.println(x + "is dead");
                    }
                }
            }
            for (int i = 0; i < Log2.length; i++) {
                    for (int x = 0; x < Log2.length; x++) {
                        if (Log2[x].rec.intersects(Log2[i].rec)) {
                            Log2[x].xpos = Log2[i].xpos + 1;
                        }
                    }
            }

        }

        if(level == 6 && level6animation == false){
            //System.out.println("level six now");
            for(int x = 0; x < level6Obs.length-1;x++) {
                if (Player1[0].rec.intersects(level6Obs[x].rec)) {
                    level6failed = true;
                }
            }
            if(Player1[0].rec.intersects(level6Obs[39].rec)){
                level6reset();
                level = 7;
                Player1[0].xpos = 100;
                Player1[0].ypos = 350;
                //System.out.println("level 7 :) wow level 6 was fast geez");
            }

        }

    }

    public void moveAll() {
        Coin1.rec = new Rectangle(Coin1.xpos, Coin1.ypos, Coin1.sidelength, Coin1.sidelength);

        for(int x=0;x<Carlist.length;x++) {

                Carlist[x].methodDrive();
            }


            //Log1.methodDrive();
            for(int x=0;x<Log2.length;x++) {

                Log2[x].methodDrive();
            }
        for(int x = 0; x<playerlength;x++) {
            if (Player1[x].isAlive == true) {
                //System.out.println("im alive");

                if (Player1[x].onLog == true) {
                    //System.out.println("on the log");
                    Player1[x].xpos = 2 * theOneThePlayerIsOn.dx + Player1[x].xpos;
                    //System.out.println(theOneThePlayerIsOn);
                    //Player1.ypos = Log1.rec.y;
                    Player1[x].rec = new Rectangle(Player1[x].xpos + 20, Player1[x].ypos + 20, Player1[x].width - 40, Player1[x].height - 40);

                } else {
                    Player1[x].onLog = false;

                }


            }

        }






    }
	public void checkCoin(){
        for(int x = 0; x<playerlength;x++) {
            if (Player1[x].rec.intersects(Coin1.rec)) {
                Coin1.xpos = 50000;
                Coin1.ypos = 50000;
                //System.out.println("coin left");
                Coin1.taken = true;
                Player1[x].coinpoints += 1;
                Coin1.taken = false;
                checkPoint();
                //System.out.println(Player1[x].points);


            }
        }

        if (Coin1.taken == true){
            Coin1.xpos = 50000;
            Coin1.ypos = 50000;
            //coinPoints+=1;
        }
    }
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
        Timer = Timer + time;
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

		}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout

      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      canvas.addKeyListener(this);
        canvas.addMouseListener(this);
      panel.add(canvas);  // adds the canvas to the panel.

      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");

   }

   public void transition(){
        transitionvarib = true;
   }
   public void renderBeginning(){

       Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
       g.setFont(bigFont);
       g.clearRect(0, 0, WIDTH, HEIGHT);
       g.drawImage(Background, 0, 0, 1000, 800, null);


       g.setColor(new Color(0,0,0));
       for(int x = 0; x <playerlength; x++) {
           g.drawRect(Player1[x].rec.x, Player1[x].rec.y, Player1[x].rec.width, Player1[x].rec.height);
       }
       for(int x=0;x<CharSelect1.length;x++) {
               g.setColor(myColor[x]);
               //this one does the character select boxes \/

               g.fillRect(CharSelect1[x].rec.x, CharSelect1[x].rec.y, CharSelect1[x].rec.width, CharSelect1[x].rec.height);
               g.setColor(Color.black);
               g.drawString(""+CharSelect1[x].levelunlock,CharSelect1[x].rec.x, CharSelect1[x].rec.y+100);
               for(int i = 0; i<playerlength;i++) {
                   if (CharSelect1[x].unlocked == false) {
                       g.drawImage(lock, CharSelect1[x].rec.x, CharSelect1[x].rec.y, CharSelect1[x].rec.width, CharSelect1[x].rec.height, null);
                   }
               }

               //Log2[x].methodDrive();
           }
           g.setColor(Color.green);
           //g.fillRect(startbutton1.rec.x, startbutton1.rec.y, startbutton1.rec.width, startbutton1.rec.height);
           g.drawImage(startbutton, startbutton1.rec.x, startbutton1.rec.y, 400, 200, null);

           //g.drawRect(Log2.rec.x, Log2.rec.y, Log2.rec.width, Log2.rec.height);
            for(int x = 0; x<playerlength;x++) {
                if(x == 0){
                    g.drawImage(Playerimage0, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);

                }
                else {
                    g.drawImage(Playerimage1, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);
                }
                //g.fillRect(200,200,200,200);

                g.setFont(bigFont);
                g.setColor(Color.black);
                g.drawString("Points: " + Player1[x].totalpoints, 100+700*x, 100);
            }
            g.fillRect(changePlayerRec.x,changePlayerRec.y,changePlayerRec.width,changePlayerRec.height);

            g.drawImage(changePlayerImage,changePlayerRec.x,changePlayerRec.y,changePlayerRec.width,changePlayerRec.height,null);
            g.fillRect(turnSoundOff.x,turnSoundOff.y,turnSoundOff.width,turnSoundOff.height);
             g.drawImage(turnSoundOffImage,turnSoundOff.x,turnSoundOff.y,turnSoundOff.width,turnSoundOff.height,null);


            g.dispose();

           bufferStrategy.show();


   }

	//paints things on the screen using bufferStrategy
	private void renderLevel1() {
        Image y = Playerimage0;



        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
        if(playerlength>1) {

            for (int x = 0; x < playerlength; x++) {
                //System.out.println(playerlength);


                if ((Player1[0].ypos < -100 && Player1[1].ypos < -100) || (Player1[1].isAlive == false && Player1[0].PlayerWin == true) || (Player1[0].isAlive == false && Player1[1].PlayerWin == true)) {
                    //System.out.println("I WIN");
                    Player1[0].PlayerWin = true;
                    Player1[1].PlayerWin = true;

                    //System.out.println( "!!!!");
                    playerReset();
                    Coin();
                    transitionvarib = true;
                    Player1[0].points += 1;
                    Player1[1].points += 1;
                    //System.out.println("AEWHFHEWHAHE");

                    for(int z = 0; z < 2; z++) {
                        if (Player1[z].isAlive == true) {
                            Player1[z].totalpoints = Player1[z].points + Player1[z].coinpoints;
                            //System.out.println(Player1[z].points + " wwww" + Player1[z].coinpoints);


                            //System.out.println(Player1[x] + "hello");

                        }
                    }
                    //System.out.println(Player1[x].points + " " + Player1[x].coinpoints + " " + Player1[x].totalpoints);

                }
            }
        }else{
            if (Player1[0].ypos < -100) {
                //System.out.println("I WIN");
                Player1[0].PlayerWin = true;

                //System.out.println(Player1.ypos + "!!!!");
                playerReset();
                Coin();
                transitionvarib = true;
                Player1[0].points += 1;
                Player1[0].totalpoints = Player1[0].points + Player1[0].coinpoints;
                //System.out.println(Player1[0].points + " " + Player1[0].coinpoints + " " + Player1[0].totalpoints);

            }
        }


        if (transitionvarib == true){
            //System.out.println(Log2[1].dx);
           // System.out.println(leveledup);

            //System.out.println("transition = true");
            g.setColor(Color.red);
            g.fillRect(0,0,1000,800);
            for(int x = 0; x <playerlength;x++) {

            }
            pause(1000);

            transitionvarib = false;

            g.clearRect(0,0,WIDTH, HEIGHT); //this isn't working :(

        }else {

            g.setColor(Color.green);
            //g.fillRect(0,0,1000,800);
            //draw the image of the astronaut
            //g.drawRect(0, Car1.ypos+25, 1000, 50);
            //g.drawRect(0, Car2.ypos+25, 1000, 50);
            g.drawImage(Background2, 0, 0, 1000, 800, null);

            g.setColor(Color.cyan);
            g.fillRect(River1.xpos, River1.ypos, River1.width, River1.height);

            //g.drawImage(Car1pic, Car1.xpos, Car1.ypos, Car1.width, Car1.height, null);
            // g.drawImage(Logimage, Log1.xpos, Log1.ypos, Log1.width, Log1.height, null);
            //g.drawImage(Logimage, Log2.xpos, Log2.ypos, Log2.width, Log2.height, null);
            //g.drawImage(Car2pic, Car2.xpos, Car2.ypos, Car2.width, Car2.height, null);

            // this one draws the player hitbox\/
            for(int x = 0; x<playerlength;x++) {
                g.setColor(new Color(0+100*x,0+200*x,0+100*x));

                g.drawRect(Player1[x].rec.x, Player1[x].rec.y, Player1[x].rec.width, Player1[x].rec.height);
            }
            //g.drawRect(Log1.rec.x, Log1.rec.y, Log1.rec.width, Log1.rec.height);
            for (int x = 0; x < Log2.length; x++) {

                //this one does the log hitboxes \/
                //g.drawRect(Log2[x].rec.x, Log2[x].rec.y, Log2[x].rec.width, Log2[x].rec.height);
                g.drawImage(Logimage, Log2[x].xpos, Log2[x].ypos, Log2[x].width, Log2[x].height, null);
                g.setColor(Color.gray);

                g.fillRect(0, Carlist[x].ypos + 25, 1000, 50);
                g.drawImage(Car1pic, Carlist[x].xpos, Carlist[x].ypos, Carlist[x].width, Carlist[x].height, null);

                //Log2[x].methodDrive();
            }

            //g.drawRect(Log2.rec.x, Log2.rec.y, Log2.rec.width, Log2.rec.height);
            for(int x = 0; x <playerlength; x++) {
                if(x==0) {
                    g.drawImage(Playerimage0, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);
                }else {
                    g.drawImage(Playerimage1, Player1[x].xpos, Player1[x].ypos, Player1[x].width, Player1[x].height, null);
                }//g.fillRect(200,200,200,200);
            }
            g.drawImage(coin, Coin1.xpos, Coin1.ypos, Coin1.sidelength, Coin1.sidelength, null);
            g.setColor(Color.black);
            g.setFont(bigFont);

            for(int x = 0; x <playerlength; x++) {
                g.drawString("Points: "+ Player1[x].totalpoints, 100+700*x,100);
            }
            g.dispose();

            bufferStrategy.show();
        }
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(driftNow == true){
            if(e.getKeyCode()==38){
                Player1[0].DriftPlayerUp();
                //System.out.println("drift now up");
                Player1[0].facingDown = false;


            }
            if(e.getKeyCode() == 40){
                Player1[0].DriftPlayerDown();
                //System.out.println("drift now down");
                Player1[0].facingDown = true;


            }
            if(e.getKeyCode() == 37){
                Player1[0].DriftPlayerLeft();
               // System.out.println("drift now down");
                Player1[0].facingLeft = true;


            }
            if(e.getKeyCode() == 39){
                Player1[0].DriftPlayerRight();
                //System.out.println("drift now down");
                Player1[0].facingLeft = false;


            }

        }

    }

    public void checkDrift(){
        if(driftNow == true){
            if(Player1[0].dy > .5 || Player1[0].dy < -.5){
                //System.out.println(Player1[0].dy);

                Player1[0].dy -= .05;

                if(Player1[0].facingDown == false) {
                    //System.out.println("FACING UP");
                    Player1[0].ypos = (int) (Player1[0].ypos - Player1[0].dy);
                }
                else if(Player1[0].facingDown == true){
                    //System.out.println("FACING DOWN");
                    Player1[0].ypos = (int) (Player1[0].ypos + Player1[0].dy);
                }
            }else{
                //System.out.println("dy = 0");
                Player1[0].dy = 0;
            }


            if(Player1[0].dx > .5 || Player1[0].dx < -.5){
                //System.out.println(Player1[0].dy);

                Player1[0].dx -= .05;

                if(Player1[0].facingLeft == false && Player1[0].dx>0) {
                   // System.out.println("FACING RIGHT");
                    Player1[0].xpos = (int) (Player1[0].xpos + Player1[0].dx);
                }
                else if(Player1[0].facingLeft == true){
                   // System.out.println("FACING LEFT");
                    Player1[0].xpos = (int) (Player1[0].xpos - Player1[0].dx);
                }
            }else{
               // System.out.println("dx = 0");
                Player1[0].dx = 0;
            }

            Player1[0].rec.x = Player1[0].xpos + 20;
            Player1[0].rec.y = Player1[0].ypos + 20;


        }
    }


    public void gravity(){
        if(level == 6){
                //System.out.println(Player1[0].dy);

            Player1[0].gravityAffect += .1;
            Player1[0].gravityAffect();
            //System.out.println(Player1[0].gravityAffect + " " + Player1[0].ypos);



        }
    }




    public void checkCharSelect() {
        //replaced into keyreleased
    }


    @Override
    public void keyReleased(KeyEvent e) {

        if(level == 6 && e.getKeyCode() == 32 && playerCantMove == false){
            Player1[0].level6jump();
        }
        /*if( level == 5 && mouseBox.intersects(600,100, 100,100)){
            stickyNote = Toolkit.getDefaultToolkit().getImage("halfFlip.png");
            flipSticky = true;
            x = Timer;

        }*/ //if this were uncommented, clicking on the sticky note would also flip it


        for(int x = 0; x < playerlength; x++) {
            if (changePlayerRec.intersects(Player1[x].rec) && e.getKeyCode() ==32) {
                if(level == 0) {
                    if (playerlength == 1) {
                        playerlength = 2;
                        Player1[1] = new Player(600, 670, 0, Playerimage0);
                        changePlayerImage = Toolkit.getDefaultToolkit().getImage("2player.png");


                        //System.out.println("hi");

                    } else {
                        playerlength = 1;
                        changePlayerImage = Toolkit.getDefaultToolkit().getImage("1player.png");

                    }
                }

            }




            if (turnSoundOff.intersects(Player1[x].rec) && e.getKeyCode() == 32) {
                if (level == 0) {
                    //System.out.println("hello");
                    turnMusicOn = true;
                    if (musicOn == true) {
                        musicOn = false;
                        //System.out.println("IT SHOULD STOP");
                    } else {
                        musicOn = true;

                        //System.out.println("IT SHOULD GO");
                    }            //System.out.println(musicOff + "= musicoff");

                }
            }
        }











        for (int i = 0; i < CharSelect1.length; i++) { //previous checkcharselect
            for(int x = 0; x<playerlength; x++) {
                if (Player1[x].rec.intersects(CharSelect1[i].rec) && e.getKeyCode() == 32 && level == 0) {
                    if (CharSelect1[i].levelunlock <= Player1[x].totalpoints || CharSelect1[i].unlocked == true) {
                        if(x==0){
                            if(Player1[0].facingLeft == false) {
                                Playerimage0 = Toolkit.getDefaultToolkit().getImage(i + "characterselect.png");

                            }else{
                                Playerimage0 = Toolkit.getDefaultToolkit().getImage(i + "characterselectl.png");

                            }

                            savedPlayerCharacter0 = i;
                            if(CharSelect1[i].unlocked == false) {
                                Player1[0].points = Player1[0].points - CharSelect1[i].levelunlock;
                                Player1[0].totalpoints = Player1[0].points + Player1[0].coinpoints;

                            }
                            CharSelect1[i].unlocked = true;

                            //System.out.println(savedPlayerCharacter0);
                        }
                        if(x==1){
                            if(Player1[1].facingLeft == false) {
                                Playerimage1 = Toolkit.getDefaultToolkit().getImage(i + "characterselect.png");
                            }else{
                                Playerimage1 = Toolkit.getDefaultToolkit().getImage(i + "characterselectl.png");

                            }
                            savedPlayerCharacter1 = i;
                            if(CharSelect1[i].unlocked == false) {
                                Player1[1].points = Player1[1].points - CharSelect1[i].levelunlock;
                                Player1[1].totalpoints = Player1[1].points + Player1[1].coinpoints;
                            }
                            CharSelect1[i].unlocked = true;
                            //System.out.println(savedPlayerCharacter1);

                        }
                        //System.out.println(Player1.isAlive);
                        if(level == 0) {
                            playSFX("bloop_x.wav", 0);
                        }
                    }else{
                        if(level == 0) {
                            playSFX("freesound_community-door-lock-82542.wav", 500_000); //i have no idea what the _ does but it works
                        }
                    }
                }
            }
        }

        if( level == 7){
            if(dialogueBeat == 1) {
                if (Player1[0].rec.intersects(450, 360, 100, 80) && e.getKeyCode() == 32) {
                    dialogueBeat += 1;
                }
            }else if(e.getKeyCode() == 32 && dialogueBeat < 10){
                dialogueBeat += 1;
            }

        }


        if( level == 5 && Player1[0].rec.intersects(catXpos,catYpos,catWidth,catHeight) && e.getKeyCode() == 32 && secretsEnabled == true){
            playerCantMove = true;
            catYpos -= 30;
            catHeight += 50;
            catWidth += 30;

            cat = Toolkit.getDefaultToolkit().getImage("catJump.png");

            catJumped = true;
            r = Timer;
            System.out.println("Flappy bird :D");
        }




        if( level == 5 && Player1[0].rec.intersects(600,100, 100,100) && e.getKeyCode() == 32){
            stickyNote = Toolkit.getDefaultToolkit().getImage("halfFlip.png");
            flipSticky = true;
            x = Timer;
            playSFX("paper_tearing.wav",0);

        }
        if( level == 5 && Player1[0].rec.intersects(100,50, 150,140) && e.getKeyCode() == 32){
            cake = Toolkit.getDefaultToolkit().getImage("cakePartiallyEaten.png");
            eatCake = true;
            z = Timer;
            playSFX("croc_chomp_x.wav",0);

        }


        if( level == 5 && Player1[0].rec.intersects(60,210, 100,300) && e.getKeyCode() == 32){
            if(signLit == true){
                signLit = false;
                sign = Toolkit.getDefaultToolkit().getImage("signImage.png");

            }else{
                signLit = true;
                sign = Toolkit.getDefaultToolkit().getImage("signImagelit.png");

            }
            playSFX("click_x.wav",0);

        }



        if( level == 5 && Player1[0].rec.intersects(800,100, 100,180) && e.getKeyCode() == 32){
            playerCantMove = true;
            rocketShip = Toolkit.getDefaultToolkit().getImage("rocketShipfired.png");
            rocketTakeOff = true;
            y = Timer;

        }

        if (Player1[0].isAlive == true && playerCantMove == false && Player1[0].ypos >= -100) {
            //System.out.println(e.getKeyCode());
            //System.out.println(Player1.ypos);
            if (e.getKeyCode() == 38) {
                if (driftNow == false) {
                   // System.out.println("shouldn't drift now!!!!");
                    Player1[0].methodPlayerUp();
                   // System.out.println("player up");
                }
                if (Player1[0].ypos < 0 && level > 2) {
                    playerReset();
                    level -= 1;
                   // System.out.println("BLA");

                } else if (Player1[0].ypos < 0 && level == 2) {
                    playerReset();
                    level = 0;
                   // System.out.println("BLA2");

                }
            }
            if (e.getKeyCode() == 39) {
                if (driftNow == false) {
                    Player1[0].methodPlayerRight();
                   // System.out.println("going right");

                }

                if (Player1[0].xpos > 1000) {
                    Player1[0].xpos = 950;
                }

                Playerimage0 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter0 + "characterselect" + ".png");

            }

            if (e.getKeyCode() == 37) {
                if (driftNow == false) {
                    Player1[0].methodPlayerLeft();

                }
                if (Player1[0].xpos < -50) {
                    Player1[0].xpos = 0;
                }

                Playerimage0 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter0 + "characterselect" + "l" + ".png");

            }
            if (e.getKeyCode() == 40) {
                if (driftNow == false) {
                    Player1[0].methodPlayerDown();
                    //System.out.println("player up");
                }
                if (Player1[0].ypos > 700 && level > 1 && level < 10) {
                    playerToCredits();
                    level += 1;
                    //System.out.println("BLA");
                }
                if (Player1[0].ypos > 500 && level == 4) {
                    //System.out.println("BLAskdfhsdhfdsj");
                }
            }

            if (playerlength > 1) {
                if (Player1[1].isAlive == true && playerCantMove == false && Player1[1].ypos >= -100) {
                    //System.out.println(e.getKeyCode());
                    //System.out.println(Player1.ypos);
                    if (e.getKeyCode() == 32) {
                        //System.out.println("rotate");
                    }
                    if (e.getKeyCode() == 87) {
                        Player1[1].methodPlayerUp();
                        if (Player1[1].ypos < 0 && level > 2) {
                            playerReset();
                            level -= 1;
                        } else if (Player1[1].ypos < 0 && level == 2) {
                            level = 0;

                        }
                    }
                    if (e.getKeyCode() == 68) {
                        Player1[1].methodPlayerRight();
                        if (Player1[1].xpos > 1000) {
                            Player1[1].xpos = 950;
                        }

                        Playerimage1 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter1 + "characterselect" + ".png");
                        Player1[1].facingLeft = false;
                    }

                    if (e.getKeyCode() == 65) {
                        Player1[1].methodPlayerLeft();
                        if (Player1[1].xpos < -50) {
                            Player1[1].xpos = 0;
                        }

                        Playerimage1 = Toolkit.getDefaultToolkit().getImage(savedPlayerCharacter1 + "characterselect" + "l" + ".png");
                        Player1[1].facingLeft = true;

                    }
                    if (e.getKeyCode() == 83) {
                        Player1[1].methodPlayerDown();
                       /* if(Player1[1].ypos > 700 && level > 1 && level < 4) {
                            playerToCredits();
                            level += 1;
                            System.out.println("BLA");
                        }if(Player1[1].ypos > 500 && level == 4){
                            System.out.println("BLAskdfhsdhfdsj");
                        }*/

                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override


    public void mouseReleased(MouseEvent e) {
        if(level == 8 && bullet[0].aimbox.intersects(e.getX(),e.getY(),1,1)){
            bullet[0].locationX=e.getX();
            bullet[0].locationY=e.getY();
            bullet[0].calcDxDy();
            for(int x = 0; x < 15; x ++){
                bullet[0].move();
                renderCredits(9);
                pause(10);
            }
        }
    }

    public void playSFX(String name, int startingpoint) {

        try {

            // Open an audio input stream.
            File soundFile = new File(name); //you could also get the sound file with an URL
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.setMicrosecondPosition(startingpoint);

            clip.start();   //start playing the sound

        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }



    public void playSound1(int number) {

        try {

            File soundFile;
            // Open an audio input steram.

            soundFile = new File("BeepBoxintro1.wav"); //you could also get the sound file with an URL

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            //clip[0].open(audioIn);
            if(clip[0].isOpen()==false) {
                clip[0].open(audioIn);
            }

            if (musicOn == false || level == 5) {
                clip[0].stop();
                pauseSoundTimer = pauseSoundTimer + 1;
                //System.out.println(pauseSoundTimer + " = pausesound timer");
                //sleep
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {

                }

                //System.out.println("it should stop now :(");
            }else{
                clip[0].start();

            }


        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }


    }

    public void playSound2(int number) {
        clip[0].setFramePosition(0);

        clip[0].start();
        System.out.println("HI");




    }







    /*public void checkSoundOff(){
        if (musicOn == false) {

            clip.stop();   //start playing the sound
            clip.drain();
            clip.flush();

            System.out.println("jeezzzzz");
        }
        //System.out.println(clip.getMicrosecondLength());

    }*/


    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}