package br.com.praiasoft.matematica;

public class Principal {

	public static void main(String[] args) {
		
		for(int w=1; w<=3999; w++){	
			
			String romano = Decimal2Romanos.converte( w);
			System.out.println( Integer.toString(w) + " - " + romano);
			
			int decimal = (int) Romanos2Decimal.calcula(romano);
			
			if(w != decimal )
			{
				System.out.println("Error. . .");
				System.exit(1);
			}
			
		}

	}

}
