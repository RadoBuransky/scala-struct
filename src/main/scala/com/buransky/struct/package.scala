package com.buransky

import com.buransky.struct.store.ByteStore

import scala.language.implicitConversions

package object struct {
  //====================================================================================================================
  // Built-in structures for common data types
  //====================================================================================================================

  implicit object ByteStruct extends Struct[Byte] {
    override def put(inst: Byte, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readByte()
  }

  implicit object ShortStruct extends Struct[Short] {
    override def put(inst: Short, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readShort()
  }

  implicit object IntStruct extends Struct[Int] {
    override def put(inst: Int, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readInt()
  }

  implicit object LongStruct extends Struct[Long] {
    override def put(inst: Long, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readLong()
  }

  implicit object FloatStruct extends Struct[Float] {
    override def put(inst: Float, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readFloat()
  }

  implicit object DoubleStruct extends Struct[Double] {
    override def put(inst: Double, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readDouble()
  }

  implicit object BooleanStruct extends Struct[Boolean] {
    override def put(inst: Boolean, byteStore: ByteStore) = byteStore.put(inst)
    override def read(byteStore: ByteStore) = byteStore.readBoolean()
  }
}
