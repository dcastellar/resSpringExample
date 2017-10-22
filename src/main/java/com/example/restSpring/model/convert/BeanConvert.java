package com.example.restSpring.model.convert;

import com.example.restSpring.model.Catalog;
import com.example.restSpring.model.api.ApiCatalog;

public class BeanConvert {

	public static ApiCatalog convertCatalogToAPI(Catalog catalog) {
		ApiCatalog api = new ApiCatalog();
		api.setId(catalog.getId());
		api.setName(catalog.getDescCatalog());
		api.setId_user(catalog.getIdClient());
		api.setId_catalog(catalog.getIdCatalog());
		return api;
	}

}
