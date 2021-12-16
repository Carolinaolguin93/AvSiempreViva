package model;

public class PromocionAbsoluta extends Promotion {

	private static final double DESCUENTO = 3;

	public PromocionAbsoluta(int id, String nombre, String tipoAttr, String typePromo, Attraction[] atracciones) {
		super(id, nombre, tipoAttr, typePromo, atracciones);
	}

	@Override
	public Double getCost() {
		int i = 0;
		int precio = 0;
		while (i < attractions.length) {
			precio += attractions[i].getCost();
			i++;
		}
		return precio - DESCUENTO;
	}

}