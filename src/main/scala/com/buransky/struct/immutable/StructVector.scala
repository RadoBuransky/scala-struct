package com.buransky.struct.immutable

import scala.collection._
import scala.collection.generic.{SeqFactory, GenericCompanion, GenericTraversableTemplate}
import scala.collection.immutable.IndexedSeq

private[struct] class StructVector[A] (structSeq: scala.collection.IndexedSeq[A])
  extends AbstractSeq[A]
     with IndexedSeq[A]
     with GenericTraversableTemplate[A, StructVector]
     with IndexedSeqLike[A, StructVector[A]]
     with Serializable {
  override def companion: GenericCompanion[StructVector] = StructVector

  override def length: Int = structSeq.length

  override def apply(idx: Int): A = structSeq.apply(idx)

  object StructVector extends SeqFactory[StructVector] {
    override def newBuilder[T]: mutable.Builder[T, StructVector[T]] = ???
  }
}