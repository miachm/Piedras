package jm.piedras;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
 
 
public class PanelBotones extends JPanel {
 
	/***/
	private static final long serialVersionUID = -5270512290174446967L;


	private Boton botones[][];
	
	
	private static int h=18,w=25;
	
	public PanelBotones() 
	{
		super(new GridLayout(h,w));
		
		botones = new Boton[h][w];
		for(int i = 0; i < h; i++)
		{
			for(int j = 0; j < w; j++)
			{
				botones[i][j]=new Boton();
				this.add(botones[i][j]);
			}
		}
	}

	public PanelBotones(LayoutManager arg0) {super(arg0);}

	public PanelBotones(boolean arg0) {super(arg0);}

	public PanelBotones(LayoutManager arg0, boolean arg1) {super(arg0, arg1);}

	
	
	public Boton[][] getBotones(){return botones;}
	
	public int getW(){return w;}
	public int getH(){return h;}
	
	public void set(char mat[][])
	{
		for(int i = 0; i < h; i++)
		{
			for(int j = 0; j < w; j++)
			{
				botones[i][j].set(mat[i][j]);
			}
		}
	}
}
