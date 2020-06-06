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
public class JMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph Map = new Graph ();
        ArrayList <Point> point_ID = new ArrayList <> ();
        ArrayList <Kangaroo> Kangaroo = new ArrayList <> ();
        boolean ColonyFormed = false;
        
        // describing map
        System.out.println("How many points are there: ");
        Scanner s = new Scanner (System.in);
        int total_Point=s.nextInt();
        for(int i =1;i<=total_Point;i++){
            Map.addVertice(i);
        }
        for (int i =1;i<=total_Point;i++){
            System.out.println("Point: "+i);
            System.out.print("Food available: ");
            int f =s.nextInt();
            System.out.print("Limit: ");
            int l = s.nextInt();
            System.out.print("Number of path connected: ");
            int m = s.nextInt();
            for (int j =1;j<=m;j++){
                System.out.println("Next: ");
                int next = s.nextInt();
                System.out.println("Height: ");
                int height= s.nextInt();
                Map.addEdge(i, next, height); 
            }
            point_ID.add(new Point(i,f,l));
        }
        
        // second input information of each kangaroos
        System.out.println("Enter how many kangaroos are there: ");
        int total_Kangaroo = s.nextInt();
        for (int i=1; i<=total_Kangaroo;i++){
            System.out.println("Kangaroo "+i);
            System.out.println("Starting point ");
            int location = s.nextInt();
            System.out.println("Amount of food can be stored in the pouch: ");
            int food = s.nextInt();
            System.out.println("The gender: ");
            char gender = s.next().toUpperCase().charAt(0);
            Kangaroo.add(new Kangaroo(gender,food));
            // update map info
            point_ID.get(location-1).addKangaroo(Kangaroo.get(i));
        }
        
        //Enter the amount max to form colony
        System.out.println("Enter the number of kangaroo to form colony: ");
        int colony = s.nextInt();
        
        // begin simulation
        JumpyGroof simulation = new JumpyGroof (Map,point_ID,Kangaroo,colony);
        
    }  
}
