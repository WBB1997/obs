import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

class SpeedControl extends Observable {
    // 加速按钮
    private JButton speedUp = new JButton("加速");
    // 减速按钮
    private JButton slowDown = new JButton("减速");

    SpeedControl(){
        // 绘制在加速时速度仪表盘指针
        speedUp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Bean b = new Bean();
                b.setFlag(true);
                b.setButton(true);
                setChanged();
                notifyObservers(b);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Bean b = new Bean();
                b.setFlag(false);
                b.setButton(true);
                setChanged();
                notifyObservers(b);
            }
        });
        // 绘制在减速时时速度仪表盘指针
        slowDown.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Bean b = new Bean();
                b.setFlag(true);
                b.setButton(false);
                setChanged();
                notifyObservers(b);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Bean b = new Bean();
                b.setFlag(false);
                b.setButton(false);
                setChanged();
                notifyObservers(b);
            }
        });
    }

    JButton getSlowDown() {
        return slowDown;
    }

    JButton getSpeedUp() {
        return speedUp;
    }
}
