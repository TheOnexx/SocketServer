package com.javaproject.server.controller;

import com.javaproject.server.model.clients.ClientContainer;
import com.javaproject.server.model.network.SocketManager;

/**
 * Created by (TheOne) on 31-Mar-18.
 */
public class ClientController {
    private ClientContainer clientContainer;
    private SocketManager socketManager;
    public ClientController(ClientContainer container, SocketManager socketManager) {
        this.clientContainer = container;
        this.socketManager = socketManager;
    }

    public void handleEvent(String clientEvent) {
        System.out.println("user message: " + clientEvent);

        socketManager.writeToSocket("Server out message: " + clientEvent);
    }
}
