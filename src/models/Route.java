package models;


public class Route {

    private int routeId;
    private String source;
    private String destination;
    private double distance;
    private CustomLinkedList<String> intermediateStops;

    public Route(int routeId, String source, String destination, double distance) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.intermediateStops = new CustomLinkedList<>();
    }


    public double calculateDistance() {
        return this.distance;
    }

    public void addStop(String stopName) {
        if (stopName == null || stopName.isEmpty()) {
            throw new IllegalArgumentException("Stop name cannot be empty.");
        }
        intermediateStops.add(stopName);
    }


    public void removeStop(String stopName) {
        intermediateStops.remove(stopName);
    }

    public CustomLinkedList<String> getIntermediateStops() {
        return intermediateStops;
    }

    // ----- Getters required by CustomGraph -----

    public int getRouteId() {
        return routeId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return source + " -> " + destination + " (" + distance + " km)";
    }
}