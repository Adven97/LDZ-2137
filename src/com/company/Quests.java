package com.company;

import java.io.PrintWriter;

public class Quests {

    static int nomOfQuests =4;

    void showStats (PrintWriter writer, int hp, int ap, int hac, int dap ) {
        writer.println("------------------------------");
        writer.println("|----"+hp+"----"+ap+"----"+hac+"----"+dap+"----|");
        writer.println("------------------------------");
        writer.println(" ");
    }

    void quest1 (PrintWriter writer){

        writer.println("Spotykasz przed zahirem grupkę pijanych studentów");
        writer.println("Jeden proponuje ci byś zabił jednego z profesorów");
        writer.println("Co robisz?");
        writer.println(" [1] Zgadzam się");
        writer.println(" [2] Nie zgadzam się");
    }
    void quest1Ans(PrintWriter writer,String messageFromClient, int tura, Quests content,int hp, int ap, int hc, int dap){
        if (messageFromClient.equals("1")) {
            writer.println("Zabiłes niewinnego człowieka, zabrał ci 2 HP");
            hp -=2;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest2(writer);
            tura++;
        }
        else  if (messageFromClient.equals("2")) {
            writer.println("nuuuda nic sie nie dzieje");
            //   content.showStats(writer,hp,ap, hc, dap);
            content.quest2(writer);
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

    void quest2 (PrintWriter writer){
        writer.println("Spotykasz gang uciekający z napadu");
        writer.println("Gang składa się z 4 druciarzy i 3 androidów");
        writer.println("Co robisz?");
        writer.println(" [1] Walczę z nimi");
        writer.println(" [2] Uciekam");

    }

    void quest2Ans(PrintWriter writer,String messageFromClient, int tura, Quests content,int hp, int ap, int hc, int dap){
        if (messageFromClient.equals("1")) {
            writer.println("Było ciężko, ale zdołałes ich pokonac, zabrał ci 20 HP");
            hp -=20;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest3(writer);
            tura++;
        }
        else if (messageFromClient.equals("2")) {
            writer.println("nuuuda nic sie nie dzieje");
            //  content.showStats(writer,hp,ap, hc, dap);
            content.quest3(writer);
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

    void quest3 (PrintWriter writer) {
        writer.println("Jesteś świadkiem strzelaniny między policją a gangiem androidów");
        writer.println("Gang składa się z 5 androidów i 3 ludzi pewno to sie zjebie ;)");
        writer.println("Co robisz?");
        writer.println(" [1] Walczę z policją");
        writer.println(" [2] Walczę z gangiem");
        writer.println(" [3] Walczę z walczę z oboma");
        writer.println(" [4] Uciekam");

    }
    void quest3Ans(PrintWriter writer,String messageFromClient, int tura, Quests content,int hp, int ap, int hc, int dap){
        if (messageFromClient.equals("1")) {
            writer.println("Nie było ciężko, zostałeś postrzelony ale zdołałes ich pokonac, zabrali ci 16 HP");
            hp -=16;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest4(writer);
            tura++;
        }
        else if (messageFromClient.equals("2")) {
            writer.println("Zostałeś postrzelony wielokrotnie");
            writer.println("Zdołałes pokonac 2 androidów i 1 człowieka,pozostali uciekli");
            writer.println("W sumie zabrali ci 22 HP");
            hp -=22;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest4(writer);
            tura++;
        }

        else if (messageFromClient.equals("3")) {
            writer.println("Ledwo udało ci się ujść z życiem");
            writer.println("Zdołałes pokonac 2 policjantów, 3 androidów i 1 człowieka,pozostali uciekli");
            writer.println("W sumie zabrali ci 56 HP");
            hp -=56;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest4(writer);
            tura++;
        }
        else if (messageFromClient.equals("4")) {
            writer.println("Z nudów poszedłeś na kebaba");
            writer.println("Wziąłes super mega rollo");
            writer.println("Twoje hp wzrosło o 13");
            hp +=13;
            content.showStats(writer,hp,ap, hc, dap);
            content.quest4(writer);
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

    void quest4 (PrintWriter writer) {
        writer.println("Udałeś się na Aleje Politechniki");
        writer.println("100 lat doszło tu do wybuchu reaktora termojądrowego");
        writer.println("Całą aokolica jest strefą zakazaną, opanowana przez zombie");
        writer.println("Właśnie atakuje cie horda 12 Zombie");
        writer.println("Co robisz?");
        writer.println(" [1] Atakuję");
        writer.println(" [2] Uciekam");


    }
    void quest4Ans(PrintWriter writer,String messageFromClient, Quests content,int hp, int ap, int hc, int dap, boolean b1, boolean b2, boolean b3){
        if (messageFromClient.equals("1")) {
            if(b1) {
                writer.println("Nie było ciężko, lekko cie pokiereszowali");
                hp -= 5;
                content.showStats(writer, hp, ap, hc, dap);
            }
            if(b2) {
                writer.println("Nie było ciężko, ale jeden cie ugryzł w noge");
                hp -= 15;
                content.showStats(writer, hp, ap, hc, dap);
            }
            if(b3) {
                writer.println("Nie było ciężko, Nie odniosłeś żadnych obrażeń");
                //hp -= 5;
                //content.showStats(writer, hp, ap, hc, dap);
            }
        }
        else if (messageFromClient.equals("2")) {
            writer.println("Zostałeś postrzelony wielokrotnie");
            writer.println("Zdołałes pokonac 2 androidów i 1 człowieka,pozostali uciekli");
            writer.println("W sumie zabrali ci 22 HP");
            hp -=22;
            content.showStats(writer,hp,ap, hc, dap);
          //  tura++;
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
