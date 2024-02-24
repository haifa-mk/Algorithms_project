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
    
   

/* 
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
        }*/
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
        // Fill maxValues with the quantity of each asset
    /*     for (int i = 0; i <   assettList.size(); i++) {
            maxValues[i] = assettList.get(i).quantity;
        System.out.println(assettList.get(i).quantity);
        }*/
     //  generateAllocation(totalInvestment, maxValues,  new ArrayList<>());
   if(bestAllocation.size()==0)
        System.out.println("There are no feasible allocations within this risk rate");
        else{
    System.out.println("Optimal Allocation:  ");
  for(int i = 0 ; i <  assettList.size() ; i++)
  System.out.println(assettList.get(i).id +": "+ bestAllocation.get(i)+ "units");
   System.out.printf("Expected Portfolio Return: %.3f\n", bestReturn);
  System.out.printf("Expected Portfolio Risk: %.3f\n", bestRisk);
        }
    }
    
    public static void findOptimalAllocation() {
        for (List<Integer> allocation : validAllocations) {
            double currentReturn = calculatePortfolio(allocation, false, totalInvestment);
            double currentRisk = calculatePortfolio(allocation, true, totalInvestment);
            if (currentReturn > bestReturn && currentRisk <= riskTolerance) {
                bestReturn = currentReturn;
                bestRisk = currentRisk;
                bestAllocation = new ArrayList<>(allocation);
            }
        }
    }


    public static void generateAllocation(int investmentAmount, int[] quantities, List<Integer> currentAllocation) {

        if (currentAllocation.size() == quantities.length) {
    
            int totalUsedInvestment = 0;
            for (int i = 0; i < currentAllocation.size(); i++) {
                // Assuming each unit of asset equals 1 unit of investment for simplicity
                totalUsedInvestment += currentAllocation.get(i);
            }
            
            // Check if the total investment used is within the investmentAmount
            if (totalUsedInvestment == investmentAmount) {
                validAllocations.add(new ArrayList<>(currentAllocation));   
                
            }
            return;
        }
    
        int assetIndex = currentAllocation.size();
       
        for (int i = 0; i <= quantities[assetIndex]; i++) {
        currentAllocation.add(i);
            generateAllocation(investmentAmount, quantities, currentAllocation);
            currentAllocation.remove(currentAllocation.size() - 1);
        }
    }


public static double calculatePortfolio(List<Integer> allocatedAssets, boolean isRiskLevel, int totalUnits) {
    double result = 0;
    double weight;

    for (int i = 0; i < allocatedAssets.size(); i++) {
        if (i < assettList.size()) {
            weight = (double) allocatedAssets.get(i) / (double) totalUnits;
            if (isRiskLevel) {
                result += weight * assettList.get(i).riskLevel;
            } else {
                result += weight * assettList.get(i).expectedReturn;
            }
        }
    }
    
    return result; 
}
   
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
