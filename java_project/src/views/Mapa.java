/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import clases.Comida;
import clases.Game;
import clases.Viborita;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Daniel Abedoy
 */
public class Mapa extends JPanel {
    
    private ArrayList<JLabel> contorno;
    private Viborita vibora;
    private Comida comida;
    
    public Mapa(){
        this.setBounds(0,0,600,400);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(new Color(158,208,15));
        
        initBorde();
        initComida();
        initViborita();
    }
    
    private void initBorde(){
        this.contorno = new ArrayList<JLabel>();
        
        for (int i = 0; i < this.getWidth(); i+=20) {
            JLabel lb = new JLabel();
            lb.setBounds(i, 0, 20, 20);
            //Imagen ladrillo
            ImageIcon fot = new ImageIcon(getClass().getResource("/images/ladrillo.png"));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT));
            lb.setIcon(icono);
            lb.setVisible(true);
            contorno.add(lb);
        }
        
        for (int i = 0; i < this.getWidth(); i+=20) {
            JLabel lb = new JLabel();
            lb.setBounds(i, this.getHeight()-20, 20, 20);
            //Imagen ladrillo
            ImageIcon fot = new ImageIcon(getClass().getResource("/images/ladrillo.png"));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT));
            lb.setIcon(icono);
            lb.setVisible(true);
            contorno.add(lb);
        }
        
        for (int i = 20; i < this.getHeight(); i+=20) {
            JLabel lb = new JLabel();
            lb.setBounds(0, i, 20, 20);
            //Imagen ladrillo
            ImageIcon fot = new ImageIcon(getClass().getResource("/images/ladrillo.png"));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT));
            lb.setIcon(icono);
            lb.setVisible(true);
            contorno.add(lb);
        }
        
        for (int i = 20; i < this.getHeight(); i+=20) {
            JLabel lb = new JLabel();
            lb.setBounds(this.getWidth()-20, i, 20, 20);
            //Imagen ladrillo
            ImageIcon fot = new ImageIcon(getClass().getResource("/images/ladrillo.png"));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_DEFAULT));
            lb.setIcon(icono);
            lb.setVisible(true);
            contorno.add(lb);
        }
        
        
        for (int i = 0; i < contorno.size(); i++) this.add(contorno.get(i));
    }

    public Viborita getVibora() {
        return vibora;
    }

    public void setVibora(Viborita vibora) {
        this.vibora = vibora;
    }
    
    private void initViborita(){   
        int l = 20;
        this.vibora = new Viborita(l*10, l*5, l,this, this.comida);
        this.add(vibora);
        for (JLabel c : this.vibora.getCuerpo()) this.add(c);
    }

    private void initComida(){
        this.comida = new Comida(this.getWidth(),this.getHeight(), 20 );
        this.add(comida);
    }
   
    public void restart(){
        
        initBorde();
        initComida();
        initViborita();
    }
    
    
}
