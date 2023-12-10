/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import model.Conversation;
import static com.coti.tools.Rutas.*;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 *
 * @author usuario
 */
public class JSONRepository implements IRepository {
    
File file=pathToFileInFolderOnDesktop("jLLM","input.json").toFile();
  ArrayList<Conversation> conversaciones;  
  
  @Override
    public ArrayList<Conversation> importConversaciones() {
        conversaciones=new ArrayList();
         Gson gson = new Gson();
  try (Scanner scannerRef = new Scanner(file)) {
       StringBuilder json = new StringBuilder();
      while (scannerRef.hasNext()) { 
      json.append(scannerRef.nextLine());
        }
     Type conversacionesType  = new TypeToken<ArrayList<Conversation>>(){}.getType();
    conversaciones=gson.fromJson(json.toString(),conversacionesType);
       return conversaciones;
   } catch (IOException e) { 
        e.printStackTrace();
         return null;
    }
    }
    
    @Override
    public boolean exportConversaciones(ArrayList<Conversation> conversaciones) {
        Gson gson = new Gson();
      String json = gson.toJson(conversaciones);
 
    try {
      Files.write(file.toPath(), json.getBytes(StandardCharsets.UTF_8));
        return true;
      }catch(IOException e){
       e.printStackTrace();
        return false;
        }
    } 
}
