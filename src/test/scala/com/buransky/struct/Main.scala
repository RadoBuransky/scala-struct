package com.buransky.struct

import java.nio.ByteBuffer

case class MyClass(a: Boolean, b: Int, c: Int, d: Double)
case class MyClass2(a: Int, b: Int)

object MyAppStructs {
  val myClassStruct = Struct(MyClass.apply _, Function.unlift(MyClass.unapply))
  val myClass2Struct = Struct(MyClass2.apply _, Function.unlift(MyClass2.unapply))
}

object Main {
  import MyAppStructs._

  def main(args: Array[String]) = {
    val bb = ByteBuffer.allocate(256)

    val pos1 = myClassStruct.write(MyClass(true, 42, 69, 13.65), bb)
    val pos2 = myClassStruct.write(MyClass(false, 3, -100, 0.5), bb)
    val pos3 = myClassStruct.write(MyClass(true, 999, -456, 0.66666666), bb)

    println(myClassStruct.read(pos2, bb).toString)
    println(myClassStruct.read(pos1, bb).toString)
    println(myClassStruct.read(pos3, bb).toString)
  }
}
