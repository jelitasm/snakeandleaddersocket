/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeandleadder;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;


public class ClientThread implements Runnable{
    
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    DiceRoleSnake main;
    StringTokenizer st;
    protected DecimalFormat df = new DecimalFormat("##,#00");
    
    public ClientThread(Socket socket, DiceRoleSnake main){
        this.main = main;
        this.socket = socket;
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
//            main.appendMessage("[IOException]: "+ e.getMessage(), "Error", Color.RED, Color.RED);
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()){
                String data = dis.readUTF();
                st = new StringTokenizer(data);
                /** Get Message CMD **/
                String CMD = st.nextToken();
                switch(CMD){
                    case "CMD_MESSAGE":
                        String msg = "";
                        String frm = st.nextToken();
                        while(st.hasMoreTokens()){
                            msg = msg +" "+ st.nextToken();
                        }
//                        main.appendMessage(msg, frm, Color.BLACK, Color.GREEN);
                        break;
                        
                     default: 
//                        main.appendMessage("[CMDException]: Unknown Command "+ CMD, "CMDException", Color.RED, Color.RED);
                    break;
                }
            }
        } catch(IOException e){
//            main.appendMessage(" Koneksi server sedang bermasalah, mohon tunggu beberapa saat !.", "Error", Color.RED, Color.RED);
        }
    }
}
