package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;

public class PvPhandler {

    void pvp1(PrintWriter writer, String messageFromClient, int clientPortNumber,  GameContent content){


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

    }

    void wyzwijKogos(PrintWriter writer, String messageFromClient, int ii,  GameContent content, Quests quests, int clientPortNumber){
        content.pvpGracze.add(1,content.getPlaya(ii));
        quests.pvpIntro(writer);
        content.listOfPlayers.get(ii).println("Ktos na porcie "+clientPortNumber+ " chce sie bić w stylu oldsqlowym");
        content.listOfPlayers.get(ii).println("Wybierz broń");
        content.listOfPlayers.get(ii).println("[z] - kamień");
        content.listOfPlayers.get(ii).println("[x] - papier");
        content.listOfPlayers.get(ii).println("[c] - nożyce");
    }

    void konczWalkevoid(GameContent content){


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
            //tura=14;
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
        //    tura=14;
        }

        ///playa 2 wygrywa
        else if((playa2==1 && playa1==3) ||(playa2==2 && playa1==1) || (playa2==3 && playa1==2) ){

            content.listOfPlayers.get(inegoPort).println(" PRZegrałEŚ ");
            content.listOfPlayers.get(inegoPort).println("stracies 10 exp ");

            content.listOfPlayers.get(twojPort).println(" WYGRAłeś ");
            content.listOfPlayers.get(twojPort).println("Zdobyles 25 exp");

            content.playersListOfskors.get(twojPort).add(25);
            content.playersListOfskors.get(inegoPort).add(-10);
          //  tura=14;
        }
    }
}
