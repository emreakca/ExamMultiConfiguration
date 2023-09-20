package at.tugraz.ist.ase.model;

public class Question {

  private Long id;

  private Integer number;

  private Integer type;

  private Integer level;

  public Question() {}

  public Question(Long id, Integer number, Integer type, Integer level) {
    this.id = id;
    this.number = number;
    this.type = type;
    this.level = level;
  }

  public Long getId() {
    return id;
  }

  public Integer getNumber() {
    return number;
  }

  public Integer getType() {
    return type;
  }

  public Integer getLevel() {
    return level;
  }

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", number=" + number +
            ", type=" + type +
            ", level=" + level +
            '}';
  }
}
