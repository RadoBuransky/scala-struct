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
}
