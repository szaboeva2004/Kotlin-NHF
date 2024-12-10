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
        return this.visit(ctx.children[0])
    }
    override fun visitFilled_constant(ctx: Btor2Parser.Filled_constantContext): Btor2Const {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = sorts[sid] as Btor2BitvecSort
        val value = when(ctx.fill.text) {
            "one" -> {
                val size = sort.width.toInt()
                BooleanArray(size) { it == size - 1 }
            }
            "ones" -> {
                val size = sort.width.toInt()
                BooleanArray(size) { true }
            }
            "zero" -> {
                val size = sort.width.toInt()
                BooleanArray(size) { false }
            }
            else -> {
                throw RuntimeException("Constant with filler shorthand needs to be one/ones/zero")
            }
        }
        var node = Btor2Const(nid, value, sort)
        Btor2Circuit.nodes[nid] = node
        return node
    }
   // override fun visitConstant(ctx: Btor2Parser.ConstantContext): Btor2Const {
   //     val nid = idVisitor.visit(ctx.id)
   //     val sid = idVisitor.visit(ctx.sid())
   //     val sort : Btor2BitvecSort = sorts[sid] as Btor2BitvecSort
   //     val value = BigInteger(ctx.bin.text, 2)
   //     return Btor2Const(nid, value, sort)
   // }
   // override fun visitConstant_d(ctx: Btor2Parser.Constant_dContext): Btor2Const {
   //     val nid = idVisitor.visit(ctx.id)
   //     val sid = idVisitor.visit(ctx.sid())
   //     val sort : Btor2BitvecSort = sorts[sid] as Btor2BitvecSort
   //     var value = BigInteger(ctx.dec.text)
   //     ctx.MINUS()?.let { value = value.multiply(BigInteger("-1")) }
   //     var node = Btor2Const(nid, value, sort)
   //     Btor2Circuit.nodes[nid] = node
   //     return node
   // }
   // override fun visitConstant_h(ctx: Btor2Parser.Constant_hContext): Btor2Const {
   //     val nid = idVisitor.visit(ctx.id)
   //     val sid = idVisitor.visit(ctx.sid())
   //     val sort : Btor2BitvecSort = sorts[sid] as Btor2BitvecSort
   //     val value = BigInteger(ctx.hex.text, 16)
   //     val node = Btor2Const(nid, value, sort)
   //     Btor2Circuit.nodes[nid] = node
   //     return node
   // }
}