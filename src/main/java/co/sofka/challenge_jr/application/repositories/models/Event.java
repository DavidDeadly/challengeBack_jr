package co.sofka.challenge_jr.application.repositories.models;

public class Event {
  private String aggregateId;
  private String eventBody;
  private String typeName;

  public Event() {
  }

  public Event(String aggregateId, String eventBody, String typeName) {
    this.aggregateId = aggregateId;
    this.eventBody = eventBody;
    this.typeName = typeName;
  }

  public String getAggregateId() {
    return aggregateId;
  }

  public String getEventBody() {
    return eventBody;
  }

  public String getTypeName() {
    return typeName;
  }
}
