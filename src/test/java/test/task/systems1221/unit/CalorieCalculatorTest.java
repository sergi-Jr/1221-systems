package test.task.systems1221.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.task.systems1221.model.Goal;
import test.task.systems1221.model.Sex;
import test.task.systems1221.user.model.User;
import test.task.systems1221.utils.SimpleCalorieCalculator;


public class CalorieCalculatorTest {
    @Test
    public void isValidCalculated() {
        User male = new User();
        male.setSex(Sex.MALE);
        male.setAge(35);
        male.setHeight(180);
        male.setWeight(75);

        User female = new User();
        female.setSex(Sex.FEMALE);
        female.setAge(22);
        female.setHeight(166);
        female.setWeight(57);

        male.setGoal(Goal.GAIN);
        female.setGoal(Goal.GAIN);
        var maleExpected = 1960;
        var maleActual = SimpleCalorieCalculator.calories(male);
        var femaleExpected = 1536;
        var femaleActual = SimpleCalorieCalculator.calories(female);
        Assertions.assertEquals(maleExpected, maleActual);
        Assertions.assertEquals(femaleExpected, femaleActual);

        male.setGoal(Goal.MAINTENANCE);
        female.setGoal(Goal.MAINTENANCE);
        maleExpected = 1705;
        maleActual = SimpleCalorieCalculator.calories(male);
        femaleExpected = 1336;
        femaleActual = SimpleCalorieCalculator.calories(female);
        Assertions.assertEquals(maleExpected, maleActual);
        Assertions.assertEquals(femaleExpected, femaleActual);

        male.setGoal(Goal.SLIMMING);
        female.setGoal(Goal.SLIMMING);
        maleExpected = 1449;
        maleActual = SimpleCalorieCalculator.calories(male);
        femaleExpected = 1135;
        femaleActual = SimpleCalorieCalculator.calories(female);
        Assertions.assertEquals(maleExpected, maleActual);
        Assertions.assertEquals(femaleExpected, femaleActual);
    }
}
