/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author usuario
 */
public class Message implements Serializable{
   private String emisor;
   private long fecha;
   private String contenido;
@JsonCreator 
    public Message(@JsonProperty("emisor")String emisor,@JsonProperty("fecha") long fecha
            ,@JsonProperty("contenido") String contenido) {
        this.emisor = emisor;
        this.fecha = fecha;
        this.contenido = contenido;
    }
   public void formatoMensaje(){
       Instant instant=Instant.ofEpochSecond(fecha);
       LocalDateTime date=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
       String fechaActual= date.format(formatter);
       System.out.println(String.format("[%s] %s : %s",fechaActual,this.emisor
               ,this.contenido));
   }
    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
      
}
