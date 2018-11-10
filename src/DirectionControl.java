import javax.swing.*;
import java.util.Observable;

public class DirectionControl extends Observable {
    // 左转按钮
    private JToggleButton turnLeft = new JToggleButton("左转");
    // 右转按钮
    private JToggleButton turnRight = new JToggleButton("右转");

    public DirectionControl(){

    }

    public JToggleButton getTurnLeft() {
        return turnLeft;
    }

    public JToggleButton getTurnRight() {
        return turnRight;
    }
}
