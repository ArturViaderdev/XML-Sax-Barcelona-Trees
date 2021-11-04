/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxarbolesartur;

import java.util.ArrayList;

/**
 * Clase que sirve para guardar una etiqueta y sus etiquetas hijas
 * La aplicación guardará la estrucutra del XML sin repeticiones de etiquetas.
 * Se permiten N niveles
 * @author alu2017363
 */
public class Etiqueta {
    private String nombre;
    //Lista de etiquetas hijas de la etiqueta
    private ArrayList<Etiqueta> etiquetas;
    
    public String getnombre()
    {
        return nombre;
    }
    
    /**
     * Añade una etiqueta hija a la etiqueta
     * @param etiqueta 
     */
    public void addhija(Etiqueta etiqueta)
    {
        etiquetas.add(etiqueta);
    }
    
    /**
     * Devuelve una etiqueta hija
     * @param cont Número de etiqueta hija
     * @return La etiqueta hija
     */
    public Etiqueta gethija(int cont)
    {
        return etiquetas.get(cont);
    }
    
    /**
     * Devuelve todas las etiquetas hijas
     * @return Las etiquetas hijas
     */
   public ArrayList<Etiqueta> gethijas()
    {
        return etiquetas;
    }
   
   /**
    * Devuelve un array de strings con los nombres de las etiquetas hijas
    * @return Nombres de las etiquetas hijas
    */
   public ArrayList<String> gethijasnombrestring()
   {
        ArrayList<String> devuelve = new ArrayList<>();
        for(Etiqueta etiqueta:etiquetas)
        {
            devuelve.add((etiqueta.getnombre()));
        }
        return devuelve;
   }
    
   /**
    * Devuelve el número de hijas que tiene la etiqueta
    * @return Número de hijas
    */
    public int getnumhijas()
    {
        return etiquetas.size();
    }
    
    /**
     * Constructor
     * @param nombre El nombre de la nueva etiqueta
     */
    public Etiqueta(String nombre)
    {
        //Se inicializa la lista de etiquetas hijas
        etiquetas = new ArrayList<>();
        //Se asigna el nombre
        this.nombre = nombre;
    }
}