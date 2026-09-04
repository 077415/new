import javax.swing.*;
import java.awt.*;

public class Smallball {
    int x;
    int y;
    Dir dir = Dir.RIGHT;
    boolean alive = true;

    Ball owner;

    public Smallball(int x, int y, Dir dir,Ball owner) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.owner = owner;


    }

    protected void draw(Graphics g) {
        Graphics2D g2d =(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval(x,y,5,5);
    }
    public void move() {
        if (dir == Dir.RIGHT) {
            x += 1;
        }
        if (dir == Dir.LEFT) {
            x -= 1;
        }
        if (dir == Dir.UP) {
            y -= 1;
        }
        if (dir == Dir.DOWN) {
            y += 1;
        }
        if (x < 0 || x > 800 || y < 0 || y > 600) {
            alive = false;
        }
    }


    public Rectangle getRect() {
        return new Rectangle(x, y, 5, 5);
    }
}