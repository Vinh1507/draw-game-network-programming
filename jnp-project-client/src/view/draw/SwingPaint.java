package view.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import constant.DrawConstant;
import controller.BufferedHelper;

public class SwingPaint {

    JButton clearBtn, blackBtn, blueBtn, greenBtn, redBtn, magentaBtn;
    DrawArea drawArea;
    JPanel controls;
    
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearBtn) {              
                BufferedHelper.send("", DrawConstant.CLEAR_ACTION);               
                drawArea.clear();
            } else if (e.getSource() == blackBtn) {
                drawArea.black();
            } else if (e.getSource() == blueBtn) {
                drawArea.blue();
            } else if (e.getSource() == greenBtn) {
                drawArea.green();
            } else if (e.getSource() == redBtn) {
                drawArea.red();
            } else if (e.getSource() == magentaBtn) {
                drawArea.magenta();
            }
        }
    };
 

    public void show(Container content) {      
        // set layout on content pane       
        this.drawArea = new DrawArea();
        // add to content pane
        // create controls to apply colors and call clear feature
        controls = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(actionListener);
        blackBtn = new JButton("Black");
        blackBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);
        greenBtn = new JButton("Green");
        greenBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);
        magentaBtn = new JButton("Magenta");
        magentaBtn.addActionListener(actionListener);      
        controls.add(greenBtn);
        controls.add(blueBtn);
        controls.add(blackBtn);
        controls.add(redBtn);
        controls.add(magentaBtn);
        controls.add(clearBtn);
        controls.setBounds(0, 10, 500, 30);
        drawArea.setBounds(0, 80, 500, 500);
        content.add(controls);       
        content.add(drawArea);
    }
    public void resize(float widthScale, float heightScale){
        controls.setBounds(round(0, widthScale), round(10, heightScale), round(500, widthScale), round(30, heightScale));
        drawArea.setBounds(round(0, widthScale), round(80, heightScale), round(500, widthScale), round(500, heightScale));
    }
    int round(float a, float b){
        return (int)(a*b);
    }
    public Color getColorByColorStr(String colorStr) {
        String res = "";
        for (int i = 0; i < colorStr.length(); i++) {
            if (!Character.isDigit(colorStr.charAt(i))) {
                res += " ";
            } else {
                res += colorStr.charAt(i);
            }
        }
        String[] rgbStr = res.trim().split("\\s+");
        if (rgbStr.length == 3) {
            return new Color(Float.parseFloat(rgbStr[0]) / 255, Float.parseFloat(rgbStr[1]) / 255,
                    Float.parseFloat(rgbStr[2]) / 255);
        }
        return Color.black;
    }

    public void handleDrawAction(String msgFromGroupChat) {
        System.out.println(msgFromGroupChat);
        if (msgFromGroupChat != null && msgFromGroupChat.isEmpty() == false) {
            // System.out.println(msgFromGroupChat);
            String[] pos = msgFromGroupChat.split(";");
            String action = pos[0];
            if (action.equals(DrawConstant.DRAW_ACTION) && pos.length == 6) {
                int oldX = Integer.parseInt(pos[1].trim());
                int oldY = Integer.parseInt(pos[2].trim());
                int currentX = Integer.parseInt(pos[3].trim());
                int currentY = Integer.parseInt(pos[4].trim());
                // System.out.println(oldX + " " + oldY + ", " + currentX + " " + currentY);
                Color color = this.getColorByColorStr(pos[5]);
                this.drawArea.updateDraw(oldX, oldY, currentX, currentY, color);
            } else if (action.equals(DrawConstant.CLEAR_ACTION)) {
                System.out.println(222);
                this.drawArea.clear();
            }
        }
    }
}
