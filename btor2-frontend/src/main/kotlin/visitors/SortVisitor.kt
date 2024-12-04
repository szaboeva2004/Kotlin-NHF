package visitors

import models.Btor2Sort
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import org.example.btor2.frontend.dsl.gen.Btor2Parser.Bitvec_sortContext

class SortVisitor : Btor2BaseVisitor<Btor2Sort>() {
    override fun visitSort(ctx: Btor2Parser.SortContext): Btor2Sort {
        print("Sort visited: \n")
        return this.visitBitvec_sort(ctx.bitvec_sort())
    }

    override fun visitBitvec_sort(ctx: Bitvec_sortContext): Btor2Sort {
        print("Bitvec sort visited: ")
        return Btor2Sort(ctx.id.NUM().text.toUInt(), ctx.width.text.toUInt())
    }
}