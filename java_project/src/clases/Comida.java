package clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Abedoy
 */
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;

public class Comida extends JLabel {
    
    private Icon iconComida;
    private int w, h, l;
    
    public Comida(int w, int h, int l){
        
        this.w = w;
        this.h=h;
        this.l = l;
        
        Point p = locationRandom();
        this.setBounds(p.x, p.y, l, l);
        this.setVisible(true);
        
        ImageIcon imgC = new ImageIcon(getClass().getResource("/images/comida.png"));
        this.iconComida = new ImageIcon(imgC.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(iconComida);
    }
    
    public Point locationRandom(){
        Random r = new Random();
        return new Point((r.nextInt((this.w/l)-2)*l)+l,(r.nextInt((this.h/l)-2)*l)+l );
    }
    
    
    public Point getPosicion(){
        return new Point(this.getX(), this.getY());
    }
    
    public void comido(){
        this.setLocation(this.locationRandom());
    }
    
}
