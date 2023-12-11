
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//Raymond Xu

public class Main extends JPanel {

    private int hp, mp, coins, spd, dmg, enemyHP, bossHP, r;

    private Timer timer;  // fires an event to trigger updating the animation.

    private Sprite player; //player sprite
    private Sprite shield; //shield sprite

    private ArrayList<Sprite> back1 = new ArrayList<Sprite>(); //background
    private ArrayList<Sprite> back2 = new ArrayList<Sprite>(); //background
    private ArrayList<Sprite> back3 = new ArrayList<Sprite>(); //background
    private ArrayList<Sprite> back4 = new ArrayList<Sprite>(); //background
    private ArrayList<Sprite> back5 = new ArrayList<Sprite>(); //background


    private ArrayList<Sprite> projectile = new ArrayList<Sprite>(); //player projectiles
    private ArrayList<Sprite> mpProjectile = new ArrayList<Sprite>(); //special mp projectiles

    //lvl 1
    private ArrayList<Sprite> enemy = new ArrayList<Sprite>(); // enemy sprites
    private ArrayList<Sprite> enemyProjectiles = new ArrayList<Sprite>(); //enemy projectiles

    //lvl 2
    private ArrayList<Sprite> enemyLvl2 = new ArrayList<Sprite>(); // enemy sprites
    private ArrayList<Sprite> enemyProjectilesLvl2 = new ArrayList<Sprite>(); //enemy projectiles

    //lvl 3
    private ArrayList<Sprite> enemyLvl3 = new ArrayList<Sprite>(); // enemy sprites
    private ArrayList<Sprite> enemyProjectilesLvl3 = new ArrayList<Sprite>(); //enemy projectiles

    //lvl 4
    private ArrayList<Sprite> enemyLvl4 = new ArrayList<Sprite>(); // enemy sprites
    private ArrayList<Sprite> enemyProjectilesLvl4 = new ArrayList<Sprite>(); //enemy projectiles

    //lvl 5
    private Sprite bosslvl5; //player sprite
    private ArrayList<Sprite> enemyProjectilesLvl5 = new ArrayList<Sprite>(); //enemy projectiles
    private ArrayList<Sprite> enemyProjectilesLvl5Bullet = new ArrayList<Sprite>(); //enemy projectiles
    private ArrayList<Sprite> enemyProjectilesLvl5Laser = new ArrayList<Sprite>(); //enemy projectiles


    private int pWidth, eWidth,e2Width, e3Width, e4Width, e5Width, epWidth, ep2Width, ep3Width, ep4Width, ep5Width, ep5BulletWidth, ep5LaserWidth; //player projectile width, enemy width and enemy projectile width

    private boolean hitEdge; //hits screen edge

    private int direction, direction2, direction3, direction4, direction5; //direction of enemy sprite

    private int playerFrameCounter;
    private int enemyFrameCounter;
    private int mpFrameCounter;

    private boolean gameFinished, gameOver, pause;
    private int togglePause;
    private int pauseFrameCounter;
    private int backCounter;

    private boolean lvlComplete, shopOpen, optionsMenuOpen;

    //shop stuff
    private boolean hpBoosterBought, speedBoosterBought, dmgBoosterBought, mpBoosterBought, dexBoosterBought;
    private Sprite hpBoosterSprite;
    private Sprite speedBoosterSprite;
    private Sprite dmgBoosterSprite;
    private Sprite mpBoosterSprite;
    private Sprite dexBoosterSprite;




    private int gameLevel;


    private boolean[] keys;



    public Main(int w, int h){
        setSize(w, h);

        hp=100;
        mp=0;
        coins=0;



        player = new Sprite(Resources.player, new Point(400, 600));
        shield = new Sprite(Resources.shield, new Point(player.getX(),player.getY()));

        hpBoosterSprite = new Sprite(Resources.hpBooster, new Point(300,250));
        mpBoosterSprite = new Sprite(Resources.mpBooster, new Point(300, 350));
        speedBoosterSprite = new Sprite(Resources.speedBooster, new Point(300, 450));
        dmgBoosterSprite = new Sprite(Resources.dmgBooster, new Point(300,550));
        dexBoosterSprite = new Sprite(Resources.dexBooster, new Point(300,650));

        spd=1;

        dmg=1;

        //backgrounds
        for (int x = 0; x <= 1500; x+=80) {
            for (int y = 0; y <= 800; y += 80) {
                    back1.add(new Sprite(Resources.back1, new Point(x, y))); //create enemy sprite
            }
        }
        for (int x = 0; x <= 1500; x+=80) {
            for (int y = 0; y <= 800; y += 80) {
                back2.add(new Sprite(Resources.back2, new Point(x, y))); //create enemy sprite
            }
        }
        for (int x = 0; x <= 1500; x+=80) {
            for (int y = 0; y <= 800; y += 80) {
                back3.add(new Sprite(Resources.back3, new Point(x, y))); //create enemy sprite
            }
        }
        for (int x = 0; x <= 1500; x+=80) {
            for (int y = 0; y <= 800; y += 80) {
                back4.add(new Sprite(Resources.back4, new Point(x, y))); //create enemy sprite
            }
        }
        for (int x = 0; x <= 1500; x+=80) {
            for (int y = 0; y <= 800; y += 80) {
                back5.add(new Sprite(Resources.back5, new Point(x, y))); //create enemy sprite
            }
        }


        //enemy lvl 1
        for (int x = 0; x <= 960; x+=120) {
            for (int y = 0; y <= 200; y+=100) {
                if (y==0) {
                    enemy.add(new Sprite(Resources.alien1, new Point(x, y))); //create enemy sprite
                }
                else if (y==100) {
                    enemy.add(new Sprite(Resources.alien1row2, new Point(x, y)));
                }
                else {
                    enemy.add(new Sprite(Resources.alien1row3, new Point(x, y)));
                }
            }
        }
        //enemy lvl 2
        for (int x = 0; x <= 960; x+=192) {
            for (int y = 0; y <= 200; y+=200) {
                if (y==0) {
                    enemyLvl2.add(new Sprite(Resources.alien2, new Point(x, y)));
                }
                else if (y==200){
                    enemyLvl2.add(new Sprite(Resources.alien2row2, new Point(x, y)));
                }
            }
        }
        //enemy lvl 3
        for (int x = 0; x <= 960; x+=160) {
            for (int y = 0; y <= 200; y+=100) {
                if (y==100 && (x==160 ||x==480 || x==800)) { //row 2 big alien
                    enemyLvl3.add(new Sprite(Resources.alien3, new Point(x, y)));
                }
                else if (y==100 && (x==0 || x==320 || x==640 || x==960)){ //row 2 small alien
                    enemyLvl3.add(new Sprite(Resources.alien3row2, new Point(x+20, y)));
                }
                else if (y==200 || y==0){ //row 1 and row 3 small alien
                    enemyLvl3.add(new Sprite(Resources.alien3row2, new Point(x+20, y)));
                }
            }
        }
        //enemy lvl 4
        for (int x = 0; x <= 960; x+=28) {
            for (int y = 0; y <= 0; y+=200) {
                if (x>=28) {
                    enemyLvl4.add(new Sprite(Resources.alien4row2, new Point(x, y)));
                    enemyHP=10;
                }
                else{
                    enemyLvl4.add(new Sprite(Resources.alien4, new Point(x, y)));
                }
            }
        }
        //enemy lvl 5
        bosslvl5 = new Sprite(Resources.alien5, new Point(0, 0));
        bossHP=50;




        direction=1;
        direction2=1;
        direction3=1;
        direction4=1;
        direction5=1;

        BufferedImage[] pics = new BufferedImage[10];

        keys = new boolean[256];
        timer = new Timer(1000/60, e->update());
        timer.start();

        playerFrameCounter =0;
        enemyFrameCounter =0;
        mpFrameCounter=0;
        backCounter=60;


        gameFinished=false;
        gameOver=false;

        gameLevel=1;

        r=0;

        pause=false;
        togglePause=1;
        pauseFrameCounter =0;

        lvlComplete=false;
        shopOpen=false;
        optionsMenuOpen=false;



        setupKeys();

    }



