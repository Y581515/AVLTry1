
import java.util.Iterator;
//********************************************************************
// KjedetBinarSakeTre.java        
//
//********************************************************************

public class AVLKjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>, Iterable<T> {

    private int antall;

    /**
     * @return the rot
     */
    public BinaerTreNode<T> getRot() {
        return rot;
    }

    /**
     * @param rot the rot to set
     */
    public void setRot(BinaerTreNode<T> rot) {
        this.rot = rot;
    }

    private BinaerTreNode<T> rot;

    /******************************************************************
     * Oppretter et tomt binart saketre.
     ******************************************************************/
    public AVLKjedetBSTre() {
        antall = 0;
        rot = null;
    }

    /******************************************************************
     * Oppretter et binart saketre med en node..
     ******************************************************************/
    public AVLKjedetBSTre(T element) {
        rot = new BinaerTreNode<T>(element);
        antall = 1;
    }

    /*****************************************************************
     * Returnerer sann hvis dette binare trett er tomt og usann ellers.
     *****************************************************************/
    @Override
    public int antall() {
        return antall;
    }

    /*****************************************************************
     * Returnerer sann hvis dette binare treet er tom og usann ellers.
     *****************************************************************/
    @Override
    public boolean erTom() {
        return (antall == 0);
    }

    /**********************************************************************
     * Legger det spesifiserte elementet pa passende plass i BS-treet. Like
     * elementer blir lagt til hayre. Bruk av rekursiv hjelpemetode.
     ********************************************************************/
    @Override
    public void leggTil(T element) {
        rot = leggTilRek(rot, element);
        antall++;
    }

    private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {
        if (p == null) {
            p = new BinaerTreNode(element);
            p.setHoyde(1);
            return p;
        } else{ 
            if(element.compareTo(p.getElement()) <= 0) {
                BinaerTreNode v = leggTilRek(p.getVenstre(), element);
                p.setVenstre(v);
            }
            else{
                BinaerTreNode h = leggTilRek(p.getHoyre(), element);
                p.setHoyre(h);
            }
        }
        p.setHoyde(NodeHoyde(p));

        if (BFaktor(p) == 2 && BFaktor(p.getVenstre()) ==1) {
            return LLRotation(p);
        } else if (BFaktor(p) == 2 && BFaktor(p.getVenstre()) ==-1) {
            return LRRotation(p);
        } else if (BFaktor(p) == -2 && BFaktor(p.getHoyre()) == -1) {
            return RRRotation(p);
        } else if (BFaktor(p) == -2 && BFaktor(p.getHoyre()) == 1) {
            return RLRotation(p);
        }
        return p;
    }

    private int NodeHoyde(BinaerTreNode<T> p) {
        int hV, hH;
        if (p != null && p.getVenstre() != null) {
            hV = p.getVenstre().getHoyde();
        } else {
            hV = 0;
        }
        if (p != null && p.getHoyre() != null) {
            hH = p.getHoyre().getHoyde();
        } else {
            hH = 0;
        }

        if (hV > hH) {
            return hV + 1;
        } else {
            return hH + 1;
        }

    }

    private int BFaktor(BinaerTreNode<T> p) {
        int hV, hH;
        if (p != null && p.getVenstre() != null) {
            hV = p.getVenstre().getHoyde();
        } else {
            hV = 0;
        }
        if (p != null && p.getHoyre() != null) {
            hH = p.getHoyre().getHoyde();
        } else {
            hH = 0;
        }

        return hV - hH;

    }

    private BinaerTreNode<T> LLRotation(BinaerTreNode<T> p) {
        BinaerTreNode<T> pL = p.getVenstre();
        BinaerTreNode<T> pLR = pL.getHoyre();

        pL.setHoyre(p);
        p.setVenstre(pLR);

        p.setHoyde(NodeHoyde(p));
        pL.setHoyde(NodeHoyde(pL));

        return pL;
    }

    private BinaerTreNode<T> LRRotation(BinaerTreNode<T> p) {
        BinaerTreNode<T> pL = p.getVenstre();
        BinaerTreNode<T> pLR = pL.getHoyre();

        pL.setHoyre(pLR.getVenstre());
        p.setVenstre(pLR.getHoyre());
        pLR.setVenstre(pL);
        pLR.setHoyre(p);

        pL.setHoyde(NodeHoyde(pL));
        p.setHoyde(NodeHoyde(p));
        pLR.setHoyde(NodeHoyde(pLR));

        return pLR;
    }

