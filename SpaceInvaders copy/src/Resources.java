import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Resources {
    // to add an image to the environment:
    // 1. put the file into the res folder.
    // 2. Declare a variable before the static block.
    // 3. Initialize the variable by copying and pasting and modifying the
    //    ImageIO line.


    public static BufferedImage player, projectile,mpProjectile, shield;
    public static BufferedImage alien1, alien1row2, alien1row3, ep1;
    public static BufferedImage alien2, alien2row2, ep2;
    public static BufferedImage alien3, alien3row2, ep3;
    public static BufferedImage alien4, alien4row2, ep4;
    public static BufferedImage alien5, alien5row2, ep5, ep5a, ep5b;
    public static BufferedImage back1, back2, back3, back4, back5;
    public static BufferedImage hpBooster, speedBooster, dmgBooster, mpBooster, dexBooster;

    public static BufferedImage[] pics;


    static{
        try{
            //player resources
            player = ImageIO.read(new File("./res/player.png"));
            projectile = ImageIO.read(new File("./res/playerlaser.png"));
            mpProjectile = ImageIO.read(new File("./res/specialproj.png"));
            shield = ImageIO.read(new File("./res/shield.png"));


            //enemy resources:

            //lvl 1
            alien1 = ImageIO.read(new File("./res/alien1.png"));
            alien1row2 = ImageIO.read(new File("./res/alien1row2.png"));
            alien1row3 = ImageIO.read(new File("./res/alien1row3.png"));
            ep1 = ImageIO.read(new File("./res/ep1.png"));

            //lvl 2
            alien2 = ImageIO.read(new File("./res/alien2.png"));
            alien2row2 = ImageIO.read(new File("./res/alien2row2.png"));
            ep2 = ImageIO.read(new File("./res/ep2.png"));

            //lvl 3
            alien3 = ImageIO.read(new File("./res/alien3row1.png"));
            alien3row2 = ImageIO.read(new File("./res/alien3row2.png"));
            ep3 = ImageIO.read(new File("./res/ep3.png"));

            //lvl 4
            alien4 = ImageIO.read(new File("./res/alien4.png"));
            alien4row2 = ImageIO.read(new File("./res/alien4row2.png"));
            ep4 = ImageIO.read(new File("./res/ep4.png"));

            //lvl 5
            alien5 = ImageIO.read(new File("./res/ufo.png"));
            alien5row2 = ImageIO.read(new File("./res/ufo.png"));
            ep5 = ImageIO.read(new File("./res/laserz.png"));
            ep5a = ImageIO.read(new File("./res/ep5a.png"));
            ep5b = ImageIO.read(new File("./res/ep5b.png"));




            back1 = ImageIO.read(new File("./res/back1.png")); //water
            back2 = ImageIO.read(new File("./res/back2.png")); //dark
            back3 = ImageIO.read(new File("./res/back3.png")); //sand
            back4 = ImageIO.read(new File("./res/back4.png")); //ice
            back5 = ImageIO.read(new File("./res/back5.png")); //final

            hpBooster = ImageIO.read(new File("./res/hpboost.png"));
            mpBooster = ImageIO.read(new File("./res/mpboost.png"));
            speedBooster = ImageIO.read(new File("./res/spdboost.png"));
            dmgBooster = ImageIO.read(new File("./res/atkboost.png"));
            dexBooster = ImageIO.read(new File("./res/dexboost.png"));





            pics = new BufferedImage[10];
            for (int i = 1; i < 11; i++) {
                pics[i-1] = ImageIO.read(new File("./res/test" + i + ".png"));
            }



        }catch(Exception e){e.printStackTrace();}
    }
}