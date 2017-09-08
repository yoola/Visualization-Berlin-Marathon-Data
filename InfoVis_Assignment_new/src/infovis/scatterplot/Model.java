package infovis.scatterplot;

import infovis.debug.Debug;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.stream.FileImageInputStream;

public class Model {
	private Vector<Data> list  = new Vector<Data>();
	private ArrayList<Range> ranges = new ArrayList<Range>();
	private ArrayList<String> labels = new ArrayList<String>();
	private int dim = 0;
	
	public ArrayList<String> getLabels() {
		return labels;
	}
	public void setLabels(ArrayList<String> labels) {
		this.labels = labels;
	}
	public Vector<Data> getList() {
		return list;
	}
	public void setList(Vector<Data> list) {
		this.list = list;
	}
	public ArrayList<Range> getRanges() {
		return ranges;
	}
	public void setRanges(ArrayList<Range> ranges) {
		this.ranges = ranges;
	}
	public Model() {
		importValues();
	}
	public Iterator<Data> iterator() {
		return list.iterator();
	}
	public int getDim() {
		return dim;
	}
	public void setDim(int dim) {
		this.dim = dim;
	}
	

	
	public void importValues() {
		
		
		File file = new File("BM_results.ssv");
		//File file = new File("cars.ssv");
		//File file = new File("cameras.ssv");
		
		
	    Debug.p(file.getAbsoluteFile().toString());
	   
	    try {
	    	 String thisLine = null;
	    	 BufferedReader br = new BufferedReader(new FileReader(file));
	         try {
	        	 //Import Labels
	        	 thisLine = br.readLine();
				 String l [] = thisLine.split(";");
				 for (int i = 0; i < l.length; i++) labels.add(l[i]); // import labels excluding name				 
				 
				 setDim(l.length);
				 
	        	 
	        	 // Import Data and adapt Ranges
				 while ((thisLine = br.readLine()) != null) { // while loop begins here
					 String values [] = thisLine.split(";");
					
					 String dValues [] = new String[values.length];
					 
					 for (int j =0; j < values.length; j++) {
						 dValues[j] = values[j];
			
					 }	
					 	 
					 list.add(new Data(dValues, values[0]));
					 
	   			}
				 
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // end while 

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
    
}
