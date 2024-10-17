package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class BufferedHelper {
    
    private static BufferedWriter bufferedWriter;
    private static BufferedReader bufferedReader;
    public static void setBuffer(Socket client){
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {           
            e.printStackTrace();
        }
    }
    public static void send(String data){
        try {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean send(String data, String action ){
        try {
            bufferedWriter.write(action + ";" + data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String receive(){
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {            
            e.printStackTrace();
        }
        return null;
    }

    public static void closeEverything(){      
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
        catch (IOException e) {               
            e.printStackTrace();
        }               
    }
}
