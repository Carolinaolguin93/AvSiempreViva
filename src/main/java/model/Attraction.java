package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Attraction extends Sugerencia{

	private Integer id;
	private String name;
	private Double cost;
	private Double duration;
	private Integer capacity;
	private boolean esPromocion = false;
	private String type;


	private Map<String, String> errors;
	
	public Attraction(Integer id, String name, Double cost, Double duration, Integer capacity, String type) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.duration = duration;
		this.capacity = capacity;
		this.type = type;

	}
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (cost <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duration <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (capacity <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}
	
	public boolean esPromocion() {
		return esPromocion;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public boolean canHost() {
		return capacity >= 1;
	}

	public void host() {
		this.capacity -= 1;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(capacity, cost, duration, errors, esPromocion, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attraction other = (Attraction) obj;
		return Objects.equals(capacity, other.capacity) && Objects.equals(cost, other.cost)
				&& Objects.equals(duration, other.duration) && Objects.equals(errors, other.errors)
				&& esPromocion == other.esPromocion && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", cost=" + cost + ", duration=" + duration + ", capacity="
				+ capacity + ", type=" + type + "]";
	}

	

	
	
}
