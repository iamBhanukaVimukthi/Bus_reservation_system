import java.util.ArrayList;
import java.util.Scanner;


class Bus {

    int route;
    String source;
    String destination;
    double distance;

    Bus left;
    Bus right;


    public Bus(int route, String source, String destination, double distance) {

        this.route = route;
        this.source = source;
        this.destination = destination;
        this.distance = distance;

        left = null;
        right = null;
    }
}

class BST {
    Bus root;

    public void insert(int route, String source, String destination, double distance) {
        root = insertRec(root, route, source, destination, distance);

    }

    private Bus insertRec(Bus root, int route, String source, String destination, double distance) {

        if(root == null) {
            return new Bus(route, source, destination, distance);

        }
        if(route < root.route) {
            root.left = insertRec(root.left, route, source, destination, distance);

        }
        else if(route > root.route) {
            root.right = insertRec(root.right, route, source, destination, distance);
        }
        return root;
    }

    public Bus search(Bus root, int route) {

        if(root == null || root.route == route) {
            return root;
        }
        if(route < root.route) {
            return search(root.left, route);
        }
        else {
            return search(root.right, route);
        }
    }

    public void inorder(Bus root) {

        if(root != null) {
            inorder(root.left);

            System.out.println("Route      : " + root.route);
            System.out.println("Source      : " + root.source);
            System.out.println("Destination : " + root.destination);
            System.out.println("Distance    : " + root.distance);
            System.out.println("-----------------------------");

            inorder(root.right);

        }
    }

    public void store(Bus root, ArrayList<Bus> list) {

        if(root != null) {

            store(root.left, list);
            list.add(root);
            store(root.right, list);

        }
    }
}

class SelectionSort {

    public static void sortByFare(ArrayList<Bus> buses) {
        int n = buses.size();

        for(int i = 0; i < n-1; i++) {
            int min = i;

            for(int j = i+1; j < n; j++) {

                if(buses.get(j).distance < buses.get(min).distance) {
                    min = j;

                }
            }
            Bus temp = buses.get(i);
            buses.set(i, buses.get(min));
            buses.set(min, temp);
        }
    }
}

public class BusReservationSystem {

    public static void main(String[] args) {

        BST tree = new BST();
        Scanner sc = new Scanner(System.in);

        tree.insert(105, "Colombo", "Colombo", 1200);
        tree.insert(102, "Colombo", "Kandy", 900);
        tree.insert(110, "Colombo", "Galle", 1500);
        tree.insert(101, "Colombo", "Matara", 800);
        tree.insert(108, "Colombo", "Jaffna", 1300);

        int choice;

        do {

            System.out.println("\n===== BUS RESERVATION SYSTEM =====");
            System.out.println("1. Display All Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Sort Buses By Fare");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    System.out.println("\n===== ALL BUS DETAILS =====");

                    tree.inorder(tree.root);

                    break;

                case 2:
                    System.out.print("\nEnter Bus ID to Search: ");

                    int id = sc.nextInt();
                    Bus result = tree.search(tree.root, id);

                    if(result != null) {
                        System.out.println("\n===== BUS FOUND =====");
                        System.out.println("Route       : " + result.route);
                        System.out.println("Source      : " + result.source);
                        System.out.println("Destination : " + result.destination);
                        System.out.println("Distance    : " + result.distance);


                    }
                    else {
                        System.out.println("Bus Not Found!");

                    }
                    break;

                case 3:
                    ArrayList<Bus> list = new ArrayList<>();
                    tree.store(tree.root, list);
                    SelectionSort.sortByFare(list);

                    System.out.println("\n===== SORTED BY FARE =====");

                    for(Bus b : list) {

                        System.out.println(

                                b.route + " | " +

                                        b.source + " | " +

                                        b.destination +

                                        " | Distance: " + b.distance

                        );

                    }
                    break;

                case 4:
                    System.out.println("System Closed.");

                    break;

                default:
                    System.out.println("Invalid Choice!");

            }
        }while(choice != 4);
        sc.close();
    }
}
