/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import static com.coti.tools.Rutas.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class RandomCSVLLM implements ILLM {
Path path =pathToFileInFolderOnDesktop("jLLM","RandomCSV.csv");

@Override
    public String getIdentifier(){
        return "RandomCSVLLM";
    }

    @Override
    public String speak(String entrada) {
           Random random=new Random();
        ArrayList<Frase> frases=new ArrayList();
        frases=importarCSV(); 
        int num;
        int max=frases.size();
        if(entrada.contains("hola")||entrada.contains("Buenas")){
            do{  
        
         num =random.nextInt( max);
            }while(!frases.get(num).getTipo().equals("saludo")); 
        return frases.get(num).getContenido();
    }
        else if(entrada.contains("?")){
            do{  
        
         num =random.nextInt( max);
            }while(!frases.get(num).getTipo().equals("afirmacion")); 
        return frases.get(num).getContenido();
    }

            num=random.nextInt(max);
            return frases.get(num).getContenido();
    }
    public ArrayList<Frase> importarCSV() {
        String delimiter=",";
          Frase f = null;
       ArrayList<Frase>  frases=new ArrayList();  
   try{     
  List<String> lineas= Files.readAllLines(path);
   for (String linea:lineas){
      f  =Frase.getfraseFromDelimitedString(linea, delimiter);
   if(f !=null){
    frases.add(f);
   }
   }
   return frases;
  }catch(IOException ex){
       System.err.println("se ha producido error a importar las frases");
       return null; }
  }
} 