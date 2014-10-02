package com.buransky.struct.mutable

import com.buransky.struct.TestData._
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SmallFixedStructBufferSpec extends FlatSpec {
  behavior of "appendSmall"

  it should "return reference equal to index" in {
    val buffer = new SmallFixedStructBuffer(testStruct)

    assert(buffer.appendSmall(data1).ref === 0)
    assert(buffer.appendSmall(data2).ref === 1)
    assert(buffer.appendSmall(data3).ref === 2)
  }
}
