/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxarbolesartur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Manejador que recoge los eventos de la lectura del XML
 * @author alu2017363
 */
public class Recorresax extends DefaultHandler {
    //Contará el número de árboles en el XML
    private int numarbres;
    //Controla si me encuentro en un árbol o no
    boolean esticarbre = false;
    //Indica si en el árbol actual falta algún campo
    boolean faltaalgo = false;
    //Indica si estoy en un atributo de árbol
    boolean estoyenatributo = false;
    //Indica si un atributo se ha recogido o si estaba sin rellenar
    boolean atributorecogido = false;
    //Indica que operación se realizará
    //Cuando es FALSE se leen todos los datos para el funcionamiento de la parte inicial de la aplicación
    //Cuando es TRUE se buscan árboles con un tipo de riego concreto.
    boolean modo;
    //Posición del atributo actual en la lista y anidaciones.
    //Cada elemento del array es un nivel más. Ej: De la etiqueta 3 dame la hija 4 y de esa la hija 2.
    ArrayList<Integer> posiciones;
    //Etiqueta actual
    Etiqueta actual;
    //Nombre del atributo actual cuando se está leyendo uno
    String atributoactual;
    //Árbol que se está leyendo ahora
    Arbre arbreactual;
    //Etiqueta padre de la actual
    Etiqueta padre = null;
    //Tipo de riego por el que se buscará en la segunda ejecución
    String buscar = "";
    //Indica cuando se encuentra un árbol con el tipo de riego concreto
    boolean buscado = false; 
    //Lista de etiquetas - Contiene un árbol de etiquetas
    private ArrayList<Etiqueta> etiquetas;
    //Lista de árboles con todos los campos
    private ArrayList<Arbre> arbresnofalta;
    //Lista resultado de buscar un tipo de riego
    private ArrayList<Arbre> resultado ;
    //Lista de tipos de riego
    private ArrayList<String> tiposriego;

    /**
     * Devuelve el tamaño de la lista resultado de buscar por un tipo de riego
     * @return Tamaño de árboles encontrados
     */
    public int getnumresultado()
    {
        return resultado.size();
    }
    
    /**
     * Getter de modo
     * @return 
     */
    public boolean isModo() {
        return modo;
    }

    /**
     * Setter de modo
     * @param modo 
     */
    public void setModo(boolean modo) {
        this.modo = modo;
    }

    /**
     * Getter de buscar
     * @return 
     */
    public String getBuscar() {
        return buscar;
    }

    /**
     * Setter de buscar
     * @param buscar 
     */
    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
    
    /**
     * Getter del resultado de la búsqueda
     * @return Lista de árboles
     */
    public ArrayList<Arbre> getResultado() {
        return resultado;
    }

    /**
     * Getter de la lista de árboles con todos los campos
     * @return 
     */
    public ArrayList<Arbre> getArbresnofalta() {
        return arbresnofalta;
    }

    /**
     * Getter tipos de riego
     * @return 
     */
    public ArrayList<String> getTiposriego() {
        return tiposriego;
    }

    /**
     * Getter de las etiquetas anidadas sin repeticiones
     * @return 
     */
    public ArrayList<Etiqueta> getetiquetas() {
        return etiquetas;
    }

    /**
     * Devuelve el número de árboles
     * @return Número de árboles total
     */
    public int getnumarbres() {
        return numarbres;
    }

    /**
     * Determina si un string está vacio o contiene solo espacios
     * @param cadena String a analizar
     * @return Si está vacio
     */
    public boolean estavacio(String cadena) {
        boolean sal = false;
        boolean encontrado = false;
        int cont = 0;
        do {
            if (cont >= cadena.length()) {
                sal = true;
            } else {
                if (cadena.charAt(cont) != ' ') {
                    sal = true;
                    encontrado = true;
                } else {
                    cont++;
                }
            }
        } while (!sal);
        return !encontrado;
    }

