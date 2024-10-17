/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.play;

import constant.DrawConstant;
import controller.BufferedHelper;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sesion.GameSesion;
import view.chat.SwingChat;
import view.countdown.SwingCountDown;
import view.draw.SwingPaint;
import view.hindWord.SwingHindWord;

/**
 *
 * @author Vinh
 */
public class PlayFrame {

    private SwingPaint paint;
    private SwingChat chat;
    private SwingCountDown countDown;
    private SwingHindWord swingHindWord;
    private StartPopUp startPopUp;
    private Container content;
    private JFrame frame;
    private boolean autoShowPaint = true;
    private boolean autoShowChat = true;
    private boolean autoShowCountdown = true;
    private boolean autoShowHindword = true;

    public PlayFrame() {
        frame = new JFrame("Draw");
        content = frame.getContentPane(); 
        startPopUp = new StartPopUp();
    }

    public void show() {
        content.setLayout(null);
        paint = new SwingPaint();
        if (autoShowPaint) {
            paint.show(content);
        }
        chat = new SwingChat();
        if (autoShowChat) {
            chat.show(content);
        }
        countDown = new SwingCountDown();

        if (autoShowCountdown) {
            countDown.show(content);
        }

        swingHindWord = new SwingHindWord();
        if (autoShowHindword) {
            swingHindWord.show(content);
        }

        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println("Client had close");
                    GameSesion.getGameSesion().socket.close();
                    //BufferedHelper.send(DrawConstant.REMOVE_CLIENT_ACTION);
                } catch (IOException ex) {
                    Logger.getLogger(PlayFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                float widthScale = (float)frame.getWidth() / 900;
                float heightScale = (float)frame.getHeight() / 600; 
                paint.resize(widthScale, heightScale);
                chat.resize(widthScale, heightScale);
                countDown.resize(widthScale, heightScale);
                swingHindWord.resize(widthScale, heightScale);
            }
        });
    }

    public SwingPaint getPaint() {
        return paint;
    }
    public StartPopUp getStartPopUp(){
        return startPopUp;
    }
    public SwingChat getChat() {
        return chat;
    }

    public SwingCountDown getCountDown() {
        return countDown;
    }

    public SwingHindWord getSwingHindWord() {
        return swingHindWord;
    }

    public void showCountdown() {
        this.countDown.show(content);
    }
}
