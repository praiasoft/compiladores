package br.com.praiasoft.matematica;
public class Decimal2Romanos {

	private static String romanos[] ={"","I","II","III","IV","V","VI","VII","VIII","IX", "X"};
	
	public static String converte( int decimal ){

		String entrada = String.valueOf(decimal);
		
		if(!(decimal >=1 && decimal <=3999)){
			throw new NumberFormatException("Favor entrar com um número entre 1 e 3999 para ser convertivo para Romanos.");
		}
		
		while(entrada.length()<4)
			entrada = '0'+entrada;
		
		String resposta = "";
		resposta += romanos[entrada.charAt(0)-'0'].replace('I', 'M');
		resposta += romanos[entrada.charAt(1)-'0'].replace('V', 'D').replace('X', 'M').replace('I', 'C')  ;
		resposta += romanos[entrada.charAt(2)-'0'].replace('V', 'L').replace('X', 'C').replace('I', 'X') ;
		resposta += romanos[entrada.charAt(3)-'0'] ;
		
		return resposta;
	}
}
