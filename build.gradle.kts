import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("org.springframework.boot") version "2.7.8"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
	group = "com.mrk3Stan"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

extra["springCloudVersion"] = "2021.0.5"

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-web")

		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
		implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

		implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
		implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")

		testImplementation("org.springframework.boot:spring-boot-starter-test")

		testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		}
	}
}