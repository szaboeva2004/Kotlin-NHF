package visitors

import models.*
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser

class StateVisitor : Btor2BaseVisitor<Btor2Node>() {
    private val idVisitor = IdVisitor()

    override fun visitStateful(ctx: Btor2Parser.StatefulContext): Btor2Node {
        check(ctx.childCount==1)
        return ctx.children[0].accept(this)
    }

    override fun visitState(ctx: Btor2Parser.StateContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort = Btor2Circuit.sorts[sid] as Btor2Sort

        val node = Btor2State(nid, sort)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitInput(ctx: Btor2Parser.InputContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort = Btor2Circuit.sorts[sid] as Btor2Sort

        val node = Btor2Input(nid, sort)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitInit(ctx: Btor2Parser.InitContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort = Btor2Circuit.sorts[sid]!!

        val param1 = Btor2Circuit.nodes[ctx.param1.NUM().text.toUInt()] as Btor2State
        val param2 = Btor2Circuit.nodes[ctx.param2.NUM().text.toUInt()] as Btor2Const


        check((param1.sort as Btor2BitvecSort).width == (param2.sort as Btor2BitvecSort).width)
        val node = Btor2Init(nid, sort, param1, param2)
        Btor2Circuit.inits[nid] = node
        return node
    }

    override fun visitNext(ctx: Btor2Parser.NextContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort = Btor2Circuit.sorts[sid] as Btor2Sort

        val param1 = Btor2Circuit.nodes[ctx.param1.NUM().text.toUInt()] as Btor2State
        val param2 = Btor2Circuit.nodes[ctx.param2.NUM().text.toUInt()] as Btor2Node
        val node = Btor2Next(nid, sort, param1, param2)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitProperty(ctx: Btor2Parser.PropertyContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val node = Btor2Bad(nid, null, Btor2Circuit.nodes[ctx.param.NUM().text.toUInt()] as Btor2Node)
        Btor2Circuit.bads[nid] = node
        Btor2Circuit.nodes[nid] = node
        return node
    }
}