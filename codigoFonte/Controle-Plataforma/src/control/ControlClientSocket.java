package control;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.InitialView;


public class ControlClientSocket {
	
	private InitialView initialView;
	
	public ControlClientSocket(InitialView initialView) {
		super();
		this.initialView = initialView;
	}
	
	public void sendToServer(ArrayList<Object> options) {

		Socket socket = null;
		ObjectOutputStream objectOutputStream = null;
		ObjectInputStream objectInputStream = null;
		
		try {
			
			socket = new Socket(this.initialView.getTextField_IP().getText(), 6789);
			
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("Enviando: " + options);
			objectOutputStream.writeObject(options);
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> resposta = (ArrayList<Object>) objectInputStream.readObject();
			
			System.out.println("Resposta: " + resposta);

      } catch (Exception e){
    	  
    	  JOptionPane.showMessageDialog(this.initialView, "Erro na conexão!!\n"+e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
    	  
          e.printStackTrace();

      } finally {

          try {

              if (objectInputStream != null) 
            	  objectInputStream.close();
            	  
              if (objectOutputStream != null){
            	  objectOutputStream.flush();
            	  objectOutputStream.close();
              }

              if (socket != null)
            	  socket.close();
              
          } catch (IOException e) {
        	  JOptionPane.showMessageDialog(this.initialView, "Erro ao fechar socket!!\n"+e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
              e.printStackTrace();
          }
      }
	}
}
