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
public class GeneradorCodigos {
    /**
     * Este constructor me permite generar un codigo a travez de la cantidad de caracteres a collocar y letras a colocar
     * @param palabra
     * @param caracteresTotales
     * @param letras
     * @return
     */
    public static String generarCodigo(String palabra, int caracteresTotales, boolean letras){
        int tipo=0;
        boolean verificar=letras;
        String codigo = palabra;
        do{
            //Armamos el código
            tipo = (int)(Math.random()*2)+1;
            if(tipo == 1){
                codigo += (int)(Math.random()*9);
            } else{
                if(verificar){
                    codigo = agregarLetra(codigo);    
                }
            }
        } while(codigo.length() < caracteresTotales);

        return codigo;
    }
    
    /**
     * Este metodo me permite crear una palabra aleatoria a travez de un random
     * @return
     */
    private static String agregarLetra(String palabra){
        int letra = (int)(Math.random()*25);
        String codigo = palabra;
        //Dependiendo del valor que nos salga en el aleatorio construiremsmos la palara
        switch(letra){
            case 0: 
            codigo+="a";
            break;
            case 1:
            codigo+="b";
            break;
            case 2:
            codigo+="c";
            break;
            case 3:
            codigo+="d";
            break;
            case 4:
            codigo+="e";
            break;
            case 5: 
            codigo+="f";
            break;
            case 6:
            codigo+="g";
            break;
            case 7:
            codigo+="h";
            break;
            case 8:
            codigo+="i";
            break;
            case 9:
            codigo+="j";
            break;
            case 10: 
            codigo+="k";
            break;
            case 11:
            codigo+="l";
            break;
            case 12:
            codigo+="m";
            break;
            case 13:
            codigo+="n";
            break;
            case 14:
            codigo+="o";
            break;
            case 15: 
            codigo+="p";
            break;
            case 16:
            codigo+="q";
            break;
            case 17:
            codigo+="r";
            break;
            case 18:
            codigo+="s";
            break;
            case 19:
            codigo+="t";
            break;
            case 20: 
            codigo+="u";
            break;
            case 21:
            codigo+="v";
            break;
            case 22:
            codigo+="w";
            break;
            case 23:
            codigo+="x";
            break;
            case 24:
            codigo+="y";
            break;
            case 25: 
            codigo+="z";
            break;
                    
        }
        return codigo;
    } 
}
