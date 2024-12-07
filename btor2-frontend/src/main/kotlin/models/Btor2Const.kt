package models

data class Btor2Const(override val nid: UInt, val value: Integer, override val sort: Btor2Sort) : Btor2Node(nid, sort)
