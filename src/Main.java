import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /* NOTATKI

		Soon!
		
		
         */

        // 1.
        String[][] system = {
                {"1", "1", "1", "1", "3", "1", "1"},
                {"1", "1", "1", "1", "3", "2", "1"},
                {"1", "1", "1", "3", "2", "1", "0"},
                {"1", "1", "1", "3", "3", "2", "1"},
                {"1", "1", "2", "1", "2", "1", "0"},
                {"1", "1", "2", "1", "2", "2", "1"},
                {"1", "1", "2", "2", "3", "1", "0"},
                {"1", "1", "2", "2", "4", "1", "1"}
        };

        // 2.a)
        Integer[][][] macierzNieodroznialnosci = new Integer[system.length][system.length][];

        for (int i = 0; i < macierzNieodroznialnosci.length; i++) { // pierwszy wiersz macierzy nieodr.
            for (int j = 0; j < macierzNieodroznialnosci[i].length; j++) { // pierwsza kolumna macierzy nieodr. // one maja taka sama dlugosc (kolumna i wiersz)
                macierzNieodroznialnosci[i][j] = TworzKomorke.TworzKomorke(system[i], system[j]);
            }
        }

        // 2.b)
        Integer id = 0;
        Map<Integer, Regula> niesprzeczneReguly = new HashMap<>();
        List<Integer> listaNumerowAtrybutowZSystemu = new LinkedList<Integer>();
        for (Integer i = 0; i < system[0].length - 1; i++) { // bez ostatniej kolumny, bo to decyzja
            listaNumerowAtrybutowZSystemu.add(i);
        }


        // 2.c)
        Map<Integer, String[]> ponumerowaneObiektyBezRegul = new HashMap<>(); // ponumerowane, tzn. do kazdego obiektu przypisana jest liczba porzadkowa na podstawie jego kolejnosci w systemie
        for (Integer numerObiektu = 0; numerObiektu < system.length; numerObiektu++) {
            ponumerowaneObiektyBezRegul.put(numerObiektu, system[numerObiektu]);
        }
        Map<Integer, String[]> ponumerowaneObiektyZRegulami = new HashMap<>();


        for (int rzad = 1; rzad < system[0].length; rzad++) {
            // 2.d1)
            Map<Integer, Regula> tymczasoweNiesprzeczneReguly = new HashMap<>();

            if (!ponumerowaneObiektyBezRegul.isEmpty()) {
                // 2.d2)
                List<List<Integer>> listaKombinacjiAtrybutow = TworzenieKombinacjiAtrybutow.utworzKombinacjeAtrybutow(listaNumerowAtrybutowZSystemu, rzad);


                // 3.
                for (int obiektDecyzyjny = 0; obiektDecyzyjny < system.length; obiektDecyzyjny++) {

                    // 3.a)
                    if (!ponumerowaneObiektyZRegulami.containsKey(obiektDecyzyjny)) {

                        System.out.println("\nReguly rzedu " + rzad + " dla obiektu nr " + (obiektDecyzyjny + 1) + "\n");
                        // 3.b)
                        for (List<Integer> kombinacjaAtrybutow : listaKombinacjiAtrybutow) {
                            // 3.c1)
                            Regula r = TworzRegule.TworzRegule(system[obiektDecyzyjny], kombinacjaAtrybutow); // prototyp reguly, jest niesprawdzona

                            if (!SprawdzWMacierzy.czyAtrybutWystepuje(macierzNieodroznialnosci[obiektDecyzyjny], r)) {
                                System.out.println("Nie wystepuje w macierzy.");
                                if (!SprawdzCzyRegulaWRegule.czyWRegule(niesprzeczneReguly, r)) {
                                    if (!czyTakaRegulaJuzJest(tymczasoweNiesprzeczneReguly, r)) {
                                        tymczasoweNiesprzeczneReguly.put(id, r);
                                    }
                                    id += 1;
                                    // 3.c4)
                                    System.out.println("Regula nie wystapila. Jest OK!");
                                    List<Integer> support = Support.jakieObiektySupportuja(system, r); // MUSIMY PRZEKAZAC PONUMEROWANE OBIEKTY Z REGULAMI, ZEBY NIE NADPISAL REGULY!
                                }
                            }
                        }
                    }
                }
            }
            niesprzeczneReguly.putAll(tymczasoweNiesprzeczneReguly);
        }
        System.out.println("\n\n\nNiesprzeczne reguly: ");
        for (Map.Entry<Integer, Regula> reguly : niesprzeczneReguly.entrySet()) {
            Regula r = reguly.getValue();
            for (Map.Entry<Integer, String> entry : r.deskryptoryReguly.entrySet()) {   // sprawdzamy deskryptory reguly foreach
                Integer key = entry.getKey();                                           // klucz deskryptora reguly
                String value = entry.getValue();
                System.out.print("( a" + (key + 1) + " = " + value + " )");
            }
            System.out.print(" => ( d = " + r.decyzja + " )[" + r.getSupport() + "]\n");
        }

    }

    public static boolean czyTakaRegulaJuzJest(Map<Integer, Regula> niesprzeczneReguly, Regula r) {

        for (Map.Entry<Integer, Regula> entryNiesprzecznychRegul : niesprzeczneReguly.entrySet()) { // sprawdzamy deskryptory reguly foreach
            Regula niesprzecznaRegula = entryNiesprzecznychRegul.getValue();
            if (niesprzecznaRegula.deskryptoryReguly.entrySet().containsAll(r.deskryptoryReguly.entrySet())) // porownuje wszystko, klucze i value
                return true;
        }
        return false;
    }


}

