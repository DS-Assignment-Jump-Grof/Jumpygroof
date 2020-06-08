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
    private int colony_formed=0;

    public JumpyGroof(Graph Map, ArrayList<Point> pointlist, ArrayList<Kangaroo> kangaroolist, int colony) {
        this.Map = Map;
        this.pointlist = pointlist;
        this.kangaroolist = kangaroolist;
        this.colony=colony;
    }

    
    public void simulation (){
        CollectFood(); 
        
        // create a recursion until all kangaroo cant move
        
        for (int i = 0; i < kangaroolist.size(); i++) {
            Kangaroo current= kangaroolist.get(i);
            if (!current.canMove()) 
                break;
            Move();
        }
        
        System.out.println("Number of colonies: "+colony_formed);
        int remain=0;
        for (int i = 0; i < kangaroolist.size(); i++) {
            Kangaroo current= kangaroolist.get(i);
            if (!current.canMove()) 
                break;
            remain++;
            System.out.println("number of remaining kangaroos : "+remain);
            System.out.println((i+1)+" "+current.getGender()+" "+current.getFood_pouch());
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
                    
                    // change the food information at next point
                    setFood(k,next);
                    // set kangaroo new location
                    k.setCurrent_point(next);
                    // change the kangaroo information at point
                    previous.removeKangaroo(k);
                    next.addKangaroo(k);
                    if (next.colonyFormed(colony)){
                        System.out.println("Point " + next.getPoint_ID() + " formed a colony");
                        colony_formed++;
                    }
                    System.out.println("Kangaroo "+(i+1)+" moved from "+previous.getPoint_ID()+" to "+next.getPoint_ID());
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
        return next.getfood_available()-FoodNeed(kangaroo,next);
    }
    
    private void setFood (Kangaroo kangaroo, Point next){
        // food left at that point
        int foodleft=foodleft(kangaroo,next);
        if (foodleft<=0){
            // if food left is not enough for the kangaroo to restore energy, it will cost food pouch
            kangaroo.setFood_pouch(kangaroo.getFood_pouch()+foodleft(kangaroo,next));
            next.setfood_available(0);
        }
        else{
            int extra = kangaroo.getFood_pouchMax()-kangaroo.getFood_pouch();
            int pouch=kangaroo.getFood_pouch();
            if (foodleft>=extra){
                // if food left has more it will take the extra food to its max.
                foodleft-=extra;
                pouch=kangaroo.getFood_pouchMax();
            }
            else{
                // if food left is not enough to full the max it wil take whats left
                pouch = kangaroo.getFood_pouch()+foodleft;
                foodleft=0;
            } 
        }
        next.setfood_available(foodleft);
    }
    
    // if the kangaroo pass the requirement to jump the next node
    // food need must enough to jump & the point has enough food for the kangaroo to restore energy
    public boolean canJump(Kangaroo kangaroo,Point next){
        return kangaroo.getFood_pouch()>=FoodNeed(kangaroo,next);
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
                }
            }
            if (Next.colonyFormed(colony)){
                if(colony(kangaroo,Next)==null){
                    // recursion to get new node
                    
                }
            }            
        } 
        return Next;
    }
    
    public Point colony (Kangaroo kangaroo,Point next){
        // it will be forced to join the colony if it is carrying food enough for
        // everyone as a gift and the limit of the point is not reached
        if((FoodNeed(kangaroo,next)==next.getKangaroos().size())&& !next.isFull()){
        // if the kangaroo can join the colony return point
        kangaroo.setMove(false);
            return next;
        }
        // if the kangaroo cant join the colony it will generate point again
        else {
            return null;
        }
    }

}
