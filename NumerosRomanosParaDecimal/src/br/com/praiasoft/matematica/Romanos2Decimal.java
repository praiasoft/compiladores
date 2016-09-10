package br.com.praiasoft.matematica;

import br.com.praiasoft.matematica.Ordem.Retorno;

public class Romanos2Decimal {
	
	public static void main(String[] args) {

		Reconhece reconhece = new Reconhece();
		reconhece.calcula("MMMDCCCLXXXVIII");	//3888
		reconhece.calcula("MMMCMXCIX");			//3999
		reconhece.calcula("MMDCXXXVIII");		//2638
		reconhece.calcula("Iii");
		reconhece.calcula("iv");
		reconhece.calcula("v");
		reconhece.calcula("vi");
		reconhece.calcula("vii");
		reconhece.calcula("vIIi");
		reconhece.calcula("ix");
		reconhece.calcula("x");
	}
}

class Reconhece{
	
	Ordem unidade = new Ordem('i', 'v', 'x', 1);
	Ordem dezena = new Ordem('x', 'l', 'c', 10);
	Ordem centena = new Ordem('c', 'd', 'm', 100);
	Ordem milhar = new Ordem('m', '*', '$', 1000);
	
	Reconhece(){
	}
	
	public long calcula( String romano ){

		romano = romano.toLowerCase();
		
		Retorno retornoMilhar = milhar.reconhece(romano);
		System.out.println(retornoMilhar);
		
		Retorno retornoCentena = centena.reconhece( retornoMilhar.getFaltaCalcular());
		System.out.println(retornoCentena);
		
		Retorno retornoDezena = dezena.reconhece( retornoCentena.getFaltaCalcular());
		System.out.println(retornoDezena);
		
		Retorno retornoUnidade = unidade.reconhece( retornoDezena.getFaltaCalcular());
		System.out.println( retornoUnidade );
		
		long resposta = (retornoMilhar.getValor() + retornoCentena.getValor() + 
				retornoDezena.getValor() + retornoUnidade.getValor());
		
		System.out.println("resposta = " + resposta);
		
		System.out.println("----------------------------------");
		
		return resposta;
	}
}



class Ordem {
	
	char i,v,x;
	long multiplicador;
	
	Ordem(char i, char v, char x, long multiplicador ){
		this.i = i;
		this.v = v;
		this.x = x;
		this.multiplicador = multiplicador;
	}
	
	public Retorno reconhece(String romano){
		
		int estado = 0;
		int resposta = 0;
		boolean sair = false;
		int w;
		
		for(w=0; w<4 && w<romano.length(); w++){
			char digito = romano.charAt(w);
			
			switch (estado) {
			case 0:
				if(digito == i){ resposta = 1; estado = 1; }
				else if(digito == v){ resposta = 5; estado = 4; }
				else if(digito == x){ resposta = 10; estado = 3;}
				else sair = true;
				break;
			case 1:
				if(digito == i){ resposta = 2; estado = 2; }
				else if(digito == v){ resposta = 4; estado = 3; }
				else if(digito == x){ resposta = 9; estado = 3;}
				else sair = true;
				break;
			case 2:
				if(digito == i){ resposta = 3; estado = 3; }
				else sair = true;				
				break;
			case 3:
				sair = true;
				break;
			case 4:
				if(digito == i){ resposta = 6; estado = 5; }
				else sair = true;
				break;
			case 5:
				if(digito == i){ resposta = 7; estado = 6; }
				else sair = true;
				break;
			case 6:
				if(digito == i){ resposta = 8; estado = 3; }
				else sair = true;
				break;
			default:
				break;
			}
			
			if(sair)break;
		}
		
		Retorno retorno = new Retorno(
				romano.substring(0, w),
				romano.substring(w),
				resposta * multiplicador);
		
		
		return retorno;
	}
	
	class Retorno{
		private String reconhecido;
		private String faltaCalcular;
		private Long valor;
		
		public Retorno() {
		}

		public Retorno(String reconhecido, String faltaCalcular, Long valor) {
			super();
			this.reconhecido = reconhecido;
			this.faltaCalcular = faltaCalcular;
			this.valor = valor;
		}

		public String getFaltaCalcular() {
			return faltaCalcular;
		}

		public void setFaltaCalcular(String faltaCalcular) {
			this.faltaCalcular = faltaCalcular;
		}

		public Long getValor() {
			return valor;
		}

		public void setValor(Long valor) {
			this.valor = valor;
		}

		public String getReconhecido() {
			return reconhecido;
		}

		public void setReconhecido(String reconhecido) {
			this.reconhecido = reconhecido;
		}

		@Override
		public String toString() {
			return "Retorno [reconhecido=" + reconhecido + ", faltaCalcular=" + faltaCalcular + ", valor=" + valor
					+ "]";
		}
	}
}