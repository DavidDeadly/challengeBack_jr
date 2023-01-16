package co.sofka.challenge_jr.domain.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Date;

public class DateBuy implements ValueObject<Date> {
  private final Date value;

  public DateBuy(Date value) {
    this.value = value;
  }

  @Override
  public Date value() {
    return value;
  }
}
