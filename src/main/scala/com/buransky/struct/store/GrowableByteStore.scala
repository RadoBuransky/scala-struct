package com.buransky.struct.store

/**
 * Byte store which can increase its capacity when needed.
 *
 * @param initialCapacity Initial store capacity in bytes.
 * @param growBy Number of bytes to grow by when more capacity is needed.
 */
private[store] class GrowableByteStore(initialCapacity: Int, growBy: Int) extends ByteStore {
  if (initialCapacity < 0)
    throw new IllegalArgumentException("Capacity cannot be negative!")

  if (growBy < 0)
    throw new IllegalArgumentException("Grow by cannot be negative!")

  override def put(b: Byte) = ensureCapacity(1) { byteBufferStore.put(b) }
  override def put(s: Short) = ensureCapacity(2) { byteBufferStore.put(s) }
  override def put(i: Int) = ensureCapacity(4) { byteBufferStore.put(i) }
  override def put(l: Long) = ensureCapacity(8) { byteBufferStore.put(l) }
  override def put(f: Float) = ensureCapacity(4) { byteBufferStore.put(f) }
  override def put(d: Double) = ensureCapacity(8) { byteBufferStore.put(d) }
  override def put(b: Boolean) = ensureCapacity(1) { byteBufferStore.put(b) }
  override def readByte() = byteBufferStore.readByte()
  override def readShort() = byteBufferStore.readShort()
  override def readInt() = byteBufferStore.readInt()
  override def readLong() = byteBufferStore.readLong()
  override def readFloat() = byteBufferStore.readFloat()
  override def readDouble() = byteBufferStore.readDouble()
  override def readBoolean() = byteBufferStore.readBoolean()
  override def clear() = byteBufferStore.clear()
  override def position = byteBufferStore.position
  override def position_= (pos: Int) = byteBufferStore.position = pos
  override def capacity = byteBufferStore.capacity

  private var byteBufferStore = new ByteBufferStore(initialCapacity)

  private def ensureCapacity[T](requiredCapacity: Int)(f: => T): T = {
    val freeCapacity = capacity - position
    if (freeCapacity < requiredCapacity) {
      val newCapacity = capacity + Math.max(growBy, requiredCapacity - freeCapacity)

      // Grow byte buffer
      byteBufferStore = reallocateByteBufferStore(byteBufferStore, newCapacity)
    }

    f
  }

  private def reallocateByteBufferStore(srcByteBufferStore: ByteBufferStore, newCapacity: Int): ByteBufferStore = {
    val newByteBufferStore = new ByteBufferStore(newCapacity)

    // http://stackoverflow.com/a/4074089/1417723
    val pos = srcByteBufferStore.byteBuffer.position()
    srcByteBufferStore.byteBuffer.rewind()
    newByteBufferStore.byteBuffer.put(srcByteBufferStore.byteBuffer)
    newByteBufferStore.byteBuffer.position(pos)

    newByteBufferStore
  }
}
