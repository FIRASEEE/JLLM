/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.yesOrNo;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngine;
import io.github.jonelo.jAdapterForNativeTTS.engines.SpeechEngineNative;
import io.github.jonelo.jAdapterForNativeTTS.engines.Voice;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import io.github.jonelo.jAdapterForNativeTTS.engines.exceptions.SpeechEngineCreationException;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;

/**
 *
 * @author usuario
 */
public class VoiceConsoleView extends SimpleConsoleView {
      private SpeechEngine speechEngine;
      private Voice voice;
       public static VoicePreferences voicePreferences=new VoicePreferences();
        @Override
   public void  showApplicationStart(String messageInicio){
    super.showApplicationStart(messageInicio);
   }
   @Override
    public void showApplicationEnd(String endInfo) {
       super.showApplicationEnd(endInfo);
    }
   
    @Override
    public void showMainMenu() {
     int opcion;
        do{
        System.out.println(" 1- Nueva Conversacion"); 
        System.out.println(" 2- Menu CRUD");
        System.out.println(" 3- Importacion/Exportacion");
        System.out.println(" 4- Salir");
          String text= " elige una de estas opciones ";
          setVoice(text);
          String text1="1-nueva Conversacion ";
           sleep();
          setVoice(text1); 
          String text2= "2-Menu CRUD ";
           sleep();
          setVoice(text2); 
          String text3= "3- Importacion/Exportacion ";
           sleep();
          setVoice(text3);         
          String text4= "4- Salir";
           sleep();
           setVoice(text4);
          String text5="introduzca una opcion";
           sleep();
          setVoice(text5);         
         opcion=readInt(" opcion : ");        
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
             String salir= "saliendo";
               sleep();
             setVoice(salir);         
                break;

            default:
              String error="opcion no valida";
                sleep();
              setVoice(error);           
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
          String info= " elige una de estas opciones ";
          setVoice(info);
        String text=" 1- Listar Conversaciones"; 
             sleep();
          setVoice(text);         
       String text2=" 2- Eliminar Conversacion";
           sleep();
          setVoice(text2);          
       String text3= " 3- Salir";
          sleep();
          setVoice(text3);   
           String text4="introduzca una opcion";
             sleep();
          setVoice(text4);     
            opcion = readInt(" opcion : ");        
        switch(opcion){
            
            case 1:
              menuConversaciones();
                break;
            case 2:
                eliminarConversacion();
                break;
            case 3:
                String text5="salir del menu....";
               sleep();
          setVoice(text5);    
               sleep();
          break;
            default:
             String error="opcion no valida";
           sleep();
          setVoice(error);       
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
      String text=" 1- Exportar"; 
                sleep();
           setVoice(text);      
       String text2=" 2- Importar";
              sleep();
           setVoice(text2);
       String text3=" 3- Salir";
             sleep();
           setVoice(text3);
       String text4="introduzca una opcion";
           sleep();
          setVoice(text4);  
        opcion = readInt("  opcion : ");        
        switch(opcion){
            
            case 1:
              exportar();
                break;
            case 2:
                importar();
                break;
            case 3:
               String text5="salir del menu....";
              sleep();
                 setVoice(text5);
                sleep();
                break;
            default:
             String error="opcion no valida";
              sleep();
             setVoice(error);     
                break;
        }
            } while(opcion!=3); 
           
    }

    private void menuConversaciones() {
       if(controller.conversacionesExiste()){
       controller.listarConversaciones();
       String pregunta ="quieres mostrar una conversacion completa ? : ";
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
             System.out.println("No existe Conversaciones para Listar"); 
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

    public void setVoice(String text){
        
         try {
          this.speechEngine = SpeechEngineNative.getInstance();
          voicePreferences.setLanguage("es"); 
          voicePreferences.setCountry("ES"); 
          this.voice = speechEngine.findVoiceByPreferences(voicePreferences);

        if (voice == null) {
            System.out.printf("Voz no encontrada %s%n", voicePreferences);
        }
        speechEngine.setVoice(voice.getName());
        speechEngine.say(text);

    } catch (SpeechEngineCreationException | IOException e) {
        System.err.println(e.getMessage());
    }
    }
  
  public void sleep(){
     try {
             Thread.sleep(2000);
         } catch (InterruptedException ex) {
               ex.printStackTrace();    
         }
}
}