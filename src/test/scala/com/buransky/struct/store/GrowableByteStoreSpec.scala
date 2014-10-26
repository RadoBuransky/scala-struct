package com.buransky.struct.store

import org.junit.runner.RunWith
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.PropertyChecks

@RunWith(classOf[JUnitRunner])
class GrowableByteStoreSpec extends FlatSpec with PropertyChecks {
  private val smallInitialCapacity = for (n <- Gen.choose(0, 9)) yield n
  private val smallGrowBy = for (n <- Gen.choose(1, 9)) yield n

  behavior of "put byte"

  it should "ensure to grow" in {
    testPut(1, _.put(_: Byte), _.readByte())
  }

  behavior of "put short"

  it should "ensure to grow" in {
    testPut(2, _.put(_: Short), _.readShort())
  }

  behavior of "put int"

  it should "ensure to grow" in {
    testPut(4, _.put(_: Int), _.readInt())
  }

  behavior of "put long"

  it should "ensure to grow" in {
    testPut(8, _.put(_: Long), _.readLong())
  }

  behavior of "put float"

  it should "ensure to grow" in {
    testPut(4, _.put(_: Float), _.readFloat())
  }

  behavior of "put double"

  it should "ensure to grow" in {
    testPut(8, _.put(_: Double), _.readDouble())
  }

  behavior of "put boolean"

  it should "ensure to grow" in {
    testPut(1, _.put(_: Boolean), _.readBoolean())
  }

  behavior of "clear"

  it should "ensure to grow" in {
    val byteStore = new GrowableByteStore(1, 1)
    byteStore.put(3: Byte)
    assert(byteStore.position === 1)
    byteStore.clear()
    assert(byteStore.position === 0)
  }

  private def putTwo[T](capacity: Int, growBy: Int, nums: List[T], tSize: Int,
                        put: (ByteStore, T) => Unit, read: (ByteStore) => T): Unit = {
    val byteStore = new GrowableByteStore(capacity, growBy)

    assert(byteStore.capacity === capacity)

    var cap = capacity
    var totalSize = 0
    nums.foreach { num =>
      put(byteStore, num)
      totalSize += tSize

      cap = newCapacity(cap, growBy, totalSize)
      assert(byteStore.capacity === cap)
    }

    byteStore.position = 0

    nums.foreach { num =>
      assert(read(byteStore) === num)
    }
  }

  private def newCapacity(capacity: Int, growBy: Int, tSize: Int): Int = {
    if (tSize <= capacity)
      capacity
    else
    if (tSize - capacity > growBy)
      tSize
    else
      capacity + growBy
  }

  private def testPut[T](size: Int,putT: (ByteStore, T) => Unit, readT: (ByteStore) => T)(implicit arbA: Arbitrary[T]) {
    forAll(smallInitialCapacity, smallGrowBy) { (capacity, growBy) =>
      forAll { (nums: List[T]) =>
        putTwo(capacity, growBy, nums, size, putT, readT)
      }
    }
  }
}
