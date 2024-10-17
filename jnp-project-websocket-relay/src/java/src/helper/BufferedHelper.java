/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.helper;

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
            System.out.println("ERR1");
        }
    }
    public static void send(String data){
        try {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("ERR2");
        }
    }
    public static boolean send(String data, String action ){
        try {
            bufferedWriter.write(action + ";" + data);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            return true;
        } catch (IOException e) {
            System.out.println("ERR3");
        }
        return false;
    }
    public static String receive(){
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {  
            System.out.println("ERR3.55555555555555555");
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
            System.out.println("ERR4");
        }               
    }
}
