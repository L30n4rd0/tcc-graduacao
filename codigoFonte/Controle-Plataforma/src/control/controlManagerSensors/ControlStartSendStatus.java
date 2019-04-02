package control.controlManagerSensors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import control.ControlClientSocket;
import view.InitialView;
import view.StatusView;

public class ControlStartSendStatus implements ActionListener{
	
	private InitialView initialView;
	
	public ControlStartSendStatus(InitialView initialView) {
		super();
		this.initialView = initialView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<Object> option = new ArrayList<Object>();
		option.add(5);
		option.add(this.initialView.getLbl_IP_local().getText());
		
		new ControlClientSocket(this.initialView).sendToServer(option);
		this.initialView.setStatusView(new StatusView());
			
	}

}