    private BinaerTreNode<T> RRRotation(BinaerTreNode<T> p) {
        BinaerTreNode<T> pR = p.getHoyre();
        BinaerTreNode<T> pRL = pR.getVenstre();

        pR.setVenstre(p);
        p.setHoyre(pRL);

        p.setHoyde(NodeHoyde(p));
        pR.setHoyde(NodeHoyde(pR));

        return pR;
    }

    private BinaerTreNode<T> RLRotation(BinaerTreNode<T> p) {
        BinaerTreNode<T> pR = p.getHoyre();
        BinaerTreNode<T> pRL = pR.getVenstre();

        pR.setVenstre(pRL.getHoyre());
        p.setHoyre(pRL.getVenstre());
        pRL.setVenstre(p);
        pRL.setHoyre(pR);

        pR.setHoyde(NodeHoyde(pR));
        p.setHoyde(NodeHoyde(p));
        pRL.setHoyde(NodeHoyde(pRL));

        return pRL;
    }

    /******************************************************************
     * Legger det spesifiserte elementet pa passende plass i dette binare saketreet.
     * Like elementer blir lagt til hayre.
     ******************************************************************/

    public void leggTil2(T element) {
        BinaerTreNode<T> newNode = new BinaerTreNode(element);
        if (rot == null) {
            rot = newNode;
            antall++;

        } else {
            BinaerTreNode<T> focusNode = rot;
            BinaerTreNode<T> parent;

            while (true) {
                parent = focusNode;
                if (element.compareTo(focusNode.getElement()) <= 0) {
                    focusNode = focusNode.getVenstre();
                    if (focusNode == null) {
                        parent.setVenstre(newNode);
                        antall++;
                        return;

                    }

                } else {
                    focusNode = focusNode.getHoyre();
                    if (focusNode == null) {
                        parent.setHoyre(newNode);
                        antall++;
                        return;

                    }

                }

            }
        }

    }

    public void leggTilSeks(T e1, T e2, T e3, T e4, T e5, T e6,T e7) {
        leggTil(e1);
        leggTil(e2);
        leggTil(e3);
        leggTil(e4);
        leggTil(e5);
        leggTil(e6);
        leggTil(e7);

    }

    public void leggTilTre(T e1, T e2, T e3) {
        leggTil(e1);
        leggTil(e2);
        leggTil(e3);

    }
    public void inOrderTraverseTree(BinaerTreNode<T> focusNode) {
        if (focusNode != null) {
            inOrderTraverseTree(focusNode.getVenstre());

            System.out.print((focusNode.getElement()) + "   ");

            inOrderTraverseTree(focusNode.getHoyre());

        }
    }

    public void zInOrderView() {
        inOrderTraverseTree(rot);
    }

    public void preOrderTraverseTree(BinaerTreNode<T> focusNode) {
        if (focusNode != null) {
            System.out.print((focusNode.getElement()) + "   ");

            preOrderTraverseTree(focusNode.getVenstre());
            preOrderTraverseTree(focusNode.getHoyre());

        }
    }

    public void zPreOrderView() {
        preOrderTraverseTree(rot);
    }

    public void postOrderTraverseTree(BinaerTreNode<T> focusNode) {
        if (focusNode != null) {

            postOrderTraverseTree(focusNode.getVenstre());
            postOrderTraverseTree(focusNode.getHoyre());

            System.out.print((focusNode.getElement()) + "   ");

        }
    }

    public void zPostOrderView() {
        postOrderTraverseTree(rot);
    }

    public T[] sortertTab() {
        T[] tab = (T[]) new Comparable[antall];

        sortertTab(rot, tab, 0);
        return tab;
    }

    public int sortertTab(BinaerTreNode<T> p, T[] tab, int neste) {

        if (p == null) {
            return neste;
        } else {
            int nyNeste = sortertTab(p.getVenstre(), tab, neste);
            tab[nyNeste] = p.getElement();
            nyNeste = sortertTab(p.getHoyre(), tab, nyNeste + 1);
            return nyNeste;
        }
    }

    public BinaerTreNode<T> findNode(T element) {
        BinaerTreNode<T> focusNode = rot;
        while (focusNode.getElement().compareTo(element) != 0) {
            if (element.compareTo(element) <= 0) {
                focusNode = focusNode.getVenstre();

            } else {
                focusNode = focusNode.getHoyre();

            }
            if (focusNode == null) {
                return null;
            }
        }

        return focusNode;
    }

    public boolean exist(T element) {

        BinaerTreNode<T> focusNode = rot;
        while (focusNode.getElement().compareTo(element) != 0) {
            if (element.compareTo(focusNode.getElement()) <= 0) {
                focusNode = focusNode.getVenstre();

            } else {
                focusNode = focusNode.getHoyre();

            }
            if (focusNode == null) {
                return false;
            }
        }

        return true;
    }

