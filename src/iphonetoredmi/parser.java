/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iphonetoredmi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 *
 * @author Yo
 */
public class parser {

    StringBuilder sb = new StringBuilder();
    String data1 = "", data3 = "";
    File myContacts;

    public parser(File f) {
        this.myContacts = f;
    }

    public void EscribirFichero() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(myContacts.getName().concat(".vcf"));
            fw.write(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(parser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(parser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void CapturarContactos() throws Exception {
        DocumentBuilderFactory bdf = DocumentBuilderFactory.newInstance();
        bdf.setValidating(false);
        DocumentBuilder dbuild = bdf.newDocumentBuilder();
        org.w3c.dom.Document doc = dbuild.parse(myContacts);
        int index = 0;
        int nodes = (doc.getChildNodes().item(0).getChildNodes().getLength());
        while (index < nodes) {
            sb.append("BEGIN:VCARD\n"
                    + "VERSION:3.0\n");
            int atributos = doc.getChildNodes().item(0).getChildNodes().item(index).getChildNodes().getLength();
            int val = 0;
            while (val != atributos) {
                int atribs = doc.getChildNodes().item(0).getChildNodes().item(index).getChildNodes().item(val).getAttributes().getLength();
                int campo = 0;
                String clave = (doc.getChildNodes().item(0).getChildNodes().item(index).getChildNodes().item(val).getNodeName());
                //System.out.println("\n" + clave);
                while (campo != atribs) {
                    String cad = doc.getChildNodes().item(0).getChildNodes().item(index).getChildNodes().item(val).getAttributes().item(campo++).toString();
                    //  System.out.println(cad);
                    //System.out.println(parseXml(cad));
                    sb.append(parseXml(clave, cad));
                }
                val++;
            }
            sb.append("END:VCARD\n");
            index++;            
            //System.out.println(sb);
            //System.out.println("\n");
        }
    }

    public String parseXml(String clave, String valor) {
        String result = "";
        switch (clave) {
            case "name":
                if (valor.contains("data1")) {
                    result = "N:;" + valor.split("=")[1].replaceAll("\"", "") + ";;;\n";
                }
                if (valor.contains("given=")) {
                    result += "FN:" + valor.split("=")[1].replaceAll("\"", "") + "\n";
                }
                break;
            case "phone_v2":
                if (valor.contains("data1")) {
                    data1 = valor.split("=")[1].replaceAll("\"", "");
                }
                if (valor.contains("data3")) {
                    data3 = valor.split("=")[1].replaceAll("\"", "");
                    result = "TEL;TYPE=" + data3.toUpperCase() + ":" + data1 + "\n";
                }
                break;
            case "note":
                break;
            case "company":
                break;
            case "postal-address_v2":
                break;
            case "contact_event":
                break;
            case "email_v2":
                break;

        }
        return result;
    }
}
