package com.kartha.cssp;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kartha.cssp.config.jwt.JwtConfiguration;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.util.DefaultResourceRetriever;
import com.nimbusds.jose.util.ResourceRetriever;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import static com.nimbusds.jose.JWSAlgorithm.RS256;

@SpringBootApplication
public class Application {

	@Autowired
	private JwtConfiguration jwtConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ConfigurableJWTProcessor configurableJWTProcessor() throws MalformedURLException {
		ResourceRetriever resourceRetriever =
				new DefaultResourceRetriever(jwtConfiguration.getConnectionTimeout(),
						jwtConfiguration.getReadTimeout());
						URL jwkSetURL= new URL(jwtConfiguration.getJwkUrl());
		JWKSource keySource= new RemoteJWKSet(jwkSetURL, resourceRetriever);
		ConfigurableJWTProcessor jwtProcessor= new DefaultJWTProcessor();
		JWSKeySelector keySelector= new JWSVerificationKeySelector(RS256, keySource);
		jwtProcessor.setJWSKeySelector(keySelector);
		return jwtProcessor;
	}

}
