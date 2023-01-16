package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Min implements ValueObject<Integer> {
  private final Integer value;

  public Min(Integer value) {
    this.value = value;
  }

  @Override
  public Integer value() {
    return value;
  }
}
