package com.pessoaDeon.domain.service.impressao;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pessoaDeon.domain.exception.EnviaBoSigmaException;
import com.pessoaDeon.domain.exception.ImpressaoErrorException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoImpressaoService {
	
	@Value("${url.api-impressao}")
	private String URL;

	public ByteArrayInputStream getPdfImpressao(String idBoSigma, HttpServletRequest request) {
		var tokenJWT = recuperarToken(request);
		if (tokenJWT != null) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.set("Accept", "application/octet-stream");
				headers.set("Authorization", tokenJWT);
				RestTemplate template = new RestTemplate();
				HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
				ResponseEntity<byte[]> response = template.exchange( URL + idBoSigma, HttpMethod.GET, requestEntity, byte[].class);
				if (response.getStatusCode().is2xxSuccessful()) {
				    byte[] responseBody = response.getBody();
				    if (responseBody != null) {
				        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(responseBody);
				        return byteArrayInputStream;
				    }
				}
			} catch (Exception e) {
				throw new ImpressaoErrorException(e.getMessage());
			}
		}
		return null;
	}
	
	private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ","");
        }
        return null;
    }
	
}
