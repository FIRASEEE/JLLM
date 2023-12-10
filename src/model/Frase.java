/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author usuario
 */
public class Frase {
    
  private String tipo;
  private int longitud;
   private String contenido;

    public Frase(String tipo, int longitud, String contenido) {
        this.tipo = tipo;
        this.longitud = longitud;
        this.contenido = contenido;
    }
public static Frase getfraseFromDelimitedString(String linea ,String delimiter){
    
      String[] frase=linea.split(delimiter);
      if(frase.length!=3){
          return null;
      }
      String tipo=frase[0];
    int longitud=Integer.parseInt(frase[1]);
   String contenido=frase[2];
   return new Frase(tipo,longitud,contenido);
}
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
   
}
