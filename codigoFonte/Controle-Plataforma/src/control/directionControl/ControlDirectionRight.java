package control.directionControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import control.ControlClientSocket;
import view.InitialView;

public class ControlDirectionRight implements ActionListener{
	
	private InitialView initialView;
	
	public ControlDirectionRight(InitialView initialView) {
		super();
		this.initialView = initialView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Object> option = new ArrayList<Object>();
		option.add(8);
		new ControlClientSocket(this.initialView).sendToServer(option);
		
	}

}
