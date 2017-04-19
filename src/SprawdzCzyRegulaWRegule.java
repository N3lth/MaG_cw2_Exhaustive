import java.util.Map;

/**
 * Created by Nelth on 2017-04-08.
 */
public class SprawdzCzyRegulaWRegule {
    public static boolean czyWRegule(Map<Integer, Regula> niesprzeczneReguly, Regula sprawdzanaRegula) {
        for (Map.Entry<Integer, Regula> entryNiesprzecznychRegul : niesprzeczneReguly.entrySet()) { // sprawdzamy deskryptory reguly foreach
            Regula niesprzecznaRegula = entryNiesprzecznychRegul.getValue();
            for (Map.Entry<Integer, String> entry : niesprzecznaRegula.deskryptoryReguly.entrySet()) { // sprawdzamy deskryptory reguly foreach
                for (Map.Entry<Integer, String> entrySprawdzanejReguly : sprawdzanaRegula.deskryptoryReguly.entrySet()) { // sprawdzamy deskryptory reguly foreach
                    if(sprawdzanaRegula.deskryptoryReguly.entrySet().containsAll(niesprzecznaRegula.deskryptoryReguly.entrySet()))
                        return true;
                }
            }
        }
        return false;
    }
}
