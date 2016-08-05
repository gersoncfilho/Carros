package br.com.livro.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.core.Application;


import org.glassfish.jersey.jettison.JettisonFeature;


public class MyApplication extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Map<String, Object> getProperties()
	{
		Map<String, Object> properties = new HashMap<>();
		//Configura o pacote para fazer scan das classes com anotacoes REST
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
	}

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
