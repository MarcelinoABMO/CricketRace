/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cricketrace;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que define os grilos da corrida, implementada em forma de thread.
 * @author marcelino
 */
public class Cricket extends Thread {
    
    //posição atual do grilo
    private int pos;
    public int Pos(){ return this.pos; }    
    
    //quantos pulos o grilo já deu
    private int steps;
    public int Steps(){ return this.steps; }
    
    //nome do grilo
    private String name;
    public String Name(){ return this .name; }
    
    public static int finishLine = 100;         //posição da linha de chegada
    private Random rand;                        //usado para incrementar randomicamente a posição do grilo
    private static int speed = 500;             //velocidade de atualização da thread em milisegundos
    private boolean isRunning = false;          //variavel de controle do loop da thread
    
    public Cricket(String name)
    {
        this.name = name;
        rand = new Random();
    }
    
    /**
     * Inicia a execução da thread
     */
    @Override
    public void start()
    {
        super.start();
        
        //inicializando as propriedade da classe
        this.isRunning = true;
        this.pos = 0;
        this.steps = 0;
    }
    
    @Override
    public void run()
    {
        //game loop
        while(this.isRunning)
        {
            Jump();
            
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cricket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Incrementa a posição do grilo randomicamente e exibe um log a cada iteração.
     */
    private void Jump()
    {
        this.steps++;
        int distance = rand.nextInt(10) + 1;
        this.pos += distance;
        
        String log;
        if (this.pos < finishLine)
            log = "O " + this.name + " pulou " + distance + "cm e ja percorreu " + this.pos + "cm";
        else{
            log = this.name + " alcancou a linha de chegada em " + this.steps + " pulos";
            this.isRunning = false;
        }
        
        System.out.println(log);
    }
}