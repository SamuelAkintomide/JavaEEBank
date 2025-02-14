package com.mycompany.bank;

import java.util.Scanner;//importing Java libraries
import java.time.LocalDate;

class Access{
    
    //intialising Java util packages
    Scanner input = new Scanner(System.in);
    LocalDate day = LocalDate.now();
    
    //Instance Fields being created
    int transfer = 101;
    int number;
    String accStatus;
    String name;
    String firstName;
    String lastName;
    String address;
    double change;
    double balance;
    double transferAmount;
    LocalDate creationDate;
    LocalDate transacDate;
    String[] arr = new String[6];
    Double[] size = new Double[6];
    String transactions;
    int[] accList = new int[100];

    //Interface to check if account is open or closed
    public void account() {
        boolean inAccount = true;
        while(inAccount == true){
            if("Closed".equals(accStatus)){
                openAccount();
                inAccount = false; 
            } else {
                accountUI();
                inAccount = false;
            }
        }
    }
    
    //Main Account Interface
    public void accountUI(){
        boolean inUI = true;
        while(inUI == true){
            boolean chosen = false; 
            System.out.println("Type: [Deposit]  To Deposit money");
            System.out.println("      [Withdraw] To Withdraw money");
            System.out.println("      [View]     To View last 6 transactions");
            System.out.println("      [Change]   To Change information");
            System.out.println("      [Close]    To close account");
            System.out.println("      [Exit]     To Logout of account");
            while (chosen == false){ //Stops option from accepting null values
                String option = (input.nextLine().toLowerCase());
                if("deposit".equals(option)) { 
                    deposit();
                    chosen = true;
                } else if ("withdraw".equals(option)) {
                    withdraw();
                    chosen = true;
                } else if ("view".equals(option)) {
                    recentTransactions();
                    chosen = true;
                } else if ("change".equals(option)) {
                    changeInfo();    
                    chosen = true;
                } else if ("close".equals(option)) {
                    closeAccount();
                    if(accStatus == "Closed"){
                        inUI = false;
                    }
                    chosen = true;
                } else if ("exit".equals(option)) {
                    inUI = false;
                    chosen = true;
                }
            }
        }
    }
    
    //Allows user to change their information
    public void changeInfo() {
        System.out.println("Current information is as follows:");
        System.out.println("First name: "+ firstName+"\nLast name:  "+lastName);
        System.out.println("Address:    "+address+"\n");
        System.out.println("Type: [First]    To change First Name");
        System.out.println("      [Last]     To change Last Name");
        System.out.println("      [Both]     To change both Names");
        System.out.println("      [Address]  To change Address");
        String option = input.nextLine().toLowerCase();
        if("first".equals(option)){
            fN();
        }else if("last".equals(option)){
            lN();
        }else if("both".equals(option)){
            fN();
            lN();
        }else if("address".equals(option)){
            ad();
        }else {
            System.out.println("No suitable option selected");
        }
        System.out.println("Current information is as follows:");
        userInfo();
    }
    
    //Eligible to be closed
    public void eClose(){
        boolean picked = false;
        System.out.println("This account is eligible to be closed");
        System.out.println("Type: [Close] To close the account");
        System.out.println("      [Exit]  To cancel this process");
        String choice = (input.nextLine().toLowerCase());
        while (picked == false){ if ("close".equals(choice)){
                accStatus = "Closed";
                picked = true;
            } else if("exit".equals(choice)){
                picked = true;
            }
        }
    }
    
