package jm.piedras;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

public class LectorTablero extends Reconocible{

//	char tablero[][],tablerobase[][];
	
	long tiempo;
	
	
	public LectorTablero()//int nF, int nC)
	{
//		tablero = new char[nF][nC];
//		tablerobase=new char[nF][nC];
	}
	
	public Jugador leerTablero(String fichero,char tablero[][],char tablerobase[][],int nF,int nC)
	{
		Jugador jugador=null;//new Jugador('u',1,1);
		try{
			FileInputStream fis = new FileInputStream(new File(fichero));
			char aux;
			
			
			//obtenemos el numero de cifras que tiene el tiempo
			int siz = fis.read()-'0';
			
			//creamos un vector para ese numero de cifras
			byte ar[] = new byte[siz];
			
			//las leemos
			fis.read(ar,0,siz);
			
			//obtenemos el tiempo
			tiempo = Integer.parseInt(new String(ar));

			//capturamos el salto de linea
			fis.read();
			
			//para saber si son 1 o 2 caracteres entre lineas,  '.' es dos caracteres, otherwise 1
			boolean ol = fis.read()=='.';
			
			
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i<nF; i++)
				{
					aux = (char)fis.read();
					tablerobase[i][j]=aux;
				}
				
				fis.read();//leemos el caracter del salto de linea
				if(ol)//leemos el 2o caracter del salto de linea si toca
					fis.read();
				//System.out.println();
			}
			
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i<nF; i++)
				{
					aux = (char)fis.read();
					
					tablero[i][j]=aux;//marcamos los dos tableros como lo que leemos
					
					if(isJugador(aux))//si es el jugador, marcamos los tablero como vacíos
					{
						tablero[i][j]=' ';
						jugador=new Jugador('u', j,i);
					}
				}
				
				fis.read();//leemos el caracter del salto de linea
				if(ol)//leemos el 2o caracter del salto de linea si toca
					fis.read();
				//System.out.println();
			}
			//cargamos el tablero
			jugador.cargar(fis);
			
			fis.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getString("LectorTablero.Fail_Load"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			e.printStackTrace();
			return null;
		}
		return jugador;
	}
	
	
	public boolean GuardarTableroBase(String fichero,char[][] tablero, char tablerobase[][],int nF,int nC,long tiempo,Jugador jugador)
	{
		this.tiempo=tiempo;
		try{
			//abrimos el archivo del que cargamos
			FileOutputStream fos = new FileOutputStream(new File(fichero));
			//escribimos le numero de cifras que tiene le limite del tiempo
			fos.write((int)(""+(tiempo+"").length()).charAt(0));
			//escribimos los bytes del tiempo(las cifras)
			fos.write((""+tiempo).getBytes());
			//un salto de linea
			fos.write((int)'\n');
			//una coma para indicar que hay un espacio entre lineas
			fos.write((int)',');

			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i< nF; i++)
				{
					fos.write((int)tablerobase[i][j]);
				}
				fos.write((int)'\n');
			}
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i< nF; i++)
				{
					if(jugador.getX()==j && jugador.getY()==i)
						fos.write('H');
					else
						fos.write((int)tablero[i][j]);
				}
				fos.write((int)'\n');
			}
			
			jugador.guardar(fos);
			
			fos.close();
			return true;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.toString(), Messages.getString("LectorTablero.Fail_Save"), JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	public long getTiempo(){return tiempo;}
	
	
	
	
	public Jugador leerTableroE(String fichero,char tablero[][],char tablerobase[][], int nF, int nC)
	{
		Jugador jugador = null;
		
		try{
			FileInputStream fis = new FileInputStream(new File(fichero));
			char aux;
			
			//obtenemos el numero de cifras que tiene el tiempo
			int siz = fis.read()-'0';
			
			//creamos un vector para ese numero de cifras
			byte ar[] = new byte[siz];
			
			//las leemos
			fis.read(ar,0,siz);
			
			//obtenemos el tiempo
			tiempo = Integer.parseInt(new String(ar));

			//capturamos el salto de linea
			fis.read();
			
			//para saber si son 1 o 2 caracteres entre lineas,  '.' es dos caracteres, otherwise 1
			boolean ol = fis.read()=='.';
			
			
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i<nF; i++)
				{
					aux = (char)fis.read();
					tablerobase[i][j]=aux;
				}
				
				fis.read();//leemos el caracter del salto de linea
				if(ol)//leemos el 2o caracter del salto de linea si toca
					fis.read();
				//System.out.println();
			}
			
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i<nF; i++)
				{
					aux = (char)fis.read();
					
					tablero[i][j]=aux;//marcamos los dos tableros como lo que leemos
					
					if(isJugador(aux))//si es el jugador, marcamos los tablero como vacíos
					{
						jugador = new Jugador('u', j,i);
					}
				}
				
				fis.read();//leemos el caracter del salto de linea
				if(ol)//leemos el 2o caracter del salto de linea si toca
					fis.read();
				//System.out.println();
			}
			//cargamos el tablero
			jugador.cargar(fis);
			
			fis.close();
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.toString(), Messages.getString("LectorTablero.Fail_Load"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			e.printStackTrace();
		}
		
		return jugador;
	}
	
	
	public boolean guardaTableroE(String archivo, char tablero[][],int nF, int nC,long tiempo, Jugador jugador)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(new File(archivo));
			
			//escribimos le numero de cifras que tiene le limite del tiempo
			fos.write((int)(""+(tiempo+"").length()).charAt(0));
			//escribimos los bytes del tiempo(las cifras)
			fos.write((""+tiempo).getBytes());
			//un salto de linea
			fos.write((int)'\n');
			
			fos.write((int)',');
			for(int j = 0;j<nC; j++)
			{
				for(int i = 0; i< nF; i++)
				{
					if(jugador.getX()==j && jugador.getY()==i)
						fos.write('H');
					else
						fos.write((int)tablero[i][j]);
				}
				fos.write((int)'\n');
			}
			
			jugador.guardar(fos);
			
			fos.close();
			return true;
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getString("LectorTablero.Fail_Save"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			return false;
		}
	}
	
	
	public boolean leerMatriz(String fichero, char mat[][],int nF, int nC)
	{
		try{
			FileInputStream fis = new FileInputStream(new File(fichero));
			
			//nos saltamos el tiempo
			int siz = fis.read()-'0'+1;
			byte bar[] = new byte[siz];
			fis.read(bar,0,siz);
			char c;
			
			boolean ol = fis.read()=='.';
			for(int i = 0; i < 25; i++)
			{
				for(int j = 0; j < 18; j++)
				{
					mat[j][i] = (char)fis.read();
				}
				fis.read();
				if(ol)
					fis.read();
			}
			for(int i = 0; i < 25; i++)
			{
				for(int j = 0; j < 18; j++)
				{
					c = (char)fis.read();
					if(c!=' ')
						mat[j][i] =c;
				}
				fis.read();
				if(ol)
					fis.read();
			}
			fis.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), Messages.getString("LectorTablero.Fail_Electors"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
			return false;
		}
	}
	
	
	public void reformmatearTodo(Niveles n,Tablero t)
	{
		n.prepara();
		do
		{
			t.cargar(n.getNivel());
			t.Guardar();
		}
		while(n.siguiente());
		System.exit(1);
	}
}
