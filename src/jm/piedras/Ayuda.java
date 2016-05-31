package jm.piedras;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JPanel;
  
public class Ayuda extends JPanel{
  
	/***/
	private static final long serialVersionUID = 8035926902826271976L;
	
	/**tama�o bloques*/
	private int pw,ph;
	
	
	Pintable pintable;
	
	/**Constructor
	 * 
	 * @param pj paneljugar
	 */
	public Ayuda()
	{
		super();
		pintable = new Pintable();
	}
	
	
	/**Pinta el componente
	 * 
	 * @param g2d Graficos donde pintar
	 * @param xh algura del panel
	 * @param xw anchura del panel
	 */
	public void paint(Graphics2D g2d,int xh,int xw)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, xw, xh);
		
		pw = xw/25;
		ph=xh/18;
		
		pintable.pw=pw;
		pintable.ph=ph;
		
		char str[];
		int lng;
		
		pintarGravedad(g2d,1,2);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Gravity").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 2*ph);
		
		pintarMeta(g2d,4,3);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Destiny").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 5*ph);
		
		pintable.pintarCaja(g2d, 7, 3);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Box").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 7*ph+20);
		
		pintable.pintarPiedra(g2d, 10, 3);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Stone").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 10*ph+20);
		
		pintable.pintarRoca(g2d, 13, 3);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Wall").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 13*ph+20);
		
		
		pintable.pintarPaja(g2d,16,3);
		g2d.setColor(Color.white);
		str=Messages.getString("Ayuda.Straw").toCharArray();
		lng=str.length;
		g2d.drawChars(str, 0, lng, 5*pw, 17*ph-10);

		str=Messages.getString("Ayuda.Help_Repeat").toCharArray();
		lng=str.length;
		g2d.drawChars(str,0, lng, 17*pw, 4*ph);
		
		str=Messages.getString("Ayuda.Help_Music").toCharArray();
		lng=str.length;
		g2d.drawChars(str,0, lng, 17*pw, 8*ph);
		
		str=Messages.getString("Ayuda.Help_Exit").toCharArray();
		lng=str.length;
		g2d.drawChars(str,0, lng, 17*pw, 12*ph);
		
		str=Messages.getString("Ayuda.QuickSave").toCharArray();
		lng=str.length;
		g2d.drawChars(str,0, lng, 17*pw, 16*ph);
		
		str=Messages.getString("Ayuda.QuickLoad").toCharArray();
		lng=str.length;
		g2d.drawChars(str,0, lng, 17*pw, 17*ph);
	}
	
	/**Pinta una meta en la posicion
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	private void pintarMeta(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.yellow);
		g2d.drawRect(j*pw, i*ph, pw, ph);
	}
	
	/**Pinta la gravedad en la posicion (i,j)
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y donde pintar en la matriz
	 * @param j posicion x donde pintar en la matriz
	 */
	private void pintarGravedad(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.RED);
		g2d.fillRect(j*pw+1, i*ph+1, pw-2,ph-2);
		g2d.setColor(Color.yellow);
		g2d.drawRect(j*pw, i*ph, pw,ph);
		j++;
		g2d.setColor(Color.GREEN);
		g2d.fillRect(j*pw+2, i*ph+2, pw-4,ph-4);
		g2d.setColor(Color.RED);
		g2d.drawRect(j*pw+1, i*ph+1, pw-2,ph-2);
		g2d.setColor(Color.yellow);
		g2d.drawRect(j*pw, i*ph, pw,ph);
	}
	
}
