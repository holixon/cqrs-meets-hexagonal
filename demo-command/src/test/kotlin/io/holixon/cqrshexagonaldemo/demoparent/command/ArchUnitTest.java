package io.holixon.cqrshexagonaldemo.demoparent.command;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitTest {


    private JavaClasses classes;

    @BeforeEach
    public void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_JARS)
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importClasspath();
    }

    @Test
    void test_application_classes_should_not_use_adapaters_directly() {

        // given
        final ArchRule myRuleForOutboundAdapters = noClasses()
                .that().resideInAPackage("..command.application..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..adapter.out.*");

        final ArchRule myRuleForInboundAdapters = noClasses()
                .that().resideInAPackage("..command.application..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("..adapter.in.*");

        // when

        // then
        myRuleForInboundAdapters.check(classes);
        myRuleForOutboundAdapters.check(classes);
    }

    @Test
    void test_application_classes_should_use_domain_classes_only() {

        // given
        final ArchRule myRuleForDomainClassesAndApplication = classes()
                .that().resideInAnyPackage("..command.application..", "..command.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(
                        "..command.application.*",
                        "..command.domain..",
                        "java.*",
                        "mu..",
                        "kotlin..");

        // when

        // then
        myRuleForDomainClassesAndApplication.check(classes);
    }

}