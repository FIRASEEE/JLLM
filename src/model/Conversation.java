/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;
import model.ILLM;
import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author usuario
 */
public class Conversation implements Serializable {
      ILLM llm;

   private long fecha_in;
   private long fecha_fin;
   private ArrayList<Message> mensajes;
      @JsonCreator
    public Conversation(@JsonProperty("fecha_in")long fecha_in,@JsonProperty("fecha_fin") long fecha_fin,
            @JsonProperty("mensajes")ArrayList<Message> mensajes) {
        this.fecha_in = fecha_in;
        this.fecha_fin = fecha_fin;
        this.mensajes = mensajes;

    }
    @JsonIgnore
    public String getPrimer20Caracter(){
    String msg=mensajes.get(0).getContenido();
    if(msg.length()<=20){
        return msg;
    }
   return msg.substring(0, 20)+"....";
    }
    public  void listar(int id){
        System.out.println(String.format("%d-  %s | %d | %s",id,this.fecha_in,this.mensajes.size()
                ,getPrimer20Caracter()));
      }  
        
public void mostrarConversacion(){
    Instant instant=Instant.ofEpochSecond(this.fecha_in);
    LocalDateTime date=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String fechaActual= date.format(formatter);
    System.out.println(String.format("Conversacion de %s", fechaActual));
     for (Message msg:this.mensajes){
     msg.formatoMensaje();
           }
}
  @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.fecha_in);
        return hash;
    }
 @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conversation other = (Conversation) obj;
        return Objects.equals(this.fecha_in, other.fecha_in);
    }
    

    public long getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(long fecha_in) {
        this.fecha_in = fecha_in;
    }

    public long getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(long fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public ArrayList<Message> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Message> mensajes) {
        this.mensajes = mensajes;
    }

}
