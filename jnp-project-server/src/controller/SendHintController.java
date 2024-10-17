package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import constant.DrawConstant;
import java.util.*;
import session.ServerSession;

public class SendHintController implements Runnable {
    private String resultWord;
    private String hindWord;
    private int timer;
    private int TIME_BETWEEN_SEND_HIND;

    public SendHintController() {      
        this.resultWord = ServerSession.currentWord;
        this.timer = ServerSession.gameDuration;
        this.initHindWord(resultWord);
        ServerSession.currentHintWord = hindWord;
    }

    public void initHindWord(String resultWord) {
        this.hindWord = "";
        for (int i = 0; i < resultWord.length(); i++) {
            if (resultWord.charAt(i) != ' ')
                this.hindWord += "_";
            else
                this.hindWord += " ";
        }
        
    }
    public void SET_TIME_BETWEEN_SEND_HIND(String resultWord) {
        int charNumber = 0;
        for (int i = 0; i < resultWord.length(); i++) {
            if (resultWord.charAt(i) != ' ')
                charNumber ++;
        }
        TIME_BETWEEN_SEND_HIND = timer/charNumber + 1;
    }
    public void getHint(){
        
    }
    public void run() {
        
        SET_TIME_BETWEEN_SEND_HIND(this.resultWord);            
        while (timer > 0) {
            try {
                Thread.sleep(1000);
                if (timer % TIME_BETWEEN_SEND_HIND == 0 && this.hindWord.contains("_")) {
                    this.hindWord = this.updateHindWord();
                    ServerSession.currentHintWord = hindWord;
                    ClientHandler.broadcastToAllUser(DrawConstant.SEND_HIND_WORD_ACTION + ";" + hindWord);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            timer--;
        }       
        ServerSession.setIsStartRound(false); 
    }

    public String updateHindWord() {
        int hindPos = 0;
        ArrayList<Integer> hindPosList = new ArrayList<>();
        for (int i = 0; i < hindWord.length(); i++) {
            if (hindWord.charAt(i) == '_') {
                hindPosList.add(i);
            }
        }
        Random rand = new Random();
        hindPos = hindPosList.get(rand.nextInt(hindPosList.size()));
        StringBuilder sb = new StringBuilder(this.hindWord);
        sb.setCharAt(hindPos, resultWord.charAt(hindPos));
        return sb.toString();
    }
}
