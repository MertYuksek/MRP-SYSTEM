package ProductOP;

import java.util.ArrayList;

public class ProductionLists {

    private ArrayList<Integer> grossRequirement = new ArrayList<>();
    private ArrayList<Integer> scheduledReceipt = new ArrayList<>();
    private ArrayList<Integer> onHandPriorPeriod = new ArrayList<>();
    private ArrayList<Integer> netRequirement = new ArrayList<>();
    private ArrayList<Integer> timePhasedNetRequirement = new ArrayList<>();
    private ArrayList<Integer> plannedOrderReleases = new ArrayList<>();
    private ArrayList<Integer> plannedOrderDelivery = new ArrayList<>();

    public ArrayList<Integer> getGrossRequirement() {

        return grossRequirement;
    }

    public void setGrossRequirement(ArrayList<Integer> grossRequirement) {
        this.grossRequirement = grossRequirement;
    }

    public ArrayList<Integer> getScheduledReceipt() {
        return scheduledReceipt;
    }

    public void setScheduledReceipt(ArrayList<Integer> scheduledReceipt) {
        this.scheduledReceipt = scheduledReceipt;
    }

    public ArrayList<Integer> getOnHandPriorPeriod() {
        return onHandPriorPeriod;
    }

    public void setOnHandPriorPeriod(ArrayList<Integer> onHandPriorPeriod) {
        this.onHandPriorPeriod = onHandPriorPeriod;
    }

    public ArrayList<Integer> getNetRequirement() {
        return netRequirement;
    }

    public void setNetRequirement(ArrayList<Integer> netRequirement) {
        this.netRequirement = netRequirement;
    }

    public ArrayList<Integer> getTimePhasedNetRequirement() {
        return timePhasedNetRequirement;
    }

    public void setTimePhasedNetRequirement(ArrayList<Integer> timePhasedNetRequirement) {
        this.timePhasedNetRequirement = timePhasedNetRequirement;
    }

    public ArrayList<Integer> getPlannedOrderReleases() {
        return plannedOrderReleases;
    }

    public void setPlannedOrderReleases(ArrayList<Integer> plannedOrderReleases) {
        this.plannedOrderReleases = plannedOrderReleases;
    }

    public ArrayList<Integer> getPlannedOrderDelivery() {
        return plannedOrderDelivery;
    }

    public void setPlannedOrderDelivery(ArrayList<Integer> plannedOrderDelivery) {
        this.plannedOrderDelivery = plannedOrderDelivery;
    }

    public void print(){
        System.out.println(" GrossRequirement: "+getGrossRequirement());
        System.out.println(" ScheduledReceipt: "+getScheduledReceipt());
        System.out.println(" OnHandPriorPeriod: "+getOnHandPriorPeriod());
        System.out.println(" NetRequirement: "+getNetRequirement());
        System.out.println(" TimePhasedNetRequirement: "+getTimePhasedNetRequirement());
        System.out.println(" PlannedOrderReleases: "+getPlannedOrderReleases());
        System.out.println(" PlannedOrderDelivery(): "+getPlannedOrderDelivery());
    }
}
