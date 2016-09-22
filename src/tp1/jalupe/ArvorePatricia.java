/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.jalupe;

import java.util.ArrayList;

/**
 *
 * @author decom
 */
public class ArvorePatricia {
    
    private static abstract class PatNo {
    }

    private static class PatNoInt extends PatNo {

        int index;
        PatNo esq, dir;
    }

    private static class PatNoExt extends PatNo {

        String chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
        ArrayList<Integer> linha,coluna;
        
        public PatNoExt(){
            linha = new ArrayList<Integer>();
            coluna = new ArrayList<Integer>();
        }
        
    }

    private PatNo raiz;
    private int nbitsChave; // Chave
    public int comparacoes;

    // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
    
    private int bit(int i, String k) {
        if(k.charAt(i-1) == '1') return 1;
        return 0;
    }
    
    
//    private int bit(int i, char k) {
//        if (i == 0) {
//            return 0;
//        }
//        int c = (int) k;
//        for (int j = 1; j <= this.nbitsChave - i; j++) {
//            c = c / 2;
//        }
//        return c % 2;
//    }

    // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    private PatNo criaNoExt(String k) {
        PatNoExt p = new PatNoExt();
        p.chave = k;
        return p;
    }
    
//    private PatNo criaNoExt(char k) {
//        PatNoExt p = new PatNoExt();
//        p.chave = k;
//
//        return p;
//    }

    private void pesquisa(String k, PatNo t, String nome) {
        if (this.eExterno(t)) {  // Se o no for externo fazer abaixo, se nao, ir para no interno
            PatNoExt aux = (PatNoExt) t;   // Pega um no externo auxiliar
            //System.out.println("Chave  : " + aux.chave + "\nString : " + k);
            if (aux.chave.equals(k)) { //compara o elemento com o o que dese, se for, retorna o elemento
              //System.out.println("Elemento encontrado");
                System.out.println("Palavra   : " + nome
                                  +"\nChave  : " + aux.chave
                                  +"\nLinha(s)  : " + aux.linha
                                  +"\nColuna(s) : " + aux.coluna);
                System.out.println();
            } else {
                System.out.println("Elemento nao encontrado");
            }
        } else { // se for interno,pega o bit (aux.index) do elemento k 
            PatNoInt aux = (PatNoInt) t; // pego o no como no interno
            if (this.bit(aux.index, k) == 0) { // pega o bit, se ele for 0, vai para a parte esquerda da arvore
                pesquisa(k, aux.esq, nome);
            } else {                            //se nao, vai para a parte direita
                pesquisa(k, aux.dir, nome);
            }
        }
    }

    private PatNo insereEntre(String k, PatNo t, int i) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
            PatNo p = this.criaNoExt(k);
            PatNoExt no = (PatNoExt) p;
            no.linha.add(ExtrairPalavra.countLinha);
            no.coluna.add(ExtrairPalavra.countColuna);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i);
            }
            
            return aux;
        }
    }

    private PatNo insere(String k, PatNo t) {
        if (t == null) {
            return this.criaNoExt(k);
        } else {
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            PatNoExt aux = (PatNoExt) p;
            int i = 1; // @{\it acha o primeiro bit diferente}@
            while ((i <= this.nbitsChave)
                    && (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) { //Se numero de i passar da variavel, o valor eh igual
                aux.linha.add(ExtrairPalavra.countLinha);
                aux.coluna.add(ExtrairPalavra.countColuna);
                return t;
            } else {
                
                return this.insereEntre(k, t, i);
            }
        }
    }

    private void central(PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
            if (!this.eExterno(filho)) {
                PatNoInt aux = (PatNoInt) filho;
                central(filho, aux.esq, "ESQ");
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Int: " + aux.index);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Int: " + aux.index);
                }
                central(filho, aux.dir, "DIR");
            } else {
                PatNoExt aux = (PatNoExt) filho;
                if (pai != null) {
                    System.out.println("Pai: " + ((PatNoInt) pai).index + " " + msg + " Ext: " + aux.chave);
                } else {
                    System.out.println("Pai: " + pai + " " + msg + " Ext: " + aux.chave);
                }
            }
        }
    }

    public void imprime() {
        this.central(null, this.raiz, "RAIZ");
    }

    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    public void pesquisa(String k, String nome) {
        this.pesquisa(k, this.raiz, nome);
    }

    public void insere(String k) {
        this.raiz = this.insere(k, this.raiz);
    }
    
    
    
}
