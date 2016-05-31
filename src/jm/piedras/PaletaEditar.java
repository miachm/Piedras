package jm.piedras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PaletaEditar extends JFrame {

	private static final long serialVersionUID = 2124965726989049600L;
	
	private Image personaje;
	
	private int pw,ph,xh,xw;
	
	private int marg=3;
	
	private PanelEditar panelE;
	
	private boolean activo;
	
	private int selx,sely;
	
	private char ultima;
	
	Pintable pintable;
	
	/**Dice el caracter asociado a esa posicion de la paleta*/
	private static final char caracteres[][]={{'g','d'},{'c','p'},{'x',','},{'w','h'}};
	
	public PaletaEditar(PanelEditar pe)
	{
		super();
		
		pintable = new Pintable();
		
		panelE=pe;
		
		ultima='x';
		
		initComponents();
		
		eventHandler();
		
		windowConfig();
	}
	
	private void initComponents()
	{
		activo=false;
		
		selx=0;
		sely=3;
		
		personaje = new ImageIcon(getClass().getResource("/imagenes/Personaje.png")).getImage();
		
		repaint();
	}
	
	private void windowConfig()
	{
		int tamElem=100;
		
		setVisible(false);
		setSize(2*tamElem,4*tamElem);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
	}
	
	
	private void eventHandler()
	{
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				pressed(e.getX(),e.getY());
			}});
	}
	
	
	protected void pressed(int x, int y)
	{
		if(activo)
		{
			if(x/pw!=1||y/ph!=2)
			{
				selx=x/pw;
				sely=y/ph;
				
				selecciona(selx,sely);
				
				repaint();
			}
		}
	}
	
	private void selecciona(int x, int y)
	{
		if(!(x==1&&y==2))
		{
			ultima=caracteres[y][x];
			
			panelE.setUltimoInsertar(ultima);
			
		}
	}
	
	public void cargar()
	{
		setVisible(true);
		activo=true;
	}
	
	
	public void esconder()
	{
		activo=false;
		setVisible(false);
	}
	
	/**Pinta el componente
	 * 
	 * @param g2d Graficos donde pintar
	 * @param xh algura del panel
	 * @param xw anchura del panel
	 */
	public void paint(Graphics g)
	{
		
		xh=getSize().height;
		xw=getSize().width;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, xw, xh);
		
		pw = xw/2;
		ph = xh/4;
		
		pintarGravedad(g2d,0,0);
		
		pintarMeta(g2d,0,1);
		
		pintarCaja(g2d, 1, 0);
		
		pintarPiedra(g2d,1,1);
		
		pintarRoca(g2d,2, 0);
		
		pintarPaja(g2d,3,0);
		pintarJugador(g2d,3,1);
		
		g2d.setColor(Color.WHITE);
		g2d.drawRect(selx*pw, sely*ph, pw, ph);
		g2d.drawRect(selx*pw+1, sely*ph+1, pw-2, ph-2);
	}
	
	
	/**Pinta una piedra en la posicion que se le pasa
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	private void pintarPiedra(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(pintable.piedra, j*pw+2, i*ph+2,pw-4,ph-4, null);
	}
	
	/**Pinta una caja en la posicion pasada
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	private void pintarCaja(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(pintable.caja, j*pw+2, i*ph+2,pw-4,ph-4, null);
	}
	
	private void pintarPaja(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(pintable.paja, j*pw+2, i*ph+2,pw-4,ph-4, null);
	}
	
	private void pintarJugador(Graphics2D g2d,int i, int j)
	{
		g2d.drawImage(personaje, j*pw+2, i*ph+2,pw-4,ph-4, null);
	}
	
	/**Pinta una meta en la posicion
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	private void pintarMeta(Graphics g2d,int i, int j)
	{
		g2d.setColor(Color.GREEN);
		g2d.drawRect(j*pw+2, i*ph+2,pw-4,ph-4);
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
	
	/**Pinta una roca en la posicion deseada
	 * 
	 * @param g2d Graficos donde pintar
	 * @param i posicion y
	 * @param j posicion x
	 */
	private void pintarRoca(Graphics2D g2d,int i, int j)
	{
		g2d.setColor(Color.CYAN);
		g2d.fillRect(j*pw+2, i*ph+2,pw-4,ph-4);
		g2d.setColor(Color.BLUE);
		g2d.fillPolygon(
				new int[]{j*pw+marg,j*pw+marg,(j+1)*pw-marg-6,(j+1)*pw-marg,(j+1)*pw-marg},
				new int[]{i*ph+marg,(i+1)*ph-marg,(i+1)*ph-marg,(i+1)*ph-marg-6,i*ph+marg}
				, 5);
	}
	
	
	/**Pone la herramienta seleccionada
	 * 		case 0://gravedad
				break;
			case 1://caja
				break;
			case 2://meta
				break;
			case 3://piedra
				break;
			case 4://roca
	 * @param c caracter asociado
	 */
	public void actualiza(char c)
	{
		if(ultima!=c)
		{
			ultima=c;
			if(c== 'c')
			{
				selx=0;
				sely=1;
			}
			if(c== 'g')
			{
				selx=0;
				sely=0;
			}
			if(c== 'd')
			{
				selx=1;
				sely=0;
			}
			if(c== 'p')
			{
				selx=1;
				sely=1;
			}
			if(c== 'x')
			{
				selx=0;
				sely=2;
			}
			if (c == 'w')
			{
				selx = 0;
				sely = 3;
			}
			if (c == 'h')
			{
				selx = 1;
				sely = 3;
			}
			repaint();
		}
	}
}
