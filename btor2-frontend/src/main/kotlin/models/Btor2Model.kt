package models

import hu.bme.mit.theta.core.decl.Decls.Var
import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvExprs.BvType

object Btor2Circuit {
    var nodes: MutableList<Btor2Node> = mutableListOf()
    var sorts: MutableList<Btor2Sort> = mutableListOf()
}

// sortID lookup in Btor2Sort
abstract class Btor2Node(id: UInt, btor2Sort: Btor2Sort? = null) {
    abstract val nid: UInt
    abstract val sort: Btor2Sort?

    open fun getVar(): VarDecl<*>? {
        return null
    }
}

abstract class Btor2Sort(sid: UInt, width: UInt) {
    abstract val sid: UInt
    abstract val width: UInt
}

data class Btor2BitvecSort(override val sid: UInt, override val width: UInt) : Btor2Sort(sid, width)



