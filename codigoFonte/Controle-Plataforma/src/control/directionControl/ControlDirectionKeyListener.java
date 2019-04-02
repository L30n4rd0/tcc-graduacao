package control.directionControl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import control.ControlClientSocket;
import view.InitialView;

public class ControlDirectionKeyListener implements KeyListener{
	
	private InitialView initialView;

	public ControlDirectionKeyListener(InitialView initialView) {
		super();
		this.initialView = initialView;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
//			System.out.println("up");
			ArrayList<Object> option = new ArrayList<Object>();
			option.add(7);
			new ControlClientSocket(this.initialView).sendToServer(option);
			
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//			System.out.println("left");
			ArrayList<Object> option = new ArrayList<Object>();
			option.add(8);
			new ControlClientSocket(this.initialView).sendToServer(option);
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//			System.out.println("down");
			ArrayList<Object> option = new ArrayList<Object>();
			option.add(9);
			new ControlClientSocket(this.initialView).sendToServer(option);
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//			System.out.println("right");
			ArrayList<Object> option = new ArrayList<Object>();
			option.add(10);
			new ControlClientSocket(this.initialView).sendToServer(option);
			
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//			System.out.println("enter");
			ArrayList<Object> option = new ArrayList<Object>();
			option.add(11);
			new ControlClientSocket(this.initialView).sendToServer(option);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
