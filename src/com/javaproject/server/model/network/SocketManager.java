package com.javaproject.server.model.network;

import java.io.*;
import java.net.Socket;

/**
 * Created by (TheOne) on 31-Mar-18.
 */
public class SocketManager {
    private Socket socket;

    public SocketManager(Socket socket) {
        this.socket = socket;
    }


    public void writeToSocket(String event) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            writer.write(event + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readSocket() throws IOException, ClassNotFoundException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return getStringFromSocket(bufferedReader);
    }


    private String getStringFromSocket(BufferedReader bufferedReader) throws IOException {
        String line = "";
        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();
        }
        return line;
    }


}
