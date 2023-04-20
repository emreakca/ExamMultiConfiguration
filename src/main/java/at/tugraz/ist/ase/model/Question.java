package at.tugraz.ist.ase.model;

public class Question {

  private Long id;

  private Integer type;

  private Integer level;

  public Question() {}

  public Question(Long id, Integer type, Integer level) {
    this.id = id;
    this.type = type;
    this.level = level;
  }

  public Long getId() {
    return id;
  }

  public Integer getType() {
    return type;
  }

  public Integer getLevel() {
    return level;
  }
}
