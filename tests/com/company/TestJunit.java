package com.company;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestJunit {

    final static int PORTT = Server.SERVER_PORT;
    static Server testServer=null;
    static ArrayList<PrintWriter> wszyscyGracze = new ArrayList<PrintWriter>();
    static ArrayList<Integer> wszyskiePorty = new ArrayList<Integer>();
    static ArrayList<Integer> scores = new ArrayList<Integer>();
    static ArrayList<Quests> listaQuestow= new ArrayList<Quests>();

    static GameContent gracze = new GameContent(wszyscyGracze, wszyskiePorty,scores,listaQuestow);

//    @Test
//     void serverTest(){
//     //   Socket clientSocket = null;
//        ServerSocket serverSocket=null;
//        Server ser=null;
//        try {
//            serverSocket = new ServerSocket(PORTT);
//         //   while (true) {
//                ser = new Server(serverSocket.accept(), gracze);
//                //serverThread.start();
//           // }
//
//
//        } catch (IOException e) {}
//        assertNotNull(ser);
//    }
    @Test
    void testujSer(){
        try {
            Socket clientSocket = new Socket("localhost", 1009);
            ServerSocket serverSocket = new ServerSocket(PORTT);
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            assertNotNull(clientSocket);
            assertNotNull(serverSocket);
            assertNotNull(writer);
        }
        catch (Exception e){}


    }

    @Test
    void testGracze() {
        assertEquals(gracze.listOfPlayers, wszyscyGracze);
    }

    @Test
    void testPorts() {
        assertEquals(gracze.listOfGamePorts, wszyskiePorty);
    }

    @Test
    void testScores() {
        assertEquals(gracze.listOfScores, scores);
    }

    @Test
    void testQuests() {
        assertEquals(gracze.listOfQuests, listaQuestow);
    }

    @Test
    void getPlaya() {

        gracze.listOfGamePorts.add(0,190);

        assertEquals(java.util.Optional.ofNullable(gracze.listOfGamePorts.get(0)), java.util.Optional.ofNullable(gracze.getPlaya(0)));
    }

    @Test
   void getPlayaNum() {
     assertEquals(java.util.Optional.ofNullable(gracze.listOfGamePorts.indexOf(190)), java.util.Optional.ofNullable(gracze.getPlayaNum(190)));
    }


    @Test
    void nomOfPlayerss() {
        assertEquals(gracze.listOfGamePorts.size(), gracze.nomOfPlayerss());
    }

    @Test
    void addPlaya() {

        try {
            PrintWriter writer = new PrintWriter(new Socket("localhost", 2137).getOutputStream(), true);
            gracze.addPlaya(writer, PORTT, new Quests(), 0);
        }
        catch (IOException e){
            System.out.println("lipaaa  "+ e.getMessage() + " "+ e.getCause());
        }

        assertNotNull(gracze.listOfPlayers.get(0));
        assertNotNull(gracze.listOfQuests.get(0));
        assertNotNull(gracze.listOfGamePorts.get(0));

    }

    @Test
    void getRandomQuestAnsTest(){
        Quests q = new Quests();
        ArrayList<Integer> myHP=new ArrayList<Integer>();
        boolean b1=true;
        boolean b2=false;
        boolean b3=false;
        boolean answer1 =false;
        boolean answer2 =true;
        try {
            PrintWriter writer = new PrintWriter(new Socket("localhost", 2137).getOutputStream(), true);
            gracze.addPlaya(writer, PORTT, new Quests(), 0);
           answer1= q.getRandomQuestAns (writer,"1", b1, b2, b3, myHP,100);

           answer2= q.getRandomQuestAns (writer,"2", b1, b2, b3, myHP,100);
        }
        catch (IOException e){
            System.out.println("lipaaa  "+ e.getMessage() + " "+ e.getCause());
        }
        assertEquals(answer1,true);
        assertEquals(answer2,false);

    }

    @Test

    void countFromAray(){
        Server s =new Server();
      //  Quests q = new Quests();
        ArrayList<Integer> tmp=new ArrayList<Integer>();
        tmp.add(1);
        tmp.add(10);
        tmp.add(19);
        tmp.add(20);

        assertEquals(s.getPointsFromya(tmp),50);
    }


}