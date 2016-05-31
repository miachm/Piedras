package jm.piedras;


public class MemoriaRapida 
{
	private boolean guardado=false;
	
	private  char[][] tablero = null;
	
	private int posx,posy,nnivel;
	
	private long tiempo;
	
	private String nivel;
	
	private char c;
	
	private boolean gravedad;
	
	public MemoriaRapida()
	{
		guardado=false;
		gravedad=false;
		tablero=new char[18][25];
		posx=0;
		posy=0;
		nnivel=0;
		tiempo=0;
		nivel="";
		c=' ';
	}
	
	
	
	public void guardar(Tablero tab,Niveles niv,Gravedad gr)
	{
		nivel=niv.getNivel();
		nnivel=niv.getPos();
		
		tiempo=gr.getCurso();
		
		for(int i= 0; i < tab.nF; i++)
		{
			for(int j = 0; j < tab.nC; j++)
			{
				tablero[i][j]=tab.get(i, j);
			}
		}

		posx=tab.jugador.getX();
		posy=tab.jugador.getY();
		c=tab.jugador.getEstado();
		
		gravedad=tab.gravedadActivada;
		guardado=true;
	}
	
	public void cargar(Tablero tab,Niveles niv,Gravedad gr)
	{
		tab.cargar(nivel);

		for(int i= 0; i < tab.nF; i++)
		{
			for(int j = 0; j < tab.nC; j++)
			{
				tab.set(i, j,tablero[i][j]);
			}
		}
		
		gr.setCurso(tiempo);
		
		niv.set(nnivel);
		
		tab.jugador.set(c, posx, posy);
		
		tab.gravedadActivada=gravedad;
		if(gravedad)
			tab.gravedad.activa();
		else
			tab.gravedad.desactiva();
	}

	public boolean isGuardado()
	{
		return guardado;
	}
}
