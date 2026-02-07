import guilines.IJeuDeBilles;
import java.util.Random;

/**
 * Classe du jeu de billes
 */
public class MonJeu implements IJeuDeBilles{

    private static final int NB_LIGNES = 9;
    private static final int NB_COLONNES = 9;
    private static final int NB_COULEURS = 3;
    private static final int NB_BILLES_AJOUTES = 3;

    private int score;
    private int[][] grilleBilles;
    private Random random;
    private int[] nouvellesCouleurs;

    /**
     * Constructeur
     */
    public MonJeu(){
        grilleBilles = new int[NB_LIGNES][NB_COLONNES];
        random = new Random();
        score = 0;
        nouvellesCouleurs = new int[NB_BILLES_AJOUTES];

        /** Initialisation des cases (-1) */
        for (int i = 0; i < NB_LIGNES; i++){
            for (int j = 0; j < NB_COLONNES; j++){
                grilleBilles[i][j] = VIDE;
            }
        }


        /**
         * Renvoie le nombre de lignes.
         * @return le nombre de lignes
         */
        @Override
        public int getNbLignes(){
            return NB_LIGNES;
        }

        /**
         * Renvoie le nombre de colonnes.
         * @return le nombre de colonnes.
         */
        @Override
        public int getNbColonnes(){
            return NB_COLONNES;
        }

        /**
         * Renvoie le nombre de billes ajoutes a chaque tour.
         * @return le nombre de billes ajoutes a la grille apres chaque tour
         */
        @Override
        public int getNbBillesAjoutees(){
            return NB_BILLES_AJOUTES;
        }

        /**
         * Renvoie le score du jeu
         */
        @Override
        public int getScore() {
            return score;
        }

        /**
         * Renvoie le nombre de couleurs.
         */
        @Override
        public int getNbCouleurs(){
            return NB_COULEURS;
        }



    }

}