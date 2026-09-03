import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GamePanel extends JPanel implements KeyListener {
    Ball ball = new Ball();
    Wall wall = new Wall();
    ArrayList<Smallball> smallballs = new ArrayList<>();


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ball.draw(g);
        wall.draw(g);
        for (Smallball smallball : smallballs) {
            smallball.draw(g);
        }

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
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Point p = ball.getMuzzlePosition();

            Smallball smallball = new Smallball(p.x, p.y, ball.dir);
            smallballs.add(smallball);
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
                Iterator<Smallball> iterator = smallballs.iterator();

                while (iterator.hasNext()) {
                    Smallball smallball = iterator.next();

                    smallball.move();
                    if (smallball.getRect().intersects(wall.getRect())) {
                        smallball.alive = false;
                    }

                    if (!smallball.alive) {
                        iterator.remove();
                    }

                }

                repaint();
            }
        });

        timer.start();
    }

}




