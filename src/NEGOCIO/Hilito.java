/*package NEGOCIO;

//HILO QUE SE OCUPA PARA PARTE GRAFICA O FORMULARIOS O PANELES
public class Hilito implements Runnable 
{
	public int contador;
	//CONSTRUCTOR
	public Hilito() {
		contador=0;
	}
	
	@Override
	public void run() 
	{
		try
		{
			while (true) 
			{
				contador++;
				System.out.println("CONTADOR: "+contador);
				Thread.sleep(50);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}                                                                                                                            
	}

	public static void main(String[] args) {
		Hilito h= new Hilito();
		
		Thread hilo= new Thread(h);
		hilo.start();
	}

}*/
