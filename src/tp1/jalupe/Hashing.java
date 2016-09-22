/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.jalupe;

/**
 *
 * @author decom
 */
public class Hashing {
    
    
    private int M;
    
    public Hashing(int M){
        this.M = M;
    }
    
    public int hash(int chave){
        
        return chave % M;
    }
    
    public int hash(char ch){
        return ch % 26;
    }
    
    public int hash(String chave){
        int soma = 0;
        for(char i : chave.toCharArray()){
            soma += (int) i;
        }
        //System.out.println(chave + " : "+soma % M);
        return soma % M;
    }
    
   public String sToB(String s){
       byte[] bytes = s.getBytes();
       String binary = new String();
       int j = 0;
       for (byte b : bytes){
            int val = b;
            for (int i = 0; i < 8; i++){
               binary += ((val & 128) == 0 ? 0 : 1);
               val <<= 1;
            }    
       }
       while(binary.length() <= 128){
          binary += 0;
       }
       if(binary.length() > 129){
           return binary.substring(0, 127);
       }
       //System.out.println("Palavra : " +s+ "\nBinary : " +binary);
       return binary;
   }  
    
    
    
}
