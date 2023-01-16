package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

public class IDType implements ValueObject<IDTypeEnum> {
  private final IDTypeEnum value;

  public IDType(IDTypeEnum value) {
    this.value = value;
  }

  @Override
  public IDTypeEnum value() {
    return value;
  }
}
