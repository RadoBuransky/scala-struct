package com.buransky.struct

private[struct] object TestData {
  case class TestClass(a: Int, b: Boolean)
  val testStruct = Struct(TestClass.apply _, Function.unlift(TestClass.unapply))

  val data1 = TestClass(1, true)
  val data2 = TestClass(-42, false)
  val data3 = TestClass(Int.MaxValue, false)
}
