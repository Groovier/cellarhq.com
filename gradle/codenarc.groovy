ruleset {
    description 'CellarHQ CodeNarc Rules'

    ruleset('rulesets/basic.xml')
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/concurrency.xml')
    ruleset('rulesets/convention.xml') {
        'CouldBeElvis' enabled: false
    }
    ruleset('rulesets/design.xml') {
        'AbstractClassWithoutAbstractMethod' enabled: false
        'AbstractClassWithPublicConstructor' enabled: false
    }
    ruleset('rulesets/dry.xml') {
        'DuplicateNumberLiteral' enabled: false
        'DuplicateStringLiteral' enabled: false
        'DuplicateListLiteral' enabled: false
        'DuplicateMapLiteral' enabled: false
    }
    ruleset('rulesets/exceptions.xml')
    ruleset('rulesets/formatting.xml')  {
        'SpaceBeforeOpeningBrace' enabled: false
        'SpaceAfterOpeningBrace' enabled: false
        'SpaceBeforeClosingBrace' enabled: false
        'SpaceAfterClosingBrace' enabled: false
    }
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/groovyism.xml') {
        'ExplicitCallToAndMethod' {
            doNotApplyToFileNames = '*Service.groovy' // jOOQ DSL
        }
        'ExplicitCallToMinusMethod' {
            doNotApplyToFileNames = '*Service.groovy' // jOOQ DSL
        }
        'ClosureAsLastMethodParameter' {
            doNotApplyToFileNames = '*Endpoint.groovy'
        }
    }
    ruleset('rulesets/imports.xml') {
        'MisorderedStaticImports' enabled: false
    }
    ruleset('rulesets/jdbc.xml')
    ruleset('rulesets/junit.xml')
    ruleset('rulesets/logging.xml')
    ruleset('rulesets/naming.xml') {
        'FactoryMethodName' enabled: false
        'FieldName' enabled: false
    }
    ruleset('rulesets/security.xml') {
        'JavaIoPackageAccess' enabled: false
    }
    ruleset('rulesets/size.xml') {
        'CrapMetric' enabled: false //This rule requires a coverage results file and isn't really helpful.
        'AbcMetric' {
            doNotApplyToFileNames = '*Endpoint.groovy'
        }
        'NestedBlockDepth' {
            doNotApplyToFileNames = '*Endpoint.groovy'
        }
    }
    ruleset('rulesets/unnecessary.xml') {
        'UnnecessaryReturnKeyword' enabled: false
        'UnnecessaryPublicModifier' enabled: false
        'UnnecessarySubstring' enabled: false
    }
    ruleset('rulesets/unused.xml') {
        'UnusedMethodParameter'  enabled: false
        'UnusedPrivateField' {
            enabled = true
            ignoreFieldNames = 'lastUpdated, dateCreated, serialVersionUID'
        }
    }
}
