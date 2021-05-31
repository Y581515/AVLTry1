
//  BinarTreNode.java               
//
//  Representerer en node i et binart tre.
//*******************************************************************

public class BinaerTreNode<T> {
    private T element;
    private Integer hoyde;
    private BinaerTreNode<T> venstre;
    private BinaerTreNode<T> hoyre;

    /*****************************************************************
     * Oppretter et nytt tre med spesifisert data.
     *****************************************************************/
    public BinaerTreNode(T el) {
        element = el;
        venstre = null;
        hoyre = null;
    }

    public BinaerTreNode(T element, BinaerTreNode<T> venstre, BinaerTreNode<T> hoyre) {
        this.element = element;
        this.venstre = venstre;
        this.hoyre = hoyre;
    }

    /******************************************************************/
    /*
     * Hent- og settmetoder /
     ******************************************************************/

    public BinaerTreNode<T> getVenstre() {
        return venstre;
    }

    public BinaerTreNode<T> getHoyre() {
        return hoyre;
    }

    public void setVenstre(BinaerTreNode<T> ny) {
        venstre = ny;
    }

    public void setHoyre(BinaerTreNode<T> ny) {
        hoyre = ny;
    }

    public T getElement() {
        return element;
    }
    

    public void setElement(T el) {
        element = el;
    }

	public int getHoyde() {
		return hoyde;
	}

	public void setHoyde(int hoyde) {
		this.hoyde = hoyde;
	}
    
    

}
