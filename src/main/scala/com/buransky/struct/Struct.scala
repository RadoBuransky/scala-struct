package com.buransky.struct

import java.nio.ByteBuffer

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