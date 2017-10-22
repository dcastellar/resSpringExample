package com.example.restSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean Contenedor del catálogo
 * 
 * @author dcastellar
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Catalog {
	
	private int id;
	private int idCatalog;
	private int idClient;
	private String descCatalog;
	private String dateStart;

	public Catalog() {
	}

	public Catalog(int idCatalog, int idClient, String descCatalog, String dateStart) {
		super();
		this.idCatalog = idCatalog;
		this.idClient = idClient;
		this.descCatalog = descCatalog;
		this.dateStart = dateStart;
	}

	public int getIdCatalog() {
		return idCatalog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdCatalog(int idCatalog) {
		this.idCatalog = idCatalog;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getDescCatalog() {
		return descCatalog;
	}

	public void setDescCatalog(String descCatalog) {
		this.descCatalog = descCatalog;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	@Override
	public String toString() {
		return "Catalog [id=" + id + ", idCatalog=" + idCatalog + ", idClient=" + idClient + ", descCatalog=" + descCatalog + ", dateStart="
				+ dateStart + "]";
	}

}
