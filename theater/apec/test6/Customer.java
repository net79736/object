package apec.test6;

public class Customer {

    private Long id;
    private String name;
    private String cardNumber;
    private String cvv;
    private String accountNumber;
    private String bankCode;
    private String email;
    private String walletAddress;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public String getCvv() {
        return cvv;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getBankCode() {
        return bankCode;
    }
    
    public String getEmail() {
        return email;
    }

    public String getWalletAddress() {
        return walletAddress;
    }
}
