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
import br.com.livro.util.RegexUtil;

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
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		
		if(id != null)
		{
			//informou o id
			Carro carro = carroService.getCarro(id);
			if(carro != null)
			{
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(response, json);
			}else
			{
				response.sendError(404,"Carro não encontrado");
			}
		}else
		{
			//Lista de carros
			List<Carro> carros = carroService.getCarros();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);
			ServletUtil.writeJSON(response, json);
		}
		
		
//		List<Carro> carros = carroService.getCarros();
//		ListaCarros lista = new ListaCarros();
//		lista.setCarros(carros);
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
		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(lista);
		//Escreve o json na response do servlet com application/json
		//ServletUtil.writeJSON(response, json);
		
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Cria o carro
		Carro carro = getCarroFromRequest(req);
		System.out.println(carro.getNome());
		
		//Salva o carro
		carroService.save(carro);
		
		//Escreve o json do carro salvo
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(carro);
		ServletUtil.writeJSON(resp, json);		
	}
	
	
	private Carro getCarroFromRequest(HttpServletRequest request)
	{
		Carro carro = new Carro();
		String id = request.getParameter("id");
		if (id != null)
		{
			//Se informou o id, busca o objeto no banco de dados
			carro = carroService.getCarro(Long.parseLong(id));
		}
		carro.setNome(request.getParameter("nome"));
		carro.setDesc(request.getParameter("descricao"));
		carro.setUrlFoto(request.getParameter("urlFoto"));
		carro.setUrlVideo(request.getParameter("urlVideo"));
		carro.setLatitude(request.getParameter("latitude"));
		carro.setLongitude(request.getParameter("longitude"));
		carro.setTipo(request.getParameter("tipo"));				
		return carro;
	}

	



}
