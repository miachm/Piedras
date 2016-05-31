package jm.piedras;

import javax.swing.JOptionPane;
 
public class Gravedad extends Thread {
 
	/**Panel del juego*/
	private PanelJugar paneljugar;
	
	/**Tablero del juego*/
	private Tablero tablero;
	
	/**bucle activo*/
	private boolean activo;
	
	/**gravedad activada o no*/
	private boolean activada,contando;
	
	/**tiempo actual*/
	private long tiempo;
	
	/**Tiempo maximo*/
	private long tiempototal=300000;
	
	/**para saber si el jugador se tiene que mover cuando mantenemos*/
	private boolean moviendose;
	
	/**Constructor
	 * 
	 * @param pj panel del juego
	 * @param t Tablero del juego
	 */
	public Gravedad(PanelJugar pj,Tablero t) {
		super();
		tablero=t;
		paneljugar = pj;
		activo=true;
		contando=false;
		activada=false;
		moviendose=false;
		
	}
	
	public void run()
	{
		
		while(activo)
		{
			if(activada)
			{
				tablero.doGravedad();
			}
			if(moviendose)
				tablero.mover();
			if(contando)
			{
				if(tiempo<=tiempototal)
				{
					tiempo = tiempo+100;
					paneljugar.setTiempo((float)(tiempototal-tiempo)/tiempototal);
				}
				
				if(tiempo==tiempototal)
					paneljugar.timeOut();
			}
			paneljugar.repaint();
			try{
				Thread.sleep(100);
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e.toString(), Messages.getString("Gravedad.Error_Gravity"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			}
		}
	}
	
	/**Desactiva la gravedad*/
	public void desactiva()
	{
		activada=false;
	}
	
	/**Activa la gravedad*/
	public void activa()
	{
		
		activada=true;
	}
	
	public void contando()
	{
		contando=true;
	}
	
	public void descontando()
	{
		contando=false;
	}
	
	/**Resetea el tiempo y la gravedad*/
	public void reset()
	{
		tiempo=0;
		activada=false;
		moviendose=false;
	}
	
	public void setLimite(long lim)
	{
		this.tiempototal=lim;
	}
	
	public void movil()
	{
		moviendose=true;
	}
	
	public void inmovil()
	{
		moviendose=false;
	}
	
	public void pausa()
	{
		tiempo=0;
		activada=false;
		moviendose=false;
		contando=false;
	}
	
	public long getCurso(){return this.tiempo;}
	
	public void setCurso(long a){tiempo=a;}
}
