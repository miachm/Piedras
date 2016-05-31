package jm.piedras;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
 
public class Jugador {

	
	  
	Image persu,persd,persr,persl;
	
	
	char estado;
	
	int posx,posy;
	
	public Jugador(char e,int x,int y) 
	{
		posx=x;
		posy=y;
		estado = e;
		
		persd = new ImageIcon(getClass().getResource("/imagenes/Personaje_Abajo.png")).getImage();
		
		persu = new ImageIcon(getClass().getResource("/imagenes/Personaje_Arriba.png")).getImage();

		persl = new ImageIcon(getClass().getResource("/imagenes/PersonajeI.png")).getImage();

		persr = new ImageIcon(getClass().getResource("/imagenes/Personaje.png")).getImage();
	}

	
	
	public void Paint(Graphics2D g2d,int pw,int ph,int despl)
	{
		int i = posy;
		int j = posx;
		if(estado=='l')
			g2d.drawImage(persl, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
		else if(estado=='d')
			g2d.drawImage(persd, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
		else if(estado=='r')
			g2d.drawImage(persr, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
		else if(estado=='u')
			g2d.drawImage(persu, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
	}
	
	
	public int getX(){return posx;}
	
	public int getY(){return posy;}
	
	public char getEstado(){return estado;}
	
	public void set(char c, int x, int y)
	{
		estado=c;
		posx=x;
		posy=y;
	}

	
	public void toUp()
	{
		posy=posy-1;
		estado='u';
	}
	
	public void toDown()
	{
		posy = posy+1;
		estado='d';
	}
	
	public void toLeft()
	{
		posx=posx-1;
		estado='l';
	}
	
	public void toRight()
	{
		posx = posx+1;
		estado='r';
	}
	
	public void cargar(FileInputStream fis)throws IOException
	{
		estado = (char)fis.read();
//		posx= fis.read()-40;
//		posy = fis.read()-40;
	}
	
	public void guardar(FileOutputStream fos) throws IOException
	{
		fos.write((int)estado);
//		fos.write(40+posx);
//		fos.write(40+posy);
	}
	
	public void girar(char c)
	{
		estado=c;
	}
	
	
	/**Devuelve la posicion de la siguiente casilla en la direccion(orientacion) que tiene
	 * 
	 * @return int[] = {posicionx,posiciony}
	 */
	public int[] siguientePosicion()
	{
		int dirx=posx,diry=posy;
		switch(estado)
		{
			case 'u':
				diry--;
				break;
			case 'd':
				diry++;
				break;
				
			case 'r':
				dirx++;
				break;
				
			case 'l':
				dirx--;
				break;
				
			default:
				break;
		};
		return new int[]{dirx,diry};
	}
}
