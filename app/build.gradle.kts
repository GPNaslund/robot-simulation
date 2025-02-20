plugins {
    id("java")
    application
    jacoco
}

group = "gn222gq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val mockitoAgent = configurations.create("mockitoAgent")
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:3.+")
    testImplementation("org.mockito:mockito-junit-jupiter:5.+")
    testImplementation("org.mockito:mockito-core:5.14.0");
    mockitoAgent("org.mockito:mockito-core:5.14.0") { isTransitive = false }
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

application {
    mainClass = "gn222gq.Main"
}