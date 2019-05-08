/*package NEGOCIO;


public class Hilo extends Thread //ESTE ES EL HILO
{
	public int contador; 
	public Hilo() 
	{
		contador=0;
	}

	@Override
	public void run()//METODO CONJUNTO DE HILOS 
	{
		try
		{
			while (true) 
			{
				contador++;
				System.out.println("CONTADOR: "+contador);
				Thread.sleep(1000);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		Hilo h= new Hilo();
		h.start();
	}
	
	

}*/