    /**
     * Recoge el valor de los atributos
     * @param chars
     * @param i
     * @param i1
     * @throws SAXException 
     */
    @Override
    public void characters(char[] chars, int i, int i1) throws SAXException {
        //Si estoy en un árbol
        if (esticarbre) {
            //Y estoy en un atributo
            if (estoyenatributo) {
                //Recojo el valor
                String entrada = dametrozo(chars, i, i1);
                //Elimino carácteres no útiles
                entrada = entrada.trim();
                //Apunto que el atributo ha sido recogido
                atributorecogido = true;
                //Si está vacio el valor
                if (estavacio(entrada)) {
                    //Se recuerda que a este árbol le falta algo
                    faltaalgo = true;
                } else {
                    //Si no está vacio se recoge el atributo correspondiente
                    //En atributoactual se indica cual es el actual
                    if (atributoactual.equals("codi")) {
                        arbreactual.setCodi(entrada);
                    } else if (atributoactual.equals("posicioX_ETRS89")) {
                        arbreactual.setPosicioX(Double.parseDouble(entrada));
                    } else if (atributoactual.equals("posicioY_ETRS89")) {
                        arbreactual.setPosicioY(Double.parseDouble(entrada));
                    } else if (atributoactual.equals("latitud_WGS84")) {
                        arbreactual.setLatitud(Double.parseDouble(entrada));
                    } else if (atributoactual.equals("longitud_WGS84")) {
                        arbreactual.setLongitud(Double.parseDouble(entrada));
                    } else if (atributoactual.equals("tipusElement")) {
                        arbreactual.setTipuselement(entrada);
                    } else if (atributoactual.equals("espaiVerd")) {
                        arbreactual.setEspaiVerd(entrada);
                    } else if (atributoactual.equals("adreca")) {
                        arbreactual.setAdreca(entrada);
                    } else if (atributoactual.equals("alcada")) {
                        arbreactual.setAlcada(entrada);
                    } else if (atributoactual.equals("catEspecieId")) {
                        arbreactual.setCatEspecieID(Integer.parseInt(entrada));
                    } else if (atributoactual.equals("nomCientific")) {
                        arbreactual.setNomCientific(entrada);
                    } else if (atributoactual.equals("nomEsp")) {
                        arbreactual.setNomEsp(entrada);
                    } else if (atributoactual.equals("nomCat")) {
                        arbreactual.setNomcat(entrada);
                    } else if (atributoactual.equals("categoriaArbrat")) {
                        arbreactual.setCategoriaArbrat(entrada);
                    } else if (atributoactual.equals("ampladaVorera")) {
                        arbreactual.setAmpladaVorera(Double.parseDouble(entrada));
                    } else if (atributoactual.equals("plantacioDT")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        arbreactual.setPlantacioDT(LocalDate.parse(entrada, formatter));
                    } else if (atributoactual.equals("tipAigua")) {
                        arbreactual.setTipAigua(entrada);
                    } else if (atributoactual.equals("tipReg")) {
                        arbreactual.setTipReg(entrada);
                        if (modo) {
                            //En la segunda pasada si el atributo es tipo riego
                            //Se comprueba si este árbol es de los buscados
                            if (entrada.equals(buscar)) {
                                //Si este árbol es de los buscados se anota
                                buscado = true;
                            }
                        } else {
                            //En la primera pasada si el atributo es tipo riego se va construyendo la lista
                            //Se añade el atributo a la lista si no estaba
                            anadesinoexiste(entrada);
                        }
                    } else if (atributoactual.equals("tipSuperf")) {
                        arbreactual.setTipSuperf(entrada);
                    } else if (atributoactual.equals("tipSuport")) {
                        arbreactual.setTipSuport(entrada);
                    } else if (atributoactual.equals("cobertaEscocell")) {
                        arbreactual.setCobertaEscocell(entrada);
                    } else if (atributoactual.equals("midaEscocell")) {
                        arbreactual.setMidaEscocell(entrada);
                    } else if (atributoactual.equals("voraEscocell")) {
                        arbreactual.setVoraEscocell(entrada);
                    }
                }
            }
        }
    }

    /**
     * Añade a la lista de tiporiego un valor si no existía
     * @param cadena Valor a añadir
     */
    private void anadesinoexiste(String cadena) {
        boolean sal = false;
        boolean encontrado = false;
        int cont = 0;
        do {
            if (cont < tiposriego.size()) {
                if (cadena.equals(tiposriego.get(cont))) {
                    encontrado = true;
                    sal = true;
                } else {
                    cont++;
                }
            } else {
                sal = true;
            }
        } while (!sal);
        if (!encontrado) {
            //Si el valor no se encontraba se añade
            tiposriego.add(cadena);
        }
    }

    /**
     * Devuelve un trozo de un array de caracteres en un string
     * Necesario para leer los valores
     * @param letras Entrada de caracteres
     * @param i Posición inicial
     * @param il Longitud
     * @return Texto devuelto
     */
    private String dametrozo(char[] letras, int i, int il) {
        String resultado = "";
        for (int cont = i; cont < i + il; cont++) {
            resultado = resultado + letras[cont];
        }
        return resultado;
    }

    /**
     * Se ejecuta cuando se cierra una etiqueta del xml.
     * @param string
     * @param string1
     * @param string2 Nombre de la etiqueta que se cierra
     * @throws SAXException 
     */
    @Override
    public void endElement(String string, String string1, String string2) throws SAXException {
        if (!modo) {
            if (posiciones.size() > 0) {
                //En la primera pasada, si nos encontrábamos en un nivel superior a 0
                //Eliminamos la posición final de la lista ya que estamos volviendo al padre
                posiciones.remove(posiciones.size() - 1);
            }
        }
        //Si estoy en un árbol
        if (esticarbre) {
            if (string2.equals("arbre")) {
                //Caso en que se cierra una etiqueta arbre
                //Recuerdo que ya no estoy en un árbol
                esticarbre = false;
                if (modo) {
                    //En la segunda pasada si este árbol es de los que tiene el tiporiego que buscamos
                    //se añade a la lista resultado.
                    if (buscado) {
                        buscado = false;
                        resultado.add(arbreactual);
                    }
                } else {
                    //En la primera pasada, si no le falta algo al árbol que se ha terminado
                    //lo añadimos a la lista.
                    if (!faltaalgo) {
                        arbresnofalta.add(arbreactual);
                    }
                }
            } else {
                //Caso en el que se ha cerrado una etiqueta de atributo pero seguimos dentro de un arbre
                estoyenatributo = false;
                //Si no se ha recogido un valor del atributo se consta que falta algo en el árbol actual.
                if (!atributorecogido) {
                    faltaalgo = true;
                }
            }
        }
    }