    // called every frame.  All game state changes should happen here.
    // Thus, all movement, adding/removing enemies or lasers, etc.
    public void update() {
        if (!gameOver && !gameFinished && !pause) {

            //frame counters
            playerFrameCounter++;
            enemyFrameCounter++;
            mpFrameCounter++;

            //mp regeneration
            if (mp<100 &&mpFrameCounter%5==0 && !mpBoosterBought){
                mp++;
            }
            if (mpBoosterBought){
                if (mp<100 &&mpFrameCounter%3==0){
                    mp++;
                }
            }


            //player movement

            //move left / left boundary
            if (keys[KeyEvent.VK_A]) {//move image left...
                player.move(-10*spd, 0);
                }
            if (player.getX() < 0){
                player.setLocation(0, player.getY());
            }
            //move right / right boundary
            if (keys[KeyEvent.VK_D]) {//move image right...
                player.move(10*spd, 0);
                }
            if (player.getX() + player.getWidth() > getWidth()){
                    player.setLocation(getWidth() - player.getWidth(), player.getY());
            }
            //move down / bottom boundary
            if (keys[KeyEvent.VK_S]) {//move image down...
                player.move(0, 10*spd);
                }
            if (player.getY() + player.getWidth() >= 700){
                player.setLocation(player.getX(), 700 - player.getWidth());
            }
            //move up / top boundary
            if (keys[KeyEvent.VK_W]) {//move image up...
                player.move(0, -10*spd);
                }
            if (player.getY() < 0){
                player.setLocation(player.getX(), 0);
            }



            //makes shield follow player
            shield.setLocation(player.getX()-46,player.getY()-46);

            //gets player projectiles
            for (Sprite p : projectile){
                pWidth=p.getWidth();
            }

            //shoots player projectiles
            if (keys[KeyEvent.VK_SPACE] && playerFrameCounter >= 30 && !dexBoosterBought) {//shoots laser if space bar is pressed
                projectile.add(new Sprite(Resources.projectile, new Point(player.getX()+ (player.getWidth()-pWidth)/2, player.getY())));
                playerFrameCounter = 0;
            }
            if (keys[KeyEvent.VK_SPACE] && playerFrameCounter >= 5 && dexBoosterBought){//shoots laser if space bar is pressed
                projectile.add(new Sprite(Resources.projectile, new Point(player.getX()+ (player.getWidth()-pWidth)/2, player.getY())));
                playerFrameCounter = 0;
            }

            //shoots player special projectiles
            if (keys[KeyEvent.VK_Q] && mpFrameCounter >= 20 && mp>=25) {//shoots special if q is pressed
                mpProjectile.add(new Sprite(Resources.mpProjectile, new Point(player.getX()+ (player.getWidth()-pWidth)/2, player.getY())));
                mpFrameCounter = 0;
                mp-=25;
            }

            //gets enemy widths
            for (Sprite e : enemy){//gets enemy width
                eWidth=e.getWidth();
            }
            for (Sprite e : enemyLvl2){//gets enemy lvl2 width
                e2Width=e.getWidth();
            }
            for (Sprite e : enemyLvl3){//gets enemy lvl3 width
                e3Width=e.getWidth();
            }
            for (Sprite e : enemyLvl4){//gets enemy lvl4 width
                e4Width=e.getWidth();
            }
            //gets enemy lvl5 width
                e5Width=bosslvl5.getWidth();

            //gets enemy projectile widths
            for (Sprite ep : enemyProjectiles){//gets enemy projectile width
                epWidth=ep.getWidth();
            }
            for (Sprite ep : enemyProjectilesLvl2){//gets enemy lvl2 projectile width
                ep2Width=ep.getWidth();
            }
            for (Sprite ep : enemyProjectilesLvl3){//gets enemy lvl3 projectile width
                ep3Width=ep.getWidth();
            }
            for (Sprite ep : enemyProjectilesLvl4){//gets enemy lvl4 projectile width
                ep4Width=ep.getWidth();
            }

            for (Sprite ep : enemyProjectilesLvl5){//gets enemy lvl5 projectile width
                ep5Width=ep.getWidth();
            }
            for (Sprite ep : enemyProjectilesLvl5Bullet){//gets enemy lvl5 projectile width
                ep5BulletWidth=ep.getWidth();
            }
            for (Sprite ep : enemyProjectilesLvl5Laser){//gets enemy lvl5 projectile width
                ep5LaserWidth=ep.getWidth();
            }



            //shoots enemy lvl 1 lasers
            if (enemyFrameCounter >= 120) { //shoots enemy lvl 1 lasers
//                if (enemy.size() > 0) { //picks one random enemy to shoot
//                    int randomEnemy = (int) (Math.random() * enemy.size()); //finds random enemy from enemy array list
//                    //System.out.println(randomEnemy);
//                    enemyProjectiles.add(new Sprite(Resources.enemyProjectiles, new Point(enemy.get(randomEnemy).getX(), enemy.get(randomEnemy).getY())));
//                    //creates laser at random enemy location
//                    enemyFrameCounter = 0; //reset enemy frame counter
//                }

                for (int i = 0; i < enemy.size(); i++) {
                    if (Math.random()<=0.1){
                      enemyProjectiles.add(new Sprite(Resources.ep1, new Point(enemy.get(i).getX()+(eWidth-epWidth)/2, enemy.get(i).getY())));
                        enemyFrameCounter = 0;
                    }
                }
            }

            //shoots enemy lvl 2 lasers
            if (gameLevel==2) {
                if (enemyFrameCounter >= 60) { //shoots enemy lvl2 lasers
                    for (int i = 0; i < enemyLvl2.size(); i++) {
                        if (Math.random() <= 0.3) {
                            enemyProjectilesLvl2.add(new Sprite(Resources.ep2, new Point(enemyLvl2.get(i).getX() + (e2Width - ep2Width) / 2, enemyLvl2.get(i).getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                }
            }

            //shoots enemy lvl 3 lasers
            if (gameLevel==3) {
                if (enemyFrameCounter >= 60) { //shoots enemy lvl2 lasers
                    for (int i = 0; i < enemyLvl3.size(); i++) {
                        if (Math.random() <= 0.5) {
                            enemyProjectilesLvl3.add(new Sprite(Resources.ep3, new Point(enemyLvl3.get(i).getX() + (e3Width - ep3Width) / 2, enemyLvl3.get(i).getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                }
            }

            //shoots enemy lvl 4 lasers
            if (gameLevel==4) {
                if (enemyFrameCounter >= 90) { //shoots enemy lvl2 lasers
                    for (int i = 0; i < enemyLvl4.size(); i++) {
                        if (Math.random() <= 0.2) {
                            enemyProjectilesLvl4.add(new Sprite(Resources.ep4, new Point(enemyLvl4.get(i).getX() + (e4Width - ep4Width) / 2, enemyLvl4.get(i).getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                }
            }

            //shoots enemy lvl 5 lasers
            if (gameLevel==5) {
                    if (r==0) {
                        if (enemyFrameCounter >= 30) { //first fire mode
                            enemyProjectilesLvl5.add(new Sprite(Resources.ep5, new Point(bosslvl5.getX() + (e5Width - ep5Width) / 2, bosslvl5.getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                    if (r==1) {
                        if (enemyFrameCounter >= 15) { //bullet fire mode
                            enemyProjectilesLvl5Bullet.add(new Sprite(Resources.ep5a, new Point(bosslvl5.getX() + (e5Width - ep5BulletWidth) / 2, bosslvl5.getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                    if (r==2) {
                        if (enemyFrameCounter >= 1) { //laser fire mode
                            enemyProjectilesLvl5Laser.add(new Sprite(Resources.ep5b, new Point(bosslvl5.getX() + (e5Width - ep5LaserWidth) / 2, bosslvl5.getY())));
                            enemyFrameCounter = 0;
                        }
                    }
                }




            //enemy lvl 1 movement
            if (gameLevel==1) {
                hitEdge = false;
                for (Sprite e : enemy) {
                    e.move(3 * direction, 0); //moves enemy to left
                    if (e.getX() <= 0 || e.getX() + e.getWidth() >= getWidth()) { //checks if enemy is offscreen
                        hitEdge = true;
                    }
                }
                if (hitEdge) {
                    direction *= -1;
                    for (Sprite e : enemy) {
                        e.move(0, 5); //moves enemy down each time it hits edge
                    }
                }
            }

            //enemy lvl 2 movement
            if (gameLevel==2) {
                hitEdge = false;
                for (Sprite e : enemyLvl2) {
                    e.move(5 * direction2, 0); //moves enemy to left
                    if (e.getX() <= 0 || e.getX() + e.getWidth() >= getWidth()) { //checks if enemy is offscreen
                        hitEdge = true;
                    }
                }
                if (hitEdge) {
                    direction2 *= -1;
                    for (Sprite e : enemyLvl2) {
                        e.move(0, 5); //moves enemy down each time it hits edge
                    }
                }
            }

            //enemy lvl 3 movement
            if (gameLevel==3) {
                hitEdge = false;
                for (Sprite e : enemyLvl3) {
                    e.move(2 * direction3, 0); //moves enemy to left
                    if (e.getX() <= 0 || e.getX() + e.getWidth() >= getWidth()) { //checks if enemy is offscreen
                        hitEdge = true;
                    }
                }
                if (hitEdge) {
                    direction3 *= -1;
                    for (Sprite e : enemyLvl3) {
                        e.move(0, 5); //moves enemy down each time it hits edge
                    }
                }
            }

            //enemy lvl 4 movement
            if (gameLevel==4) {
                hitEdge = false;
                for (Sprite e : enemyLvl4) {
                    e.move(2 * direction4, 0); //moves enemy to left
                    if (e.getX() <= 0 || e.getX() + e.getWidth() >= getWidth()) { //checks if enemy is offscreen
                        hitEdge = true;
                    }
                }
                if (hitEdge) {
                    direction4 *= -1;
                    for (Sprite e : enemyLvl4) {
                        e.move(0, 5); //moves enemy down each time it hits edge
                    }
                }
            }

            //enemy lvl 5 movement
            if (gameLevel==5) {
                hitEdge = false;
                    bosslvl5.move(3 * direction5, 0); //moves enemy to left
                    if (bosslvl5.getX() <= 0 || bosslvl5.getX() + bosslvl5.getWidth() >= getWidth()) { //checks if enemy is offscreen
                        hitEdge = true;
                    }
                if (hitEdge) {
                    direction5 *= -1;
                        bosslvl5.move(0, 5); //moves enemy down each time it hits edge
                    r++;
                    if (r>=3){
                        r=(int)(Math.random()*3);
                    }
                }
            }


            //player projectiles and enemy collision lvl 1
            if (gameLevel==1) {
                for (int i = 0; i < enemy.size(); i++) {
                    for (int j = 0; j < projectile.size(); j++) {
                        if (enemy.get(i).intersects(projectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            projectile.remove(j);
                            enemy.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //player projectiles and enemy collision lvl 2
            if (gameLevel==2) {
                for (int i = 0; i < enemyLvl2.size(); i++) {
                    for (int j = 0; j < projectile.size(); j++) {
                        if (enemyLvl2.get(i).intersects(projectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            projectile.remove(j);
                            enemyLvl2.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //player projectiles and enemy collision lvl3
            if (gameLevel==3) {
                for (int i = 0; i < enemyLvl3.size(); i++) {
                    for (int j = 0; j < projectile.size(); j++) {
                        if (enemyLvl3.get(i).intersects(projectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            projectile.remove(j);
                            enemyLvl3.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //player projectiles and enemy collision lvl4
            if (gameLevel==4) {
                for (int i = 0; i < enemyLvl4.size(); i++) {
                    for (int j = 0; j < projectile.size(); j++) {
                        if (enemyLvl4.get(i).intersects(projectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            projectile.remove(j);
                            if (enemyHP<=1) {
                                enemyLvl4.remove(i);
                                coins++;
                                break;
                            }
                            else{
                                enemyHP-=dmg;
                            }
                        }
                    }
                }
            }

            //player projectiles and enemy collision lvl5
            if (gameLevel==5) {
                    for (int j = 0; j < projectile.size(); j++) {
                        if (bosslvl5.intersects(projectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            projectile.remove(j);
                            bossHP-=dmg;
                            if (bossHP<=0) {
                                gameFinished=true;
                                coins++;
                                break;
                            }
                    }
                }
            }



            //mp projectiles and enemy collision lvl 1
            if (gameLevel==1) {
                for (int i = 0; i < enemy.size(); i++) {
                    for (int j = 0; j < mpProjectile.size(); j++) {
                        if (enemy.get(i).intersects(mpProjectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            enemy.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //mp projectiles and enemy collision lvl 2
            if (gameLevel==2) {
                for (int i = 0; i < enemyLvl2.size(); i++) {
                    for (int j = 0; j < mpProjectile.size(); j++) {
                        if (enemyLvl2.get(i).intersects(mpProjectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            enemyLvl2.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //mp projectiles and enemy collision lvl3
            if (gameLevel==3) {
                for (int i = 0; i < enemyLvl3.size(); i++) {
                    for (int j = 0; j < mpProjectile.size(); j++) {
                        if (enemyLvl3.get(i).intersects(mpProjectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            enemyLvl3.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //mp projectiles and enemy collision lvl4
            if (gameLevel==4) {
                for (int i = 0; i < enemyLvl4.size(); i++) {
                    for (int j = 0; j < mpProjectile.size(); j++) {
                        if (enemyLvl4.get(i).intersects(mpProjectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            enemyLvl4.remove(i);
                            coins++;
                            break;
                        }
                    }
                }
            }

            //mp projectiles and enemy collision lvl5
            if (gameLevel==5) {
                    for (int j = 0; j < mpProjectile.size(); j++) {
                        if (bosslvl5.intersects(mpProjectile.get(j))) { //if enemy hitbox intersects projectile hitbox
                            bossHP-=dmg;
                            if (bossHP<=0) {
                                gameFinished=true;
                                coins++;
                                break;
                            }
                        }
                    }
                }


            //mp drain
            if (keys[KeyEvent.VK_SHIFT] && mp>0){
                mp--;
            }

            //enemy projectiles and player collision lvl 1
            if (gameLevel==1) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectiles.size() > 0) {
                        for (int j = 0; j < enemyProjectiles.size(); j++) {
                            if (player.intersects(enemyProjectiles.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectiles.remove(j);
                                hp -= 2;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectiles.size() > 0) {
                        for (int j = 0; j < enemyProjectiles.size(); j++) {
                            if (shield.intersects(enemyProjectiles.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectiles.remove(j);
                            }
                        }
                    }
                }
            }

            //enemy projectiles and player collision lvl 2
            if (gameLevel==2) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl2.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl2.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl2.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl2.remove(j);
                                hp -= 5;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl2.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl2.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl2.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl2.remove(j);
                            }
                        }
                    }
                }
            }

            //enemy projectiles and player collision lvl 3
            if (gameLevel==3) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl3.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl3.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl3.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl3.remove(j);
                                hp -= 8;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl3.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl3.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl3.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl3.remove(j);
                            }
                        }
                    }
                }
            }

            //enemy projectiles and player collision lvl 4
            if (gameLevel==4) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl4.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl4.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl4.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl4.remove(j);
                                hp -= 10;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl4.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl4.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl4.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl4.remove(j);
                            }
                        }
                    }
                }
            }

            //enemy projectiles and player collision lvl 5
            if (gameLevel==5) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl5.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl5.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5.remove(j);
                                hp -= 10;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl5.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl5.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5.remove(j);
                            }
                        }
                    }
                }
            }
            //bullet
            if (gameLevel==5) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl5Bullet.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5Bullet.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl5Bullet.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5Bullet.remove(j);
                                hp -= 10;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl5Bullet.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5Bullet.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl5Bullet.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5Bullet.remove(j);
                            }
                        }
                    }
                }
            }
            //laser
            if (gameLevel==5) {
                if (mp<=1 || !keys[KeyEvent.VK_SHIFT]) { //activates iframes

                    if (enemyProjectilesLvl5Laser.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5Laser.size(); j++) {
                            if (player.intersects(enemyProjectilesLvl5Laser.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5Laser.remove(j);
                                hp -= 1;
                            }
                        }
                    }
                }else {//enemy projectiles and shield collision
                    if (enemyProjectilesLvl5Laser.size() > 0) {
                        for (int j = 0; j < enemyProjectilesLvl5Laser.size(); j++) {
                            if (shield.intersects(enemyProjectilesLvl5Laser.get(j))) { //if player hitbox intersects enemy projectile hitbox
                                enemyProjectilesLvl5Laser.remove(j);
                            }
                        }
                    }
                }
            }


            togglePause=1;
            lvlComplete=false;

            //pauses if lvl 1 complete
            if (enemy.size() == 0 && gameLevel==1) {
                pause=true;
                lvlComplete=true;
                togglePause*=-1;
                pauseFrameCounter =0;
            }
            //pauses if lvl 2 complete
            if (enemyLvl2.size() == 0 && gameLevel==2){
                pause=true;
                lvlComplete=true;
                togglePause*=-1;
                pauseFrameCounter =0;
            }
            //pauses if lvl 3 complete
            if (enemyLvl3.size() == 0 && gameLevel==3){
                pause=true;
                lvlComplete=true;
                togglePause*=-1;
                pauseFrameCounter =0;
            }
            //pauses if lvl 4 complete
            if (enemyLvl4.size() == 0 && gameLevel==4){
                pause=true;
                lvlComplete=true;
                togglePause*=-1;
                pauseFrameCounter =0;
            }
            //you win if lvl 5 complete


            //game over
            if (hp <= 0) {
                gameOver = true; //game is over if hp is 0
            }

            repaint();
        }
        if (gameFinished||gameOver || pause) {
            if (keys[KeyEvent.VK_R]) {
                pause = false;
                gameOver = false;
                gameFinished = false;

                player.setLocation(400, 600);
                projectile = new ArrayList<Sprite>();

                enemy = new ArrayList<Sprite>();
                enemyLvl2 = new ArrayList<Sprite>();
                enemyLvl3 = new ArrayList<Sprite>();
                enemyLvl4 = new ArrayList<Sprite>();
                bosslvl5 = new Sprite(Resources.alien5, new Point(0, 0));
                bossHP=50;

                enemyProjectiles = new ArrayList<Sprite>();
                enemyProjectilesLvl2 = new ArrayList<Sprite>();
                enemyProjectilesLvl3 = new ArrayList<Sprite>();
                enemyProjectilesLvl4 = new ArrayList<Sprite>();
                enemyProjectilesLvl5 = new ArrayList<Sprite>();

                //backgrounds
                for (int x = 0; x <= 1500; x+=80) {
                    for (int y = 0; y <= 800; y += 80) {
                        back1.add(new Sprite(Resources.back1, new Point(x, y))); //create enemy sprite
                    }
                }
                for (int x = 0; x <= 1500; x+=80) {
                    for (int y = 0; y <= 800; y += 80) {
                        back2.add(new Sprite(Resources.back2, new Point(x, y))); //create enemy sprite
                    }
                }
                for (int x = 0; x <= 1500; x+=80) {
                    for (int y = 0; y <= 800; y += 80) {
                        back3.add(new Sprite(Resources.back3, new Point(x, y))); //create enemy sprite
                    }
                }
                for (int x = 0; x <= 1500; x+=80) {
                    for (int y = 0; y <= 800; y += 80) {
                        back4.add(new Sprite(Resources.back4, new Point(x, y))); //create enemy sprite
                    }
                }
                for (int x = 0; x <= 1500; x+=80) {
                    for (int y = 0; y <= 800; y += 80) {
                        back5.add(new Sprite(Resources.back5, new Point(x, y))); //create enemy sprite
                    }
                }


                hitEdge = false;

                direction = 1;
                direction2 = 1;
                direction3 = 1;
                direction4 = 1;
                direction5 = 1;


                enemyFrameCounter = 0;
                playerFrameCounter = 0;
                mpFrameCounter = 0;
                backCounter = 60;

                lvlComplete=false;
                shopOpen=false;
                optionsMenuOpen=false;


                //enemy lvl 1
                for (int x = 0; x <= 960; x += 120) {
                    for (int y = 0; y <= 200; y += 100) {
                        if (y == 0) {
                            enemy.add(new Sprite(Resources.alien1, new Point(x, y))); //create enemy sprite
                        } else if (y == 100) {
                            enemy.add(new Sprite(Resources.alien1row2, new Point(x, y)));
                        } else {
                            enemy.add(new Sprite(Resources.alien1row3, new Point(x, y)));
                        }
                    }
                }
                //enemy lvl 2
                for (int x = 0; x <= 960; x+=192) {
                    for (int y = 0; y <= 200; y+=200) {
                        if (y==0) {
                            enemyLvl2.add(new Sprite(Resources.alien2, new Point(x, y)));
                        }
                        else if (y==200){
                            enemyLvl2.add(new Sprite(Resources.alien2row2, new Point(x, y)));
                        }
                    }
                }
                //enemy lvl 3
                for (int x = 0; x <= 960; x+=160) {
                    for (int y = 0; y <= 200; y+=100) {
                        if (y==100 && (x==160 ||x==480 || x==800)) { //row 2 big alien
                            enemyLvl3.add(new Sprite(Resources.alien3, new Point(x, y)));
                        }
                        else if (y==100 && (x==0 || x==320 || x==640 || x==960)){ //row 2 small alien
                            enemyLvl3.add(new Sprite(Resources.alien3row2, new Point(x+20, y)));
                        }
                        else if (y==200 || y==0){ //row 1 and row 3 small alien
                            enemyLvl3.add(new Sprite(Resources.alien3row2, new Point(x+20, y)));
                        }
                    }
                }
                //enemy lvl 4
                for (int x = 0; x <= 960; x+=28) {
                    for (int y = 0; y <= 0; y+=200) {
                        if (x>=28) {
                            enemyLvl4.add(new Sprite(Resources.alien4row2, new Point(x, y)));
                            enemyHP=10;
                        }
                        else{
                            enemyLvl4.add(new Sprite(Resources.alien4, new Point(x, y)));
                            enemyHP=10;
                        }
                    }
                }
                //enemy lvl 5


                r=0;

                gameLevel = 1;


                spd=1;
                if (speedBoosterBought){
                    spd=2;
                }

                hp = 100;
                if (hpBoosterBought){
                    hp=150;
                }
                dmg=1;
                if (dmgBoosterBought) {
                    dmg = 5;
                }


                    mp = 0;

            }
        }
        pauseFrameCounter++;

        //pause button
        if (keys[KeyEvent.VK_ESCAPE] && togglePause==1&& pauseFrameCounter >= 20){
            pause=true;
            togglePause*=-1;
            pauseFrameCounter =0;
        }
        else if (keys[KeyEvent.VK_ESCAPE] && togglePause==-1 && pauseFrameCounter >= 20){
            pause=false;
            togglePause*=-1;
            pauseFrameCounter =0;
            shopOpen=false;
            optionsMenuOpen=false;
        }
        //next lvl button
        if (keys[KeyEvent.VK_G] && togglePause==-1 && pauseFrameCounter >= 20 && lvlComplete) {
            pause = false;
            togglePause *= -1;
            pauseFrameCounter = 0;
            gameLevel++;
        }
        //shop button
        if (keys[KeyEvent.VK_P] && togglePause==1 && pauseFrameCounter >= 20 && !shopOpen){
            pause=true;
            shopOpen=true;
            togglePause*=-1;
            pauseFrameCounter =0;
        }
        //options button
        if (keys[KeyEvent.VK_O] && togglePause==1 && pauseFrameCounter >= 20 && !optionsMenuOpen){
            pause=true;
            optionsMenuOpen=true;
            togglePause*=-1;
            pauseFrameCounter =0;
        }

        //shop items
        if (shopOpen && keys[KeyEvent.VK_1] && coins>=20){
            hpBoosterBought=true;
            coins-=20;
            hp=150;
        }
        if (shopOpen && keys[KeyEvent.VK_2] && coins>=30){
            mpBoosterBought=true;
            coins-=30;
        }
        if (shopOpen && keys[KeyEvent.VK_3] && coins>=40){
            speedBoosterBought=true;
            coins-=40;
            spd=2;
        }
        if (shopOpen && keys[KeyEvent.VK_4] && coins>=50){
            dmgBoosterBought=true;
            coins-=50;
            dmg=5;
        }
        if (shopOpen && keys[KeyEvent.VK_5] && coins>=60){
            dexBoosterBought=true;
            coins-=60;

        }



    }

    @Override
    protected void paintComponent(Graphics g) {
        if (enemy.size()==0)
         /*
            ALL drawing happens here.
            Note that ONLY drawing should happen here - any game state
            changes should happen elsewhere.
         */
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //draw backgrounds
        if (gameLevel==1) {
            g2.setColor(Color.white);
            g2.fillRect(0, 0, 1500, 1500); //sets background color
            for (Sprite background : back1) {
                background.draw(g2);
            }
        }
        if (gameLevel==2) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, 1500, 1500); //sets background color
            for (Sprite background : back2) {
                background.draw(g2);
            }
        }
        if (gameLevel==3 ) {
            g2.setColor(Color.white);
            g2.fillRect(0, 0, 1500, 1500); //sets background color

            for (Sprite background : back3) {
                background.draw(g2);
            }
        }
        if (gameLevel==4) {
            g2.setColor(Color.white);
            g2.fillRect(0, 0, 1500, 1500); //sets background color
            for (Sprite background : back4) {
                background.draw(g2);
            }
        }
        if (gameLevel==5) {
            g2.setColor(Color.white);
            g2.fillRect(0, 0, 1500, 1500); //sets background color
            for (Sprite background : back5) {
                background.draw(g2);
            }
        }


        //draw player
        player.draw(g2);
        //draw shield
        if (mp>=2 && keys[KeyEvent.VK_SHIFT]) {
            shield.draw(g2);
        }


        //draws player projectile
        for (Sprite laser : projectile) { //draws projectiles
            laser.move(0, -10);
            laser.draw(g2);
        }
        //draws special player projectile
        for (Sprite mpLaser : mpProjectile) { //draws special projectiles
            mpLaser.move(0, -5);
            mpLaser.draw(g2);
        }


        //draws enemy lvl 1 projectiles
        for (Sprite enemyLaser : enemyProjectiles) { //draws enemy projectiles
            enemyLaser.move(0, 8);
            enemyLaser.draw(g2);
        }
        //draws enemy lvl 2 projectiles
        for (Sprite enemyLaser : enemyProjectilesLvl2) { //draws enemy projectiles
            enemyLaser.move(0, 10);
            enemyLaser.draw(g2);
        }
        //draws enemy lvl 3 projectiles
        for (Sprite enemyLaser : enemyProjectilesLvl3) { //draws enemy projectiles
            enemyLaser.move(0, 5);
            enemyLaser.draw(g2);
        }
        //draws enemy lvl 4 projectiles
        for (Sprite enemyLaser : enemyProjectilesLvl4) { //draws enemy projectiles
            enemyLaser.move(0, 3);
            enemyLaser.draw(g2);
        }
        //draws enemy lvl 5 projectiles
        for (Sprite enemyLaser : enemyProjectilesLvl5) { //draws enemy projectiles
            enemyLaser.move(0, 5);
            enemyLaser.draw(g2);
        }
        for (Sprite enemyLaser : enemyProjectilesLvl5Bullet) { //draws enemy projectiles
            enemyLaser.move(0, 5);
            enemyLaser.draw(g2);
        }
        for (Sprite enemyLaser : enemyProjectilesLvl5Laser) { //draws enemy projectiles
            enemyLaser.move(0, 5);
            enemyLaser.draw(g2);
        }



        //removes offscreen player projectiles
        for (int i = 0; i < projectile.size(); i++) {
            if (projectile.get(i).getLocation().y <= -50) { //removes offscreen projectiles
                projectile.remove(i);
            }
        }
        //removes offscreen special projectiles
        for (int i = 0; i < mpProjectile.size(); i++) {
            if (mpProjectile.get(i).getLocation().y <= -50) { //removes offscreen projectiles
                mpProjectile.remove(i);
            }
        }

        //removes offscreen enemy lvl 1 projectiles
        for (int i = 0; i < enemyProjectiles.size(); i++) {
            if (enemyProjectiles.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectiles.remove(i);
            }
        }
        //removes offscreen enemy lvl 2 projectiles
        for (int i = 0; i < enemyProjectilesLvl2.size(); i++) {
            if (enemyProjectilesLvl2.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl2.remove(i);
            }
        }
        //removes offscreen enemy lvl 3 projectiles
        for (int i = 0; i < enemyProjectilesLvl3.size(); i++) {
            if (enemyProjectilesLvl3.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl3.remove(i);
            }
        }
        //removes offscreen enemy lvl 4 projectiles
        for (int i = 0; i < enemyProjectilesLvl4.size(); i++) {
            if (enemyProjectilesLvl4.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl4.remove(i);
            }
        }
        //removes offscreen enemy lvl 5 projectiles
        for (int i = 0; i < enemyProjectilesLvl5.size(); i++) {
            if (enemyProjectilesLvl5.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl5.remove(i);
            }
        }
        //removes offscreen enemy lvl 5 bullet projectiles
        for (int i = 0; i < enemyProjectilesLvl5Bullet.size(); i++) {
            if (enemyProjectilesLvl5Bullet.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl5Bullet.remove(i);
            }
        }
        //removes offscreen enemy lvl 5 laser projectiles
        for (int i = 0; i < enemyProjectilesLvl5Laser.size(); i++) {
            if (enemyProjectilesLvl5Laser.get(i).getLocation().y >= 800) { //removes offscreen enemy projectiles
                enemyProjectilesLvl5Laser.remove(i);
            }
        }




        Color ui = new Color(47,46,46);
        g2.setColor(ui);
        g2.fillRect(0,700,1500,100);

        Color BarBackground = new Color(74,74,74);
        g2.setColor(BarBackground);
        g2.fillRect(120,710,300,33);
        g2.fillRect(120,750,300,33);

        Color healthBar = new Color(190,51,51);
        g2.setColor(healthBar);
        g2.fillRect(120,710,hp*3,33);

        Color mpBar = new Color(98,109,218);
        g2.setColor(mpBar);
        g2.fillRect(120,750,mp*3,33);

        int fontSize1 = 24;
        g2.setFont((new Font("Press Start 2P", Font.PLAIN, fontSize1)));
        g2.setColor(Color.white);
        g2.drawString(" HP:   " + hp + "/100", 20, 740);
        g2.drawString(" MP:   " + mp + "/100", 20, 780);
        g2.setColor(Color.yellow);
        g2.drawString("Coins: " + coins,1200,740);
        g2.setColor(Color.white);
        g2.drawString("Shop (P)" ,1200,780);
        g2.setColor(Color.yellow);
        drawCenteredString(g2, "LEVEL: " + gameLevel, new Rectangle(0,680,1500,100), new Font("Press Start 2P", Font.PLAIN, 40));
        g2.setColor(Color.white);
        drawCenteredString(g2, "Options (O)", new Rectangle(0,720,1500,100), new Font("Press Start 2P", Font.PLAIN, 24));





        //draws enemies
        if (gameLevel==1) {//draws level 1 enemies
            for (Sprite enemy : enemy) {
                enemy.draw(g2);
            }
        }
        if (gameLevel==2){//draws level 2 enemies
            for (Sprite enemy2 : enemyLvl2){
                enemy2.draw(g2);
            }
        }
        if (gameLevel==3){//draws level 3 enemies
            for (Sprite enemy3 : enemyLvl3){
                enemy3.draw(g2);
            }
        }
        if (gameLevel==4){//draws level 4 enemies
            for (Sprite enemy3 : enemyLvl4){
                enemy3.draw(g2);
            }
        }
        if (gameLevel==5){//draws level 5 enemies
            bosslvl5.draw(g2);
        }


        //draws enemy health bars
        if (gameLevel==1) {
            for (Sprite e : enemy) {
                g2.setColor(BarBackground);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                g2.setColor(healthBar);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                g2.setColor(Color.black);
                g2.drawRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);
            }
        }
        if (gameLevel==2) {
            for (Sprite e : enemyLvl2) {
                g2.setColor(BarBackground);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                g2.setColor(healthBar);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                g2.setColor(Color.black);
                g2.drawRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);
            }
        }
        if (gameLevel==3) {
            for (Sprite e : enemyLvl3) {
                    g2.setColor(BarBackground);
                    g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                    g2.setColor(healthBar);
                    g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                    g2.setColor(Color.black);
                    g2.drawRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);
            }
        }
        if (gameLevel==4) {
            for (Sprite e : enemyLvl4) {
                g2.setColor(BarBackground);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);

                g2.setColor(healthBar);
                g2.fillRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), enemyHP*5, 5);

                g2.setColor(Color.black);
                g2.drawRect(e.getX() + e.getWidth() / 2-25, e.getY() + e.getWidth(), 50, 5);
            }
        }
        if (gameLevel==5) {
                g2.setColor(BarBackground);
                g2.fillRect(bosslvl5.getX() + bosslvl5.getWidth() / 2-25, bosslvl5.getY() + bosslvl5.getWidth(), 50, 5);

                g2.setColor(healthBar);
                g2.fillRect(bosslvl5.getX() + bosslvl5.getWidth() / 2-25, bosslvl5.getY() + bosslvl5.getWidth(), bossHP, 5);

                g2.setColor(Color.black);
                g2.drawRect(bosslvl5.getX() + bosslvl5.getWidth() / 2-25, bosslvl5.getY() + bosslvl5.getWidth(), 50, 5);
        }






        if (gameFinished) { //says "you win" if game is finished
            Color pauseBack = new Color(0,0,0,200);
            g2.setColor(pauseBack);
            g2.fillRect(0,0,1500,800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "YOU WIN!", new Rectangle(0,0,1500,800), new Font("Press Start 2P", Font.PLAIN, 80));
            drawCenteredString(g2, "RESTART (R)", new Rectangle(0,200,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));

        }
        if (gameOver) { //says "game over" if you lose
            Color pauseBack = new Color(0,0,0,200);
            g2.setColor(pauseBack);
            g2.fillRect(0,0,1500,800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "GAME OVER!", new Rectangle(0,0,1500,800), new Font("Press Start 2P", Font.PLAIN, 70));
            drawCenteredString(g2, "RESTART (R)", new Rectangle(0,200,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));

        }
        if (pause && !lvlComplete && !shopOpen && !optionsMenuOpen){
            Color pauseBack = new Color(0,0,0,200);
            g2.setColor(pauseBack);
            g2.fillRect(0,0,1500,800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "PAUSED", new Rectangle(0,0,1500,800), new Font("Press Start 2P", Font.PLAIN, 80));
            drawCenteredString(g2, "RESUME (esc)", new Rectangle(0,100,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));
            drawCenteredString(g2, "RESTART (R)", new Rectangle(0,200,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));
        }
        if (lvlComplete){
            Color back = new Color(0,0,0,200);
            g2.setColor(back);
            g2.fillRect(0,0,1500,800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "YOU WIN", new Rectangle(0,0,1500,800), new Font("Press Start 2P", Font.PLAIN, 80));
            drawCenteredString(g2, "NEXT LEVEL (G)", new Rectangle(0,100,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));
            drawCenteredString(g2, "RESTART (R)", new Rectangle(0,200,1500,800), new Font("Press Start 2P", Font.PLAIN, 40));

        }
        if (shopOpen) {
            Color back1 = new Color(0, 0, 0, 100);
            g2.setColor(back1);
            g2.fillRect(200,0,1100,800);

            Color back = new Color(0, 0, 0, 200);
            g2.setColor(back);
            g2.fillRect(0, 0, 1500, 800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "SHOP", new Rectangle(0, -300, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 80));
            drawCenteredString(g2, "RESUME (esc)", new Rectangle(0, -200, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 40));

            //draw shop sprites
                hpBoosterSprite.draw(g2);
                mpBoosterSprite.draw(g2);
                speedBoosterSprite.draw(g2);
                dmgBoosterSprite.draw(g2);
                dexBoosterSprite.draw(g2);

            g2.setFont((new Font("Press Start 2P", Font.PLAIN, fontSize1)));
            Color shopcolor = new Color(105,105,105);

            if (hpBoosterBought){
                g2.setColor(shopcolor);
            }
            else{
                g2.setColor(Color.yellow);
            }
            g2.drawString(" 20C - Boosts HP              (1)", 400,285);

            if (mpBoosterBought){
                g2.setColor(shopcolor);
            }
            else{
                g2.setColor(Color.yellow);
            }
            g2.drawString(" 30C - Boosts MP Regen        (2)", 400,385);

            if (speedBoosterBought){
                g2.setColor(shopcolor);
            }
            else{
                g2.setColor(Color.yellow);
            }
            g2.drawString(" 40C - Boosts Movement Speed  (3)", 400,485);

            if (dmgBoosterBought){
                g2.setColor(shopcolor);
            }
            else{
                g2.setColor(Color.yellow);
            }
            g2.drawString(" 50C - Boosts Damage          (4)", 400,585);

            if (dexBoosterBought){
                g2.setColor(shopcolor);
            }
            else{
                g2.setColor(Color.yellow);
            }
            g2.drawString(" 60C - Boosts Shooting Speed  (5)", 400,685);


        }
        if (optionsMenuOpen) {
            Color back = new Color(0, 0, 0, 200);
            g2.setColor(back);
            g2.fillRect(0, 0, 1500, 800);
            g2.setColor(Color.white);
            drawCenteredString(g2, "OPTIONS", new Rectangle(0, -300, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 80));
            drawCenteredString(g2, "RESUME (esc)", new Rectangle(0, -200, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 40));
            g2.setColor(Color.yellow);
            drawCenteredString(g2, "Move Up:         (W)", new Rectangle(0, -140, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Move Left:       (A)", new Rectangle(0, -100, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Move Down:       (S)", new Rectangle(0, -60, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Move Right:      (D)", new Rectangle(0, -20, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));

            drawCenteredString(g2, "Shoot:       (space)", new Rectangle(0, 40, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Special:         (Q)", new Rectangle(0, 80, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Shield:      (shift)", new Rectangle(0, 120, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));

            drawCenteredString(g2, "Shop:            (P)", new Rectangle(0, 180, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));

            drawCenteredString(g2, "Pause:         (esc)", new Rectangle(0, 240, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Restart:         (R)", new Rectangle(0, 280, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));
            drawCenteredString(g2, "Options:         (O)", new Rectangle(0, 320, 1500, 800), new Font("Press Start 2P", Font.PLAIN, 24));

        }






    }


    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }


    public void setupKeys(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
    }


    public static void main(String[] args) {


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int width = 1500;
        int height = 800;
        window.setBounds(0, 0, width, height + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Main(width, height);
        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(true);




    }
}
