/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame.play;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Vinh
 */
public class EndPopUp {

    public EndPopUp() {
    }
    
    public void show(String keyword) throws InterruptedException{
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);

        JLabel label = new JLabel("Đáp Án: " + keyword);
        label.setForeground(Color.GREEN);
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // Đặt font và kích thước chữ
        Font font = new Font("Arial", Font.BOLD, 20); // Ví dụ: Font Arial, đậm, kích thước 16
        label.setFont(font);

        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        dialog.add(label);

        int delay = 3000;

        dialog.setLocationRelativeTo(null);
        dialog.setSize(400, 150);
        dialog.setVisible(true);
        Thread.sleep(delay);
        dialog.dispose();
    
    }
}


