package visitors

import models.Btor2Circuit
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser
class Btor2Visitor : Btor2BaseVisitor<Btor2Circuit>(){
    private val sortVisitor = SortVisitor()
    private val constantVisitor = ConstantVisitor()
    private val operationVisitor = OperationVisitor()
    private val statefulVisitor = StateVisitor()

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

    override fun visitConstantNode(ctx: Btor2Parser.ConstantNodeContext): Btor2Circuit {
        println(constantVisitor.visit(ctx))
        return Btor2Circuit
    }

    override fun visitOperation(ctx: Btor2Parser.OperationContext): Btor2Circuit {
        println(operationVisitor.visit(ctx))
        return Btor2Circuit
    }

    override fun visitStateful(ctx: Btor2Parser.StatefulContext?): Btor2Circuit {
        println(statefulVisitor.visit(ctx))
        return Btor2Circuit
    }


}