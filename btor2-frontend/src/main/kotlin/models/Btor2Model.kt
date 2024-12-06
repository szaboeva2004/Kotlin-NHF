package models
// Magic Kotlin data class
data class Btor2Sort(val sid: UInt, val width: UInt)
// sortID lookup in Btor2Sort
abstract class Btor2Node {
    abstract var nid: UInt
    abstract var sort: Btor2Sort
}

object Btor2Circuit {
    var nodes: MutableList<Btor2Node> = mutableListOf()
    var sorts: MutableList<Btor2Sort> = mutableListOf()
}