package jm.piedras;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelIniciar extends JPanel {
  
	/***/
	private static final long serialVersionUID = -6169537261008809264L;

	private Ventana ventana;
	
	private Image fondo;
	
	private JLabel jugar,editar,creditos,salir;
	
	private Color texto,texto2;
	
	private KeyListener klistener;
	
	private Sonido sonido;
	
	private HashMap<JLabel,Integer> set;
	
	private HashMap<JLabel,Font> sfuentes1,sfuentes2;
	
	private String fuentes[]={"Calibri","Calibri","Calibri","Calibri"};//{"Segoe Print","Segoe Script","FangSong","MV Boli"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	
	private JLabel jls[];
	 
	int selected;
	
	public PanelIniciar(Ventana v,Sonido ss)
	{
		super();
		
		ventana=v;
		sonido=ss;
		
		
		creaComponentes();
		
		configuracionComponentes();


		selected = 0;
		
		jugar.setForeground(texto2);
		jugar.setFont(sfuentes2.get(jugar));
		
		repaint();
	}
	
	
	private void creaComponentes()
	{
		fondo = new ImageIcon(ventana.getClass().getResource("/imagenes/Menu.png")).getImage(); //$NON-NLS-1$
				
		setLayout(new GridLayout(5,1));
		
		
		jugar = new JLabel(Messages.getString("PanelIniciar.Play"),JLabel.CENTER); //$NON-NLS-1$
		editar = new JLabel(Messages.getString("PanelIniciar.Edit"),JLabel.CENTER); //$NON-NLS-1$
		creditos = new JLabel(Messages.getString("PanelIniciar.Credits"),JLabel.CENTER); //$NON-NLS-1$
		salir = new JLabel(Messages.getString("PanelIniciar.Exit"),JLabel.CENTER); //$NON-NLS-1$
		
		texto = Color.WHITE;
		texto2 = Color.YELLOW;

		JLabel titulo = new JLabel(Messages.getString("PanelIniciar.Title_Game"),JLabel.CENTER); //$NON-NLS-1$
		
		titulo.setFont(new Font("Calibri",Font.BOLD,50));//FangSong //$NON-NLS-1$
		titulo.setForeground(texto);
		
		add(titulo);
		
		jls =new JLabel[]{jugar,editar,creditos,salir};
		
		set = new HashMap<JLabel,Integer>();
		sfuentes1 = new HashMap<JLabel,Font>();
		sfuentes2 = new HashMap<JLabel,Font>();
	}
		
	
	private void configuracionComponentes()
	{
		int idte=0;
		for(JLabel j:jls)
		{
			set.put(j, idte);
			sfuentes1.put(j, new Font(fuentes[idte],Font.PLAIN,35));
			sfuentes2.put(j, new Font(fuentes[idte],Font.BOLD,35));
			
			j.setFont(sfuentes1.get(j));
			j.setForeground(texto);
			add(j);
			j.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent arg0) {}
				public void mouseEntered(MouseEvent arg0) {
					JLabel jj =(JLabel)arg0.getSource();
					jj.setForeground(texto2);
					jj.setFont(sfuentes2.get(arg0.getSource()));
					sonido.Play_Menu_Mover();
					selected = set.get(arg0.getSource());
				}
				public void mouseExited(MouseEvent arg0) {
					JLabel jj =(JLabel)arg0.getSource();
					jj.setForeground(texto);
					jj.setFont(sfuentes1.get(arg0.getSource()));
				}
				public void mousePressed(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent arg0) {}});
			idte++;
		}
		

		jugar.addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent arg0) {ventana.botonJugar();}});
		editar.addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent arg0) {ventana.botonEditar();}});
		creditos.addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent arg0) {ventana.botonCreditos();}});
		salir.addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent arg0) {ventana.botonSalir();}});
		
		
		klistener = new KeyListener()
		{
			public void keyPressed(KeyEvent arg0) {
				sonido.Play_Menu_Mover();
				int code = arg0.getKeyCode();
				if(code==27)
					ventana.botonSalir();
				else
					teclaPulsada(arg0);
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		};
		
		ventana.requestFocus();
		
		this.addKeyListener(klistener);
	}

	
	public void paint(Graphics c)
	{
		Graphics2D g2d = (Graphics2D)c;

		if(c!=null && fondo!=null)
			g2d.drawImage(fondo, 0, 0,this.getSize().width,getSize().height,null);
		
		super.paintComponents(g2d);
	}
	
	public void teclaPulsada(KeyEvent e)
	{
		int code=e.getKeyCode();
		char c=e.getKeyChar();
		if(code==38 || code==87)//si pulsamos arriba
			upPressed();
		else if(code==40||code==83)//si pulsamos abajo
			downPressed();
		else if(c=='\n')
			jls[selected].getMouseListeners()[1].mousePressed(null);
	}
	
	private void deselecciona()
	{
		jls[selected].setFont(sfuentes1.get(jls[selected]));
		jls[selected].setForeground(texto);
		
	}
	
	private void selecciona()
	{
		jls[selected].setFont(sfuentes2.get(jls[selected]));
		jls[selected].setForeground(texto2);
	}
	
	private void upPressed()
	{
		deselecciona();
		selected=(selected+3)%4;
		selecciona();
		
	}
	
	private void downPressed()
	{
		deselecciona();
		selected=(selected+1)%4;
		selecciona();
	}
	
	
	public KeyListener getKListener(){return klistener;}
	
}
