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
import java.util.Collections;
import java.util.Random;

import static com.company.Quests.nomOfQuests;

public class Server implements Runnable {

    final static int SERVER_PORT = 2137;

    Socket clientSocket = null;
    GameContent content;
    Quests quests;
    int tura;
    boolean cybernetyk, badass, haker;
    static int MAXHP=100,HP, AP, hack, DAP, lvl, exp,exp2;
    static int nextLevel;
    int nomOfPlayas;
    static boolean gameLoop;


    Server() {}

    Server(Socket clientSocket, GameContent content) {
        this.clientSocket = clientSocket;
        this.content = content;
        //this.quests=quests;
        this.tura=0;
        this.cybernetyk=false;
        this.haker=false;
        this.badass=false;
        this.nomOfPlayas =0;
        gameLoop=true;
    }
    public static void main(String[] args) throws IOException {

        ArrayList<PrintWriter> wszyscyGracze = new ArrayList<PrintWriter>();
        ArrayList<Integer> wszyskiePorty = new ArrayList<Integer>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<Quests> listOfQuests = new ArrayList<Quests>();
        GameContent gracze = new GameContent(wszyscyGracze, wszyskiePorty,scores,listOfQuests);


        System.out.println("Serwer uruchomiony na porcie "+SERVER_PORT);
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

            while (true) {
                Thread serverThread = new Thread(new Server(serverSocket.accept(), gracze));
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

            content.addPlaya(writer,clientPortNumber, new Quests(),0);
            content.showPlot(writer);
            quests = content.listOfQuests.get(content.getPlayaNum(clientPortNumber)); /// moze to nie działa? - przerob na metode
            exp2 = content.listOfScores.get(content.getPlayaNum(clientPortNumber));
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
                                this.lvl = cybernetyk.getLevel();
                              //  this.exp2 = cybernetyk.getExp();

                                nextLevel=10;

                                content.startCampaign(writer, 23);
                                tura++;

                            }
                            else if (messageFromClient.equals("1") && haker) {
                                Haker haker = new Haker();
                                this.HP = haker.getHP();
                                this.AP = haker.getAP();
                                this.DAP = haker.getDAP();
                                this.hack = haker.getHack();
                                this.lvl = haker.getLevel();
                               // this.exp2 = haker.getExp();

                                nextLevel=10;

                                content.startCampaign(writer, 21);
                                tura++;

                            }
                            else if (messageFromClient.equals("1") && badass) {
                                BadAss badAss = new BadAss();
                                this.HP = badAss.getHP();
                                this.AP = badAss.getAP();
                                this.DAP = badAss.getDAP();
                                this.hack = badAss.getHack();
                                this.lvl = badAss.getLevel();
                              //  this.exp2 = badAss.getExp();

                                nextLevel=10;

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

                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {
                        if (messageFromClient.equals("1")) {
                            writer.println("Pokonałes przeciwników, lecz oni zabrali ci 12 HP");
                            this.HP -=12;
                            this.exp2+=1;
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
                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest1Ans(writer,messageFromClient,tura,quests);
                        quests.quest2(writer);
                        tura++;
//
                    }
                }

                if(tura ==6) {
                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest2Ans(writer,messageFromClient,tura,quests);
                        quests.quest3(writer);
                        tura++;

                    }
                }
                if(tura ==7) {
                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }
                   // System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest3Ans(writer,messageFromClient,tura,quests);
                        quests.quest4(writer);
                        tura++;

                    }
                }
                if(tura ==8) {
                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }
                 //   System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest4Ans(writer, messageFromClient, quests, cybernetyk, haker, badass);
                        tura++;
                    }

                    }

                if(tura >=9) {

                        content.addPlayaScore(content.getPlayaNum(clientPortNumber), exp2);
                        System.out.println("gracz na porcie o indexie: "+content.getPlayaNum(clientPortNumber)+ " ma "+String.valueOf(exp2)+" expa");


                        writer.println("");
                        writer.println("Wciśnij q by zobaczyć ranking");
                        writer.println("");
                    checkLvl(writer,cybernetyk,haker,badass);
                    if(checkHP()){
                        gameLoop=false;
                    }
                    //writer.println("jest tura: "+tura);
                    Random rand = new Random();
                    int num = rand.nextInt(nomOfQuests);
                    quests.getRandomQuest(writer, Quests.lokacje);

//                    switch (num){
//                        case 0:
//                            quests.quest1(writer);
//                            break;
//                        case 1:
//                            quests.quest2(writer);
//                            break;
//                        case 2:
//                            quests.quest3(writer);
//                            break;
//                        case 3:
//                            quests.quest4(writer);
//                            break;
//                        case 4:
//                            quests.getRandomQuest(writer, Quests.lokacje);
//                            break;
//                    }

                    if ((messageFromClient = reader.readLine()) != null) {
                        if (messageFromClient.equals("q")) {
                            writer.println("Obecni gracze na serwerze "+ content.nomOfPlayerss());
                            //Collections.sort(content.listOfScores);
                            for(int i=0; i< content.nomOfPlayerss();i++){
                                writer.println((i+1)+" to gracz na porcie - "+content.getPlaya(i) + " i ma "+content.getPlayaScore(i)+" punktów exp");
                            }
                        }
                        else {
                            quests.getRandomQuestAns(writer, messageFromClient, quests, cybernetyk, haker, badass);
                            tura++;

                        }

//                        switch (num){
//                            case 0:
//                                //quests.quest1(writer,22);
//                                quests.quest1Ans(writer,messageFromClient,tura,quests);
//                                tura++;
//                                break;
//                            case 1:
//                               // quests.quest2(writer,22);
//                                quests.quest2Ans(writer,messageFromClient,tura,quests);
//                                tura++;
//                                break;
//                            case 2:
//                                //quests.quest3(writer,22);
//                                quests.quest3Ans(writer,messageFromClient,tura,quests);
//                                tura++;
//                                break;
//                            case 3:
//                                quests.quest4Ans(writer,messageFromClient,quests,cybernetyk,haker,badass);
//                                tura++;
//                                break;
//                            case 4:
//                                quests.getRandomQuestAns(writer,messageFromClient,quests,cybernetyk,haker,badass);
//                                tura++;
//                                break;
//                        }
                    }
                }

            }
            if(!gameLoop){
                writer.println("Umarłes, koniec gry, do widzenia! było nie umierać!");
            }
        }

        catch (IOException e) {
            System.out.println("Nie udało się połączyć "+e.getMessage());
        }
    }

