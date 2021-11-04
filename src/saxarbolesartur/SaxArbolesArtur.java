/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxarbolesartur;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * Clase principal
 * @author alu2017363
 */
public class SaxArbolesArtur {

    /** Inicio de la aplicación
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Se crean el parseador y el manejador
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            Recorresax recorre = new Recorresax();
            try {
                //Se lanza el análisis del archivo
                //En esta ejecución se: 
                //- Detecta la estructura del XML
                //guardándose la etiquetas anidadas en memoria pero sin repeticiones.
                //- Se cuentan los árboles totales.
                //- Se guardan en una lista los árboles con todos los campos.
                //- Se genera una lista de los tipos de riego que hay.
                parser.parse(new File("datos.xml"), recorre);
                System.out.println("1");
                //Se muestran las etiquetas anidadas recursivamente.
                Metodos.mostraretiquetas(recorre.getetiquetas());
                System.out.println("2");
                //Se muestra el número de árboles total.
                Metodos.mostrarnumarboles(recorre);
                System.out.println("3");
                //Se muestran los árboles que tienen todos los campos.
                Metodos.mostrararboles(recorre.getArbresnofalta());
                //Se muestra el número de árboles que tienen todos los campos
                Metodos.numarbolestodoscampos(recorre);
                System.out.println("4");
                //Se ofrece al usuario la opción de seleccionar un tipo de riego para mostrar árboles
                Metodos.buscaporuncampo(recorre, parser);
                //Se muestran cuantos árboles hay de ese tipo de riego
                Metodos.mostrarnumeroresultado(recorre);
            } catch (IOException ex) {
                //Caso de error
                System.out.println("Ha ocurrido un error.");
                Logger.getLogger(SaxArbolesArtur.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException | SAXException ex) {
            System.out.println("Ha ocurrido un error.");
            Logger.getLogger(Recorresax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
