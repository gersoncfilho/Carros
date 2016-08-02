package br.com.livro.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.util.JAXBUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CarroServlet
 */
@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	CarroService carroService = new CarroService();
    		
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Carro> carros = carroService.getCarros();
		ListaCarros lista = new ListaCarros();
		lista.setCarros(carros);
		/*
		//Gera o XML
		String xml = JAXBUtil.toXML(lista);
		
		//Escreve o xml na response do servlet com application/xml
		ServletUtil.writeXML(response, xml);
		*/
		
		
		/*
		// Gera o JSON
		String json = JAXBUtil.toJSON(lista);
		//Escreve o json na response do servlet com application/json
		ServletUtil.writeJSON(response, json);
		*/
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		//Escreve o json na response do servlet com application/json
		ServletUtil.writeJSON(response, json);
		
	}
}
