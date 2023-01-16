package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class IDClient implements ValueObject<String> {

  private final String value;

  public IDClient(String value) {
    this.value = value;
  }

  @Override
  public String value() {
    return value;
  }
}
