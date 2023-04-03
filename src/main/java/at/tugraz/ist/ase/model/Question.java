package at.tugraz.ist.ase.model;

public class Question {
    private Integer number;

    private Integer type;

    private Integer level;

    public Question(Integer number, Integer type, Integer level) {
        this.number = number;
        this.type = type;
        this.level = level;
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
}
