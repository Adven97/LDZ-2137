package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

//import static com.company.Server.exp2;
//import static com.company.Server.exp3;
//import static com.company.Server.lvl;
//import static com.company.Server.myPoints;

public class Quests {

  //  static int nomOfQuests =5;
    static int ileLudzi, ileDruciarzy, ileAndroid;
    static String[] lokacje ={"na Piotrkowskiej", "na Wólczańskiej", "na Al. Politechniki", "na Zachodniej", "na Zgierskiej", "na Limance",
            "na Widzewie", "na Radogoszczu","na ulicy 1-go Korpusu Pancernego Wojska Polskiego", "na Zielonej", "na odnowionym Księżnym Młonie",
            "w opuszczonym Parku Poniatowskiego", "w opuszczonej sukcesji", "w postapokaliptycznej Manufakturze", "na 166 piętrze akademika",
            "w zrujnowanym pałacu Shillera", "W Zgierskiej metropolii", "W starej opuszczonej zajezdni tramwajowej", "na nowoczesnym dworcu Łódź Kaliska",
            "na PostBałuckim Runku implantów", "na stadionie Widzewa", "na stadionie ŁKSu", "w Chinatown"};

//
//    void showStats (PrintWriter writer, int hp, int ap, int hac, int dap, GameContent knt ) {
//        writer.println("|---------------------------------------------|");
//        writer.println("|----HP----AP---HACK---DAP----|----exp: "+909+"-----|");
//        writer.println("|---("+hp+")----"+ap+"----"+hac+"----"+dap+"-----|----LEVEL: "+lvl+"---|");
//        writer.println("|---------------------------------------------|");
//        writer.println(" ");
//    }


    void showStats (PrintWriter writer, int hp, int ap, int hac, int dap,int exp,int lvl) {
        writer.println("|---------------------------------------------|");
        writer.println("|----HP----AP---HACK---DAP----|----exp: "+exp+"-----|");
        writer.println("|---("+hp+")----"+ap+"----"+hac+"----"+dap+"-----|----LEVEL: "+lvl+"---|");
        writer.println("|---------------------------------------------|");
        writer.println(" ");
    }



    void quest0Ans(PrintWriter writer,String messageFromClient, int tura, Quests content, GameContent knt,ArrayList<Integer> myHP,ArrayList<Integer> myPoints){

            if (messageFromClient.equals("1")) {
                writer.println("Pokonałes przeciwników, lecz oni zabrali ci 12 HP");
                myHP.add(-12);
                myPoints.add(2);
               // showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);

            }
            else if (messageFromClient.equals("2")) {
                writer.println("nuuuda nic sie nie dzieje");
              //  showStats(writer,getPointsFromya(myHP),AP,hack,DAP,getPointsFromya(myPoints),lvl);

            }
            else if (messageFromClient.equals("exit")) {
                writer.println("Do widzenia");
            }
            else {
                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                tura=4;
            }

    }
    void quest1 (PrintWriter writer){

        writer.println("Spotykasz przed Kebabem grupkę pijanych janyszy");
        writer.println("Jeden proponuje ci byś zabił sąsiada konfidenta i złodzieja");
        writer.println("Co robisz?");
        writer.println(" [1] Zgadzam się");
        writer.println(" [2] Nie zgadzam się");
    }
    void quest1Ans(PrintWriter writer,String messageFromClient, int tura, Quests content, GameContent knt,ArrayList<Integer> myHP,ArrayList<Integer> myPoints){
        if (messageFromClient.equals("1")) {
            writer.println("Zabiłes niewinnego człowieka, zabrał ci 2 HP");
            myHP.add(-2);
          //  knt.exp3[knt.nomOfPlayerss()-1]+=1;
            myPoints.add(2);

           // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            // content.quest2(writer);
            tura++;
        }
        else  if (messageFromClient.equals("2")) {
            writer.println("nuuuda nic sie nie dzieje");
            //   content.showStats(writer,hp,ap, hc, dap);
            //  content.quest2(writer);
            tura++;
        }

        else if (messageFromClient.equals("exit")) {
            writer.println("Do widzenia");
        }
        else {
            writer.println("Polecenie niezrozumiałe, za kare kibole spuszczają ci łomot");
            myHP.add(-8);
        }
    }

    void quest2 (PrintWriter writer){
        writer.println("Spotykasz gang uciekający z napadu");
        writer.println("Gang składa się z 4 druciarzy i 3 androidów");
        writer.println("Co robisz?");
        writer.println(" [1] Walczę z nimi");
        writer.println(" [2] Uciekam");

    }

