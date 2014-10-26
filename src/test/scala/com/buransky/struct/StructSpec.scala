package com.buransky.struct

import com.buransky.struct.store.ByteStore
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.scalatest.prop.PropertyChecks
import org.mockito.Mockito._

@RunWith(classOf[JUnitRunner])
class StructSpec extends FlatSpec with PropertyChecks with MockitoSugar {
  behavior of "apply"

  it should "work for 1 parameter" in {
    case class Struct1(a1: Int)

    val s = Struct(Struct1.apply _, Function.unlift(Struct1.unapply))
    forAll { (a1: Int) =>
      val s1 = Struct1(a1)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)

      // Read
      when(bs.readInt()).thenReturn(a1)
      assert(s.read(bs) === s1)
    }
  }

  it should "work for 2 parameters" in {
    case class Struct2(a1: Byte, a2: Short)

    val s = Struct(Struct2.apply _, Function.unlift(Struct2.unapply))
    forAll { (a1: Byte, a2: Short) =>
      val s1 = Struct2(a1 ,a2)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)
      verify(bs).put(a2)

      // Read
      when(bs.readByte()).thenReturn(a1)
      when(bs.readShort()).thenReturn(a2)
      assert(s.read(bs) === s1)
    }
  }

  it should "work for 3 parameters" in {
    case class Struct3(a1: Long, a2: Float, a3: Boolean)

    val s = Struct(Struct3.apply _, Function.unlift(Struct3.unapply))
    forAll { (a1: Long, a2: Float, a3: Boolean) =>
      val s1 = Struct3(a1 ,a2, a3)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)
      verify(bs).put(a2)
      verify(bs).put(a3)

      // Read
      when(bs.readLong()).thenReturn(a1)
      when(bs.readFloat()).thenReturn(a2)
      when(bs.readBoolean()).thenReturn(a3)
      assert(s.read(bs) === s1)
    }
  }

  it should "work for 4 parameters" in {
    case class Struct4(a1: Long, a2: Float, a3: Boolean, a4: Byte)

    val s = Struct(Struct4.apply _, Function.unlift(Struct4.unapply))
    forAll { (a1: Long, a2: Float, a3: Boolean, a4: Byte) =>
      val s1 = Struct4(a1 ,a2, a3, a4)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)
      verify(bs).put(a2)
      verify(bs).put(a3)
      verify(bs).put(a4)

      // Read
      when(bs.readLong()).thenReturn(a1)
      when(bs.readFloat()).thenReturn(a2)
      when(bs.readBoolean()).thenReturn(a3)
      when(bs.readByte()).thenReturn(a4)
      assert(s.read(bs) === s1)
    }
  }

  it should "work for 5 parameters" in {
    case class Struct5(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double)

    val s = Struct(Struct5.apply _, Function.unlift(Struct5.unapply))
    forAll { (a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double) =>
      val s1 = Struct5(a1 ,a2, a3, a4, a5)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)
      verify(bs).put(a2)
      verify(bs).put(a3)
      verify(bs).put(a4)
      verify(bs).put(a5)

      // Read
      when(bs.readLong()).thenReturn(a1)
      when(bs.readFloat()).thenReturn(a2)
      when(bs.readBoolean()).thenReturn(a3)
      when(bs.readByte()).thenReturn(a4)
      when(bs.readDouble()).thenReturn(a5)
      assert(s.read(bs) === s1)
    }
  }

  it should "work for 6 parameters" in {
    case class Struct6(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int)

    val s = Struct(Struct6.apply _, Function.unlift(Struct6.unapply))
    forAll { (a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int) =>
      val s1 = Struct6(a1 ,a2, a3, a4, a5, a6)
      val bs = mock[ByteStore]

      // Put
      s.put(s1, bs)
      verify(bs).put(a1)
      verify(bs).put(a2)
      verify(bs).put(a3)
      verify(bs).put(a4)
      verify(bs).put(a5)
      verify(bs).put(a6)

      // Read
      when(bs.readLong()).thenReturn(a1)
      when(bs.readFloat()).thenReturn(a2)
      when(bs.readBoolean()).thenReturn(a3)
      when(bs.readByte()).thenReturn(a4)
      when(bs.readDouble()).thenReturn(a5)
      when(bs.readInt()).thenReturn(a6)
      assert(s.read(bs) === s1)
    }
  }

  val byte: Byte = -32
  val short: Short = -31532
  val int: Int = 5133035
  val long: Long = 4345435300l
  val float: Float = 65.7f
  val double: Double = -6987464.35431321
  val boolean: Boolean = true

  it should "work for 7 parameters" in {
    case class Struct7(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float)

    val s = Struct(Struct7.apply _, Function.unlift(Struct7.unapply))
    val s1 = Struct7(long, float, boolean, byte, double, int, float)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 8 parameters" in {
    case class Struct8(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte)

    val s = Struct(Struct8.apply _, Function.unlift(Struct8.unapply))
    val s1 = Struct8(long, float, boolean, byte, double, int, float, byte)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 9 parameters" in {
    case class Struct9(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double)

    val s = Struct(Struct9.apply _, Function.unlift(Struct9.unapply))
    val s1 = Struct9(long, float, boolean, byte, double, int, float, byte, double)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 10 parameters" in {
    case class Struct10(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int)

    val s = Struct(Struct10.apply _, Function.unlift(Struct10.unapply))
    val s1 = Struct10(long, float, boolean, byte, double, int, float, byte, double, int)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 11 parameters" in {
    case class Struct11(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean)

    val s = Struct(Struct11.apply _, Function.unlift(Struct11.unapply))
    val s1 = Struct11(long, float, boolean, byte, double, int, float, byte, double, int, boolean)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 12 parameters" in {
    case class Struct12(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short)

    val s = Struct(Struct12.apply _, Function.unlift(Struct12.unapply))
    val s1 = Struct12(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 13 parameters" in {
    case class Struct13(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float)

    val s = Struct(Struct13.apply _, Function.unlift(Struct13.unapply))
    val s1 = Struct13(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 14 parameters" in {
    case class Struct14(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int)

    val s = Struct(Struct14.apply _, Function.unlift(Struct14.unapply))
    val s1 = Struct14(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 15 parameters" in {
    case class Struct15(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int)

    val s = Struct(Struct15.apply _, Function.unlift(Struct15.unapply))
    val s1 = Struct15(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 16 parameters" in {
    case class Struct16(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short)

    val s = Struct(Struct16.apply _, Function.unlift(Struct16.unapply))
    val s1 = Struct16(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 17 parameters" in {
    case class Struct17(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte)

    val s = Struct(Struct17.apply _, Function.unlift(Struct17.unapply))
    val s1 = Struct17(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 18 parameters" in {
    case class Struct18(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte,
                         a18: Double)

    val s = Struct(Struct18.apply _, Function.unlift(Struct18.unapply))
    val s1 = Struct18(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte, double)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 19 parameters" in {
    case class Struct19(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte,
                        a18: Double, a19: Float)

    val s = Struct(Struct19.apply _, Function.unlift(Struct19.unapply))
    val s1 = Struct19(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte, double, float)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 20 parameters" in {
    case class Struct20(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte,
                        a18: Double, a19: Float, a20: Int)

    val s = Struct(Struct20.apply _, Function.unlift(Struct20.unapply))
    val s1 = Struct20(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte, double, float, int)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 21 parameters" in {
    case class Struct20(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte,
                        a18: Double, a19: Float, a20: Int, a21: Short)

    val s = Struct(Struct20.apply _, Function.unlift(Struct20.unapply))
    val s1 = Struct20(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte, double, float, int, short)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  it should "work for 22 parameters" in {
    case class Struct20(a1: Long, a2: Float, a3: Boolean, a4: Byte, a5: Double, a6: Int, a7: Float, a8: Byte, a9: Double,
                        a10: Int, a11: Boolean, a12: Short, a13: Float, a14: Int, a15: Int, a16: Short, a17: Byte,
                        a18: Double, a19: Float, a20: Int, a21: Short, a22: Float)

    val s = Struct(Struct20.apply _, Function.unlift(Struct20.unapply))
    val s1 = Struct20(long, float, boolean, byte, double, int, float, byte, double, int, boolean, short, float, int,
      int, short, byte, double, float, int, short, float)

    testStruct(s, s1, s1.productIterator.toSeq)
  }

  private def testStruct[A](struct: Struct[A], data: A, args: Seq[Any]): Unit = {
    val bs = mock[ByteStore]

    val byteCount = args.count(_.isInstanceOf[Byte])
    val shortCount = args.count(_.isInstanceOf[Short])
    val intCount = args.count(_.isInstanceOf[Int])
    val longCount = args.count(_.isInstanceOf[Long])
    val floatCount = args.count(_.isInstanceOf[Float])
    val doubleCount = args.count(_.isInstanceOf[Double])
    val booleanCount = args.count(_.isInstanceOf[Boolean])

    // Put
    struct.put(data, bs)
    verify(bs, times(byteCount)).put(byte)
    verify(bs, times(shortCount)).put(short)
    verify(bs, times(intCount)).put(int)
    verify(bs, times(longCount)).put(long)
    verify(bs, times(floatCount)).put(float)
    verify(bs, times(doubleCount)).put(double)
    verify(bs, times(booleanCount)).put(boolean)

    // Read
    when(bs.readByte()).thenReturn(byte)
    when(bs.readShort()).thenReturn(short)
    when(bs.readInt()).thenReturn(int)
    when(bs.readLong()).thenReturn(long)
    when(bs.readFloat()).thenReturn(float)
    when(bs.readDouble()).thenReturn(double)
    when(bs.readBoolean()).thenReturn(boolean)
    assert(struct.read(bs) === data)
  }
}
