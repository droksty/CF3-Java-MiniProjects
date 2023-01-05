package gr.aueb.cf.projects;


/**
 * Το παρακάτω είναι μία αντιγραφή-παρουσίαση του γνωστού αλγόριθμου
 * του Kadane, όπως τον έχω καταλάβει.
 * Σε καμία περίπτωση δεν είναι original/δική μου λύση.
 */
public class Project2 {

    public static void main(String[] args) {
        //
        int[] ints = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int currentMax; // Το τοπικό μέγιστο άθροισμα από τη θέση 0 μέχρι μία θέση i του πίνακα
        int previousMax; // Το currentMax της θέσης i-1. Μόνο για την εκτύπωση της διαδικασίας.
        int arrayMax; // Το μέγιστο (συνεχόμενο) άθροισμα του πίνακα

        // Τα max έως τη θέση 0 είναι η τιμή της ίδιας της θέσης 0
        currentMax = ints[0];
        previousMax = currentMax;
        arrayMax = ints[0];

        //
        presentArray(ints);


        /* Η getMaxSum διασχίζει γραμμικά τον πίνακα ξεκινώντας από τo 2ο στοιχείο (δείκτης i=1).
         * Σε κάθε επανάληψη προσθέτει την τιμή του currentMax στην τιμή του array[i].
         * Αν το άθροισμα είναι μικρότερο από την τιμή του array[i], τότε currentMax είναι το ίδιο το array[i].
         * Αν το άθροισμα είναι μεγαλύτερο από την τιμή του array[i], τότε currentMax = το άθροισμα
         * Στη συνέχεια συγκρίνει το currentMax με το arrayMax και εκχωρεί στο arrayMax το μεγαλύτερο αριθμό */
        arrayMax = getMaxSum(ints, previousMax, currentMax, arrayMax);
        System.out.println("Το μέγιστο άθροισμα του πίνακα είναι: " + arrayMax);
    }


    //

    /**
     * Βρίσκει το μεγαλύτερο (συνεχόμενο) άθροισμα ενός πίνακα με μία for O(n)
     * @param ints  Ο πίνακας
     * @return  το μεγαλύτερο (συνεχόμενο) άθροισμα
     */
    public static int getMaxSum(int[] ints, int previousMax, int currentMax, int arrayMax) {
        for (int i = 1; i < ints.length; i++) {
            previousMax = currentMax;
            currentMax = Math.max(ints[i], currentMax + ints[i]);
            arrayMax = Math.max(currentMax, arrayMax);

            System.out.printf("currentMax έως τη θέση %d => array[%d] ή (array[%d] + currentMax έως τη θέση %d) => %d ή (%d + %d) => %d\n",
                    i, i, i, i-1, ints[i], ints[i], previousMax, currentMax);
        }

        System.out.println();
        return arrayMax;
    }


    public static void presentArray(int[] arr) {
        System.out.print("Αυτές είναι οι τιμές του δοκιμαστικού πίνακα:\n");
        for (int num : arr) {
            System.out.print(num + " | ");
        }
        System.out.print("\n\n");
    }
}
