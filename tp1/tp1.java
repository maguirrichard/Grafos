/**********************************
    * TP1
    * @author Maguirrichard Oliveira.
    * @version 1 02/2017.
    *********************************/

import java.util.Scanner;

class tp1
{
/*   Linhas = origem  -  Colunas = destino */
  public static void main(String[] args)
  {
     Scanner in = new Scanner(System.in);
     String str = in.nextLine();
     //MyIO.println(str);
     str = in.nextLine();
     //MyIO.println(str);
     int tam = Integer.parseInt(str);
     int[][] matriz = new int[tam][tam];

     matriz = zerarMatriz(matriz, tam);
     //mostrarMatriz(matriz, tam);

     str = in.nextLine();
     while(!str.equals("FIM"))
     {
        matriz = completMatriz(matriz, tam, str);
        str = in.nextLine();
     }
     //mostrarMatriz(matriz, tam);


     str = in.nextLine();
     //System.out.print("grau:");
     mostraGrau(matriz,tam, str);

     str = in.nextLine();
     //System.out.print("n vertice:");
     verificaVertice(matriz, str);

     //System.out.print("n aresta:");
     contaArestas(matriz, tam);

     //System.out.print("completo:");
     verificaCompleto(matriz, tam);

     //System.out.print("falta pra completo:");
     completaGrafo(matriz, tam);
  }

  public static int[][] completMatriz(int[][] matriz, int tam, String s)
  {
     int[][] m = matriz;
     int linha = 0, coluna = 0, peso = 0;
     String array[] = s.split(",");
     linha = Integer.parseInt(array[0]);
     coluna = Integer.parseInt(array[1]);
     peso = Integer.parseInt(array[2]);
     m[linha][coluna] = peso;     
     return m;
  }

  public static void mostraGrau(int[][] matriz, int tam, String str)
  {
     int[][] m = matriz;
     int coluna = Integer.parseInt(str);
     int linha = 0;
     int cont = 0;
     for(; linha < tam; linha++)
     {
        if(m[linha][coluna] != 0) cont++;
     }
     linha = coluna;
     for(coluna += 1; coluna < tam; coluna++)
     {
        if(m[linha][coluna] != 0) cont++;
     }
     System.out.println(cont);     
  }

  public static void verificaVertice(int[][] matriz, String str)
  {
     int[][] m = matriz;
     String array[] = str.split(",");
     int linha = Integer.parseInt(array[0]);
     int coluna = Integer.parseInt(array[1]);
     if(m[linha][coluna] != 0)
        System.out.println("SIM");  
     else if(m[coluna][linha] != 0)
        System.out.println("SIM");
     else
        System.out.println("NAO");
  }

  public static void contaArestas (int[][] matriz, int tam)
  {
     int cont = 0;
     int[][] m = matriz;
     for(int linha = 0; linha < tam; linha++)
     {
        for(int coluna = 0; coluna < tam; coluna++)
        {
           if(linha < coluna && m[linha][coluna] != 0) cont++;
        }
     }
     System.out.println(cont);
  }

  public static void verificaCompleto (int[][] matriz, int tam)
  {
     boolean r = true;
     int[][] m = matriz;
     for(int linha = 0; linha < tam; linha++)
     {
        for(int coluna = 0; coluna < tam; coluna++)
        {
           if(linha < coluna && m[linha][coluna] == 0) 
           {
              r = false;
              coluna = tam;
           }
        }
        if(r == false) linha = tam;
     }
     if(r == false) MyIO.println("NAO");
     else System.out.println("SIM");
  }

  public static void completaGrafo (int[][] matriz, int tam)
  {
     int[][] m = matriz;
     for(int linha = 0; linha < tam; linha++)
     {
        for(int coluna = 0; coluna < tam; coluna++)
        {
           if(linha < coluna && m[linha][coluna] == 0) 
           {
              System.out.println(linha + "," + coluna + ",1"); 
           }
        }
     }

  }

  public static int[][] zerarMatriz(int[][] matriz, int tam)
  {
     int[][] m = matriz;
     for(int linha = 0; linha < tam; linha++)
     {
        for(int coluna = 0; coluna < tam; coluna++)
        {
           m[linha][coluna] = 0;
        }
     }
     
     return m;
  }

  public static void mostrarMatriz(int[][] matriz, int tam)
  {
     int[][] m = matriz;
     for(int linha = 0; linha < tam; linha++)
     {
        for(int coluna = 0; coluna < tam; coluna++)
        {
           System.out.print("" + m[linha][coluna] + " ");
        }
        System.out.println("");
     }
     System.out.println("");
  }
}
