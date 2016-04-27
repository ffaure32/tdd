package fr.maincare.tdd.tennis;

import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Set {

    private String joueur1;
    private String joueur2;
    private String serveur;

    private List<Jeu> jeux = Lists.newArrayList();
    
    public Set() {
        this("joueur1", "joueur2");
    }
    
    public Set(String joueur1, String joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }
    
    public String score() {
        return scoreJoueur(joueur1)+"-"+scoreJoueur(joueur2);
    }

    private int scoreJoueur(String nomJoueur) {
        int scoreJoueur = 0;
        for(Jeu jeu : jeux) {
            if(nomJoueur.equals(jeu.vainqueur())) {
                scoreJoueur++;
            }
        }
        return scoreJoueur;
    }
    
    public Jeu nouveauJeu() {
        if(jeuEnCours()) {
            throw new IllegalStateException("impossible de démarrer un nouveau jeu si un jeu est en cours");
        }
        Jeu jeu = initJeu();
        jeux.add(jeu);
        return jeu;
    }

    private Jeu initJeu() {
        String receveur = null;
        if(serveur == null || serveur.equals(joueur2)) {
            serveur = joueur1;
            receveur = joueur2;
        } else {
            serveur = joueur2;
            receveur = joueur1;
        }
        return new Jeu(serveur, receveur);
    }

    private boolean jeuEnCours() {
        Jeu jeuCourant = Iterables.getLast(jeux, null);
        return (jeuCourant != null && jeuCourant.vainqueur() == null);
    }

    public String vainqueur() {
        int scoreJoueur1 = scoreJoueur(joueur1);
        int scoreJoueur2 = scoreJoueur(joueur2);
        boolean ecartSuffisant = Math.abs(scoreJoueur1-scoreJoueur2) >= 2;
        if(ecartSuffisant) {
            if(scoreJoueur1>=6) {
                return joueur1;
            } else if(scoreJoueur2>=6) {
                return joueur2;
            }
        }
        return null;
    }

}
