package inventoryManagementSytem.customerAdress;

public class CustomerAddress {
    private int id;
    private String name;
    private String location;
    private String pincode;

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    
    @Override
    public String toString() {
        return "CustomerAddress[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pincode='" + pincode + '\'' +
                ']';
    }
}
