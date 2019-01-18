package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

//import static com.company.Server.lvl;

public class GameContent {

    //ArrayList<String> listOfQuests = new ArrayList<String>();
    ArrayList<PrintWriter> listOfPlayers = null;
    ArrayList<Integer> listOfGamePorts = null;
    ArrayList<Integer> listOfScores = null;
    ArrayList<Quests> listOfQuests= null;
    ArrayList<ArrayList<Integer>> playersListOfskors = new ArrayList<ArrayList<Integer>>();
    ArrayList<String> attacerWeapons = new ArrayList<String>();
    ArrayList<String> defendWeapons = new ArrayList<String>();

    ArrayList<Integer> pvpGracze = new ArrayList<Integer>();
   // ArrayList<String> weapons = new ArrayList<String>();
    int[] exp3;

    ArrayList<ArrayList<Integer>> clientsAppointment = new ArrayList<ArrayList<Integer>>();
    int options;

    GameContent(ArrayList<PrintWriter> listOfPlayers, ArrayList<Integer> listOfGamePorts, ArrayList<Integer> listOfScores,
                ArrayList<Quests> listOfQuests) {

        this.listOfPlayers = listOfPlayers;
        this.listOfGamePorts = listOfGamePorts;
        this.listOfScores = listOfScores;
        this.listOfQuests = listOfQuests;
        this.options =1;
        this.exp3 = new int[20];
    }

    int getPointsFromya(ArrayList<Integer> a){

        int sum = 0;
        for(int i = 1; i < a.size(); i++)
            sum += a.get(i);
        return sum;
    }


    int getPlaya(int i) {
        return listOfGamePorts.get(i);
    }

    int getPlayaNum(int port) {
        return listOfGamePorts.indexOf(port);
    }

    int getPlayaScore(int i) {
        return listOfScores.get(i);
    }

//    void setPlayaScore(int i, String st) {
//        listOfScores.set(i,st);
//    }

    void addPlayaScore(int i,int st) {
        listOfScores.add(i,st);
    }

    int nomOfPlayerss(){
        return listOfGamePorts.size();
    }

    void addPlaya(PrintWriter writer, int port, Quests q, int index) {
        listOfPlayers.add(writer);
        listOfQuests.add(q);
        listOfGamePorts.add(port);
        listOfScores.add(exp3[index]);
    }

    void showRanking(PrintWriter writer) {
        for (int i = 0; i < listOfPlayers.size(); i++) {
            writer.println((i+1)+" - "+listOfPlayers.get(i));
        }
    }



    void beginingOfTheGame (PrintWriter writer) {
        writer.println("----------------WITAJ W GRZE ŁóDź 2137----------------");
        writer.println("Wybierz klasę postaci");
        writer.println(" [1] Cybernetyk");
        writer.println(" [2] Haker");
        writer.println(" [3] BadAss");
    }

    void showPlot(PrintWriter writer){
        writer.println("Jest rok 2137");
        writer.println("Przestępczość w Łodzi osiągnęła poziom krytyczny");
        writer.println("Na ulicach rządzą gangi ludzi, druciarzy i androidów");
        writer.println("Policja nie jest w stanie zrobić nic by ich powstrzymać");
        writer.println("Prezydent miasta w desperacji postanawia sprowadzić kogoś");
        writer.println("Kogoś kto będzie w stanie powstrzymać szerzącą się przestępczość");
        writer.println("Tym ratunkiem jesteś właśnie TY!");
        writer.println("-------Wciśnij 1 aby kontynuować--------");
    }


    void cybernetykBackstory(PrintWriter writer){
        writer.println("Jesteś cybernetykiem, mieszkasz w Łodzi całe swoje życie");
        writer.println("Ta misja to dla ciebie sprawa osobista");
        writer.println("Dzięki wielu implantom zabijanie podobnych do ciebie druciarzy to łatwizna");
        writer.println("-------Wciśnij 1 aby kontynuować--------");
    }

    void hakerBackstory(PrintWriter writer){
        writer.println("Jesteś wybitnym hakerem z Tokjo, twoja specjalizacja to zabijanie androidów");
        writer.println("10 lat temu, android zamordował twoją ukochaną");
        writer.println("Od tamtej pory robisz wszystko by unicestwić wszystkie androidy z powierzchni Ziemi");
        writer.println("-------Wciśnij 1 aby kontynuować--------");
    }

    void badassBackstory(PrintWriter writer){
        writer.println("Jesteś płatnym najemnikiem z hAmeryki, zabijanie to dla ciebie nie tylko praca, ale też i świetna zabawa");
        writer.println("Twoja specjalizacja to ludzie");
        writer.println("-------Wciśnij 1 aby kontynuować--------");

    }

    void startCampaign(PrintWriter writer, int godz){
        options=2;
        writer.println("Piątek, godzina "+godz+", poszedłeś na patrol na Piotrkowską");
        writer.println("Widzisz 5 osobowy gang składający się z ludzi");
        writer.println("Próbują sprzedać heroinę 12 latkowi");
        writer.println("Co robisz?");
        writer.println(" [1] Podchodzę i walczę");
        writer.println(" [2] Omijam i idę dalej");
    }

    }



