import javax.swing.*;

public class BallFrame extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("new game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);
    }
}