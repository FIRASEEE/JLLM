/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import static com.coti.tools.Rutas.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class XMLRepository implements IRepository {
    File f= pathToFileInFolderOnDesktop("jLLM","input.xml").toFile();
    @Override
    public ArrayList<Conversation> importConversaciones() {
      ArrayList<Conversation> conversaciones;
        ObjectMapper xmlMapper = new XmlMapper();
    try (Scanner scannerRef = new Scanner(f)) { 
        StringBuilder xml = new StringBuilder();
   while (scannerRef.hasNext()) {
       xml.append(scannerRef.nextLine());
        }
   conversaciones= xmlMapper.readValue(xml.toString(),new TypeReference<ArrayList<Conversation>>() {});
    return conversaciones;
    }catch(IOException e){  
          e.printStackTrace();
          return null;
    }
    }

    @Override
    public boolean exportConversaciones(ArrayList<Conversation> conversaciones) {
           ObjectMapper xmlMapper = new XmlMapper();
         try{
     String xml = xmlMapper.writeValueAsString(conversaciones); 
    Files.write(f.toPath(), xml.getBytes(StandardCharsets.UTF_8));
         return true;
         }catch(IOException e){  
          e.printStackTrace();
         return false;
         }

    }
    
}
