package com.example.restSpring.model.api;

public class ApiCatalog {

	private int id;
	private String name;
	private int id_user;
	private int id_catalog;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_catalog() {
		return id_catalog;
	}

	public void setId_catalog(int id_catalog) {
		this.id_catalog = id_catalog;
	}

}
