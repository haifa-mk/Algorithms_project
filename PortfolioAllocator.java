import java.util.*;

public class PortfolioAllocator {
static List<Integer> bestAllocation = new ArrayList<>();
static double bestReturn = -1; //any val
static double bestRisk = Double.MAX_VALUE; // initialise with a high value
static Scanner input = new Scanner(System.in);
   static List<Asset> assets = new ArrayList<>();
   static double totalInvestment;
   static double riskTolerance;
    public void getAllInputs() {
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



    //change the return value to list or object containg the optimal allocation  in both methods
    public static List<List<Integer>> assestAllocations(int targetSum, int[] maxValues) {
        List<List<Integer>> result = new ArrayList<>();
        generateAllocation(targetSum, maxValues, new ArrayList<>());
        return result;
    }

    public static void generateAllocation(int numOfAssets, int[] quantities, List<Integer> currentAllocation) {
        if (currentAllocation.size() == quantities.length) {
            int sum = 0;
            for (int num : currentAllocation) {
                sum += num;
            }
            if (sum == numOfAssets) {
                double currentReturn = calculatePortfolio(currentAllocation);
                double currentRisk = calculatePortfolio(currentAllocation);
                if (currentReturn > bestReturn && currentRisk <= riskTolerance) {
                    bestReturn = currentReturn;
                    bestRisk = currentRisk;
                    bestAllocation = new ArrayList<>(currentAllocation); 
                }
            }
           
            return;
        }
    
        for (int i = 0; i <= quantities[currentAllocation.size()]; i++) {
            currentAllocation.add(i);
            generateAllocation(numOfAssets, quantities, currentAllocation);
            currentAllocation.remove(currentAllocation.size() - 1);
        }
    }
    


    public static double calculatePortfolio(List<Integer> allocation) {
        double totalRisk = 0;
        double totalQuan = 0;
    
        // Calculate the total quantity to find weights
        for (int i = 0; i < allocation.size(); i++) {
            totalQuan += allocation.get(i);
        }
    
        // calculate the risk using the weights
        for (int i = 0; i < allocation.size(); i++) {
            double weight = (double)allocation.get(i) / totalQuan;
            totalRisk += weight * assets.get(i).riskLevel;
        }
    
        return totalRisk;
    }
    




}
