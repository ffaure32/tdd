package fr.maincare.tdd.tennis;

import java.util.List;

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
        int scoreJoueur1 = 0;
        int scoreJoueur2 = 0;
        for(Jeu jeu : jeux) {
            if(joueur1.equals(jeu.vainqueur())) {
                scoreJoueur1++;
            } else if(joueur2.equals(jeu.vainqueur())) {
                scoreJoueur2++;
            }
        }
        return scoreJoueur1+"-"+scoreJoueur2;
    }

    public Jeu nouveauJeu() {
        Jeu jeu = null;
        if(serveur == null || serveur.equals(joueur2)) {
            serveur = joueur1;
            jeu = new Jeu(joueur1, joueur2);
        } else {
            serveur = joueur2;
            jeu = new Jeu(joueur2, joueur1);
        }
        jeux.add(jeu);
        return jeu;
    }

}
