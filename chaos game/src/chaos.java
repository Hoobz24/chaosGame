import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Created by 572669 on 9/13/2019.
 */
public class chaos extends JPanel{

    float x1 = 0;
    float y1 = 500;
    float x2 = 250;
    float y2 = 0;
    float x3 = 500;
    float y3 = 500;

    float pointx = 250;
    float pointy = 250;

    float fd = 0.5f;

    int amount = 2;

    int pre;
    int now;

    ArrayList<Integer> pointsX = new ArrayList<>();
    ArrayList<Integer> pointsY = new ArrayList<>();

    chaos(){
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        frame.add(this);


        while(true){
            try{
                Thread.sleep(1);

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            update();
            repaint();


        }

    }

    public void update() {
        if(pointsX.size() < 800000) {
            for (int i2 = 0; i2 < 15; i2++) {
                now = (int) Math.round(Math.random() * 4);

                if (now == pre) {
                    fd = 1f/amount;
                    amount += 2;
                } else {
                    amount = 2;
                    fd = .5f;
                }

                switch (now) {
                    case 1: {
                        pointx = lerp((float) pointx, (float) x1, fd);
                        pointy = lerp((float) pointy, (float) y1, fd);
                    }
                    break;
                    case 2: {
                        pointx = lerp((float) pointx, (float) x2, fd);
                        pointy = lerp((float) pointy, (float) y2, fd);

                    }
                    break;
                    case 3: {
                        pointx = lerp((float) pointx, (float) x3, fd);
                        pointy = lerp((float) pointy, (float) y3, fd);
                    }
                    break;

                }

                pointsX.add((int) pointx);
                pointsY.add((int) pointy);
                pre = now;

            }
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.drawString("Points: " + pointsX.size(), 10, 10);
        for(int i = 0; i < pointsX.size(); i++){
            g2d.setColor(Color.black);
            g2d.fillRect(pointsX.get(i), pointsY.get(i), 1, 1);
        }


    }

    public float lerp(float a, float b, float f){
        return a + f * (b - a);
    }
    public static void main(String args[]){
        new chaos();
    }
}