    public boolean remove(T element) {
        BinaerTreNode<T> focusNode = rot;
        BinaerTreNode<T> parent = rot;
        boolean isItALeftChild = true;
        while (focusNode.getElement().compareTo(element) != 0) {
            parent = focusNode;
            if (element.compareTo(focusNode.getElement()) <= 0) {
                isItALeftChild = true;
                focusNode = focusNode.getVenstre();

            } else {
                isItALeftChild = false;
                focusNode = focusNode.getHoyre();

            }

            if (focusNode == null) {
                return false;
            }

        }
        if (focusNode.getVenstre() == null && focusNode.getVenstre() == null) {
            if (focusNode == rot) {
                rot = null;
            } else if (isItALeftChild) {
                parent.setVenstre(null);

            } else {
                parent.setHoyre(null);
            }
        }

        else if (focusNode.getHoyre() == null) {
            if (focusNode == rot) {
                rot = focusNode.getVenstre();
            } else if (isItALeftChild) {
                parent.setVenstre(focusNode.getVenstre());

            } else {
                parent.setHoyre(focusNode.getVenstre());
            }

        }

        else if (focusNode.getVenstre() == null) {
            if (focusNode == rot) {
                rot = focusNode.getHoyre();
            } else if (isItALeftChild) {
                parent.setVenstre(focusNode.getHoyre());

            } else {
                parent.setHoyre(focusNode.getHoyre());
            }

        }

        else {
            BinaerTreNode<T> replacement = getReplacementNodeVHAHogre(focusNode);

            if (focusNode == rot) {
                rot = replacement;

            } else if (isItALeftChild) {
                parent.setVenstre(replacement);

            } else {
                parent.setHoyre(replacement);
            }

            replacement.setVenstre(focusNode.getVenstre());

        }

        return true;
    }

    private BinaerTreNode<T> getReplacementNodeVHAHogre(BinaerTreNode<T> replacedNode) {

        BinaerTreNode<T> replacementParent = replacedNode;
        BinaerTreNode<T> replacement = replacedNode;

        BinaerTreNode<T> focusNode = replacedNode.getHoyre();

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.getVenstre();

        }

        if (replacement != replacedNode.getHoyre()) {
            replacementParent.setVenstre(replacement.getHoyre());
            replacement.setHoyre(replacedNode.getHoyre());

        }