//    static void bubbleSort(GameContent content) {
//        //ArrayListarr = co
//        int n = content.nomOfPlayerss();
//        int temp = 0;
//        for(int i=0; i < n; i++){
//            for(int j=1; j < (n-i); j++){
//                if(Integer.parseInt(content.getPlayaScore(j-1) ) > Integer.parseInt(content.getPlayaScore(j) )){
//                    //swap elements
//                    temp = Integer.parseInt(content.getPlayaScore(j-1) );
//                    Integer.parseInt(content.getPlayaScore(j-1)) = Integer.parseInt(content.getPlayaScore(j) );
//                    Integer.parseInt(content.getPlayaScore(j) ) = temp;
//                }
//
//            }
//        }
//
//    }

    void checkLvl(PrintWriter writ, boolean b1, boolean b2, boolean b3){
        if(exp2 >= nextLevel){
            lvl++;
            nextLevel=nextLevel*2;
            writ.println("");
            writ.println("   LEVEL UP!");
            writ.println("");
            MAXHP+=10;
            HP=MAXHP;

            if(b1){
               // HP+=10;
                AP+=1;
                hack+=2;
                DAP+=4;
            }
            if(b2){
              //  HP+=10;
                AP+=1;
                hack+=4;
                DAP+=2;
            }
            if(b3){
             //   HP+=10;
                AP+=5;
                hack+=1;
                DAP+=1;
            }
        }
    }
    boolean checkHP(){
        if(HP <= 0){

            return true;
        }
        return false;
    }
}
