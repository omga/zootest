package com.test.zoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

/**
 * Servlet implementation class XMLServletController
 */
@WebServlet("/XMLServletController")
public class XMLServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private XMLAnimalParser parser=new XMLAnimalParser();
	ZooMain zm=new ZooMain();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLServletController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//zm=(ZooMain) getServletContext().getAttribute("ZooMain");
		parser.write(parser.createDocument(zm.getAnimals()),getServletContext().getRealPath("/").concat("output.xml"));
		response.sendRedirect("output.xml");	
	
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Animals> animals=new ArrayList<Animals>();
		File file=new File(getServletContext().getRealPath("/").concat("input.xml"));
		ServletInputStream istream= request.getInputStream();
		PrintWriter pw = null;
		BufferedReader bf=null;
		StringBuilder sb=new StringBuilder();
		try {
			bf=new BufferedReader(new InputStreamReader(istream));
			pw = new PrintWriter(file);
			String str="";
			while(true){
				str=bf.readLine();
				if(str.startsWith("<?xml")) break;
			}
			pw.write(str);
			while(!(str=bf.readLine()).startsWith("---")){
				pw.write(str);
			}
			
			pw.flush();
		
			parser.populateListWithXmlData(parser.parse(file),animals);
			for(Animals animal:animals){
				System.out.println("added animal "+animal);
			}
			PopulateAnimalList.addToTable(animals);
			zm.addAnimals(animals);
			response.sendRedirect("zozo.jsp");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			try{
				pw.close();
				bf.close();
				istream.close();
			}catch(NullPointerException npe){
				npe.printStackTrace();
			}
		}	
	}
}
