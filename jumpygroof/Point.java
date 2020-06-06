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

    public void setPoint_ID(int point_ID) {
        this.point_ID = point_ID;
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

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ArrayList getKangaroos() {
        return kangaroos;
    }

    public void setKangaroos(ArrayList kangaroos) {
       this.kangaroos=kangaroos;
    }
    
    public void setFemale(int female) {
        female++;
        this.female=female;
    }

    public int getFemale() {
        return female;
    }

    public void addKangaroo (Kangaroo kangaroo){
        kangaroo.setCurrent_point(this);
        if (kangaroo.getGender()=='f')
            setFemale(female);
        kangaroos.add(kangaroo);
    }
    
    // the kangaroo at this point will take turn to collect food to it's maximum
    public void collectFood (){
        if (food_available!=0){
            for (int i=0;i<kangaroos.size();i++){
                Kangaroo now = kangaroos.get(i);
                if (food_available<=now.getFood_pouchMax()){
                    // if food avalible is not enough the kangaroo will take what's available
                    now.setFood_pouch(food_available);
                    food_available=0;
                }
                else{
                    now.setFood_pouch(now.getFood_pouchMax());
                    food_available -= now.getFood_pouchMax();
                }                
            }
        }
    }

    public void removeKangaroo(Kangaroo kangaroo) {
        kangaroo.setCurrent_point(this);
        kangaroos.remove(kangaroo);
    }

    
}
