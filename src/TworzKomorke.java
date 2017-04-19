import java.util.LinkedList;
import java.util.List;

public class TworzKomorke {

    public static Integer[] TworzKomorke(String[] obiektAktualniePrzerabiany, String[] obiektInny){
        List<Integer> komorka = new LinkedList<>();


        if(obiektAktualniePrzerabiany[obiektAktualniePrzerabiany.length-1].equals(obiektInny[obiektInny.length-1])) // sprawdzamy najpierw decyzje
            return new Integer[0];

        for(Integer i = 0; i<obiektAktualniePrzerabiany.length; i++){
            if(obiektAktualniePrzerabiany[i].equals(obiektInny[i]))
                komorka.add(i); // wrzucasz indeksy atrybutow
        }
        Integer [] komorkaTablica = komorka.toArray(new Integer[komorka.size()]);

        return komorkaTablica;
    }


    static int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }
}
