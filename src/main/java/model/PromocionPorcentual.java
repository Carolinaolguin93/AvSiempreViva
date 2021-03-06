package model;

public class PromocionPorcentual extends Promotion {

	private static final double DESCUENTO = 0.2;

	public PromocionPorcentual(int id, String nombre, String tipoAttr, String typePromo , Attraction[] atracciones) {
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
		double descuento = precio * DESCUENTO;
		double precioTotal = precio - descuento;
		return precioTotal;
	}

}