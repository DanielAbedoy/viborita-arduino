/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import com.panamahitek.PanamaHitek_Arduino;
import java.util.ArrayList;
import jssc.SerialPortEventListener;

/**
 *
 * @author Daniel Abedoy
 */
public class Arduino {
    
    private PanamaHitek_Arduino arduino;
    private String puerto;
    
    public Arduino(){
        this.arduino = new PanamaHitek_Arduino();
        this.puerto = "";
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
    
    public ArrayList<String> getPuertos(){
        ArrayList<String> puertos = new ArrayList<String>();
        
        if(this.arduino.getPortsAvailable() > 0){
            this.arduino.getSerialPorts().forEach( i -> puertos.add(i) );
        }else puertos.add("Ningun puerto encontrado");
        
        return puertos;
    }
    
    
    public boolean conectar(SerialPortEventListener ev){
        try {
            this.arduino.arduinoRX(this.puerto, 9600, ev);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    

    
    public PanamaHitek_Arduino getArduino(){
        return this.arduino;
    }
}
