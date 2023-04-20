package at.tugraz.ist.ase.model;

public class Requirement {
  Requirement lhs;
  String operator;
  Requirement rhs;

  public Requirement() {}

  public Requirement(Requirement lhs, String operator, Requirement rhs) {
    this.lhs = lhs;
    this.operator = operator;
    this.rhs = rhs;
  }

  public Requirement getLhs() {
    return lhs;
  }

  public void setLhs(Requirement lhs) {
    this.lhs = lhs;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Requirement getRhs() {
    return rhs;
  }

  public void setRhs(Requirement rhs) {
    this.rhs = rhs;
  }
}
