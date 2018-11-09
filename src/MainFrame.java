import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class MainFrame extends JFrame {
    private JPanel NorthPanle; // 仪表盘面板

    private static int x;
    private static int y;
    private static int r = 400;
    private static boolean flag = false;
    private static double start = 0;
    private Timer timer;

    MainFrame() {
        super();
        // 加速按钮
        JButton speedUp = new JButton("加速");
        // 减速按钮
        JButton slowDown = new JButton("减速");
        // 左转按钮
        JButton turnLeft = new JButton("左转");
        // 右转按钮
        JButton turnRight = new JButton("右转");
        speedUp.addMouseListener(new printSpeedUp());
        slowDown.addMouseListener(new printSlowDown());
        // 按钮面板
        JPanel southPanle = new JPanel();
        NorthPanle = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                x = NorthPanle.getWidth() / 2;
                y = 21 + r;
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // 画背景
                g2d.setColor(new Color(0, 0, 0));
                g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
                // 画面板
                g2d.setColor(new Color(48, 48, 48));
                g2d.fillArc(x - r, y - r, r * 2, r * 2, 0, 180);
                // 画刻度 和 刻度值
                Color mainColor = new Color(123, 123, 123);
                Color subColor = new Color(173, 173, 173);
                Color strColor = new Color(255, 255, 255);
                int mainLen = 15;
                int subLen = 5;
                Stroke mainStroke = new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
                Stroke subStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
                g2d.setFont(new Font("微软雅黑", Font.BOLD, 14));
                for (int i = 0; i <= 180; i++) {
                    Point Start = new Point((int) (x - Math.cos(i * Math.PI / 180) * r + 0.5), (int) (y - Math.sin(i * Math.PI / 180) * r + 0.5));
                    Point end;
                    if (i % 10 == 0) {
                        g2d.setColor(mainColor);
                        g2d.setStroke(mainStroke);
                        end = new Point((int) (x - Math.cos(i * Math.PI / 180) * (r - mainLen) + 0.5), (int) (y - Math.sin(i * Math.PI / 180) * (r - mainLen) + 0.5));
                        g2d.drawString(Integer.toString(i),
                                (int) (x - Math.cos(i * Math.PI / 180) * (r - mainLen - Integer.toString(i).length() - 20)) - 9,
                                (int) (y - Math.sin(i * Math.PI / 180) * (r - mainLen - Integer.toString(i).length() - 20)));
                    } else {
                        g2d.setColor(subColor);
                        g2d.setStroke(subStroke);
                        end = new Point((int) (x - Math.cos(i * Math.PI / 180) * (r - subLen) + 0.5), (int) (y - Math.sin(i * Math.PI / 180) * (r - subLen) + 0.5));
                    }
                    g2d.drawLine(Start.x, Start.y, end.x, end.y);
                }
                // 画中心点
                g2d.setColor(new Color(123, 123, 123));
                g2d.fillArc(x - 25, y - 25, 50, 50, 0, 180);
            }
        };
        southPanle.setLayout(new FlowLayout(FlowLayout.CENTER));
        southPanle.add(speedUp);
        southPanle.add(slowDown);
        southPanle.add(turnLeft);
        southPanle.add(turnRight);
        this.getContentPane().add(NorthPanle, BorderLayout.CENTER);
        this.getContentPane().add(southPanle, BorderLayout.SOUTH);
        this.setVisible(true);
        printPoint(start * Math.PI / 180);
        Timer newTimer = new Timer();
        newTimer.schedule(new TimerTask() {
            public void run() {
                if (flag) {
                    if(start >= 0) {
                        NorthPanle.repaint();
                        start -= 0.5;
                        printPoint(start * Math.PI / 180);
                    }
                }
            }
        }, 0, 100);
    }

    // 画指针
    private void printPoint(double degrees) {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = (Graphics2D) NorthPanle.getGraphics();
            g2d.rotate(degrees, x, y);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(255, 69, 0));
            int X[] = {x, x - 350, x - 350, x, x};
            int Y[] = {y - 5, y - 1, y + 1, y + 3, y - 3};
            g2d.fillPolygon(X, Y, 5);
        });
    }

    // 绘制在加速时速度仪表盘指针
    class printSpeedUp extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            flag = false;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (start <= 180) {
                        NorthPanle.repaint();
                        start += 0.5;
                        printPoint(start * Math.PI / 180);
                    }
                    System.out.println("mousePressed");
                }
            }, 0, 100);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            timer.cancel();
            flag = true;
            System.out.println("mouseReleased");
        }
    }

    // 绘制在减速时时速度仪表盘指针
    class printSlowDown extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if(start >= 0) {
                        NorthPanle.repaint();
                        start -= 0.5;
                        printPoint(start * Math.PI / 180);
                    }
                    System.out.println("mousePressed");
                }
            }, 0,100);
            System.out.println("mousePressed");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            timer.cancel();
            System.out.println("mouseReleased");
        }
    }
}