package sakila.inventory.model;

import sakila.business.model.Store;

public class inventory {
	private int inventoryId;
	private Film film;
	private Store store;
	private String lastUpdate;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "inventory [inventoryId=" + inventoryId + ", film=" + film + ", store=" + store + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
}
