package models

import hu.bme.mit.theta.core.decl.Decls
import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvExprs


abstract class Btor2Operation(id: UInt, sort: Btor2Sort) : Btor2Node(id, sort) {
    override fun getVar(): VarDecl<*>? {
        return Decls.Var("operation_$nid", BvExprs.BvType(sort?.width?.toInt() ?: 0))
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
