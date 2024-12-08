package visitors

import models.Btor2BitvecSort
import models.Btor2Circuit
import models.Btor2Circuit.sorts
import models.Btor2Const
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import org.example.btor2.frontend.dsl.gen.Btor2Parser.*
import java.math.BigInteger

class ConstantVisitor : Btor2BaseVisitor<Btor2Const>() {
    val idVisitor = IdVisitor()

    override fun visitConstantNode(ctx: Btor2Parser.ConstantNodeContext): Btor2Const {
        check(ctx.childCount==1)
        return ctx.children[0].accept(this)
    }
    override fun visitFilled_constant(ctx: Btor2Parser.Filled_constantContext): Btor2Const {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = sorts[sid?.toInt()!!] as Btor2BitvecSort
        val value = when(ctx.fill.text) {
            "one" -> {
                check(sort.width == 1u)
                BigInteger("1")
            }
            "ones" -> {
                BigInteger("1".repeat(sort.width.toInt()))
            }
            "zero" -> {
                BigInteger("0".repeat(sort.width.toInt()))
            }
            else -> {
                throw RuntimeException("Constant with filler shorthand needs to be one/ones/zero")
            }
        }
        var node = Btor2Const(nid, value, sort)
        Btor2Circuit.nodes.add(node)
        return node
    }
    override fun visitConstant(ctx: Btor2Parser.ConstantContext): Btor2Const {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = sorts[sid?.toInt()!!] as Btor2BitvecSort
        val value = BigInteger(ctx.bin.text, 2)
        return Btor2Const(nid, value, sort)
    }
    override fun visitConstant_d(ctx: Btor2Parser.Constant_dContext): Btor2Const {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = sorts[sid?.toInt()!!] as Btor2BitvecSort
        var value = BigInteger(ctx.dec.text)
        ctx.MINUS()?.let { value = value.multiply(BigInteger("-1")) }
        var node = Btor2Const(nid, value, sort)
        Btor2Circuit.nodes.add(node)
        return node
    }
    override fun visitConstant_h(ctx: Btor2Parser.Constant_hContext): Btor2Const {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = sorts[sid?.toInt()!!] as Btor2BitvecSort
        val value = BigInteger(ctx.hex.text, 16)
        var node = Btor2Const(nid, value, sort)
        Btor2Circuit.nodes.add(node)
        return node
    }
}