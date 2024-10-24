/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jLLM;
import model.JLLMModel;
import model.ILLM;
import model.FakeLLM;
import model.RandomCSVLLM;
import model.IRepository;
import model.JSONRepository;
import view.ApplicationView;
import view.SimpleConsoleView;
import controller.JLLMController;
import model.XMLRepository;
import view.VoiceConsoleView;
import static com.coti.tools.Esdia.*;
import com.coti.tools.Rutas.*;
import static com.coti.tools.Rutas.pathToFileInFolderOnDesktop;
import io.github.jonelo.jAdapterForNativeTTS.engines.VoicePreferences;
import model.SmartLLM;
/**
 *
 * @author usuario
 */
public class JLLM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
             ApplicationView view;
             IRepository repository;
             JLLMModel model;
                ILLM llm;
        if(args.length==3){
           repository=optionRepository(args[0]);
           llm=optionLLM(args[1]);
         view=optionView(args[2]);   
        }
        else{
        view=new SimpleConsoleView();
        repository=new JSONRepository();
       llm=new FakeLLM();
        }
        model=new JLLMModel(llm,repository);
        JLLMController controller=new JLLMController(model,view);
        controller.initApp();
        
    }

    private static IRepository optionRepository(String arg) {
     switch(arg){
         case "xml":
       return new XMLRepository();
         case "json":
          return new JSONRepository();
        default:
     return new JSONRepository();
    }
    }

    private static ApplicationView optionView(String arg) {
          switch(arg){
         case "consola":
       return new SimpleConsoleView();
         case "voz":
             System.out.println("Elige el genero del hablante");
        String opcion=readString("esribe  hombre o mujer : ");

        switch(opcion){
            case "hombre":
              VoiceConsoleView.voicePreferences.setGender(VoicePreferences.Gender.MALE);
                 return new VoiceConsoleView();
            case "mujer":
              VoiceConsoleView.voicePreferences.setGender(VoicePreferences.Gender.FEMALE);
            return new VoiceConsoleView();
            default:
                VoiceConsoleView.voicePreferences.setGender(VoicePreferences.Gender.MALE);    
           return new VoiceConsoleView();
        }
        
         
         default:
     return new SimpleConsoleView();
    }
    }

    private static ILLM optionLLM(String arg) {
       switch(arg){
          case "fake":
          return new FakeLLM();
          case "csv":
              if(pathToFileInFolderOnDesktop("jLLM","RandomCSV.csv").toFile().exists()){
           return new RandomCSVLLM();
              }
              else{
                  System.err.println("Fichero CSV no existe");
              }
          case "smart":
              return new SmartLLM();
        default:
        return new FakeLLM();
             }
    }
    
}