    void quest2Ans(PrintWriter writer,String messageFromClient, int tura, Quests content, GameContent knt,ArrayList<Integer> myHP,ArrayList<Integer> myPoints){
        if (messageFromClient.equals("1")) {
            writer.println("Było ciężko, ale zdołałes ich pokonac, zabrał ci 20 HP");
            myHP.add(-20);
            myPoints.add(4);
            //content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            // content.quest3(writer);
            tura++;
        }
        else if (messageFromClient.equals("2")) {
            writer.println("nuuuda nic sie nie dzieje");
            tura++;
        }

        else if (messageFromClient.equals("exit")) {
            writer.println("Do widzenia");
        }
        else {
            writer.println("Polecenie niezrozumiałe, za kare kibole spuszczają ci łomot");
            myHP.add(-10);
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
    void quest3Ans(PrintWriter writer,String messageFromClient, int tura, Quests content, GameContent knt,ArrayList<Integer> myHP,ArrayList<Integer> myPoints){
        if (messageFromClient.equals("1")) {
            writer.println("Nie było ciężko, zostałeś postrzelony ale zdołałes ich pokonac, zabrali ci 16 HP");
            myHP.add(-16);
            myPoints.add(4);
          //  content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            // content.quest4(writer);
            tura++;
        }
        else if (messageFromClient.equals("2")) {
            writer.println("Zostałeś postrzelony wielokrotnie");
            writer.println("Zdołałes pokonac 2 androidów i 1 człowieka,pozostali uciekli");
            writer.println("W sumie zabrali ci 22 HP");
            myHP.add(-22);
            myPoints.add(4);
         //   content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            //  content.quest4(writer);
            tura++;
        }

        else if (messageFromClient.equals("3")) {
            writer.println("Ledwo udało ci się ujść z życiem");
            writer.println("Zdołałes pokonac 2 policjantów, 3 androidów i 1 człowieka,pozostali uciekli");
            writer.println("W sumie zabrali ci 56 HP");
            myHP.add(-56);
            myPoints.add(8);
           // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            // content.quest4(writer);
            tura++;
        }
        else if (messageFromClient.equals("4")) {
            writer.println("Z nudów poszedłeś na kebaba");
            writer.println("Wziąłes super mega rollo");
            writer.println("Twoje hp wzrosło o 13");
            myHP.add(13);
         //   content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            // content.quest4(writer);
            tura++;
        }

        else if (messageFromClient.equals("exit")) {
            writer.println("Do widzenia");
        }
        else {
            writer.println("Polecenie niezrozumiałe, za kare kibole spuszczają ci łomot");
            myHP.add(-12);
        }
    }

    void quest4 (PrintWriter writer) {
        writer.println("Udałeś się na Aleje Politechniki");
        writer.println("100 lat doszło tu do wybuchu reaktora termojądrowego");
        writer.println("Cała okolica w promieniu 100 metrów jest strefą zakazaną");
        writer.println("Właśnie atakuje cie horda Zombie");
        writer.println("Co robisz?");
        writer.println(" [1] Atakuję");
        writer.println(" [2] Uciekam");

    }
    void quest4Ans(PrintWriter writer,String messageFromClient, Quests content, boolean b1, boolean b2, boolean b3, GameContent knt,ArrayList<Integer> myHP,ArrayList<Integer> myPoints){
        if (messageFromClient.equals("1")) {
            if(b1) {
                writer.println("Nie było ciężko, lekko cie pokiereszowali");
                writer.println(" -5 HP");
                myHP.add(-5);
                myPoints.add(2);
              //  content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            }
            if(b2) {
                writer.println("Nie było ciężko, ale jeden cie ugryzł w noge");
                writer.println(" -15 HP");
                myHP.add(-15);
              //  knt.exp3[knt.nomOfPlayerss()-1]+=1;
               // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            }
            if(b3) {
                writer.println("Nie było ciężko, Nie odniosłeś żadnych obrażeń, lika BOSS");
               // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                myPoints.add(4);
            }
        }
        else if (messageFromClient.equals("2")) {
            if(b1 || b2) {
                writer.println("Niby udało ci się uciec, ale niestety poślizgnąłeś się i upadłeś na twarz xD");
                writer.println(" -5 HP");
                myHP.add(-5);
              //  content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
            }
            if (b3) {
                writer.println("Uciekając znalazłeś paczke leków");
                writer.println(" +13 HP");
                myHP.add(13);
              //  content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
            }
        }

        else if (messageFromClient.equals("exit")) {
            writer.println("Do widzenia");
        }
        else {
            writer.println("Polecenie niezrozumiałe, za kare kibole spuszczają ci łomot");
            myHP.add(-15);
        }
    }


    void getRandomQuest (PrintWriter writer, String[] lokacje) {

        Random rand = new Random();
        int num = rand.nextInt(lokacje.length);
        if(num == 20 || num==21) {
            ileLudzi = 30;
            ileDruciarzy = 25;
            ileAndroid = 25;
        }
        else{
            //zrobic z 10 jakas zmienna spawn z servera
            ileLudzi = rand.nextInt(10);
            ileDruciarzy = rand.nextInt(10);
            ileAndroid = rand.nextInt(10);
        }

        writer.println("Jesteś "+lokacje[num]);
        writer.println("Właśnie atakuje cie "+ileLudzi+ " złych ziomków, "+ileDruciarzy+" Druciarzy i "+ileAndroid+" Androidów");
        writer.println("Co robisz?");
        writer.println(" [1] Atakuję");
        writer.println(" [2] Uciekam");

    }

    boolean getRandomQuestAns (PrintWriter writer,String messageFromClient, boolean b1, boolean b2, boolean b3, ArrayList<Integer> myHP,int maxx) {
        boolean tem=false;
        int tmax =maxx-100;
        if (messageFromClient.equals("1")) {
            if(b1) {

                writer.println("Druciarze to dla ciebie pestka, gorzej z ludzmi i androidami");
                writer.println(" "+ileZycia(4, 1, 2)+" HP");
                myHP.add(ileZycia(4, 1, 2));
             //   knt.exp3[knt.nomOfPlayerss()-1]+=2;
                //content.addPlayaScore(content.getPlayaNum(String.valueOf(clientPortNumber)), String.valueOf(exp));
                tem=true;
               // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
            }
            if(b2) {
                writer.println("Dobrze rozwaliłes tych andków, z pozostałymi nie było tak łatwo");
                writer.println(" "+ileZycia(4, 2, 1)+" HP");
                myHP.add(ileZycia(4, 2, 1));
              //  knt.exp3[knt.nomOfPlayerss()-1]+=2;
               // content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                tem=true;
            }
            if(b3) {
                writer.println("Poradziłes sobie z ludzmi, niestety z pozostałymi nie było łatwo");
                writer.println(" "+ileZycia(1, 2, 4)+" HP");
                myHP.add(ileZycia(1, 2, 4));
                //knt.exp3[knt.nomOfPlayerss()-1]+=2;
              //  content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                tem=true;
            }
        }
        else if (messageFromClient.equals("2")) {
            Random rand = new Random();
            int num = rand.nextInt(7)+2;
            tem=false;
            if(b1) {
                writer.println("Udało ci sie uciec, po drodze znalazłeś zajefajny implant");
                writer.println(" +"+num+" HP");
                if(getPointsFromyaa(myHP) <=tmax){
                    myHP.add(num);
                    writer.println("masz punktuw: "+getPointsFromyaa(myHP));
                }

              //  content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
            }

            if(b2) {
                writer.println("Udało ci sie uciec, poszedłes do CyberMcDonalda się najeść");
                writer.println(" +"+num+" HP");
                if(getPointsFromyaa(myHP) <tmax){
                    myHP.add(num);
                }
              //  content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
            }
            if (b3) {
                num+=2;
                writer.println("Ty farcie! Uciekając znalazłeś paczke leków");
                writer.println(" +"+num+" HP");
                if(getPointsFromyaa(myHP) < tmax){
                    myHP.add(num);
                }
               // content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
            }
        }


        else if (messageFromClient.equals("exit")) {
            writer.println("Do widzenia");
        }

        else {
            writer.println("Utrata kolejki ");
        }

        return tem;
    }

    void pvpIntro(PrintWriter writer){
        writer.println("Zwyzwałes gracza na pojedynek na kamień, papier, nożyce na MEssEngerZe");
        writer.println("Czekasz na odpowiedź");
    }

    int ileZycia(int ludzie, int druty, int andki){
        int ileZdrowiaZabrali=0;
        for(int i=0; i<ileLudzi; i++){
            ileZdrowiaZabrali+=ludzie;
        }
        for(int i=0; i<ileDruciarzy; i++){
            ileZdrowiaZabrali+=druty;
        }
        for(int i=0; i<ileAndroid; i++){
            ileZdrowiaZabrali+=andki;
        }

        return ileZdrowiaZabrali*(-1);
    }

    int getPointsFromyaa(ArrayList<Integer> a){

        int sum = 0;
        for(int i = 0; i < a.size(); i++)
            sum += a.get(i);
        return sum;
    }
}
