/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.net.Socket;
import java.util.*;

import constant.DrawConstant;
import session.ServerSession;

/**
 *
 * @author User
 */
public class ClientHandler implements Runnable {

    public  static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername, messageFromClient;
    private String hintWord;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;            
            this.bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("SERVER: " + clientUsername + " has enterd the group!");
            //this.sendInitialInfoToCurrentUser();
        } catch (IOException e) {
            closeEverything(socket, bufferReader, bufferedWriter);
        }
    }

    public void sendInitialInfoToCurrentUser() {
        sendCurrentUser(String.format("%s;%s", DrawConstant.SET_END_GAME_ACTION, "" + ServerSession.endGame.getTime()));     
        sendCurrentUser(DrawConstant.SEND_RESULT_WORD + ";" + ServerSession.currentWord);
        sendCurrentUser(DrawConstant.SEND_HIND_WORD_ACTION + ";" + ServerSession.currentHintWord);
    }
    public static void sendInitialInfoToAllUser(){
        broadcastToAllUser(String.format("%s;%s", DrawConstant.SET_END_GAME_ACTION, "" + ServerSession.endGame.getTime()));
        broadcastToAllUser(DrawConstant.SEND_RESULT_WORD + ";" + ServerSession.currentWord);
        broadcastToAllUser(DrawConstant.SEND_HIND_WORD_ACTION + ";" + ServerSession.currentHintWord);
        
    }
    

    @Override
    public void run() {       
        while (socket.isConnected()) {
            try {
                receiveMessage();
            } catch (Exception e) {
                break;
            }                  
        }
    }
    public void receiveMessage() throws IOException{        
        messageFromClient = bufferReader.readLine();
        broadcastMessage(messageFromClient);
    }
    public static void broadcastToAllUser(String messageToSend) {
        System.out.println(clientHandlers.size());
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if(clientHandler.getSocket() != null){
                    System.out.println(messageToSend);
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
                
            } catch (Exception e) {
               
            }
        }
    }
    public Socket getSocket(){
        return socket;
    }
    public void broadcastMessage(String messageToSend) {
        for (ClientHandler clientHandler : clientHandlers) {                     
            try {
                if (!clientHandler.clientUsername.equals(this.clientUsername) && clientHandler.getSocket() != null) {
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (Exception e) {
                closeEverything(socket, bufferReader, bufferedWriter);
            }
            
        }
    }

    public void sendCurrentUser(String messageToSend) {
        try {
            if(this != null){
                this.bufferedWriter.write(messageToSend);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClientHandler() {
        clientHandlers.remove(this);
        System.out.println("had left group");
        //this.broadcastMessage("SERVER: " + this.clientUsername + " has left the group!");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {  
        try {
            removeClientHandler();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
