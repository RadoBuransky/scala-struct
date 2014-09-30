package com.buransky.struct

case class StructRef(ref: Int) extends AnyVal
case class SmallStructRef(ref: Short) extends AnyVal
case class TinyStructRef(ref: Byte) extends AnyVal

private[struct] object StructRef {
  val nil = StructRef(-1)
}