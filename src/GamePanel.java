import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener {
    Ball ball = new Ball();
    Wall wall = new Wall();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.draw(g);
        wall.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ball.setDir(Dir.RIGHT);
            ball.setMoving(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ball.setDir(Dir.LEFT);
            ball.setMoving(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ball.setDir(Dir.DOWN);
            ball.setMoving(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ball.setDir(Dir.UP);
            ball.setMoving(true);
        }


    }
    @Override
    public void keyReleased(KeyEvent e) {
        ball.setMoving(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);


        Timer timer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.move();
                repaint();
            }
        });

        timer.start();
    }

}




