/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxarbolesartur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * Clase con métodos de la aplicación que son estáticos
 * @author alu2017363
 */
public class Metodos {
    /**
     * Muestra cuantos árboles se han encontrado con un tipo de riego concreto
     * Para ello solo recoge los datos que se encuentran en el manejador
     * @param recorre Recibe el manejador como parámetro
     */
    public static void mostrarnumeroresultado(Recorresax recorre)
    {
        System.out.println("There are " + recorre.getnumresultado() + " trees with tipreg " + recorre.getBuscar());
    }
    
    /**
     * Muestra una ArrayList de árboles
     * @param arboles Los árboles
     */
    public static void mostrararboles(ArrayList<Arbre> arboles)
    {
        for(Arbre arbol:arboles)
        {
            System.out.println(arbol.toString());
        }
    }
    
    /**
     * Muestra la lista de los tipos de riego. Lee un arraylist de strings.
     * @param tipos ArrayList de String que debe contener la lista de tiporiego
     */
    public static void muestralista(ArrayList<String> tipos)
    {
        //Mensaje informativo para el usuario. Después se le pedirá selección en otro método.
        System.out.println("Elige un tipo de riego:");
        for(int cont=0;cont<tipos.size();cont++)
        {
            //Se muestran todos los tipos de riego
            System.out.println((cont+1) + "-" + tipos.get(cont));
        }
    }
    
    /**
     * Permite al usuario introducir un número desde 1 hasta un máximo
     * @param max Máximo que podrá introducir el usuario
     * @return Número seleccionado por el usuario
     */
    public static int introduceopcion(int max)
    {
        Scanner entrada = new Scanner(System.in);
        int opcion = -1;
        do
        {
            System.out.println("Introduce una opción.");
            try
            {
                opcion = entrada.nextInt();
                if(opcion<1 || opcion > max)
                {
                    System.out.println("Opción incorrecta.");
                }
            }catch(InputMismatchException ex)
            {
                System.out.println("Error de entrada."); 
                entrada.next();
                //Se limpia la entrada en caso de haber error
            }
            //Se seguirá pidiendo un número al usuario hasta que introduzca uno correcto.
        }while(opcion<1 || opcion>max);
        return opcion-1;
    }
    
    /**
     * Muestra el número de árboles. Solamente lee del manejador
     * @param recorre Recibe el manejador como parámetro
     */
    public static void mostrarnumarboles(Recorresax recorre)
    {
        System.out.println("There are " + recorre.getnumarbres()+ " trees!");
    }
    
    /**
     * Muestra el número de árboles con todos los datos. Solo lo obitene del manejador.
     * @param recorre El manejador
     */
    public static void numarbolestodoscampos(Recorresax recorre)
    {
        System.out.println(recorre.getArbresnofalta().size()+ " trees with all data.");
    }
    
    /**
     * Permite al usuario buscar árboles con un tipo de riego seleccionado
     * @param recorre Manejador
     * @param parser Parseador
     */
    public static void buscaporuncampo(Recorresax recorre, SAXParser parser)
    {
        try {
            int opcion;
            //Se muestra la lista de tipos de riego
            muestralista(recorre.getTiposriego());
            //El usuario selecciona un tipo de riego
            opcion = introduceopcion(recorre.getTiposriego().size());
            //Se muestra el tipo de riego elegido
            System.out.println("Opcion " + recorre.getTiposriego().get(opcion));
            //Se cambia la variable modo en el manejador para hacerle dar otra pasada en la que buscará
            //y no repetirá el trabajo de la primera pasada.
            recorre.setModo(true);
            //Se introduce en el manejador el tipo de riego por el que se buscará
            recorre.setBuscar(recorre.getTiposriego().get(opcion));    
            //Se vuelve a leer el XML
            parser.parse(new File("datos.xml"), recorre);  
            //Se muestran los árboles que tienen el tipo de riego seleccionado
            Metodos.mostrararboles(recorre.getResultado());  
        } catch (SAXException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra todas las etiquetas en memoria
     * @param etiquetas Lista de etiquetas nivel 0
     */
    public static void mostraretiquetas(ArrayList<Etiqueta> etiquetas)
    {
        int nivel;
        nivel=0;
        //En el nivel 0 cada etiqueta de la lista se muestra
        for(Etiqueta etiqueta : etiquetas)
        {
            muestraetiqueta(etiqueta,nivel);  
        }
    }
    
    /**
     * Muestra una etiqueta y sus hijas. Método recursivo.
     * @param etiqueta La etiqueta
     * @param nivel El nivel en el que se encuentra. Sirve para generar las tabulaciones.
     */
    private static void muestraetiqueta(Etiqueta etiqueta,int nivel)
    {
        String tabulacion ="";
        //Dependiendo del nivel añadimos tabulaciones
        for(int cont=0;cont<nivel;cont++)
        {
            tabulacion = tabulacion + "\t";
        }
        //Se muestra el nombre de la etiqueta
        System.out.println(tabulacion + etiqueta.getnombre());
        //Se recorren las etiquetas hijas
        for(Etiqueta hija : etiqueta.gethijas())
        {
            //Se muestra cada etiqueta hija
            muestraetiqueta(hija,nivel+1);
        }
    }
    
    /**
     * Obtiene los nombres del primer nivel de una lista de etiquetas
     * @param etiquetas Lista de etiquetas
     * @return Lista de nombres
     */
    public static ArrayList<String> obtienenombres(ArrayList<Etiqueta> etiquetas)
    {
        //Creamos una lista
        ArrayList<String> devuelve = new ArrayList<>();
        //Para cada etiqueta
        for(Etiqueta etiqueta:etiquetas)
        {
            //Añado el nombre a la lista
            devuelve.add(etiqueta.getnombre());
        }
        //Se devuelve la lista de nombres
        return devuelve;
    }
}