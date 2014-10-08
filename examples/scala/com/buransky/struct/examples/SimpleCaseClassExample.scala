package com.buransky.struct.examples

import com.buransky.struct._
import com.buransky.struct.mutable.FixedStructBuffer

object SimpleCaseClassExample {
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
    // Create empty buffer
    val buffer = new FixedStructBuffer(simpleStruct)

    // Append a few instances
    buffer += Simple(42, true)
    buffer += Simple(1, false)
    buffer += Simple(-777, false)

    // When done, retrieve immutable indexed sequence for further usage
    val seq = buffer.toIndexedSeq

    // Let's print all case classes for example
    seq.foreach(println)

    // Output:
    // Simple(42,true)
    // Simple(1,false)
    // Simple(-777,false)
  }
}
