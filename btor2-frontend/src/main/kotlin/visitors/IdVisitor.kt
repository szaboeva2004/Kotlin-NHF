package visitors

import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser

class IdVisitor : Btor2BaseVisitor<String>() {
    override fun visitNid(ctx: Btor2Parser.NidContext): String {
        return ctx.NUM().text
    }

    override fun visitSid(ctx: Btor2Parser.SidContext): String {
        return ctx.NUM().text
    }
}