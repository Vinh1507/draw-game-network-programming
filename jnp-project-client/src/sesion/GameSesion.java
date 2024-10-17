/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import java.net.Socket;

/**
 *
 * @author Vinh
 */
public class GameSesion {
    private static GameSesion instance;
    public String userName;
    public int userScore = 0;
    public String resultWord;
    public Socket socket;
    private  GameSesion(){       
    };
    public static GameSesion getGameSesion(){
        if(instance == null){
            instance = new GameSesion();
        }
        return instance;
    }

   
}
