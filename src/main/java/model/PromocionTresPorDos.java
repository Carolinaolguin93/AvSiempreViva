package model;

public class PromocionTresPorDos extends Promotion {

	public PromocionTresPorDos(int id, String nombre, String tipoAttr,String typePromo, Attraction[] atracciones) {
		super(id, nombre, tipoAttr, typePromo, atracciones);
	}

	@Override
	public Double getCost() {
		int i = 0;
		double precio = 0;
		while (i < attractions.length - 1) {
			precio += attractions[i].getCost();
			i++;
		}
		return precio; 

	}

}
