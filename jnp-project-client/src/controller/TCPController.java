package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import constant.ChatConstant;
import constant.DrawConstant;
import constant.TimeConstant;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import frame.play.PlayFrame;
import sesion.GameSesion;

/**
 *
 * @author User
 */
public class TCPController {

    private Socket socket;
    private String username;
    private PlayFrame playFrame;

    public TCPController(int roomId) {
        try {
            this.socket = new Socket("localhost", roomId);
            GameSesion.getGameSesion().socket = socket;
            this.username = GameSesion.getGameSesion().userName;
            this.playFrame = new PlayFrame();
            BufferedHelper.setBuffer(socket);
            this.playFrame.show();
            listenForMessage();
            sendMessage();
            
        } catch (Exception e) {
            closeEverything(socket);
        }
    }

    public void sendMessage() {
        try {
            BufferedHelper.send(username);
            // Scanner scanner = new Scanner(System.in);
            // this.playFrame = new MainView();
            // this.playFrame.show();
            // while(socket.isConnected()){
            // String messageToSend = scanner.nextLine();
            // bufferedWriter.write(username + ": " + messageToSend);
            // bufferedWriter.newLine();
            // bufferedWriter.flush();
            // }
        } catch (Exception e) {
            closeEverything(socket);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = BufferedHelper.receive();
                        System.out.println(msgFromGroupChat);
                        if (msgFromGroupChat != null && msgFromGroupChat.isEmpty() == false) {
                            // System.out.println(msgFromGroupChat);
                            String[] pos = msgFromGroupChat.split(";");
                            if (pos.length == 0) {
                                continue;
                            }
                            String action = pos[0];
                            if (action.equals(DrawConstant.DRAW_ACTION) && pos.length == 6 ||
                                    action.equals(DrawConstant.CLEAR_ACTION) && pos.length == 1) {
                                playFrame.getPaint().handleDrawAction(msgFromGroupChat);
                            } else if (action.equals(ChatConstant.SEND_ACTION)) {
                                playFrame.getChat().updateChat(pos[1]);
                            } else if (action.equals(TimeConstant.SET_END_GAME_ACTION) && pos.length > 1) {
                                long gameDuration = (Long.parseLong(pos[1]) - new Date().getTime()) / 1000;
                                if (gameDuration > 0) {
                                    System.out.println(">>>" + gameDuration);
                                    playFrame.getCountDown().setTime(gameDuration);
                                    playFrame.showCountdown();
                                }
                                
                            } else if (action.equals(DrawConstant.SEND_HIND_WORD_ACTION) && pos.length > 1) {
                                String receivedHindWord = pos[1];
                                String formatHindWord = "";
                                for (int i = 0; i < receivedHindWord.length(); i++) {
                                    formatHindWord += receivedHindWord.charAt(i) + " ";
                                }
                                playFrame.getSwingHindWord().updateHindWord(formatHindWord.trim());
                            } else if(action.equals(DrawConstant.SEND_RESULT_WORD)){
                                GameSesion.getGameSesion().resultWord = pos[1];
                                //System.out.println(pos[1]);
                            } else if(action.equals(DrawConstant.YOUR_TURN_DRAW)){
                                String result = pos[1];
                                System.out.println(pos[1]);
                                playFrame.getStartPopUp().show(pos[1]);
                            }
                        }

                    } catch (Exception e) {
                        closeEverything(socket);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket) {
        try {
            BufferedHelper.closeEverything();
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
