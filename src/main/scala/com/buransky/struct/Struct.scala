package com.buransky.struct

import com.buransky.struct.store.ByteStore

/**
 * Definition of a structure for the given type.
 * @tparam T type of entity to be stored by the structure
 */
private[struct] trait Struct[T] {
  /**
   * Stores provided instance in the byte store,
   * @param inst instance of type T to be stored
   * @param byteStore byte store to hold the data
   */
  def put(inst: T, byteStore: ByteStore): Unit

  /**
   * Reads instance from the byte store.
   * @param byteStore byte store to retrieve data from
   * @return instance created from data read
   */
  def read(byteStore: ByteStore): T
}

/**
 * Companion object, Provides factory methods for structures.
 */
object Struct {
  def apply[A1, A2, B](f1: Function2[A1, A2, B], f2: Function1[B, Tuple2[A1, A2]])
                      (implicit a1: Struct[A1], a2: Struct[A2]) = {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = f2(t)
        a1.put(a._1, byteStore)
        a2.put(a._2, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        f1(a1.read(byteStore), a2.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, B](f1: Function4[A1, A2, A3, A4, B], f2: Function1[B, Tuple4[A1, A2, A3, A4]])
                              (implicit a1: Struct[A1], a2: Struct[A2], a3: Struct[A3], a4: Struct[A4])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = f2(t)
        a1.put(a._1, byteStore)
        a2.put(a._2, byteStore)
        a3.put(a._3, byteStore)
        a4.put(a._4, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        f1(a1.read(byteStore), a2.read(byteStore), a3.read(byteStore), a4.read(byteStore))
      }
    }
  }
}