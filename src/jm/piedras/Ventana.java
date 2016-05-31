package jm.piedras;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana extends JFrame {

	/***/ 
	private static final long serialVersionUID = 3537487473852470916L;
	
	/**panelactual*/
	private JPanel panelactual;
	
	/**keylistener actual*/
	private KeyListener klactual;
	
	/**panel inicial*/
	private PanelIniciar paneliniciar;
	
	/**keylistener para el panel inicial*/
	private KeyListener kliniciar;
	
	/**panel del juego*/
	private PanelJugar paneljugar;
	
	/**keylistener para el panel jugar*/
	private KeyListener kljugar;
	
	/**Panel de los creditos*/
	private PanelCreditos panelcreditos;
	
	/**KeyListener de los creditos*/
	private KeyListener klcreditos;
	
	/**Panel de edicion*/
	PanelEditar paneleditar;
	
	/**keylistener de edicion*/
	KeyListener kleditar;
	
	/**Contenedor principal de la ventana*/
	private Container cp;
	
	private Sonido sonido;
	
	/**constructor por defecto*/
	public Ventana()
	{
		super(Messages.getString("Ventana.Titulo")); //$NON-NLS-1$
		
		try{
		initComponents();
		
		
		confVentana();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.toString(),"ERRPR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**crea los componentes de la ventana*/
	private void initComponents()
	{
		sonido = new Sonido(this);
		
		cp = getContentPane();
		
		cp.setLayout(new GridLayout(1,1));
		 
		
		paneliniciar=new PanelIniciar(this,sonido);
		
		kliniciar = paneliniciar.getKListener();
		
		paneljugar = new PanelJugar(this,sonido);
		
		kljugar = paneljugar.getKListener();
		
		paneleditar=new PanelEditar(this);
		
		kleditar=paneleditar.getKListener();
		
		panelcreditos=new PanelCreditos(this);
		
		klcreditos=panelcreditos.getKListener();
		
		cp.add(paneliniciar);
		
		addKeyListener(kliniciar);
		
		panelactual=paneliniciar;
		klactual=kliniciar;
		
	}
	
	
	/**Configura la ventana*/
	private void confVentana()
	{
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icon2.png")).getImage()); //$NON-NLS-1$
	}
	
	
	/**Cuando se pulsa sobre jugar*/
	public void botonJugar()
	{
		sonido.Play_Menu_Click();
		
		cp.remove(paneliniciar);
		removeKeyListener(kliniciar);
		
		cp.add(paneljugar);
		this.repaint();
		setSize(801,601);
		
		addKeyListener(kljugar);
		
		klactual=kljugar;
		panelactual=paneljugar;
		paneljugar.prepara();
	}
	
	
	/**Cuando se pulsa sobre editar*/
	public void botonEditar()
	{
		sonido.Play_Menu_Click();
		
		cp.remove(paneliniciar);
		removeKeyListener(kliniciar);
		
		cp.add(paneleditar);
		addKeyListener(kleditar);
		
		panelactual=paneleditar;
		klactual=kleditar;
		
		setSize(801,601);
		repaint();
		paneleditar.prepara();
	}
	
	
	/**cuando se pulsa sobre creditos*/
	public void botonCreditos()
	{
		removeKeyListener(klactual);
		cp.remove(panelactual);
		
		cp.add(panelcreditos);
		addKeyListener(klcreditos);
		
		panelactual=panelcreditos;
		klactual=klcreditos;
		
		setSize(801,601);
		repaint();
	}
	
	
	/**cuando se pulsa sobre salir*/
	public void botonSalir()
	{
		paneljugar.pararGravedad();
		System.exit(1);
	}
	
	/**volver de a la pantalla inicial*/
	public void volver()
	{
		cp.remove(panelactual);
		removeKeyListener(klactual);
		
		panelactual=paneliniciar;
		klactual=kliniciar;
		
		cp.add(paneliniciar);
		addKeyListener(klactual);
		
		setSize(800,600);
		repaint();
	}
	
	
	public void errorMusica(Exception e)
	{
//		JOptionPane.showMessageDialog(parentComponent, message)
		JOptionPane.showMessageDialog(this,Messages.getString("Ventana.Fail_Sound")+e,Messages.getString("Ventana.Error"),JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
		System.exit(1);
		//showMessageDialog(null, "Eggs are not supposed to be green.",1);
	}
	
}
