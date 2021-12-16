package model;

import java.util.Comparator;

public class OrdenarParaSugerir implements Comparator<Sugerencia> {

	String atraccionFavorita;

	public OrdenarParaSugerir(String atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {

		if (o1.getType().equals(atraccionFavorita) && (!o2.getType().equals(atraccionFavorita))) {
			return -1;
		} else if ((!o1.getType().equals(atraccionFavorita)) && o2.getType().equals(atraccionFavorita)) {
			return 1;
		}
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}
		if (o1.getCost() > o2.getCost()) {
			return -1;
		} else if (o1.getCost() < o2.getCost()) {
			return 1;
		}
		if (o1.getDuration() > o2.getDuration()) {
			return -1;
		} else if (o1.getDuration() < o2.getDuration()) {
			return 1;
		}
		return 0;
	}
}