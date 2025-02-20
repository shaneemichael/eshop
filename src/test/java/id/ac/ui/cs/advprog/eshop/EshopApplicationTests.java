package id.ac.ui.cs.advprog.eshop;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        // If the context fails to load, this test will fail.
    }
    
    @Test
    void testMainMethodRuns() {
        // This will invoke the main method.
        assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }
}