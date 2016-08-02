package br.com.livro.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletUtil
 */
@WebServlet("/ServletUtil")
public class ServletUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static void writeXML(HttpServletResponse response, String xml) throws IOException
    {
    	if (xml != null)
    	{
    		PrintWriter writer = response.getWriter();
    		response.setContentType("application/xml; charset=UTF-8");
    		writer.write(xml);
    		writer.close();
    	}
    }
    
    public static void writeJSON(HttpServletResponse response, String json) throws IOException
    {
    	if (json != null)
    	{
    		PrintWriter writer = response.getWriter();
    		response.setContentType("application/json; charset=UTF-8");
    		writer.write(json);
    		writer.close();
    	}
    }
}
