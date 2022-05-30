/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import javax.swing.JFrame;

/**
 *
 * @author akosh
 */
public class Frame extends JFrame{
    
    Frame(){
        
        this.add(new Panel());
        this.setTitle("Puzzle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
    
}
