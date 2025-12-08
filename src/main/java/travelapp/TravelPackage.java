package travelapp;

public class TravelPackage {
    private int id;
    private String name;
    private String type;
    private int durationDays;
    private double basePrice;
    private Transport transport;
    private MealPlan mealPlan;
    private int availableSeats;
    private double rating;

    public TravelPackage(int id, String name, String type, int durationDays, double basePrice,
                         Transport transport, MealPlan mealPlan, int availableSeats, double rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.durationDays = durationDays;
        this.basePrice = basePrice;
        this.transport = transport;
        this.mealPlan = mealPlan;
        this.availableSeats = availableSeats;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getDurationDays() { return durationDays; }
    public double getBasePrice() { return basePrice; }
    public Transport getTransport() { return transport; }
    public MealPlan getMealPlan() { return mealPlan; }
    public int getAvailableSeats() { return availableSeats; }
    public double getRating() { return rating; }

    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %d днів | %.2f$ | %s | %s | місць: %d | ★%.1f",
                id, name, type, durationDays, basePrice, transport, mealPlan, availableSeats, rating);
    }
}