        return replacement;
    }

    private BinaerTreNode<T> getReplacementNodeVHAVenstre(BinaerTreNode<T> replacedNode) {

        BinaerTreNode<T> replacementParent = replacedNode;
        BinaerTreNode<T> replacement = replacedNode;

        BinaerTreNode<T> focusNode = replacedNode.getVenstre();

        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.getHoyre();

        }

        if (replacement != replacedNode.getVenstre()) {
            replacementParent.setHoyre(replacement.getVenstre());
            replacement.setVenstre(replacedNode.getVenstre());

        }

        return replacement;
    }

    // public void printTree(int rows){
    // int space=0;
    // int iteration = 1;

    // while(iteration < rows){
    // int indent = (int)Math.abs(((Math.pow(-2, iteration))*(-16+(Math.pow(2,
    // iteration)))));
    // int indexToPrint =(int)(0.5*(-2+(Math.pow(2, iteration))));
    // int itemsPerRow = (int)(Math.pow(2, iteration-1));
    // int maxIndexToPrint = indexToPrint+itemsPerRow;

    // for(int j=0;j<indent;j++){
    // System.out.println(" ");
    // }

    // }
    // }

    /******************************************************************
     * Fjerner noden med minste verdi fra dette binære søketreet.
     *********************************************************************/
    @Override
    public T fjernMin() {
        T svar = null;
        BinaerTreNode<T> focusNode = rot;
        BinaerTreNode<T> parent = rot;

        while (focusNode.getVenstre() != null) {
            parent = focusNode;
            focusNode = focusNode.getVenstre();
        }
        if (focusNode == rot) {
            if (antall() > 0) {
                svar = rot.getElement();
            }
            rot = focusNode.getHoyre();
        } else {
            svar = focusNode.getElement();
            parent.setVenstre(focusNode.getHoyre());

        }

        return svar;
    }//

    /******************************************************************
     * Fjerner noden med største verdi fra dette binære søketreet.
     ******************************************************************/
    @Override
    public T fjernMaks() {
        T svar = null;
        BinaerTreNode<T> focusNode = rot;
        BinaerTreNode<T> parent = rot;

        while (focusNode.getHoyre() != null) {
            parent = focusNode;
            focusNode = focusNode.getHoyre();
        }
        if (focusNode == rot) {
            if (antall() > 0) {
                svar = rot.getElement();
            }
            rot = focusNode.getVenstre();
        } else {
            svar = focusNode.getElement();
            parent.setHoyre(focusNode.getVenstre());

        }

        return svar;
    }//

    /******************************************************************
     * Returnerer det minste elementet i dette binære søketreet.
     ******************************************************************/
    @Override
    public T finnMin() {
        BinaerTreNode<T> e = rot;
        while (e.getVenstre() != null) {
            e = e.getVenstre();
        }
        return e.getElement();
    }//

    /******************************************************************
     * Returnerer det største elementet i dette binære søketreet.
     ******************************************************************/
    @Override
    public T finnMaks() {
        BinaerTreNode<T> e = rot;
        while (e.getHoyre() != null) {
            e = e.getHoyre();
        }
        return e.getElement();
    }

    public int hoyde() {
        return hoydeRek(rot) - 1;
    }

    private int hoydeRek(BinaerTreNode<T> e) {
        int i = 0;
        int j = 0;
        if (e.getHoyre() != null) {
            i = hoydeRek(e.getHoyre());
        }
        if (e.getVenstre() != null) {
            j = hoydeRek(e.getVenstre());
        }
        if (i > j) {
            return i + 1;
        } else {
            return j + 1;
        }
    }

    public int hoyde2() {
        return hoydeRek2(rot);
    }

    private int hoydeRek2(BinaerTreNode<T> e) {
        if (e == null) {
            return -1;
        } else {

            int vHogde = hoydeRek2(e.getVenstre());
            int hHogde = hoydeRek2(e.getHoyre());
            return Math.max(vHogde, hHogde) + 1;
        }
    }

    /*******************************************************************************
     * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
     * BS-treet, null ellers. Bruk av rekursjon /
     ******************************************************************************/
    @Override
    public T finn(T element) {
        // Sak med rekursiv hjelpemetode

        // return finnRek(element, rot);
        return null;
    }

    // Den rekursive hjelpemetoden for saking

    // TODO

    /************************************************************************
     * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
     * BS-treet, null ellers. Uten bruk av rekursjon. /
     ************************************************************************/
    public T finn2(T element) {
        // TODO
        return null;
    }

    public void visInorden() {
        visInorden(rot);
        System.out.println();
    }

    private void visInorden(BinaerTreNode<T> p) {
        if (p != null) {
            visInorden(p.getVenstre());
            System.out.print(" " + p.getElement());
            visInorden(p.getHoyre());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InordenIterator<T>(rot);

    }

    public boolean remove2(T element) {
        BinaerTreNode<T> focusNode = rot;
        BinaerTreNode<T> parent = rot;
        boolean isItALeftChild = true;
        while (focusNode.getElement().compareTo(element) != 0) {
            parent = focusNode;
            if (element.compareTo(focusNode.getElement()) <= 0) {
                isItALeftChild = true;
                focusNode = focusNode.getVenstre();

            } else {
                isItALeftChild = false;
                focusNode = focusNode.getHoyre();

            }

            if (focusNode == null) {
                return false;
            }

        }
        if (focusNode.getVenstre() == null && focusNode.getVenstre() == null) {
            if (focusNode == rot) {
                rot = null;
            } else if (isItALeftChild) {
                parent.setVenstre(null);

            } else {
                parent.setHoyre(null);
            }
        }

        else if (focusNode.getHoyre() == null) {
            if (focusNode == rot) {
                rot = focusNode.getVenstre();
            } else if (isItALeftChild) {
                parent.setVenstre(focusNode.getVenstre());

            } else {
                parent.setHoyre(focusNode.getVenstre());
            }

        }

        else if (focusNode.getVenstre() == null) {
            if (focusNode == rot) {
                rot = focusNode.getHoyre();
            } else if (isItALeftChild) {
                parent.setVenstre(focusNode.getHoyre());

            } else {
                parent.setHoyre(focusNode.getHoyre());
            }

        }

        else {
            BinaerTreNode<T> replacement = getReplacementNodeVHAVenstre(focusNode);

            if (focusNode == rot) {
                rot = replacement;

            } else if (isItALeftChild) {
                parent.setVenstre(replacement);

            } else {
                parent.setHoyre(replacement);
            }

            replacement.setHoyre(focusNode.getHoyre());

        }

        return true;
    }

}// class
