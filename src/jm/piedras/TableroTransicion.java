package jm.piedras;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

public class TableroTransicion extends Tablero {

	public TableroTransicion(Ventana v, PanelJugar pj, LectorTablero l) {
		super(v, pj, l);
	}

	
	
	
	
	public void set(Tablero t)
	{
		for(int i = 0; i < nF; i++)
		{
			for(int j = 0; j < nC; j++)
			{
				t.tablero[i][j]=tablero[i][j];
				t.tablerobase[i][j]=tablerobase[i][j];
			}
		}
		t.jugador.set(jugador.getEstado(), jugador.getX(), jugador.getY());
		t.tiempo=tiempo;
		t.cargadoDe=cargadoDe;
		t.gravedad.reset();
		t.gravedad.contando();
		t.gravedadActivada=false;
		t.gravedad.setLimite(tiempo);
	}
	
	
	
	
	public void paint(Graphics2D g2d,int xh, int xw,int fila)
	{
		ph = xh/nF;
		pw=xw/nC;
		
		despl = (xw-(pw*nC))/2;
		
		//Pintar el tablero base
		for(int i = 0;  i < fila; i++)
		{
			for(int j = 0; j < nC; j++)
			{
				if(isPared(i,j))//comprobamos los objetos inmóviles primero
				{
					pintarRoca(g2d,i,j);
				}
				else if(isMeta(i,j))
				{
					pintarMeta(g2d, i, j);
				}
				else if(isGravedad(i,j))
				{
					pintarGravedad(g2d,i,j);
				}
				if(isCaja(i,j))//luego pintamos los objetos móviles encima
				{
					pintarCaja(g2d, i, j);
				}
				else if(isPiedra(i,j))
				{
					pintarPiedra(g2d, i, j);
				}
				else if(isPaja(i,j))
				{
					pintarPaja(g2d,i,j);
				}
			}
		}
//		//pintar el jugador
//		if(jugador!=null)
//			jugador.Paint(g2d, pw, ph);
	}
	
	public void cargar(String fichero)
	{
		cargadoDe=fichero;
		
		jugador=lector.leerTablero(fichero,tablero,tablerobase, nF, nC);
		
		if(jugador!=null)
		{
			tiempo=lector.getTiempo();
		}
		else
		{
			JOptionPane.showMessageDialog(null, Messages.getString("TableroTransicion.Error_Loading_Map")+fichero , Messages.getString("TableroTransicion.Error"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
			System.exit(1);
		}
	}
}
