package visitors

import models.*
import models.Btor2Circuit.nodes
import org.example.btor2.frontend.dsl.gen.Btor2BaseVisitor
import org.example.btor2.frontend.dsl.gen.Btor2Parser

class OperationVisitor : Btor2BaseVisitor<Btor2Node>() {
    val idVisitor = IdVisitor()
    override fun visitOperation(ctx: Btor2Parser.OperationContext): Btor2Node {
        check(ctx.childCount == 1)
        return ctx.children[0].accept(this)
    }

    override fun visitExt(ctx: Btor2Parser.ExtContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = Btor2Circuit.sorts[sid] as Btor2BitvecSort

        val opd = nodes[ctx.opd1.text.toUInt()] as Btor2Node
        val w = ctx.w.text.toUInt()

        check(sort.width == (opd.sort as Btor2BitvecSort).width + w)
        val node =  Btor2ExtOperation(nid, sort, opd, w)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitSlice(ctx: Btor2Parser.SliceContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = Btor2Circuit.sorts[sid] as Btor2BitvecSort

        val opd = nodes[ctx.opd1.text.toUInt()] as Btor2Node
        val u = ctx.u.text.toUInt()
        val l = ctx.l.text.toUInt()

        val node =  Btor2SliceOperation(nid, sort, opd, u, l)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitBinop(ctx: Btor2Parser.BinopContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = Btor2Circuit.sorts[sid] as Btor2BitvecSort

        val opd1 = nodes[ctx.opd1.text.toUInt()] as Btor2Node
        val opd2 = nodes[ctx.opd2.text.toUInt()] as Btor2Node

        val op = when (ctx.BINOP().text) {
            "and" -> Btor2BinaryOperator.AND
            "nand" -> Btor2BinaryOperator.NAND
            "nor" -> Btor2BinaryOperator.NOR
            "or" -> Btor2BinaryOperator.OR
            "xor" -> Btor2BinaryOperator.XOR
            "eq" -> Btor2BinaryOperator.EQ
            "neq" -> Btor2BinaryOperator.NEQ
            "slt" -> Btor2BinaryOperator.SLT
            "sle" -> Btor2BinaryOperator.SLE
            "sgt" -> Btor2BinaryOperator.SGT
            "sgte" -> Btor2BinaryOperator.SGTE
            "ult" -> Btor2BinaryOperator.ULT
            "ule" -> Btor2BinaryOperator.ULE
            "ugt" -> Btor2BinaryOperator.UGT
            "ugte" -> Btor2BinaryOperator.UGTE
            "add" -> Btor2BinaryOperator.ADD
            "mul" -> Btor2BinaryOperator.MUL
            "udiv" -> Btor2BinaryOperator.UDIV
            "urem" -> Btor2BinaryOperator.UREM
            "sdiv" -> Btor2BinaryOperator.SDIV
            "srem" -> Btor2BinaryOperator.SREM
            "smod" -> Btor2BinaryOperator.SMOD
            else -> throw RuntimeException("Binary operator unknown")
        }

        var node =  Btor2BinaryOperation(nid, sort, op, opd1, opd2)
        Btor2Circuit.nodes[nid] = node
        return node
    }

    override fun visitUnop(ctx: Btor2Parser.UnopContext): Btor2Node {
        val nid = idVisitor.visit(ctx.id)
        val sid = idVisitor.visit(ctx.sid())
        val sort : Btor2BitvecSort = Btor2Circuit.sorts[sid] as Btor2BitvecSort

        val op = when (ctx.UNARYOP().text) {
            "not" -> Btor2UnaryOperator.NOT
            "inc" -> Btor2UnaryOperator.INC
            "dec" -> Btor2UnaryOperator.DEC
            "neg" -> Btor2UnaryOperator.NEG
            else -> throw RuntimeException("Unary operator unknown")
        }

        val opd = nodes[ctx.opd1.text.toUInt()] as Btor2Node


        val node =  Btor2UnaryOperation(nid, sort, op, opd)
        Btor2Circuit.nodes[nid] = node
        return node
    }
}