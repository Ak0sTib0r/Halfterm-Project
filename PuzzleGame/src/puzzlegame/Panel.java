/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author akosh
 */

public class Panel extends JPanel implements ActionListener{
    
    int levelIndex = 1;
    
    static final int SCREEN_WIDTH = 825;
    static final int SCREEN_HEIGHT = 625;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    
    int leftPlayerXCoordinate = 200;
    int leftPlayerYCoordinate = 300;
    
    int rightPlayerXCoordinate = 600;
    int rightPlayerYCoordinate = 300;
    
    int leftGoalX = UNIT_SIZE;
    int leftGoalY = UNIT_SIZE;
    
    int rightGoalX = SCREEN_WIDTH - (2*UNIT_SIZE);
    int rightGoalY = SCREEN_HEIGHT - (2*UNIT_SIZE);
    
    Timer timer;
    
    boolean run = false;
    
    Panel(){
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(0, 0, 0));
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
        
    }
    
    public void startGame(){
        run = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
        
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g); 
    }
    
    //Draw method
    public void draw(Graphics g){
        
        for(int i = 0; i < SCREEN_HEIGHT; i++){
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
        }
        
        for(int j = 0; j < SCREEN_WIDTH; j++){
            g.drawLine(0, j * UNIT_SIZE, SCREEN_WIDTH, j * UNIT_SIZE);
        }
        
        //Red Goal
        g.setColor(Color.YELLOW);
        g.fillRect(leftGoalX, leftGoalY, UNIT_SIZE, UNIT_SIZE);
        //
           
        g.setColor(Color.RED);
        g.fillRect(leftPlayerXCoordinate, leftPlayerYCoordinate, UNIT_SIZE, UNIT_SIZE);
        
        //Blue Goal
        g.setColor(Color.MAGENTA);
        g.fillRect(rightGoalX, rightGoalY, UNIT_SIZE, UNIT_SIZE);
        //
        
        g.setColor(Color.BLUE);
        g.fillRect(rightPlayerXCoordinate, rightPlayerYCoordinate, UNIT_SIZE, UNIT_SIZE);
        
        g.setColor(Color.GRAY);
        g.fillRect((SCREEN_WIDTH - UNIT_SIZE)/2, 0, UNIT_SIZE, SCREEN_HEIGHT);
                  
    }
    
    public void checkSuccess(){
        if ((leftPlayerXCoordinate == leftGoalX) && (leftPlayerYCoordinate == leftGoalY)){
            if ((rightPlayerXCoordinate == rightGoalX) && (rightPlayerYCoordinate == rightGoalY)){
                levelIndex += 1;
                
                System.out.println(levelIndex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    //Movement + Collisions with boundaries
    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (leftPlayerXCoordinate > 0){
                       leftPlayerXCoordinate -= UNIT_SIZE;  
                    }
                    
                    if (rightPlayerXCoordinate < SCREEN_WIDTH - UNIT_SIZE){
                        rightPlayerXCoordinate += UNIT_SIZE;
                    }
                    
                    checkSuccess();
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if (leftPlayerXCoordinate < ((SCREEN_WIDTH - UNIT_SIZE)/2) - UNIT_SIZE){
                         leftPlayerXCoordinate += UNIT_SIZE;
                    }
                    
                    if (rightPlayerXCoordinate > ((SCREEN_WIDTH - UNIT_SIZE)/2) + UNIT_SIZE){
                        rightPlayerXCoordinate -= UNIT_SIZE;
                    }
                    
                    checkSuccess();
                    break;
                    
                case KeyEvent.VK_UP:
                    if (leftPlayerYCoordinate > 0){
                        leftPlayerYCoordinate -= UNIT_SIZE;
                    }
                    
                    if (rightPlayerYCoordinate < SCREEN_HEIGHT - UNIT_SIZE){
                        rightPlayerYCoordinate += UNIT_SIZE;
                    }
                    
                    checkSuccess();
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if (leftPlayerYCoordinate < SCREEN_HEIGHT - UNIT_SIZE){
                        leftPlayerYCoordinate += UNIT_SIZE;
                    }
                    
                    if (rightPlayerYCoordinate > 0){
                        rightPlayerYCoordinate -= UNIT_SIZE;
                    }
                    
                    checkSuccess();
                    break;
            }
        }
    }
    
}
