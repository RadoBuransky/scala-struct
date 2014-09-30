package com.buransky.struct

import java.io.Serializable

import scala.collection.generic.{CanBuildFrom, GenericCompanion, GenericTraversableTemplate, SeqFactory}
import scala.collection.mutable._

@SerialVersionUID(5658465458987987946L)
final class StructBuffer[A] extends AbstractBuffer[A]
  with Buffer[A]
  with GenericTraversableTemplate[A, StructBuffer]
  with BufferLike[A, StructBuffer[A]]
  with Builder[A, StructBuffer[A]]
  with Serializable {
  override def companion: GenericCompanion[StructBuffer] = StructBuffer

  override def update(n: Int, newelem: A): Unit = ???

  override def remove(n: Int): A = ???

  override def +=:(elem: A): this.type = ???

  override def insertAll(n: Int, elems: collection.Traversable[A]): Unit = ???

  override def result(): this.type = ???

  override def clear(): Unit = ???

  override def +=(elem: A): this.type = ???

  override def apply(n: Int): A = ???

  override def length: Int = ???

  override def iterator: Iterator[A] = ???
}

object StructBuffer extends SeqFactory[StructBuffer] {
  implicit def canBuildFrom[A]: CanBuildFrom[Coll, A, StructBuffer[A]] = ReusableCBF.asInstanceOf[GenericCanBuildFrom[A]]
  def newBuilder[A]: Builder[A, StructBuffer[A]] = new GrowingBuilder(new StructBuffer[A])
}