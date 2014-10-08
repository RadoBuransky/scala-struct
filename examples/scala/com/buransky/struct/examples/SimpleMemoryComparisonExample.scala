package com.buransky.struct.examples

import com.buransky.struct.Struct
import com.buransky.struct.mutable.FixedStructBuffer

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 * This example compares Java heap memory consumption of Scala Struct with standard mutable ArrayBuffer.
 * The result is that Scala Struct takes 6 times less space in memory.
 */
object SimpleMemoryComparisonExample {
  /**
   * Simple case class that we would like to store efficiently in the buffer,
   */
  private case class Simple(num: Int, flag: Boolean)

  /**
   * Definition of Simple structure for the Simple case class.
   */
  private val simpleStruct = Struct(Simple.apply _, Function.unlift(Simple.unapply))

  /**
   * Example entry point.
   */
  def main(args: Array[String]): Unit = {
    // Test standard ArrayBuffer
    testArrayBuffer()

    // Test FixedStructBuffer
    testScalaStruct()
  }

  private def testArrayBuffer(): Unit = {
    // Create empty buffer
    val buffer = ArrayBuffer.empty[Simple]

    // This takes exactly 29,388,632 bytes in Java heap memory =
    // 8,388,632 bytes takes array itself (1,048,576 items on a 64-bit machine - 8 bytes per reference) +
    // 21,000,000 bytes take all Simple case class instances
    addMillionSimples(buffer)
  }

  private def testScalaStruct(): Unit = {
    // Create empty buffer
    val buffer = new FixedStructBuffer(simpleStruct)

    // This takes exactly 5,000,000 bytes in Java heap memory = (4 bytes int + 1 byte boolean) * 1 mil items
    addMillionSimples(buffer)
  }

  private def addMillionSimples(buffer: mutable.Buffer[Simple]): Unit = {
    // Append a million instances
    1 to 1000000 foreach { n =>
      buffer += Simple(n, true)
    }
  }
}
