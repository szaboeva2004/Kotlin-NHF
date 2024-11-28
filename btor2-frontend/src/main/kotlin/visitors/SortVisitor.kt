package visitors

import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import org.example.btor2.frontend.dsl.gen.Btor2Parser.Bitvec_sortContext

class SortVisitor : Btor2BaseVisitor<String>() {
    override fun visitSort(ctx: Btor2Parser.SortContext): String {
        return ctx.text
    }

    override fun visitBitvec_sort(ctx: Bitvec_sortContext?): String {
        return ctx?.text ?: ""
    }
}