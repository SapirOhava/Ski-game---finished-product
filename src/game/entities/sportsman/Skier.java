package game.entities.sportsman;
/**

 */
import game.enums.Discipline;
import game.enums.Gender;


public class Skier extends WinterSportsman{
    public Skier(int id ,String name, double age, Gender gender, double acceleration, double maxSpeed, Discipline discipline , String color) {
        super(id ,name, age, gender, acceleration, maxSpeed, discipline , color );
    }

    public Skier clone(){
        return new Skier( -getId(),getName(), getAge() , getGender() , getAcceleration() , getMaxSpeed() , getDiscipline(), getColor());
    }


}
