public class Customer {
    public  int customerID;
    public  String customerName ;
    public  boolean customerGender ;

    public Customer(){
        this.customerID = 101 ;
        this.customerName = "Somchai Jaidee";
        this.customerGender = true ;
    }
    
    public Customer(int cid){
        this.customerID = 101 ;
    }

    public int getCustomerID(){
        showDetail();
        return customerID;
    }
    public void setCustomerID(int id){
        this.customerID = customerID ;
    }

    private void showDetail(){
        IO.println("Customer Detail");
        IO.println("Cusotomer ID : "+this.customerID);
        IO.println("CustomerName : "+this.customerName);
        if (this.customerGender == true ){
            IO.println("Customer Gender : Male ");
        }else{
            IO.println("Customer Gender : Female ");
        }
        
    }

    public void displayDetail(){
        showDetail();
    }
    
}
