import java.util.Arrays;

public class SprawdzWMacierzy {
    public static boolean czyAtrybutWystepuje(Integer[][] macierzNieodroznialnosci, Regula r) {
        boolean flaga = false;
        for (int j = 0; j < macierzNieodroznialnosci.length; j++) { // po kolumnach (reszta obiektow)
            if (macierzNieodroznialnosci[j].length != 0) {  // jesli komorka nie jest pusta
                if (Arrays.asList(macierzNieodroznialnosci[j]).containsAll(r.deskryptoryReguly.keySet())) {
                    flaga = true;
                }
            }
        }
        return flaga;
    }
}