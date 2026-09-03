import java.awt.*;

public class Wall {

    protected void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(350, 500, 450, 500);
    }

    public Rectangle getRect() {
        return new Rectangle(350, 497, 100, 6);
    }
}