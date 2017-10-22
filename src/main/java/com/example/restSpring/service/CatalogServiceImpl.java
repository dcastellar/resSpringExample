package com.example.restSpring.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.restSpring.model.Catalog;
import com.example.restSpring.model.ResultCatalog;
import com.example.restSpring.model.api.ApiCatalog;
import com.example.restSpring.model.convert.BeanConvert;

@Service("CatalogService")
@Transactional
public class CatalogServiceImpl extends RestServices implements CatalogService {

	public static final String URI_CATALOG = "http://192.168.1.207:8080/content/catalog";

	/**
	 * 
	 */
	public int findById(long id) {
		System.out.println("Testing getCatalog API----------");
		RestTemplate restTemplate = getRestTemplate();
		ResultCatalog catalog = restTemplate.getForObject(URI_CATALOG + "?id_catalog=" + id, ResultCatalog.class);
		// HttpHeaders headers = new HttpHeaders();
		// headers.set("Accept", "application/json");
		// HttpEntity<?> entity = new HttpEntity<>(headers);
		// HttpEntity<ResultCatalog> response = restTemplate.exchange(REST_SERVICE_URI + "?id=1", HttpMethod.GET, entity, ResultCatalog.class);
		System.out.println(catalog);
		if (catalog != null && catalog.getData() != null ) {
			return catalog.getData().getId();
		} else {
			return 0;
		}
	}

	@Override
	public void createCatalog(Catalog catalog) {
		System.out.println("Testing createCatalog API----------");
		RestTemplate restTemplate = getRestTemplate();
		ResultCatalog a = restTemplate.postForObject(URI_CATALOG, BeanConvert.convertCatalogToAPI(catalog), ResultCatalog.class);
		catalog.setIdCatalog(a.getData().getId());
		System.out.println(a);
	}

	@Override
	public void updateCatalog(Catalog catalog) {
		System.out.println("\nTesting update createCatalog API----------");
		RestTemplate restTemplate = getRestTemplate();
		ApiCatalog api = BeanConvert.convertCatalogToAPI(catalog);
		HttpEntity<ApiCatalog> request = new HttpEntity<ApiCatalog>(api);
		ResponseEntity<ResultCatalog> response = restTemplate.exchange(URI_CATALOG, HttpMethod.PUT, request, ResultCatalog.class);
		System.out.println(response.getBody());
	}

	@Override
	public Catalog findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


}
