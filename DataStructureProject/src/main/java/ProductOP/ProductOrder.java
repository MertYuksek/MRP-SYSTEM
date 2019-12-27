package ProductOP;

import java.util.ArrayList;

public class ProductOrder {

    private ArrayList<Integer> demand;
    private ProductTree productTree;

    public ProductOrder(ArrayList<Integer> demand, ProductTree productTree) {
        this.demand = demand;
        this.productTree = productTree;
        productOrder();
    }

    public void productOrder(){

        ArrayList<ProductNode> list = productTree.getDuplicateList();

        for(int i=0; i<list.size(); i++){

            ProductionLists productionList = list.get(i).getProductionLists();

            if(productTree.getDuplicateNodes(list.get(i)).size()==1){

                if (i == 0) {
                    productionList.setGrossRequirement(demand);
                }
                else {
                    productionList.setGrossRequirement(operationsOfGrossRequierement(list.get(i)));
                }

                productionList.setScheduledReceipt(operationsOfScheduledReceipt(list.get(i)));
                productionList.setOnHandPriorPeriod(operationsOfOnHandPriorPeriodList(list.get(i)));
                productionList.setNetRequirement(operationsOfNetRequirement(productionList.getGrossRequirement(), productionList.getOnHandPriorPeriod(), productionList.getScheduledReceipt()));
                productionList.setTimePhasedNetRequirement(operationsTimePhasedNetRequirement(list.get(i), productionList.getNetRequirement()));
                productionList.setPlannedOrderReleases(operationPlannedOrderReleases(list.get(i), productionList.getTimePhasedNetRequirement()));
                productionList.setPlannedOrderDelivery(operationsPlannedOrderDelivery(list.get(i), productionList.getPlannedOrderReleases()));
            }
        }
        duplicateNodesOp();
    }

    public void duplicateNodesOp(){

        ArrayList<ProductNode> nodes = productTree.getList();

        for(int i=0; i<nodes.size(); i++){

            ArrayList<ProductNode> duplicateNodes = productTree.getDuplicateNodes(nodes.get(i));

            if(duplicateNodes.size()>1){

                for(int j=0; j<duplicateNodes.size(); j++) {
                    duplicateNodes.get(0).getProductionLists().setGrossRequirement(addGrossRequirements(duplicateNodes.get(0),duplicateNodes.get(j)));
                }
                duplicateNodes.get(0).getProductionLists().setScheduledReceipt(operationsOfScheduledReceipt(duplicateNodes.get(0)));
                duplicateNodes.get(0).getProductionLists().setOnHandPriorPeriod(operationsOfOnHandPriorPeriodList(duplicateNodes.get(0)));
                duplicateNodes.get(0).getProductionLists().setNetRequirement(operationsOfNetRequirement(duplicateNodes.get(0).getProductionLists().getGrossRequirement(), duplicateNodes.get(0).getProductionLists().getOnHandPriorPeriod(), duplicateNodes.get(0).getProductionLists().getScheduledReceipt()));
                duplicateNodes.get(0).getProductionLists().setTimePhasedNetRequirement(operationsTimePhasedNetRequirement(duplicateNodes.get(0),duplicateNodes.get(0).getProductionLists().getNetRequirement()));
                duplicateNodes.get(0).getProductionLists().setPlannedOrderReleases(operationPlannedOrderReleases(duplicateNodes.get(0), duplicateNodes.get(0).getProductionLists().getTimePhasedNetRequirement()));
                duplicateNodes.get(0).getProductionLists().setPlannedOrderDelivery(operationsPlannedOrderDelivery(duplicateNodes.get(0),duplicateNodes.get(0).getProductionLists().getPlannedOrderReleases()));

            }
        }
    }

    public ArrayList<Integer> addGrossRequirements(ProductNode firstDuplicateNode,ProductNode node){

        ArrayList<Integer> list = new ArrayList<>();

        if(firstDuplicateNode.getProductionLists().getGrossRequirement().size()==0){
            return operationsOfGrossRequierement(firstDuplicateNode);
        }
        else {

            for (int i=0; i<10; i++){
                list.add(firstDuplicateNode.getProductionLists().getGrossRequirement().get(i));
            }

            for(int i=0; i<10; i++){
                list.set(i, list.get(i) + node.getParent().getProductionLists().getPlannedOrderReleases().get(i)*node.getRequirement());
            }
            return list;
        }

    }

