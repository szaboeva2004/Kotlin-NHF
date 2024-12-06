plugins {
    kotlin("jvm") version "1.9.0"
    application
    antlr
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    antlr("org.antlr:antlr4:4.9.2")
    implementation("org.antlr:antlr4-runtime:4.9.2")
    implementation("hu.bme.mit.theta:theta-xcfa:6.8.1")
    implementation("hu.bme.mit.theta:theta-core:6.8.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
apply<AntlrPlugin>()

open class GenerateGrammarExtension(project: Project) {
    var packageName: String = "org.example.${project.name.replace("-", ".")}.dsl.gen"
}

tasks {
    val grammar = extensions.create<GenerateGrammarExtension>("grammar", project)

    named("generateGrammarSource", AntlrTask::class) {
        val packageName = grammar.packageName
        val directoryName = packageName.replace(".", File.separator)
        val pacakgeQualifiedOutputDirectory = outputDirectory.resolve(directoryName)
        outputDirectory = pacakgeQualifiedOutputDirectory

        arguments.addAll(listOf("-package", packageName, "-Werror", "-visitor"))
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateGrammarSource")
}