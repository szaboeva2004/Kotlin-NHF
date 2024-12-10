package models

import hu.bme.mit.theta.core.decl.Decls.Var
import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvExprs.BvType
import visitors.Btor2Visitor

public interface Btor2NodeVisitor<R, P> {
    fun visit(node: Btor2UnaryOperation, param: P) : R
    fun visit(node: Btor2BinaryOperation, param: P) : R
    fun visit(node: Btor2SliceOperation, param: P) : R
    fun visit(node: Btor2ExtOperation, param: P) : R
    fun visit(node: Btor2Const, param: P) : R
    fun visit(node: Btor2BitvecSort, param: P) : R
    fun visit(node: Btor2Input, param: P) : R
    fun visit(node: Btor2State, param: P) : R
    fun visit(node: Btor2Init, param: P) : R
    fun visit(node: Btor2Next, param: P) : R
    fun visit(node: Btor2Bad, param: P) : R
}


object Btor2Circuit {
    var nodes: MutableMap<UInt, Btor2Node> = mutableMapOf()
    var sorts: MutableMap<UInt, Btor2Sort> = mutableMapOf()
    var inits: MutableMap<UInt, Btor2Init> = mutableMapOf()
}

// sortID lookup in Btor2Sort
abstract class Btor2Node(id: UInt, btor2Sort: Btor2Sort? = null) {
    abstract val nid: UInt
    abstract val sort: Btor2Sort?

    open fun getVar(): VarDecl<*>? {
        return null
    }

    abstract fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R
}

abstract class Btor2Sort(sid: UInt, width: UInt) {
    abstract val sid: UInt
    abstract val width: UInt
}

data class Btor2BitvecSort(override val sid: UInt, override val width: UInt) : Btor2Sort(sid, width)




