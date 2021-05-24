package fr.gsb.rv.technique;

import fr.gsb.rv.entites.Visiteur;

public class Session {

    private static Session session = null ;
    private Visiteur leVisiteur ;

    private Session(Visiteur leVisiteur){
        super() ;
        this.leVisiteur = leVisiteur ;
    }

    public static boolean ouvrir(Visiteur visiteur){
        if( visiteur != null ){
            Session.session = new Session( visiteur ) ;
            return true ;
        }
        else {
            return false ;
        }
    }

    public static Session getSession(){
        return Session.session ;
    }

    public static void fermer(){
        Session.session = null ;
    }

    public Visiteur getLeVisiteur(){
        return this.leVisiteur ;
    }
}