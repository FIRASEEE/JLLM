/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.ArrayList;
import view.ApplicationView;
import model.JLLMModel;
import model.Message;
import model.Conversation;

/**
 *
 * @author usuario
 */
public class JLLMController {
    JLLMModel model;
    ApplicationView view;

    public JLLMController(JLLMModel model, ApplicationView view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }
    public void initApp(){
         String initInfo;
         String endInfo;
        if(model.update()){
        initInfo=String.format("Cargar estado de applicacion con exito : %d "
                + "Conversaciones Cargadas",model.getConversaciones().size());    
        }
        else{
         initInfo="Bienvenida por primera vez";
        }
        view.showApplicationStart(initInfo);
        view.showMainMenu();
        if(model.saveAppState()){
          endInfo="Guardar estado de Applicacion con exito";  
        }
        else{
            endInfo="No se ha podido Guardar estado del applicacion";
        }
        view.showApplicationEnd(endInfo);
    }

    public void nuevaConversacion(Message mensaje,ArrayList<Message> mensajes){
       model.nuevaConversacion(mensaje,mensajes);
    }
    public void listarConversaciones() {
        model.listarConversaciones();
    }

    public boolean unaConversacion(int numero) {
       if(model.unaConversacion(numero)){
           return true;
       }
       else{
           return false;
       }
    }

    public boolean eliminarConversacion(int numero) {
     if(model.eliminarConversacion(numero)){
         return true;
     }
     else{
         return false;
     }
         
    }

    public boolean importar() {
  return model.importar();
    }

    public boolean exportar() {
       return model.exportar();
    }
  public boolean conversacionesExiste(){
      if(!model.getConversaciones().isEmpty()){
          return true;
      }
      else{
          return false;
      }
  }    
}
