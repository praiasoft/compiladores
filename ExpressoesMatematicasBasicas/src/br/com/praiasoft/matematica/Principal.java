package br.com.praiasoft.matematica;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Principal {
	private float resultado;
	
	private String f[] = new String[100];
	
	private List<Float> pilha = new ArrayList<Float>();
	
	private int x;

	
	private void adicionaNaPilha( float item ){
		pilha.add(item);
	}
	
	private float removeDaPilha(){
		if(pilha.isEmpty()){
			System.out.println("erro de pilha");
			return 0;
		}else
			return  pilha.remove(pilha.size()-1);
	}
	
	private boolean reconhece( String w ){
		
		if(x < f.length){ 
			if(f[x].equals(w)){
				System.out.println(x+ " " + f[x]+"  "+ w);
				x++;
				return true;
			} else
				return false;
		}else{
			System.out.println("erro 2");
			return false;
		}
	}

	public float calcula( String expressao ){
		f = (expressao + "$").split("");
		x = 0;
		if(e()){
			if(reconhece("$")){
				System.out.println("ok");
			}else{
				System.out.println("erro 3");
			}
		}else{
			System.out.println("erro");
		}
		return removeDaPilha();
		
	}
	
/*	
	E -> E+T | T
	T -> T*F | F
	F -> (E) | digito

eliminando recursão a esquerda

	E -> TE’
	E’ -> +TE’| ε
	T -> FT’
	T’-> *FT’ | ε
	F -> (E)| digito
*/	

	public boolean e(){
		System.out.println("e()");
		return t() && e_linha();
	}
	public boolean e_linha(){
		System.out.println("e_linha()");
		
		if(reconhece("+")){
			float a = removeDaPilha();
			boolean x = t();
			boolean y = e_linha();
			float b = removeDaPilha();
			adicionaNaPilha(a+b);
			return x && y;
		}else if (reconhece("-")){
			float a = removeDaPilha();
			boolean x = t();
			boolean y = e_linha();
			float b = removeDaPilha();
			adicionaNaPilha(a-b);
			return x && y;
			
		}
		return true;
	}
	public boolean t (){
		System.out.println("t()");

		return f() && t_linha();
	}
	public boolean t_linha() {
		System.out.println("t_linha()");

		if(reconhece("*")){
			float a = removeDaPilha();
			boolean x = f();
			boolean y = t_linha();
			float b = removeDaPilha();
			adicionaNaPilha(a*b);
			return x && y;			
		}else if(reconhece("/")){
			float a = removeDaPilha();
			boolean x = f();
			boolean y = t_linha();
			float b = removeDaPilha();
			adicionaNaPilha(a/b);
			return x && y;			
		}
		return true;
	}
	public boolean f(){
		System.out.println("f()");

		if(reconhece("(")){
			if(e()){
				if(reconhece(")")){
					return true;
				}else{
					System.out.println("falta ')'");
					return false;
				}
			}else{
				return false;
			}
		}else if(reconheceNumero()){
			return true;
		}else{
			System.out.println("Erro. esperado '(' ou digito");
			return false;
		}
	}
	

	private boolean reconheceNumero( ){
		String result = "";
		boolean reconheceu = false;
		
		for(int i = x; i< f.length; i++){
			if(f[i].compareTo("0") >= 0 && f[i].compareTo("9") <= 0){
				result += f[i];
				x++;
				reconheceu = true;
			}else{
				break;
			}
		}
		if(reconheceu){ 
			try {
				adicionaNaPilha(Float.valueOf(result));		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reconheceu;
	}
	
	public static void main(String[] args) {
		Principal principal = new Principal();
		
		//for(int w = 0; w < 10; w++){
			String expressao = JOptionPane.showInputDialog("Entre com a expressão","(123+321*543)/(765-987)");
		
			JOptionPane.showMessageDialog(null,  principal.calcula( expressao ));
		//}

	}

}