    //Ineligible to be closed
    public void iClose(){
        System.out.println("This account is not eligible to be closed");
        System.out.println("Current balance is: $" + balance);
        System.out.println("Type: [Withdraw]  To withdraw remaining funds");
        System.out.println("      [Transfer]  To transfer funds to a different account");
        System.out.println("      [Exit]      To cancel this process");
        String choice = (input.nextLine().toLowerCase());
        boolean picked = false;
        while (picked == false){
            if("withdraw".equals(choice)){
                balance -= balance;
                System.out.println("Account balance is now: $" + balance);
                System.out.println("Account has now been closed");
                accStatus = "Closed";
                picked = true;
            } else if("transfer".equals(choice)){
                boolean transfering = true;
                while (transfering == true){
                    System.out.println("Enter Account number");
                    transfer = input.nextInt();
                    boolean finding = false; 
                        for(int j:accList){
                            if(transfer == j){
                                finding = true;
                            }
                        }
                    if(finding == true){
                        System.out.println(balance+ " has be transfered to account "+transfer);
                        transferAmount = balance;
                        balance = 0;
                        System.out.println("Account balance: $"+ balance);
                        System.out.println("Account has now been closed");
                        accStatus = "Closed";
                        picked = true;  
                        transfering = false;
                    } else {
                        transfer = 101;
                        System.out.println("Account number not present");
                        System.out.println("Would you like to cancel [Yes/No]");
                        boolean loop = true;
                        while(loop == true){
                            String picking = (input.nextLine().toLowerCase());
                            if( "no".equals(picking)){
                                picked = true;  
                                loop = false;
                                transfering = false;
                            } else if("yes".equals(picking)) { 
                                loop = false;
                            }
                        }
                    }        
                }
            } else if("exit".equals(choice)){
                picked = true;
            } 
        }
    }
    
     //Close Account
    public void closeAccount(){
        System.out.println("To close the account the balance has to be 0");
        if(balance == 0){
            eClose();
        } else {
            iClose();            
        }
    }
    
    //Open Account
    public void openAccount(){
        boolean carryon = true;
        System.out.println("Account is closed and cant be used");
        System.out.println("Account can be deleted in the main menu");
        System.out.println("Type: [Open] To re-open the account");
        System.out.println("      [Exit] To logout of account");
        String option = (input.nextLine().toLowerCase());
        while (carryon == true ){
            if("open".equals(option)){
                accStatus = "Open";
                carryon = false;
            } else if("exit".equals(option)){
                carryon = false;
            }
        }         
    }
     
    //Displays User Information
    public void accInfo() {
        System.out.println("\nAccount number:         "+number);
        userInfo();
        System.out.println("Account Balance:        "+ balance);
        System.out.println("Account Opening Date:   "+ creationDate);
        System.out.println("Account Status:         "+accStatus+"\n");
    }
    
    //Specific information the user can change
    public void userInfo() {
        System.out.println("Account Holder Name:    "+ name);
        System.out.println("Account Holder Address: "+ address);
    }
    
     //Displays recent transactions in order of size
    public void recentTransactions() {
        boolean display = false;
        int lengthCheck = 0;
        for(Double h: size){
            if( h != null){
                display = true;
                lengthCheck ++; 
            }
        }
        if(display != true){
            System.out.println("No transaction have been made\n");
        }else{
            System.out.println("\nCurrent Balance: $"+balance);
            System.out.println("Last 6 transactions (order of size):");
            Double[] nums = size.clone();
            int[] sortedArray = new int[lengthCheck];
            for (int i = 0; i < lengthCheck; i++) {
                int temp = i;
                for (int j = 0; j < lengthCheck; j++) {
                    boolean in = false;
                    for(int b: sortedArray){
                        if(j == b){
                            in = true;
                        }
                    if ((nums[j] > nums[temp])&&(in != true)) {
                        temp = j;
                        }
                    }
                }
                nums[temp] = 0.0;
                sortedArray[i] = temp;
            }
            for(int f: sortedArray){
                System.out.println(arr[f]);
            }
        }  
    }
    
    //Keeps size to last 6 transactions
    public void changeHash(){ 
        System.out.println("Balance: $" + balance);
        for(int i = 5; i > 0; i--){
            int x = i-1;
            arr[i] = arr[x];
            size[i] = size[x];
        }
        arr[0] = transactions;
        size[0] = change;
    }
    
    //Deposit
    public void deposit() {
        System.out.println("Enter amount to deposit (Dont Include Symbols)");
        if(input.hasNextDouble()) {
            change = input.nextDouble();
            balance += change;
            transactions = ("Deposited "+change+" on "+day);
            changeHash(); 
        } else {
            System.out.println("Value entered not suitable");
        }
    }
    
