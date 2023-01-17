package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class Enabled implements ValueObject<Boolean> {
  private final Boolean value;

  public Enabled(Boolean value) {
    this.value = value;
  }

  @Override
  public Boolean value() {
    return value;
  }
}
