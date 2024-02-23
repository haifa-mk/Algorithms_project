import java.util.*;

public class PortfolioAllocator {
static List<Integer> bestAllocation = new ArrayList<>();
static double bestReturn = -1; //any val
static double bestRisk = Double.MAX_VALUE; // initialise with a high value
static Scanner input = new Scanner(System.in);
   static List<Asset> assets = new ArrayList<>();
   static int totalInvestment;
   static double riskTolerance; 
   static int no;


public static void main(String[] args) {
  
        System.out.println("enter the no of assets:");
       no = input.nextInt();
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
        totalInvestment = input.nextInt();
        System.out.println("Enter risk tolerance level:");
        riskTolerance = input.nextDouble();

        int[] maxValues = new int[no]; // Initialise the array with the size of the assets list
    
        // Fill maxValues with the quantity of each asset
        for (int i = 0; i < no; i++) {
            maxValues[i] = assets.get(i).quantity;
        }
       generateAllocation(totalInvestment, maxValues,  new ArrayList<>());
    System.out.println("Optimal Allocation:  ");
  for(int i = 0 ; i < no ; i++)
  System.out.println(assets.get(i).id +": "+ bestAllocation.get(i)+ "units");
  System.out.println("Expected Portfolio Return: "+bestReturn);
    System.out.println("Expected Portfolio Risk: "+bestRisk);
    }
    
    public void findOptimal(){
        //code
    }



    public static void generateAllocation(int investmentAmount, int[] quantities, List<Integer> currentAllocation) {

        if (currentAllocation.size() == quantities.length) {
            int sum = 0;
            for (int num : currentAllocation) {
                sum += num;
            }
            if (sum == investmentAmount) {
            	  double currentReturn = calculatePortfolio(currentAllocation,false, investmentAmount);
                double currentRisk = calculatePortfolio(currentAllocation, true , investmentAmount);
                if (currentReturn > bestReturn && currentRisk <= riskTolerance) {
                    bestReturn = currentReturn;
                    bestRisk = currentRisk;
                    bestAllocation = new ArrayList<>(currentAllocation);
            }
            return;
        }

        for (int i = 0; i <= quantities[currentAllocation.size()]; i++) {
            currentAllocation.add(i);
            generateAllocation(investmentAmount, quantities, currentAllocation);
            currentAllocation.remove(currentAllocation.size() - 1);
        }
    }

}
public static double calculatePortfolio(List<Integer> allocatedAssets, boolean isRiskLevel, int totalUnits) {
    double result = 0;
    double weight;

    for (int i = 0; i < allocatedAssets.size(); i++) {
        weight = (double) allocatedAssets.get(i) / (double) totalUnits; 
        if (isRiskLevel) {
            result += weight * assets.get(i).riskLevel;
        } else {
            result += weight * assets.get(i).expectedReturn;
        }
    }
    return result; 
}
}
