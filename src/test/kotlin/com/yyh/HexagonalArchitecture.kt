package com.yyh

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest

@AnalyzeClasses(packages = ["com.yyh"], importOptions = [com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests::class])
class HexagonalArchitecture {

    @ArchTest
    fun `domain layer should not depend on adapter layer`(classes: JavaClasses) {
        val domainLayer = com.tngtech.archunit.library.Architectures.layeredArchitecture()
            .consideringAllDependencies()
            .layer("Domain").definedBy("com.yyh.domain..")
            .layer("Application").definedBy("com.yyh.application..")
            .layer("Adapter").definedBy("com.yyh.adapter..")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Adapter")
            .whereLayer("Application").mayOnlyBeAccessedByLayers("Adapter")
            .whereLayer("Adapter").mayNotBeAccessedByAnyLayer()
            .check(classes)a
    }
}