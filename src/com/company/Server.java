package com.company;

import java.io.IOException;
import java.lang.Runnable;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.net.BindException;
import java.util.Random;

import static com.company.Quests.nomOfQuests;

public class Server implements Runnable {

    final static int SERVER_PORT = 2137;

    Socket clientSocket = null;
    GameContent content;
    Quests quests;
    int tura;
    boolean cybernetyk, badass, haker;
    int HP, AP, hack, DAP;
    int nomOfPlayas;
    boolean gameLoop;


    Server() {}

    Server(Socket clientSocket, GameContent content, Quests quests) {
        this.clientSocket = clientSocket;
        this.content = content;
        this.quests=quests;
        this.tura=0;
        this.cybernetyk=false;
        this.haker=false;
        this.badass=false;
        this.nomOfPlayas =0;
        gameLoop=true;
    }
    public static void main(String[] args) throws IOException {

        ArrayList<PrintWriter> wszyscyGracze = new ArrayList<PrintWriter>();
        GameContent gracze = new GameContent(wszyscyGracze);
        Quests zadania = new Quests();

        System.out.println("Serwer uruchomiony na porcie "+SERVER_PORT);
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                Thread serverThread = new Thread(new Server(serverSocket.accept(), gracze,zadania));
                serverThread.start();
            }
        }
        catch(BindException e) {
            System.out.println("Nie udało sie połączyć z portem " + SERVER_PORT);
        }
    }

    public void run() {

        String clientIp = "localhost";
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int clientPortNumber = Integer.parseInt(reader.readLine());

            System.out.println("Nowy Klient, Ip: " + clientIp + ", Port: " + clientPortNumber);
            PrintWriter writer = new PrintWriter(new Socket(clientIp, clientPortNumber).getOutputStream(), true);

            content.addPlaya(writer);
            content.showPlot(writer);
            tura++;
            nomOfPlayas++;

            String messageFromClient;
            while (gameLoop) {
                

                    if(tura ==1) {
                        if ((messageFromClient = reader.readLine()) != null) {
                            if (messageFromClient.equals("1")) {
                                content.beginingOfTheGame(writer);
                                tura++;

                            } else if (messageFromClient.equals("exit")) {
                                writer.println("Do widzenia");
                            } else {
                                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                                content.beginingOfTheGame(writer);
                            }
                        }
                    }

                    if(tura ==2) {
                        if ((messageFromClient = reader.readLine()) != null) {
                            if (messageFromClient.equals("1")) {
                                content.cybernetykBackstory(writer);
                                cybernetyk = true;
                                tura++;
                            }
                            else if (messageFromClient.equals("2")) {
                                content.hakerBackstory(writer);
                                haker = true;
                                tura++;
                            }
                           else if (messageFromClient.equals("3")) {
                                content.badassBackstory(writer);
                                badass = true;
                                tura++;

                            } else if (messageFromClient.equals("exit")) {
                                writer.println("Do widzenia");
                            } else {
                                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                                content.beginingOfTheGame(writer);
                            }
                        }
                    }

                    if(tura ==3) {
                        if ((messageFromClient = reader.readLine()) != null) {
                            if (messageFromClient.equals("1") && cybernetyk) {
                                Cybernetyk cybernetyk = new Cybernetyk();
                                this.HP = cybernetyk.getHP();
                                this.AP = cybernetyk.getAP();
                                this.DAP = cybernetyk.getDAP();
                                this.hack = cybernetyk.getHack();

                                content.startCampaign(writer, 23);
                                tura++;

                            }
                            else if (messageFromClient.equals("1") && haker) {
                                Haker haker = new Haker();
                                this.HP = haker.getHP();
                                this.AP = haker.getAP();
                                this.DAP = haker.getDAP();
                                this.hack = haker.getHack();

                                content.startCampaign(writer, 21);
                                tura++;

                            }
                            else if (messageFromClient.equals("1") && badass) {
                                BadAss badAss = new BadAss();
                                this.HP = badAss.getHP();
                                this.AP = badAss.getAP();
                                this.DAP = badAss.getDAP();
                                this.hack = badAss.getHack();

                                content.startCampaign(writer, 23);
                                tura++;

                            } else if (messageFromClient.equals("exit")) {
                                writer.println("Do widzenia");
                            } else {
                                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                                content.beginingOfTheGame(writer);
                            }
                        }
                    }

                if(tura ==4) {
                    System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        if (messageFromClient.equals("1")) {
                            writer.println("Pokonałes przeciwników, lecz oni zabrali ci 12 HP");
                            this.HP -=12;
                            quests.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            quests.quest1(writer);
                            tura++;
                        }
                        else if (messageFromClient.equals("2")) {
                            writer.println("nuuuda nic sie nie dzieje");
                            quests.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            quests.quest1(writer);
                            tura++;
                        }
                         else if (messageFromClient.equals("exit")) {
                            writer.println("Do widzenia");
                        }
                        else {
                            writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                            //content.beginingOfTheGame(writer);
                        }
                    }
                }

                if(tura ==5) {
                    System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest1Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                        tura++;
//                        if (messageFromClient.equals("1")) {
//                            writer.println("Zabiłes niewinnego człowieka, zabrał ci 2 HP");
//                            this.HP -=2;
//                            content.quest2(writer,15);
//                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
//                           tura++;
//                        }
//                      else  if (messageFromClient.equals("2")) {
//                            writer.println("nuuuda nic sie nie dzieje");
//                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
//                            content.quest2(writer,15);
//                            tura++;
//                        }
//                       else if (messageFromClient.equals("3")) {
//                            writer.println("Ta menda chce nas ujebać");
//                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
//                            content.quest2(writer,15);
//                            tura++;
//                        }
//                        else if (messageFromClient.equals("exit")) {
//                            writer.println("Do widzenia");
//                        }
//                        else {
//                            writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
//                            //content.beginingOfTheGame(writer);
//                        }
                    }
                }

                if(tura ==6) {
                    System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest2Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                        tura++;
                      /*  if (messageFromClient.equals("1")) {
                            writer.println("Było ciężko, ale zdołałes ich pokonac, zabrał ci 20 HP");
                            this.HP -=20;
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            content.quest3(writer,18,messageFromClient,tura, content,this.HP,this.AP, this.hack, this.DAP);
                            tura++;
                        }
                        else if (messageFromClient.equals("2")) {
                            writer.println("nuuuda nic sie nie dzieje");
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            content.quest3(writer,18,messageFromClient,tura, content,this.HP,this.AP, this.hack, this.DAP);
                            tura++;
                        }

                        else if (messageFromClient.equals("exit")) {
                            writer.println("Do widzenia");
                        }
                        else {
                            writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                            //content.beginingOfTheGame(writer);
                        }*/
                    }
                }
                if(tura ==7) {
                    System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest3Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                        tura++;
                       /* if (messageFromClient.equals("1")) {
                            writer.println("Nie było ciężko, zostałeś postrzelony ale zdołałes ich pokonac, zabrali ci 16 HP");
                            this.HP -=16;
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            tura++;
                        }
                        else if (messageFromClient.equals("2")) {
                            writer.println("Zostałeś postrzelony wielokrotnie");
                            writer.println("Zdołałes pokonac 2 androidów i 1 człowieka,pozostali uciekli");
                            writer.println("W sumie zabrali ci 22 HP");
                            this.HP -=22;
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            tura++;
                        }

                        else if (messageFromClient.equals("3")) {
                            writer.println("Ledwo udało ci się ujść z życiem");
                            writer.println("Zdołałes pokonac 2 policjantów, 3 androidów i 1 człowieka,pozostali uciekli");
                            writer.println("W sumie zabrali ci 56 HP");
                            this.HP -=56;
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                            tura++;
                        }
                        else if (messageFromClient.equals("4")) {
                            writer.println("Z nudów poszedłeś na kebaba");
                            writer.println("Wziąłes super mega rollo");
                            writer.println("Twoje hp wzrosło o 12");
                            this.HP +=12;
                            content.showStats(writer,this.HP,this.AP, this.hack, this.DAP);
                             tura++;
                        }

                        else if (messageFromClient.equals("exit")) {
                            writer.println("Do widzenia");
                        }
                        else {
                            writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                            //content.beginingOfTheGame(writer);
                        }*/
                    }
                }
                if(tura ==8) {
                    System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest4Ans(writer, messageFromClient, quests, this.HP, this.AP, this.hack, this.DAP, cybernetyk, haker, badass);
                        tura++;
                    }
                        //gameLoop=false;
                      //  writer.println("koniec gry");
                    }
                if(tura >=9) {
                    System.out.println("jest tura: "+tura);
                    Random rand = new Random();
                    int num = rand.nextInt(nomOfQuests);

                    switch (num){
                        case 0:
                            quests.quest1(writer);

                            break;
                        case 1:
                            quests.quest2(writer);

                            break;
                        case 2:
                            quests.quest3(writer);

                            break;
                        case 3:
                            quests.quest4(writer);
                            break;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {


                        switch (num){
                            case 0:
                                //quests.quest1(writer,22);
                                quests.quest1Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                                tura++;
                                break;
                            case 1:
                               // quests.quest2(writer,22);
                                quests.quest2Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                                tura++;
                                break;
                            case 2:
                                //quests.quest3(writer,22);
                                quests.quest3Ans(writer,messageFromClient,tura,quests,this.HP,this.AP, this.hack, this.DAP);
                                tura++;
                                break;
                            case 3:

                                quests.quest4Ans(writer,messageFromClient,quests,this.HP,this.AP, this.hack, this.DAP,cybernetyk,haker,badass);
                                tura++;
                                break;
                        }
                    }
                    //gameLoop=false;
                    //  writer.println("koniec gry");
                }

            }
        }

        catch (IOException e) {
            System.out.println("Nie udało się połączyć "+e.getMessage());
        }
    }
    void losujQuest(){

    }
}


//if (messageFromClient.equals("1")) {}


