package io.holixon.cqrshexagonaldemo.demoparent.transactions.architecture

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeJars
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices
import io.holixon.cqrshexagonaldemo.demoparent.transactions.architecture.CommonArchUnitTest.Companion.BASE_PACKAGE

@Suppress("unused") // @ArchTest properties are used by ArchUnit
@AnalyzeClasses(
        packages = [BASE_PACKAGE], importOptions = [DoNotIncludeTests::class, DoNotIncludeJars::class])
internal class CommonArchUnitTest {

    companion object {
        const val BASE_PACKAGE = "io.holixon.cqrshexagonaldemo.demoparent.transactions"
        const val SHARED_PACKAGE = "$BASE_PACKAGE.shared.."
        const val COMMAND_PACKAGE = "$BASE_PACKAGE.command.."
        const val QUERY_PACKAGE = "$BASE_PACKAGE.query.."
    }

    @ArchTest
    val shouldNotContainCycles: ArchRule =
            slices().matching("$BASE_PACKAGE.(*)..").should().beFreeOfCycles()

    @ArchTest
    val shouldComplyWithCqrsArchitecture: ArchRule =
        layeredArchitecture()
            .consideringAllDependencies()
            .layer("shared").definedBy(SHARED_PACKAGE)
            .layer("command").definedBy(COMMAND_PACKAGE)
            .layer("query").definedBy(QUERY_PACKAGE)
            .whereLayer("shared").mayNotAccessAnyLayer()
            .whereLayer("command").mayOnlyBeAccessedByLayers("query")
            .whereLayer("query").mayOnlyAccessLayers("shared", "command")
            .ignoreDependency(
                DescribedPredicate.alwaysTrue(),
                JavaClass.Predicates.resideOutsideOfPackages("$BASE_PACKAGE..")
            )
}