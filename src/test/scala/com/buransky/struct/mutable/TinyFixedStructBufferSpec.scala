package com.buransky.struct.mutable

import com.buransky.struct.TestData._
import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TinyFixedStructBufferSpec extends FlatSpec {
  behavior of "appendTiny"

  it should "return reference equal to index" in {
    val buffer = new TinyFixedStructBuffer(testStruct)

    assert(buffer.appendTiny(data1).ref === 0)
    assert(buffer.appendTiny(data2).ref === 1)
    assert(buffer.appendTiny(data3).ref === 2)
  }
}
