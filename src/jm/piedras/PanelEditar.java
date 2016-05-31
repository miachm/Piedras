 package jm.piedras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

  
public class PanelEditar extends JPanel {
 
	/**serialVersionUID*/
	private static final long serialVersionUID = -9020008485022846960L;


	/**Ventana*/
	private Ventana ventana;
	
	/**Paleta para seleccionar la ficha*/
	private PaletaEditar paleta;

	/**Tablero de editar*/
	private TableroEditar tableroE;

	/**Manipulador de eventos del teclado(KeyListener)*/
	private KeyListener kl;

	/**Panel para elegir el nivel*/
	private Elecciones elecciones;

	/**Manejador de niveles*/
	private Niveles niveles;

	/**Dice si hemos creado un nuevo mapa*/
	private boolean nuevo;

	/**fichero actual, si estamos editando alguno*/
	private String actual;

	/**última tecla pulsada(ultimo elemento insertado)*/
	private char ultima;
	
	/**Dice si se esta arrastrando con el botón izquierdo, true, si se ha pulsado(no soltado) el botón izquierdo
	 * o si se esta arrastrando con el botón derecho(eliminando)*/
	private boolean arrastrando,eliminando;
	
	LectorTablero lector;
	
	/**Constructor
	 * 
	 * @param v Ventana*/
	public PanelEditar(Ventana v) {
		super();
		ventana =v;
		
		lector = new LectorTablero();
		
		//instanciamos los componentes
		tableroE = new TableroEditar(this,lector);
		
		elecciones = new Elecciones(lector);
		niveles = new Niveles();
		
		
		///Manipulador de eventos de las teclas
		kl = new KeyListener()
		{
			public void keyPressed(KeyEvent arg0) {
//				System.out.println(arg0.getKeyCode()+", "+arg0.getExtendedKeyCode()+", "+arg0.getKeyChar());
				int code = arg0.getKeyCode();
				char c = arg0.getKeyChar();
				if(code==27)//si se pulsa escape, salimos
				{
					escPressed();
				}
				else//sino procesamos la tecla
					codigo(c,code);
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		};
		
		addKeyListener(kl);
		
		//añadimos un manipulador de eventos de movimiento del ratón
		addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent arg0) {//cuando se arrastra el ratón
				
				selecciona(arg0.getY(),arg0.getX());
				if(!(arrastrando&&eliminando))
				{
					if(arrastrando)//si hemos hecho click con el boton izquierdo
					{
						codigo(ultima,0);
					}
					if(eliminando)//si hemos pulsado con el botón derecho, sin soltar
					{
						tableroE.quitar();
						repaint();
					}
				}
			}
			public void mouseMoved(MouseEvent arg0) {//cuando se mueve el ratón
				selecciona(arg0.getY(),arg0.getX());
			}});
		
		//añadimos un manipulador de eventos de acciones del raton
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0) {repaint();}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {//cuando se pulsa el ratón
				int c = arg0.getButton();
				if(c==MouseEvent.BUTTON1)//si se pulsa sobre el botón izquierdo
				{
					selecciona(arg0.getY(),arg0.getX());
					codigo(ultima,0);
					arrastrando=true;//
				}
				else if(c==MouseEvent.BUTTON3)//cuando le damos con el botón derecho, eliminamos
				{
					tableroE.quitar();
					eliminando=true;
				}
				repaint();
			}
			public void mouseReleased(MouseEvent arg0) {
				arrastrando=false;//soltamos el ratón
				eliminando=false;
			}});
		
		
		arrastrando=false;//iniciamos arrastrando a false
		
		paleta = new PaletaEditar(this);

		
	}
	
	/**Preparamos el panel(reiniciamos el panel)*/
	public void prepara()
	{
		niveles.prepara();
		
		ultima='x';
		
		arrastrando=false;
		
		eliminando=false;
		
		elecciones.prepara(niveles);
		
		//mostramos el panel para seleccionar el nivel
		int res = JOptionPane.showConfirmDialog(ventana, elecciones, Messages.getString("PanelEditar.Ventana_Title"), JOptionPane.OK_CANCEL_OPTION); 
		
		if(res ==JOptionPane.OK_OPTION)//si le hemos dado a OK
		{
			nuevo=false;
			niveles.set(elecciones.get());
			actual =niveles.getNivel();
			tableroE.cargar(actual);
		}
		else//si cancelamos, creamos uno nuevo
		{
			nuevo=true;
			tableroE.reinicia();
		}
		
		tableroE.selecciona(100,100);//seleccionamos una casilla de por enmedio
		repaint();
		
		mostrarAyuda();
		
		//cargamos la paleta(la mostramos)
		paleta.cargar();
		
		this.requestFocus();
	}
	
	
	public void paint(Graphics c)
	{
		
		Graphics2D g2d = (Graphics2D)c;
		
		int xh = getSize().height;
		int xw = getSize().width;
		
		tableroE.paint(g2d, xh, xw);
	}
	
	
	/**cuando seleccionamos un elemento
	 * 
	 * @param y
	 * @param x
	 */
	private void selecciona(int y, int x)
	{
		tableroE.selecciona(y,x);
		repaint();
	}
	
	
	/**Devuelve el keylistener del panel de editar*/
	public KeyListener getKListener()
	{
		return kl;
	}

	/**Cuando pulsamos la tecla Escape*/
	public void escPressed()
	{
		paleta.esconder();
		ventana.volver();
	}
	
	/**Cuando le damos a guardar*/
	public void Guarda()
	{
		try{
			String res;
			
			if(this.nuevo)//si hemos creado uno nuevo
			{
				File f = new File("./niveles/");
				
				String n[]=f.list();
				
				int nn=n.length;
				
				boolean ok = true;
				
				do{
				
					res= "./niveles/nivel"+(nn<11?"0":"")+(nn<100?"0":"")+(nn+1)+".map"; 
					
					ok=true;
					
					for(String s:n)
					{
						if(s.equals(res))
							ok=false;
					}
					nn++;
				}
				while(!ok);
			}
			else//si hemos editado uno
			{
				res = actual;
			}
			
			if(tableroE.Guardar(res))//si se ha guardado
			{
				paleta.esconder();
				ventana.volver();//volvemos al panel inicial
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**Cuando se pulsa una tecla que no es Esc*/
	public void codigo(char c,int code)
	{
		/* Teclas de edición - aquellas que se pueden repetir, que son elementos individuales que puedes poner en el tablero
		 * Teclas de control - aquellas que no se pueden repetir con frecuencia(T e Y,incluso Ayuda), que puedes presionar una sola vez(Guardar, jugador), 
		 * 				y que controlan el estado de la edición (Guardar, quitar, arriba, abajo, derecha, izquierda)
		 */
		//guardamos la ultima actual, por si la tecla pulsada es de control, no de añadir elementos
		char aux =ultima;
		//guardamos la última para si arrastramos
		ultima=c; 
		if(c=='p')//piedra
		{
			tableroE.anyadir('P');
		}
		else if(c=='g')//gravedad
		{
			tableroE.anyadir('G');
		}
		else if(c=='d')//destino/meta
		{
			tableroE.anyadir('F');
		}
		else if(c=='c')//caja
		{
			tableroE.anyadir('C');
		}
		else if(c=='x')//pared
		{
			tableroE.anyadir('X');
		}
		else if(c=='w')//paja
		{
			tableroE.anyadir('W');
		}
		else//si no es una tecla de edición, quizas sea de control
		{
			
			ultima=aux;//decimos que la última es la última de edición, para tener una última válida
			if(c==' ')//quitar
			{
				tableroE.quitar();
			}
			else if(c=='h')//jugador (es de control, porque solo puede haber un jugador en el tablero, y no  puedes poner dos al arrastrar o pulsar)
			{
				tableroE.anyadir('H');
			}
			else if(c=='t')//fila de pared
			{
				tableroE.anyadirT();
			}
			else if(c=='y')//columna de pared
			{
				tableroE.anyadirY();
			}
			else if(c=='s')//guardar
			{
				Guarda();
			}
			else if(code==112)//Mostrar Ayuda
				mostrarAyuda();
			else if(code==38 || code==87)//UP
				tableroE.mover(-1, 0);
			else if(code==40||code==83)//DOWN
				tableroE.mover(+1, 0);
			else if(code==37||code==65)//LEFT
				tableroE.mover(0, -1);
			else if(code==39||code==68)//RIGHT
				tableroE.mover(0, +1);
		}
		actualizarPaleta();
		repaint();
	}
	
	/**Muestra un JOptionPane con la ayuda*/
	private void mostrarAyuda()
	{
		String ayuda =Messages.getString("PanelEditar.Help") 
				+ Messages.getString("PanelEditar.Help_Key") 
				+ Messages.getString("PanelEditar.Help_Wall") 
				+ Messages.getString("PanelEditar.Help_Gravity") 
				+ Messages.getString("PanelEditar.Help_Destiny") 
				+ Messages.getString("PanelEditar.Help_Box") 
				+ Messages.getString("PanelEditar.Help_Stone") 	
				+ Messages.getString("PanelEditar.Help_Straw")
				+ Messages.getString("PanelEditar.Help_Delete") 
				+ Messages.getString("PanelEditar.Help_Column") 
				+ Messages.getString("PanelEditar.Help_Row") 
				+ Messages.getString("PanelEditar.Help_About_Row_Column") 
				+ Messages.getString("PanelEditar.Help_Player") 
				+ Messages.getString("PanelEditar.Help_Save") 
				+ Messages.getString("PanelEditar.Help_Exit") 
				+ Messages.getString("PanelEditar.Help_Mouse_Left") 
				+ Messages.getString("PanelEditar.Help_Mouse_Right") 
				+";)"; 
		
		JOptionPane.showMessageDialog(this, ayuda, Messages.getString("PanelEditar.Help_Name"), JOptionPane.INFORMATION_MESSAGE); 
	}
	
	/**Pone el ítem que se introducirá cuando se pulse el botón*/
	public void setUltimoInsertar(char c)
	{
		codigo(c,0);
	}
	
	
	private void actualizarPaleta()
	{
		paleta.actualiza(ultima);
	}
	
	public Ventana getVentana()
	{
		return this.ventana;
	}
}
