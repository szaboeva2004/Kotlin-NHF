package btor2xcfa

import hu.bme.mit.theta.xcfa.model.*
import hu.bme.mit.theta.xcfa.passes.ProcedurePassManager
import models.Btor2Circuit
/*
object Btor2XcfaBuilder{
    fun btor2xcfa(circuit: Btor2Circuit) : XCFA {
        val xcfaBuilder = XcfaBuilder("Btor2XCFA")
        val procBuilder = XcfaProcedureBuilder("main", Btor2Pass())
        xcfaBuilder.addProcedure(procBuilder)
        procBuilder.createInitLoc()
        procBuilder.addLoc(XcfaLocation("l1", false, false, false, EmptyMetaData))
        // Btor2Circuiten végigmegyünk, elmentjük fieldként az egyes modellekben az értéküket, ha nem null akkor addoljuk a fához
        // BTor2Circuitbe init lista
        for (node in Btor2Circuit.nodes) {
            node.getVar()?.let { varDecl ->
                val loc = XcfaLocation("l${node.nid}", false, false, false, EmptyMetaData)
                val edge = XcfaEdge(procBuilder.initLoc, loc, XcfaAssignment(varDecl, varDecl))
                procBuilder.addEdge(edge)
            }
        }
    }

}

class Btor2Pass() : ProcedurePassManager() {
    // No optimization for now c:
}*/