/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.*;
import views.Mapa;

/**
 *
 * @author Daniel Abedoy
 */
public class Viborita extends JLabel{
    
    private ArrayList<JLabel> cuerpo;
    private Icon iconCuerpo;
    private int l;
    private boolean muerte;
    private String direccion;
    private Comida comida;
    private Mapa mapa;
    
    public Viborita(int x, int y,int l,Mapa mapa, Comida comida){
        
        this.setBounds(x,y,l,l);
        ImageIcon img = new ImageIcon(getClass().getResource("/images/cabeza.png"));
        Icon img_cabeza = new ImageIcon(img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        this.setIcon(img_cabeza);
        this.setVisible(true);
        
        this.l = l;
        ImageIcon imgC = new ImageIcon(getClass().getResource("/images/cuerpo.png"));
        this.iconCuerpo = new ImageIcon(imgC.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
        this.cuerpo = new ArrayList<JLabel>();
        
        initCuerpo(x, y);
        this.direccion = "r";
        this.comida = comida;
        this.mapa = mapa;
        
        this.muerte = false;
    }
    
    private void initCuerpo(int x,int y){
        
        for (int i = 1; i < 6; i++) {
            JLabel lb = new JLabel(this.iconCuerpo);
            lb.setBounds(x-(i*20),y,this.l,this.l);
            lb.setVisible(true);
            this.cuerpo.add(lb);
        }
        
    }
    
    
    public void avanzar(String dir){
        
        switch(dir){
            case "u":
                
                this.setBounds(this.getX(), this.getY()-20, this.getWidth(), this.getHeight());
                break;
                
            case "d":
                
                this.setBounds(this.getX(), this.getY()+20, this.getWidth(), this.getHeight());
                break;
                
            case "r":
                
                this.setBounds(this.getX()+20, this.getY(), this.getWidth(), this.getHeight());
                break;
                
            case "l":
                
                this.setBounds(this.getX()-20, this.getY(), this.getWidth(), this.getHeight());
                break;
        }
        
    }
    
    public void mover(){
        if(this.isMuerte())return;
        for (int i = this.cuerpo.size()-1; i >0; i--) {
            JLabel cPost = this.cuerpo.get(i-1);
            this.cuerpo.get(i).setLocation(cPost.getX(),cPost.getY());
        }
        this.cuerpo.get(0).setLocation(this.getX(), this.getY());
        this.avanzar(this.direccion);
        
    }
    
    public ArrayList<JLabel> getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(ArrayList<JLabel> cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setDireccion(String direccion) {
        if(direccion.equals("u") && this.direccion.equals("d"))return;
        if(direccion.equals("d") && this.direccion.equals("u"))return;
        if(direccion.equals("r") && this.direccion.equals("l"))return;
        if(direccion.equals("l") && this.direccion.equals("r"))return;
        this.direccion = direccion;
    }
    
    public void comer(){
        Point comida = this.comida.getPosicion();
        Point miPosicion = new Point(this.getX(), this.getY());
        if(comida.x == miPosicion.x && comida.y == miPosicion.y){
            JLabel lb = new JLabel(this.iconCuerpo);
            lb.setBounds(this.cuerpo.get(this.cuerpo.size()-1).getX(),this.cuerpo.get(this.cuerpo.size()-1).getY(),this.l,this.l);
            lb.setVisible(true);
            this.cuerpo.add(lb);
            this.mapa.add(this.cuerpo.get(this.cuerpo.size()-1));
            this.comida.comido();
        }
    }
    
    public void muerte(){
        if(this.getX() == 0 || this.getX() == this.mapa.getWidth()-this.l) this.muerte= true;
        if(this.getY() == 0 || this.getY() == this.mapa.getHeight()-this.l) this.muerte= true;
    }

    public boolean isMuerte() {
        return muerte;
    }
    
}
