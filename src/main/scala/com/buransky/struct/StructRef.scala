package com.buransky.struct

//TODO: Reference to a mutable store doesn't make sense. What if I delete something at the beginning?

/**
 * Reference to a structure within a given ByteStore. It can be used for direct retrieval.
 * @param ref reference to a stored structure
 */
case class StructRef(ref: Int) extends AnyVal
case class SmallStructRef(ref: Short) extends AnyVal
case class TinyStructRef(ref: Byte) extends AnyVal