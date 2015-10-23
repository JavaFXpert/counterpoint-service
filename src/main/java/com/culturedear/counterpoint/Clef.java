package com.culturedear.counterpoint;

/**
 * Created by jamesweaver on 9/25/15.
 */
public class Clef {
  public String sign;

  public int line;

  public Clef(String sign, int line) {
    this.sign = sign;
    this.line = line;
  }

  public int getLine() {
    return line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }
}
