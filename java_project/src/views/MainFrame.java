/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import clases.Arduino;
import clases.Game;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import views.Mapa;

/**
 *
 * @author Daniel Abedoy
 */
public class MainFrame extends JFrame{
    
    private Mapa mapa;
    private Game game;
    private Arduino control;
    
    public MainFrame(Arduino control){
        
        super("Viborita Arduino Game");
        
        this.setBounds(0,0,700,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(51,60,101));
        
        this.control = control;
        initComponents();
    }
    
    private void initComponents(){
        
        //Mapa
        this.mapa = new Mapa();
        this.mapa.setLocation(50,35);
        this.add(this.mapa);
        
        //Game
        this.game = new Game(this.mapa,this, this.control);
        this.game.startTimer();
        this.game.eventos();
    }
    
    
}
