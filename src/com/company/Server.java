package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.lang.Runnable;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.util.*;
import java.net.BindException;

//import static com.company.Quests.nomOfQuests;

public class Server implements Runnable {

   // static String SERVER_POR;
    static int SERVER_PORT;
    static String SERVER_IP;

    Socket clientSocket = null;
    GameContent content;
    Quests quests;
    PvPhandler pvp;
    int tura;
    boolean cybernetyk, badass, haker;
     int HP, AP, hack, DAP, exp,exp2;
    int MAXHP;
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
        pvp=new PvPhandler();
        //this.exp3= exp;
        myPoints = new ArrayList<Integer>();
        myHP = new ArrayList<Integer>();
        this.tura=0;
        this.cybernetyk=false;
        this.haker=false;
        this.badass=false;
        //this.nomOfPlayas =0;
        gameLoop=true;
        MAXHP=100;
    }
    public static void main(String[] args) throws IOException {
        loadXMLFile();
//        try {
//        File fXmlFile = new File("/Users/mkyong/staff.xml");
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(fXmlFile);
//        //optional, but recommended
//        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
//        doc.getDocumentElement().normalize();
//        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//        NodeList nList = doc.getElementsByTagName("staff");
//        System.out.println("----------------------------");
//
//        for (int temp = 0; temp < nList.getLength(); temp++) {
//            Node nNode = nList.item(temp);
//            System.out.println("Current Element :" + nNode.getNodeName());
//            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                System.out.println("Staff id : " + eElement.getAttribute("id"));
//                System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//                System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//            }
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }


        ArrayList<PrintWriter> wszyscyGracze = new ArrayList<PrintWriter>();
        ArrayList<Integer> wszyskiePorty = new ArrayList<Integer>();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        ArrayList<Quests> listOfQuests = new ArrayList<Quests>();
      //  int[] exp33 = new int[222];
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

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int clientPortNumber = Integer.parseInt(reader.readLine());

            System.out.println("Nowy Klient, Ip: " + SERVER_IP + ", Port: " + clientPortNumber);
            PrintWriter writer = new PrintWriter(new Socket(SERVER_IP, clientPortNumber).getOutputStream(), true);

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
                        if (cybernetyk) {
                            Cybernetyk cybernetyk = new Cybernetyk();
                            this.HP = cybernetyk.getHP();
                            this.AP = cybernetyk.getAP();
                            this.DAP = cybernetyk.getDAP();
                            this.hack = cybernetyk.getHack();
                            this.lvl = cybernetyk.getLevel();
                            myHP.add(cybernetyk.getHP());
                            //exp3[content.nomOfPlayerss()-1] = cybernetyk.getExp();
                           // content.addPlayaScore(content.getPlayaNum(clientPortNumber), 0);

                            nextLevel=50;
                            content.startCampaign(writer);
                            tura++;

                        }
                        else if (haker) {
                            Haker haker = new Haker();
                            this.HP = haker.getHP();
                            this.AP = haker.getAP();
                            this.DAP = haker.getDAP();
                            this.hack = haker.getHack();
                            this.lvl = haker.getLevel();

                            myHP.add(haker.getHP());
                         //   exp3[content.nomOfPlayerss()-1] = haker.getExp();
                         //   content.addPlayaScore(content.getPlayaNum(clientPortNumber), 0);

                            nextLevel=50;
                            content.startCampaign(writer);
                            tura++;

                        }
                        else if (badass) {
                            BadAss badAss = new BadAss();
                            this.HP = badAss.getHP();
                            this.AP = badAss.getAP();
                            this.DAP = badAss.getDAP();
                            this.hack = badAss.getHack();
                            this.lvl = badAss.getLevel();

                            myHP.add(badAss.getHP());
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

                    if ((messageFromClient = reader.readLine()) != null) {
                        quests.quest0Ans(writer,messageFromClient,tura,quests,content,myHP,myPoints);
                        showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);
                        quests.quest1(writer);
                        tura++;
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

                if(tura ==9) {

//                    if(getPointsFromya(myHP) >100){
//                        myHP.add(content.getPlayaNum(clientPortNumber),100);
//                    }
                    if ((messageFromClient = reader.readLine()) != null) {

                        checkLvl(writer,cybernetyk,haker,badass,clientPortNumber);
                        content.infoBox(writer);

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
                            boolean b=quests.getRandomQuestAns(writer, messageFromClient, cybernetyk, haker, badass,myHP,MAXHP);
                            if(b){
                                myPoints.add(12);
                            }
                            myPoints.add(1);

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
                        pvp.pvp1(writer,messageFromClient,clientPortNumber,content);

                        tura++;
                    }
                }

                if(tura ==12) {
                    int nomOfPotentials=0;
                    if ((messageFromClient = reader.readLine()) != null) {
                        for (int ii = 0; ii < content.nomOfPlayerss(); ii++) {
                            if (messageFromClient.contains(String.valueOf(content.getPlaya(ii)))) {
                                pvp.wyzwijKogos(writer, ii,content,quests,clientPortNumber);

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

                    pvp.konczWalkevoid(content);
                    tura=14;

                }
                if(tura==14){
                    writer.println("Tymczasem ty idziesz dalej w miasto");
                    quests.getRandomQuest(writer, Quests.lokacje);
                    tura++;
                }
                if(tura==15){
                    if ((messageFromClient = reader.readLine()) != null) {
                        boolean b = quests.getRandomQuestAns(writer, messageFromClient, cybernetyk, haker, badass, myHP,MAXHP);
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
            MAXHP+=50;
            myHP.add(50);

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

    public static void loadXMLFile()
    {
        try {
            File file = new File("C:/Users/adven97/Desktop/PAI/LDZ 2137/xml/server_config.xml");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.loadFromXML(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            String key;
            while (enuKeys.hasMoreElements()) {
                switch(key = (String) enuKeys.nextElement())
                {
                    case "port":
                        SERVER_PORT = Integer.parseInt(properties.getProperty(key));
                        break;
                    case "defaultHP":
                        SERVER_IP = properties.getProperty(key);
                         break;

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}