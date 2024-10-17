/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import constant.DrawConstant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import session.ServerSession;

/**
 *
 * @author Vinh
 */
public class StartNewRoundController implements Runnable{
    public StartNewRoundController() {
       
    }
    
    @Override
    public void run() {
        while (true) {                    
            if(!ServerSession.getIsStartRound()){
                ServerSession.setIsStartRound(true);              
                setupNewRound();
                new Thread(new SendHintController()).start();
                ClientHandler.sendInitialInfoToAllUser();
            }            
        }
    }
    private void setupNewRound(){            
        Date startGame = new Date();   
        SetResultController setResultController = new SetResultController();
        ServerSession.currentWord = setResultController.getRandomResult();
        System.out.println(ServerSession.currentWord);
        ServerSession.endGame = new Date(startGame.getTime() + ServerSession.gameDuration * 1000); 
        
        ArrayList<ClientHandler> clientHandlers = ClientHandler.clientHandlers;
        if(clientHandlers.size() > 0){
            Random random = new Random();
            int randomNumber = random.nextInt(clientHandlers.size());
            ClientHandler drawClient = clientHandlers.get(randomNumber);
            drawClient.sendCurrentUser(DrawConstant.YOUR_TURN_DRAW + ";" + ServerSession.currentWord);
        }
        
    }
}
