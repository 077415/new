import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bown {
    int x;
    int y;
    int step = 0;
    Image image;


    protected void draw(Graphics g) {
        g.drawImage(image, x, y, 20, 20, null);

    }
    public Rectangle getRect() {
        return new Rectangle(x, y, 5, 5);
    }
    public Bown(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(new File("Gemini_Generated_Image_y59d3fy59d3fy59d.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(image);
    }
    public void step(){
        step ++;
    }


}
