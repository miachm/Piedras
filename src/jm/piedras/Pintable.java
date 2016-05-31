package jm.piedras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Pintable extends Reconocible{

	
	
	protected int despl;
	
	
	/**pixeles por fila y columna*/
	protected int pw,ph;
	
	protected boolean gravedadActivada;
	
	/**Margen en los Ladrillos*/
	protected int marg = 3;

	/**Imagenes de las piedras y cajas*/
	protected Image piedra,caja,paja;
	
	
	public Pintable()
	{
		marg=3;
		piedra = new ImageIcon(getClass().getResource("/imagenes/Piedra.png")).getImage();

		caja = new ImageIcon(getClass().getResource("/imagenes/Caja.png")).getImage();
		
		paja = new ImageIcon(getClass().getResource("/imagenes/Paja.png")).getImage();
		
		gravedadActivada=false;
		
		pw=20;
		ph=20;
		despl=0;
	}
	
	public void setPintable(boolean ga,int pw, int ph, int despl)
	{
		gravedadActivada=ga;
		this.pw=pw;
		this.ph=ph;
		this.despl=despl;
	}
	
	/**Pinta una roca en la posicion deseada
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	protected void pintarRoca(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.CYAN);
		g2d.fillRect(j*pw+despl, i*ph, pw, ph);
		g2d.setColor(Color.BLUE);
		g2d.fillPolygon(
				new int[]{j*pw+marg+despl,j*pw+marg+despl,(j+1)*pw-marg-6+despl,(j+1)*pw-marg+despl,(j+1)*pw-marg+despl},
				new int[]{i*ph+marg,(i+1)*ph-marg,(i+1)*ph-marg,(i+1)*ph-marg-6,i*ph+marg}
				, 5);
	}
	
	
	/**Pinta una piedra en la posicion que se le pasa
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	protected void pintarPiedra(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(piedra, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
	}
	
	/**Pinta una caja en la posicion pasada
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	protected void pintarCaja(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(caja, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
	}
	
	/**Pinta una bola de paja en la posicion pasada
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	
	protected void pintarPaja(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(paja, j*pw+1+despl, i*ph+1,pw-1,ph-1, null);
	}
	
	/**Pinta una meta en la posicion
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	protected void pintarMeta(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.yellow);
		g2d.drawRect(j*pw+despl, i*ph, pw, ph);
	}
	
	/**Pinta la gravedad en la posicion (i,j)
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y donde pintar en la matriz
	 * @param j posicion x donde pintar en la matriz
	 */
	protected void pintarGravedad(Graphics2D g2d,int i, int j)
	{
		if(gravedadActivada)//si esta activada
		{
			g2d.setColor(Color.GREEN);
			g2d.fillRect(j*pw+2+despl, i*ph+2, pw-4,ph-4);
			g2d.setColor(Color.RED);
			g2d.drawRect(j*pw+1+despl, i*ph+1, pw-2,ph-2);
			g2d.setColor(Color.yellow);
			g2d.drawRect(j*pw+despl, i*ph, pw,ph);
		}
		else//si esta desactivada
		{
			g2d.setColor(Color.RED);
			g2d.fillRect(j*pw+1+despl, i*ph+1, pw-2,ph-2);
			g2d.setColor(Color.yellow);
			g2d.drawRect(j*pw+despl, i*ph, pw,ph);
		}
	}
	
	
}
