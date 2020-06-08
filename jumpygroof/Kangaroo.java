/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;


/**
 *
 * @author Dina
 */
public class Kangaroo {
    private char gender;
    private int food_pouch=0;
    private int food_pouchMax;
    private boolean move;
    private Point current_point;

    public Kangaroo(char gender, int food_pouchMax) {
        this.gender = gender;
        this.food_pouchMax = food_pouchMax;
        move=true;
    }

    public char getGender() {
        return gender;
    }
    
    public int getFood_pouchMax() {
        return food_pouchMax;
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

    public boolean canMove() {
        return move&&getGender()=='m';
    }

    public void setMove(boolean move) {
        this.move = move;
    }

}
