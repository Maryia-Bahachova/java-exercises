package com.epam.autotasks;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

@Data
@Builder
public class Cat {

    private String name;
    private Integer age;
    private Breed breed;
    private ContestResult contestResult;

    public enum Breed {

        BRITISH, MAINE_COON, MUNCHKIN, PERSIAN, SIBERIAN
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cat)) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name)
                && Objects.equals(age, cat.age)
                && breed == cat.breed
                && Objects.equals(contestResult, cat.contestResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, breed, contestResult);
    }
}
