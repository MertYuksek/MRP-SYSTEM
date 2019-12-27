package ProductOP;

import java.util.ArrayList;

public class ProductNode {

    public ArrayList<ProductNode> childs = new ArrayList<>();
    private int id;
    private String name;
    private ProductionLists productionLists = new ProductionLists();
    private ArrayList<Integer> infoFrom;
    private int amountOnHand;
    private int leadTime;
    private int lotRule;
    private int requirement;
    private int scheduledReceipt;
    private int arrivalOnWeek;
    private ProductNode parent = null;

    public ProductNode(int id, String name, int requirement){
        this.id = id;
        this.name = name;
        this.requirement = requirement;
    }

    public int getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setInfoFrom(ArrayList<Integer> infoFrom) {

        this.infoFrom = infoFrom;
    }

    public void setMemeber(ArrayList<Integer> list){
        setInfoFrom(list);
        setAmountOnHand(list.get(0));
        setScheduledReceipt(list.get(1));
        setArrivalOnWeek(list.get(2));
        setLeadTime(list.get(3));
        setLotRule(list.get(4));
    }

    public int getAmountOnHand() {
        return amountOnHand;
    }

    public void setAmountOnHand(int amountOnHand) {
        this.amountOnHand = amountOnHand;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public int getLotRule() {
        return lotRule;
    }

    public void setLotRule(int lotRule) {
        this.lotRule = lotRule;
    }

    public ProductionLists getProductionLists() {

        return productionLists;
    }

    public int getRequirement() {
        return requirement;
    }

    public ProductNode getParent() {
        return parent;
    }

    public void setParent(ProductNode parent) {
        this.parent = parent;
    }

    public int getScheduledReceipt() {
        return scheduledReceipt;
    }

    public void setScheduledReceipt(int scheduledReceipt) {
        this.scheduledReceipt = scheduledReceipt;
    }

    public int getArrivalOnWeek() {
        return arrivalOnWeek;
    }

    public void setArrivalOnWeek(int arrivalOnWeek) {
        this.arrivalOnWeek = arrivalOnWeek;
    }
}
