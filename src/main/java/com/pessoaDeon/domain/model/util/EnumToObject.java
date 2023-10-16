package com.pessoaDeon.domain.model.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumToObject {
	
	private String key;
	private String value;
	
	public EnumToObject(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

}
