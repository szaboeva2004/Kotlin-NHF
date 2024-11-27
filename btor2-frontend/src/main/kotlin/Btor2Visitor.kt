import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser

class Btor2Visitor : Btor2BaseVisitor<String>(){
    override fun visitBtor2(ctx: Btor2Parser.Btor2Context): String {
        for (child in ctx.children) {
            print(child.accept(this))
        }
        return "asd"
    }

    override fun visitLine(ctx: Btor2Parser.LineContext?): String {
        return "asd"
    }
}