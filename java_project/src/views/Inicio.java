/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import clases.Arduino;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Daniel Abedoy
 */
public class Inicio extends JFrame {

    public Inicio() {

        super("Inicio | Vivorita");
        this.setBounds(0, 0, 500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(56, 178, 54));
        this.setVisible(true);

        initComponents();
    }

    private void initComponents() {
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, this.getWidth(), this.getHeight());
        ImageIcon imgI = new ImageIcon(getClass().getResource("/images/fondo-inicio.gif"));
        Icon icono = new ImageIcon(imgI.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_DEFAULT));
        fondo.setIcon(icono);
        fondo.setLayout(null);
        this.add(fondo);

        JLabel titulo = new JLabel("Vivorita Game Arduino");
        titulo.setBounds(60, 20, 400, 30);
        titulo.setFont(new Font("Consolas", 2, 32));
        titulo.setForeground(Color.white);
        fondo.add(titulo);

        JLabel autor = new JLabel("por Daniel Abedoy");
        autor.setBounds(180, 53, 400, 15);
        autor.setFont(new Font("Consolas", 2, 16));
        autor.setForeground(Color.white);
        fondo.add(autor);

        JLabel descripcion = new JLabel("Selecciona el modo de juego");
        descripcion.setBounds(30, 140, 400, 15);
        descripcion.setFont(new Font("Consolas", 2, 16));
        descripcion.setForeground(Color.white);
        fondo.add(descripcion);

        JButton bt_keys = new JButton("Teclado");
        bt_keys.setBounds(40, 170, 200, 50);
        bt_keys.setBackground(new Color(130, 178, 44));
        bt_keys.setVisible(true);

        bt_keys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                new MainFrame(null);
            }
        });

        fondo.add(bt_keys);

        JButton bt_arduino = new JButton("Arduino");
        bt_arduino.setBounds(260, 170, 200, 50);
        bt_arduino.setBackground(new Color(130, 178, 44));

        bt_arduino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectPuertoArduino();
            }
        });

        bt_arduino.setVisible(true);
        fondo.add(bt_arduino);
        
        this.update(this.getGraphics());
    }

    private void selectPuertoArduino() {

        Arduino arduino = new Arduino();
        ArrayList<String> puertos = arduino.getPuertos();
        Object[] objts = new Object[puertos.size()];
        for (int i = 0; i < puertos.size(); i++) {
            objts[i] = puertos.get(i);
        }
        JComboBox cBox_opsciones = new JComboBox(objts);
        JOptionPane.showMessageDialog(this, cBox_opsciones, "Selecciona el puerto COM", JOptionPane.QUESTION_MESSAGE);
        String puerto = cBox_opsciones.getSelectedItem().toString();

        if (puerto.equals("Ningun puerto encontrado")) return;
        arduino.setPuerto(puerto);

        this.dispose();
        new MainFrame(arduino);

    }
}
