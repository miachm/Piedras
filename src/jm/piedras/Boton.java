package jm.piedras;

import java.awt.Color;
import javax.swing.JButton;
   
public class Boton extends JButton {

	/***/
	private static final long serialVersionUID = -8393624522286768087L;
 
	public Boton() {
		super();
		setEnabled(false);
	}

	public void set(char c)
	{
		
		setText(null);
//		setBackground(Color.black);
//		setIcon(null);
//		this.setFont(new Font("Times New Roman",22,2));
		setForeground(Color.WHITE);
		if(Tablero.isCaja(c))
		{
			setBackground(Color.RED);
		}
		else if( Tablero.isGravedad(c))
		{
			setBackground(Color.blue);
		}
		else if(Tablero.isMeta(c))
		{
			setBackground(Color.YELLOW);
		}
		else if(Tablero.isPared(c))
		{
			setBackground(Color.BLACK);
		}
		else if(Tablero.isPiedra(c))
		{
//			setText("P");
			setBackground(Color.MAGENTA);
		}
		else if(Tablero.isJugador(c))
		{
//			setText("J");
			setBackground(Color.GRAY);
		}
		else if(Tablero.isPaja(c))
		{
			setBackground(Color.PINK);
		}
		else
		{
			setBackground(Color.WHITE);
		}
		this.setSize(40, 40);
	}
}
