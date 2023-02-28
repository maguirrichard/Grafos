/**********************************
    * TP2
    * @author Maguirrichard Oliveira.
    * @version 1 02/2017.
    *********************************/

import java.util.Scanner;

class tp2
{
  /* Linhas = origem  -  Colunas = destino */
  public static void main(String[] args)
  {
     Grafo graf;
     Scanner in = new Scanner(System.in);
     String str = in.nextLine();
     int cont = Integer.parseInt(str);

     for(int i = 0; i < cont; i++)
     {
        str = in.nextLine();
        if(confereResolve(str))
        {
           graf = new Grafo(str);
           graf.bfs();
        }else System.out.println("erro!\n");
     }
  }

  public static boolean confereResolve(String s)
  {
     boolean resp = false;
     int cont = 0;
     for(int i = 0; i < (s.length()-1); i++)
     {
        for(int j = i+1; j < s.length(); j++)
        {
           if((int)s.charAt(i) != 48 && (int)s.charAt(j) != 48 &&
              ((int)s.charAt(i) < (int)s.charAt(j))) cont++;
        }
     }
     if(cont % 2 == 0) resp = true;    
     return resp;
  }
}

class Grafo
{
  public Vertice origem;
  public String fim = "123456780";
  
  public Grafo(String s)
  {
     origem = new Vertice(s);
  }

  public void bfs()
  {
     int aj = -1;
     origem.cor = 'c';
     origem.pai = null;
     Fila fil = new Fila();
     Arvore a = new Arvore();
     Vertice tmp;
     try{
        fil.inserir(origem);
        while(!fil.isVazia() && aj == -1)
        {
           tmp = fil.remover();
           if(!tmp.elemento.equals(fim))
           {
              if(!a.pesquisar(tmp.elemento))
              {
                 a.inserir(tmp.elemento);
                 tmp = geraVizi(tmp);
                 for(int i = 0; i < tmp.vizinhos.length; i++)
                 {
                    if(tmp.vizinhos[i].cor == 'b')
                    {
                       tmp.vizinhos[i].cor = 'c';
                       tmp.vizinhos[i].pai = tmp;
                       fil.inserir(tmp.vizinhos[i]);
                    }
                 }
                 tmp.cor = 'p';
              }
           }else{
              aj = 0;
              mostraSeq(tmp);
           }  
        }
     }catch(Exception erro){
        System.out.println(erro.getMessage());
     }
  }

  public Vertice geraVizi(Vertice k)
  {
     String m = k.elemento;

     if(m.charAt(0) == '0'){
        k.vizinhos = new Vertice[2];
        k.vizinhos[0] = new Vertice(swap(m,0,1));
        k.vizinhos[1] = new Vertice(swap(m,0,3));

     }else if(m.charAt(1) == '0'){
        k.vizinhos = new Vertice[3];
        k.vizinhos[0] = new Vertice(swap(m,1,0));
        k.vizinhos[1] = new Vertice(swap(m,1,2));
        k.vizinhos[2] = new Vertice(swap(m,1,4));

     }else if(m.charAt(2) == '0'){
        k.vizinhos = new Vertice[2];
        k.vizinhos[0] = new Vertice(swap(m,2,1));
        k.vizinhos[1] = new Vertice(swap(m,2,5));

     }else if(m.charAt(3) == '0'){
        k.vizinhos = new Vertice[3];
        k.vizinhos[0] = new Vertice(swap(m,3,0));
        k.vizinhos[1] = new Vertice(swap(m,3,4));
        k.vizinhos[2] = new Vertice(swap(m,3,6));

     }else if(m.charAt(4) == '0'){
        k.vizinhos = new Vertice[4];
        k.vizinhos[0] = new Vertice(swap(m,4,1));
        k.vizinhos[1] = new Vertice(swap(m,4,3));
        k.vizinhos[2] = new Vertice(swap(m,4,5));
        k.vizinhos[3] = new Vertice(swap(m,4,7));

     }else if(m.charAt(5) == '0'){
        k.vizinhos = new Vertice[3];
        k.vizinhos[0] = new Vertice(swap(m,5,2));
        k.vizinhos[1] = new Vertice(swap(m,5,4));
        k.vizinhos[2] = new Vertice(swap(m,5,8));

     }else if(m.charAt(6) == '0'){
        k.vizinhos = new Vertice[2];
        k.vizinhos[0] = new Vertice(swap(m,6,3));
        k.vizinhos[1] = new Vertice(swap(m,6,7));

     }else if(m.charAt(7) == '0'){
        k.vizinhos = new Vertice[3];
        k.vizinhos[0] = new Vertice(swap(m,7,4));
        k.vizinhos[1] = new Vertice(swap(m,7,6));
        k.vizinhos[2] = new Vertice(swap(m,7,8));

     }else if(m.charAt(8) == '0'){
        k.vizinhos = new Vertice[2];
        k.vizinhos[0] = new Vertice(swap(m,8,5));
        k.vizinhos[1] = new Vertice(swap(m,8,7));
     }     
    
     return k;
  }

