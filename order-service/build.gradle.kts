plugins {
    kotlin("plugin.jpa") version "1.6.21"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly("com.mysql:mysql-connector-j")
}