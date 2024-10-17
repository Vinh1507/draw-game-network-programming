/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chat;

import java.awt.Container;
import java.awt.Font;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constant.ChatConstant;
import controller.BufferedHelper;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import sesion.GameSesion;

/**
 *
 * @author Vinh
 */
public class SwingChat {
    private JTextField chatField;
    private JLabel chatTilte,userScoreLb;
    private JButton sendBtn;
    private JTextArea chatArea;
    private JScrollPane jScrollPane;
   
    public void show(Container content) { 
        chatField = new JTextField();
        chatTilte = new JLabel();
        userScoreLb = new JLabel();
        sendBtn = new JButton();
        chatArea = new JTextArea();
        jScrollPane = new JScrollPane();
        
        chatTilte.setText("Group chat");     
        userScoreLb.setText("Score: " + 0);
        chatArea.setEnabled(false);
        chatArea.setFont(new Font("Tahoma", 0, 20));
        chatArea.setForeground(Color.BLACK);       
        sendBtn.setText("Send");      
        
        
        chatTilte.setBounds(550, 10, 150, 30);
        userScoreLb.setBounds(750, 10, 150, 30);
        jScrollPane.setBounds(510, 80, 350, 420);
        chatField.setBounds(510, 510, 275, 30);
        sendBtn.setBounds(790, 510, 70, 30);
        
        chatField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendBtnEvent();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {              
                sendBtnEvent();
            }
        });
        content.add(chatTilte);
        content.add(userScoreLb);
        jScrollPane.setViewportView(chatArea);
        content.add(jScrollPane);
        content.add(chatField);
        content.add(sendBtn);
    }
    public void resize(float widthScale, float heightScale){
        chatTilte.setBounds(round(550, widthScale), round(10, heightScale), round(150, widthScale), round(30, heightScale));
        userScoreLb.setBounds(round(750, widthScale), round(10, heightScale), round(150, widthScale), round(30, heightScale));
        jScrollPane.setBounds(round(510, widthScale), round(80, heightScale), round(350, widthScale), round(420, heightScale));
        chatField.setBounds(round(510, widthScale), round(510, heightScale), round(275, widthScale), round(30, heightScale));
        sendBtn.setBounds(round(790, widthScale), round(510, heightScale), round(70, widthScale), round(30, heightScale));

     }
    int round(float a, float b){
        return (int)(a*b);
    }
    public void updateChat(String message) {
        chatArea.append(message + "\n");
    }
    private void sendBtnEvent(){
        if(chatField.getText().toLowerCase().equals(GameSesion.getGameSesion().resultWord.toLowerCase())){
            answerCorrect();
            chatField.setEditable(false);
            sendBtn.setEnabled(false);
        }
        else{
            String message = GameSesion.getGameSesion().userName + ": " + chatField.getText();
            BufferedHelper.send(message, ChatConstant.SEND_ACTION);
            chatArea.append(message + "\n") ;
            chatField.setText("");
        }
        
    }
    private void answerCorrect(){
        GameSesion.getGameSesion().userScore += 10;
        userScoreLb.setText("Score: " + GameSesion.getGameSesion().userScore );
        chatArea.append(GameSesion.getGameSesion().userName + " has guest correct answer\n") ;
        chatField.setText("");
    }
}
