package models

import hu.bme.mit.theta.core.decl.Decls
import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvExprs
import hu.bme.mit.theta.core.type.bvtype.BvType

// Inputs and States
data class Btor2Input(override val nid: UInt, override val sort: Btor2Sort) : Btor2Node(nid, sort)
{
    val value = Decls.Var("input_$nid", BvExprs.BvType(sort.width.toInt()))

    override fun getVar(): VarDecl<*>? {
        return value
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }

}
data class Btor2State(override val nid: UInt, override val sort: Btor2Sort) : Btor2Node(nid, sort) {
    val value = Decls.Var("state_$nid", BvExprs.BvType(sort.width.toInt()))

    override fun getVar(): VarDecl<BvType>? {
        return value
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}
data class Btor2Init(override val nid: UInt, override val sort: Btor2Sort, val state: Btor2State, val value: Btor2Const) : Btor2Node(nid, sort)
{
    val declsVar = Decls.Var("init_$nid", BvExprs.BvType(sort.width.toInt()))

    override fun getVar(): VarDecl<*>? {
        return declsVar
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}

data class Btor2Next(override val nid: UInt, override val sort: Btor2Sort, val state: Btor2State, val value: Btor2Node) : Btor2Node(nid, sort)
{
    val declsVar = Decls.Var("next_$nid", BvExprs.BvType(sort.width.toInt()))

    override fun getVar(): VarDecl<*>? {
        return declsVar
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}
data class Btor2Bad(override val nid: UInt, override val sort: Btor2Sort?, val operand: Btor2Node) : Btor2Node(nid, null)
{
    override fun getVar(): VarDecl<*>? {
        return null
    }
    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}