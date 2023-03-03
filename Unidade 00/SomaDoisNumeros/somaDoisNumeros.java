package SomaDoisNumeros;

import java.util.*;

class somaDoisNumeros {

	public static void main(String[] args) {
		Scanner buffer = new Scanner(System.in);
		int numA, numB;
		
		System.out.println("Primeiro valor:"); //Ler primeiro valor
		numA = buffer.nextInt();
		
		System.out.println("Segundo valor:"); //Ler segundo valor
		numB = buffer.nextInt();
		
		System.out.println("A soma de " + numA + " + " + numB + " = " + (numA + numB));
		buffer.close();
	}

}
