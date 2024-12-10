import btor2xcfa.Btor2XcfaBuilder
import hu.bme.mit.theta.xcfa.model.toDot
import models.Btor2Circuit
import org.antlr.v4.runtime.BailErrorStrategy
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.example.btor2.frontend.dsl.gen.Btor2Lexer
import org.example.btor2.frontend.dsl.gen.Btor2Parser
import visitors.Btor2Visitor
import java.io.File

fun main() {
    val visitor = Btor2Visitor()
    val c = """
        1 sort bitvec 3
        2 zero 1
        3 state 1
        4 init 1 3 2    
        5 one 1
        6 add 1 3 5
        7 next 1 3 6
        8 ones 1
        9 sort bitvec 1
        10 eq 9 3 8
        11 bad 10
    """.trimIndent()

    val input = CharStreams.fromString(c)
    val lexer = Btor2Lexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = Btor2Parser(tokens)
    parser.errorHandler = BailErrorStrategy()
    val context = parser.btor2()

    context.accept(visitor)

    val xcfa = Btor2XcfaBuilder.btor2xcfa(Btor2Circuit)

    val str = xcfa.toDot()
    File("output.dot").writeText(str)
}