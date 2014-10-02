package com.buransky

import scala.language.implicitConversions

package object struct {
  //====================================================================================================================
  // Built-in structures for common data types
  //====================================================================================================================

  /**
   * Integer structure
   */
  implicit object IntStruct extends Struct[Int] {
    override def put(inst: Int, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readInt()
  }

  /**
   * Boolean structure
   */
  implicit object BooleanStruct extends Struct[Boolean] {
    override def put(inst: Boolean, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readBoolean()
  }

  /**
   * Double structure
   */
  implicit object DoubleStruct extends Struct[Double] {
    override def put(inst: Double, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readDouble()
  }
}
