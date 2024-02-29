package io.holixon.cqrshexagonaldemo.demoparent.transactions

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class ArchUnitTest {
    private var classes: JavaClasses? = null

    @BeforeEach
    fun setUp() {
        classes = ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importClasspath()
    }

    @Test
    fun test_application_classes_should_not_use_adapaters_directly() {

        // given
        val myRuleForOutboundAdapters: ArchRule = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..transactions.application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..adapter.out.*")
        val myRuleForInboundAdapters: ArchRule = ArchRuleDefinition.noClasses()
            .that().resideInAPackage("..transactions.application..")
            .should()
            .dependOnClassesThat()
            .resideInAPackage("..adapter.in.*")

        // when

        // then
        myRuleForInboundAdapters.check(classes)
        myRuleForOutboundAdapters.check(classes)
    }

    @Test
    fun test_application_classes_should_use_domain_classes_only() {

        // given
        val myRuleForDomainClassesAndApplication: ArchRule = ArchRuleDefinition.classes()
            .that().resideInAnyPackage("..transactions.application..", "..transactions.domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                "..transactions.application.*",
                "..transactions.domain..",
                "java.*",
                "mu..",
                "kotlin.."
            )

        // when

        // then
        myRuleForDomainClassesAndApplication.check(classes)
    }
}