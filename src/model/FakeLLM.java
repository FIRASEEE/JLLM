/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class FakeLLM implements ILLM{

             Date date =new Date();
       String fechaString=date.toString();
   String [] saludos={"hola,que tal?","Saludo","Buenos dias,que tal","Buenas como vas"};
   String [] salir= {"Adios!","Hasta luego","Chao ,ha sido un placer"};      
         String [] tal={"bien gracias por la pregunta","todo bien"};
    String [] personal={"Como modelo de lenguaje de inteligencia artificial, no tengo pensamientos ni opiniones propias"
           , "perdon,no creo o no pienso,soy un modelo de AI","disculpe,no puedar mi opinion"};
        String [] vacio={"tu mensaje está vacío","parece que no has escrito nada,estas bien"};
        String [] ayuda={"claro!, en que puedo ayudarte","dime porfavor,estoy aqui para ayudarte"};
 

   @Override
    public String getIdentifier() {
      return "FakeLLM";
    }

    @Override
    public String speak(String entrada) {
               Random random = new Random();
        if(entrada.contains("hola")||entrada.contains("buenas")){
            int num=random.nextInt(saludos.length);
            return saludos[num];     
        }
        else if (entrada.contains("tal")){
               int num=random.nextInt(tal.length);
            return tal[num];     
        }
        else if(entrada.contains("crees ")|| entrada.contains("piensas")){
             int num=random.nextInt(personal.length);
            return personal[num];           
        }
        else if(entrada.contains("fecha")||(entrada.contains("hora"))){
         return fechaString;            
        }
        else if(entrada.contains("ayuda")){
             int num=random.nextInt(ayuda.length);
            return ayuda[num];    
        }
        else if(entrada.isBlank()){
            int num=random.nextInt(vacio.length);
            return vacio[num];  
        }
        else if(entrada.contains("adios")){
            int num=random.nextInt(salir.length);
            return salir[num];  
        }
        else{
          return "no entiendo lo que has dicho,repite porfavor";  
        }
       
    }

}
