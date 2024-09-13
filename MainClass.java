import java.util.Scanner;
public class MainClass{ 
	public static void main(String args[]){
 		Scanner scanner = new Scanner(System.in);
		int  a;
		int  b;
		int  c;
		int  d;
		System.out.println("Programa Teste");
		System.out.println("Digite A");
		a = scanner.nextInt();
		System.out.println("Digite B");
		b = scanner.nextInt();
		
		if (a<b){
		  c = a+b;
 		}
		else {
		  c = a-b;
		}

		System.out.println("C e igual a ");
		System.out.println(c);
		d = c*a+b;
		System.out.println("D e igual a ");
		System.out.println(d);
    }
}
