package infovis.paracoords;

import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import java.util.*;
import java.awt.geom.*;

import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;



class Slice {
	
	String country;
	double value;
	Color color;

	public Slice(String country, double value, Color color) {
		
		this.country = country;
	    this.value = value;
	    this.color = color;
	    }
	}



public class DrawPieChart{
	
	private Vector<String> pie_elems = new Vector<String>();
	private Vector<String> pie_names = new Vector<String>();
	private Map<String, Integer> map_freq = new HashMap<String, Integer>(); 
	private Map<String, Color> map_colors = new HashMap<String, Color>(); 
	

//	private Color[] color_ = { Color.decode("#A9EFE0"), Color.green,Color.yellow, Color.red, Color.gray ,Color.magenta, Color.pink, Color.cyan, Color.darkGray, Color.orange,
//						Color.lightGray, Color.decode("#905656"), Color.decode("#B650AC"), Color.decode("#509FB6"), Color.decode("#A9EFE0"), Color.decode("#564DB0"),
//						Color.decode("#B3AFD8"), Color.decode("#4DD2AD"), Color.decode("#D0C774"),
//						Color.decode("#E1A472"), Color.decode("#C2E3C7") };
//	
	
	
	public void countElems(){
		
		
		for(int p=0; p<pie_elems.size();p++){
			
			if(!map_freq.containsKey(pie_elems.elementAt(p))){
				
				map_freq.put(pie_elems.elementAt(p),0);	
			}
			
			if(map_freq.containsKey(pie_elems.elementAt(p))){
				
				map_freq.put(pie_elems.elementAt(p), map_freq.get(pie_elems.elementAt(p)) + 1);
			}
		}
	
	}
	
	
	public void SetCountryColor(Graphics g2D, Vector<String> countries_vec){
		
		int r = 130;
		int g = 10;
		int b = 200;
		
		for(int p=0; p<countries_vec.size();p++){
			
			r = (int)(Math.random() * 250);
			g = (int)(Math.random() * 250);
			b = (int)(Math.random() * 250);
			
			if(!map_colors.containsKey(countries_vec.elementAt(p))){
					
				map_colors.put(countries_vec.elementAt(p),new Color(r, g, b));	
			}					
		}	
		
		
	}
	
	
	public void drawPie(Graphics g2D, int i, Vector<String> countries_vec, Vector<String> names_vec, int x2, int y2, String year, Point MouseMove){
		
		g2D.setFont(new Font("Arial",Font.PLAIN,18));
		g2D.drawString("Top 20 Countries "+year, x2+100, 50);
		g2D.setFont(new Font("Arial",Font.PLAIN,12));
		
		int x = x2+50;	
		int y = (y2/3)+60;
		
		Rectangle area = new Rectangle(x,y,y2/2,y2/2);	
		Arc2D.Double arc_ = new Arc2D.Double();
		
	
		for(int k=0; k<20; k++){
	
			pie_elems.add(countries_vec.elementAt(i*20+k));	
			pie_names.add(names_vec.elementAt(i*20+k));
		}
		
		
		SetCountryColor(g2D,countries_vec);	
		countElems();
		
		int z = 0;	
		x = x+50;
		y = y -120;
		
		Vector<Slice> slices = new Vector<Slice>();
		
		Color color_tmp = new Color(0,0,0);
		String country_tmp = "";
		String key_tmp = "";
		
		
		for(Map.Entry m:map_freq.entrySet()){
			
			
			country_tmp = String.valueOf(m.getKey());
			
			
			
			for(Map.Entry m_:map_colors.entrySet()){
				
				key_tmp = String.valueOf(m_.getKey());
				
				
				if(key_tmp.contains(country_tmp)){
				
					color_tmp = (Color)m_.getValue();
				}
				
			}
			
			g2D.setColor(Color.BLACK);
			g2D.drawString((String)m.getKey(), x,y);
			g2D.setColor(color_tmp);
			g2D.fillOval(x-12, y-10, 10, 10);
			
			slices.add(new Slice(country_tmp, (int)m.getValue(), color_tmp));
			
			y = y+15;
			z++;
			if(z%4 == 0){
				
				x = x+60;
				y = (y2/3)-60;
			}
	
		}  
		
		
	   	   
	    double total = 0.0D;
	    for (int s = 0; s < slices.size(); s++) {
	      total += slices.elementAt(s).value;
	    }

	    double curValue = 0.0D;
	    int startAngle = 0;
	    int t_inarc = 0;
	    
	    for (int t = 0; t < slices.size(); t++) {
	    	
	    	
	    	startAngle = (int) (curValue * 360 / total);
	    	int arcAngle = (int) (slices.elementAt(t).value * 360 / total);

	    	g2D.setColor(slices.elementAt(t).color);
	    	g2D.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
	      
	    	arc_.setArc(area.x, area.y, area.width, area.height,startAngle,arcAngle,Arc2D.PIE);
	           
	      
	    	if(arc_.contains(MouseMove)){
	    		
	    		t_inarc = t;
	    		//System.out.println("inside arc\n");
	    	}
	      
	      
	    	curValue += slices.elementAt(t).value;
	    }
	    
	    
	    
	    // do it again so pie does not overwrite hover text
	    
	    curValue = 0.0D;
	    startAngle = 0;
	    
	    for (int t = 0; t < slices.size(); t++) {
	    	
	    	
	    	startAngle = (int) (curValue * 360 / total);
	    	int arcAngle = (int) (slices.elementAt(t).value * 360 / total);	      
	    	arc_.setArc(area.x, area.y, area.width, area.height,startAngle,arcAngle,Arc2D.PIE);
	
	    	if(t==t_inarc && arc_.contains(MouseMove)){
	    		
	    		g2D.setColor(Color.BLACK);
	    		g2D.drawString(String.valueOf(slices.elementAt(t).country)+": ", MouseMove.x+5, MouseMove.y-5);
	    		
	    		int x_ = MouseMove.x+10;
	    		int y_ = MouseMove.y+15;
	    		
	    		for(int p=0; p<pie_elems.size();p++){
	    			
	    			
	    			if(pie_elems.elementAt(p).contains(slices.elementAt(t).country)){
	    				
	    				g2D.drawString((p+1)+". "+pie_names.elementAt(p), x_, y_);
	    				
	    				y_ = y_+15;
	    				
	    			}	
	    		}
	    		
	    		
	    	}
	      
	      
	    	curValue += slices.elementAt(t).value;
	    }
	   
	    
	    g2D.setColor(Color.BLACK);
		map_freq.clear();
		pie_elems.clear();
		pie_names.clear();
	}
	
}