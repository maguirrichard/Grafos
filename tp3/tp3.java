/**********************************
    * TP3
    * @author Maguirrichard Oliveira.
    * @version 1 02/2017.
    *********************************/
 
import java.util.Scanner;
 
class tp3
{
  /* Linhas = origem  -  Colunas = destino */
  public static void main(String[] args)
  {
     Scanner in = new Scanner(System.in);
     String str = in.nextLine();
     int cont = Integer.parseInt(str);
     Grafo graf = new Grafo (cont);
 
     for(int i = 0; i < cont; i++)
     {
        str = in.nextLine();
        graf.pegaArq(str);
     }
     graf.montaGraf();
     //graf.mostraFila();

     str = in.nextLine();
     cont = Integer.parseInt(str);
     graf.resetArr(cont);

     for(int i = 0; i < cont; i++)
     {
        str = in.nextLine();
        graf.pegaMatFalt(str);
     }
     
     graf.graf();
  }
 
}
 
class Grafo
{
  public Fila[] fil;
  public String[] array;
  public int primeiroL;
  public int tamanho;

  public Grafo(int i)
  {
     this.fil =  new Fila[i];
     this.array = new String[i];
     this.tamanho = i;
     this.primeiroL = 0;
  }

  public void pegaArq(String s)
  {
     array[primeiroL] = s;
     primeiroL++;
  }

  public void graf()
  {
     int[] a = new int[array.length];
     for(int i = 0; i < array.length; i++)
     {
        for(int j = 0; j < tamanho; j++)
        {
           if(array[i].equals(fil[j].primeiro.prox.materia))
           {
              a[i] = fil[j].primeiro.prox.grau;
           }
        }
     }

     //a = quicksort(a);

     String[] s = new String[100];
     primeiroL = 0;

     for(int l = 0; l < array.length; l++)
     {
        for(int m = 0; m < tamanho; m++)
        {
           if(array[l].equals(fil[m].primeiro.prox.materia))
              s = busca(s, fil[m].primeiro.prox);
        }        
     }

     int t = 0;
     for(; s[t] != null; t++);

     array = new String[t];

     for(int b = 0; b < t; b++) array[b] = s[b];

     a = new int[t];

     for(int z = 0; z < t; z++)
     {
        for(int x = 0; x < tamanho; x++)
        {
           if(array[z].equals(fil[x].primeiro.prox.materia))
           {
              a[z] = fil[x].primeiro.prox.grau;
           }
        }
     }
     
     a = quicksort(a);
    
     for(int p = 0; p < t; p++)
        System.out.println(array[p]);

     int n = (a[a.length-1] - a[0]) + 1;
     System.out.println();
     System.out.println("Faltam " + n +  " semestres para formar");
  }

  public String[] busca(String[] s, No n)
  {
     if(naoContem(s, n.materia))
     {
        s[primeiroL] = n.materia;
        primeiroL++;
     }

     for(No i = n.prox; i != null; i = i.prox)
     {
        for(int j = 0; j < tamanho; j++)
        {
           if(i.materia.equals(fil[j].primeiro.prox.materia))
              s = busca(s, fil[j].primeiro.prox);
        }
     }
     
     return s;
  }

  public boolean naoContem(String[] s, String a)
  {
     boolean resp = true;
     for(int i = 0; i < s.length; i++)
     {
        if(a.equals(s[i])) resp = false;
     }
     return resp;
  }


  /**
   * Algoritmo de ordenacao Quicksort.
   */
  public int[] quicksort(int[] a)
  {
     a = quicksort(a, 0, (array.length)-1);
     return a;
  }
 
  /**
   * Algoritmo de ordenacao Quicksort.
   * @param int esq inicio do array a ser ordenado
   * @param int dir fim do array a ser ordenado
   */
  private int[] quicksort(int[] a, int esq, int dir)
  {
     int i = esq, j = dir;
     int pivo = a[(dir+esq)/2];
     while (i <= j)
     {
        while (a[i] < pivo) i++;
        while (a[j] > pivo) j--;
        if (i <= j)
        {
           a = swap(i, j, a);
           i++;
           j--;
        }
     }
     if (esq < j)  quicksort(a, esq, j);
     if (i < dir)  quicksort(a, i, dir);
     return a;
  }

  public int[] swap(int i, int j, int[] a)
  {
     int k = a[i];
     a[i] = a[j];
     a[j] = k;
     
     //swap do array de string
     String s = array[i];
     array[i] = array[j];
     array[j] = s;

     return a;
  }

  public Fila[] clone()
  {
     int h = fil.length;
     Fila[] clone = new Fila[h];

     for(int i = 0; i < h; i++)
        clone[i] = fil[i].clone();

     return clone;
  }

