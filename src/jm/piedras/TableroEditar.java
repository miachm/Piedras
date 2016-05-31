package jm.piedras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;

import javax.swing.JOptionPane;

public class TableroEditar extends Jugable
{

	private LectorTablero lector;
	
	/**panelJugar*/
	protected PanelEditar paneleditar;
	
	/**posicion seleccionada*/
	private int selx,sely;
	
	/**Tiempo para resolver el tablero(nivel)*/
	private long tiempolimite;
	
	
	/**tamanyo del lienzo*/
	protected int xh,xw;
	
	
	/**Constructor
	 * 
	 * @param v Ventana
	 * @param pj paneljugar
	 */
	public TableroEditar(PanelEditar pe,LectorTablero l) 
	{
		super();
		
		paneleditar=pe;
		
		lector = l;
		
		jugador=null;//el tablero se inicia vacío, sin jugador
		
		
		char tableroo[][] = {
			{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}};
		char tableroo2[][] = {
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
		tablero = tableroo2;
		tablerobase=tableroo;
		
	}
	
	/**añade un elemento al tablero*/
	public void anyadir(char c)
	{
		if(selx<nC&&sely<nF)
		{
			if(isPared(c))
			{
				if(get(sely,selx)==' '&&getB(sely,selx)==' ')
					setB(sely,selx,'X');
			}
			else if(isGravedad(c)||isMeta(c))
			{
				if(getB(sely,selx)==' ')
				{
					setB(sely,selx,c);
				}
			}
			else if(get(sely,selx)==' '&&!isPared(sely,selx))
			{
				if(c=='H')
				{
					if(jugador==null)
					{
						jugador=new Jugador('r',selx,sely);
						set(sely,selx,c);
					}
				}
				else
					set(sely,selx,c);
			}
		}
		
	}

	/**Añade una fila de pared*/
	public void anyadirT()
	{
		boolean ok =true;
		if(selx<nC&&sely<nF)
		{
			for(int i = 0; i < nC; i++)
			{
				if(isJugador(tablero[sely][i]))
				{
					ok=false;
				}
			}
			
			if(ok)
			{
				for(int i = 0; i < nC; i++)
				{
					setB(sely,i,'X');
					set(sely,i,' ');
				}
			}
		}
	}
	
	/**Añade una columna de pared*/
	public void anyadirY()
	{
		boolean ok =true;
		if(selx<nC&&sely<nF)
		{
			for(int i = 0; i < nF; i++)
			{
				if(isJugador(tablero[i][selx]))
				{
					ok=false;
				}
			}
			
			if(ok)
			{
				for(int i = 0; i < nF; i++)
				{
					setB(i,selx,'X');
					set(i,selx,' ');
				}
			}
		}
	}

	/**Carga el tablero de un fichero
	 * @param fichero fichero de donde cargar*/
	public void cargar(String fichero)
	{
		jugador=lector.leerTableroE(fichero,tablero,tablerobase,nF,nC);
		tiempolimite=lector.getTiempo();
		selx=2;
		sely=2;
		
	}
	
	/**Comprueba que hay un numero correcto de elementos en el tablero*/
	private boolean correcto()
	{
	//	System.out.println((jugador==null)+", ");
		if(jugador==null)
			return false;
		int nnC=0,nnF=0;
		for(int i = 0; i < nF; i++)
		{
			for(int j = 0; j <nC; j++)
			{
				if(isMeta(i,j))
					nnF++;
				if(isCaja(i,j))
					nnC++;
			}
		}
		
		//System.out.println(nnC+","+nnF);
		
		return jugador!=null&&nnC==nnF&& nnC>0;
	}
	
	/**Guarda el tablero en un fichero*/
	public boolean Guardar(String archivo)
	{
		if(correcto())
		{
			//pide el tiempo para resolver el tablero
			
			pideTiempo();
			archivo = pideNombre(archivo);
			
			if (archivo != null && lector.GuardarTableroBase(archivo, tablero, tablerobase, nF, nC, tiempolimite, jugador))//guardaTableroE(archivo, tablero, nF, nC, tiempolimite, jugador))
			{
				JOptionPane.showMessageDialog(null, Messages.getString("TableroEditar.Good_Save")+ 
			    archivo.substring(archivo.lastIndexOf('/')+1), Messages.getString("TableroEditar.Saved"), JOptionPane.INFORMATION_MESSAGE); 
				return true;
			}
			else return false;
		}
		return false;
	}
	
	/**mueve
	 * 
	 * @param y pos y
	 * @param x pos x
	 */
	public void mover(int y, int x)
	{
		
		if(selx<nC-x&&sely<nF-y&&selx>0&&sely>0)
		{
			selx=selx+x;
			sely=sely+y;
		}
	}
	
	

	public void paint(Graphics2D g2d,int xh, int xw)
	{
		ph = xh/nF;
		pw=xw/nC;
		
		despl = 0;//(xw-(pw*nC))/2;
		
		//Pintar el fondo negro
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, xw, xh);
		
		//Pintar el tablero base
		for(int i = 0;  i < nF; i++)
		{
			for(int j = 0; j < nC; j++)
			{
				if(isPared(i,j))//comprobamos los objetos inmóviles primero
				{
					pintarRoca(g2d,i,j);
				}
				else if(isMeta(i,j))
				{
					pintarMeta(g2d, i, j);
				}
				else if(isGravedad(i,j))
				{
					pintarGravedad(g2d,i,j);
				}
				if(isCaja(i,j))//luego pintamos los objetos móviles encima
				{
					pintarCaja(g2d, i, j);
				}
				else if(isPiedra(i,j))
				{
					pintarPiedra(g2d, i, j);
				}
				else if(isPaja(i,j))
				{
					pintarPaja(g2d,i,j);
				}
				if(i==sely&&j==selx)
				{
					g2d.setColor(Color.WHITE);
					g2d.drawRect(j*pw, i*ph, pw,ph);
				}
			}
		}
		//pintar el jugador
		if(jugador!=null)
			jugador.Paint(g2d, pw, ph,0);
	}
	
	
	/**Pide el nombre para guardar el nuevo tablero
	 * 
	 * @param nom nombre inicial
	 * @return nombre que ha seleccionado el usuario
	 */
	private String pideNombre(String nom)
	{
		boolean repetir;
		String devolver;
		final int entero = nom.lastIndexOf('/')+1;
		final String ab = nom.substring(entero);
		do
		{
			repetir = false;
			devolver = JOptionPane.showInputDialog(Messages.getString("TableroEditar.Name_For_Save"),ab+"");  
			
			if (devolver == null)
				return null;
			
			devolver = nom.substring(0,entero) + devolver;
			
			File aux = new File(devolver);
			
			if (aux.exists())
			{
				int a = JOptionPane.showConfirmDialog(null,Messages.getString("TableroEditar.Overwrite"),Messages.getString("TableroEditar.Exist_File"),JOptionPane.YES_NO_OPTION);  
				
				if (a != JOptionPane.YES_OPTION)
					repetir = true;
				//else aux.mkdirs();
			}
		} while (repetir);
		//if (Fil)
		return devolver;
	}
	
