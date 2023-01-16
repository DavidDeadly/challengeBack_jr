package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Enabled implements ValueObject<Integer> {
  private final Integer value;

  public Enabled(Integer value) {
    this.value = value;
  }

  @Override
  public Integer value() {
    return value;
  }
}
