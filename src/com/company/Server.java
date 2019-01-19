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

//import static com.company.Quests.nomOfQuests;

public class Server implements Runnable {

    final static int SERVER_PORT = 2137;

    Socket clientSocket = null;
    GameContent content;
    Quests quests;
    int tura;
    boolean cybernetyk, badass, haker;
    static int MAXHP=100,HP, AP, hack, DAP, exp,exp2;
    //static int[] exp3;
     int nextLevel;
    int nomOfPlayas;
     boolean gameLoop;
    //static int[] exp3;
    ArrayList<Integer> myPoints=null;
    ArrayList<Integer> myHP=null;
    int lvl;


    Server() {}

    Server(Socket clientSocket, GameContent content) {
        this.clientSocket = clientSocket;
        this.content = content;
        //this.quests=quests;

        //this.exp3= exp;
        myPoints = new ArrayList<Integer>();
        myHP = new ArrayList<Integer>();
        this.tura=0;
        this.cybernetyk=false;
        this.haker=false;
        this.badass=false;
        //this.nomOfPlayas =0;
        gameLoop=true;
    }
    public static void main(String[] args) throws IOException {

        ArrayList<PrintWriter> wszyscyGracze = new ArrayList<PrintWriter>();
        ArrayList<Integer> wszyskiePorty = new ArrayList<Integer>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<Quests> listOfQuests = new ArrayList<Quests>();
      //  int[] exp33 = new int[222];
        GameContent gracze = new GameContent(wszyscyGracze, wszyskiePorty,scores,listOfQuests);

//        ArrayList<Integer> prykaldowaa = new ArrayList<Integer>();
//        prykaldowaa.add(100);
//        prykaldowaa.add(-12);
//        prykaldowaa.add(-8);
//        prykaldowaa.add(-18);
//        System.out.println("prykladowa ma : "+ getPointsFromya(prykaldowaa));


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


       //     content.exp3[content.nomOfPlayerss()]=0;
            content.addPlaya(writer,clientPortNumber, new Quests(),content.nomOfPlayerss());
            content.showPlot(writer);
            quests = content.listOfQuests.get(content.getPlayaNum(clientPortNumber)); /// moze to nie działa? - przerob na metode
            //exp2 = content.listOfScores.get(content.getPlayaNum(clientPortNumber));
            content.playersListOfskors.add(myPoints);
            content.attacerWeapons.add("nic");
            content.defendWeapons.add("nic");
            tura++;
            nomOfPlayas++;

            String messageFromClient;
            while (gameLoop) {


                if(tura ==1) {
                    if ((messageFromClient = reader.readLine()) != null) {
                         if (messageFromClient.equals("exit")) {
                            writer.println("Do widzenia");
                        } else {
                            content.beginingOfTheGame(writer);
                            tura++;
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
                            myHP.add(cybernetyk.getHP());
                            //exp3[content.nomOfPlayerss()-1] = cybernetyk.getExp();
                            content.addPlayaScore(content.getPlayaNum(clientPortNumber), 0);

                            nextLevel=50;

                            content.startCampaign(writer);
                            tura++;

                        }
                        else if (messageFromClient.equals("1") && haker) {
                            Haker haker = new Haker();
                            this.HP = haker.getHP();
                            this.AP = haker.getAP();
                            this.DAP = haker.getDAP();
                            this.hack = haker.getHack();
                            this.lvl = haker.getLevel();

                            myHP.add(haker.getHP());
                         //   exp3[content.nomOfPlayerss()-1] = haker.getExp();
                            content.addPlayaScore(content.getPlayaNum(clientPortNumber), 0);

                            nextLevel=50;

                            content.startCampaign(writer);
                            tura++;

                        }
                        else if (messageFromClient.equals("1") && badass) {
                            BadAss badAss = new BadAss();
                            this.HP = badAss.getHP();
                            this.AP = badAss.getAP();
                            this.DAP = badAss.getDAP();
                            this.hack = badAss.getHack();
                            this.lvl = badAss.getLevel();

                            myHP.add(badAss.getHP());

                          //  exp3[content.nomOfPlayerss()-1]= badAss.getExp();
                            content.addPlayaScore(content.getPlayaNum(clientPortNumber), 0);

                            nextLevel=50;

                            content.startCampaign(writer);
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

                    System.out.println("tyle graczy: "+content.nomOfPlayerss() );
                   // checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {
                        if (messageFromClient.equals("1")) {
                            writer.println("Pokonałes przeciwników, lecz oni zabrali ci 12 HP");
                            myHP.add(-12);
                            myPoints.add(2);
                            showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                            quests.quest1(writer);
                            tura++;
                        }
                        else if (messageFromClient.equals("2")) {
                            writer.println("nuuuda nic sie nie dzieje");
                            showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                            quests.quest1(writer);
                            tura++;
                        }
                        else if (messageFromClient.equals("exit")) {
                            writer.println("Do widzenia");
                        }
                        else {
                            writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                            tura=4;
                        }
                    }
                }

                if(tura ==5) {
                   // checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {

                        quests.quest1Ans(writer,messageFromClient,tura,quests,content,myHP,myPoints);
                        showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                        quests.quest2(writer);
                        tura++;
//
                    }
                }

                if(tura ==6) {
                    //checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                    if(checkHP()){
                        gameLoop=false;
                    }

                    if ((messageFromClient = reader.readLine()) != null) {

                        quests.quest2Ans(writer,messageFromClient,tura,quests,content,myHP,myPoints);
                        showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                        quests.quest3(writer);
                        tura++;

                    }
                }
                if(tura ==7) {
                    if(checkHP()){
                        gameLoop=false;
                    }
                    // System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {

                        quests.quest3Ans(writer,messageFromClient,tura,quests,content,myHP,myPoints);
                        showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                        quests.quest4(writer);
                        tura++;

                    }
                }
                if(tura ==8) {
                    checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                    if(checkHP()){
                        gameLoop=false;
                    }
                    //   System.out.println("jest tura: "+tura);
                    if ((messageFromClient = reader.readLine()) != null) {

                        quests.quest4Ans(writer, messageFromClient, quests, cybernetyk, haker, badass,content,myHP,myPoints);
                        showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                        quests.getRandomQuest(writer, Quests.lokacje);
                        tura++;
                    }

                }
               // String bron2=null;
                if(tura ==9) {





                    if ((messageFromClient = reader.readLine()) != null) {


                        writer.println("");
                        writer.println("Wciśnij w by zobaczyć graczy na serwerze");
                        writer.println("Wciśnij q by wejść w chat");
                        writer.println("Wciśnij e by wyzwać gracza na pojedynek");
                        writer.println("Napisz 'exit' i wciśnij enter by wyjść");
                        writer.println("");

                        for(int ii=0; ii< content.nomOfPlayerss();ii++){
                            if(content.getPlaya(ii) != clientPortNumber) {
                                if(messageFromClient.equals(content.getPlaya(ii))){
                                    quests.pvpIntro(writer);
                                }
                            }
                        }
                        if (messageFromClient.equals("w")) {
                            if(content.nomOfPlayerss() >1) {
                                writer.println("Obecni gracze na serwerze " + content.nomOfPlayerss());

                                for (int ii = 0; ii < content.nomOfPlayerss(); ii++) {
                                    int x = getPointsFromya(content.playersListOfskors.get(ii));
                                    writer.println("Gracz na porcie - " + content.getPlaya(ii) + " i ma " + x + " punktów exp");
                                }
                            }
                            else {
                                writer.println("Na razie jesteś sam na tym serwerze");
                                writer.println("");
                                quests.getRandomQuest(writer, Quests.lokacje);
//                                tura=9;
                            }
                        }

                        else if(messageFromClient.equals("q")){
                            if(content.nomOfPlayerss() >1) {
                                writer.println("Okno chatu, wpisz 'q' by wyjść");
                                tura = 22;
                            }
                            else {
                                writer.println("Na razie jesteś sam na tym serwerze");
                                writer.println("");
                                quests.getRandomQuest(writer, Quests.lokacje);
                                //tura=9;
                            }
                        }
                        else if(messageFromClient.equals("e")){
                            if(content.nomOfPlayerss() >1) {
                                tura = 11;
                                content.pvpGracze.add(0, clientPortNumber);
                            }
                            else {
                                writer.println("Nie ma z kim walczyć, jesteś sam na tym serwerze");
                                writer.println("");
                                quests.getRandomQuest(writer, Quests.lokacje);
                                //tura=9;
                            }
                        }
                        else if(content.pvpGracze.size()==2) {
                         if (messageFromClient.equals("z")) {
                                writer.println("kamień");
                                content.defendWeapons.add(content.getPlayaNum(clientPortNumber), "kamień");
                                tura = 13;
                            } else if (messageFromClient.equals("x")) {
                                writer.println("papier");
                                content.defendWeapons.add(content.getPlayaNum(clientPortNumber), "papier");
                                tura = 13;
                            } else if (messageFromClient.equals("c")) {
                                writer.println("nożyce");
                                content.defendWeapons.add(content.getPlayaNum(clientPortNumber), "nożyce");
                                tura = 13;
                            }
                        }

                        else {
                            boolean b=quests.getRandomQuestAns(writer, messageFromClient, quests, cybernetyk, haker, badass,content,myHP);
                            if(b){
                                myPoints.add(12);
                            }
                            myPoints.add(1);
                            checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                            if(checkHP()){
                                gameLoop=false;
                            }
                            showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);


                            quests.getRandomQuest(writer, Quests.lokacje);
                        }
                    }

                }

                if(tura ==11) {

                    writer.println("Wybierz broń");
                    writer.println("[z] - kamień");
                    writer.println("[x] - papier");
                    writer.println("[c] - nożyce");
                    if ((messageFromClient = reader.readLine()) != null) {
                        if(messageFromClient.equals("z")){
                            writer.println("kamień");
                            content.attacerWeapons.add(content.getPlayaNum(clientPortNumber),"kamień");
                        }
                        else if(messageFromClient.equals("x")){
                            writer.println("wybrałeś papier");
                            content.attacerWeapons.add(content.getPlayaNum(clientPortNumber),"papier");
                        }
                        else if(messageFromClient.equals("c")){
                            writer.println("wybrałeś nożyce");
                            content.attacerWeapons.add(content.getPlayaNum(clientPortNumber),"nożyce");
                        }
                        writer.println("");
                        writer.println("Lista graczy");
                        for(int ii=0; ii< content.nomOfPlayerss();ii++){
                            if(content.getPlaya(ii) != clientPortNumber) {
                                writer.println("Gracz na porcie - " + content.getPlaya(ii));
                            }
                        }
                        writer.println(" ");
                        writer.println("Podaj numer portu gracza którego chcesz zwyzywać");
                        tura++;
                    }
                }

                if(tura ==12) {
                    int nomOfPotentials=0;
                    if ((messageFromClient = reader.readLine()) != null) {
                        for (int ii = 0; ii < content.nomOfPlayerss(); ii++) {
                            if (messageFromClient.contains(String.valueOf(content.getPlaya(ii)))) {
                                content.pvpGracze.add(1,content.getPlaya(ii));
                                quests.pvpIntro(writer);
                                content.listOfPlayers.get(ii).println("Ktos na porcie "+clientPortNumber+ " chce sie bić w stylu oldsqlowym");
                                content.listOfPlayers.get(ii).println("Wybierz broń");
                                content.listOfPlayers.get(ii).println("[z] - kamień");
                                content.listOfPlayers.get(ii).println("[x] - papier");
                                content.listOfPlayers.get(ii).println("[c] - nożyce");
                                nomOfPotentials++;
                                tura=14;
                                break;
                            }
                            else if(nomOfPotentials==0){
                                writer.println("nie znaleziono nikogo");
                                quests.getRandomQuest(writer, Quests.lokacje);
                                tura=9;
                            }
                        }
                    }
                }

                if(tura ==13) {

                    int playa1=0;
                    int playa2=0;
                    int inegoPort=content.getPlayaNum(content.pvpGracze.get(1));
                    int twojPort=content.getPlayaNum(content.pvpGracze.get(0));


                            if (content.defendWeapons.get(inegoPort).equals("kamień")) {
                                  content.listOfPlayers.get(twojPort).println("przeciwnik wybrał kamień ");
                                // writer.println("wybraliście kamień - remis");
                                playa1 = 1;

                            } else if (content.defendWeapons.get(inegoPort).equals("papier")) {
                                content.listOfPlayers.get(twojPort).println("przeciwnik wybral papier ");
                                //  writer.println("wybraliście papier - remis");
                                playa1 = 2;

                            } else if (content.defendWeapons.get(inegoPort).equals("nożyce")) {
                                   content.listOfPlayers.get(twojPort).println(" przeciwnik wybral nożyce ");
                                // writer.println("wybraliście nożyce - remis");
                                playa1 = 3;
                            }


                    if (content.attacerWeapons.get(twojPort).equals("kamień")) {
                        content.listOfPlayers.get(inegoPort).println("przeciwnik wybral kamień");
                        // writer.println("wybraliście kamień - remis");
                        playa2 = 1;

                    } else if (content.attacerWeapons.get(twojPort).equals("papier")) {
                        content.listOfPlayers.get(inegoPort).println("przeciwnik wybral papier ");
                        //  writer.println("wybraliście papier - remis");
                        playa2 = 2;

                    } else if (content.attacerWeapons.get(twojPort).equals("nożyce")) {
                        content.listOfPlayers.get(inegoPort).println("przeciwnik wybral nożyce ");
                        // writer.println("wybraliście nożyce - remis");2\
                        playa2 = 3;
                    }


                    if(playa1 == playa2){
                        //writer.println("remis , bo wyzwany ma "+playa1+" a wyzywający ma "+playa2);
                        content.listOfPlayers.get(inegoPort).println("remis , bo wyzwany ma "+playa1+" a wyzywający ma "+playa2);
                        content.listOfPlayers.get(twojPort).println("remis , bo wyzwany ma "+playa1+" a wyzywający ma "+playa2);
                        tura=14;
                    }

                    ///playa 1 wygrywa
                    else if((playa1==1 && playa2==3) ||(playa1==2 && playa2==1) || (playa1==3 && playa2==2) ){
                       // writer.println(" nie remis , bo wyzwany ma "+playa1+" a wyzywający ma "+playa2);
                        content.listOfPlayers.get(inegoPort).println(" WYGRAłeś ");
                        content.listOfPlayers.get(inegoPort).println(" otrzymujesz 25 exp");

                        content.listOfPlayers.get(twojPort).println(" PRzegRAłeŚ ");
                        content.listOfPlayers.get(twojPort).println(" tracisz 10 exp");

                        content.playersListOfskors.get(inegoPort).add(25);
                        content.playersListOfskors.get(twojPort).add(-10);
                        tura=14;
                    }

                    ///playa 2 wygrywa
                    else if((playa2==1 && playa1==3) ||(playa2==2 && playa1==1) || (playa2==3 && playa1==2) ){

                        content.listOfPlayers.get(inegoPort).println(" PRZegrałEŚ ");
                        content.listOfPlayers.get(inegoPort).println("stracies 10 exp ");

                        content.listOfPlayers.get(twojPort).println(" WYGRAłeś ");
                        content.listOfPlayers.get(twojPort).println("Zdobyles 25 exp");

                        content.playersListOfskors.get(twojPort).add(25);
                        content.playersListOfskors.get(inegoPort).add(-10);
                        tura=14;
                    }


                }
                if(tura==14){
                    writer.println("Tymczasem ty idziesz dalej w miasto");
                    quests.getRandomQuest(writer, Quests.lokacje);
                    tura++;
                }
                if(tura==15){
                    if ((messageFromClient = reader.readLine()) != null) {
                        boolean b = quests.getRandomQuestAns(writer, messageFromClient, quests, cybernetyk, haker, badass, content, myHP);
                        if (b) {
                            myPoints.add(20);
                        }
                        content.pvpGracze.clear();
                        myPoints.add(1);
                        showStats(writer, getPointsFromya(myHP), AP, hack, DAP, getPointsFromya(myPoints),lvl);
                        quests.getRandomQuest(writer, Quests.lokacje);
                        tura=9;
                    }

                }
                if (tura == 22) {

                    if ((messageFromClient = reader.readLine()) != null) {
                        if(messageFromClient.equals("q")){

                            quests.getRandomQuest(writer, Quests.lokacje);
                            tura=9;
                        }
                        for(int ii=0; ii< content.nomOfPlayerss();ii++){
                            if(content.getPlaya(ii) == clientPortNumber){
                                continue;
                            }
                            content.listOfPlayers.get(ii).println("[ "+clientPortNumber+" ] "+messageFromClient);
                        }
                    }
                }

            }
            if(!gameLoop){
                writer.println("Umarłes, koniec gry, do widzenia! było nie umierać!");
                content.listOfPlayers.remove(writer);
                content.listOfGamePorts.remove(content.getPlayaNum(clientPortNumber));
            }
        }

        catch (IOException e) {
            System.out.println("Nie udało się połączyć "+e.getMessage());
        }
    }


    void checkLvl(PrintWriter writ, boolean b1, boolean b2, boolean b3, int prt){
        if(getPointsFromya(content.playersListOfskors.get(content.getPlayaNum(prt))) >= nextLevel){
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
        if(getPointsFromya(myHP) <= 0){
            return true;
        }
        return false;
    }
    int getPointsFromya(ArrayList<Integer> a){

        int sum = 0;
        for(int i = 0; i < a.size(); i++)
            sum += a.get(i);
        return sum;
    }

    void showStats (PrintWriter writer, int hp, int ap, int hac, int dap,int exp,int lvl) {
        writer.println("|---------------------------------------------|");
        writer.println("|----HP----AP---HACK---DAP----|----exp: "+exp+"-----|");
        writer.println("|---("+hp+")----"+ap+"----"+hac+"----"+dap+"-----|----LEVEL: "+lvl+"---|");
        writer.println("|---------------------------------------------|");
        writer.println(" ");
    }

}