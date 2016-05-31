package jm.piedras;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

  
public class Niveles {
	 
	static String dirniveles="niveles/"; 
	
	String niveles[]={"out.tda"}; 
	
	int nniveles;
	
	int nivelactual;
	
	
	public Niveles()
	{
		cargar();
	}
	
	public void prepara()
	{
		cargar();
		
		nivelactual=0;
	}
	
	
	public void cargar()
	{
		try
		{
			File f = new File(dirniveles);
			ArrayList<String> array = new ArrayList<String>();
			niveles =f.list();
			for(String ni:niveles)
				array.add(ni);
			nniveles = niveles.length;
			Collections.sort(array);
			niveles=array.toArray(new String[0]);
//			System.out.println(nniveles+"");
//			for(String s:niveles)
//			{
//				System.out.println(s);
//			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getString("Niveles.Fail_Load_Levels"),JOptionPane.ERROR_MESSAGE ); 
			nniveles=1;
			niveles = new String[]{"out.tda"}; 
		}
	}
	
	public String getNivel(){return dirniveles+niveles[nivelactual];}
	
	public int getPos(){return nivelactual;}
	
	public boolean siguiente()
	{
		if(nivelactual<nniveles-1)
			nivelactual++;
		else
			return false;
		
		return true;
	}
	
	
	public void set(int a)
	{
		nivelactual=a;
	}
	
	
	public String[] lista(){return niveles;}

	
//	public static void main(String args[])
//	{
//		Niveles n=new Niveles();
//		
//		
//		for(String s:n.niveles)
//		{
//			try{
//				File f = new File(dirniveles+s);
//				f.renameTo(new File(dirniveles+cambianombre(s,2,3)));
//				
//			}catch(Exception e){e.printStackTrace();}
//		}
//	}
//	
//	protected static String cambianombre(String a, int b, int c)
//	{
//		int pos = a.lastIndexOf('.');
//		
//		String ext = a.substring(pos);
//		String aux = a.substring(0,pos);
//		String aux2;
//		
//		aux2 = aux.substring(pos-b);
//		
//		aux = aux.substring(0, pos-b);
//		
//		System.out.println(aux+", "+aux2);
//		
//		for(int i=b; i<b; i++)
//			aux = aux+"0";
//		aux = aux+aux2+ext;
//		
//		System.out.println(aux);
//		
//		return aux;
//	}
	
	//Llenar este vector con tus valores
	public static final int orden[]={2,6,6,4,6,5,3,4,8,8,6,5,6,6,4,8,7,5,8,4,5,7,3,7,7,8,7,6,6,2,7,2,4,4,10,8,6,8,8,4,5,7,4,4,4,8,7};
	
	//main para ordenar los niveles
	public static void main(String args[])
	{
	
		/*
		Coge los niveles de ./Niveles2/ y los pone en ./Niveles/ ordenados segun el criterio de orden
		*/
		Niveles n=new Niveles();
		
		String nivs[]=n.lista();
		for(int i = 0; i <46; i++)
			System.out.println(nivs[i]);
		
		System.out.println(nivs.length);
		
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		for(int i = 0; i<nivs.length; i++)
		{
			lista.add(i);
		}
		
		
		Collections.sort(lista, new Comparator<Integer>(){
			public int compare(Integer o1, Integer o2) {
				return orden[o1]-orden[o2];
			}});
		
		
		System.out.println(lista);
		
		for(int i = 0; i < 45; i++)
			System.out.print(orden[lista.get(i)]+", ");
		System.out.println();
		
		for(int i =0; i<nivs.length-1; i++)
		{
			intercambia(nivs,i,lista.get(i));//copia el fichero i, en la carpeta /niveles/ como el fichero nuevo lista[i](la lista esta ordenada)
		}
		
	}
	
	public static void intercambia(String nivs[],int a , int b)
	{
		try
		{
			File f1 = new File("./niveles2/"+nivs[a]);
			
			File f2 = new File(dirniveles+nivs[b]);
			
			FileInputStream fr = new FileInputStream(f1);
			
			byte buffer[] = new byte[700];
			
			int n =fr.read(buffer);
			
			fr.close();
			
			FileOutputStream fos = new FileOutputStream(f2,false);
			
			fos.write(buffer, 0, n);
			
			fos.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
