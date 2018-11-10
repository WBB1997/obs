import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class MyPanle extends JPanel implements Observer {
    private static int x = 0;
    private static int y = 0;

    private static boolean flag = true; // 加速减速标记
    private static boolean constant = false; // 定速巡航标记
    private static double start = 0;
    private Timer timer;

    private JPanel tmp;

    MyPanle() {
        super();
        tmp = this;
        new Timer().schedule(new TimerTask() {
            public void run() {
                if (!flag) {
                    if(!constant) {
                        if (start >= 0)
                            start -= 0.5;
                    }
                    tmp.repaint();
                    printPoint(start * Math.PI / 180);
                }
            }
        }, 0, 100);
    }

    @Override
    public void update(Observable o, Object arg) {
        SpeedControl.bean b = (SpeedControl.bean) arg;
        flag = b.getFlag();
        if (o instanceof SpeedControl) {
            if (b.getButton()) {
                if (flag) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (start + 0.5 <= 180) {
                                tmp.repaint();
                                start += 0.5;
                                printPoint(start * Math.PI / 180);
                            }
                        }
                    }, 0, 50);
                } else
                    timer.cancel();
            } else {
                if (flag) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            if (start - 1 >= 0) {
                                tmp.repaint();
                                start -= 1;
                                printPoint(start * Math.PI / 180);
                            }
                        }
                    }, 0, 50);
                } else
                    timer.cancel();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        x = this.getWidth() / 2;
        int r = 400;
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

    // 画指针和转向灯
    void printPoint(double degrees) {
        SwingUtilities.invokeLater(() -> {
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.rotate(degrees, x, y);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(255, 69, 0));
            int X[] = {x, x - 350, x - 350, x, x};
            int Y[] = {y - 5, y - 1, y + 1, y + 3, y - 3};
            g2d.fillPolygon(X, Y, X.length);
            g2d = (Graphics2D) this.getGraphics();
            g2d.setColor(new Color(255, 69, 0));
            int lX[] = {20, 60, 60, 120, 120, 60, 60, 20};
            int lY[] = {100, 40, 70, 70, 130, 130, 160, 100};
//            if (this.isSelected())
//                g2d.fillPolygon(lX, lY, lX.length);
//            else
//                g2d.drawPolygon(lX, lY, lX.length);
//            int rX[] = {970, 930, 930, 870, 870, 930, 930, 970};
//            int rY[] = {100, 40, 70, 70, 130, 130, 160, 100};
//            if (this.isSelected())
//                g2d.fillPolygon(rX, rY, rX.length);
//            else
//                g2d.drawPolygon(rX, rY, rX.length);
            g2d.dispose();
        });
    }
}
