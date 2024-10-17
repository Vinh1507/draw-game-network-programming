/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.pickroom;

import constant.DrawConstant;
import controller.BufferedHelper;
import controller.TCPController;
import frame.play.PlayFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Vinh
 */
public class PickRoomFrame {
    private JFrame pickRoomFrame;
    private Container content;
    private PlayFrame playFrame;
    public PickRoomFrame() {
        pickRoomFrame = new JFrame("Choose Room");
        playFrame = new PlayFrame();
        content = pickRoomFrame.getContentPane();
        content.setLayout(null);
    }
    public void show(){
        pickRoomFrame.setVisible(true);
        initialUI();
        
        pickRoomFrame.setSize(500, 500);
        pickRoomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }
    public void initialUI(){
        JButton roomBtn = new JButton();
        roomBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        roomBtn.setText("Start");      
        content.add(roomBtn);
        roomBtn.setBounds(10, 150, 170, 30);
        roomBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pickRoomFrame.setVisible(false);
                TCPController room = new TCPController(1234);               
            }
        });
    }
}
