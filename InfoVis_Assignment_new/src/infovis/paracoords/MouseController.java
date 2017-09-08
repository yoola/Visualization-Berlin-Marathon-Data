package infovis.paracoords;

import infovis.scatterplot.Model;

import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {
	private View view = null;
	private Model model = null;
	Shape currentShape = null;
	private Point start = new Point(0, 0); 
	private Point end = new Point(0, 0);
	private Point otw = new Point(0, 0);
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
		
		start.x = e.getX();
		start.y = e.getY();
		view.setMousePosition(start);
		view.setMouseStart(start);
		view.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		
		end.x = e.getX();
		end.y = e.getY();
		
		view.setMousePosition(end);		
		view.setMouseEnd(end);
		view.repaint();
	}

	public void mouseDragged(MouseEvent e) {
		
		otw.x = e.getX();
		otw.y = e.getY();
		
		
		view.setMousePosition(otw);
		view.repaint();
	}

	public void mouseMoved(MouseEvent e) {
		
		view.setMouseMove(new Point(e.getX(), e.getY()));
		view.repaint();
		
	
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
