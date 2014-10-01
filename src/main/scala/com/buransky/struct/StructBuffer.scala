package com.buransky.struct

import java.nio.ByteBuffer

import scala.collection.generic.{CanBuildFrom, GenericCompanion, GenericTraversableTemplate, SeqFactory}
import scala.collection.mutable

class StructBuffer[A](struct: Struct[A])
  extends mutable.AbstractBuffer[A]
     with mutable.Buffer[A]
     with mutable.IndexedSeq[A]
     with GenericTraversableTemplate[A, StructBuffer]
     with mutable.BufferLike[A, StructBuffer[A]]
     with mutable.IndexedSeqOptimized[A, StructBuffer[A]]
     with mutable.Builder[A, StructBuffer[A]]
     with Serializable {

  override def companion: GenericCompanion[StructBuffer] = StructBuffer

  override def seq: mutable.IndexedSeq[A] = this

  private object StructBuffer extends SeqFactory[StructBuffer] {
    implicit def canBuildFrom[T]: CanBuildFrom[Coll, T, StructBuffer[T]] =
      ReusableCBF.asInstanceOf[GenericCanBuildFrom[T]]

    def newBuilder[T]: mutable.Builder[T, StructBuffer[T]] = new StructBuffer[T](struct.asInstanceOf[Struct[T]])

  }

  def appendRef(elem: A): StructRef = {
    val result = StructRef(byteBuffer.position())
    this += elem
    result
  }

  override def +=:(elem: A): this.type = ???

  override def +=(elem: A): this.type = {
    struct.write(elem, byteBuffer)
    _length += 1
    this
  }

  override def update(n: Int, newelem: A): Unit = ???

  override def clear(): Unit = {
    byteBuffer.clear()
    _length = 0
  }

  override def length: Int = _length

  override def remove(n: Int): A = ???

  override def insertAll(n: Int, elems: Traversable[A]): Unit = ???

  override def apply(v1: Int): A = struct.read(StructRef(v1), byteBuffer)

  override def result(): StructBuffer[A] = this

  private lazy val byteBuffer = ByteBuffer.allocate(256)
  private var _length = 0
}

class SmallStructBuffer[A](struct: Struct[A]) extends StructBuffer[A](struct) {
  def appendSmall(elem: A): SmallStructRef = SmallStructRef(super.appendRef(elem).ref.toShort)
}

class TinyStructBuffer[A](struct: Struct[A]) extends SmallStructBuffer[A](struct) {
  def appendTiny(elem: A): TinyStructRef = TinyStructRef(super.appendRef(elem).ref.toByte)
}