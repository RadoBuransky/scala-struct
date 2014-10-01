package com.buransky.struct.mutable

import java.nio.ByteBuffer

import com.buransky.struct._
import com.buransky.struct.immutable.StructVector

import scala.collection.generic.{CanBuildFrom, GenericCompanion, GenericTraversableTemplate, SeqFactory}
import scala.collection.{mutable => cm, immutable}

class FixedStructBuffer[A](struct: Struct[A])
  extends cm.AbstractBuffer[A]
     with cm.Buffer[A]
     with cm.IndexedSeq[A]
     with GenericTraversableTemplate[A, FixedStructBuffer]
     with cm.BufferLike[A, FixedStructBuffer[A]]
     with cm.IndexedSeqOptimized[A, FixedStructBuffer[A]]
     with cm.Builder[A, FixedStructBuffer[A]]
     with Serializable {

  def appendRef(elem: A): StructRef = {
    this += elem
    StructRef(length - 1)
  }

  override def companion: GenericCompanion[FixedStructBuffer] = StructBuffer

  override def seq: cm.IndexedSeq[A] = this

  override def toIndexedSeq: immutable.IndexedSeq[A] = {
    structVector = structVector match {
      case None => Some(new StructVector[A](this))
      case _ => structVector
    }

    structVector.get
  }

  override def +=(elem: A): this.type = {
    checkReadonly()
    struct.write(elem, byteBuffer)

    structSize match {
      case None => structSize = Some(byteBuffer.position())
      case _ => // We've already got the structrure size
    }

    _length += 1
    this
  }

  override def clear(): Unit = {
    checkReadonly()
    byteBuffer.clear()
    _length = 0
  }

  override def length: Int = _length

  override def apply(v1: Int): A = {
    if (isEmpty)
      throw new IndexOutOfBoundsException

    struct.read(StructRef(v1 * structSize.get), byteBuffer)
  }

  override def result(): FixedStructBuffer[A] = this

  // TODO: Implement
  override def +=:(elem: A): this.type = ???
  override def update(n: Int, newelem: A): Unit = ???
  override def remove(n: Int): A = ???
  override def insertAll(n: Int, elems: Traversable[A]): Unit = ???

  private lazy val byteBuffer = ByteBuffer.allocate(256)
  private var _length = 0
  private var structSize: Option[Int] = None
  private var structVector: Option[StructVector[A]] = None

  private def checkReadonly(): Unit = {
    if (structVector.isDefined)
      throw new IllegalStateException("Buffer has been converted to immutable sequence and therefore" +
        " it cannot be modified!")
  }

  private object StructBuffer extends SeqFactory[FixedStructBuffer] {
    implicit def canBuildFrom[T]: CanBuildFrom[Coll, T, FixedStructBuffer[T]] =
      ReusableCBF.asInstanceOf[GenericCanBuildFrom[T]]

    def newBuilder[T]: cm.Builder[T, FixedStructBuffer[T]] =
      new FixedStructBuffer[T](struct.asInstanceOf[Struct[T]])
  }
}

class SmallFixedStructBuffer[A](struct: Struct[A]) extends FixedStructBuffer[A](struct) {
  def appendSmall(elem: A): SmallStructRef = SmallStructRef(super.appendRef(elem).ref.toShort)
}

class TinyFixedStructBuffer[A](struct: Struct[A]) extends SmallFixedStructBuffer[A](struct) {
  def appendTiny(elem: A): TinyStructRef = TinyStructRef(super.appendRef(elem).ref.toByte)
}