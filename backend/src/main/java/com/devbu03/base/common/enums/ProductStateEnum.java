package com.devbu03.base.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductStateEnum {
 UPDATE(2),
 CREATE(1),
 DELETE(-1);

  private final int value;


  public int getValue() {
    return value;
  }
}
