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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class PanelCreditos extends JPanel {

	  
	/***/
	private static final long serialVersionUID = 8582764565711534117L;

	private Ventana ventana;
	
	private KeyListener kl;
	
	Image fondo;
	
	public PanelCreditos(Ventana v) {
		ventana=v;
		
		
		fondo = new ImageIcon(v.getClass().getResource("/imagenes/Menu.png")).getImage();
		
		Font f = new Font("Calibri",2,35),f2=new Font("Calibri",0,60);
		
		JLabel cred,aut,lang,data;
		
		cred=new JLabel(Messages.getString("PanelCreditos.Credits"),JLabel.CENTER);
		aut=new JLabel(Messages.getString("PanelCreditos.Autors"),JLabel.CENTER);
		lang=new JLabel(Messages.getString("PanelCreditos.Language"),JLabel.CENTER);
		data=new JLabel(Messages.getString("PanelCreditos.Date"),JLabel.CENTER);

		cred.setForeground(Color.WHITE);
		aut.setForeground(Color.WHITE);
		lang.setForeground(Color.WHITE);
		data.setForeground(Color.WHITE);
		
		cred.setFont(f2);
		aut.setFont(f);
		lang.setFont(f);
		data.setFont(f);
		
		this.setLayout(new GridLayout(4,1));
		
		add(cred);
		this.add(aut);
		add(lang);
		add(data);
		
		kl=new KeyListener()
		{
			public void keyPressed(KeyEvent arg0) {
				int code = arg0.getKeyCode();
				if(code==27)
				{
					ventana.volver();
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		};
		
		addKeyListener(kl);
		this.addMouseListener(new MouseAdapter(){public void mousePressed(MouseEvent arg0) {ventana.volver();}});
		
	}
	
	
	public void paint(Graphics c)
	{
		Graphics2D g2d = (Graphics2D)c;

		if(c!=null && fondo!=null)
			g2d.drawImage(fondo, 0, 0,this.getSize().width,getSize().height,null);
		
		super.paintComponents(g2d);
	}
	
	
	public KeyListener getKListener()
	{
		return kl;
	}

}
