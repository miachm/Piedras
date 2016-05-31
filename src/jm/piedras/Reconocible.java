package jm.piedras;

public class Reconocible 
{

	
	public static boolean isPiedra(char a){return a=='p'||a=='P';}
	
	public static boolean isPared(char a){return a=='x'||a=='X';}

	public static boolean isCaja(char a){return a=='c'|| a=='C';}

	public static boolean isMeta(char a){return a=='d'||a=='F';}

	public static boolean isGravedad(char a){return a=='g'||a=='G';}
	
	/**Dice si el caracter que se le pasa representa al jugador*/
	public static boolean isJugador(char a){return a=='H'||a=='h';}
	
	public static boolean isPaja(char a){return a=='w' || a == 'W';}
}
