import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bown {
    int x;
    int y;
    int step = 0;
    BufferedImage image;


    protected void draw(Graphics g) {
        g.drawImage(image, x, y, 40, 40, null);

    }
    public Rectangle getRect() {
        return new Rectangle(x, y, 5, 5);
    }
    public Bown(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            image = ImageIO.read(
                    new File("Gemini_Generated_Image_y59d3fy59d3fy59d.jpeg")
            );
            makeTransparent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void step(){
        step ++;
    }
    private void makeTransparent() {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                int rgb = image.getRGB(x, y);

                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;

                if (red < 30 && green < 30 && blue < 30) {
                    newImage.setRGB(x, y, 0x00000000);
                } else {
                    newImage.setRGB(x, y, rgb);
                }
            }
        }

        image = newImage;
    }


}
