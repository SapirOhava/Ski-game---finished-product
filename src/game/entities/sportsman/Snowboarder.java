package game.entities.sportsman;
/**

 */
import game.enums.Discipline;
import game.enums.Gender;

public class Snowboarder extends WinterSportsman{
    public Snowboarder(int id ,String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline , String color) {
        super(id ,name, age, gender, acceleration, maxSpeed, discipline, color);
    }

    public Snowboarder clone(){
        return new Snowboarder( -getId(),getName(), getAge() , getGender() , getAcceleration() , getMaxSpeed() , getDiscipline(), getColor());
    }


}
