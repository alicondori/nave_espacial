package PRESENTACION;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NEGOCIO.Bala;
import NEGOCIO.Nave;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraficadorEspacial extends JPanel implements Runnable
{

	Image imgFondo;
	Image imgEnemigo;
	Image imgNave;
	Image imgBala;
	Image imgCometa;
	
	Image imgVacio;
	Image imgExplocion;
	Image imgPerdiste;
	
	int pyEnemigo=-20;
	
	int pxNave=400;
	int pyNave=600;
	
	int pxBala=420;
	int pyBala=580;
	
	public Nave naveBuena;
	
	boolean disparado;
	int contador=0;
	
	boolean habilitar;
	
	Nave[] vecEnemigo= new Nave[8];
	Nave[] vecCometa=new Nave[4];
	
	Vector<Bala> vecBalas= new Vector<Bala>();
	private JLabel lblCantidadPuntaje;
	//ELEMENTOS
	
	//CONSTRUCTOR
	public GraficadorEspacial() 
	{
		setLayout(null);
		
		JLabel lblPuntaje = new JLabel("PUNTAJE");
		lblPuntaje.setBounds(1028, 102, 125, 29);
		lblPuntaje.setFont(new Font("Arial", Font.BOLD, 25));
		lblPuntaje.setForeground(new Color(0, 153, 255));
		add(lblPuntaje);
		
		lblCantidadPuntaje = new JLabel("0.00");
		lblCantidadPuntaje.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCantidadPuntaje.setForeground(new Color(0, 204, 102));
		lblCantidadPuntaje.setBounds(1069, 142, 46, 14);
		add(lblCantidadPuntaje);
		
		
		
		imgFondo= Toolkit.getDefaultToolkit().getImage("Fondo.jpg");
		imgEnemigo= Toolkit.getDefaultToolkit().getImage("Enemigo.png");
		imgNave= Toolkit.getDefaultToolkit().getImage("Nave.png");
		imgVacio= Toolkit.getDefaultToolkit().getImage("vacio.png");
		imgExplocion= Toolkit.getDefaultToolkit().getImage("Explocion.png");
		imgCometa=Toolkit.getDefaultToolkit().getImage("Cometa.png");
		imgPerdiste=Toolkit.getDefaultToolkit().getImage("GameOver.png");
		
		naveBuena=new Nave(pxNave, pyNave);
		iniciarEnemigo(1);
		
		iniciarCometa();
		//INICIALIZANDO
		disparado=false;
		habilitar=true;
		
	}
	

	public void paint(Graphics g) //PARA DIBUJAR
	{
		super.paint(g);
		Graphics2D g2= (Graphics2D)g;
		g2.drawImage(imgFondo, 0, 0, 1000, 750, this);
		if (naveBuena.estado==0) 
		{
			g2.drawImage(imgNave, naveBuena.posX, naveBuena.posY, 60, 60, this);
		}
		else
		{
			g2.drawImage(imgExplocion, naveBuena.posX, naveBuena.posY, 60, 60, this);
			habilitar=false;
			g2.drawImage(imgPerdiste, 350, 350, 300, 80, this);
			
		}
		//DIBUJANDO ENEMIGO
		for (int i = 0; i < vecEnemigo.length; i++) 
		{
			if (vecEnemigo[i].estado==0) 
			{
				g2.drawImage(imgEnemigo, vecEnemigo[i].posX, vecEnemigo[i].posY, 40, 60, this);
			}
			if (vecEnemigo[i].estado==1) 
			{
				g2.drawImage(imgExplocion, vecEnemigo[i].posX, vecEnemigo[i].posY, 60, 60, this);

			}
			if (vecEnemigo[i].estado==2) 
			{
				g2.drawImage(imgVacio, vecEnemigo[i].posX, vecEnemigo[i].posY, 60, 60, this);

			}
			
		}
		//DIBUJANDO COMETA
		for (int i = 0; i < 4; i++) 
		{
			if (vecCometa[i].estado==0) 
			{
				g2.drawImage(imgCometa, vecCometa[i].posX, vecCometa[i].posY, 20, 40, this);
			}
		}
		//DIBUJANDO BALAS
		for (int i = 0; i < vecBalas.size(); i++) 
		{
			if (vecBalas.get(i).estado==0) 
			{
				//VECTOR QUE DIBUJA LAS VALAS CON SUS RESPECTIVOS POSCICIONES
				g2.drawImage(imgBala, vecBalas.get(i).posX, vecBalas.get(i).posY, 15, 40, this);
			}
			if (vecBalas.get(i).estado==1) 
			{
				g2.drawImage(imgExplocion, vecBalas.get(i).posX, vecBalas.get(i).posY, 20, 40, this);

			}
			if (vecBalas.get(i).estado==2) 
			{
				g2.drawImage(imgVacio, vecBalas.get(i).posX, vecBalas.get(i).posY, 20, 40, this);
				
			}
			
		}
		
	}
	
	public void run() 
	{
		try
		{
			while (habilitar==true)//UN BUCLE 
			{
				if(disparado)//HACE QUE LA BALA SUBA
				{
					for (int i = 0; i < vecBalas.size(); i++) {
						vecBalas.get(i).posY =  vecBalas.get(i).posY -3;
					}
				}
				
				//ENEMIGO BAJE
				for (int i = 0; i < vecEnemigo.length; i++) {
					vecEnemigo[i].posY=vecEnemigo[i].posY+1;
				}
				
				//COMETA BAJE
				for (int i = 0; i < vecCometa.length; i++) {
					vecCometa[i].posY=vecCometa[i].posY+1;
				}
				
				//VERIFICA SI EL ENEMIGO SE SALIO DEL HEIGHT "ALTURA PANEL"
				if( vecEnemigo[0].posY > this.getHeight())
				{
					iniciarEnemigo(3);
				}
				if (vecEnemigo[0].posY==370) {
					iniciarEnemigo(2);
				}
				//VERIFICA SI EL COMETA SE SALIO DEL HEIGHT "ALTURA PANEL"
				if( vecCometa[0].posY > this.getHeight())
				{
					iniciarCometa();
				}
				
				//VERIFICA SI LA BALA CHOCA CON EL ENEMIGO O COMETA
				for (int t = 0; t < vecBalas.size(); t++) 
				{
					int x=vecBalas.get(t).posX;
					int y=vecBalas.get(t).posY;
					
					//PARA ENEMIGO
					for (int i = 0; i < vecEnemigo.length; i++) 
					{
						//CREANDO EL RECTANGULO
						Rectangle rec=new Rectangle(vecEnemigo[i].posX,vecEnemigo[i].posY,40,60);
						
						/*VERIFICA SI LA BALA ESTA EN EL AREA DEL ENEMIGO, Y SI EL ENEMIGO ESTA
						 * EN ESTADO "0"*/
						if ((rec.contains(x,y) || rec.contains(x+15,y)) 
								&& vecEnemigo[i].estado==0 && vecBalas.get(t).estado==0)
						{
							
							vecEnemigo[i].estado=1;
							vecBalas.get(t).estado=1;
							
							contador+=25;//CONTADOR PUNTAJE
							lblCantidadPuntaje.setText(""+contador);
						}
					}
					//PARA COMETA
					for (int i = 0; i < vecCometa.length; i++)
					{
						Rectangle rec=new Rectangle(vecCometa[i].posX,vecCometa[i].posY,20,40);
						
						/*VERIFICA SI LA BALA ESTA EN EL AREA DEL COMETA*/
						if ((rec.contains(x,y) || rec.contains(x+15,y)) &&
								vecBalas.get(t).estado==0 && vecBalas.get(t).posY>=0)
						{
							vecBalas.get(t).estado=1;
						}
					}
				}
				
				//VERIFICA SI LA NAVE CHOCA CON EL ENEMIGO
				for (int i = 0; i < vecEnemigo.length; i++) 
				{
					//ENEMIGO
					Rectangle rec=new Rectangle(vecEnemigo[i].posX,vecEnemigo[i].posY,40,60);
					if ((rec.contains(naveBuena.posX,naveBuena.posY)||rec.contains(naveBuena.posX+60,naveBuena.posY)
							|| rec.contains(naveBuena.posX+30,naveBuena.posY)) &&
							vecEnemigo[i].estado==0 && naveBuena.estado==0)
					{
						vecEnemigo[i].estado=1;
						naveBuena.estado=1;
					}
					
				}
				//VERIFICA SI EL COMETA CHOCA CON LA NAVE
				for (int i = 0; i < vecCometa.length; i++) 
				{
					//COMETA
					Rectangle rec1=new Rectangle(vecCometa[i].posX,vecCometa[i].posY,20,40);
					if ((rec1.contains(naveBuena.posX,naveBuena.posY) || rec1.contains(naveBuena.posX+60,naveBuena.posY)
							|| rec1.contains(naveBuena.posX+30,naveBuena.posY)) && naveBuena.estado==0)
					{
						naveBuena.estado=1;
					}
				}
				Thread.sleep(10);//DETIENE POR 15 MILISEGUNDOS EL THEARD
				this.repaint();//REDIBUJA
				
				//PARA QUE SE VUELVA EN VACIO
				for (int i = 0; i < vecEnemigo.length; i++) 
				{
					if (vecEnemigo[i].estado==1) 
					{
						vecEnemigo[i].estado=2;
					}
				}
				for (int i = 0; i < vecBalas.size(); i++) 
				{
					if (vecBalas.get(i).estado==1 && vecBalas.get(i).posY>=0) 
					{
						vecBalas.get(i).estado=2;
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//METODO MOVER NAVE
	public void moverNave(int x, int y)
	{
		naveBuena.posX = naveBuena.posX + x;
		naveBuena.posY = naveBuena.posY + y;
		/*pxNave= pxNave + x;
		pyNave= pyNave + y;*/
	}
	
	
	//METODO DISPARAR BALA
	public void dispararBala()
	{
		pxBala= naveBuena.posX + 20;//POSICION DEL DISPARO
		pyBala= naveBuena.posY - 20;
		
		Bala bl= new Bala(pxBala, pyBala);//CREA OBJETO BALA
		vecBalas.add(bl);//AGREGANDO EL OBJETO AL VECTOR VALAS CON RESPECTIVA POSICION
		
 		if(!disparado)/*(variable==false) SOLO ENTRA UNA VEZ PORQUE SOLO NECESITAMOS
 		ASIGNAR UNA VEZ LA IMAGEN A LA VARIABLE BALA*/
		{
			imgBala= Toolkit.getDefaultToolkit().getImage("Bala.png");
			disparado= true;
		}
	}
	
	public void iniciarEnemigo(int escuadronEnemigo)
	{
		if (escuadronEnemigo==1) 
		{
			int pxEnemigo=100;
			int pxEnemigo2=200;
			for (int i = 0; i < 8; i++) 
			{
				if (i<=3) 
				{
					Nave en=new Nave(pxEnemigo, pyEnemigo);
					vecEnemigo[i]=en;
					pxEnemigo+=200;
				}
				else 
				{
					Nave en1=new Nave(pxEnemigo2, pyEnemigo);
					en1.estado=2;
					vecEnemigo[i]=en1;
					pxEnemigo2 +=200;
				}
			}
		}
		if (escuadronEnemigo==2) 
		{
			int pxEnemigo=200;
			for (int i = 4; i < 8; i++) 
			{
				Nave en=new Nave(pxEnemigo, pyEnemigo);
				vecEnemigo[i]=en;
				pxEnemigo+=200;
			}
		}
		//LLENANDO SOLO AL ESCUADRON 1
		if (escuadronEnemigo==3) 
		{
			int pxEnemigo=100;
			for (int i = 0; i < 4; i++) 
			{
				Nave en=new Nave(pxEnemigo, pyEnemigo);
				vecEnemigo[i]=en;
				pxEnemigo+=200;
			}
		}
	}
	public void iniciarCometa()
	{
		
		Random aleatorio = new Random(System.currentTimeMillis());
        
		for (int i = 0; i < 4; i++) 
		{
			int pxEnemigo=10;
			pxEnemigo = pxEnemigo+ aleatorio.nextInt(700);
			Nave comen=new Nave(pxEnemigo, pyEnemigo-120);
			
			vecCometa[i]=comen;
			//pxEnemigo+=200;
		}
	}
	public int puntajeNave()
	{
		return contador;
	}
	public int obtenerPosXNave()
	{
		int x=naveBuena.posX;
		return x;
	}
}
