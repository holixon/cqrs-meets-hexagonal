package io.holixon.cqrshexagonaldemo.demoparent.transactions.architecture

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.onionArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices
import io.holixon.cqrshexagonaldemo.demoparent.transactions.architecture.ArchUnitTest.Companion.BASE_PACKAGE

@Suppress("unused") // @ArchTest properties are used by ArchUnit
@AnalyzeClasses(
        packages = [BASE_PACKAGE], importOptions = [DoNotIncludeTests::class, DoNotIncludeJars::class])
internal class ArchUnitTest {

    companion object {
        const val BASE_PACKAGE = "io.holixon.cqrshexagonaldemo"
        const val DOMAIN_MODEL_PACKAGE = "..domain.model.."
        const val DOMAIN_SERVICE_PACKAGE = "..domain.service.."
        const val APPLICATION_PACKAGE = "..application.."
        const val IN_ADAPTER_PACKAGE = "..adapter.inbound.."
        const val OUT_ADAPTER_PACKAGE = "..adapter.outbound.."
    }


    @ArchTest
    val shouldNotContainCycles: ArchRule =
            slices().matching("$BASE_PACKAGE.(*)..").should().beFreeOfCycles()

    @ArchTest
    val shouldComplyWithHexaArchitecture: ArchRule =
            onionArchitecture()
                    .domainModels(DOMAIN_MODEL_PACKAGE)
                    .domainServices(DOMAIN_SERVICE_PACKAGE)
                    .applicationServices(APPLICATION_PACKAGE)
                    .adapter("in-adapter", IN_ADAPTER_PACKAGE)
                    .adapter("out-adapter", OUT_ADAPTER_PACKAGE)
                    .withOptionalLayers(true)
}