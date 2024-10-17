package view.hindWord;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Container;

public class SwingHindWord {
    private JLabel jLabel1 = new JLabel();
    private String hindWord;

    public void show(Container content) {
        jLabel1.setText("hindWord");
        jLabel1.setFont(new Font(Font.SANS_SERIF, 0, 16));
        jLabel1.setBounds(550, 30, 300, 30);
        content.add(jLabel1);
    }
    public void resize(float widthScale, float heightScale){
        jLabel1.setBounds(round(550, widthScale), round(30, heightScale), round(300, widthScale), round(30, heightScale));

    }
    int round(float a, float b){
        return (int)(a*b);
    }
    public void updateHindWord(String newHindWord) {
        this.hindWord = newHindWord;
        jLabel1.setText(hindWord);
    }
}
