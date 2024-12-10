package models

import hu.bme.mit.theta.core.decl.VarDecl
import hu.bme.mit.theta.core.type.bvtype.BvLitExpr
import java.math.BigInteger

data class Btor2Const(override val nid: UInt, val value: BooleanArray, override val sort: Btor2Sort) : Btor2Node(nid, sort)
{
    override fun getVar(): VarDecl<*>? {
        return null
    }

    fun getExpr() : BvLitExpr {
        return BvLitExpr.of(value)
    }

    override fun <R, P> accept(visitor: Btor2NodeVisitor<R, P>, param : P): R {
        return visitor.visit(this, param)
    }
}