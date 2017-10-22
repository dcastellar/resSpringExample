package com.example.restSpring.service;

import com.example.restSpring.model.Catalog;

public interface CatalogService {

	int findById(long id);

	Catalog findByName(String name);

	void createCatalog(Catalog catalog);

	void updateCatalog(Catalog catalog);

}
