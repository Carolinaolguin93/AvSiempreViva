package model;

import java.util.Arrays;

public abstract class Promotion extends Sugerencia{

	private Integer id;
	private String name;
	private String type;
	protected Attraction[] attractions;
	private boolean esPromocion = true;

	public Promotion(int id, String name, String type, Attraction[] attractions) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.attractions = attractions;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Attraction[] getAttractions() {
		return attractions;
	}

	public String getType() {
		return this.type;
	}

	public boolean esPromocion() {
		return esPromocion;
	}

	public Double getDuration() {
		int i = 0;
		double duracion = 0;
		while (i < attractions.length) {
			duracion += attractions[i].getDuration();
			i++;
		}
		return duracion;
	}

	public void host() {
		int i = 0;
		while (i < attractions.length) {
			attractions[i].host();
			i++;
		}
	}

	public boolean canHost() {
		int i = 0;
		while (i < attractions.length) {
			if (attractions[i].canHost()) {
				i++;
			} else {
				return false;
			}
		}
		return true;
	}

	public abstract Double getCost();

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", name=" + name + ", type=" + type + ", attraction="
				+ Arrays.toString(attractions) + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}



}
