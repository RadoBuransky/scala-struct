[![Build Status](https://travis-ci.org/RadoBuransky/scala-struct.svg?branch=master)](https://travis-ci.org/RadoBuransky/scala-struct) [![Coverage Status](https://coveralls.io/repos/RadoBuransky/scala-struct/badge.png?branch=master)](https://coveralls.io/r/RadoBuransky/scala-struct?branch=master)
Scala Struct 1.0.0
==================

Scala library for memory-optimized structures.  

Easily store fixed-size data structures in a byte buffer and retrieve them later. Significantly reduces memory
requirements especially when large numbers of small structures have to be kept in memory.

Use as a dependency
-------------------

Configure your Maven, Gradle or SBT build script to use Sonatype OSS release repository and add dependency to Scala
Struct.

*Gradle*

```Groovy
repositories {
    maven { url "https://oss.sonatype.org/content/repositories/releases" }
}

dependencies {
    compile 'com.buransky:scala-struct:1.0.0'
}
```

*SBT*

```Scala
resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "com.buransky" % "scala-struct" % "1.0.0"
```

*Maven*

```XML
<repositories>
    <repository>
        <id>oss-sonatype</id>
        <name>oss-sonatype</name>
        <url>https://oss.sonatype.org/content/repositories/releases/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.buransky</groupId>
        <artifactId>scala-struct</artifactId>
        <version>1.0.0</version>
    </dependency>
<dependencies>
```

Download
---------------
[Get the latest binary JAR of Scala Struct](https://github.com/RadoBuransky/scala-struct/releases/download/v1.0.0/scala-struct-1.0.0.jar)

Simple example
--------------

```scala
object SimpleCaseClassExample {
  // Simple case class that we would like to store efficiently in the buffer,
  private case class Simple(num: Int, flag: Boolean)

  // Definition of Simple structure for the Simple case class.
  private val simpleStruct = Struct(Simple.apply _, Function.unlift(Simple.unapply))

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
```
    
6 times less memory example
---------------------------
This example compares Java heap memory consumption of Scala Struct with standard mutable ArrayBuffer. The result is that
Scala Struct takes *6 times less space* in memory. 5 MB versus 29 MB.

```scala
object SimpleMemoryComparisonExample {
  // Simple case class that we would like to store efficiently in the buffer,
  private case class Simple(num: Int, flag: Boolean)

  // Definition of Simple structure for the Simple case class.
  private val simpleStruct = Struct(Simple.apply _, Function.unlift(Simple.unapply))

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
```
    
Supported features
------------------
- **Basic value types** - Byte, Short, Int, Long, Float, Double, Boolean
- **Fixed buffer** - byte buffer which can store structres with fixed size
- **Case classes with up to 22 fields** - Scala limits tuple size to 22
