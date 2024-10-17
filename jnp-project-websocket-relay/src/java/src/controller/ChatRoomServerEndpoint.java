/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import src.helper.BufferedHelper;

@ServerEndpoint(value = "/chatRoomServer")
public class ChatRoomServerEndpoint {

    static Set<Session> users = Collections.synchronizedSet(new HashSet<>());
    static String TCPRelayUsername = "hellofromrelay";
    static Socket tcpClientRelay = null;

    @OnOpen
    public void handleOpen(Session session) {
        users.add(session);
        if (tcpClientRelay == null) {
            try {
                createTCPClientRelay();
            } catch (IOException ex) {
                System.out.println("ERR create");
            }
        }
    }

    @OnMessage
    public void handleMessage(String message, Session userSession) throws IOException {
        String username = (String) userSession.getUserProperties().get("username");
        System.out.println("username" + username);
        if (username == null) {
            userSession.getUserProperties().put("username", message);
            userSession.getBasicRemote().sendText("System: you are connectd as " + message);
        } else {
            System.out.println("message " + message);
            for (Session session : users) {
                try {
                    String otherUsername = (String) session.getUserProperties().get("username");
                    System.out.println("");
                    if (otherUsername.equals(username) == false) {
                        session.getBasicRemote().sendText(message);
                    }
                } catch (Exception e) {
                }
            }
            BufferedHelper.send(message);
        }
    }

    @OnClose
    public void handleClose(Session session) {
        users.remove(session);
    }

    @OnError
    public void handleError(Throwable t) {
//        t.printStackTrace();
    }

    public void createTCPClientRelay() throws IOException {
        System.out.println("Create tcp relay");
        tcpClientRelay = new Socket("localhost", 1234);
        BufferedHelper.setBuffer(tcpClientRelay);
        BufferedHelper.send(TCPRelayUsername);
        listenForMessage();
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;

                while (tcpClientRelay.isConnected()) {
                    try {
                        msgFromGroupChat = BufferedHelper.receive();
                        if (msgFromGroupChat != null) {
                            System.out.println("received message in tcp relay...");
                            System.out.println(msgFromGroupChat);
                            relayFromTCPToWebsocket(TCPRelayUsername, msgFromGroupChat);
                        }
                    } catch (Exception e) {
                        closeEverything(tcpClientRelay);
                    }
                }
            }
        }).start();
    }
    
    public void relayFromTCPToWebsocket(String username, String message){
        for (Session session : users) {
            try {
                String otherUsername = (String) session.getUserProperties().get("username");
                System.out.println("");
                if (otherUsername.equals(username) == false) {
                    session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
            }
        }
    }

    public void closeEverything(Socket socket) {
        try {
            BufferedHelper.closeEverything();
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
        }
    }
}
