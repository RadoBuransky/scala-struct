package com.buransky.struct

/**
 * (De)serialization logic between user-provided type and structure fields.
 */
private[struct] trait Struct[T] {
  def write(t: T, byteStore: ByteStore): Unit
  def read(byteStore: ByteStore): T
}

object Struct {
  def apply[A1, A2, B](f1: Function2[A1, A2, B], f2: Function1[B, Tuple2[A1, A2]])
                      (implicit a1: Struct[A1], a2: Struct[A2]) = {
    new Struct[B] {
      override def write(t: B, byteStore: ByteStore) = {
        val a = f2(t)
        a1.write(a._1, byteStore)
        a2.write(a._2, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        f1(a1.read(byteStore), a2.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, B](f1: Function4[A1, A2, A3, A4, B], f2: Function1[B, Tuple4[A1, A2, A3, A4]])
                              (implicit a1: Struct[A1], a2: Struct[A2], a3: Struct[A3], a4: Struct[A4])= {
    new Struct[B] {
      override def write(t: B, byteStore: ByteStore) = {
        val a = f2(t)
        a1.write(a._1, byteStore)
        a2.write(a._2, byteStore)
        a3.write(a._3, byteStore)
        a4.write(a._4, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        f1(a1.read(byteStore), a2.read(byteStore), a3.read(byteStore), a4.read(byteStore))
      }
    }
  }
}