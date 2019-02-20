public class Property{
    private int id;
    private String propertyName;
    private String location;
    private Long phone;

    public Property(String propertyName,String location,Long phone){
        this.propertyName =  propertyName;
        this.location = location;
        this.phone = phone;
        this.id = id;
    }

    public String getPropertyName(){
        return propertyName;
    }

    public String getLOcation(){
        return location;
    }

    public Long getPhone(){
        return phone;
    }

    public int getId(){
        return id;
    }
}