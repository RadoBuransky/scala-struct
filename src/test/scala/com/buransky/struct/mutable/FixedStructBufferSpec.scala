package com.buransky.struct.mutable

import com.buransky.struct.TestData._
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FixedStructBufferSpec extends FlatSpec {
  behavior of "appendRef"

  it should "return reference equal to index" in {
    val buffer = new FixedStructBuffer(testStruct)

    assert(buffer.appendRef(data1).ref === 0)
    assert(buffer.appendRef(data2).ref === 1)
    assert(buffer.appendRef(data3).ref === 2)
  }
}
