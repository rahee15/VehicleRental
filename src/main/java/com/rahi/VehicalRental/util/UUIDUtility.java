package com.rahi.VehicalRental.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UUIDUtility {
  public static UUID generate() {
    return UUID.randomUUID();
  }
}
