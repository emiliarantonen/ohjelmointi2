package harkkatyo;


/**
 * Rajapinta tietueelle johon voidaan taulukon avulla rakentaa 
 * "attribuutit".
 * @author vesal
 * @version Mar 23, 2012
 * @example
 */
public interface Tietue {

    
    /**
     * @return tietueen kenttien lukumäärä
     * @example
     * <pre name="test">
     *   #import rekisteri.Kilpailu;
     *   Kilpailu kil = new Kilpailu();
     *   kil.getKenttia() === 5;
     * </pre>
     */
    public abstract int getKenttia();


    /**
     * @return ensimmäinen käyttäjän syötettävän kentän indeksi
     * @example
     * <pre name="test">
     *   Kilpailu kil = new Kilpailu();
     *   kil.ekaKentta() === 2;
     * </pre>
     */
    public abstract int ekaKentta();


    /**
     * @param k minkä kentän kysymys halutaan
     * @return valitun kentän kysymysteksti
     * @example
     * <pre name="test">
     *   Kilpailu kil = new Kilpailu();
     *   kil.getKysymys(2) === "ala";
     * </pre>
     */
    public abstract String getKysymys(int k);


    /**
     * @param k Minkä kentän sisältö halutaan
     * @return valitun kentän sisältö
     * @example
     * <pre name="test">
     *   Kilpailu kil = new Kilpailu();
     *   kil.parse("   2   |  10  |   SM-kisat  | 2010 | 14-16 v | 16.45 | 7  ");
     *   kil.anna(0) === "2";   
     *   kil.anna(1) === "10";   
     *   kil.anna(2) === "SM-kisat";   
     *   kil.anna(3) === "2010";   
     *   kil.anna(4) === "14-16 v";   
     *   kil.anna(5) === "16.45";  
     *   kil.anna(6) === "7";
     * </pre>
     */
    public abstract String anna(int k);

    
    /**
     * Asetetaan valitun kentän sisältö.  Mikäli asettaminen onnistuu,
     * palautetaan null, muutoin virheteksti.
     * @param k minkä kentän sisältö asetetaan
     * @param s asetettava sisältö merkkijonona
     * @return null jos ok, muuten virheteksti
     * @example
     * <pre name="test">
     *   Harrastus har = new Harrastus();
     *   har.aseta(3,"kissa") === "aloitusvuosi: Ei kokonaisluku (kissa)";
     *   har.aseta(3,"1940")  === null;
     *   har.aseta(4,"kissa") === "h/vko: Ei kokonaisluku (kissa)";
     *   har.aseta(4,"20")    === null;
     * </pre>
     */
    public abstract String aseta(int k, String s);


    /**
     * Tehdään identtinen klooni tietueesta
     * @return kloonattu tietue
     * @throws CloneNotSupportedException jos kloonausta ei tueta
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kilpailu kil = new Kilpailu();
     *   kil.parse("   2   |  10  |   SM-kisat  | 2010 | 14-16 v | 16.45 | 7 ");
     *   Object kopio = kil.clone();
     *   kopio.toString() === kil.toString();
     *   kil.parse("   1   |  11  |   SM-kisat  | 2017 | yli 16v. | 17.20 | 5 ");
     *   kopio.toString().equals(kil.toString()) === false;
     *   kopio instanceof Kilpailu === true;
     * </pre>
     */
    public abstract Tietue clone() throws CloneNotSupportedException;


    /**
     * Palauttaa tietueen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return tietue tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kilpailu kilpailu = new Kilpailu();
     *   kilpailu.parse("   2   |  10  |   Kalastus  | 1949 | 22 t ");
     *   kilpailu.toString()    =R= "2\\|10\\|Kalastus\\|1949\\|22.*";
     * </pre>
     */
    @Override
    public abstract String toString();


    public abstract String getNimi();

}

