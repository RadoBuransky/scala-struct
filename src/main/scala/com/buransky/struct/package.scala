package com.buransky

import scala.language.implicitConversions

package object struct {
  implicit def structRefToInt(structRef: StructRef): Int = structRef.ref

  //====================================================================================================================
  // Built-in structures
  //====================================================================================================================

  /**
   * Integer structure
   */
  implicit object IntStruct extends Struct[Int] {
    override def write(t: Int, byteStore: ByteStore) = byteStore.put(t)
    override def read(byteStore: ByteStore) = byteStore.readInt()
  }

  /**
   * Boolean structure
   */
  implicit object BooleanStruct extends Struct[Boolean] {
    override def write(t: Boolean, byteStore: ByteStore) = byteStore.put(t)
    override def read(byteStore: ByteStore) = byteStore.readBoolean()
  }

  /**
   * Double structure
   */
  implicit object DoubleStruct extends Struct[Double] {
    override def write(t: Double, byteStore: ByteStore) = byteStore.put(t)
    override def read(byteStore: ByteStore) = byteStore.readDouble()
  }
}
