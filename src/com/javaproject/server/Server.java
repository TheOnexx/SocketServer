package com.javaproject.server;

import com.javaproject.server.controller.ClientController;
import com.javaproject.server.model.clients.ClientContainer;
import com.javaproject.server.model.network.SocketManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by (TheOne) on 26-Mar-18.
 */
public class Server {
    public void start(int port) {
        startSocket(port);
    }

    private void startSocket(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                createThreadForClient(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createThreadForClient(Socket client) {
        SocketManager socketManager = new SocketManager(client);
        ClientContainer container = new ClientContainer();
        ClientController clientController = new ClientController(container, socketManager);

        new Thread(() -> {
            try {
                 while (!Thread.interrupted()) {
                     String clientEvent = socketManager.readSocket();
                     if(clientEvent != null && !clientEvent.isEmpty()) {
                         clientController.handleEvent(clientEvent);
                     } else {
                         Thread.sleep(500);
                     }
                 }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                Thread.yield();
                e.printStackTrace();
            }
        }).start();
    }
}
