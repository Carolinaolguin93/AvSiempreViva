package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Promotion extends Sugerencia{

	private Integer id;
	private String name;
	private String type;
	protected Attraction[] attractions;
	private boolean esPromocion = true;

	private Map<String, String> errors;

	public Promotion(int id, String name, String type, Attraction[] attractions) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.attractions = attractions;
	}
	/*
	private void validate() {
		errors = new HashMap<String, String>();

		if (coins <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (time <= 0) {
			errors.put("duration", "Debe ser positivo");
		}	
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
		
	}*/
	

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
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(attractions);
		result = prime * result + Objects.hash(esPromocion, id, name, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promotion other = (Promotion) obj;
		return Arrays.equals(attractions, other.attractions) && esPromocion == other.esPromocion
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(type, other.type);
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
