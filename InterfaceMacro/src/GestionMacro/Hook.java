/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionMacro;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Antoni
 */
public class Hook implements NativeKeyListener {
    private int activationCode = 50;
    private int delay;
    private String macro;
    private Process process;
    private RunMacro runMacro = new RunMacro();

    public void setMacro(String macro) {
        this.macro = macro;
    }
   
    public int getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(int activationCode) {
        this.activationCode = activationCode;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    
    public Hook() {
        try {
            // 1. Enregistrer le hook auprès du système
            GlobalScreen.registerNativeHook();
            
            // 2. Ajouter l'écouteur à cette classe
            GlobalScreen.addNativeKeyListener(this);
            
            // 3. Optionnel : Désactiver les logs polluants dans la console
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(java.util.logging.Level.OFF);
            
        } catch (NativeHookException ex) {
            System.err.println("Erreur Hook : " + ex.getMessage());
        }
    }
    
    private boolean arreterMacro() {
        if (process != null && process.isAlive()) {
            process.destroy(); 
            return true;
        }
        return false;
    }
    
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyName = NativeKeyEvent.getKeyText(e.getKeyCode());
        
        if (keyName.equals(java.awt.event.KeyEvent.getKeyText(activationCode)) && macro != null) {
            try {
                if (!arreterMacro()) {
                    process = Runtime.getRuntime().exec(runMacro.getMacro(macro, delay));
                }
            } catch (IOException ex) {
                Logger.getLogger(Hook.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // Utile si vous voulez que la macro s'arrête quand on relâche la touche
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        // Inutile pour des macros (ne détecte que les caractères imprimables)
    }

    // Dans l'événement de fermeture de votre fenêtre (WindowClosing)
    public void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.out.println("erreur closing");
        }
    }
    
    
    
}
