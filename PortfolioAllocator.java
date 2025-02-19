import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PortfolioAllocator extends Throwable {
static List<List<Integer>> validAllocations = new ArrayList<>();
static List<Integer> bestAllocation = new ArrayList<>();
static double bestReturn = -1; //any val
static double bestRisk = Double.MAX_VALUE; // initialise with a high value
static Scanner input = new Scanner(System.in);

  static  List <Asset> assettList;
   static int totalInvestment;
   static double riskTolerance; 
   static int no;


public static void main(String[] args) {
    
   

        System.out.println("Enter the File name:");
        String fileName = input.next();
        assettList = readFromFile(fileName);
        System.out.println("Enter total investment amount:");
        totalInvestment = input.nextInt();
        System.out.println("Enter risk tolerance level:");
        riskTolerance = input.nextDouble();

      int[] maxValues = new int[assettList.size()]; // Initialise the array with the size of the assets list
  
      for(int i = 0; i<assettList.size() ; i++)
          maxValues[i]= assettList.get(i).quantity;

    generateAllocation(totalInvestment, maxValues,  new ArrayList<>());
    findOptimalAllocation();

    // check if an optimal allocation has been found and display the results
   if(bestAllocation.size()==0)
        System.out.println("There are no feasible allocations within this risk rate");
  else{
      System.out.println("Optimal Allocation:  ");
      for(int i = 0 ; i <  assettList.size() ; i++)
          System.out.println(assettList.get(i).id +": "+ bestAllocation.get(i)+ " units");
          System.out.printf("Expected Portfolio Return: %.3f\n", bestReturn);
          System.out.printf("Expected Portfolio Risk: %.3f\n", bestRisk);
        }
    }
     //Method to find the optimal allocation among all valid allocations
    public static void findOptimalAllocation() {
        for (List<Integer> allocation : validAllocations) {
            double currentReturn = calculatePortfolio(allocation, false, totalInvestment);
            double currentRisk = calculatePortfolio(allocation, true, totalInvestment);
            //will update if a better one if found
            if (currentReturn > bestReturn && currentRisk <= riskTolerance) {
                bestReturn = currentReturn;
                bestRisk = currentRisk;
                bestAllocation = new ArrayList<>(allocation);
            }
        }
    }


    public static void generateAllocation(int investmentAmount, int[] quantities, List<Integer> currentAllocation) {
    // Base case: if the current allocation includes all assets
        if (currentAllocation.size() == quantities.length) {
    
            int totalUsedInvestment = 0;
            for (int i = 0; i < currentAllocation.size(); i++) {
                // Assuming each unit of asset equals 1 unit of investment for simplicity
                totalUsedInvestment += currentAllocation.get(i);
            }

             // Add the current allocation to valid allocations if it matches the investment amount
            if (totalUsedInvestment == investmentAmount) {
                validAllocations.add(new ArrayList<>(currentAllocation));   
                
            }
            return;
        }
    
        int assetIndex = currentAllocation.size();
       
        // iterate through all possible quantities for the current asset and perfom recursion
        for (int i = 0; i <= quantities[assetIndex] && i<=investmentAmount; i++) {
        currentAllocation.add(i);
            generateAllocation(investmentAmount, quantities, currentAllocation);
            currentAllocation.remove(currentAllocation.size() - 1);
        }
    }

 // method to calculate the total return or risk of a given allocation
public static double calculatePortfolio(List<Integer> allocatedAssets, boolean isRiskLevel, int totalUnits) {
    double result = 0;
    double weight;
 
    for (int i = 0; i < allocatedAssets.size(); i++) {
        if (i < assettList.size()) {
            weight = (double) allocatedAssets.get(i) / (double) totalUnits;
              // calculates the return or risk based on the isRiskLevel flag
            if (isRiskLevel) {
                result += weight * assettList.get(i).riskLevel;
            } else {
                result += weight * assettList.get(i).expectedReturn;
            }
        }
    }
    
    return result; 
}
    // a method to read assets from a file and populate the assetList
public static List <Asset> readFromFile(String fileName , Boolean fileReadingError) {
    List <Asset> assetsList = new ArrayList <Asset> ();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" : ");
            if (parts.length == 4) {
                String assetID = parts[0];
                double expectedReturn = Double.parseDouble(parts[1]);
                double riskLevel = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                Asset asset = new Asset(assetID,expectedReturn,riskLevel,quantity);
                assetsList.add(asset);

            } else {
                System.err.println("Invalid format in line: " + line);
            }
        }
    } catch (IOException | NumberFormatException e) {
        fileReadingError = true;
    
    }
    return assetsList;
}


   public static List <Asset> readFromFile(String fileName ) {
    List <Asset> assetsList = new ArrayList <Asset> ();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" : ");
            if (parts.length == 4) {
                String assetID = parts[0];
                double expectedReturn = Double.parseDouble(parts[1]);
                double riskLevel = Double.parseDouble(parts[2]);
                int quantity = Integer.parseInt(parts[3]);
                Asset asset = new Asset(assetID,expectedReturn,riskLevel,quantity);
                assetsList.add(asset);

            } else {
                System.err.println("Invalid format in line: " + line);
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Error while reading file");
         System. exit(0);
       
    
    }
    return assetsList;
}
}
