package com.fcn.spdy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "ComparisonServlet", urlPatterns = { "/ComparisonServlet" })
public class ComparisonServlet extends HttpServlet {

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
		List<List<FlowObject>> fol = new ArrayList<List<FlowObject>>();
		for (Part part : request.getParts()) {
			String fileName = extractFileName(part);
			file = savePath + File.separator + fileName;
			part.write(file);
			List<FlowObject> fo = MainPCAPAnalyser.main(file, savePath + File.separator);
			fol.add(fo);
		}
		String json = JsonGenerator.convert(fol, savePath + File.separator);
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("statistics",json);
		getServletContext().getRequestDispatcher("/comparison.jsp").forward(request, response);
		 
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
