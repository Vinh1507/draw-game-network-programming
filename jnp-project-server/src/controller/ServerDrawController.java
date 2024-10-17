/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import session.ServerSession;
import java.util.List;
import java.util.Random;


public class ServerDrawController {
    private ServerSocket serverSocket;
    private Date startGame, endGame;
    private boolean isStarted = false;  
    private int round = 5;
    private String resultWord ;
    private ClientHandler clientHandler;
    private SetResultController setResultController;
    public ServerDrawController(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        setResultController = new SetResultController();
    }
   
    public void startServer() {
        try {           
            while (!serverSocket.isClosed()) { 
                Socket socket = serverSocket.accept();      
                clientHandler = new ClientHandler(socket);
                if(!isStarted){                   
                    isStarted = true;             
                    new Thread(new StartNewRoundController()).start();
                    System.out.println("A new client has connected");
                }
                else{
                    System.out.println("A new client has connected");                                        
                    clientHandler.sendInitialInfoToCurrentUser();
                }
                                               
                Thread thread = new Thread(clientHandler);
                thread.start();
            }   
        } catch (Exception e) {
        }
    }
   
    
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        ServerDrawController server = new ServerDrawController(serverSocket);
        server.startServer();
    }
}
