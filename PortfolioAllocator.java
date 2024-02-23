import java.util.*;

public class PortfolioAllocator {
static List<Integer> bestAllocation = new ArrayList<>();
static double bestReturn = -1; //any val
static double bestRisk = Double.MAX_VALUE; // initialise with a high value
static Scanner input = new Scanner(System.in);
   static List<Asset> assets = new ArrayList<>();
   static double totalInvestment;
   static double riskTolerance; static int no;
    public void getAllInputs() {
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
            	  double currentReturn = calculatePortfolio(currentAllocation,false, numOfAssets);
                double currentRisk = calculatePortfolio(currentAllocation, true , numOfAssets);
                if (currentReturn > bestReturn && currentRisk <= riskTolerance) {
                    bestReturn = currentReturn;
                    bestRisk = currentRisk;
                    bestAllocation = new ArrayList<>(currentAllocation);
            }
            return;
        }

        for (int i = 0; i <= quantities[currentAllocation.size()]; i++) {
            currentAllocation.add(i);
            generateAllocation(numOfAssets, quantities, currentAllocation);
            currentAllocation.remove(currentAllocation.size() - 1);
        }
    }

}
public static double calculatePortfolio(List<Integer> allocatedAssest ,boolean isRiskLevel ,int numOfAssets) {
        double Result= 0;
        double weight;
   
        for (int i = 0; i < allocatedAssest.size(); i++) {
            weight = (double)allocatedAssest.get(i) /(double) numOfAssets;
            if(isRiskLevel){
            Result += weight * assets.get(i).riskLevel ;
            return Result ;}
            else{
            Result += weight * assets.get(i).expectedReturn ;
            return Result ;}
          }
           return 0;
 
    } 
}
