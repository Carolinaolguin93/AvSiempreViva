package model;

public abstract class Sugerencia {

	public abstract Integer getId();

	public abstract Double getDuration();

	//public abstract String getTipoDeAtraccion(); PENDIENTE

	public abstract boolean esPromocion();

	public abstract Double getCost();

	public abstract String getName();

	public abstract boolean canHost();

	public abstract void host();

}
