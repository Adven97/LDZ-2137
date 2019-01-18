package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import static com.company.Server.lvl;

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


        static int nomOfQuests =5;
        static int ileLudzi, ileDruciarzy, ileAndroid;

        static String[] lokacje ={"na Piotrkowskiej", "na Wólczańskiej", "na Al. Politechniki", "na Zachodniej", "na Zgierskiej", "na Limance",
                "na Widzewie", "na Radogoszczu","na ulicy 1-go Korpusu Pancernego Wojska Polskiego", "na Zielonej",
                "w opuszczonym Parku Poniatowskiego", "w opuszczonej sukcesji", "w postapokaliptycznej Manufakturze", "w Akademiku",
                "w zrujnowanym pałacu Shillera", "W Zgierzu", "W starej opuszczonej zajezdni tramwajowej, na Nowoczesnym dworcu Łódź Kaliska",
                "na Post Bałuckim Runku implantów"};


        void showStats (PrintWriter writer, int hp, int ap, int hac, int dap, GameContent knt ) {
            writer.println("|---------------------------------------------|");
            writer.println("|----HP----AP---HACK---DAP----|----exp: "+909+"-----|");
            writer.println("|---("+hp+")----"+ap+"----"+hac+"----"+dap+"-----|----LEVEL: "+lvl+"---|");
            writer.println("|---------------------------------------------|");
            writer.println(" ");
        }

        void quest1 (PrintWriter writer){

            writer.println("Spotykasz przed zahirem grupkę pijanych studentów");
            writer.println("Jeden proponuje ci byś zabił jednego z profesorów");
            writer.println("Co robisz?");
            writer.println(" [1] Zgadzam się");
            writer.println(" [2] Nie zgadzam się");
        }
        void quest1Ans(PrintWriter writer, String messageFromClient, int tura, com.company.Quests content, GameContent knt){
            if (messageFromClient.equals("1")) {
                writer.println("Zabiłes niewinnego człowieka, zabrał ci 2 HP");
                Server.HP -=2;
                //  knt.exp3[knt.nomOfPlayerss()-1]+=1;

                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
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

        void quest2Ans(PrintWriter writer, String messageFromClient, int tura, com.company.Quests content, GameContent knt){
            if (messageFromClient.equals("1")) {
                writer.println("Było ciężko, ale zdołałes ich pokonac, zabrał ci 20 HP");
                Server.HP -=20;
                knt.exp3[knt.nomOfPlayerss()-1]+=2;
                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                // content.quest3(writer);
                tura++;
            }
            else if (messageFromClient.equals("2")) {
                writer.println("nuuuda nic sie nie dzieje");
                //  content.showStats(writer,hp,ap, hc, dap);
                // content.quest3(writer);
                tura++;
            }

            else if (messageFromClient.equals("exit")) {
                writer.println("Do widzenia");
            }
            else {
                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
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
        void quest3Ans(PrintWriter writer, String messageFromClient, int tura, com.company.Quests content, GameContent knt){
            if (messageFromClient.equals("1")) {
                writer.println("Nie było ciężko, zostałeś postrzelony ale zdołałes ich pokonac, zabrali ci 16 HP");
                Server.HP -=16;
                knt.exp3[knt.nomOfPlayerss()-1]+=2;
                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                // content.quest4(writer);
                tura++;
            }
            else if (messageFromClient.equals("2")) {
                writer.println("Zostałeś postrzelony wielokrotnie");
                writer.println("Zdołałes pokonac 2 androidów i 1 człowieka,pozostali uciekli");
                writer.println("W sumie zabrali ci 22 HP");
                Server.HP -=22;
                knt.exp3[knt.nomOfPlayerss()-1]+=2;
                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                //  content.quest4(writer);
                tura++;
            }

            else if (messageFromClient.equals("3")) {
                writer.println("Ledwo udało ci się ujść z życiem");
                writer.println("Zdołałes pokonac 2 policjantów, 3 androidów i 1 człowieka,pozostali uciekli");
                writer.println("W sumie zabrali ci 56 HP");
                Server.HP -=56;
                knt.exp3[knt.nomOfPlayerss()-1]+=4;
                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                // content.quest4(writer);
                tura++;
            }
            else if (messageFromClient.equals("4")) {
                writer.println("Z nudów poszedłeś na kebaba");
                writer.println("Wziąłes super mega rollo");
                writer.println("Twoje hp wzrosło o 13");
                Server.HP +=13;
                content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                // content.quest4(writer);
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
            writer.println("Cała okolica jest strefą zakazaną, opanowana przez zombie");
            writer.println("Właśnie atakuje cie horda 12 Zombie");
            writer.println("Co robisz?");
            writer.println(" [1] Atakuję");
            writer.println(" [2] Uciekam");

        }
        void quest4Ans(PrintWriter writer, String messageFromClient, com.company.Quests content, boolean b1, boolean b2, boolean b3, GameContent knt){
            if (messageFromClient.equals("1")) {
                if(b1) {
                    writer.println("Nie było ciężko, lekko cie pokiereszowali");
                    writer.println(" -5 HP");
                    Server.HP -= 5;
                    knt.exp3[knt.nomOfPlayerss()-1]+=1;
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                }
                if(b2) {
                    writer.println("Nie było ciężko, ale jeden cie ugryzł w noge");
                    writer.println(" -15 HP");
                    Server.HP -= 15;
                    knt.exp3[knt.nomOfPlayerss()-1]+=1;
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                }
                if(b3) {
                    writer.println("Nie było ciężko, Nie odniosłeś żadnych obrażeń, lika BOSS");
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                    knt.exp3[knt.nomOfPlayerss()-1]+=2;
                }
            }
            else if (messageFromClient.equals("2")) {
                if(b1 || b2) {
                    writer.println("Niby udało ci się uciec, ale niestety poślizgnąłeś się i upadłeś na twarz");
                    writer.println(" -6 HP");
                    Server.HP -= 6;
                    content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
                }
                if (b3) {
                    writer.println("Uciekając znalazłeś paczke leków");
                    writer.println(" +15 HP");
                    Server.HP += 15;
                    content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
                }
            }

            else if (messageFromClient.equals("exit")) {
                writer.println("Do widzenia");
            }
            else {
                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
                //content.beginingOfTheGame(writer);
            }
        }


        void getRandomQuest (PrintWriter writer, String[] lokacje) {

            Random rand = new Random();
            int num = rand.nextInt(lokacje.length);
            if(num == 11) {
                ileLudzi = 30;
                ileDruciarzy = 25;
                ileAndroid = 25;
            }
            else{
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

        void getRandomQuestAns (PrintWriter writer, String messageFromClient, com.company.Quests content, boolean b1, boolean b2, boolean b3, GameContent knt) {

            if (messageFromClient.equals("1")) {
                if(b1) {

                    writer.println("Druciarze to dla ciebie pestka, gorzej z ludzmi i androidami");
                    writer.println(" -"+ileZycia(4, 1, 2)+" HP");
                    Server.HP -= ileZycia(4, 1, 2);
                    knt.exp3[knt.nomOfPlayerss()-1]+=2;
                    //content.addPlayaScore(content.getPlayaNum(String.valueOf(clientPortNumber)), String.valueOf(exp));
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                }
                if(b2) {
                    writer.println("Dobrze rozwaliłes tych andków, z pozostałymi nie było tak łatwo");
                    writer.println(" -"+ileZycia(4, 2, 1)+" HP");
                    Server.HP -= ileZycia(4, 2, 1);
                    knt.exp3[knt.nomOfPlayerss()-1]+=2;
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                }
                if(b3) {
                    writer.println("Poradziłes sobie z ludzmi, niestety z pozostałymi nie było łatwo");
                    writer.println(" -"+ileZycia(1, 2, 4)+" HP");
                    Server.HP -= ileZycia(1, 2, 4);
                    knt.exp3[knt.nomOfPlayerss()-1]+=2;
                    content.showStats(writer,Server.HP,Server.AP, Server.hack, Server.DAP,knt);
                }
            }
            else if (messageFromClient.equals("2")) {
                Random rand = new Random();
                int num = rand.nextInt(7)+2;

                if(b1) {
                    writer.println("Udało ci sie uciec, po drodze znalazłeś zajefajny implant");
                    writer.println(" +"+num+" HP");
                    Server.HP += num;
                    content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
                }

                if(b2) {
                    writer.println("Udało ci sie uciec, poszedłes do CyberMcDonalda się najeść");
                    writer.println(" +"+num+" HP");
                    Server.HP += num;
                    content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
                }
                if (b3) {
                    num+=2;
                    writer.println("Ty farcie! Uciekając znalazłeś paczke leków");
                    writer.println(" +"+num+" HP");
                    Server.HP += num;
                    content.showStats(writer, Server.HP, Server.AP, Server.hack, Server.DAP,knt);
                }
            }

            else if (messageFromClient.equals("q")) {
                writer.println("Tu bedzie ranking");
            }

            else if (messageFromClient.equals("exit")) {
                writer.println("Do widzenia");
            }

            else {
                writer.println("Polecenie niezrozumiałe, spróbuj ponownie");
            }

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

            return ileZdrowiaZabrali;
        }


    }



