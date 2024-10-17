/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.login;

import controller.TCPController;
import frame.pickroom.PickRoomFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.JFrame;
import sesion.GameSesion;

/**
 *
 * @author Vinh
 */
public class LoginFrame {

    private JFrame loginFrame;
    private Container content;
    private JButton loginBtn;
    private JTextField nameTf;
    private JLabel applicationNameLb, loginLb, mesageLb;
    public LoginFrame() {
        loginFrame = new JFrame("Login");
        content = loginFrame.getContentPane();
    }
    public void show(){
        loginFrame.setVisible(true);
        loginBtn = new JButton();
        nameTf = new JTextField();
        applicationNameLb = new JLabel();
        loginLb = new JLabel();
        mesageLb = new JLabel();
       
        content.setLayout(null);

        loginBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginBtn.setText("Play");
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginBtnExe();
            }
        });
        nameTf.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    loginBtnExe();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        content.add(loginBtn);
        loginBtn.setBounds(160, 220, 70, 30);
       
        content.add(nameTf);
        nameTf.setBounds(160, 150, 210, 30);

        applicationNameLb.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        applicationNameLb.setText("Let's Draw");
        content.add(applicationNameLb);
        applicationNameLb.setBounds(110, 50, 210, 50);

        loginLb.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        loginLb.setText("Enter your name: ");
        content.add(loginLb);
        loginLb.setBounds(10, 150, 170, 30);
              
        mesageLb.setFont(new Font("Tahoma", 0, 18));
        mesageLb.setForeground(new Color(255, 51, 51));
        content.add(mesageLb);
        mesageLb.setBounds(120, 270, 220, 30);
        
        
        loginFrame.setSize(500, 400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }
    private void loginBtnExe(){
        if(!nameTf.getText().isEmpty()){
            GameSesion.getGameSesion().userName = nameTf.getText();         
            loginFrame.setVisible(false);
            //PickRoomFrame pickRoom = new PickRoomFrame();
            //pickRoom.show();
            TCPController room = new TCPController(1234);
        }
        else {
            mesageLb.setText("Please enter your name!");
        }
    }
}