    public ArrayList<Integer> operationsOfGrossRequierement(ProductNode node){

        ArrayList<Integer> list = new ArrayList<>();
        for(int k=0; k<10; k++){
            list.add(0);
        }

        for(int i=0; i<10; i++){
            list.set(i,node.getParent().getProductionLists().getPlannedOrderReleases().get(i)*node.getRequirement());
        }

        return list;
    }

    public ArrayList<Integer> operationsOfScheduledReceipt (ProductNode node){

        ArrayList<Integer> list =new ArrayList<>();

        for(int m=0; m<10; m++){
            list.add(0);
        }

        if(node.getScheduledReceipt()!=0){
            list.set(node.getArrivalOnWeek()-1,node.getScheduledReceipt());
        }
        return list;
    }

    public ArrayList<Integer> operationsOfOnHandPriorPeriodList (ProductNode node){

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> rList = node.getProductionLists().getScheduledReceipt();
        ArrayList<Integer> gList = node.getProductionLists().getGrossRequirement();

        int onHand = node.getAmountOnHand();

        for(int i=0; i<10; i++){

            list.add(onHand);
            onHand+=rList.get(i);

            if(onHand>=gList.get(i)){
                onHand = onHand-gList.get(i);
            }
            else {
                if(((gList.get(i)-onHand)%node.getLotRule())==0){
                    onHand = 0;
                }
                else {
                    int number = (int) Math.round(((gList.get(i)-onHand)/node.getLotRule())+0.5);
                    onHand = number*node.getLotRule() - (gList.get(i)-onHand);
                }
            }
        }

        return list;
    }

    public ArrayList<Integer> operationsOfNetRequirement (ArrayList<Integer> gross, ArrayList<Integer> prior,ArrayList<Integer> scheduled){

        ArrayList<Integer> list =new ArrayList<>();
        for(int i=0; i<10; i++){
            int number = (gross.get(i)-(prior.get(i)+scheduled.get(i)))>0?(gross.get(i)-(prior.get(i)+scheduled.get(i))):0;
            list.add(number);
        }
        return list;
    }

    public ArrayList<Integer> operationsTimePhasedNetRequirement(ProductNode node, ArrayList<Integer> netRequiremnt){

        int leadTime = node.getLeadTime();
        ArrayList<Integer> list = new ArrayList<>();

        for(int m=0; m<10; m++){
            list.add(0);
        }

        for(int i=0; i<10; i++){
            if(netRequiremnt.get(i)!=0 &&(i-leadTime)<0){
                throw new ArithmeticException("Bu zaman aralığında ürünleri üretemeyiz");
            }
            else if(netRequiremnt.get(i)==0){
                list.set(i,0);
            }
            else if(netRequiremnt.get(i)!=0 && (i-leadTime)>=0){
                list.set(i-leadTime,netRequiremnt.get(i));
                list.set(i,0);
            }

        }
        return list;
    }

    public ArrayList<Integer> operationPlannedOrderReleases(ProductNode node, ArrayList<Integer> timeRequirement){

        int lotSize = node.getLotRule();
        ArrayList<Integer> list =new ArrayList<>();

        for(int m=0; m<10; m++){
            list.add(0);
        }

        for(int i=0; i<10; i++){

            int requiremnt = timeRequirement.get(i);

            if(requiremnt!=0){

                if(requiremnt%lotSize==0){
                    list.set(i,requiremnt);
                }
                else {
                    int number = (requiremnt/lotSize)+1;
                    list.set(i,number*lotSize);
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> operationsPlannedOrderDelivery(ProductNode node, ArrayList<Integer> releases){

        int leadTime = node.getLeadTime();
        ArrayList<Integer> list = new ArrayList<>();

        for(int m=0; m<10; m++){
            list.add(0);
        }

        for(int i=0; i<10; i++){
            if(releases.get(i)!=0 &&(i+leadTime)>10){
                throw new ArithmeticException("Bu zaman aralığında ürünleri üretemeyiz");
            }
            else if(releases.get(i)!=0 && (i+leadTime)<10){
                list.set(i+leadTime,releases.get(i));
            }
        }
        return list;
    }

}
