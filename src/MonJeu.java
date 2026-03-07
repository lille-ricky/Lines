import guilines.IJeuDesBilles;
import java.util.Random;

/**
 * Classe du jeu de billes
 */
public class MonJeu implements IJeuDesBilles{

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
    public MonJeu() {
        grilleBilles = new int[NB_LIGNES][NB_COLONNES];
        random = new Random();
        score = 0;
        nouvellesCouleurs = new int[NB_BILLES_AJOUTES];

        /** Initialisation des cases (-1) */
        for (int i = 0; i < NB_LIGNES; i++) {
            for (int j = 0; j < NB_COLONNES; j++) {
                grilleBilles[i][j] = VIDE;
            }
        }
    }


        /**
         * Renvoie le nombre de lignes.
         * @return le nombre de lignes
         */
        
        public int getNbLignes(){
            return NB_LIGNES;
        }

        /**
         * Renvoie le nombre de colonnes.
         * @return le nombre de colonnes.
         */
        
        public int getNbColonnes(){
            return NB_COLONNES;
        }

        /**
         * Renvoie le nombre de billes ajoutes a chaque tour.
         * @return le nombre de billes ajoutes a la grille apres chaque tour
         */
        
        public int getNbBillesAjoutees(){
            return NB_BILLES_AJOUTES;
        }

        /**
         * Renvoie le score du jeu
         */
        
        public int getScore() {
            return score;
        }

        /**
         * Renvoie le nombre de couleurs.
         */
        
        public int getNbCouleurs(){
            return NB_COULEURS;
        }

        /**
         * Renvoie les nouvelles couleurs qui vont etre ajoutes a la grille.
         * @return les nouvelles couleurs en queue pour etre rajoutes a la liste.
         */
        public int[] getNouvellesCouleurs(){
            return nouvellesCouleurs;
        }


        /**
         * Renvoie la couleur de la bille a la position [ligne][colonne]
         * @return la couleur de la bille (0 - NB_COULEURS - 1) ou VIDE si case vide
         */
        public int getCouleur(int ligne, int colonne){
            if (ligne < 0 || ligne >= NB_LIGNES || colonne < 0 || colonne >= NB_COLONNES ) {
                return VIDE;
            }
            return  grilleBilles[ligne][colonne];
        }

        /**
         * Verifie si otutes les cases de la grille sont remplies, renvoie true si oui et false sinon.
         * @return true si toutes les cases de la grilles sont remplies, false sinon.
         */
        
        public boolean partieFinie(){
            for (int i = 0; i < NB_LIGNES; i++){
                for (int j = 0; j < NB_COLONNES; j++){
                    if (grilleBilles[i][j] == VIDE) return false;
                }
            }

            return true;
        }

        /**
         * Reinitialise le score, et remet toutes les grilles a vide.
         *
         */
        public void reinit(){
            score = 0;

            for (int i = 0; i < NB_LIGNES; i++){
                for (int j = 0; j < NB_COLONNES; j++){
                    grilleBilles[i][j] = VIDE;
                }
            }
        }

        /**
         * Genere les couleurs des prochaines billes a rajouter
         */
        public void genererNouvellesCouleurs(){
            for (int i = 0; i < NB_BILLES_AJOUTES; i++){
                nouvellesCouleurs[i] = random.nextInt(NB_COULEURS);
            }
        }

        /**
         * Place une bille de couleur donne sur une case vide aleatoire
         * @param couleur la couleur de la bille a placer
         * @return le Point representant la position de la bille dans la grille, ou null sinon.
         */
        public java.awt.Point placerBilleAleatoire(int couleur){
            int nbCasesVides = 0;
            for (int i = 0; i < NB_LIGNES; i++){
                for (int j = 0; j < NB_COLONNES; j++){
                    if (grilleBilles[i][j] == VIDE){
                        nbCasesVides++;
                    }
                }
            }

                if (nbCasesVides == 0){
                    return null;
                }

                int caseChoisie = random.nextInt(nbCasesVides);
                int compteur = 0;

                for (int i = 0; i < NB_LIGNES; i++){
                    for (int j = 0; j < NB_COLONNES; j++){
                        if (grilleBilles[i][j] == VIDE){
                            if (compteur == caseChoisie){
                                grilleBilles[i][j] = couleur;
                                return new java.awt.Point(j, i);
                            }
                            compteur++;
                        }
                    }
                }
                return null;
            }

        /**
         * Place les nouvelles billes sur la grille selon les couleurs predefinies
         * @return la liste des positions ou les nouvelles billes ont ete places.
         */
        public java.util.List<java.awt.Point> placerNouvellesBilles(){
            java.util.List<java.awt.Point> positions = new java.util.ArrayList<>();

            for (int i = 0; i < NB_BILLES_AJOUTES; i++){
                java.awt.Point position = placerBilleAleatoire(nouvellesCouleurs[i]);
                if (position != null){
                    positions.add(position);
                }
            }

            genererNouvellesCouleurs();

            return positions;
        }


        /**
         * Deplace une bille aux cordonnees (ligD, colD) vers la case (ligA, colA)
         * @param ligD ligne de depart
         * @param colD colonne de depart
         * @param ligA ligned d'arrive
         * @param colA colonne d'arrive
         * @return la liste des positions ou les nouvelles billes ont ete ajoutees, null sinon
         */
        public java.util.List<java.awt.Point> deplace(int ligD, int colD,int ligA, int colA){
            if (grilleBilles[ligD][colD] == VIDE) return null;

            if (grilleBilles[ligA][colA] != VIDE) return null;

            grilleBilles[ligA][colA] = grilleBilles[ligD][colD];
            grilleBilles[ligD][colD] = VIDE;

            return placerNouvellesBilles();
        }


} 



