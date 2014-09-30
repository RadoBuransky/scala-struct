package com.buransky.struct

/**
 * Reference to a stored structure for later retireval. Doesn't contain information about actual buffer.
 */
sealed trait StructRef extends Any

case class IntStructRef(ref: Int) extends AnyVal with StructRef
case class ShortStructRef(ref: Short) extends AnyVal with StructRef
case class ByteStructRef(ref: Byte) extends AnyVal with StructRef

private[struct] case object NilStructRef extends StructRef