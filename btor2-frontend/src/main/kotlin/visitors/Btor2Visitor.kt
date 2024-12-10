package visitors

import models.Btor2Circuit
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
class Btor2Visitor : Btor2BaseVisitor<Btor2Circuit>(){
    private val sortVisitor = SortVisitor()


    override fun visitBtor2(ctx: Btor2Parser.Btor2Context): Btor2Circuit {
        for (child in ctx.children) {
            println(child.accept(this))
        }
        return Btor2Circuit
    }

    override fun visitLine(ctx: Btor2Parser.LineContext?): Btor2Circuit {
        for (child in ctx?.children!!) {
            println(child.accept(this))
        }
        return Btor2Circuit
    }

    override fun visitSort(ctx: Btor2Parser.SortContext?): Btor2Circuit {
        val result = sortVisitor.visit(ctx)
        println(result)
        Btor2Circuit.sorts[result.sid] = result
        return Btor2Circuit
    }

}