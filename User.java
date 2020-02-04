public class User{
    private Boolean isAvailable = true;
    private Integer number;
    private Integer totalUsers;

    User(int i){
        this.number = i;
    }

    public void updateUsers(int i){
        this.totalUsers = i;
    }
    public Integer getNumber(){
        return this.number;
    }
    public void setNumber(int n){
        this.number = n;
    }
    public Boolean getAvailability(){
        return this.isAvailable;
    }
    public void setIsAvailable(Boolean b){
        this.isAvailable = b;
    }


}
