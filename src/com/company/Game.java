package com.company;

import java.lang.Runnable;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game implements Runnable {

    Socket mainSocket = null;
    //final static String HOST_NAME = "localhost";

    Game(Socket mainSocket) {
        this.mainSocket = mainSocket;
    }

    public static void main(String[] args) throws IOException {

        Server.loadXMLFile();
        int serverPort = Server.SERVER_PORT;
        String hostName = Server.SERVER_IP;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Witamy w grze ŁóDź 2137");
            System.out.println("Proszę podać 4 cyfrowy numer portu");
            int clientPort = Integer.parseInt(reader.readLine());

            Socket clientSocket = new Socket(hostName, serverPort);
            ServerSocket serverSocket = new ServerSocket(clientPort);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(clientPort);

            Thread clientThread = new Thread(new Game(serverSocket.accept()));
            clientThread.start();

            String messageToServer;
            while (true) {
                if ((messageToServer = reader.readLine()) != null) {
                    writer.println(messageToServer);
                    if(messageToServer.equals("exit")){
                        System.exit(1);
                    }

                }
            }
        }
        catch(Exception e) {
            System.out.println("Nie udało się połączyć "+e.getMessage());
        }
    }

    public void run() {

        try {
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
            String messageFromServer;
            while (true) {
                if ((messageFromServer = serverReader.readLine()) != null) {
                    System.out.println(messageFromServer);
                    if(messageFromServer.equals("Umarłes, koniec gry, do widzenia! było nie umierać!")){
                        System.exit(1);
                    }
                }

            }
        }
        catch (IOException e) {
            System.out.println("Nie udało się połączyć "+e.getMessage());
        }
    }
}

