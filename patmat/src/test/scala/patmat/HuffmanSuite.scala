package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("test times function") {
    assert(times(List('b','b','a'))===List(('b', 2), ('a', 1)))
    assert(times(List('b','c','a'))===List(('b', 1), ('a', 1), ('c', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("test singleton function") {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    assert(singleton(List(t1)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("createCodeTree test") {
    new TestTrees {
      assert(createCodeTree(List('a', 'b', 'a', 'c', 'b', 'a', 'a')) == Fork(Fork(Leaf('c', 1), Leaf('b', 2), List('c', 'b'), 3), Leaf('a', 4), List('c', 'b', 'a'), 7))
    }
  }

  test ("decode and encode frenchCode") {
    new TestTrees {
      assert(encode(frenchCode)(decodedSecret) == secret)
    }
  }
  test("fastencode and decode frenchCode") {
    new TestTrees{
      assert(quickEncode(frenchCode)(decodedSecret) == secret)
    }
  }
}
