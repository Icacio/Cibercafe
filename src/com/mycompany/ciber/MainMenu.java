package com.mycompany.ciber;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
        
public class MainMenu extends JFrame implements ActionListener {
    private JButton botónTiempo;
    private JButton botónActividad;
    private JButton botónPrecios;
    private JTextField biemField;
    private final JLabel[] pcNumero = new JLabel[6];
    private final JToggleButton[] button = new JToggleButton[6];
    public static User user[] = new User[6];
    
    public MainMenu() {
        int screen_size = Toolkit.getDefaultToolkit().getScreenSize().width/5*4;
        int s = screen_size/10;
        Font font_size = new Font("SansSerif.plain", 0,13);
        
        botónTiempo = new JButton();
        botónActividad = new JButton();
        botónPrecios = new JButton();
        biemField = new JTextField();
        
        for (int i = 0; i < 6;i++) {
            button[i] = new JToggleButton();
            pcNumero[i] = new JLabel();
        }
        
        getContentPane().setBackground(new Color(0, 255, 255));
        setLayout(null);
        setSize(screen_size,screen_size*10/16);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        for (int i = 0; i < 6;i++) {
        	button[i].setText("Iniciar tiempo");
        	button[i].setFont(font_size);
        	button[i].setActionCommand(Integer.toString(i));
        	button[i].setFocusable(false);
        	button[i].addActionListener(this);
        	pcNumero[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        	pcNumero[i].setText("PC"+Integer.toString(i+1));
        	pcNumero[i].setFont(font_size);
        	if (i < 3) {
        		button[i].setBounds(s*(3*i+1),2*s,2*s,s/2);
        		pcNumero[i].setBounds(s*(3*i+1),2*s-(int)(s/2),2*s,s/2);
        	} else { 
        		button[i].setBounds(s*(3*i-8),(int)(3.4*s),2*s,s/2);
        		pcNumero[i].setBounds(s*(3*i-8),(int)(2.9*s),2*s,s/2);
        	}
        	add(button[i]);
        	add(pcNumero[i]);
        	}
       
        botónTiempo.setBounds(s,(int)(4.8*s),2*s,s/2);
        botónTiempo.setText("TIEMPO");
        botónTiempo.setFont(font_size);
        botónTiempo.setActionCommand("6");
        botónTiempo.addActionListener(this);
        add(botónTiempo);
        
        botónActividad.setBounds(s*4,(int)(4.8*s),2*s,s/2);
        botónActividad.setText("ACTIVIDAD");
        botónActividad.setFont(font_size);
        botónActividad.setActionCommand("7");
        botónActividad.addActionListener(this);
        add(botónActividad);
        
        botónPrecios.setBounds(s*7,(int)(4.8*s),2*s,s/2);
        botónPrecios.setText("PRECIOS");
        botónPrecios.setFont(font_size);
        botónPrecios.setActionCommand("8");
        botónPrecios.addActionListener(this);
        add(botónPrecios);
        
        biemField.setBounds(s,6*s/10,3*s,s*3/4);
        biemField.setText("BIENVENIDO/A");
        biemField.setEditable(false);
        biemField.setFont(new Font("Arial Narrow", 0,screen_size/192*12));
        biemField.setEnabled(false);
        add(biemField);
    }

    private void jToggleButtonActionPerformed(int i) {
        if (user[i] == null) {
            button[i].setText("Detener");
            user[i] = new User(i+1);
        } else {
            button[i].setText("Iniciar tiempo");
            Actividad a = new Actividad(user[i]);
            a.setVisible(true);
            a.user=null;
            user[i] = null;
        }
    }//*/

    public static void main(String args[]) {
    	try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Tiempo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {*/
                new MainMenu().setVisible(true);/*
            }
        });*/
    }
    public static String format (double h, double m, double s) {
        if (m<10) {
            if (s<10) return Integer.toString((int)h)+":0"+Integer.toString((int)m)+":0"+Integer.toString((int)s);
            else return "0"+Integer.toString((int)h)+":0"+Integer.toString((int)m)+":"+Integer.toString((int)s);
        } else {
            if (s<10) return Integer.toString((int)h)+":"+Integer.toString((int)m)+":0"+Integer.toString((int)s);
            else return "0"+Integer.toString((int)h)+":"+Integer.toString((int)m)+":"+Integer.toString((int)s);
        
        }
        
    }
    
    static void actividad() {
    	var dialog = new JDialog();
		var p = new JPanel();
		dialog.add(p);
		dialog.add(new JPanel(),"West");
		dialog.add(new JPanel(),"East");
		dialog.add(new JPanel(),"North");
		dialog.add(new JPanel(),"South");
		var pc = new JButton[6];
		for (int i = 0; i < 3;i++) {
			var panel = new JPanel();
			panel.setLayout(new BorderLayout());
			p.add(panel);
			
			pc[i] = new JButton("PC"+(i+1));
			pc[i].setActionCommand("actividad"+i);
			final int j = i;
			pc[i].addActionListener((e)->{
				if (user[j]!=null) {
					Actividad a = new Actividad(user[j]);
					a.setVisible(true);
					a.user=null;
					user[j] = null;
				}
			});
			panel.add(pc[i],"North");
			
			pc[i+3] = new JButton("PC"+(i+4));
			pc[i+3].setActionCommand("actividad"+i);
			pc[i+3].addActionListener((e)->{
				if (user[j+3]!=null) {
					Actividad a = new Actividad(user[j+3]);
					a.setVisible(true);
					a.user=null;
					user[j+3] = null;
				}
			});
			panel.add(pc[i+3],"South");//*/
		}
		dialog.pack();
		dialog.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() != null) {
			int some;
			try {
				some = Integer.parseInt(e.getActionCommand());
			} catch(Exception ex) {some = 10;}
			if (some > 5)
				switch(some) {
				case 6: Tiempo.singleton.setVisible(true); break;
				case 7: actividad(); break;
				case 8: new Precios().setVisible(true); 
				}
			else
			jToggleButtonActionPerformed(some);
		}
	}
}
