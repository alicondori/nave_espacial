package PRESENTACION;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*import NEGOCIO.Hilito;*/

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class FormNaveEspacial extends JFrame {

	private JPanel contentPane;
	private GraficadorEspacial grafEspacial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormNaveEspacial frame = new FormNaveEspacial();
					frame.setVisible(true);
					//METODOS
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. CONSTRUCTOR
	 */
	public FormNaveEspacial() {
		addKeyListener(new KeyAdapter() {

			@Override
			
			public void keyPressed(KeyEvent ke) 
			{
				if (grafEspacial.obtenerPosXNave()<0) 
				{
					if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
					{
						grafEspacial.moverNave(15, 0);
					}
					if(ke.getKeyCode()==KeyEvent.VK_UP)
					{
						grafEspacial.moverNave(0, -15);
					}
					if(ke.getKeyCode()==KeyEvent.VK_DOWN)
					{
						grafEspacial.moverNave(0, 15);
					}
				}
				else 
				{
					if (grafEspacial.obtenerPosXNave()>925) 
					{
						if(ke.getKeyCode()==KeyEvent.VK_LEFT)
						{
							grafEspacial.moverNave(-15, 0);
						}
						if(ke.getKeyCode()==KeyEvent.VK_UP)
						{
							grafEspacial.moverNave(0, -15);
						}
						if(ke.getKeyCode()==KeyEvent.VK_DOWN)
						{
							grafEspacial.moverNave(0, 15);
						}
					}
					else{
						
						if(ke.getKeyCode()==KeyEvent.VK_LEFT)
						{
							grafEspacial.moverNave(-15, 0);
						}
						if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
						{
							grafEspacial.moverNave(15, 0);
						}
						if(ke.getKeyCode()==KeyEvent.VK_UP)
						{
							grafEspacial.moverNave(0, -15);
						}
						if(ke.getKeyCode()==KeyEvent.VK_DOWN)
						{
							grafEspacial.moverNave(0, 15);
						}
					}
				}
				if(ke.getKeyCode()==KeyEvent.VK_SPACE)
				{
					grafEspacial.dispararBala();
				}
			}
			//EVENTO PARA EL TECLADO
			@Override
			public void keyTyped(KeyEvent e) 
			{
				if (grafEspacial.obtenerPosXNave()<0) 
				{
					if (e.getKeyChar()=='w' || e.getKeyChar()=='W') 
					{
						grafEspacial.moverNave(0, -15);
					}
					if (e.getKeyChar()=='s' || e.getKeyChar()=='S') 
					{
						grafEspacial.moverNave(0, 15);
					}
					if (e.getKeyChar()=='d' || e.getKeyChar()=='D') 
					{
						grafEspacial.moverNave(15, 0);
					}
				}
				else {
					if (grafEspacial.obtenerPosXNave()>925) 
					{
						if (e.getKeyChar()=='w' || e.getKeyChar()=='W') 
						{
							grafEspacial.moverNave(0, -15);
						}
						if (e.getKeyChar()=='s' || e.getKeyChar()=='S') 
						{
							grafEspacial.moverNave(0, 15);
						}
						if (e.getKeyChar()=='a' || e.getKeyChar()=='A') 
						{
							grafEspacial.moverNave(-15, 0);
						}
					}
					else{
						if (e.getKeyChar()=='w' || e.getKeyChar()=='W') 
						{
							grafEspacial.moverNave(0, -15);
						}
						if (e.getKeyChar()=='s' || e.getKeyChar()=='S') 
						{
							grafEspacial.moverNave(0, 15);
						}
						if (e.getKeyChar()=='a' || e.getKeyChar()=='A') 
						{
							grafEspacial.moverNave(-15, 0);
						}
						if (e.getKeyChar()=='d' || e.getKeyChar()=='D') 
						{
							grafEspacial.moverNave(15, 0);
						}
					}
				}
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 0, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		grafEspacial = new GraficadorEspacial();
		grafEspacial.setBounds(0, 0, 1200, 800);
		contentPane.add(grafEspacial);
		
		grafEspacial.repaint();
		
		Thread hilo= new Thread(grafEspacial);//INTANCIANDO EL HILO
		hilo.start();//ESTO INICIA EL PROCESO
		
		//MAXIMIZAR EL FROM "PANTALLA COMPLETA"
		//setExtendedState(MAXIMIZED_BOTH);
	}
}
