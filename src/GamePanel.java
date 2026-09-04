import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    Ball ball = new Ball();
    Wall wall = new Wall();
    int score = 0;
    int enemyCount = 0;

    Properties properties = new Properties();

    ArrayList<Smallball> smallballs = new ArrayList<>();
    ArrayList<OtherBall> otherballs = new ArrayList<>();
    ArrayList<Bown> bowns = new ArrayList<>();




    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
        if (ball.alive) {
            ball.draw(g);
        }
        g.setColor(Color.BLACK);
        wall.draw(g);
        for (Smallball smallball : smallballs) {
            smallball.draw(g);
        }

        for (OtherBall otherBall : otherballs) {
            otherBall.draw(g);
        }
        for (Bown bown : bowns) {
            bown.draw(g);
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

            Smallball smallball = new Smallball(p.x, p.y, ball.dir, ball);
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
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);


            properties.load(fis);

            enemyCount = Integer.parseInt(
                    properties.getProperty("enemyCount")
            );

            System.out.println(enemyCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();

        for (int i = 0; i < enemyCount; i++) {
            int x = random.nextInt(700);
            int y = random.nextInt(500);

            otherballs.add(new OtherBall(x, y));
        }
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);


        Timer timer = new Timer(3, new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ball.alive) {
                    return;
                }

                if (ball.alive) {
                    ball.move();
                }

                Iterator<OtherBall> iterator = otherballs.iterator();

                while (iterator.hasNext()) {
                    OtherBall otherBall = iterator.next();

                    otherBall.move();
                    for (OtherBall otherBall2 : otherballs) {
                        if (otherBall != otherBall2
                                && otherBall.getRect().intersects(otherBall2.getRect())) {
                            otherBall.moveBack();


                        }
                        if (otherBall.getRect().intersects(ball.getRect())) {
                            otherBall.moveBack();
                        }
                    }
                    if (otherBall.firing) {
                        smallballs.add(otherBall.getBullet());
                        otherBall.firing = false;
                    }

                    if (!otherBall.alive) {
                        iterator.remove();
                    }

                }


                Iterator<Smallball> smallballIterator = smallballs.iterator();

                while (smallballIterator.hasNext()) {
                    Smallball smallball = smallballIterator.next();

                    smallball.move();
                    if (smallball.getRect().intersects(wall.getRect())) {
                        smallball.alive = false;
                    }
                    if (smallball.getRect().intersects(ball.getRect())
                            && smallball.owner != ball) {
                        ball.alive = false;
                        smallball.alive = false;
                    }

                    for (OtherBall otherBall : otherballs) {
                        if (smallball.getRect().intersects(otherBall.getRect())) {
                            if (smallball.owner == ball && otherBall.alive) {                                otherBall.alive = false;
                                score++;
                                smallball.alive = false;
                                Bown bown = new Bown(otherBall.x, otherBall.y);
                                bowns.add(bown);

                            }
                        }
                    }

                    if (!smallball.alive) {
                        smallballIterator.remove();
                    }

                }
                Iterator<Bown> bownIterator = bowns.iterator();

                while (bownIterator.hasNext()) {
                    Bown bown = bownIterator.next();

                    bown.step();
                    if (bown.step >= 100) {
                        bownIterator.remove();
                    }
                }


                repaint();
            }
        });

        timer.start();
    }

}




