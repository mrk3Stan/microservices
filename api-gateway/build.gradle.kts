configurations.implementation {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.boot:spring-boot-starter-oath2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-security")
}