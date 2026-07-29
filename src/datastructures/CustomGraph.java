package datastructures;


public class CustomGraph {

    // Maps each stop name to the CustomLinkedList of routes departing from it
    private CustomHashTable<String, CustomLinkedList<Route>> adjacencyTable;


    private CustomLinkedList<String> vertices;

    public CustomGraph() {
        adjacencyTable = new CustomHashTable<>();
        vertices = new CustomLinkedList<>();
    }


    public void addStop(String stopName) {
        if (!adjacencyTable.containsKey(stopName)) {
            adjacencyTable.put(stopName, new CustomLinkedList<>());
            vertices.add(stopName);
        }
    }


    public void addRoute(Route route) {
        if (route == null) {
            throw new IllegalArgumentException("Route cannot be null.");
        }
        addStop(route.getSource());
        addStop(route.getDestination());
        adjacencyTable.get(route.getSource()).add(route);
    }


    public void removeRoute(String source, String destination) {
        removeRoutesTo(adjacencyTable.get(source), destination);
    }


    public void removeStop(String stopName) {
        adjacencyTable.remove(stopName);
        vertices.remove(stopName);
        for (String stop : vertices) {
            removeRoutesTo(adjacencyTable.get(stop), stopName);
        }
    }

    private void removeRoutesTo(CustomLinkedList<Route> routes, String stopName) {
        if (routes == null || routes.isEmpty()) {
            return;
        }
        CustomLinkedList<Route> toRemove = new CustomLinkedList<>();
        for (Route r : routes) {
            if (r.getDestination().equals(stopName)) {
                toRemove.add(r);
            }
        }
        for (Route r : toRemove) {
            routes.remove(r);
        }
    }


    public CustomLinkedList<Route> getRoutesFrom(String stop) {
        CustomLinkedList<Route> routes = adjacencyTable.get(stop);
        return (routes != null) ? routes : new CustomLinkedList<>();
    }


    public CustomLinkedList<String> getAllStops() {
        return vertices;
    }


    public CustomLinkedList<String> bfs(String startStop) {
        CustomLinkedList<String> visitOrder = new CustomLinkedList<>();

        if (!adjacencyTable.containsKey(startStop)) {
            return visitOrder; // stop does not exist in the network
        }

        CustomHashSet<String> visited = new CustomHashSet<>();
        CustomQueue<String> queue = new CustomQueue<>();

        queue.enqueue(startStop);
        visited.add(startStop);

        while (!queue.isEmpty()) {
            String current = queue.dequeue();
            visitOrder.add(current);

            for (Route route : getRoutesFrom(current)) {
                String neighbor = route.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }
        return visitOrder;
    }


    public CustomLinkedList<String> dfs(String startStop) {
        CustomLinkedList<String> visitOrder = new CustomLinkedList<>();

        if (!adjacencyTable.containsKey(startStop)) {
            return visitOrder;
        }

        CustomHashSet<String> visited = new CustomHashSet<>();
        dfsHelper(startStop, visited, visitOrder);
        return visitOrder;
    }

    private void dfsHelper(String stop, CustomHashSet<String> visited, CustomLinkedList<String> visitOrder) {
        visited.add(stop);
        visitOrder.add(stop);

        for (Route route : getRoutesFrom(stop)) {
            String neighbor = route.getDestination();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, visitOrder);
            }
        }
    }


    public void displayGraph() {
        for (String stop : vertices) {
            System.out.print(stop + " -> ");
            CustomLinkedList<Route> routes = getRoutesFrom(stop);
            if (routes.isEmpty()) {
                System.out.println("(no outgoing routes)");
            } else {
                for (Route r : routes) {
                    System.out.print(r + "  ");
                }
                System.out.println();
            }
        }
    }
}