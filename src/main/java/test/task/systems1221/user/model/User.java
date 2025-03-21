package test.task.systems1221.user.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import test.task.systems1221.meal.model.Meal;
import test.task.systems1221.model.BaseEntity;
import test.task.systems1221.model.Goal;
import test.task.systems1221.model.Sex;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class User implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(nullable = false)
    @ToString.Include
    private String name;

    @Column(nullable = false, unique = true)
    @ToString.Include
    private String email;

    @Column(nullable = false)
    @ToString.Include
    private int age;

    @Column(nullable = false)
    @ToString.Include
    private int weight;

    @Column(nullable = false)
    @ToString.Include
    private int height;

    @Enumerated(EnumType.STRING)
    @ToString.Include
    @Column(nullable = false, name = "goal")
    private Goal goal;

    @Enumerated(EnumType.STRING)
    @ToString.Include
    @Column(nullable = false, name = "sex")
    private Sex sex;

    @ToString.Include
    private int dailyRate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Meal> meals = new HashSet<>();

    public void addMeal(Meal meal) {
        meals.add(meal);
        meal.setUser(this);
    }

    public void addAllMeals(List<Meal> list) {
        meals.addAll(list);
        list.forEach(m -> m.setUser(this));
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy proxy
                ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}

