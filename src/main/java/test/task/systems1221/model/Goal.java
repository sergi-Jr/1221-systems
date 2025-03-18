package test.task.systems1221.model;

import lombok.Getter;

@Getter
public enum Goal {
    SLIMMING("Weight slimming"),
    MAINTENANCE("Weight maintenance"),
    GAIN("Weight gain");

    private final String description;

    Goal(String description) {
        this.description = description;
    }

}
