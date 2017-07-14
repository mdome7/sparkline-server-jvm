package com.labs2160.sparklineserver.core

/**
  * Color holder
  */
class Color(val value: String) {

    // validate value
    if (!value.matches("^#[0123456789ABCDEFabcdef]{6}$") && !value.matches("^[a-zA-Z]+$")) throw new IllegalArgumentException(s"Invalid color value: ${value}")

}

object Color {
    def apply(value: String) = new Color(value)

    val White = Color("#FFFFFF")
    val Black = Color("#000000")
    val Red = Color("#FF0000")
    val Green = Color("#00FF00")
    val Blue = Color("#0000FF")
}
