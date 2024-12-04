package visitors

import models.Btor2Circuit
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
// TODO Bto2Circuit-eket visszaadni
class Btor2Visitor : Btor2BaseVisitor<String>(){
    val sortVisitor = SortVisitor()

    override fun visitBtor2(ctx: Btor2Parser.Btor2Context): String {
        for (child in ctx.children) {
            print(child.accept(this))
        }
        return "Btor2 visited "
    }

    override fun visitLine(ctx: Btor2Parser.LineContext?): String {
        for (child in ctx?.children!!) {
            print(child.accept(this))
        }
        return "Line visited" + ctx?.getText()
    }

    override fun visitComment(ctx: Btor2Parser.CommentContext?): String {
        return "Comment visited" + ctx?.getText()
    }
// TODO Modell típusok, belegyűjtjük a Btor2 megfeleleő listáiba
    override fun visitSort(ctx: Btor2Parser.SortContext?): String {
        val result = sortVisitor.visit(ctx)
        Btor2Circuit.sorts.add(result)
        return "Sort visited " + result
    }
}