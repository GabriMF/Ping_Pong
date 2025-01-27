/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gabi.pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


/**
 *
 * @author alu15d
 */

public class App extends JFrame implements KeyListener {
    
    private int windowWidth = 1000;
    private int windowHeight = 800;
    private Ball ball;
    private Racket racket;
    
    private int key=0;
    private long goal;
    private long tiempoDemora=8;
    
    private int Good;
    private int Bad;
    
    public App(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowWidth, windowHeight);
        this.setResizable(false);
        this.setLocation(100, 100);
        this.setVisible(true);
       
        this.createBufferStrategy(2);
        
        this.addKeyListener(this);
       
        initObjects();
       
        while(true) {
            ball();
            sleep();
        }         
    }
       
    private void initObjects() {
        
        ball = new Ball(windowWidth/2, windowHeight/2, 3, -3);
        racket = new Racket(windowHeight/2, 80);
    }
   
    private void ball() {
     
        ball.x = ball.x + ball.veloX;
        ball.y = ball.y + ball.veloY;
       
        checkCollision();
        
        if(ball.x <= 0 || ball.x >= windowWidth - 40){
            ball.veloX = -ball.veloX;
            Bad++;
        }
        
        if(ball.y <= 20 || ball.y >= (windowHeight - 40))
            ball.veloY = -ball.veloY;
        
        drawScreen();
    }   
    
    private void checkCollision(){
        if ( (ball.x <= 75 && ball.x >= 60) && (ball.y > racket.y) && (ball.y < racket.y + racket.alto)){
            if (ball.veloX < 0){
                Good++;
            }
            ball.veloX = -ball.veloX;
        }
        
        if ( (ball.x >= 895 && ball.x <= 910) && (ball.y > racket.y) && (ball.y < racket.y + racket.alto)){
            if (ball.veloX > 0){
                Good++;
            }
            ball.veloX = -ball.veloX;
        }
    }
   
    private void drawScreen() {
        
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
       
        try {
            g = bf.getDrawGraphics();
                
            g.setColor(Color.black);
            g.fillRect(0, 0, windowWidth, windowHeight);
            
            score(g);
            ballDraw(g);
            racketDraw(g);
            
        } finally {
            g.dispose();
        }
        bf.show();
             
        Toolkit.getDefaultToolkit().sync();
    }
   
    private void ballDraw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(ball.x, ball.y, 20, 20);
    }
    
    private void racketDraw(Graphics g) {
    
        switch (key){
            case KeyEvent.VK_UP:{
                if (racket.y>23)
                racket.y=racket.y-6;
                break;
            }    
            case KeyEvent.VK_DOWN:{
                if (racket.y<windowHeight-78)
                racket.y=racket.y+6;
                break;
            }
            case KeyEvent.VK_E:{
                System.exit(0);
            }    
        }
        g.setColor(Color.RED);
        g.fillRect(75, racket.y, 15, racket.alto);
        g.fillRect(910, racket.y, 15, racket.alto);
    }
    
    private void score(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Puntos: " + Good, 20, 50);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Faltas: - " + Bad, 20, 70);
        
        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Resultado: " + (Good - Bad), 20, 90);
    }
    
    private void sleep(){
        goal = ( System.currentTimeMillis() + tiempoDemora );
        while(System.currentTimeMillis() < goal) {
            
        }
    }
        
    @Override
    public void keyPressed(KeyEvent e){
        key=e.getKeyCode();
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
}
  
