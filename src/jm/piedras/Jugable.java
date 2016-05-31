package jm.piedras;

import java.awt.Color;
import java.awt.Graphics2D;

public class Jugable extends Pintable {

	
	/**Tiene el tablero con las cosas que no se pueden mover*/
	protected char tablerobase[][] = { 
			{'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','g',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','d',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x'},
			{'x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x','x'}
			};

	/**tablero con las cajas y piedras*/
	protected char tablero[][] = {
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','c',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','p',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
			{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
			};
	
	
	/**Jugador*/
	protected Jugador jugador;
	
	
	public Jugable()
	{
		super();
		jugador=null;
	}
	
	
	public void paint(Graphics2D g2d,int xh, int xw)
	{
		ph = xh/nF;
		pw=xw/nC;
		
		despl = (xw-(pw*nC))/2;
		
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
			}
		}
		//pintar el jugador
		if(jugador!=null)
			jugador.Paint(g2d, pw, ph,despl);
	}
	

	/**Dice si en el tablero la posicion i,y es una piedra*/
	protected boolean isPiedra(int i, int j){return isPiedra(tablero[i][j]);}
	
	/**Dice si en el tablero la posicion i,y es una pared*/
	protected boolean isPared(int i,int j){return isPared(tablerobase[i][j]);}
	/**Dice si en el tablero la posicion i,y es una caja*/
	protected boolean isCaja(int i, int j){return isCaja(tablero[i][j]);}

	/**Dice si en el tablero la posicion i,y es una meta*/
	protected boolean isMeta(int i, int j){return isMeta(tablerobase[i][j]);}

	/**Dice si en el tablero la posicion i,y es un boton de gravedad*/
	protected boolean isGravedad(int i, int j){return isGravedad(tablerobase[i][j]);}
	
	
	/**Indica que el caracter corresponde a una bola de paja*/
	protected boolean isPaja(int i,int j){return isPaja(tablero[i][j]);}
	
	

	/**numero de filas y columnas*/
	protected int nF=18,nC=25;
	
	/**Pone un valor en el tablero
	 * 
	 * @param y posicion y
	 * @param x posicion x
	 * @param c caracter a poner
	 */
	public void set(int y, int x, char c)
	{
		tablero[y][x]=c;
	}
	
	/**Obtiene el valor del tablero
	 * 
	 * @param y posicion y
	 * @param x posicion x
	 * @return caracter en esa posicion
	 */
	public char get(int y,int x)
	{
		return tablero[y][x];
	}
	
	/**Pone un valor en el tablerobase
	 * 
	 * @param y posicion y
	 * @param x posicion x
	 * @param c caracter a poner
	 */
	public void setB(int y, int x, char c)
	{
		tablerobase[y][x]=c;
	}
	
	/**Obtiene el valor del tablero Base
	 * 
	 * @param y posicion y
	 * @param x posicion x
	 * @return caracter en esa posicion
	 */
	public char getB(int y,int x)
	{
		return tablerobase[y][x];
	}
}
