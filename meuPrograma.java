import java.util.Scanner;

public class meuPrograma{ 
   
   public static void main(String args[]){ 
      
      Scanner _scTrx = new Scanner(System.in);
      int a;
      int b;
      String x;
      String y;
      System.out.println("Hello World");
      System.out.println("Fim do programa");
      a = _scTrx.nextInt();
      b = _scTrx.nextInt();
      x = _scTrx.nextLine();
      x = _scTrx.nextLine();
      System.out.println(a);

      //Falta adicionar WHILE, DO-WHILE e FOR 
      
      if (a+2>5-b) {
         System.out.println("maior que 5");
      }
      else {
         System.out.println("menor ou igual a 5");
      }

   }
}