	/***Pide el tiempo para resolver el nivel al usuario*/
	private void pideTiempo()
	{
		int cambio = 1;
		String op = JOptionPane.showInputDialog(Messages.getString("TableroEditar.Time_For_Solve"),tiempolimite+"ms");  
		
		if(op!=null && op.length() >0)
		{
			long intt= tiempolimite;
			op = op.trim();
			char op1[] = op.toCharArray();
			if (!Character.isDigit(op1[op.length()-1]))
			{
				if (op.length() > 1 && op1[op.length()-1] == 's' && op1[op.length()-2] == 'm')
				{
					op = op.substring(0,op.length()-2);
				}
				else if(op1[op.length()-1] == 's')
				{
					op = op.substring(0,op.length()-1);
					cambio = 1000;
					
					if (op.length() > 12){intt = tiempolimite; return;} // demasiado grande}
				}
				else if(op1[op.length()-1] == 'm')
				{
					op = op.substring(0,op.length()-1);
					cambio = 60*1000;
					
					if (op.length() > 5){intt = tiempolimite; return; }// demasiado grande
				}
				else
				{
					intt = tiempolimite;
					return;
				}
			}
			try{
				intt = Long.parseLong(op)*cambio;
			
			}catch(Exception e)
			{
				intt = tiempolimite;
			}
			tiempolimite=intt;
		}
	}
	
	
	protected void pintarGravedad(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.RED);
		g2d.fillRect(j*pw+1, i*ph+1, pw-2,ph-2);
		g2d.setColor(Color.yellow);
		g2d.drawRect(j*pw, i*ph, pw,ph);
	}
	

	protected void pintarMeta(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.GREEN);
		g2d.fillRect(j*pw, i*ph, pw, ph);
		g2d.setColor(Color.red);
		g2d.drawRect(j*pw, i*ph, pw, ph);
		g2d.drawRect(j*pw+1, i*ph+1, pw-2, ph-2);
	}
	
	/**quita el elememto seleccionado*/
	public void quitar()
	{
		if(selx<nC&&sely<nF)
		{
			if(isJugador(tablero[sely][selx]))
			{
				jugador=null;
			}
			if(get(sely,selx)!=' ')
				set(sely,selx,' ');
			else
				setB(sely,selx,' ');
		}
	}
	
	/**reinicia el tablero*/
	public void reinicia()
	{
		char mat[][]= {
				{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X','X'}
				};
		char tableroo2[][] = {
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}};
		tablerobase=mat;
		tablero=tableroo2;
		jugador=null;
		tiempolimite=300000;
	}
	
	/**selecciona una posicion en el tablero*/
	public void selecciona(int y, int x)
	{
		
		int sx=x/pw,sy=y/ph;
		if((sx!=selx||sy!=sely)&&sx>0&&sy>0&&sx<nC-1&&sy<nF-1)
		{
			selx=sx;
			sely=sy;
		}
	}
}
