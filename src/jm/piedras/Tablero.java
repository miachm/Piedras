package jm.piedras;

import javax.swing.JOptionPane;


public class Tablero extends Jugable 
{
	
	/***/
	private static final long timeMin = 200;
	
	/***/
	private long tiempo_aux = 0;

	/**tamanyo del lienzo*/
	protected int xh,xw;

	/**Ventana*/
	protected Ventana ventana;

	/**panelJugar*/
	protected PanelJugar paneljugar;

	/**Hilo de Gravedad*/
	protected Gravedad gravedad;
	
	/**Tiempo que tienes para resolver el tablero*/
	protected long tiempo=30000;
	
	/**Fichero del que se cargó el actual nivel(tablero)*/
	protected String cargadoDe;
	
	/**Lector de Tableros*/
	protected LectorTablero lector;
	
	/**ultimaposicion donde se clickó en el tablero(para arrastrar)*/
	protected int ultimoclickx,ultimoclicky;
	
	/**Constructor
	 * 
	 * @param v Ventana
	 * @param pj paneljugar
	 */
	public Tablero(Ventana v,PanelJugar pj,LectorTablero l) 
	{
		super();
		ventana = v;
		
		paneljugar=pj;
		
		lector = l;
		
		jugador=new Jugador('u',10,10);
		
		tiempo=300000;
		cargadoDe=null;
		
	}

