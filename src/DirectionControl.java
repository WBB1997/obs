import javax.swing.*;
import java.util.Observable;

class DirectionControl extends Observable {
    // 左转按钮
    private JToggleButton turnLeft = new JToggleButton("左转");
    // 右转按钮
    private JToggleButton turnRight = new JToggleButton("右转");

    DirectionControl(){
        // 左转按钮
        turnLeft.addChangeListener(e -> {
            JToggleButton toggleBtn = (JToggleButton) e.getSource();
            if (toggleBtn.isSelected())
                turnRight.setSelected(false);
            Bean bean = new Bean();
            bean.setButton(true);
            setChanged();
            notifyObservers(bean);
        });
        // 右转按钮
        turnRight.addChangeListener(e -> {
            JToggleButton toggleBtn = (JToggleButton) e.getSource();
            if (toggleBtn.isSelected())
                turnLeft.setSelected(false);
            Bean bean = new Bean();
            bean.setButton(false);
            setChanged();
            notifyObservers(bean);
        });
    }

    JToggleButton getTurnLeft() {
        return turnLeft;
    }

    JToggleButton getTurnRight() {
        return turnRight;
    }
}
