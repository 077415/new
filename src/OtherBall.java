import java.awt.*;
import java.util.Random;

public class OtherBall extends Ball {
    int step = 0;
    int fireStep = 0;
    Smallball bullet;
    boolean firing = false;


    @Override
    public void move(){
        super.move();
        step++;
        fireStep++;

        if (step > 100) {
            randomDir();
            step = 0;
        }
        if (fireStep > 300) {
            fireStep = 0;
            bullet = fire();
            firing = true;

        }
    }

    public OtherBall(int x,int y){
        this.x = x;
        this.y = y;
        this.moving = true;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 50, 50);
    }

    public void randomDir() {
        Random random = new Random();
        int n = random.nextInt(4);

        if (n == 0) dir = Dir.UP;
        if (n == 1) dir = Dir.DOWN;
        if (n == 2) dir = Dir.LEFT;
        if (n == 3) dir = Dir.RIGHT;
    }
    public Smallball fire() {
        Point p = getMuzzlePosition();
        return new Smallball(p.x, p.y, dir);
    }
    public Smallball getBullet() {
        return bullet;
    }




}

