public class Customer {

    private final String ssn, name, address;
    private final Account account;

    public Customer(String ssn, String name, String address, Account account) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.account = account;
    }


    @Override
    public String toString(){
        return "\nProfile\n" + "SSN#: " +ssn+ "\n"+ "Name: " +name+ "\n" + "Address: " +address+ "\n" +account;
    }

    public String Info(){
        return "\nAccount Number: " +account.getAccNum()+ "\n" + "SSN#: " +ssn+ "\n" + "Name: " +name+ "\n" + "Address: " +address+ "\n";
    }

    Account getAccount(){
        return account;
    }

}
