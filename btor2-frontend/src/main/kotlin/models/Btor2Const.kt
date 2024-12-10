package models

import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.Expr
import hu.bme.mit.theta.core.type.bvtype.BvLitExpr

data class Btor2Const(override val nid: UInt, val value: BooleanArray, override val sort: Btor2Sort) : Btor2Node(nid, sort)
{
    override fun getVar(): VarDecl<*>? {
        return null
    }

    override fun getExpr(): Expr<*> {
        return BvLitExpr.of(value)
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}