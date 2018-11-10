import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    MainFrame() {
        // 按钮面板
        JPanel southPanle = new JPanel();
        // 仪表盘面板
        MyPanel northPanle = new MyPanel();
        // 速度控制
        SpeedControl speedControl = new SpeedControl();
        // 将按钮面板添加到观察列表
        speedControl.addObserver(northPanle);
        // 方向控制
        DirectionControl directionControl = new DirectionControl();
        // 将按钮面板添加到观察列表
        directionControl.addObserver(northPanle);
        // 按钮面板
        southPanle.setLayout(new FlowLayout(FlowLayout.CENTER));
        southPanle.add(speedControl.getSpeedUp());
        southPanle.add(speedControl.getSlowDown());
        southPanle.add(directionControl.getTurnLeft());
        southPanle.add(directionControl.getTurnRight());
        this.add(northPanle, BorderLayout.CENTER);
        this.add(southPanle, BorderLayout.SOUTH);
        this.setVisible(true);
        // 初始化按钮
        SwingUtilities.invokeLater(()->northPanle.printPoint(0));
    }
}