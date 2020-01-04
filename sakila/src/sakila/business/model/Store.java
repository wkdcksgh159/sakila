package sakila.business.model;

import sakila.address.model.Address;

public class Store {
	private int storeId;
	private int managerStaff;
	private Address address;
	private String lastUpdate;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getManagerStaff() {
		return managerStaff;
	}
	public void setManagerStaff(int managerStaff) {
		this.managerStaff = managerStaff;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", managerStaff=" + managerStaff + ", address=" + address + ", lastUpdate="
				+ lastUpdate + "]";
	}
	
}
