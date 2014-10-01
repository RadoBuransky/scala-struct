package com.buransky

import java.nio.ByteBuffer

package object struct {
  implicit def structRefToInt(structRef: StructRef): Int = structRef.ref

  //====================================================================================================================
  // Built-in structures
  //====================================================================================================================

  /**
   * Integer structure
   */
  implicit object IntStruct extends Struct[Int] {
    override def write(t: Int, bb: ByteBuffer) = positionBefore(bb) { bb.putInt(t) }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.getInt }
  }

  /**
   * Boolean structure
   */
  implicit object BooleanStruct extends Struct[Boolean] {
    override def write(t: Boolean, bb: ByteBuffer) =  positionBefore(bb) {
      bb.put((if (t) 1 else 0).toByte)
    }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.get == 1 }
  }

  /**
   * Double structure
   */
  implicit object DoubleStruct extends Struct[Double] {
    override def write(t: Double, bb: ByteBuffer) =  positionBefore(bb) { bb.putDouble(t) }
    override def read(ref: StructRef, bb: ByteBuffer) = positionAndRead(ref, bb) { bb.getDouble }
  }
}
