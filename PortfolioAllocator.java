import java.util.*;

public class PortfolioAllocator {
    List<Asset> assets = new ArrayList<>();
    double totalInvestment;
    double riskTolerance;
    public void getAllInputs() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter the no of assets:");
        int no = input.nextInt();
        for (int i = 0; i < no; i++) {
            System.out.println("Enter ALL details for asset :ID ,Expected Return ,Risk Level ,Quantity respectvley:");
            String id = input.next();
            double expectedReturn = input.nextDouble();
            double riskLevel = input.nextDouble();
            int quantity = input.nextInt();
            Asset asset = new Asset(id, expectedReturn, riskLevel, quantity);
            assets.add(asset);
        }
        System.out.println("Enter total investment amount:");
        totalInvestment = input.nextDouble();
        System.out.println("Enter risk tolerance level:");
        riskTolerance = input.nextDouble();

      
    }
    public void findOptimal(){
        //code
    }


}
