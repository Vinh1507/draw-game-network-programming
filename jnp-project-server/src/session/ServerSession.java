/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Date;

/**
 *
 * @author Vinh
 */
public class ServerSession {
    public static String currentWord;
    public static String currentHintWord;
    private static boolean isStartRound = false;
    public static int gameDuration = 60;
    public static Date endGame;
    public static synchronized boolean getIsStartRound(){
        return isStartRound;
    }
    public static synchronized void setIsStartRound(boolean val){
        isStartRound = val;
    }
}
