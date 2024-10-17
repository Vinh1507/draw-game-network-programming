/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Random;



public class SetResultController {
    private ArrayList<String> listResultWord = new ArrayList<>();
    public SetResultController(){
        addNewResultWord();
    }
    public void addNewResultWord() {
        listResultWord.add("CON MEO");
        listResultWord.add("XE DAP");
        listResultWord.add("CAI KEO");
        listResultWord.add("TRONG CAY");
        listResultWord.add("MAT TRANG");
        listResultWord.add("NAU AN");
        listResultWord.add("MOI HO RANG LANH");
        listResultWord.add("DOC SACH");
        listResultWord.add("MOT CON NGUA DAU CA TAU BO CO");
        listResultWord.add("CAY NHA LA VUON");
        listResultWord.add("BUN DAU MAM TOM");
        listResultWord.add("AN QUA NHO KE TRONG CAY");
        listResultWord.add("UONG NUOC NHO NGUON");
        listResultWord.add("LA RUNG VE COI");
        listResultWord.add("ANH EM NHU THE TAY CHAN");
    }
    public String getRandomResult() {
        Random random = new Random();
        int randomNumber = random.nextInt(listResultWord.size());
        return listResultWord.get(randomNumber);
    }
}
