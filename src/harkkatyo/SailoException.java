package harkkatyo;

/**
 * @author mineanupponen ja emililiarantonen
 * @version 1.3.2022
 *
 */
public class SailoException extends Exception{
 
        private static final long serialVersionUID = 1L;


        /**
         * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
         * käytettävä viesti
         * @param viesti Poikkeuksen viesti
         */
        public SailoException(String viesti) {
            super(viesti);
        }

}
