package infovis.paracoords;

import java.awt.Point;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;


public class DrawAxes{
	
	
	private boolean xaxis_moved = false;
	private boolean xaxis_was_moved = false;
	
	private boolean yaxis_moved = false;
	private boolean yaxis_was_moved = false;
	
	private int diff_xaxis = 0;
	private int diffx_ = 0;
	
	private int diff_yaxis = 0;
	private int diffy_ = 0;
		
	
	public Vector<Integer> drawXaxis(Graphics g2D, int x1, int y1, int x2, int y2, int Line_dist_x, Point mouse_pos, 
			Point mouse_start, int first_year, int last_year){
		
		int x1_ = x1;
		int x2_ = x2;
		int y2_ = y2;
		
		int x2cut = x2_;
		int x1cut = x1_;
		
		Vector<Integer> data_ = new Vector<Integer>();
	
	
		for(int i=0; i<4; i++){
			
			
			int x_mpos = mouse_pos.x;
			int y_mpos = mouse_pos.y;
			
			
			
			
			//---testing if mouse close to y-axis---
			
			if(xaxis_moved){
				
				diff_xaxis = (mouse_start.x-x_mpos);
				diffx_= diff_xaxis;			
				
			}
			
					
			if(y_mpos <= y2_+10 && y_mpos >= y2_-10 && i==0){
				
				xaxis_moved = true;	
				xaxis_was_moved = true;
							
			}else{			
				
				if(xaxis_was_moved){
					
					diff_xaxis = diffx_;
				}	
				
				xaxis_moved = false;
			}
			
			
			g2D.drawLine(x1_,y2_,x2,y2_);
			g2D.setFont(new Font("Arial",Font.PLAIN,14));
			g2D.drawString("Year", x2-10, y2+35);
			g2D.setFont(new Font("Arial",Font.PLAIN,12));
			
			
			if(diff_xaxis!=0 && i==0){
				
				Line_dist_x = Line_dist_x+Math.abs(diff_xaxis/5);
				
				
			}
			
			// --- mouse moves to the right
			//-- painting lines on y-axis, making sure only those on y-axis are painted
			
			if(diff_xaxis<=0){
				
				
				if(x1_+(Line_dist_x/2)<=x2cut){
					
					g2D.drawLine(x1_+(Line_dist_x/2),y2_-2,x1_+(Line_dist_x/2),y2_+2);
				}
			
				x1_ = x1_+Line_dist_x;
				
				if(x1_<=x2cut){
						
					g2D.drawLine(x1_,y2_-5,x1_,y2_+5);			
					g2D.drawString(String.valueOf(first_year), x1_-10, y2_+20);					
				}
				
				
				
				
				
			// --- mouse moves to the right
			}else{
				
				if(x2_-(Line_dist_x/2) >= x1cut){
					
					g2D.drawLine(x2_-(Line_dist_x/2),y2_-2,x2_-(Line_dist_x/2),y2_+2);
				
				}
				
				x2_ = x2_-Line_dist_x;	
				
				if(x2_>=x1cut){
					
					g2D.drawLine(x2_,y2_-5,x2_,y2_+5);				
					g2D.drawString(String.valueOf(last_year), x2_-10, y2_+20);
				}
				
				
				
			}
				
			first_year= first_year+10;
			last_year= last_year-10;
		}
		
		data_.add(diff_xaxis);
		data_.add(Line_dist_x);
		return data_;
	}
	
	
	
	public Vector<Integer> drawYaxis(Graphics g2D, int x1, int y1, int x2, int y2, int Line_dist_y, Point mouse_pos, 
			Point mouse_start, int last_time){
		
		int y2_ = y2;
		int x1_ = x1;
		int y1_ = y1;
		int y1cut = y1_;
		int y2cut = y2_;
		
		Vector<Integer> data_ = new Vector<Integer>();
		
		for(int i=0; i<3; i++){
			
			
			int x_mpos = mouse_pos.x;
			int y_mpos = mouse_pos.y;
			
			
			//---testing if mouse close to y-axis---
			
			if(yaxis_moved){
				
				diff_yaxis = (mouse_start.y-y_mpos);
				diffy_= diff_yaxis;			
				
			}
			
					
			if(x_mpos <= x1_+10 && x_mpos >= x1_-10 && i==0){
				
				yaxis_moved = true;	
				yaxis_was_moved = true;
							
			}else{			
				
				if(yaxis_was_moved){
					
					diff_yaxis = diffy_;
				}	
				
				yaxis_moved = false;
			}
			
			
			g2D.drawLine(x1,y1-10,x1,y2_);
			g2D.setFont(new Font("Arial",Font.PLAIN,14));
			g2D.drawString("Time in h", x1-75, y1-10);
			g2D.setFont(new Font("Arial",Font.PLAIN,12));
			
			if(diff_yaxis!=0 && i==0){
				
				Line_dist_y = Line_dist_y+Math.abs(diff_yaxis/3);
			}
			
			
			if(diff_yaxis>=0){
				
				y2_ = y2_-Line_dist_y;	
				
				if(y2_>=y1cut){
										
					g2D.drawLine(x1-5,y2_,x1+5,y2_);
					g2D.drawString(String.valueOf(i+1), x1-15, y2_+5);		
				}
				
				if(y2_+(Line_dist_y/2)>=y1cut){
					
					g2D.drawLine(x1-2,y2_+(Line_dist_y/2),x1+2,y2_+(Line_dist_y/2));							
				}
				
				
							
			}else{
				
				if(y1_<=y2cut){
									
					g2D.drawLine(x1-5,y1_,x1+5,y1_);
					g2D.drawString(String.valueOf(last_time), x1-15, y1_+5);
				}
				
				if(y1_+(Line_dist_y/2)<=y2cut){
					
					g2D.drawLine(x1-2,y1_+(Line_dist_y/2),x1+2,y1_+(Line_dist_y/2));			
				}
				
				y1_ = y1_+Line_dist_y;	
				last_time--;
			}
			
		}
		
		data_.add(diff_yaxis);
		data_.add(Line_dist_y);
		return data_;
		
		
	}
}
