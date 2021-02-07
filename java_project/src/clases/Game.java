/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import views.MainFrame;
import views.Mapa;

/**
 *
 * @author Daniel Abedoy
 */
public class Game {

    private Mapa mapa;
    private Timer timer;
    private MainFrame mainFrame;
    private Arduino control;

    public Game(Mapa mapa, MainFrame mainFrame, Arduino control) {
        this.mapa = mapa;
        this.mainFrame = mainFrame;
        this.control = control;
    }

    public void startTimer() {
        this.timer = new Timer(100, (a) -> {

            if (this.mapa.getVibora().isMuerte()) {
                this.timer.stop();
            }

            this.mapa.getVibora().mover();
            this.mapa.getVibora().comer();
            this.mapa.getVibora().muerte();
        });

        this.timer.start();
    }

    public void eventos() {
        if (control == null) {
            this.mainFrame.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent ke) {

                }

                @Override
                public void keyPressed(KeyEvent ke) {

                    if (ke.getKeyCode() == 38) {
                        mapa.getVibora().setDireccion("u"); // UP 38
                    }
                    if (ke.getKeyCode() == 40) {
                        mapa.getVibora().setDireccion("d");// DOWN 40
                    }
                    if (ke.getKeyCode() == 39) {
                        mapa.getVibora().setDireccion("r");// RIGHT 39
                    }
                    if (ke.getKeyCode() == 37) {
                        mapa.getVibora().setDireccion("l");// LEFT 37
                    }
                    if (ke.getKeyCode() == 32) {
                        mapa.restart();// SPACE 32
                    }

                }

                @Override
                public void keyReleased(KeyEvent ke) {

                }
            });
        } else {
            if (!this.control.conectar(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent spe) {
                    try {
                        String comando = control.getArduino().printMessage();

                        if (comando.equals("u")) {
                            mapa.getVibora().setDireccion("u"); // UP 38
                        }
                        if (comando.equals("d")) {
                            mapa.getVibora().setDireccion("d");// DOWN 40
                        }
                        if (comando.equals("r")) {
                            mapa.getVibora().setDireccion("r");// RIGHT 39
                        }
                        if (comando.equals("l")) {
                            mapa.getVibora().setDireccion("l");// LEFT 37
                        }
                        if (comando.equals("s")) {
                            mapa.restart();// SPACE 32
                        }
                    } catch (Exception e) {
                    }
                }
            })) {
                this.control = null;
                JOptionPane.showMessageDialog(null, "Algo salio mal, teclado activado");
                eventos();
            }
        }

    }

}
