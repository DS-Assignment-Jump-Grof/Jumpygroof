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
public class JumpyGroof {
    
    private Graph Map;
    private ArrayList <Point> pointlist;
    private ArrayList <Kangaroo> kangaroolist;
    private int colony;

    public JumpyGroof(Graph Map, ArrayList<Point> pointlist, ArrayList<Kangaroo> kangaroolist, int colony) {
        this.Map = Map;
        this.pointlist = pointlist;
        this.kangaroolist = kangaroolist;
        this.colony=colony;
    }

    
    public void simulation (){
        CollectFood(); 
        for (int i = 0; i < kangaroolist.size(); i++) {
            Kangaroo currentKangaroo = kangaroolist.get(i);
            System.out.println("Kangaroo " + (i+1) + 
                    " at Point " + currentKangaroo.getCurrent_point().getPoint_ID() +
                    " with food in pouch " + currentKangaroo.getFood_pouch());
        }
        for (int i = 0; i < kangaroolist.size(); i++) {
            Kangaroo current= kangaroolist.get(i);
            if (!current.canMove()) 
                break;
            Move();
        }
    }
    
    // Method to move the kangaroos turn by turn
    private void Move (){
        for (int i =0;i<kangaroolist.size();i++){
            Kangaroo k = kangaroolist.get(i);
            if (k.canMove()){
                Point previous = k.getCurrent_point();
                Point next =NextPoint(k,k.getCurrent_point());
                if (next!=null){
                    System.out.println("Kangaroo " +(i+1)+ " is moving from point " + previous.getPoint_ID() + " to point " + next.getPoint_ID());
                    // set kangaroo new location
                    k.setCurrent_point(next);
                    // change the kangaroo information at point
                    previous.removeKangaroo(k);
                    next.addKangaroo(k);
                    // change the food information at next point
                    int next_food=foodleft(k,next);
                    next.setfood_available(next_food);
                    if (next.colonyFormed(colony)){
                        System.out.println("Point " + next.getPoint_ID() + " has formed a colony");
                    }
                }
                else{
                    k.setMove(false);
                }
            }
            else{
                k.setMove(false);
            }
        }
    }
   
    
    // All kangaroo including female start by collecting food into their pouch
    public void CollectFood (){
        for (int i=0;i<pointlist.size();i++){
            Point current = pointlist.get(i);
            if (current.getfood_available()!=0){
                for (int j=0;j<current.getKangaroos().size();j++){
                    Kangaroo k = current.getKangaroos().get(j);
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
            System.out.println("Point " + current.getPoint_ID() + " : " + current.getfood_available());
        }
    }
    
    // count the food need 
    public int FoodNeed(Kangaroo kangaroo,Point next) {
        int height = Map.getHeight(kangaroo.getCurrent_point(), next);
        return height+(kangaroo.getFood_pouch()/2);
    }
    
    
    // food left at the point after the kangaroo restore 
    public int foodleft(Kangaroo kangaroo,Point next){
        int left=next.getfood_available()-FoodNeed(kangaroo,next);
        System.out.println(left);
        if (left<=0){
            left=0;
        }
        return left;
    }
    
    // if the kangaroo pass the requirement to jump the next node
    // food need must enough to jump & the point has enough food for the kangaroo to restore energy
    public boolean canJump(Kangaroo kangaroo,Point next){
        return kangaroo.getFood_pouch()>=FoodNeed(kangaroo,next);
    }

    // it will be forced to join the colony if it is carrying food enough for
    // everyone as a gift and the limit of the point is not reached
    public boolean joinColony (Kangaroo kangaroo,Point next){
        return (FoodNeed(kangaroo,next)==next.getKangaroos().size())&&(!next.isFull());
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
                Next=next.get(i);
                if (canJump(kangaroo,Next)&&(!Next.isFull())){
                    Point[i]=current.getfood_available()+current.getFemale()+foodleft(kangaroo,Next);
                }
                else{
                    Point[i]=0;
                }
            }
            // bubble sort. The highest will be the most point and the next point to travel
            for ( int pass = 1; pass < Point.length; pass++ ) {
         	// control number of comparison
		for ( int i = 0; i < Point.length -pass; i++ ) {
                    if ( Point[ i ] > Point[ i + 1 ] )  {
                        Next=next.get(i);    
                    }
                    if (Next.colonyFormed(colony)){
                        if (joinColony(kangaroo,Next)){
                            kangaroo.setMove(false);
                             // set kangaroo new location
                            kangaroo.setCurrent_point(Next);
                            // change the kangaroo information at point
                            current.removeKangaroo(kangaroo);
                            Next.addKangaroo(kangaroo);
                            // give food to everyone as a gift 
                            
                            break;
                        }
                    }
                }
            }            
        }
        return Next;
    }
    
    
}
