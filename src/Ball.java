import javax.swing.*;
import java.awt.*;

public class Ball  {
    int x = 100;
    int y = 100;


    Dir dir = Dir.RIGHT;
    boolean moving = false;


    protected void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));

        g.drawOval(x, y, 50, 50);

        if (dir == Dir.RIGHT) {
            g.fillOval(x + 55, y + 25, 10, 10);
        }

        if (dir == Dir.LEFT) {
            g.fillOval(x - 15, y + 25, 10, 10);
        }

        if (dir == Dir.UP) {
            g.fillOval(x + 25, y - 15, 10, 10);
        }

        if (dir == Dir.DOWN) {
            g.fillOval(x + 25, y + 55, 10, 10);
        }
    }
    public void move() {
        if (!moving) {
            return;
        }
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

        if (x < 0) {
            x = 0;
        }
        if (x > 750) {
            x = 750;
        }

        if (y < 0) {
            y = 0;
        }
        if (y > 550) {
            y = 550;
        }
    }
    public void setDir(Dir dir) {
        this.dir = dir;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    public Point getMuzzlePosition() {
        if (dir == Dir.RIGHT) {
            return new Point(x + 55, y + 25);
        }

        if (dir == Dir.LEFT) {
            return new Point(x - 15, y + 25);
        }

        if (dir == Dir.UP) {
            return new Point(x + 25, y - 15);
        }

        return new Point(x + 25, y + 55);
    }
}