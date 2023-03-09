/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iphonetoredmi;

import com.sun.org.apache.xerces.internal.parsers.AbstractSAXParser;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import com.sun.org.apache.xerces.internal.xni.parser.XMLParserConfiguration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.xml.sax.XMLReader;

/**
 *
 * @author Yo
 */
public class IphoneToRedmi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Lista de Contactos (.vcf)", "vcf"));
            chooser.showOpenDialog(chooser);
            File fichero = chooser.getSelectedFile();
            parser p = new parser(fichero);
            p.CapturarContactos();
            p.EscribirFichero();
            JOptionPane.showMessageDialog(chooser, "Ha concluido el proceso de extracción de los contactos", "Información", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(chooser, "Gracias por usar Iphone2Redmi Contact Exporter\nAplicación creada por MSc. Allan Ayes (01/04/2021)\n\nAcepte para salir del programa", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);

        } catch (Exception ex) {
            Logger.getLogger(IphoneToRedmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
