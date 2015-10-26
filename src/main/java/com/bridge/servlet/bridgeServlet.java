package com.bridge.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bridge.main.*;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "BridgeServlet", urlPatterns = { "/BridgeServlet",
		"/BridgeServlet.do" }, initParams = {
		@WebInitParam(name = "id", value = "1"),
		@WebInitParam(name = "name", value = "pankaj") })
public class bridgeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String HTML_START = "<html><body>";
	public static final String HTML_END = "</body></html>";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public bridgeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Date date = new Date();
		Quartz.main(new String[0]);
		// Main.bridgeStart();
		out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date=" + date
				+ "</h3>" + HTML_END);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}