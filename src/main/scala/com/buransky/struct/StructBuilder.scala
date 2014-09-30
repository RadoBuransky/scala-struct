package com.buransky.struct

import java.nio.ByteBuffer

import java.io.Serializable

import scala.collection.generic.{CanBuildFrom, GenericCompanion, GenericTraversableTemplate, SeqFactory}
import scala.collection.mutable._

private[struct] class StructBuffer[A](struct: Struct[A]) extends AbstractBuffer[Struct[A]]
  with Buffer[Struct[A]]
  with GenericTraversableTemplate[Struct[A], StructBuffer]
  with BufferLike[Struct[A], StructBuffer[A]]
  with Builder[Struct[A], StructBuffer[A]]
  with Serializable {
  override def companion: GenericCompanion[StructBuffer] = new StructBufferFactory(struct)

  override def update(n: Int, newelem: Struct[A]): Unit = ???

  override def remove(n: Int): Struct[A] = ???

  override def +=:(elem: Struct[A]): this.type = ???

  override def insertAll(n: Int, elems: collection.Traversable[Struct[A]]): Unit = ???

  override def result(): this.type = ???

  override def clear(): Unit = ???

  override def +=(elem: Struct[A]): this.type = ???

  override def apply(n: Int): Struct[A] = ???

  override def length: Int = ???

  override def iterator: Iterator[Struct[A]] = ???
}

class StructBufferFactory[T](struct: Struct[T]) extends SeqFactory[StructBuffer] {
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, StructBuffer[A]] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]
  def newBuilder[A]: Builder[Struct[A], StructBuffer[A]] = {
    struct match {
      case sa: Struct[A] => new GrowingBuilder(new StructBuffer(sa))
      case _ =>
    }
  }
}

class StructBuilder[A](struct: Struct[A]) {
  def append(elem: A): StructRef = struct.write(elem, byteBuffer)
  def get(ref: StructRef): A = struct.read(ref, byteBuffer)
  def apply(ref: StructRef): A = get(ref)

  private lazy val byteBuffer = ByteBuffer.allocate(StructBuilder.initialCapacity)
}

class SmallStructBuilder[A](struct: Struct[A]) extends StructBuilder[A](struct) {
  def appendSmall(elem: A): SmallStructRef = SmallStructRef(super.append(elem).ref.toShort)
}

class TinyStructBuilder[A](struct: Struct[A]) extends SmallStructBuilder[A](struct) {
  def appendTiny(elem: A): TinyStructRef = TinyStructRef(super.append(elem).ref.toByte)
}

object StructBuilder {
  def apply[A](struct: Struct[A], maxCapacity: Int): StructBuilder[A] = {
    maxCapacity match {
      case _ if maxCapacity <= Byte.MaxValue => new SmallStructBuilder[A](struct)
      case _ if maxCapacity <= Short.MaxValue => new SmallStructBuilder[A](struct)
      case _ if maxCapacity <= Int.MaxValue => new StructBuilder[A](struct)
      case _ => throw new IllegalArgumentException("Capacity too large! Maximal capacity is " + Int.MaxValue)
    }
  }

  private val initialCapacity = 256
}