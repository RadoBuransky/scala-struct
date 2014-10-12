package com.buransky.struct

import com.buransky.struct.store.ByteStore

/**
 * Definition of a structure for the given type.
 * @tparam T type of entity to be stored by the structure
 */
private[struct] trait Struct[T] {
  /**
   * Stores provided instance in the byte store,
   * @param inst instance of type T to be stored
   * @param byteStore byte store to hold the data
   */
  def put(inst: T, byteStore: ByteStore): Unit

  /**
   * Reads instance from the byte store.
   * @param byteStore byte store to retrieve data from
   * @return instance created from data read
   */
  def read(byteStore: ByteStore): T
}

/**
 * Companion object, Provides factory methods for structures.
 */
object Struct {
  def apply[A1, B](applyB: Function1[A1, B], unapplyB: Function1[B, Tuple1[A1]])
                      (implicit a1Struct: Struct[A1]) = {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, B](applyB: Function2[A1, A2, B], unapplyB: Function1[B, Tuple2[A1, A2]])
                      (implicit a1Struct: Struct[A1], a2Struct: Struct[A2]) = {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, B](applyB: Function3[A1, A2, A3, B],
                           unapplyB: Function1[B, Tuple3[A1, A2, A3]])
                          (implicit a1Struct: Struct[A1],
                           a2Struct: Struct[A2],
                           a3Struct: Struct[A3]) = {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, B](applyB: Function4[A1, A2, A3, A4, B],
                               unapplyB: Function1[B, Tuple4[A1, A2, A3, A4]])
                              (implicit a1Struct: Struct[A1],
                               a2Struct: Struct[A2],
                               a3Struct: Struct[A3],
                               a4Struct: Struct[A4])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, B](applyB: Function5[A1, A2, A3, A4, A5, B],
                               unapplyB: Function1[B, Tuple5[A1, A2, A3, A4, A5]])
                              (implicit a1Struct: Struct[A1],
                               a2Struct: Struct[A2],
                               a3Struct: Struct[A3],
                               a4Struct: Struct[A4],
                               a5Struct: Struct[A5])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, B](applyB: Function6[A1, A2, A3, A4, A5, A6, B],
                                   unapplyB: Function1[B, Tuple6[A1, A2, A3, A4, A5, A6]])
                                  (implicit a1Struct: Struct[A1],
                                   a2Struct: Struct[A2],
                                   a3Struct: Struct[A3],
                                   a4Struct: Struct[A4],
                                   a5Struct: Struct[A5],
                                   a6Struct: Struct[A6])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, B](applyB: Function7[A1, A2, A3, A4, A5, A6, A7, B],
                                       unapplyB: Function1[B, Tuple7[A1, A2, A3, A4, A5, A6, A7]])
                                      (implicit a1Struct: Struct[A1],
                                       a2Struct: Struct[A2],
                                       a3Struct: Struct[A3],
                                       a4Struct: Struct[A4],
                                       a5Struct: Struct[A5],
                                       a6Struct: Struct[A6],
                                       a7Struct: Struct[A7])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, B](applyB: Function8[A1, A2, A3, A4, A5, A6, A7, A8, B],
                                           unapplyB: Function1[B, Tuple8[A1, A2, A3, A4, A5, A6, A7, A8]])
                                          (implicit a1Struct: Struct[A1],
                                           a2Struct: Struct[A2],
                                           a3Struct: Struct[A3],
                                           a4Struct: Struct[A4],
                                           a5Struct: Struct[A5],
                                           a6Struct: Struct[A6],
                                           a7Struct: Struct[A7],
                                           a8Struct: Struct[A8])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, B](applyB: Function9[A1, A2, A3, A4, A5, A6, A7, A8, A9, B],
                                               unapplyB: Function1[B, Tuple9[A1, A2, A3, A4, A5, A6, A7, A8, A9]])
                                              (implicit a1Struct: Struct[A1],
                                               a2Struct: Struct[A2],
                                               a3Struct: Struct[A3],
                                               a4Struct: Struct[A4],
                                               a5Struct: Struct[A5],
                                               a6Struct: Struct[A6],
                                               a7Struct: Struct[A7],
                                               a8Struct: Struct[A8],
                                               a9Struct: Struct[A9])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, B]
    (applyB: Function10[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, B],
    unapplyB: Function1[B, Tuple10[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10]])
    (implicit a1Struct: Struct[A1],
    a2Struct: Struct[A2],
    a3Struct: Struct[A3],
    a4Struct: Struct[A4],
    a5Struct: Struct[A5],
    a6Struct: Struct[A6],
    a7Struct: Struct[A7],
    a8Struct: Struct[A8],
    a9Struct: Struct[A9],
    a10Struct: Struct[A10])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, B]
  (applyB: Function11[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, B],
   unapplyB: Function1[B, Tuple11[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, B]
  (applyB: Function12[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, B],
   unapplyB: Function1[B, Tuple12[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, B]
  (applyB: Function13[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, B],
   unapplyB: Function1[B, Tuple13[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, B]
  (applyB: Function14[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, B],
   unapplyB: Function1[B, Tuple14[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, B]
  (applyB: Function15[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, B],
   unapplyB: Function1[B, Tuple15[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, B]
  (applyB: Function16[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, B],
   unapplyB: Function1[B, Tuple16[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, B]
  (applyB: Function17[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, B],
   unapplyB: Function1[B, Tuple17[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, B]
  (applyB: Function18[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, B],
   unapplyB: Function1[B, Tuple18[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17],
   a18Struct: Struct[A18])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
        a18Struct.put(a._18, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore), a18Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, B]
  (applyB: Function19[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, B],
   unapplyB: Function1[B, Tuple19[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17],
   a18Struct: Struct[A18],
   a19Struct: Struct[A19])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
        a18Struct.put(a._18, byteStore)
        a19Struct.put(a._19, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore), a18Struct.read(byteStore), a19Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, B]
  (applyB: Function20[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, B],
   unapplyB: Function1[B, Tuple20[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17],
   a18Struct: Struct[A18],
   a19Struct: Struct[A19],
   a20Struct: Struct[A20])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
        a18Struct.put(a._18, byteStore)
        a19Struct.put(a._19, byteStore)
        a20Struct.put(a._20, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore), a18Struct.read(byteStore), a19Struct.read(byteStore), a20Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, B]
  (applyB: Function21[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, B],
   unapplyB: Function1[B, Tuple21[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17],
   a18Struct: Struct[A18],
   a19Struct: Struct[A19],
   a20Struct: Struct[A20],
   a21Struct: Struct[A21])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
        a18Struct.put(a._18, byteStore)
        a19Struct.put(a._19, byteStore)
        a20Struct.put(a._20, byteStore)
        a21Struct.put(a._21, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore), a18Struct.read(byteStore), a19Struct.read(byteStore), a20Struct.read(byteStore),
          a21Struct.read(byteStore))
      }
    }
  }

  def apply[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, B]
  (applyB: Function22[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, B],
   unapplyB: Function1[B, Tuple22[A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22]])
  (implicit a1Struct: Struct[A1],
   a2Struct: Struct[A2],
   a3Struct: Struct[A3],
   a4Struct: Struct[A4],
   a5Struct: Struct[A5],
   a6Struct: Struct[A6],
   a7Struct: Struct[A7],
   a8Struct: Struct[A8],
   a9Struct: Struct[A9],
   a10Struct: Struct[A10],
   a11Struct: Struct[A11],
   a12Struct: Struct[A12],
   a13Struct: Struct[A13],
   a14Struct: Struct[A14],
   a15Struct: Struct[A15],
   a16Struct: Struct[A16],
   a17Struct: Struct[A17],
   a18Struct: Struct[A18],
   a19Struct: Struct[A19],
   a20Struct: Struct[A20],
   a21Struct: Struct[A21],
   a22Struct: Struct[A22])= {
    new Struct[B] {
      override def put(t: B, byteStore: ByteStore) = {
        val a = unapplyB(t)
        a1Struct.put(a._1, byteStore)
        a2Struct.put(a._2, byteStore)
        a3Struct.put(a._3, byteStore)
        a4Struct.put(a._4, byteStore)
        a5Struct.put(a._5, byteStore)
        a6Struct.put(a._6, byteStore)
        a7Struct.put(a._7, byteStore)
        a8Struct.put(a._8, byteStore)
        a9Struct.put(a._9, byteStore)
        a10Struct.put(a._10, byteStore)
        a11Struct.put(a._11, byteStore)
        a12Struct.put(a._12, byteStore)
        a13Struct.put(a._13, byteStore)
        a14Struct.put(a._14, byteStore)
        a15Struct.put(a._15, byteStore)
        a16Struct.put(a._16, byteStore)
        a17Struct.put(a._17, byteStore)
        a18Struct.put(a._18, byteStore)
        a19Struct.put(a._19, byteStore)
        a20Struct.put(a._20, byteStore)
        a21Struct.put(a._21, byteStore)
        a22Struct.put(a._22, byteStore)
      }

      override def read(byteStore: ByteStore): B = {
        applyB(a1Struct.read(byteStore), a2Struct.read(byteStore), a3Struct.read(byteStore), a4Struct.read(byteStore),
          a5Struct.read(byteStore), a6Struct.read(byteStore), a7Struct.read(byteStore), a8Struct.read(byteStore),
          a9Struct.read(byteStore), a10Struct.read(byteStore), a11Struct.read(byteStore), a12Struct.read(byteStore),
          a13Struct.read(byteStore), a14Struct.read(byteStore), a15Struct.read(byteStore), a16Struct.read(byteStore),
          a17Struct.read(byteStore), a18Struct.read(byteStore), a19Struct.read(byteStore), a20Struct.read(byteStore),
          a21Struct.read(byteStore), a22Struct.read(byteStore))
      }
    }
  }
}