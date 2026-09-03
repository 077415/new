import javax.swing.*;
import java.awt.*;

public class BallFrame extends JFrame{
    public static void main(String[]args){
        JFrame frame  = new JFrame("new game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        frame.setVisible(true);

        Ball ball = new Ball();
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setVisible(true);

        Wall wall= new Wall();

        frame.setVisible(true);


    }
}



