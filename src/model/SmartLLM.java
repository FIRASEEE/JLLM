/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.*;
import io.github.amithkoujalgi.ollama4j.core.utils.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.InterruptedException;

/**
 *
 * @author usuario
 */
public class SmartLLM implements ILLM {
    @Override
    public String getIdentifier() {
     return "SmartLLM";
    }

    @Override
    public String speak(String entrada) {
     String response;
       String host = "http://localhost:11434/";
       OllamaAPI ollamaAPI = new OllamaAPI(host);
       
        try {  
            response= ollamaAPI.ask("mistral",entrada);
             return response;  
        } catch (OllamaBaseException ex) {
           ex.printStackTrace();
           return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (InterruptedException ex) {
         ex.getStackTrace();
            return null;
        }
       
        
    }
}