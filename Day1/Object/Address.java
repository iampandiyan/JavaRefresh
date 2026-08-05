package Object;

public class Address implements Cloneable {
    
     String city;    

   Address(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
    @Override
    public Address clone() {
        try { 
            return (Address) super.clone(); 
        } catch (CloneNotSupportedException e) 
        { 
            throw new AssertionError(e); 
        }
    }


}
