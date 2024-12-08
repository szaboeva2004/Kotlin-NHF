package models

import hu.bme.mit.theta.core.decl.VarDecl
import java.math.BigInteger

data class Btor2Const(override val nid: UInt, val value: BigInteger, override val sort: Btor2Sort) : Btor2Node(nid, sort)
{
    override fun getVar(): VarDecl<*>? {
        return null
    }
}