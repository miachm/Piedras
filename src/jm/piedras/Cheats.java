package jm.piedras;

public class Cheats {

	int actual;
	 
	 
	private final static char palabra[] ={'p','o','y','a','r','e','c','o','r','d','s'};
	
	private final static int n =palabra.length;
	
	PanelJugar paneljugar;
	Tablero tablero;
	
	private boolean contando;
	
	public Cheats(PanelJugar pj, Tablero t)
	{
		paneljugar=pj;
		tablero=t;
		actual=0;
		contando=true;
	}
	
	public void prepara()
	{
		actual=0;
	}
	
	public void cheat(char c)
	{
		if(actual>=n)
		{
			if(actual==n&&palabra[0]!=c)
				doCheat(c);
			else
			{
				if(actual==2*n-1)
				{
					actual=0;
				}
				else if(palabra[actual-n]==c)
					actual++;
				else if(actual>n)
					actual=n;
			}
		}
		else
		{
			if(palabra[actual]==c)
				actual++;
			else
				actual=0;
		}
	}
	
	private void doCheat(char c)
	{
		switch(c)
		{
			case 'n':
				paneljugar.siguiente();
				break;
				
			case 't':
				if(contando)
					paneljugar.destiempo();
				else
					paneljugar.tiempo();
				contando=!contando;
				break;
				
			case 'b':
				tablero.borraSiguiente();
				break;
				
			case 'v':
				tablero.eliminaSiguiente();
				break;
				
			default:
				break;
		}
		paneljugar.repaint();
	}
	
	public void siguientenivel()
	{
		contando=true;
	}
	
}
