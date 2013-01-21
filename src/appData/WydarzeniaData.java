package appData;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import domain.Wydarzenie;

public class WydarzeniaData {
	private static List<Wydarzenie> allwydarzenia = new ArrayList<Wydarzenie>();
    public static List<Wydarzenie> getAllWydarzenia() {
        return allwydarzenia;
    }
}


