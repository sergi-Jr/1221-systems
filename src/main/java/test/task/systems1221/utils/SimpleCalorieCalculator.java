package test.task.systems1221.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import test.task.systems1221.model.Sex;
import test.task.systems1221.user.model.User;

@ConfigurationProperties(prefix = "user.suggestion")
public class SimpleCalorieCalculator {
    private static float slim = 0.75f;
    private static int main = 1;
    private static float gain = 1.25f;

    public static int calories(User user) {
        switch (user.getGoal()) {
            case SLIMMING -> {
                return calculate(user, slim);
            }
            case MAINTENANCE -> {
                return calculate(user, main);
            }
            case GAIN -> {
                return calculate(user, gain);
            }
            case null, default -> throw new IllegalArgumentException("Unknown parameter in user.getGoal()");
        }
    }

    private static int calculate(User user, float goalMiltiplier) {
        int preResult = (int) (10 * user.getWeight() + 6.25f * user.getHeight() - 5 * user.getAge());
        if (user.getSex() == Sex.MALE) {
            return (int) ((preResult + 5) * goalMiltiplier);
        } else {
            return (int) ((preResult - 161) * goalMiltiplier);
        }
    }
}
