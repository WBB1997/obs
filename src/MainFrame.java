import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

class MainFrame extends JFrame {

    MainFrame() {
//        super();
        JPanel southPanle = new JPanel();
        // 仪表盘面板
        MyPanle northPanle = new MyPanle();
        SpeedControl speedControl = new SpeedControl();
        speedControl.addObserver(northPanle);
        DirectionControl directionControl = new DirectionControl();
        directionControl.addObserver(northPanle);
//        turnRight.addChangeListener(e -> {
//            JToggleButton toggleBtn = (JToggleButton) e.getSource();
//            if (toggleBtn.isSelected())
//                turnLeft.setSelected(false);
//        });
//        turnLeft.addChangeListener(e -> {
//            JToggleButton toggleBtn = (JToggleButton) e.getSource();
//            if (toggleBtn.isSelected())
//                turnRight.setSelected(false);
//        });
        // 按钮面板
        southPanle.setLayout(new FlowLayout(FlowLayout.CENTER));
        southPanle.add(speedControl.getSpeedUp());
        southPanle.add(speedControl.getSlowDown());
        southPanle.add(directionControl.getTurnLeft());
        southPanle.add(directionControl.getTurnRight());
        this.getContentPane().add(northPanle, BorderLayout.CENTER);
        this.getContentPane().add(southPanle, BorderLayout.SOUTH);
        this.setVisible(true);
        northPanle.printPoint(0);
    }
}