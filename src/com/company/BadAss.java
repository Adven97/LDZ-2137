package com.company;

public class BadAss implements Character {

    int HP;
    int AP;
    int hack;
    int DAP;

    BadAss(){
        HP = 100;
        AP =77;
        hack=45;
        DAP=58;
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

    @Override
    public void setHP(int HP) {
        this.HP= HP;
    }

    @Override
    public void setAP(int AP) {
        this.AP= AP;
    }

    @Override
    public void setHack(int hack) {
        this.hack = hack;
    }

    @Override
    public void setDAP(int DAP) {
        this.DAP= DAP;
    }
}
