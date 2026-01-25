/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionMacro;

/**
 *
 * @author Antoni
 */
public class RunMacro {
    private String path;
    
    public RunMacro() {
        path = new java.io.File(".").getAbsolutePath();
        path = path.replace("InterfaceMacro\\.", "codeMacro\\bin\\");
    }
    
    public String getMacro(String macro, int delay){
        return switch (macro) {
            case "leftright" -> leftRight();
            case "autoclick" -> autoClick(delay);
            default -> test();
        };
    }
    
    private String test(){
        return path+"test.exe";
    }
    
    private String leftRight(){
        return path+"leftRight.exe";
    }
    
    private String autoClick(int delay){
        return path+"autoclick.exe"+" "+delay;
    }
    
}