    //Withdraw
    public void withdraw() {
        System.out.println("Enter amount to withdraw (Dont Include Symbols)");
        if(input.hasNextDouble()) {    
            change = input.nextDouble();
            if (balance < change){
                System.out.println("Insufficient funds");
                System.out.println("Balance: $" + balance);
            } else {
                balance -= change;
                transactions = ("Withdrew "+change+" on "+day);
                changeHash();
            }
        } else {
            System.out.println("Value entered not suitable");
            System.out.println("Balance: $" + balance);
        }
    }
    
    //Creats new account
    public void newUser() {
        creationDate = day;//Date account was created
        fN();
        lN();
        ad();
        System.out.println("Account has been created");
        System.out.println("Information is as follows:");
        userInfo();
        accStatus = "Open";
    }

    //First Name
    public void fN(){
        System.out.println("Enter first name");
        firstName = input.nextLine();
        name = (firstName+" "+lastName);
    }
    
    //Last Name
    public void lN(){
        System.out.println("Enter last name");
        lastName = input.nextLine();
        name = (firstName+" "+lastName);
    }
    
    //Address
    public void ad(){
        System.out.println("Enter address");
        address = input.nextLine();
    }
}

//Main Method
public class BankP2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean user = true;
        boolean found = false;
        int numberOfAccounts = 100;
        int arr[] = new int[numberOfAccounts];
        Access C[] = new Access[numberOfAccounts];//number of accounts
        int n = 0; 
        System.out.println("Welcome");
        while (user == true){
            boolean picked = false;
            System.out.println("Type: [New]       To create a new account");
            System.out.println("      [Returning] To access an account");
            System.out.println("      [Display]   To display all accounts");
            System.out.println("      [Delete]    To delete an account");
            System.out.println("      [Exit]      To Exit the program");
            while(picked == false){
                String status = (input.nextLine().toLowerCase());
                if(status != null){
                    if ("returning".equals(status)) { //Created Accounts
                        System.out.println("Enter account number");
                        int acNum = input.nextInt();
                        for(int i = 0; i < arr.length; i++){
                            if(arr[i] == acNum ){
                                found = true;
                                break;
                            }   
                        }
                        if(found == false) {
                            System.out.println("Account not present");
                        } else {
                            for(int h: arr){
                                C[acNum].accList[h] = h;
                            }
                            C[acNum].account();
                            //transfering money to accounts
                            if((C[acNum].transfer != 101)){
                                int transferToAcc =  C[acNum].transfer;
                                double transferSize = C[acNum].transferAmount;
                                C[acNum].transfer = 101;
                                C[acNum].transferAmount = 0;
                                C[transferToAcc].balance += transferSize; 
                            }
                        }
                        
                        picked = true;
                    } else if ("new".equals(status)) {//Account Creation
                        for(int i = 0; i < numberOfAccounts; i++){
                            if(C[i] == null ){
                                C[i] = new Access();
                                C[i].newUser();
                                System.out.println("\nAccount number is "+i+"\n");
                                C[i].number = i;
                                arr[i] = i;
                                break;
                            }                        
                        }
                        picked = true;
                    } else if ("display".equals(status)) {//Display info
                        int count = 0;
                        for (int i = 0; i < numberOfAccounts; i++) { 
                            if(C[i] != null){
                                C[i].accInfo();
                                count ++;
                            }
                        }
                        if(count == 0){
                            System.out.println("There are no accounts");
                        }
                        picked = true;
                    } else if ("delete".equals(status)) {//Deletes accounts
                        System.out.println("Enter account number?");
                        int del = input.nextInt();
                        if(C[del].accStatus == "Closed"){
                            C[del] = null;
                            
                        } else {
                            System.out.println("Account status is Open");
                            System.out.println("Account must first be closed before deleting");
                        }
                        picked = true;
                    } else if ("exit".equals(status)) {              
                        System.out.println("Would you like to exit the program? [Yes/No]");
                        if("yes".contains(input.nextLine().toLowerCase())) {
                            user = false;
                        }
                        picked = true;
                    }  
                }
            }
        }  
    }  
}