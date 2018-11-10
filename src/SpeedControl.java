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
        speedUp.addMouseListener(new printSpeedUp());
        slowDown.addMouseListener(new printSlowDown());
    }

    JButton getSlowDown() {
        return slowDown;
    }

    JButton getSpeedUp() {
        return speedUp;
    }

    // 绘制在加速时速度仪表盘指针
    class printSpeedUp extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            bean b = new bean();
            b.setFlag(true);
            b.setButton(true);
            setChanged();
            notifyObservers(b);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            bean b = new bean();
            b.setFlag(false);
            b.setButton(true);
            setChanged();
            notifyObservers(b);
        }
    }

    // 绘制在减速时时速度仪表盘指针
    class printSlowDown extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            bean b = new bean();
            b.setFlag(true);
            b.setButton(false);
            setChanged();
            notifyObservers(b);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            bean b = new bean();
            b.setFlag(false);
            b.setButton(false);
            setChanged();
            notifyObservers(b);
        }
    }

    public class bean{
        Boolean flag;
        Boolean constant;
        Boolean button;

        void setConstant(Boolean constant) {
            this.constant = constant;
        }

        void setFlag(Boolean flag) {
            this.flag = flag;
        }

        public void setButton(Boolean button) {
            this.button = button;
        }

        public Boolean getConstant() {
            return constant;
        }

        public Boolean getFlag() {
            return flag;
        }

        public Boolean getButton() {
            return button;
        }
    }
}
