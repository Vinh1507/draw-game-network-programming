package view.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;

import constant.DrawConstant;
import controller.BufferedHelper;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class DrawArea extends JComponent {
    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    private Color oldColor = Color.black;

    public DrawArea() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                // System.out.println(currentX + " " + currentY);
                String data = String.format("%s;%d;%d;%d;%d;%s", DrawConstant.DRAW_ACTION, oldX, oldY,
                        currentX, currentY, g2.getColor().toString());
                BufferedHelper.send(data);
                if (g2 != null) {
                    g2.setPaint(oldColor);
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    // refresh draw area to repaint
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                    oldColor = g2.getColor();
                }
            }
        });
    }

    private void initialDraw() {
        int t1 = 10, t2 = 10;
        for (int i = 0; i < 200; i++) {
            g2.drawLine(t1, t2, t1 + 1, t2 + 1);
            t1++;
            t2++;
        }
    }

    public void updateDraw(int oldX, int oldY, int currentX, int currentY, Color color) {
        if (g2 != null) {
            // draw line if g2 context not null
            g2.setPaint(color);
            g2.drawLine(oldX, oldY, currentX, currentY);
            // refresh draw area to repaint
            repaint();
         }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            // Khởi tạo hình ảnh với kích thước ban đầu của thành phần
            image = createImage(getWidth(), getHeight());
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //clear();
            initialDraw();
        }
 
        g.drawImage(image, 0, 0, null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Gọi hàm cập nhật kích thước hình ảnh khi cửa sổ thay đổi kích thước
                updateImageSize(getWidth(), getHeight());
            }
        });
    }

    private void updateImageSize(int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D newG2 = newImage.createGraphics();
        newG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Sao chép nội dung từ hình ảnh cũ vào hình ảnh mới
        newG2.drawImage(image, 0, 0, null);
       
        // Cập nhật biến tham chiếu đến hình ảnh mới
        image = newImage;       
        g2 = newG2;
        //clear();
        repaint(); // Yêu cầu vẽ lại thành phần
    }
    // create exposed methods
    public void clear() {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();
    }

    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
        oldColor = Color.red;
    }

    public void black() {
        g2.setPaint(Color.black);
        oldColor = Color.black;
    }

    public void magenta() {
        g2.setPaint(Color.magenta);
        oldColor = Color.magenta;
    }

    public void green() {
        g2.setPaint(Color.green);
        oldColor = Color.green;
    }

    public void blue() {
        g2.setPaint(Color.blue);
        oldColor = Color.blue;
    }
}
