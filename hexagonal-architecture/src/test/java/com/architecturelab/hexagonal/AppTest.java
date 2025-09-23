// src/test/java/com/architecturelab/hexagonal/AppTest.java
package com.architecturelab.hexagonal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AppTest {

    @Test
    void contextLoads() {
        assertThat(true).isTrue();
    }
}
