package infovis.scatterplot;

import java.awt.Color;

import com.sun.org.apache.regexp.internal.recompile;

public class Data{
	private String [] values;
	private Color color = Color.BLACK;
	private String label = "";
	
	public Data(String[] values, String label) {
		super();
		this.values = values;
		this.label = label;
	}

	public Data(String[] values, Color color, String label) {
		super();
		this.values = values;
		this.color = color;
		this.label = label;
	}

	public Data (String[] values) {
		this.values = values;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
	public int getDimension(){
		 return values.length;
	}
	public String getValue(int index){
		return values[index];
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(label);
		stringBuffer.append('[');
		for (String value : values) {
			stringBuffer.append(value);
			stringBuffer.append(',');
		}
		
		stringBuffer.append(']');
	return stringBuffer.toString();
	}

}
