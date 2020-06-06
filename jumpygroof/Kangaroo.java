/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;

import java.util.ArrayList;

/**
 *
 * @author Dina
 */
public class Kangaroo {
    private int no_kangaroo;
    private ArrayList kangaroo;
    private char gender;
    private int food_pouch;
    private int food_pouchMax;
    private Point current_point;

    public Kangaroo(char gender, int food_pouch) {
        no_kangaroo++;
        kangaroo = new ArrayList (no_kangaroo);
        this.gender = gender;
        this.food_pouchMax = food_pouch;
    }

    public int getNo_kangaroo() {
        return no_kangaroo;
    }

    public void setNo_kangaroo(int no_kangaroo) {
        this.no_kangaroo = no_kangaroo;
    }

    public ArrayList getKangaroo() {
        return kangaroo;
    }

    public void setKangaroo(ArrayList kangaroo) {
        this.kangaroo = kangaroo;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getFood_pouchMax() {
        return food_pouchMax;
    }

    public void setFood_pouchMax(int food_pouchMax) {
        this.food_pouchMax = food_pouchMax;
    }
    
    public int getFood_pouch() {
        return food_pouch;
    }

    public void setFood_pouch(int food_pouch) {
        this.food_pouch = food_pouch;
    }
    
    public Point getCurrent_point() {
        return current_point;
    }

    public void setCurrent_point(Point current_point) {
        this.current_point = current_point;
    }
    
    @Override
    public String toString() {
        return "Kangaroo{" + kangaroo + '}';
    }


}
