package models

import hu.bme.mit.theta.core.decl.Decls.Var
import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvExprs.BvType

// Magic Kotlin data class
abstract class Btor2Sort(sid: UInt, width: UInt) {
    abstract val sid: UInt
    abstract val width: UInt
}

data class Btor2BitvecSort(override val sid: UInt, override val width: UInt) : Btor2Sort(sid, width)

// sortID lookup in Btor2Sort
abstract class Btor2Node(id: UInt, btor2Sort: Btor2Sort) {
    abstract val nid: UInt
    abstract val sort: Btor2Sort

    open fun getVar(): VarDecl<*>? {
        return null
    }
}

object Btor2Circuit {
    var nodes: MutableList<Btor2Node> = mutableListOf()
    var sorts: MutableList<Btor2Sort> = mutableListOf()
}

abstract class Btor2Operation(id: UInt, sort: Btor2Sort) : Btor2Node(id, sort) {
    override fun getVar(): VarDecl<*>? {
        return Var("operation_$nid", BvType(sort.width.toInt()))
    }
}

// Operators
data class Btor2UnaryOperation(override val nid: UInt, override val sort : Btor2Sort, val operator: Btor2UnaryOperator, val operand: Btor2Node) : Btor2Operation(nid, sort)

data class Btor2ExtOperation(override val nid: UInt, override val sort : Btor2Sort, val operand: Btor2Node, val w : UInt) : Btor2Operation(nid, sort)

data class Btor2SliceOperation(override val nid: UInt, override val sort : Btor2Sort, val operand: Btor2Node, val u : UInt, val l : UInt) : Btor2Operation(nid, sort)

data class Btor2BinaryOperation(override val nid: UInt, override val sort : Btor2Sort, val operator: Btor2BinaryOperator, val op1: Btor2Node, val op2: Btor2Node) : Btor2Operation(nid, sort)

enum class Btor2UnaryOperator {
    NOT,
    INC,
    DEC,
    NEG
}

enum class Btor2BinaryOperator {
    AND,
    NAND,
    NOR,
    OR,
    XOR,
    EQ,
    NEQ,
    SLT,
    SLE,
    SGT,
    SGTE,
    ULT,
    ULE,
    UGT,
    UGTE,
    ADD,
    MUL,
    UDIV,
    UREM,
    SDIV,
    SREM,
    SMOD
}

data class Btor2Const(override val nid: UInt, val value: Integer, override val sort: Btor2Sort) : Btor2Node(nid, sort)

// Inputs and States
data class Btor2Input(override val nid: UInt, override val sort: Btor2Sort) : Btor2Node(nid, sort)

data class Btor2State(override val nid: UInt, override val sort: Btor2Sort) : Btor2Node(nid, sort) {
    override fun getVar(): VarDecl<*>? {
        return Var("state_$nid", BvType(sort.width.toInt()))
    }
}
data class Btor2Init(override val nid: UInt, override val sort: Btor2Sort, val state: Btor2State, val value: Btor2Const) : Btor2Node(nid, sort)

data class Btor2Next(override val nid: UInt, override val sort: Btor2Sort, val state: Btor2State, val value: Btor2Node) : Btor2Node(nid, sort)