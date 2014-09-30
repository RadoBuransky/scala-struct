package com.buransky.struct

case class MyClass(a: Boolean, b: Int, c: Int, d: Double)
case class MyClass2(a: Int, b: Int)

object MyAppStructs {
  val myClassStruct = Struct(MyClass.apply _, Function.unlift(MyClass.unapply))
  val myClass2Struct = Struct(MyClass2.apply _, Function.unlift(MyClass2.unapply))
}

object Main {
  import com.buransky.struct.MyAppStructs._

  def main(args: Array[String]) = {
    val small = StructBuilder(myClassStruct, 10)
    val medium = new SmallStructBuilder(myClassStruct)
    val large = new TinyStructBuilder(myClassStruct)

    val pos1 = small.append(MyClass(true, 42, 69, 13.65))
    val pos2 = medium.appendSmall(MyClass(false, 3, -100, 0.5))
    val pos3 = large.appendTiny(MyClass(true, 999, -456, 0.66666666))

    println(small(pos1))
    println(medium(pos1))
    println(large(pos1))

    println("Stop!")
    Thread.sleep(15000)
  }
}
