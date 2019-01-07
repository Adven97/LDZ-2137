package com.company;

public class Haker implements Character {

    int HP;
    int AP;
    int hack;
    int DAP;

    int lvl;
    int exp;

    Haker(){
        HP = 100;
        AP =37;
        hack=79;
        DAP=55;

        lvl=1;
        exp=0;
    }


    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public int getAP() {
        return AP;
    }

    @Override
    public int getHack() {
        return hack;
    }

    @Override
    public int getDAP() {
        return DAP;
    }

    public int getLevel() {
        return lvl;
    }

    public int getExp() {
        return exp;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void setAP(int AP) {
        this.AP = AP;
    }

    @Override
    public void setHack(int hack) {
        this.hack=hack;
    }

    @Override
    public void setDAP(int DAP) {
        this.DAP = DAP;
    }

    public void setLevel(int lvl) {
        this.lvl = lvl;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
