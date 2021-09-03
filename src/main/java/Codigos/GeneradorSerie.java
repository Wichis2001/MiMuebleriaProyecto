/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigos;

/**
 *
 * @author luis
 */
public class GeneradorSerie {
    int dato;
    String numero;

    /**
     * A travez del valor númerico que posea nuestra consulta procedemos a agregar un numero de serie dependiendo del tamaño del número de serie
     * @param dato
     * @return
     */
    public String numeroSerie(int dato){
        this.dato=dato+1;
        if((this.dato>=10000000)&&(this.dato<=100000000)){
            numero=""+dato;
        }
        if((this.dato>=1000000)&&(this.dato<=10000000)){
            numero="0"+dato;
        }
        if((this.dato>=100000)&&(this.dato<=1000000)){
            numero="00"+dato;
        }
        if((this.dato>=10000)&&(this.dato<=100000)){
            numero="000"+dato;
        }
        if((this.dato>=1000)&&(this.dato<=10000)){
            numero="0000"+dato;
        }
        if((this.dato>=100)&&(this.dato<=1000)){
            numero="00000"+dato;
        }
        if((this.dato>=10)&&(this.dato<=100)){
            numero="000000"+dato;
        }
        if(this.dato<10){
            numero="0000000"+this.dato;
        }
        return numero;
    }
    
    /**
     * A travez del valor númerico que posea nuestra consulta procedemos a agregar un numero de serie dependiendo del tamaño del número de serie
     * @param dato
     * @return
     */
    public String numeroSerieFactura(int dato){
        this.dato=dato;
        if((this.dato>=10000000)&&(this.dato<=100000000)){
            numero=""+dato;
        }
        if((this.dato>=1000000)&&(this.dato<=10000000)){
            numero="0"+dato;
        }
        if((this.dato>=100000)&&(this.dato<=1000000)){
            numero="00"+dato;
        }
        if((this.dato>=10000)&&(this.dato<=100000)){
            numero="000"+dato;
        }
        if((this.dato>=1000)&&(this.dato<=10000)){
            numero="0000"+dato;
        }
        if((this.dato>=100)&&(this.dato<=1000)){
            numero="00000"+dato;
        }
        if((this.dato>=10)&&(this.dato<=100)){
            numero="000000"+dato;
        }
        if(this.dato<10){
            numero="0000000"+this.dato;
        }
        return numero;
    }
}
