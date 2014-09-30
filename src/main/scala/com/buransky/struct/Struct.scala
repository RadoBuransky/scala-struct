package com.buransky.struct

import java.nio.ByteBuffer

//==============================================================
// Lib
//==============================================================

sealed trait StructRef extends Any
private[struct] case object NilStructRef extends StructRef
private[struct] case class IntStructRef(ref: Int) extends AnyVal with StructRef
private[struct] case class ShortStructRef(ref: Short) extends AnyVal with StructRef
private[struct] case class ByteStructRef(ref: Byte) extends AnyVal with StructRef

object DefaultStructs {
  implicit object IntStruct extends Struct[Int] {
    override def write(t: Int, bb: ByteBuffer) = positionBefore(bb) { bb.putInt(t) }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.getInt }
  }

  implicit object BooleanStruct extends Struct[Boolean] {
    override def write(t: Boolean, bb: ByteBuffer) =  positionBefore(bb) {
      bb.put((if (t) 1 else 0).toByte)
    }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.get == 1 }
  }

  implicit object DoubleStruct extends Struct[Double] {
    override def write(t: Double, bb: ByteBuffer) =  positionBefore(bb) { bb.putDouble(t) }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.getDouble }
  }
}

/**
 * (De)serialization logic between user-provided type and structure fields.
 */
abstract class Struct[T] {
  def write(t: T, bb: ByteBuffer): StructRef
  def read(ref: StructRef, bb: ByteBuffer): T

  protected def read(bb: ByteBuffer): T = read(NilStructRef, bb)
  protected def positionBefore(bb: ByteBuffer)(f: => Unit): StructRef = {
    val result = IntStructRef(bb.position())
    f
    result
  }

  protected def positionAndRead[T](ref: StructRef, bb: ByteBuffer)(f: => T): T = {
    ref match {
      case NilStructRef => // Don't do anything
      case IntStructRef(r) => bb.position(r)
      case ShortStructRef(r) => bb.position(r)
      case ByteStructRef(r) => bb.position(r)
      case _ => throw new IllegalStateException("Unknown reference type!")
    }

    f
  }
}

object Struct {
  def apply[A1, A2, B](f1: Function2[A1, A2, B], f2: Function1[B, Tuple2[A1, A2]])
                      (implicit a1: Struct[A1], a2: Struct[A2]) = {
    new Struct[B] {
      override def write(t: B, bb: ByteBuffer) = positionBefore(bb) {
        val a = f2(t)
        a1.write(a._1, bb)
        a2.write(a._2, bb)
      }

      override def read(ref: StructRef, bb: ByteBuffer): B = positionAndRead(ref, bb) { f1(a1.read(bb), a2.read(bb)) }
    }
  }

  def apply[A1, A2, A3, A4, B](f1: Function4[A1, A2, A3, A4, B], f2: Function1[B, Tuple4[A1, A2, A3, A4]])
                              (implicit a1: Struct[A1], a2: Struct[A2], a3: Struct[A3], a4: Struct[A4])= {
    new Struct[B] {
      override def write(t: B, bb: ByteBuffer) = positionBefore(bb) {
        val a = f2(t)
        a1.write(a._1, bb)
        a2.write(a._2, bb)
        a3.write(a._3, bb)
        a4.write(a._4, bb)
      }

      override def read(ref: StructRef, bb: ByteBuffer): B = positionAndRead(ref, bb) {
        f1(a1.read(bb), a2.read(bb), a3.read(bb), a4.read(bb))
      }
    }
  }
}

//==============================================================
// User
//==============================================================

case class MyClass(a: Boolean, b: Int, c: Int, d: Double)
case class MyClass2(a: Int, b: Int)

object MyAppStructs {
  import DefaultStructs._

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