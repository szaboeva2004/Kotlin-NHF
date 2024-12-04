package visitors

import models.Btor2Circuit
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
// TODO Bto2Circuit-eket visszaadni
class Btor2Visitor : Btor2BaseVisitor<Btor2Circuit>(){
    val sortVisitor = SortVisitor()


    override fun visitBtor2(ctx: Btor2Parser.Btor2Context): Btor2Circuit {
        for (child in ctx.children) {
            print(child.accept(this))
        }
        return Btor2Circuit
    }

    override fun visitLine(ctx: Btor2Parser.LineContext?): Btor2Circuit {
        for (child in ctx?.children!!) {
            print(child.accept(this))
        }
        return Btor2Circuit
    }

    //override fun visitComment(ctx: Btor2Parser.CommentContext?): String {
    //    return "Comment visited" + ctx?.getText()
    //}

// TODO Modell típusok, belegyűjtjük a Btor2 megfeleleő listáiba
    override fun visitSort(ctx: Btor2Parser.SortContext?): Btor2Circuit {
        val result = sortVisitor.visit(ctx)
        Btor2Circuit.sorts.add(result)
        return Btor2Circuit
    }
}