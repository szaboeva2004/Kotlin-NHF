package btor2xcfa

import hu.bme.mit.theta.core.stmt.AssignStmt
import hu.bme.mit.theta.xcfa.model.*
import hu.bme.mit.theta.xcfa.passes.ProcedurePassManager
import models.Btor2Circuit

object Btor2XcfaBuilder{
    fun btor2xcfa(circuit: Btor2Circuit) : XCFA {
        var i : Int = 1
        val xcfaBuilder = XcfaBuilder("Btor2XCFA")
        val procBuilder = XcfaProcedureBuilder("main", Btor2Pass())
        xcfaBuilder.addProcedure(procBuilder)
        procBuilder.createInitLoc()

        Btor2Circuit.nodes.forEach() {
            it.value.getVar()?.let { varDecl ->
                procBuilder.addVar(varDecl)
                procBuilder.addLoc(XcfaLocation("l${i}", false, false, false, EmptyMetaData))
                i++
            }
        }

        var lastLoc = procBuilder.initLoc
        Btor2Circuit.inits.forEach() {
            val loc = XcfaLocation("l${i}", false, false, false, EmptyMetaData)


            procBuilder.addLoc(loc)
            AssignStmt.of(it.value.state.getVar(), it.value.value.getExpr())

            var edge = XcfaEdge(lastLoc,loc, XcfaLabel(), EmptyMetaData)
            i++
            lastLoc=loc
        }




        return xcfaBuilder.build()
    }

}

class Btor2Pass() : ProcedurePassManager() {
    // No optimization for now c:
}