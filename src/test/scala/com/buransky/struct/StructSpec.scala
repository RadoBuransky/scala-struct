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
}
