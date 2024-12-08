package visitors

import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser

class IdVisitor : Btor2BaseVisitor<UInt>() {
    override fun visitNid(ctx: Btor2Parser.NidContext): UInt {
        return ctx.NUM().text.toUInt()
    }

    override fun visitSid(ctx: Btor2Parser.SidContext): UInt {
        return ctx.NUM().text.toUInt()
    }
}