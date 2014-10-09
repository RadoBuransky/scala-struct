package com.buransky.struct.store

private[struct] trait ByteStore {
  /**
   * Appends a single Byte to this store and returns the identity of the store.
   * @param b the Byte to append.
   * @return the updated store.
   */
  def put(b: Byte): ByteStore

  /**
   * Appends a single Short to this store and returns the identity of the store.
   * @param s the Short to append.
   * @return the updated store.
   */
  def put(s: Short): ByteStore

  /**
   * Appends a single Int to this store and returns the identity of the store.
   * @param i the Int to append.
   * @return the updated store.
   */
  def put(i: Int): ByteStore

  /**
   * Appends a single Long to this store and returns the identity of the store.
   * @param l the Long to append.
   * @return the updated store.
   */
  def put(l: Long): ByteStore

  /**
   * Appends a single Float to this store and returns the identity of the store.
   * @param f the Float to append.
   * @return the updated store.
   */
  def put(f: Float): ByteStore

  /**
   * Appends a single Double to this store and returns the identity of the store.
   * @param d the Double to append.
   * @return the updated store.
   */
  def put(d: Double): ByteStore

  /**
   * Appends a single Boolean to this store and returns the identity of the store.
   * @param b the Boolean to append.
   * @return the updated store.
   */
  def put(b: Boolean): ByteStore

  /**
   * Read the next Byte.
   * @return the Byte read,
   */
  def readByte(): Byte

  /**
   * Read the next Short.
   * @return the Short read,
   */
  def readShort(): Short

  /**
   * Read the next Int.
   * @return the Int read,
   */
  def readInt(): Int

  /**
   * Read the next Long.
   * @return the Long read,
   */
  def readLong(): Long

  /**
   * Read the next Float.
   * @return the Float read,
   */
  def readFloat(): Float

  /**
   * Read the next Double.
   * @return the Double read,
   */
  def readDouble(): Double

  /**
   * Read the next Boolean.
   * @return the Boolean read,
   */
  def readBoolean(): Boolean

  /**
   * Clears this store.
   */
  def clear(): Unit

  /**
   * Maximal capacity of the byte store in bytes.
   */
  def capacity: Int

  /**
   * Gets actual position in bytes.
   * @return actual position in bytes.
   */
  def position: Int

  /**
   * Sets actual position in bytes.
   */
  def position_= (pos: Int)
}

private[struct] object ByteStore {
  val defaultInitialCapacity = 256
  val defaultGrowBy = 256

  def apply(initialCapacity: Int = defaultInitialCapacity, growBy: Int = defaultGrowBy): ByteStore = {
    if (growBy > 0)
      new GrowableByteStore(initialCapacity, growBy)
    else
      new ByteBufferStore(initialCapacity)
  }
}