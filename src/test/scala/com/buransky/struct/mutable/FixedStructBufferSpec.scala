package com.buransky.struct.mutable

import com.buransky.struct.IntStruct
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks
import org.scalacheck.Gen

@RunWith(classOf[JUnitRunner])
class FixedStructBufferSpec extends FlatSpec with PropertyChecks {
  behavior of "append"

  private val smallCounts = for (n <- Gen.choose(0, 50)) yield n

  it should "increase size when elements are added" in {
    forAll(smallCounts) { (count: Int) =>
      val fb = new FixedStructBuffer(IntStruct)
      Range(0, count).foreach(fb += _)
      assert(fb.size === count)
    }
  }

  it should "fail if called after converting the buffer to a sequence" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb.toIndexedSeq
    intercept[IllegalStateException] {
      fb += 1
    }
  }

  it should "not add an element if called after converting the buffer to a sequence" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb.toIndexedSeq
    intercept[IllegalStateException] {
      fb += 1
    }
    assert(fb.size === 0)
  }

  behavior of "clear"

  it should "reset length" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb += 5
    assert(fb.length === 1)
    fb.clear()
    assert(fb.length === 0)
  }

  it should "fail if buffer has been converted to a sequence" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb += 5
    assert(fb.length === 1)
    fb.toIndexedSeq
    intercept[IllegalStateException] {
      fb.clear()
    }
    assert(fb.size === 1)
  }

  behavior of "toIndexedSeq"

  it should "return the same sequence when called multiple times" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb += 5
    val seq = fb.toIndexedSeq
    assert(fb.toIndexedSeq eq seq)
  }

  behavior of "apply"

  it should "get n-th value" in {
    val fb = new FixedStructBuffer(IntStruct)
    fb += 5
    fb += 98
    fb += -18
    assert(fb(1) === 98)
    assert(fb(0) === 5)
    assert(fb(2) === -18)
  }

  behavior of "prepend"

  it should "be not implemented yet" in {
    val fb = new FixedStructBuffer(IntStruct)
    intercept[NotImplementedError] {
      fb.+=:(1)
    }
  }

  behavior of "update"

  it should "be not implemented yet" in {
    val fb = new FixedStructBuffer(IntStruct)
    intercept[NotImplementedError] {
      fb.update(0, 1)
    }
  }

  behavior of "remove"

  it should "be not implemented yet" in {
    val fb = new FixedStructBuffer(IntStruct)
    intercept[NotImplementedError] {
      fb.remove(1)
    }
  }

  behavior of "insertAll"

  it should "be not implemented yet" in {
    val fb = new FixedStructBuffer(IntStruct)
    intercept[NotImplementedError] {
      fb.insertAll(40, List(1, 2))
    }
  }
}