	/**nos pasan la gravedad*/
	public void setGravedad(Gravedad g)
	{
		gravedad=g;
		g.start();
	}
	
	
	/**Carga el tablero desde un fichero
	 * 
	 * @param fichero fichero desde el que cargar
	 */
	public void cargar(String fichero)
	{
		cargadoDe=fichero;
		
		jugador=lector.leerTablero(fichero,tablero,tablerobase, nF, nC);
		
		if(jugador!=null)
		{
			tiempo=lector.getTiempo();
		}
		else
		{
			JOptionPane.showMessageDialog(null, Messages.getString("Tablero.Error_Loading_Map")+fichero , Messages.getString("Tablero.Error"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			System.exit(1);
		}
		
		gravedad.reset();
		gravedad.contando();
		gravedadActivada=false;
		gravedad.setLimite(tiempo);
	}
	
	
	/**Guarda el tablero en un fichero*/
	public void Guardar()
	{
		lector.GuardarTableroBase(cargadoDe, tablero, tablerobase, nF, nC, tiempo, jugador);
	}
	
	/**Cuando se pulsa el boton de Arriba*/
	public void upPressed()
	{
		int y = jugador.getY(),x=jugador.getX();
		
		if(!isPared(y-1,x))
		{
			if(empujarCaja(0,-1))
			{
				jugador.toUp();
				comprobaciones();
			}
		}
		jugador.girar('u');
	}

	/**Cuando se pulsa el boton de Abajo*/
	public void downPressed()
	{
		int y = jugador.getY(),x=jugador.getX();
		
		if(!isPared(y+1,x))
		{
			if(empujarCaja(0,+1))
			{
				jugador.toDown();
				comprobaciones();
			}
		}
		jugador.girar('d');
	}

	/**Cuando se pulsa el boton de Izquierda*/
	public void leftPressed()
	{
		int y = jugador.getY(),x=jugador.getX();
		
		if(!isPared(y,x-1))
		{
			if(empujarCaja(-1,0))
			{
				jugador.toLeft();
				comprobaciones();
			}
		}
		jugador.girar('l');
	}

	/**Cuando se pulsa el boton de Derecha*/
	public void rightPressed()
	{
		int y = jugador.getY(),x=jugador.getX();
		
		if(!isPared(y,x+1))
		{
			if(empujarCaja(1,0))
			{
				jugador.toRight();
				comprobaciones();
			}
		}
		jugador.girar('r');
		
	}
	

	/**Dice si hay algun obstaculo en el camino que tomamos, y si hay una caja delante, y se puede mover la mueve
	 * 
	 * @param x direccion x, por ej, -1 si vamos en el eje x -> x-1
	 * @param y direccion y, por ej, -1 si vamos en el eje y -> y-1, +1=> y->y+1
	 * @return true si se puede mover, false sino
	 */
	protected boolean empujarCaja(int x, int y) 
	{
		int px=jugador.getX(),py=jugador.getY();
		
		if(tablero[py+y][px+x]!=' ' && !isPaja(py+y,px+x))
		{
			if(tablero[py+2*y][px+2*x]==' '&&!isPared(py+2*y,px+2*x)) //&& !isPaja(py+2*y,px+2*x))
			{
				//if (!isPaja(py+y,px+x))
				tablero[py+2*y][px+2*x]=tablero[py+y][px+x];
				tablero[py+y][px+x]=' ';
				return true;
			}
			return false;
		}
		else
		{
			tablero[py+y][px+x] = ' ';
			return true;
		}
	}
	
	/**Hace las comprobaciones necesarias en cada jugada*/
	public void comprobaciones()
	{
		int x=jugador.getX(), y=jugador.getY();
		
		if(!tableroTerminado()&&isGravedad(y,x))
		{
			if(gravedadActivada)
			{
				gravedadActivada=false;
				gravedad.desactiva();
			}
			else
			{
				gravedad.activa();
				gravedadActivada=true;
			}
		}
		
		comprobarTerminado();
	}
	
	
	/**Comprueba que el tablero no esta terminado*/
	public synchronized void comprobarTerminado()
	{
		if(tableroTerminado())
		{
			gravedad.reset();
			paneljugar.siguiente();
		}
	}
	
	/**Dice si el tablero esta solucionado
	 * 
	 * @return true si todas las cajas estan en su sitio
	 */
	private boolean tableroTerminado()
	{
		for(int i = 0; i < nF; i++)
		{
			for(int j=0; j < nC; j++)
			{
				if(isMeta(i,j)&& !isCaja(i,j)|| (isCaja(i,j)&&!isMeta(i,j)))
					return false;
			}
		}
		return true;
	}
	
	/**Hace le efecto de la gravedad en una posicion*/
	public synchronized void doGravedad()
	{
		int cont=0;
		for(int i = nF-1; i>0; i--)
		{
			for(int j = 0; j< nC; j++)
			{
				if(tablero[i][j]==' ' &&!isPared(tablerobase[i][j])&& !isPaja(tablero[i][j]) && !(jugador.getX()==j&&jugador.getY()==i))
				{
					if(tablero[i-1][j]!=' ' && !isPaja(i-1,j))
					{
						tablero[i][j]=tablero[i-1][j];
						tablero[i-1][j]=' ';
						cont++;
					}
				}
			}
		}
		if(cont==0)
			comprobarTerminado();
	}
	
	
	/**Elimina el siguiente objeto(si hay) en la direccion del jugador(siguiente casilla) del tablero(No puede borrar cajas)*/
	public void eliminaSiguiente()
	{
		int dir[]=jugador.siguientePosicion();
		
		if(dir[0]>0 && dir[1]>0 && dir[0] <nC-1 && dir[1]<nF-1)
			if(!isCaja(dir[1],dir[0]))
				set(dir[1],dir[0],' ');
	}
	
	/**Elimina el siguiente objeto(si hay) en la direccion del jugador(siguiente casilla) del tablerobase(no puede borrar las metas)*/
	public void borraSiguiente()
	{
		int dir[]=jugador.siguientePosicion();
		
		if(dir[0]>0 && dir[1]>0 && dir[0] <nC-1 && dir[1]<nF-1)
			if(!isMeta(dir[1],dir[0]))
				setB(dir[1],dir[0],' ');
	}
	
	public void click(int x,int y)
	{
	//	metodoRutaCorta(x,y);
		long aux = System.currentTimeMillis();
		
		if ((aux-tiempo_aux) > timeMin)
		{
			ultimoclickx=x/pw;
			ultimoclicky=y/ph;
			relacion_click((x-despl)/pw,y/ph);
			tiempo_aux = System.currentTimeMillis();
		}
	}
	public void arrastrar(int x,int y)
	{
//		long aux = System.currentTimeMillis();
//		
//		if ((aux-tiempo_aux) > timeMin)
		//{
			ultimoclickx=(x-despl)/pw;
			ultimoclicky=y/ph;
		//}
	}
	
	private void relacion_click(int x,int y)
	{		
		int posy = jugador.getY(),posx=jugador.getX();
		
		int dirx = Math.abs(x-posx);
		int diry = Math.abs(y-posy);
		
		if (dirx > diry)
		{
			int signo = x-posx;
			if (signo > 0) rightPressed();
			else if(signo!=0) leftPressed();
		}
		else
		{
			int signo = y-posy;
			if (signo > 0) downPressed();
			else if(signo!=0) upPressed();
		}
	}
	
//	public void relacion_click(int x,int y)
//	{		
//		int posy = jugador.getY()*pw,posx=jugador.getX()*ph;
//		
//		int dirx = Math.abs(x-posx);
//		int diry = Math.abs(y-posy);
//		
//		if (dirx > diry)
//		{
//			int signo = x-posx;
//			if (signo > 0) rightPressed();
//			else leftPressed();
//		}
//		else
//		{
//			int signo = y-posy;
//			if (signo > 0) downPressed();
//			else upPressed();
//		}
//	}
	
	/**mueve el personaje hacia el último click que hicimos*/
	public void mover(){
	//	if ()
		long aux = System.currentTimeMillis();
		
		if ((aux-tiempo_aux) > timeMin)
		{
			if(ultimoclickx!=jugador.getX()||ultimoclicky!=jugador.getY())
			{
				relacion_click(ultimoclickx,ultimoclicky);
				tiempo_aux = System.currentTimeMillis();
			}
		}
	}
	
	/*
	private static boolean comprobar(Cord a,Cord b,int x,int y)
	{
		return a.x+x == b.x && a.y+y == b.y;
	}
	
	private boolean comprobar_pared(Cord a,int x,int y)
	{
		return isPared(a.x+x,a.y+y) || isPiedra(a.x+x,a.y+y);
	}
	
	private void metodoRutaCorta(int x,int y)
	{
		int posy = jugador.getY(),posx=jugador.getX();
		if (isPared(x,y)) return;
		
		final Cord destino = new Cord(x,y);
		Stack<Cord> Pila = new Stack<Cord>();
		Pila.push(new Cord(posx,posy));
		while (!Pila.empty() && Pila.peek().equals(destino))
		{
			if (comprobar(Pila.peek(),destino,-1,0))
			{
				System.out.println("Encontrado");
				return;
			}
			else if (comprobar(Pila.peek(),destino,1,0))
			{
				System.out.println("Encontrado");
				return;
			}
			else if (comprobar(Pila.peek(),destino,0,-1))
			{
				System.out.println("Encontrado");
				return;
			}
			else if (comprobar(Pila.peek(),destino,0,1))
			{
				System.out.println("Encontrado");
				return;
			}
			
			
		}
	}*/
}
