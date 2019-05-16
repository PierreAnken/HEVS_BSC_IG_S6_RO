package graph;
public class Noeud
    {
    private Info info ;
    private Noeud suivant ;

    // constructors
    public Noeud()
        {
        info = null ;
        suivant = null ;
        }

    public Noeud(Noeud n)
        {
        info = n.info ;
        suivant = n.suivant ;
        }



    public Noeud(Info info)
        {
        this.info = info ;
        suivant = null ;
        }

  // getters

    public Noeud getSuivant()
        {
        return suivant ;
        }

    public Info getInfo()
        {
        return info ;
        }

    // setters

    public void setSuivant(Noeud nouveau)
        {
        suivant = nouveau ;
        }

    //utile lors d'echanges de noeuds, donc d'infos
    public void setInfo(Info nouveau)
        {
        info = nouveau ;
        }
    }