package infovis.paracoords;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;


public class DrawComponents{
	
	private DrawPieChart draw_pie = new DrawPieChart();
		
	public boolean mouse_inside_Area(Point mpos, Point area, String time, int width){
  		
  		if(mpos.x >= area.x-width && mpos.x <= area.x+width && mpos.y >= area.y-width && mpos.y <= area.y+width){
  					
  			return true;
  		}
  		
  		return false;		
  	}
	
	public void drawData(Graphics g2D, int x1, int y1, int x2, int y2, Vector<String> years_vec, Vector<String> winners_times_vec, 
			Vector<Double> winners_conv_times_vec, Vector<String> winners_firstnames_vec, Vector<String> winners_lastnames_vec,
			Vector<String> countries_vec,Vector<String> names_vec,int diff_xaxis, int diff_yaxis, int Line_dist_x, int Line_dist_y, 
			int number_of_elems, int women_data_index, Point MousePos, Point MouseMove){
		
		
		int x1_ = x1;
		int y1_ = y1;
		int x2_ = x2;
		int y2_ = y2;
		
		int x2cut = x2_;
		int x1cut = x1_;
		int y1cut = y1_;
		int y2cut = y2_;
		
		Integer val_year_0 = 0;
		Double val_time_0 = 0.0;
		
		
		Integer val_year_1 = 0;
		Double val_time_1 = 0.0;
		
		int last_year = 2020;
		int first_year = 1970;
		int last_time = 3;
		
		
		// set controller size	
		

		Point data_point_conv_0 = new Point(0,0);
		Point data_point_conv_1 = new Point(0,0);
		String time_0 ="";
		String time_1 = "";
		String name_0 ="";
		String name_1 = "";
		
		
		
		for(int i=0; i<number_of_elems; i++){
			
			
			time_0 = winners_times_vec.elementAt(i);
			time_1 = winners_times_vec.elementAt(i+1);
			name_0 = winners_lastnames_vec.elementAt(i)+", "+ winners_firstnames_vec.elementAt(i);
			name_1 = winners_lastnames_vec.elementAt(i+1)+", "+ winners_firstnames_vec.elementAt(i+1);
			
			
			
			if(diff_xaxis<=0 &&  diff_yaxis >0){
				
				//System.out.print("FIRST CASE\n");
				
				val_year_0 = x1_+(Line_dist_x/10)*(50-(last_year-Integer.parseInt(years_vec.elementAt(i))));
				val_time_0 = y2_-winners_conv_times_vec.elementAt(i)*(Line_dist_y+0.0);
				
				
				val_year_1 = x1_+(Line_dist_x/10)*(50-(last_year-Integer.parseInt(years_vec.elementAt(i+1))));
				val_time_1 = y2_-winners_conv_times_vec.elementAt(i+1)*(Line_dist_y+0.0);
				
				data_point_conv_0 = new Point(val_year_0, val_time_0.intValue());
				data_point_conv_1 = new Point(val_year_1, val_time_1.intValue());

				
				if(mouse_inside_Area(MousePos, data_point_conv_0, time_0, 4)){
					
					draw_pie.drawPie(g2D, i, countries_vec, names_vec, x2, y2, years_vec.elementAt(i), MouseMove);
					
					g2D.drawLine(val_year_0, val_time_0.intValue()+30, val_year_0, val_time_0.intValue()-30);  // draw cursor line
					g2D.drawString(name_0, val_year_0, val_time_0.intValue()-48);
					g2D.drawString(time_0, val_year_0, val_time_0.intValue()-33);
					
				}else if(mouse_inside_Area(MousePos, data_point_conv_1, time_1, 4)){
					
					if(i==number_of_elems-1){
						
						draw_pie.drawPie(g2D, i+1, countries_vec, names_vec, x2, y2, years_vec.elementAt(i+1),MouseMove);	
					}
					
					
					g2D.drawLine(val_year_1, val_time_1.intValue()+30, val_year_1, val_time_1.intValue()-30);
					g2D.drawString(name_1, val_year_1, val_time_1.intValue()-48);
					g2D.drawString(time_1, val_year_1, val_time_1.intValue()-33);
					
				}
				
				
				
				if(val_year_0<=x2cut && val_time_0 >=y1cut&&!(i==women_data_index)){
					g2D.drawLine(val_year_0,val_time_0.intValue(), val_year_1,val_time_1.intValue());
					if(i<women_data_index){
						g2D.setColor(Color.BLUE);	
					}else{
						g2D.setColor(Color.RED);
					}
					
					g2D.fillOval(val_year_0-2,val_time_0.intValue()-2, 4,4);
					g2D.fillOval(val_year_1-2,val_time_1.intValue()-2, 4,4);
					g2D.setColor(Color.BLACK);
				}
				
			}else if(diff_xaxis>0 && diff_yaxis <=0){
				
				//System.out.print("SECOND CASE\n");
				
				val_year_0 = x2_-(Line_dist_x/10)*(50+(first_year-Integer.parseInt(years_vec.elementAt(i))));
				val_time_0 = y1_+(last_time-winners_conv_times_vec.elementAt(i))*(Line_dist_y+0.0);
				
				val_year_1 = x2_-(Line_dist_x/10)*(50+(first_year-Integer.parseInt(years_vec.elementAt(i+1))));
				val_time_1 = y1_+(last_time-winners_conv_times_vec.elementAt(i+1))*(Line_dist_y+0.0);
				
				data_point_conv_0 = new Point(val_year_0, val_time_0.intValue());
				data_point_conv_1 = new Point(val_year_1, val_time_1.intValue());
				
				if(mouse_inside_Area(MousePos, data_point_conv_0, time_0, 4)){
					
					draw_pie.drawPie(g2D, i, countries_vec, names_vec, x2, y2, years_vec.elementAt(i),MouseMove);
				
					g2D.drawLine(val_year_0, val_time_0.intValue()+30, val_year_0, val_time_0.intValue()-30);  // draw cursor line
					g2D.drawString(name_0, val_year_0, val_time_0.intValue()-48);
					g2D.drawString(time_0, val_year_0, val_time_0.intValue()-33);
					
				}else if(mouse_inside_Area(MousePos, data_point_conv_1, time_1, 4)){
					
					if(i==number_of_elems-1){
						
						draw_pie.drawPie(g2D, i+1, countries_vec, names_vec, x2, y2, years_vec.elementAt(i+1),MouseMove);	
					}
					
					
					g2D.drawLine(val_year_1, val_time_1.intValue()+30, val_year_1, val_time_1.intValue()-30);
					g2D.drawString(name_1, val_year_1, val_time_1.intValue()-48);
					g2D.drawString(time_1, val_year_1, val_time_1.intValue()-33);
					
				}
				
				
				
				if(val_year_0>=x1cut && val_time_0 <= y2cut&&!(i==women_data_index)){
					g2D.drawLine(val_year_0,val_time_0.intValue(), val_year_1,val_time_1.intValue());
					if(i<women_data_index){
						g2D.setColor(Color.BLUE);	
					}else{
						g2D.setColor(Color.RED);
					}
					g2D.fillOval(val_year_0-2,val_time_0.intValue()-2, 4,4);
					g2D.fillOval(val_year_1-2,val_time_1.intValue()-2, 4,4);
					g2D.setColor(Color.BLACK);
					
				}
				
				
			}else if(diff_xaxis<=0 && diff_yaxis <=0){
				
				//System.out.print("THIRD CASE\n");
				
				val_year_0 = x1_+(Line_dist_x/10)*(50-(last_year-Integer.parseInt(years_vec.elementAt(i))));
				val_time_0 = y1_+(last_time-winners_conv_times_vec.elementAt(i))*(Line_dist_y+0.0);
				
				val_year_1 = x1_+(Line_dist_x/10)*(50-(last_year-Integer.parseInt(years_vec.elementAt(i+1))));
				val_time_1 = y1_+(last_time-winners_conv_times_vec.elementAt(i+1))*(Line_dist_y+0.0);
				
				data_point_conv_0 = new Point(val_year_0, val_time_0.intValue());
				data_point_conv_1 = new Point(val_year_1, val_time_1.intValue());
				
					
				
				if(mouse_inside_Area(MousePos, data_point_conv_0, time_0, 4)){
					
					draw_pie.drawPie(g2D, i, countries_vec, names_vec, x2, y2, years_vec.elementAt(i),MouseMove);
					
					g2D.drawLine(val_year_0, val_time_0.intValue()+30, val_year_0, val_time_0.intValue()-30); // draw cursor line
					g2D.drawString(name_0, val_year_0, val_time_0.intValue()-48);
					g2D.drawString(time_0, val_year_0, val_time_0.intValue()-33);
					

					
				}else if(mouse_inside_Area(MousePos, data_point_conv_1, time_1, 4)){
					
					if(i==number_of_elems-1){
						
						draw_pie.drawPie(g2D, i+1, countries_vec, names_vec, x2, y2, years_vec.elementAt(i+1),MouseMove);	
					}
					
					
					g2D.drawLine(val_year_1, val_time_1.intValue()+30, val_year_1, val_time_1.intValue()-30);
					g2D.drawString(name_1, val_year_1, val_time_1.intValue()-48);
					g2D.drawString(time_1, val_year_1, val_time_1.intValue()-33);
					
				}
				
				if(val_year_0<=x2cut && val_time_0 <= y2cut && !(i==women_data_index)){
					
					g2D.drawLine(val_year_0,val_time_0.intValue(), val_year_1,val_time_1.intValue());
					if(i<women_data_index){
						g2D.setColor(Color.BLUE);	
					}else{
						g2D.setColor(Color.RED);
					}
					g2D.fillOval(val_year_0-2,val_time_0.intValue()-2, 4,4);
					g2D.fillOval(val_year_1-2,val_time_1.intValue()-2, 4,4);
					g2D.setColor(Color.BLACK);
		
				}
	
			}else if(diff_xaxis>0 && diff_yaxis >0){
				
				//System.out.print("FOURTH CASE\n");
				
				val_year_0 = x2_-(Line_dist_x/10)*(50+(first_year-Integer.parseInt(years_vec.elementAt(i))));
				val_time_0 = y2_-winners_conv_times_vec.elementAt(i)*(Line_dist_y+0.0);
				
				val_year_1 = x2_-(Line_dist_x/10)*(50+(first_year-Integer.parseInt(years_vec.elementAt(i+1))));
				val_time_1 = y2_-winners_conv_times_vec.elementAt(i+1)*(Line_dist_y+0.0);
				
				data_point_conv_0 = new Point(val_year_0, val_time_0.intValue());
				data_point_conv_1 = new Point(val_year_1, val_time_1.intValue());

				
				if(mouse_inside_Area(MousePos, data_point_conv_0, time_0, 4)){
					
					draw_pie.drawPie(g2D, i, countries_vec, names_vec, x2, y2, years_vec.elementAt(i),MouseMove);
					
					g2D.drawLine(val_year_0, val_time_0.intValue()+30, val_year_0, val_time_0.intValue()-30);  // draw cursor line
					g2D.drawString(name_0, val_year_0, val_time_0.intValue()-48);
					g2D.drawString(time_0, val_year_0, val_time_0.intValue()-33);
					
				}else if(mouse_inside_Area(MousePos, data_point_conv_1, time_1, 4)){
					
					if(i==number_of_elems-1){
						
						draw_pie.drawPie(g2D, i+1, countries_vec, names_vec, x2, y2, years_vec.elementAt(i+1),MouseMove);	
					}
					
					
					g2D.drawLine(val_year_1, val_time_1.intValue()+30, val_year_1, val_time_1.intValue()-30);
					g2D.drawString(name_1, val_year_1, val_time_1.intValue()-48);
					g2D.drawString(time_1, val_year_1, val_time_1.intValue()-33);
					
				}
				
				
				if(val_year_0>=x1cut && val_time_0 >=y1cut&&!(i==women_data_index)){
					g2D.drawLine(val_year_0,val_time_0.intValue(), val_year_1,val_time_1.intValue());
					if(i<women_data_index){
						g2D.setColor(Color.BLUE);	
					}else{
						g2D.setColor(Color.RED);
					}
					g2D.fillOval(val_year_0-2,val_time_0.intValue()-2, 4,4);
					g2D.fillOval(val_year_1-2,val_time_1.intValue()-2, 4,4);
					g2D.setColor(Color.BLACK);
				}		
			}	
		}	
		
	}
	
	
}
