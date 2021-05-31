
import java.util.*;
public interface BSTreADT<T extends Comparable<T>> {
    // Burde hatt javadoc her
    // anbefaler at du fyller ut med javadoc her

    /*****************************************************************
     * Returnerer sann hvis dette binore trett er tomt og usann ellers.
     *****************************************************************/

    public int antall();

    /*****************************************************************
     * Returnerer sann hvis dette binore treet er tom og usann ellers.
     *****************************************************************/
    public boolean erTom();

    /******************************************************************
     * Legger det spesifiserte elementet po passende plass i dette binore soketreet.
     * Like elementer blir lagt til hoyre.
     ******************************************************************/
    public void leggTil(T element);

    /**********************************************************************
     * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
     * binore treet ellers returneres null./
     ************************************************************************/
    public T finn(T element);

    /*****************************************************************
     * Returnerer en referanse til minste elementet, null viss tre er tomt.
     *****************************************************************/
    public T finnMin();

    /*****************************************************************
     * Returnerer en referanse til storste elementet, null viss tre er tomt.
     *****************************************************************/
    public T finnMaks();

    /************************************************************************
     * Fjerner et element fra dette treet hvis det fins, ellers returneres null
     ************************************************************************/

    // public T fjern( T element);
    // Ikke impelmentert

    /************************************************************************
     * Fjerner minste elementet fra dette treet hvis det fins, ellers returneres null
     ************************************************************************/
    public T fjernMin();

    /************************************************************************
     * Fjerner storste elementet fra dette treet hvis det fins, ellers returneres null
     ************************************************************************/
    public T fjernMaks();
}
