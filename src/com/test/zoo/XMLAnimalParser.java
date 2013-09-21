package com.test.zoo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLAnimalParser {

	//return parsed Document
    public Document parse(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        return document;
    }
    //generate Animals objects from xml and add it to the list
    public void populateListWithXmlData(Document document,List<Animals> animalsList) throws DocumentException {

        Element root = document.getRootElement();
        
        // iterate through child elements of root
        for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
        	Animals animal=new Animals();
            Element element = (Element) i.next();
            animal.setName(element.elementText("name"));
            animal.setSpecies(element.elementText("species"));
            animal.setAge(Integer.valueOf(element.elementText("age")));
            animal.setCage(element.elementText("cage"));
            animal.setKeeper(element.elementText("keeper"));
            animalsList.add(animal);
        }            
        
    }
    //create and return new xml document
    public Document createDocument(List<Animals> animals) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement( "AnimalsList" ); 
        
        for(Animals animal:animals){System.out.println("123");
        	Element el1=root.addElement("animal");
        	Element elname=el1.addElement("name").addText(animal.getName());
        	Element elspec=el1.addElement("species").addText(animal.getSpecies());
        	Element elage=el1.addElement("age").addText(String.valueOf(animal.getAge()));
        	Element elcage=el1.addElement("cage").addText(animal.getCage());
        	Element elkeeper=el1.addElement("keeper").addText(animal.getKeeper());
        }
        System.out.println("321");

        return document;
    }
    //write xml to a file
    public void write(Document document,String path) throws IOException {    	

    	OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter( path ),format);        
        writer.write( document );System.out.println("12453");
        writer.close();

    }
    
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("output.xml");
		List<Animals> animals=new ArrayList<Animals>();
		Animals an=new Animals();an.setName("Joy");an.setAge(1);an.setCage("house");
		an.setKeeper("Andrey");an.setSpecies("cat");
		Animals an2=new Animals();an2.setName("sasa");an2.setAge(21);an2.setCage("hosuse");
		an2.setKeeper("Andrey");an2.setSpecies("cat");
		animals.add(an);
		animals.add(an2);
		
		
		XMLAnimalParser parser=new XMLAnimalParser();
		try {
			//Document doc= parser.parse(file);
			//parser.bar(doc,animals);
			Document doc2= parser.createDocument(animals);
			//parser.bar(doc2);
			parser.write(doc2,"output.xml");
			for (Animals a:animals)
				System.out.println(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
