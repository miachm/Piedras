package jm.piedras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelJugar extends JPanel {
  
	/***/
	private static final long serialVersionUID = 5367907252017489897L;
	
	/**Tamanyo del panel*/
	private int xh,xw;

	/**Ventana en la que esta*/
	private Ventana ventana;

	/**Keylistener para el modo de juego*/
	private KeyListener kl;

	/**tablero del juego*/
	private Tablero tablero;
 
	/**Niveles del juego*/
	private Niveles niveles;

	/**Elecciones para elegir el nivel*/
	private Elecciones elecciones;

	/**Nivel actual*/
	private String actual;

	/**tiempo restante*/
	private float tiempo;

	/**hilo del tiempo y de la gravedad*/
	private Gravedad gravedad;

	/**booleano para controlar si estoy mostrando la ayuda o no*/
	private boolean mostrarAyuda;

	/**Panel de ayuda*/
	private Ayuda ayuda;
	
	/**Trucos del juego*/
	private Cheats cheats;
	
	/**Sonido del juego*/
	private Sonido sonido;
	
	/**booleano para decir si se esta escuchando o no la musica*/
	private boolean musica = true;
	
	/**Tablero para la transicion entre niveles*/
	TableroTransicion tablerotrans;
	
	/**num de pasos en la transcicion*/
	int niveltransicion;
	
	/**Clase donde se guarda el juego de guardado y cargado rapido*/
	MemoriaRapida memoriaRapida;
	
	/**la usamos para entre dos niveles si se pulsa algo no haga caso , y  se espere antes de pasr al siguiente nivel*/
	private boolean entreniveles = false;
	
	/**Clase para leer el tablero*/
	LectorTablero lector;
	
	int tiempoguardado;
	
	long ultimokey;
	
	
	/**Constructor
	 * 
	 * @param v Ventana
	 */
	public PanelJugar(Ventana v,Sonido s) {
		super();
		
		ventana=v;
		sonido=s;
		
		ultimokey=System.currentTimeMillis();
		
		mostrarAyuda=false;
		
		kl = new KeyListener()//creamos el manipulador de eventos del teclado
		{
			public void keyPressed(KeyEvent arg0) {
				int code = arg0.getKeyCode();
				if(!entreniveles)
				{
					if(!mostrarAyuda&&System.currentTimeMillis()-ultimokey>100)//si no estamos mostrando la ayuda
					{
						if(code==38 || code==87)//si pulsamos arriba
							upPressed();
						else if(code==40||code==83)//si pulsamos abajo
							downPressed();
						else if(code==37||code==65)//si pulsamos a la izquierda
							leftPressed();
						else if(code==39||code==68)//si pulsamos derecha
							rightPressed();
						else if(arg0.getKeyChar()=='r')//si pulsamos la 'r'
						{
							tablero.cargar(actual);//volvemos a cargar el tablero actual
							repaint();
						}
						else if(code==112)//si pulsamos F1
						{
							mostrarAyuda=true;
							gravedad.descontando();//mostramos la ayuda
						}
						else if (code == 116) //si pulsamos F5
						{
							f5();
						}
						else if (code == 120) //si pulsamos F9
						{
							f9();
						}
						else if (arg0.getKeyChar() == 'm') // si pulsamos m
						{
							if (sonido.musica_sonando())
							{
								musica = false;
								sonido.stop_jugar();
							}
							else
							{
								sonido.play_jugar();
								musica = true;
							}
						}
						else if(code==27)//si pulsamos esc
						{
							escPressed();
						}
						cheats.cheat(arg0.getKeyChar());
					}
					else if(code==112)//si pulsamos F1 estando en ayuda
					{
						mostrarAyuda=false;//quitamos la ayuda, y volvemos a contar el tiempo
						gravedad.contando();
						repaint();
					}
					else if(code==27)//si pulsamos salir mientras ayuda, volvemos al modo normal
					{
						mostrarAyuda=false;
						gravedad.contando();
					}
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		};
		
		this.addKeyListener(kl);
		
		lector = new LectorTablero();
		
//		alre
		tablero = new Tablero(ventana,this,lector);
		
		tablerotrans=new TableroTransicion(ventana,this,lector);
		
		niveles = new Niveles();
		
		elecciones = new Elecciones(lector);
		
		gravedad = new Gravedad(this,tablero);
		
		tablero.setGravedad(gravedad);
		
		ayuda = new Ayuda();
		
		cheats = new Cheats(this,tablero);
		
		memoriaRapida=new MemoriaRapida();
		
		tiempo=1;
		tiempoguardado=0;
		
//		lector.reformmatearTodo(niveles, tablero);

		//////////////////////////////////////////////////////////
		//////////////MANIPULADORES DE EVENTOS DEL RATÓN//////////
		//////////////////////////////////////////////////////////
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){gravedad.movil();}
			public void mouseReleased(MouseEvent e){gravedad.inmovil();}
			public void mouseClicked(MouseEvent e){tablero.click(e.getX(), e.getY());}});
		
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e){tablero.arrastrar(e.getX(), e.getY());}
			public void mouseMoved(MouseEvent e) {}});
		
	}
	
	/**Prepara el panel para entrar*/
	public void prepara()
	{
		niveles.prepara();//carga los niveles
		
		elecciones.prepara(niveles);//carga las elecciones
		
		int res = JOptionPane.showConfirmDialog(elecciones, elecciones, Messages.getString("PanelJugar.Levels"), JOptionPane.OK_CANCEL_OPTION);//Pedimos que seleccionen nivel
		
		if(res ==JOptionPane.OK_OPTION)//si ha seleccionado uno y dado a ok
		{
			niveles.set(elecciones.get());//obtenemos el nivel seleccionado
			actual =niveles.getNivel();
			tablero.cargar(niveles.getNivel());//cargamos le nivel
			repaint();
			
			if (musica)
				sonido.play_jugar();
		}
		else//si ha cancelado
		{
			ventana.volver();//volvemos al panel inicial
		}
		
		cheats.prepara();
		
		entreniveles=false;
		
	}
	
	/**Para ir al siguiente tablero*/
	public void siguiente()
	{
		if(niveles.siguiente())//si hay un siguiente
		{
			gravedad.reset();
			actual=niveles.getNivel();//lo obtenemos
			tablerotrans.cargar(actual);
			
			entreniveles=true;
			niveltransicion=0;
			repaint();
			new Thread(new Runnable(){public void run(){
				
				while(niveltransicion<18)
				{
					repaint();
					niveltransicion++;
					try{
						Thread.sleep(100);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				entreniveles=false;
				
//				tablero.cargar(actual);//lo cargamos
				tablerotrans.set(tablero);
				repaint();
				cheats.siguientenivel();
				gravedad.reset();
			}}).start();
		}
		else//si no hay mas niveles, vamos a los creditos
		{
			gravedad.descontando();
			gravedad.reset();
			escPressed();
			ventana.botonCreditos();
		}
	}
	
	
	public void paint(Graphics c)
	{
		xh=getSize().height-20;
		xw=getSize().width;

		int btiempo = xw-20;
	
		Graphics2D g2d = (Graphics2D)c;
		
		//Pintar el fondo negro
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, getSize().width,getSize().height);
		
		if(!mostrarAyuda&&!entreniveles)
		{
			tablero.paint(g2d, xh, xw);
		}
		else if(entreniveles)
		{
			tablero.paint(g2d, xh, xw);
			tablerotrans.paint(g2d, xh, xw, niveltransicion);
		}
		else
		{
			ayuda.paint(g2d, xh, xw);
		}
		
		
		if(tiempo<0.3)
			g2d.setColor(Color.red);
		else
			g2d.setColor(Color.YELLOW);
		g2d.fillRect(10,xh,(int)(btiempo*tiempo),getSize().height-10);
		
		
		
		if(tiempoguardado>0)
		{
			int pw=xw/18;
			int ph=xh/25;
			g2d.setColor(Color.BLACK);
			g2d.fillRect(10, 2, pw*3, ph/2+10);
			g2d.setColor(Color.WHITE);
			char chars[]=Messages.getString("Jugar.Saving").toCharArray();
			g2d.drawChars(chars, 0, chars.length, 20, 16);
			tiempoguardado--;
		}
	}
	
	
	/**Devuelve el KeyListener de este panel
	 * 
	 * @return kl
	 */
	public KeyListener getKListener()
	{
		return kl;
	}
	
	/**Cuando pulsamos escape*/
	private void escPressed()
	{
		gravedad.pausa();
//		tablero.Guardar();
		sonido.stop_jugar();
		ventana.volver();
	}
	
	/**cuando pulsamos la tecla arriba o w*/
	private void upPressed()
	{
		tablero.upPressed();
		actualiza();
	}
	
	/**cuando pulsamos la tecla abajo*/
	private void downPressed()
	{
		tablero.downPressed();
		actualiza();
	}
	
	/**Cuando pulsamos la tecla izquierda*/
	private void leftPressed()
	{
		tablero.leftPressed();
		actualiza();
	}
	
	/**Cuando pulsamos la tecla derecha*/
	private void rightPressed()
	{
		tablero.rightPressed();
		actualiza();
	}
	
	/**Cuando se pulsa F9 se realiza un guardado rapido*/
	private void f9()
	{
		if(memoriaRapida.isGuardado())
		{
			memoriaRapida.cargar(tablero, niveles, gravedad);
			actual=niveles.getNivel();
			
		}
	}
	
	/**Cuando se pulsa f5 se realiza un cargado rapido, del ultimo guardado rapido(si ha habido alguno antes)*/
	private void f5()
	{
		memoriaRapida.guardar(tablero, niveles, gravedad);
		tiempoguardado=20;
	}
	
	/**actualiza la pantalla*/
	private void actualiza()
	{
		this.repaint();
	}
	
	/**define el porcentaje de tiempo que queda
	 * 
	 * @param f 0-1 ,x ej. 0.76 -> 76% del tiempo
	 */
	public void setTiempo(float f)
	{
		tiempo=f;
		repaint();
	}
	
	/**cuando se agota el tiempo*/
	public void timeOut()
	{
		JOptionPane.showMessageDialog(null,Messages.getString("PanelJugar.Time_Expired"),Messages.getString("PanelJugar.Time_Out"),JOptionPane.ERROR_MESSAGE);
		ventana.volver();
	}
	
	/**para parar el hilo de la gravedad y el tiempo*/
	public void pararGravedad()
	{
		gravedad.interrupt();
	}
	
	public void destiempo()
	{
		gravedad.descontando();
	}
	
	public void tiempo()
	{
		gravedad.contando();
	}
}