    /**
     * Busca una etiqueta por el nombre en el nivel actual
     * @param cadena Nombre a buscar
     * @return Posición de la etiqueta encontrada o -1 si no se ha encontrado.
     */
    private int busca(String cadena) {
        boolean sal = false;
        boolean encontrado = false;
        int cont = 0;
        ArrayList<String> buscaen;
        if (posiciones.size() == 0) {
            //Si estamos en el nivel 0 buscamos en la lista de los nombres del nivel 0
            buscaen = Metodos.obtienenombres(etiquetas);
        } else {
            //Si estamos en un nivel superior buscamos en las hijas del padre de la actual.
            buscaen = padre.gethijasnombrestring();
        }
        do {
            //Buscamos el nombre buscado en la arraylist de búsqueda
            if (cont < buscaen.size()) {
                if (buscaen.get(cont).equals(cadena)) {
                    encontrado = true;
                    sal = true;
                } else {
                    cont++;
                }
            } else {
                sal = true;
            }
        } while (!sal);

        if (!encontrado) {
            //Si no se encuentra se devuelve -1
            cont = -1;
        }
        //Si se encuentra se devuelve la posición.
        return cont;
    }

    /**
     * Evento que se ejecuta al empezar una etiqueta del xml
     * @param string
     * @param string1
     * @param string2 Nombre de la etiqueta que se abre o empieza 
     * @param atrbts
     * @throws SAXException 
     */
    @Override
    public void startElement(String string, String string1, String string2, Attributes atrbts) throws SAXException {
        int posicion;
        if (!modo) {
            //En la primera pasada 
            if (posiciones.size() != 0) {
                //Si el nivel no es el 0.
                //Necesitamos obtener la etiqueta padre
                //La podemos obtener porque tenemos las posiciones anidadas en una lista
                //El bucle recorre la lista de posiciones
                for (int cont = 0; cont < posiciones.size(); cont++) {
                    if (cont == 0) {
                        //En el nivel 0 coge una etiqueta de la lista etiquetas.
                        padre = etiquetas.get(posiciones.get(cont));
                    } else {
                        //En los niveles superiores obtiene la hija concreta
                        //El bucle recorre el array
                        //obtiene la hija, de la hija, de la hija ...
                        padre = padre.gethija(posiciones.get(cont));
                    }
                }
            }
            //Una vez tenemos la etiqueta padre necesitamos saber
            //que posición tiene la etiqueta actual como hija de ese padre, si existe.
            posicion = busca(string2);
            if (posicion == -1) {
                //Si no se ha encontrado la etiqueta se añade
                //Se crea una nueva etiqueta con el nombre
                actual = new Etiqueta(string2);
                if (posiciones.size() == 0) {
                    //En el nivel 0 la nueva etiqueta se añade a la lista de etiquetas
                    etiquetas.add(actual);
                    //Se recuerda la posición de la nueva etiqueta en la lista
                    posiciones.add(etiquetas.size() - 1);
                } else {
                    //Si el nivel es superior a 0 la nueva etiqueta se añade al padre.
                    padre.addhija(actual);
                    //Se añade la posición de la etiqueta en el padre a la lista de posiciones
                    posiciones.add(padre.getnumhijas() - 1);
                }

            } else {
                //Si la etiqueta existía ya recordamos su posición en la lista de posiciones anidadas
                posiciones.add(posicion);
            }
        }
        //Si no estoy en un arbol
        if (!esticarbre) {
            if (string2.equals("arbre")) {
                //Caso en el que empieza un árbol
                if(!modo)
                {
                    //En la primera pasada cuento el número de árboles total
                    numarbres++;
                } 
                esticarbre = true;
                //Creo un nuevo árbol en memoria
                arbreactual = new Arbre();
                faltaalgo = false;
                atributoactual = "ara-no";   
            }
        } else {
            //Si estoy en un árbol lo que empieza es un atributo
            estoyenatributo = true;
            //recuerdo el atributo que es
            atributoactual = string2;
            atributorecogido = false;
        }
    }

    /**
     * Se ejecuta al finalizar el documento
     * @throws SAXException 
     */
    @Override
    public void endDocument() throws SAXException {
        
    }
    
    /**
     * Constructor
     */
    public Recorresax() {
        //Inicializo las variables
        etiquetas = new ArrayList<>();
        posiciones = new ArrayList<>();
        arbresnofalta = new ArrayList<>();
        tiposriego = new ArrayList<String>();   
        numarbres = 0;
        this.modo = false;
    }

    /**
     * Se ejecuta al empezar el documento
     */
    @Override
    public void startDocument() {
        if(modo)
        {
            //En la segunda pasada inicializo la ArrayList del resultado de buscar árboles por un tipo de riego
            resultado = new ArrayList<>();
        }
    }
}
