import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.10" apply false
	kotlin("plugin.jpa") version "1.6.0" apply false

	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
}

repositories {
	mavenCentral()
}

allprojects {
	group = "happyprogfrog"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
		plugin("org.jetbrains.kotlin.plugin.spring")

		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

project(":api") {
	dependencies {
		implementation(project(":domain"))
		implementation(project(":kafka"))
	}
}

project(":consumer") {
	dependencies {
		implementation(project(":domain"))
		implementation(project(":kafka"))
	}
}

// 최상위 프로젝트에 대해서 실행 가능한 main 함수가 없음을 명시
tasks.bootJar {
	enabled = false
}

tasks.jar {
	enabled = true
}

project(":domain") {
	val jar: Jar by tasks
	val bootJar: BootJar by tasks

	bootJar.enabled = false // 실행 가능한 main 함수가 없음을 명시
	jar.enabled = true
}

project(":kafka") {
	val jar: Jar by tasks
	val bootJar: BootJar by tasks

	bootJar.enabled = false // 실행 가능한 main 함수가 없음을 명시
	jar.enabled = true
}