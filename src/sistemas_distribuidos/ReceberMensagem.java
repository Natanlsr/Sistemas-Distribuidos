
package sistemas_distribuidos;

/**
 *
 * @author Natan Rodovalho
 */

import  java.util.Scanner;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReceberMensagem implements Runnable{
    private Socket cliente;
    private Servidor servidor;
    
    
    public ReceberMensagem(Socket cliente,Servidor servidor){
        this.cliente = cliente;
        this.servidor = servidor;
    }
    
    public void run(){
        Scanner s;
        try {
            s = new Scanner(this.cliente.getInputStream());
            PrintStream cliente_retorno = new PrintStream(this.cliente.getOutputStream());
            while(s.hasNextLine()){
                cliente_retorno.println(servidor.processaComando(s.nextLine()));
            }
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(ReceberMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
    }
}
