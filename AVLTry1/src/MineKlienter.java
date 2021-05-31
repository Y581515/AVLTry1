
public class MineKlienter{
    public static void main(String[] args) {
        AVLKjedetBSTre <Integer >bstre = new AVLKjedetBSTre<Integer >();

        bstre.leggTil2(100);
        bstre.leggTil2(50);
        bstre.leggTil2(150);
        bstre.leggTil2(25);
        bstre.leggTil2(75);
        bstre.leggTil2(125);
        bstre.leggTil2(175);
        bstre.leggTil2(12);
        bstre.leggTil2(30);
        bstre.leggTil2(60);
        bstre.leggTil2(80);
        bstre.leggTil2(120);
        bstre.leggTil2(130);
        bstre.leggTil2(160);
        bstre.leggTil2(180);
        System.out.println("\n\n");
        

        System.out.println("inOrderTraverseTree");
        bstre.zInOrderView();
        System.out.println("\n\npreOrderTraverseTree");
        bstre.zPreOrderView();
        System.out.println("\n\npostOrderTraverseTree");
        bstre.zPostOrderView();
        System.out.println("");
        System.out.println("Tre hogden er "+bstre.hoyde());
        System.out.println("Tre hogden er "+bstre.hoyde2());

        bstre.remove2(25);
        bstre.remove2(75);
        bstre.remove2(125);
        bstre.remove2(150);
        System.out.println("\n\nsletting 75,120,180,30 \ninOrderTraverseTree");
        bstre.zInOrderView();
        System.out.println("\n\n");
        Integer min = bstre.finnMin();
        Integer max = bstre.finnMaks();
        System.out.println("min = "+min+", max = "+ max);
        System.out.println("\n\nsletter min og max");
        bstre.fjernMin();
        bstre.fjernMaks();
        bstre.zInOrderView();
        

        // //Tester pa om et bestemt element fins
        // int element = 25;
        // System.out.println("\nTester paa om elementet " + element + " fins");

        // if (bstre.findNode(element) != null) {
        // System.out.println("Elementet " + element + " fins i bs-treet");
        // } else {
        // System.out.println("Elementet " + element + " fins ikke i bs-treet");
        // }

        // element = 75;
        // System.out.println("\nTester paa om elementet " + element + " fins");

        // if (bstre.findNode(element) != null) {
        // System.out.println("Elementet " + element + " fins i bs-treet");
        // } else {
        // System.out.println("Elementet " + element + " fins ikke i bs-treet");
        // }

    }    
}
