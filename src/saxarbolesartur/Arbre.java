/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxarbolesartur;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que guarda los datos de un árbol
 * @author arturv
 */
public class Arbre {
    private String codi;
    private double posicioX;
    private double posicioY;
    private double latitud;
    private double longitud;
    private String tipuselement;
    private String espaiVerd;
    private String adreca;
    private String alcada;
    private int catEspecieID;
    private String nomCientific;
    private String nomEsp;
    private String nomcat;
    private String categoriaArbrat;
    private double ampladaVorera;
    private LocalDate plantacioDT;
    private String tipAigua;
    private String tipReg;
    private String tipSuperf;
    private String tipSuport;
    private String cobertaEscocell;
    private String midaEscocell;
    private String voraEscocell;

    /**
     * Convierte el árbol en un string que muestra todos los campos
     * @return 
     */
    @Override
    public String toString() {
        return codi +" - " + posicioX +" - " + posicioY +" - " + latitud +" - " + longitud +" - " + tipuselement +" - " + espaiVerd +" - " + adreca +" - " + alcada +" - " + catEspecieID +" - " + nomCientific +" - " + nomEsp +" - " + nomcat +" - " + categoriaArbrat +" - " + ampladaVorera +" - " + plantacioDT +" - " + tipAigua +" - " + tipReg +" - " + tipSuperf +" - " + tipSuport +" - " + cobertaEscocell +" - " + midaEscocell +" - " + voraEscocell + '}';
    }

    public LocalDate getPlantacioDT() {
        return plantacioDT;
    }

    public void setPlantacioDT(LocalDate plantacioDT) {
        this.plantacioDT = plantacioDT;
    }

    public String getMidaEscocell() {
        return midaEscocell;
    }

    public void setMidaEscocell(String midaEscocell) {
        this.midaEscocell = midaEscocell;
    }
    
    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }
    
    public double getPosicioX() {
        return posicioX;
    }

    public void setPosicioX(double posicioX) {
        this.posicioX = posicioX;
    }

    public double getPosicioY() {
        return posicioY;
    }

    public void setPosicioY(double posicioY) {
        this.posicioY = posicioY;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTipuselement() {
        return tipuselement;
    }

    public void setTipuselement(String tipuselement) {
        this.tipuselement = tipuselement;
    }

    public String getEspaiVerd() {
        return espaiVerd;
    }

    public void setEspaiVerd(String espaiVerd) {
        this.espaiVerd = espaiVerd;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getAlcada() {
        return alcada;
    }

    public void setAlcada(String alcada) {
        this.alcada = alcada;
    }

    public int getCatEspecieID() {
        return catEspecieID;
    }

    public void setCatEspecieID(int catEspecieID) {
        this.catEspecieID = catEspecieID;
    }

    public String getNomCientific() {
        return nomCientific;
    }

    public void setNomCientific(String nomCientific) {
        this.nomCientific = nomCientific;
    }

    public String getNomEsp() {
        return nomEsp;
    }

    public void setNomEsp(String nomEsp) {
        this.nomEsp = nomEsp;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public String getCategoriaArbrat() {
        return categoriaArbrat;
    }

    public void setCategoriaArbrat(String categoriaArbrat) {
        this.categoriaArbrat = categoriaArbrat;
    }

    public double getAmpladaVorera() {
        return ampladaVorera;
    }

    public void setAmpladaVorera(double ampladaVorera) {
        this.ampladaVorera = ampladaVorera;
    }

    public String getTipAigua() {
        return tipAigua;
    }

    public void setTipAigua(String tipAigua) {
        this.tipAigua = tipAigua;
    }

    public String getTipReg() {
        return tipReg;
    }

    public void setTipReg(String tipReg) {
        this.tipReg = tipReg;
    }

    public String getTipSuperf() {
        return tipSuperf;
    }

    public void setTipSuperf(String tipSuperf) {
        this.tipSuperf = tipSuperf;
    }

    public String getTipSuport() {
        return tipSuport;
    }

    public void setTipSuport(String tipSuport) {
        this.tipSuport = tipSuport;
    }

    public String getCobertaEscocell() {
        return cobertaEscocell;
    }

    public void setCobertaEscocell(String cobertaEscocell) {
        this.cobertaEscocell = cobertaEscocell;
    }

    public String getVoraEscocell() {
        return voraEscocell;
    }

    public void setVoraEscocell(String voraEscocell) {
        this.voraEscocell = voraEscocell;
    }

    /**
     * Constructor
     */
    public Arbre()
    {
        
    }
}
