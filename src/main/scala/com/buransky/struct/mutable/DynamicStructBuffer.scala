//package com.buransky.struct.mutable
//
//import java.nio.ByteBuffer
//
//import com.buransky.struct._
//
//import scala.collection.generic.{CanBuildFrom, GenericCompanion, GenericTraversableTemplate, SeqFactory}
//import scala.collection.{ mutable => cm }
//
////TODO: Rewrite to LinearSeq
//class DynamicStructBuffer[A](struct: Struct[A])
//  extends cm.AbstractBuffer[A]
//  with cm.Buffer[A]
//  with cm.IndexedSeq[A]
//  with GenericTraversableTemplate[A, DynamicStructBuffer]
//  with cm.BufferLike[A, DynamicStructBuffer[A]]
//  with cm.IndexedSeqOptimized[A, DynamicStructBuffer[A]]
//  with cm.Builder[A, DynamicStructBuffer[A]]
//  with Serializable {
//
//  def appendRef(elem: A): StructRef = {
//    val result = StructRef(byteBuffer.position())
//    this += elem
//    result
//  }
//
//  override def companion: GenericCompanion[DynamicStructBuffer] = DynamicStructBuffer
//
//  override def seq: cm.IndexedSeq[A] = {
//    println("seq")
//    this
//  }
//
//  override def +=(elem: A): this.type = {
//    struct.write(elem, byteBuffer)
//    _length += 1
//    this
//  }
//
//  override def clear(): Unit = {
//    byteBuffer.clear()
//    _length = 0
//  }
//
//  override def length: Int = _length
//
//  override def apply(v1: Int): A = struct.read(StructRef(v1), byteBuffer)
//
//  override def result(): DynamicStructBuffer[A] = this
//
//  // TODO: Implement
//  override def +=:(elem: A): this.type = ???
//  override def update(n: Int, newelem: A): Unit = ???
//  override def remove(n: Int): A = ???
//  override def insertAll(n: Int, elems: Traversable[A]): Unit = ???
//
//  private lazy val byteBuffer = ByteBuffer.allocate(256)
//  private var _length = 0
//
//  private object DynamicStructBuffer extends SeqFactory[DynamicStructBuffer] {
//    implicit def canBuildFrom[T]: CanBuildFrom[Coll, T, DynamicStructBuffer[T]] =
//      ReusableCBF.asInstanceOf[GenericCanBuildFrom[T]]
//
//    def newBuilder[T]: cm.Builder[T, DynamicStructBuffer[T]] =
//      new DynamicStructBuffer[T](struct.asInstanceOf[Struct[T]])
//  }
//}
//
//class SmallDynamicStructBuffer[A](struct: Struct[A]) extends DynamicStructBuffer[A](struct) {
//  def appendSmall(elem: A): SmallStructRef = SmallStructRef(super.appendRef(elem).ref.toShort)
//}
//
//class TinyDynamicStructBuffer[A](struct: Struct[A]) extends SmallDynamicStructBuffer[A](struct) {
//  def appendTiny(elem: A): TinyStructRef = TinyStructRef(super.appendRef(elem).ref.toByte)
//}
