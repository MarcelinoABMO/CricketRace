/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketrace;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marce
 */
public class CricketRace {

    private static Cricket[] crickets;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Set();
    }
    
    public static void Set()
    {
        //pegando os inputs
            //pegando a quantidade de grilos
        System.out.print("Quantos grilos devem correr? ");
        Scanner scan = new Scanner(System.in);
        int count = scan.nextInt();
            //pegando a posição da linha de chegada
        System.out.print("Qual a distancia para a chegada? ");
        int length = scan.nextInt();
        
        Set(count, length);
    }
    
    public static void Set(int count, int length)
    {
        Cricket.finishLine = length;
        
        crickets = new Cricket[count];
        for(int i = 0; i < crickets.length; i++)
            crickets[i] = new Cricket("Grilo_" + (i + 1));
        
        //startando as threads
        for(Cricket c : crickets)
            c.start();
        
        //aguardando todas as threads terminarem
        for(Cricket c : crickets)
            try {
                c.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CricketRace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //imprimir o resultado final
        System.out.println("\nResultado Final:");
        for(int i = 0; i < crickets.length; i++)
            System.out.println(crickets[i].Name() + " percorreu um total de " +
                crickets[i].Pos() + "cm em " + crickets[i].Steps() + " pulos");
    }
    
}
