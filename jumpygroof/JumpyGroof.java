/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dina
 */
public class JumpyGroof {
    private Graph Map;
    private ArrayList <Point> point_current;
    private ArrayList <Kangaroo> Kangaroo;
    private boolean ColonyFormed = false;
    private int colony;

    public JumpyGroof(Graph Map, ArrayList<Point> point_current, ArrayList<Kangaroo> Kangaroo, int colony) {
        this.Map = Map;
        this.point_current = point_current;
        this.Kangaroo = Kangaroo;
        this.colony=colony;
        Move();
    }
    
    // Method to move the kangaroos turn by turn
    private void Move (){
        CollectFood();
        
    }
   
    
    // All kangaroo including female start by collecting food into their pouch
    public void CollectFood (){
        for (int i=0;i<point_current.size();i++){
            Point current = point_current.get(i);
            if (current.getfood_available()!=0){
                for (int j=0;j<current.getKangaroos().size();j++){
                    Kangaroo k = (Kangaroo) current.getKangaroos().get(j);
                    if (current.getfood_available()<=k.getFood_pouchMax()){
                        // if food avalible is not enough the kangaroo will take what's available
                        k.setFood_pouch(current.getfood_available());
                        current.setfood_available(0);
                }
                else{
                        k.setFood_pouch(k.getFood_pouchMax());
                        int temp=current.getfood_available()-k.getFood_pouch();
                        current.setfood_available(temp);
                    }
                }                
            }
        }
    }
    
    // count the food need 
    public int FoodNeed(Kangaroo kangaroo,Point next) {
        int height = Map.getHeight(kangaroo.getCurrent_point(), next);
        return height+(kangaroo.getFood_pouch()/2);
    }

    // choose the next point
    public Point NextPoint(Kangaroo kangaroo,Point current){
        // create a list of nodes
        ArrayList <Point> next = Map.getAdjacent(current);
        int[] Point=new int [next.size()];
        Point Next=null;
        if (!next.isEmpty()){
            // i = current nodes
            for (int i=0;i<next.size();i++){
                // the greater the point the most potential the nodes is
                Point[i]=current.getfood_available()+current.getFemale()+(kangaroo.getFood_pouch()-FoodNeed(kangaroo,current));
            }
            // bubble sort. The highest will be the most point and the next point to travel
            for ( int pass = 1; pass < Point.length; pass++ ) {
         	// control number of comparison
		for ( int i = 0; i < Point.length -pass; i++ ) {
                    if ( Point[ i ] > Point[ i + 1 ] )  {
                        Next=next.get(i);
                    }
                }
            }            
        }
        return Next;
    }
    
    //
}
