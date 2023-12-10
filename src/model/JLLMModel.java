/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static com.coti.tools.Rutas.*;
import controller.JLLMController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.time.Instant;

/**
 *
 * @author usuario
 */
public class JLLMModel {
    
    private ILLM llm;
    private  IRepository repository;
   private   ArrayList<Conversation> conversaciones;
       File ficheroEstadoSerializado;
    public JLLMModel(ILLM llm,IRepository repository) {
        this.repository = repository;
        this.llm=llm;
         conversaciones=new ArrayList();
        ficheroEstadoSerializado=pathToFileInFolderOnDesktop("jLLM","jLLM.bin").toFile();
    }
      public void nuevaConversacion(Message message, ArrayList<Message> mensajes){
          
          if(message.getContenido().contains("/salir")&&(mensajes.isEmpty())) {
                     System.out.println("...Conversacion vacia  ");
                   System.out.println("...Chat Closed  ");
          }
          else   if(message.getContenido().equals("/salir")&& !(mensajes.isEmpty())){
                int max=mensajes.size()-1;
           long fecha_in=mensajes.get(0).getFecha();
           long fecha_fin=mensajes.get(max).getFecha();
               Conversation conversacion=new Conversation(fecha_in,fecha_fin,mensajes);
           conversaciones.add(conversacion);
          System.out.println("...Chat Closed  ");
          }     
          else{
           message.formatoMensaje();
           String respuesta=llm.speak(message.getContenido());
           long epoch=(Instant.now().getEpochSecond());
           Message answer= new Message(llm.getIdentifier(),epoch,respuesta);
           mensajes.add(answer);
           answer.formatoMensaje();
      }     
      }
    public boolean importar() {
       ArrayList<Conversation> conversacionesImportadas;
       conversacionesImportadas = repository.importConversaciones();
       if(conversacionesImportadas!=null){
       
        for (Conversation conv :conversacionesImportadas){
           if(!conversaciones.contains(conv)){
             conversaciones.add(conv);
           } 
            
        }
      
   
        return true;
    }
    else{
        return false;
    }
    }

    public boolean exportar() {
     if(repository.exportConversaciones(conversaciones)){
            return true;
    }
     else{
         return false;
     }
    }
    public void listarConversaciones() {
    int id=0;
      for(Conversation conv:this.conversaciones){
       id++;
       conv.listar(id);
    }
     
    }
    
    
  
   
    public boolean update()  {
      if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()) {
           ObjectInputStream ois = null;
           try {
            ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
            this.conversaciones = (ArrayList<Conversation>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex){ 
                System.err.println("Error durante la deserialización: " + ex.getMessage());
                return false;
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        // Dejamos el error para la depuración, por el canal err.
                     System.err.println("Error durante la deserialización: " + ex.getMessage());
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }
   public boolean saveAppState(){
           ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado));
            oos.writeObject(conversaciones);
            return true;
        } catch (IOException ex) {
 
            System.err.println("Error durante la serialización: " + ex.getMessage());
            return false;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.err.println("Error al cerrar el flujo: " + ex.getMessage());
                    return false;
                }
            }
        }

    }
   public boolean eliminarConversacion(int num){
       if(num<1||num>conversaciones.size()){
           return false;
       }
       Conversation convAEliminar=conversaciones.get(num-1);
      if(conversaciones.contains(convAEliminar)){
         conversaciones.remove(convAEliminar);
         return true;
      }
      else{
          return false;
      }
   }
  
       public ILLM getLlm() {
        return llm;
    }

    public void setLlm(ILLM llm) {
        this.llm = llm;
    }

    public IRepository getRepository() {
        return repository;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Conversation> getConversaciones() {
        return conversaciones;
    }

    public void setConversaciones(ArrayList<Conversation> conversaciones) {
        this.conversaciones = conversaciones;
    }

    public boolean unaConversacion(int num) {
        if(num<1||num>conversaciones.size()){
           return false;
       }
     Conversation convAMostrar=conversaciones.get(num-1);
     if(conversaciones.contains(convAMostrar)){
     convAMostrar.mostrarConversacion();
     return true;
    }
     else{
         return false;
     }
}


}