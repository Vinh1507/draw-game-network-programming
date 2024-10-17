/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.countdown;

import frame.play.EndPopUp;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sesion.GameSesion;

/**
 *
 * @author hi
 */
public class SwingCountDown {
    private JLabel lbTime;
    private long time = 0;
    private EndPopUp endPopUp;

    public SwingCountDown() {
        endPopUp = new EndPopUp();
    }
    
    public void show(Container content) {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                lbTime = new JLabel();
                lbTime.setBounds(240, 50, 500, 25);
                content.add(lbTime);
                System.out.println(">>> time");
                System.out.println(time);
                while (time > 2) {
                    lbTime.setText(timeFomat(time));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SwingCountDown.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    time--;
                }
                content.remove(lbTime);
                try {
                    endPopUp.show(GameSesion.getGameSesion().resultWord);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SwingCountDown.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    private void resetUI(){
        
    }
    public void resize(float widthScale, float heightScale){
        lbTime.setBounds(round(240, widthScale), round(50, heightScale), round(500, widthScale), round(25, heightScale));
    }
    int round(float a, float b){
        return (int)(a*b);
    }
    public void setTime(long time) {
        this.time = time;      
    }

    private String timeFomat(long second) {
        long minute = second / 60;
        second = second % 60;
        return (minute + " : " + second);
    }
}
