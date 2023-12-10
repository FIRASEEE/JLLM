/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.JLLMController;
/**
 *
 * @author usuario
 */
public abstract class ApplicationView {
  protected JLLMController controller;

    public void setController(JLLMController controller) {
        this.controller = controller;
    }

 public abstract void showApplicationStart(String initInfo);
 public abstract void showMainMenu();
 public abstract void showApplicationEnd(String endInfo);  
    
}
