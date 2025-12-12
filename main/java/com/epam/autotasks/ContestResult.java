package com.epam.autotasks;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ContestResult {

    private final Integer running;
    private final Integer jumping;
    private final Integer purring;
    private final Integer sum;

    public ContestResult(Integer running, Integer jumping, Integer purring) {
        this.running = running;
        this.jumping = jumping;
        this.purring = purring;
        this.sum = countResults(running, jumping, purring);
    }

    public Integer countResults(Integer running, Integer jumping, Integer purring) {
        int sum = 0;
        if (running != null) sum += running;
        if (jumping != null) sum += jumping;
        if (purring != null) sum += purring;
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestResult)) return false;
        ContestResult that = (ContestResult) o;
        return Objects.equals(running, that.running) &&
                Objects.equals(jumping, that.jumping) &&
                Objects.equals(purring, that.purring) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(running, jumping, purring, sum);
    }
}
