package com.fcn.spdy;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

@MultipartConfig
@WebServlet(name = "ReadFile", urlPatterns = { "/ReadFile" })
public class PcapServlet extends HttpServlet {

	private static final long serialVersionUID = 33680364889301047L;
	private static final String SAVE_DIR = "\\";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String file = ""; 
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			file = savePath + File.separator + fileName;
			part.write(file);
		}
		String json = MainPCAPAnalyser.generateJson(file, savePath + File.separator);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("statistics",json);
		getServletContext().getRequestDispatcher("/visualization.jsp").forward(request, response);
		 
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
