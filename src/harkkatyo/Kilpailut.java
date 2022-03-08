package harkkatyo;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * @author emiliarantonen
 * @version 8.3.2022
 *
 */
public class Kilpailut {
    
    private final Collection<Kilpailu> alkiot = new ArrayList<Kilpailu> ();
    
    /**
     * @param kil lisättävä kilpailu
     */
    public void lisaa(Kilpailu kil) {
        alkiot.add(kil);
    }
    
    /**
     * @param tunnusNro ketä etsitään
     * @return lista kilpailuista
     */
    public List<Kilpailu> annaKilpailut(int tunnusNro) {
        List<Kilpailu> etsityt=new ArrayList<Kilpailu>();
        for (Kilpailu kil : alkiot)
            if (kil.getIdNro() == tunnusNro) etsityt.add(kil);
        return etsityt;
    }
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kilpailut kilpailut = new Kilpailut();
        Kilpailu SM1 = new Kilpailu();
        SM1.vastaaSMKisat(2);
        Kilpailu SM2 = new Kilpailu();
        SM2.vastaaSMKisat(1);
        Kilpailu SM3 = new Kilpailu();
        SM3.vastaaSMKisat(2);
        Kilpailu SM4 = new Kilpailu();
        SM4.vastaaSMKisat(2);

        kilpailut.lisaa(SM1);
        kilpailut.lisaa(SM2);
        kilpailut.lisaa(SM3);
        kilpailut.lisaa(SM4);

        System.out.println("============= Kilpailut testi =================");

        List<Kilpailu> kilpailut2 = kilpailut.annaKilpailut(2);

        for (Kilpailu kil : kilpailut2) {
            System.out.print(kil.getIdNro() + " ");
            kil.tulosta(System.out);
        }

    }

}
