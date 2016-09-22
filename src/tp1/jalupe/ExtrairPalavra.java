/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.jalupe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author decom
 */
public class ExtrairPalavra {
    
    
    private BufferedReader arqAlf, arqTxt;
    private StringTokenizer palavras;
    private String delimitadores;
    
    public static int countLinha = 0;
    public static int countColuna = 0;
    
    public ExtrairPalavra(String nomeArqAlf, String nomeArqTxt) throws Exception{
        this.arqAlf = new BufferedReader(new FileReader(nomeArqAlf));
        this.arqTxt = new BufferedReader(new FileReader(nomeArqTxt));
        this.delimitadores =  "\r \n.";
        this.palavras = null; 
    }
    
    public String proximaPalavra() throws Exception{
        if(palavras == null || !palavras.hasMoreTokens()){
            String linha = arqTxt.readLine();
            countColuna = 0;
            countLinha++;
            if(linha == null) return null;
            this.palavras = new StringTokenizer(linha, this.delimitadores);
            if(!palavras.hasMoreTokens()) return "";
        }
        countColuna++;
        return this.palavras.nextToken();
    }
    public void fecharArquivos() throws IOException{
        this.arqAlf.close(); this.arqTxt.close();
    }
    
    
    
}
