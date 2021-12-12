package model;

import java.util.ArrayList;

import utils.Crypt;

public class User {

	private Integer id;
	private String username, password, type;
	private Boolean admin;
	private Double coins;
	private Double time;
	private ArrayList<Sugerencia> itinerario = new ArrayList<Sugerencia>();
	private double costoTotalItinerario;
	private int tiempoTotalItinerario;

	public User(Integer id, String username, String password, Double coins, Double time, Boolean admin, String type) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.time = time;
		this.admin = admin;
		this.type = type;
	}

	public void agregarSugerenciaAlItinerario(Sugerencia sugerencia) {
		this.itinerario.add(sugerencia);
		this.costoTotalItinerario += sugerencia.getCost();
		this.tiempoTotalItinerario += sugerencia.getDuration();
		if (sugerencia.esPromocion()) {
			Promotion auxPromocion = (Promotion) sugerencia;
			for (Attraction atraccionDePromocion : auxPromocion.getAttractions()) {
				this.itinerario.add(atraccionDePromocion);
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Sugerencia> getItinerario() {
		return itinerario;
	}

	public synchronized void buyActivity(Sugerencia sugerencia) {
		this.coins -= sugerencia.getCost();
		this.time -= sugerencia.getDuration();
	}

	public boolean canAfford(Sugerencia sugerencia) {
		return sugerencia.getCost() <= this.coins;
	}

	public boolean canAttend(Sugerencia sugerencia) {
		return sugerencia.getDuration() <= this.time;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Boolean getAdmin() {
		return admin;
	}

	public Double getCoins() {
		return coins;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Double getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", coins=" + coins + ", time="
				+ time + ", type=" + type + "]";
	}

}
