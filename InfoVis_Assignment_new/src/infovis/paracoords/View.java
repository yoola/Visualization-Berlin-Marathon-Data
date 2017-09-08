package infovis.paracoords;


import infovis.scatterplot.Data;
import infovis.scatterplot.Model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import javax.swing.JPanel;


public class View extends JPanel {
	private Model model = null;
	private DrawComponents draw_data = new DrawComponents();
	private DrawAxes draw_axis = new DrawAxes();	

	private Vector<Data> list1 = new Vector<Data>();	
	private Vector<String> years_vec = new Vector<String>();
	private Vector<String> winners_times_vec = new Vector<String>();
	private Vector<Double> winners_conv_times_vec = new Vector<Double>();
	private Vector<String> winners_firstnames_vec = new Vector<String>();
	private Vector<String> winners_lastnames_vec = new Vector<String>();
	private Vector<String> names_vec = new Vector<String>();
	private Vector<String> countries_vec = new Vector<String>();
	private Vector<Integer> data_xaxis = new Vector<Integer>();
	private Vector<Integer> data_yaxis = new Vector<Integer>();
	
	private Point mouse_pos = new Point(0, 0); 
	private Point mouse_start = new Point(0, 0);
	private Point mouse_end = new Point(0, 0);
	private Point mouse_move = new Point(0, 0);
	
	private int diff_xaxis = 0;	
	private int diff_yaxis = 0;

	
	
	public Point getMousePosition(){
  		
  		return mouse_pos;
  	}
  	
  	public void setMousePosition(Point pos){
  		
  		mouse_pos = pos;
  	}
  	
  	public Point getMouseStart(){
  		
  		return mouse_start;
  	}
  	
  	public void setMouseStart(Point pos){
  		
  		mouse_start = pos;
  	}
  	
  	
  	public Point getMouseEnd(){
  		
  		return mouse_end;
  	}
  	
  	public void setMouseEnd(Point pos){
  		
  		mouse_end = pos;
  	}
  	
  	public Point getMouseMove(){
  		
  		return mouse_move;
  	}
  	
  	
  	public void setMouseMove(Point pos){
  		
  		mouse_move = pos;
  		
  		
  		
  	}
  	
  	
  	
  	
  	
	@Override
	public void paint(Graphics g) {
	
		
		
		Graphics g2D = (Graphics) g;
		
		//g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.clearRect(0, 0, getWidth(), getHeight());
		//g2D.setColor(Color.decode("#EBECE3"));
		//g2D.fillRect(0, 0, getWidth(), getHeight());
		
		list1 = model.getList();
		
		int first_year = 1980;
		int last_year = 2010;
		int last_time = 3;
		int number_of_elems = list1.size()/20-1;
		int women_data_index = 39;
		int x1 = 100;  // starting coordinates for axis lines
		int y1 = 100;
		int x2 = getWidth()/2+100;
		int y2 = getHeight()-100;	
	
		
		g2D.setFont(new Font("Arial",Font.PLAIN,18));	
		g2D.drawString("Times of Berlin Marathon Winners", getWidth()/4, y1-50);
		g2D.setColor(Color.RED);
		g2D.fillOval(getWidth()/4-2, y1-22, 4, 4);
		g2D.setColor(Color.BLACK);
		g2D.drawLine(getWidth()/4+2, y1-20, getWidth()/4+20, y1-20);
		g2D.setFont(new Font("Arial",Font.PLAIN,12));
		g2D.drawString("Women", getWidth()/4+25, y1-15);
		
		g2D.setColor(Color.BLUE);
		g2D.fillOval(getWidth()/4+90, y1-22, 4, 4);
		g2D.setColor(Color.BLACK);
		g2D.drawLine(getWidth()/4+94, y1-20, getWidth()/4+112, y1-20);
		g2D.setFont(new Font("Arial",Font.PLAIN,12));
		g2D.drawString("Men", getWidth()/4+117, y1-15);




		g2D.setFont(new Font("Arial",Font.PLAIN,12));
		
		
		//------Start get necessary data
		
		
		String current_val ="";
			
		for(int i3 = 0; i3< list1.size(); i3++){
		
		
				current_val = list1.elementAt(i3).getValue(0);		
				
				countries_vec.add(list1.elementAt(i3).getValue(5));
				names_vec.add(list1.elementAt(i3).getValue(2)+", "+list1.elementAt(i3).getValue(3));
				
				if(current_val.equals("1")){
					
					years_vec.add(list1.elementAt(i3).getValue(12));
					winners_times_vec.add(list1.elementAt(i3).getValue(11));
					winners_firstnames_vec.add(list1.elementAt(i3).getValue(3));
					winners_lastnames_vec.add(list1.elementAt(i3).getValue(2));
				}
		}
		
		
		//convert times
		
		for(int i=0; i<winners_times_vec.size(); i++){

			String values [] = winners_times_vec.elementAt(i).split(":");
		
			Double val0 =  Double.parseDouble(values[0].substring(1, 2));
			Double val1 =  Double.parseDouble(values[1]);
			Double val2 =  Double.parseDouble(values[2]);
	
			Double value = val0+(1.0/60.0)*val1+(0.01/60.0)*val2;
			winners_conv_times_vec.add(value);
		}
		
		
		//------------ End get necessary data




		//---------- draw x-axis -----------------

		int Line_dist_x = (x2-x1)/5;

		data_xaxis = draw_axis.drawXaxis(g2D, x1, y1, x2, y2, Line_dist_x, getMousePosition(), mouse_start, first_year, last_year);

		diff_xaxis = data_xaxis.elementAt(0);
		Line_dist_x = data_xaxis.elementAt(1);

		// ----------- draw y-axis ---------------------


		int Line_dist_y = (y2-y1)/3;


		data_yaxis = draw_axis.drawYaxis(g2D, x1, y1, x2, y2, Line_dist_y, getMousePosition(), mouse_start, last_time);

		diff_yaxis = data_yaxis.elementAt(0);
		Line_dist_y = data_yaxis.elementAt(1);


		// ------- draw data into graph ------------

		draw_data.drawData(g2D, x1, y1, x2, y2, years_vec, winners_times_vec, winners_conv_times_vec, winners_firstnames_vec,
				winners_lastnames_vec, countries_vec, names_vec, diff_xaxis, diff_yaxis, Line_dist_x, Line_dist_y, number_of_elems,
				women_data_index, getMousePosition(), getMouseMove());
		
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
