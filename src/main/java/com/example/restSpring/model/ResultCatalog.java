package com.example.restSpring.model;

import com.example.restSpring.model.api.ApiCatalog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultCatalog {
	
	private String success;
	private ApiCatalog data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public ApiCatalog getData() {
		return data;
	}

	public void setData(ApiCatalog data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Results [success=" + success + ", data=" + data + "]";
	}
}