  public String swap(String v, int x, int y)
  {
     String s = "";
     char t = v.charAt(x);
     char u = v.charAt(y);
     for(int i = 0; i < v.length(); i++)
     {
        if(i == x) s += u;
        else if(i == y) s += t;
        else s += v.charAt(i);
     }
     return s;
  }

  public void mostraSeq(Vertice k)
  {
     Fila fil = new Fila();
     mostraSeq(k, fil);
     fil.mostrar();
  }

  public void mostraSeq(Vertice k, Fila fil)
  {
     if(k != origem) mostraSeq(k.pai, fil);
     try{
        fil.inserir(k);
     }catch(Exception erro){
        System.out.println(erro.getMessage());
     }
  }

}

class Vertice
{
  public String elemento;
  public Vertice[] vizinhos;
  public char cor;
  public Vertice pai;

  public Vertice(String s)
  {
     this.elemento = s;
     this.cor = 'b';
     this.pai = null;
  }

  public Vertice()
  {
     this.cor = 'p';
     this.pai = null;
  }
}

class Fila 
{
  private No primeiro;
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
  public void inserir(Vertice s) throws Exception
  {
     ultimo.prox = new No(s);
     ultimo = ultimo.prox;
  }
 
 
  /*
   * Remove elemento da fila (politica FIFO).
   * @return Elemento removido.
   * @trhows Exception Se a fila nao tiver elementos.
   */
  public Vertice remover() throws Exception
  {
     No tmp = primeiro.prox;
     primeiro = primeiro.prox;
     return tmp.vert;
  }

  public boolean isVazia()
  {
     return (primeiro == ultimo);
  }

  public void mostrar()
  {
     int cont = 0;
     for(No i = primeiro.prox; i != null; i = i.prox)
     {
        System.out.println(i.vert.elemento);
        cont++;
     }
     System.out.println(cont);
  }
}

class No
{
  public Vertice vert;
  public No prox;

  public No()
  {
     this.vert = null;
     this.prox = null;
  }

  public No(Vertice s)
  {
     this.vert = s;
     this.prox = null;
  }
}

/**
 * No da arvore binaria
 * @author Max do Val Machado
 */
class No2
  {
  public int elemento; // Conteudo do no.
  public No2 esq, dir;  // Filhos da esq e dir.
 
  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   */
  public No2(int elemento)
  {
     this(elemento, null, null);
  }
 
  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   * @param esq No da esquerda.
   * @param dir No da direita.
   */
    public No2(int elemento, No2 esq, No2 dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}
 
/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
class Arvore 
{
  private No2 raiz; // Raiz da arvore.
 
  /**
   * Construtor da classe.
   */
  public Arvore() 
  {
     raiz = null;
  }
 
  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */
  public boolean pesquisar(String x)
  {
     int c = Integer.parseInt(x);
     return pesquisar(c, raiz);
  }
 
  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
  * @return <code>true</code> se o elemento existir,
  * <code>false</code> em caso contrario.
  */
  private boolean pesquisar(int x, No2 i) 
  {
     boolean resp;
     if (i == null) resp = false;
 
     else if (x == i.elemento) resp = true;
 
     else if (x < i.elemento) resp = pesquisar(x, i.esq);
 
     else resp = pesquisar(x, i.dir);

     return resp;
  } 

  public void inserir(String x) throws Exception 
  {
     int c = Integer.parseInt(x);
     raiz = inserir(c, raiz);
  }
 
  /**
   * Metodo privado recursivo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se o elemento existir.
   */
  private No2 inserir(int x, No2 i) throws Exception
  {
     if (i == null) i = new No2(x);
 
     else if (x < i.elemento) i.esq = inserir(x, i.esq);
 
     else if (x > i.elemento) i.dir = inserir(x, i.dir);
 
     else throw new Exception("Erro ao inserir!");
 
     return i;
  }
}