  public void montaGraf()
  {
     for(int m = 0; m < tamanho; m++)
     {
        String[] spl1 = array[m].split(";");
        fil[m] = new Fila();
        if(spl1.length == 1)
           fil[m].inserir(spl1[0], 0);
        else if(spl1.length > 1)
        {
           if(!spl1[1].contains(","))
              fil[m].inserir(spl1[0], 1);
           else{
              String[] a = spl1[1].split(",");
              fil[m].inserir(spl1[0], a.length);
           }
        }
        for(int i = 0; i < tamanho; i++)
        {
           String[] spl2 = array[i].split(";");
           if(spl2.length > 1 && !spl2[1].contains(",")) 
           {
              if(spl2[1].equals(spl1[0]))
                 fil[m].inserir(spl2[0]);
           }else if(spl2.length > 1 && spl2[1].contains(",")){
              String[] spl3 = spl2[1].split(",");
              if(spl3[0].equals(spl1[0]))
                 fil[m].inserir(spl2[0]);
              else if(spl3[1].equals(spl1[0]))
                 fil[m].inserir(spl2[0]);
           } 
        }  
     }
     achaGrafo();
  }

  public void achaGrafo()
  {
     for(int i = 0; i < tamanho; i++)
     {
        if(fil[i].primeiro.prox.grau == 0)
        {
           for(No k = fil[i].primeiro.prox.prox; k != null; k = 
               k.prox)
           {
              k.grau = 1;
              achaGrau(k.materia, k.grau);
           }
        }
     }
  }

  public void achaGrau(String mat, int grau)
  {
     int i = 0;
     for(; i < tamanho && !fil[i].primeiro.prox.materia.equals(mat); i++);
     No k = fil[i].primeiro.prox;
     if(k.grau < grau) k.grau = grau;
     grau++;

     if(k.prox != null)
     {
        for(No m = k.prox; m != null; m = m.prox)
        {
           if(m.grau < grau) m.grau = grau;
           achaGrau(m.materia, m.grau);
        }
     }     
  }

  public void resetArr(int k)
  {
     array = new String[k];
     primeiroL = 0;
  }

  public void pegaMatFalt(String s)
  {
     array[primeiroL] = s;
     primeiroL++;
  }


  public void mostraFila(Fila[] f)
  {
     for(int i = 0; i < tamanho; i++)
        f[i].mostrar();
  }

  public void mostraFila()
  {
     for(int i = 0; i < tamanho; i++)
        fil[i].mostrar();
  }

  public void mostraListStr()
  {
     for(int i = 0; i < array.length; i++)
        System.out.println(array[i]);
  }
}
 
class Fila 
{
  public No primeiro;
  private No ultimo;
  
  
  /*
   * Construtor da classe que cria uma fila sem elementos (somente no cabeca).
   */
  public Fila()
  {
     primeiro = new No();
     ultimo = primeiro;
  }
  
  
  /*
   * Insere elemento na fila (politica FIFO).
   * @param x int elemento a inserir.
   */
  public void inserir(String s, int g) 
  {
     ultimo.prox = new No(s, g);
     ultimo = ultimo.prox;
  }

  /*
   * Insere elemento na fila (politica FIFO).
   * @param x int elemento a inserir.
   */
  public void inserir(String s) 
  {
     ultimo.prox = new No(s);
     ultimo = ultimo.prox;
  }

  /*
   * Insere elemento na fila (politica FIFO).
   * @param x int elemento a inserir.
   */
  public void inserir(No n) 
  {
     ultimo.prox = n;
     ultimo = ultimo.prox;
  }

  public Fila clone()
  {
     Fila fil = new Fila();
     for(No i = this.primeiro.prox; i != null; i = i.prox)
        fil.inserir(i);
     
     return fil;
  }  
  
  /*
   * Remove elemento da fila (politica FIFO).
   * @return Elemento removido.
   * @trhows Exception Se a fila nao tiver elementos.
   */
  public String remover()
  {
     No tmp = primeiro.prox;
     primeiro = primeiro.prox;
     return tmp.materia;
  }

  public String removerR()
  {
     No i = primeiro.prox;
     for(; i.prox != ultimo; i = i.prox);
     String str = ultimo.materia;
     ultimo = i;
     ultimo.prox = null;
     i = null;
     return str;
  }

  public int pesquisar(String s)
  {
     No i = primeiro.prox;
     int cont = 0;
     for(; i != null && !i.materia.equals(s); i = i.prox, cont++);
     
     return cont;
  }
 
  public boolean isVazia()
  {
     return (primeiro == ultimo);
  }
 
  public void mostrar()
  {
     No i = primeiro.prox;
     System.out.print(i.materia + " " + i.grau);
     for(i = i.prox; i != null; i = i.prox)
     {
        System.out.print(" -> " + i.materia + " " + i.grau);
     }
     System.out.println("");

     /*No i = primeiro.prox;
     System.out.print(i.materia);
     for(i = i.prox; i != null; i = i.prox)
     {
        System.out.print(" -> " + i.materia);
     }
     System.out.println("");*/
  }


  public int tamanho()
  {
     int t = 0;

     for(No i = primeiro.prox; i != null; i = i.prox) t++;

     return t;
  }
}
 
class No
{
  public String materia;
  public No prox;
  public int grau;
 
  public No()
  {
     this.materia = "";
     this.prox = null;
     this.grau = 0;
  }
 
  public No(String s)
  {
     this.materia = s;
     this.prox = null;
     this.grau = 0;
  }

  public No(String s, int grau)
  {
     this.materia = s;
     this.prox = null;
     this.grau = grau;
  }
}
 
