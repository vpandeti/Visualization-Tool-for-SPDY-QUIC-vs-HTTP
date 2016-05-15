package com.fcn.spdy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet(name = "ReadFile", urlPatterns = { "/ReadFile" })
public class StatisticsServlet extends HttpServlet {

	private static final long serialVersionUID = 5885356367302352823L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = (String) request.getAttribute("statistics");
	}

}
