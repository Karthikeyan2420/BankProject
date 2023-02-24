public class BankCustomers {
    String customerName,phoneNo,email,aadhar,dateOfBirth;

    public String getDoorNo() {
        return DoorNo;
    }

    public String getArea() {
        return Area;
    }

    public void setDoorNo(String doorNo) {
        DoorNo = doorNo;
    }

    public void setArea(String area) {
        Area = area;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public void setDoorNo1(String doorNo1) {
        DoorNo1 = doorNo1;
    }

    public void setArea1(String area1) {
        Area1 = area1;
    }

    public void setCity1(String city1) {
        City1 = city1;
    }

    public void setPincode1(String pincode1) {
        Pincode1 = pincode1;
    }

    public String getCity() {
        return City;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getDoorNo1() {
        return DoorNo1;
    }

    public String getArea1() {
        return Area1;
    }

    public String getCity1() {
        return City1;
    }

    public String getPincode1() {
        return Pincode1;
    }

    String DoorNo,Area,City,Pincode,DoorNo1,Area1,City1,Pincode1;
    String opendate,accountTypepe,balance,accountNo;
    int customerId;
    String n,f;

    public BankCustomers(int customerId,String doorNo, String area, String city, String pincode, String doorNo1, String area1, String city1, String pincode1 ) {
        DoorNo = doorNo;
        Area = area;
        City = city;
        Pincode = pincode;
        DoorNo1 = doorNo1;
        Area1 = area1;
        City1 = city1;
        Pincode1 = pincode1;
        this.customerId = customerId;
    }

    /*public BankCustomers(String n, String f){
            this.n=n;
            this.f=f;

        }
        public String getn(){
            return this.n;
        }
        public String getf(){
            return this.f;
        }*/
    public BankCustomers(int id, String customerName, String phoneNo, String email, String aadhar, String dateOfBirth){
        this.customerId=id;
        this.customerName=customerName;
        this.phoneNo=phoneNo;
        this.email=email;
        this.aadhar=aadhar;
        this.dateOfBirth=dateOfBirth;
    }
    /*public BankCustomers(int customerId, String , String currentAddressr){
        this.customerId=customerId;this.permanentAddress=permanentAddressr;this.currentAddress=currentAddressr;
    }
   /* public BankCustomers(int customerId, String pa){
        this.customerId=customerId;this.permanentAddress=pa;this.currentAddress=pa;
    }*/
    public BankCustomers(int customerId, String accountNo, String opd, String acty, String balance){
        this.customerId=customerId; this.accountNo=accountNo; this.opendate=opd; this.accountTypepe=acty; this.balance=balance;
    }
    public int getcustomerId(){
        return this.customerId;
    }
    public String getcustomerName(){
        return this.customerName;
    }
    public String getphoneNo(){
        return this.phoneNo;
    }
    public String getemail(){
        return this.email;
    }
    public String getaadhar(){
        return this.aadhar;
    }
    public String getdateOfBirth(){
        return this.dateOfBirth;
    }
   /* public String getpermanentAddressr(){
        return this.permanentAddress;
    }
    public String getcurrentAddress(){
        return this.currentAddress;
    }*/
    public String getaccountNo(){
        return this.accountNo;
    }
    public String getopendate(){
        return this.opendate;
    }
    public String getaccountType(){
        return this.accountTypepe;
    }
    public String getbalance(){
        return this.balance;
    }




    public  int setcustomerId(int customerId){
        this.customerId=customerId;
        return this.customerId;
    }
    public String setcustomerName(String name){
        this.customerName=name;
        return this.customerName;
    }
    public String setphoneNo(String phoneNo){
        this.phoneNo=phoneNo;
        return this.phoneNo;
    }
    public String setemail(String email){
        this.email=email;
        return this.email;
    }
    public String setaadhar(String aadhar){
        this.aadhar=aadhar;
        return this.aadhar;
    }
    public String setdateOfBirth(String dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        return this.dateOfBirth;
    }
   /* public String setpermanentAddress(String permanentAddress){
        this.permanentAddress=permanentAddress;
        return this.permanentAddress;
    }
    public String setcurrentAddress(String currentAddress){
        this.currentAddress=dateOfBirth;
        return this.currentAddress;
    }*/
    public String setaccountNo(String accountNo){
        this.accountNo=accountNo;
        return this.accountNo;
    }
    public String setaccountType(String acty){
        this.accountTypepe=acty;
        return this.accountTypepe;
    }
    public String setopenDate(String opda){
        this.opendate=opda;
        return this.opendate;
    }
    public String setbalance(String balance){
        this.balance=balance;
        return this.balance;
    }

}
