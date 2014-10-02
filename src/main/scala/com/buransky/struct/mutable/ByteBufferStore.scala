package com.buransky.struct.mutable

import java.nio.ByteBuffer

import com.buransky.struct.ByteStore

private[mutable] class ByteBufferStore extends ByteStore {
  /**
   * Appends a single Byte to this store and returns the identity of the store.
   * @param b the Byte to append.
   * @return the updated store.
   */
  override def put(b: Byte): ByteStore = {
    byteBuffer.put(b)
    this
  }

  /**
   * Appends a single Short to this store and returns the identity of the store.
   * @param s the Short to append.
   * @return the updated store.
   */
  override def put(s: Short): ByteStore = {
    byteBuffer.putShort(s)
    this
  }

  /**
   * Appends a single Int to this store and returns the identity of the store.
   * @param i the Int to append.
   * @return the updated store.
   */
  override def put(i: Int): ByteStore = {
    byteBuffer.putInt(i)
    this
  }

  /**
   * Appends a single Long to this store and returns the identity of the store.
   * @param l the Long to append.
   * @return the updated store.
   */
  override def put(l: Long): ByteStore = {
    byteBuffer.putLong(l)
    this
  }

  /**
   * Appends a single Float to this store and returns the identity of the store.
   * @param f the Float to append.
   * @return the updated store.
   */
  override def put(f: Float): ByteStore = {
    byteBuffer.putFloat(f)
    this
  }

  /**
   * Appends a single Double to this store and returns the identity of the store.
   * @param d the Double to append.
   * @return the updated store.
   */
  override def put(d: Double): ByteStore = {
    byteBuffer.putDouble(d)
    this
  }

  /**
   * Appends a single Boolean to this store and returns the identity of the store.
   * @param b the Boolean to append.
   * @return the updated store.
   */
  override def put(b: Boolean): ByteStore = {
    byteBuffer.put((if (b) 1 else 0).toByte)
    this
  }

  /**
   * Read the next Byte.
   * @return the Byte read,
   */
  override def readByte(): Byte = byteBuffer.get()

  /**
   * Read the next Short.
   * @return the Short read,
   */
  override def readShort(): Short = byteBuffer.getShort
  /**
   * Read the next Int.
   * @return the Int read,
   */
  override def readInt(): Int = byteBuffer.getInt

  /**
   * Read the next Long.
   * @return the Long read,
   */
  override def readLong(): Long = byteBuffer.getLong

  /**
   * Read the next Float.
   * @return the Float read,
   */
  override def readFloat(): Float = byteBuffer.getFloat

  /**
   * Read the next Double.
   * @return the Double read,
   */
  override def readDouble(): Double = byteBuffer.getDouble

  /**
   * Read the next Boolean.
   * @return the Boolean read,
   */
  override def readBoolean(): Boolean = byteBuffer.get() != 0

  /**
   * Clears this store.
   */
  def clear(): Unit = byteBuffer.clear()

  /**
   * Gets actual position in bytes.
   * @return actual position in bytes.
   */
  def position: Int = byteBuffer.position()

  /**
   * Sets actual position in bytes.
   */
  def position_= (pos: Int): Unit = byteBuffer.position(pos)

  /**
   * Underlying byte buffer. The real storage.
   */
  private lazy val byteBuffer = ByteBuffer.allocate(256)
}
