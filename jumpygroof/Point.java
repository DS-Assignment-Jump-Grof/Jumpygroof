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
public class Point {
    private int point_ID;
    private int food_available;
    private int limit;
    private int female;
    private ArrayList <Kangaroo> kangaroos;

    public Point(int point_ID, int food_available, int limit) {
        this.point_ID = point_ID;
        this.food_available = food_available;
        this.limit = limit;
        kangaroos = new ArrayList();
        female=0;
    }

    public int getPoint_ID() {
        return point_ID;
    }

    public int getfood_available() {
        return food_available;
    }

    public void setfood_available(int food_available) {
        this.food_available = food_available;
    }

    public boolean isFull() {
        return kangaroos.size()==limit;
    }
    
    public boolean colonyFormed (int colony){
        return kangaroos.size()==colony;
    }

    public void setFemale(int female) {
        female++;
        this.female=female;
    }

    public int getFemale() {
        return female;
    }

    public ArrayList<Kangaroo> getKangaroos() {
        return kangaroos;
    }

    public void addKangaroo (Kangaroo kangaroo){
        kangaroo.setCurrent_point(this);
        if (kangaroo.getGender()=='f')
            setFemale(female);
        kangaroos.add(kangaroo);
    }
    
    public void removeKangaroo(Kangaroo kangaroo) {
        kangaroo.setCurrent_point(this);
        kangaroos.remove(kangaroo);
    }
}
