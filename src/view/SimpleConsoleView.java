/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import model.Message;
import model.Conversation;
import java.util.Scanner;
import java.time.Instant;
import static com.coti.tools.Esdia.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author usuario
 */

public class SimpleConsoleView extends ApplicationView {
   @Override
   public void  showApplicationStart(String messageInicio){
       System.out.println(messageInicio);
   }
   @Override
    public void showApplicationEnd(String endInfo) {
       System.out.println(endInfo);
    }
   
    @Override
    public void showMainMenu() {
     int opcion;
        do{
        System.out.println(" 1- Nueva Conversacion"); 
        System.out.println(" 2- Menu CRUD");
        System.out.println(" 3- Importacion/Exportacion");
        System.out.println(" 4- Salir");
         opcion=readInt("introduzca una opcion : ");        
        switch(opcion){
            
            case 1:
              nuevaConversacion();
                break;
            case 2:
                menuCrud();
                break;
            case 3:
              submenuExpImp();
                break;
            case 4:
                System.out.println("saliendo....");
                break;
            default:
                System.out.println("opcion no valida");
                break;
        }
    } while(opcion!=4);           
        
    }
    
    private void menuCrud() {
        int opcion; 
        do{
        System.out.println(" 1- Listar Conversaciones"); 
        System.out.println(" 2- Eliminar Conversacion");
        System.out.println(" 3- Salir");
               opcion = readInt("introduzca una opcion : ");        
        switch(opcion){
            
            case 1:
              menuConversaciones();
                break;
            case 2:
                eliminarConversacion();
                break;
            case 3:
                System.out.println("salir del menu....");
                break;
            default:
                System.out.println("opcion no valida");
                break;
        }
    } while(opcion!=3);           
        
    } 

    private void submenuExpImp() {
          int opcion; 
        do{
        System.out.println(" 1- Exportar"); 
        System.out.println(" 2- Importar");
        System.out.println(" 3- Salir");
               opcion = readInt("introduzca una opcion : ");        
        switch(opcion){
            
            case 1:
              exportar();
                break;
            case 2:
                importar();
                break;
            case 3:
                System.out.println("salir del menu ....");
                break;
            default:
                System.out.println("opcion no valida");
                break;
        }
            } while(opcion!=3); 
           
    }

    private void menuConversaciones() {
     if(controller.conversacionesExiste()){
       controller.listarConversaciones();
       String pregunta ="quieres mostrar una conversacion completa y\n? : ";
       if(yesOrNo(pregunta)){
          int numero=readInt("introduzca el numero de la conversacion que quieras mirar : ");
          if(controller.unaConversacion(numero)){
              System.out.println("Conversacion mostrada con exito");
          }
          else{
              System.err.println("no se puede mostrar una conversacion con este numero");
            }
            }
            }
           else{
            System.out.println("No existe conversaciones para listar");
           }
    }

    private void eliminarConversacion() {
        if(controller.conversacionesExiste()){
         int numero=readInt("introduzca el numero de la conversacion que quieras eliminar : ");
        if(controller.eliminarConversacion(numero)){
           System.out.println("Conversacion eliminada con exito");
        }
       else{
           System.err.println("no se puede eliminar con este numero ");
        }
        }
        else{
            System.out.println("No existe Conversaciones para eliminar");
        }
    }
      
   private void nuevaConversacion() {
     String entrada="";
     ArrayList<Message> mensajes=new ArrayList();
      while (!entrada.equals("/salir")){
        Instant instant = Instant.now();
        long time = instant.getEpochSecond();
        String emisor = "Yo";
        entrada = readString("escribe : ");
        Message mensaje=new Message(emisor, time, entrada);   
        if(!entrada.equals("/salir")){
        mensajes.add(mensaje);
          }
        controller.nuevaConversacion(mensaje,mensajes);
         }
    }
    private void importar() {
    if(controller.importar()){
    System.out.println("Imporatcion con exito");    
     }
       else{
    System.err.println(" error de importacion");
    
}
    }
    
    private void exportar() {
        String pregunta=" Quieres exportar todas las conversaciones  ";
         if(!yesOrNo(pregunta)){
          int numero=readInt("introduzca el numero total  de las conversaciones que quieras exportar : ");
          Set<Integer> check =new HashSet<>();
             String numeros;
          do{  
           numeros=readString("introduzca los numeros de conversaciones que quieras exportar\n"
                    + "Por favor los numeros deben separados por espacio \n");
          } while(numeros.split(" ").length!=numero);
          String [] numbers=numeros.split(" ");
        
          for(int i=0;i<numero;i++){
           check.add(Integer.parseInt(numbers[i]));
          }
          if(controller.exportarAlgunos(check)){
        System.out.println("Exportacion con exito");    
          }
         else{
          System.err.println(" error de Exportacion");
            }
          }
         else{
         if(controller.exportarTodos()){
          System.out.println("Exportacion con exito");    
          }
         else{
       System.err.println(" error de Exportacion");
}
}
} 
}