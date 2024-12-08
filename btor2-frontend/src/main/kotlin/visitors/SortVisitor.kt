package visitors

import models.Btor2BitvecSort
import models.Btor2Circuit
import models.Btor2Sort
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import org.example.btor2.frontend.dsl.gen.Btor2Parser.Bitvec_sortContext

class SortVisitor : Btor2BaseVisitor<Btor2Sort>() {
    override fun visitSort(ctx: Btor2Parser.SortContext): Btor2Sort {
        return this.visitBitvec_sort(ctx.bitvec_sort())
    }

    override fun visitBitvec_sort(ctx: Bitvec_sortContext): Btor2Sort {
        var sort = Btor2BitvecSort(ctx.id.NUM().text.toUInt(), ctx.width.text.toUInt())
        Btor2Circuit.sorts.add(sort)
        return sort
    }
}