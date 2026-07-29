public class Member5_Demo package demo;

import datastructures.CustomGraph;
import models.Route;

public class Member5_Demo {
    public static void main(String[] args) {
        System.out.println("=== MEMBER 5: GRAPH REPRESENTATION, BFS & DFS DEMO ===\n");

        CustomGraph busNetwork = new CustomGraph();


        busNetwork.addRoute(new Route(1, "Colombo", "Kandy", 115.0));
        busNetwork.addRoute(new Route(2, "Colombo", "Galle", 119.0));
        busNetwork.addRoute(new Route(3, "Kandy", "Jaffna", 260.0));
        busNetwork.addRoute(new Route(4, "Galle", "Matara", 45.0));
        busNetwork.addRoute(new Route(5, "Jaffna", "Colombo", 396.0)); // creates a cycle

        System.out.println("--- Bus Route Network (Adjacency List) ---");
        busNetwork.displayGraph();

        System.out.println("\n--- BFS Traversal from Colombo ---");
        busNetwork.bfs("Colombo").display();

        System.out.println("\n--- DFS Traversal from Colombo ---");
        busNetwork.dfs("Colombo").display();

        System.out.println("\n--- Removing stop: Galle ---");
        busNetwork.removeStop("Galle");
        System.out.println("Routes from Colombo after removal:");
        for (Route r : busNetwork.getRoutesFrom("Colombo")) {
            System.out.println("  " + r);
        }

        System.out.println("\n--- Adding intermediate stop to Colombo -> Kandy route ---");
        Route colomboToKandy = busNetwork.getRoutesFrom("Colombo")
                .linearSearch(r -> r.getDestination().equals("Kandy"));
        if (colomboToKandy != null) {
            colomboToKandy.addStop("Kegalle");
            System.out.println("Intermediate stops on Colombo -> Kandy:");
            colomboToKandy.getIntermediateStops().display();
        }

        System.out.println("\n=== END OF MEMBER 5 DEMO ===");
    }
}

