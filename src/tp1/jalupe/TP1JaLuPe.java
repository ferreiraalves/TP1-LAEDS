
package tp1.jalupe;

import java.io.BufferedReader;
import java.util.StringTokenizer;


public class TP1JaLuPe {
    
    
      
    public static int linha = 1;
    public static int coluna = 1;
    
    public static void main(String[] args) {
        
            
        try {
            ExtrairPalavra palavras = new ExtrairPalavra("teste.txt", "ex1.txt");
            ArvorePatricia arvore = new ArvorePatricia(128);
            Hashing hash = new Hashing(8);
            String palavra = null;
            int i = 1;
            arvore.imprime();
            while ((palavra = palavras.proximaPalavra()) != null) {
                arvore.insere(hash.sToB(palavra));
            }
            arvore.pesquisa(hash.sToB("trabalho"),"trabalho");
            arvore.pesquisa(hash.sToB("computacao"),"computacao");
            arvore.pesquisa(hash.sToB("governo"),"governo");
            arvore.pesquisa(hash.sToB("educacao"),"educacao");
            arvore.pesquisa(hash.sToB("tecnologia"),"tecnologia");
            arvore.pesquisa(hash.sToB("formacao"),"formacao");
            arvore.pesquisa(hash.sToB("desenvolvimento"),"desenvolvimento");
            arvore.pesquisa(hash.sToB("que"),"que");
            arvore.pesquisa(hash.sToB("informatica"),"informatica");
            arvore.pesquisa(hash.sToB("crise"),"crise");
            arvore.pesquisa(hash.sToB("em"),"em");

            
            
            palavras.fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
        
        
    }
    
}
