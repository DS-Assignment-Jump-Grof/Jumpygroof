/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jumpygroof;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Dina
 */
public class JMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph Map = new Graph ();
        ArrayList <Point> point = new ArrayList <> ();
        ArrayList <Kangaroo> kangaroo = new ArrayList <> ();
        boolean ColonyFormed = false;
        
        // first input information of the map
        System.out.println("How many points are there: ");
        Scanner s = new Scanner (System.in);
        Random r = new Random ();
        int total_Point=s.nextInt();
        int food[]= new int[total_Point]; 
        int limit []= new int [total_Point];
        
        // create random map
        for(int i =0;i<total_Point;i++){
            food[i]=r.nextInt(15)+10;
            limit[i]=r.nextInt(10)+5;
            point.add(new Point(i+1, food[i],limit[i]));
            Map.addVertice(point.get(i));
        }
        
        // add the path 
        for (int i =0;i<total_Point;i++){
            System.out.println("Point "+(i+1)+" Food avalaible : "+food[i]+" Limit : "+limit[i]);
            System.out.print("Number of path connected: ");
            int m = s.nextInt();
            for (int j =0;j<m;j++){
                System.out.print("Next: ");
                int next = s.nextInt();
                System.out.print("Height: ");
                int height= s.nextInt();
                Map.addEdge(point.get(i), point.get(next-1), height);
            }
        }
        
        // second input information of each kangaroos
        System.out.println("Enter how many kangaroos are there: ");
        int total_Kangaroo = s.nextInt();
        for (int i =0;i<total_Kangaroo;i++){
            System.out.println("Kangaroo "+(i+1)+"\nStarting point : ");
            int start = s.nextInt()-1;
            System.out.print("Gender: ");
            char gender = s.next().toLowerCase().charAt(0);
            System.out.print("Max food can be stored: ");
            int max = s.nextInt();
            kangaroo.add(new Kangaroo(gender,max));
            point.get(start).addKangaroo(kangaroo.get(i));
        }
        
        System.out.print("Number of kangaroos to form colony : ");
        int colony= s.nextInt();
        
        // begin simulation
        JumpyGroof j= new JumpyGroof (Map,point,kangaroo,colony);
        j.simulation();
    }  
}
