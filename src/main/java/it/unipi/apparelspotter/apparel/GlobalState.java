package it.unipi.apparelspotter.apparel;

public class GlobalState {
    private static volatile GlobalState instance;
    private String currentRetailerId;
    private String currentCustomerId;
    private GlobalState() {}

    public static GlobalState getInstance() {
        if (instance == null) {
            synchronized (GlobalState.class) {
                if (instance == null) {
                    instance = new GlobalState();
                }
            }
        }
        return instance;
    }

    public String getCurrentRetailerId() {
        return currentRetailerId;
    }

    public void setCurrentRetailerId(String currentRetailerId) {
        this.currentRetailerId = currentRetailerId;
    }

    public String getCurrentCustomerId() {
        return currentCustomerId;
    }

    public void setCurrentCustomerId(String currentCustomerId) {
        this.currentCustomerId = currentCustomerId;
    }

    // Other global properties and methods
}
