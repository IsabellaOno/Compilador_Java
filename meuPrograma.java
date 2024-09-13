import java.util.Scanner;
public class MainClass{ 
	public static void main(String args[]){
 		Scanner scanner = new Scanner(System.in);
		int  a;
		int  b;
		int  c;
		System.out.println("Programa Testando Item 9");

		System.out.println("Digite A");

		a = scanner.nextInt();
		System.out.println("Digite B");

		b = scanner.nextInt();
		
		do {
		  c = a+b;
 		} while (a<b);

    }
}
