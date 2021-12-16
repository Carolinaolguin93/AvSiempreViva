package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Promotion extends Sugerencia{

	private Integer id;
	private String name;
	private String tipoAttr;
	private String typePromo;
	protected Attraction[] attractions;
	private boolean esPromocion = true;
	
	private Map<String, String> errors;
	ArrayList<Integer> idAttractions = new ArrayList<Integer>();

	public Promotion(int id, String name, String tipoAttr,String typePromo, Attraction[] attractions) {
		this.id = id;
		this.name = name;
		this.tipoAttr = tipoAttr;
		this.attractions = attractions;
		this.setTypePromo(typePromo);
	}	
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if(tipoAttr == null) {
			errors.put("type", "No elegiste el tipo!");
		}if(tipoAttr.equals("Visita_Guiada") && attractions.length != 3) {
			errors.put("Paseo", "La promo debe tener 3 atracciones");
		}
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
	
	
	public ArrayList<Integer> getIdAttractions() {
		for(Attraction atr : attractions) {
			idAttractions.add(atr.getId());
		}
		return idAttractions;
	}

	public String getType() {
		return this.tipoAttr;
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
		result = prime * result + Objects.hash(esPromocion, id, name, tipoAttr);
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
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(tipoAttr, other.tipoAttr);
	}

	public abstract Double getCost();

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", name=" + name + ", type=" + tipoAttr + ", attraction="
				+ Arrays.toString(attractions) + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.tipoAttr = type;
	}

	public String getTypePromo() {
		return typePromo;
	}

	public void setTypePromo(String typePromo) {
		this.typePromo = typePromo;
	}



}
