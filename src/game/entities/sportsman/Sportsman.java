package game.entities.sportsman;
/**

 */
import game.entities.MobileEntity;
import game.enums.Gender;


public class Sportsman extends MobileEntity {

    //the fields isn't final because i want to give the user the option to change the fields.
    private int id;
    private  String name;
    private  double age;
    private  Gender gender;
    private String color;

    public Sportsman(int id ,String name, double age, Gender gender, double acceleration, double maxSpeed , String color) {
        super(0, acceleration,maxSpeed);
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.color = color;
    }

    //region Getters & setters
    public String getName() {
        return name;
    }

    public double getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender newGender){this.gender = newGender;}

    public void setColor(String NewColor){ this.color = NewColor;}
    public void  setId(int NewId){ this.id = NewId;}
    public String getColor(){return this.color;}
    public int getId(){return this.id;}

}
