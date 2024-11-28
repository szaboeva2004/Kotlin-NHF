package visitors

import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import org.example.btor2.frontend.dsl.gen.Btor2Parser.*

class ConstantVisitor : Btor2BaseVisitor<Int>() {
    override fun visitConstant(ctx: Btor2Parser.ConstantContext): Int {
        return ctx.text.toInt()
    }

    override fun visitConstant_d(ctx: Constant_dContext?): Int {
        return ctx?.text?.toInt() ?: 0
    }

    override fun visitConstant_h(ctx: Constant_hContext?): Int {
        return ctx?.text?.toInt() ?: 0
    }

    override fun visitFilled_constant(ctx: Filled_constantContext?): Int {
        return ctx?.text?.toInt() ?: 0
    }
}