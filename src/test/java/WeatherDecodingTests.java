import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeatherDecodingTests {
    WeatherDecoding sut;

    @BeforeEach
    public void init() {
        System.out.println("Test started");
        sut = new WeatherDecoding();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Test completed");
        sut = null;
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Tests completed");
    }

    @Test
    public void testClouds(){
        String a = "OVC";
        String expected = "Сплошная облачность ";

        String result = sut.checkClouds(a);

        assertEquals(expected,result);
    }

    @Test
    public void testWind(){
        String a = "400";
        String b = "100";
        var expected = DirectionMismatchException.class;

        assertThrows(expected,
                () -> sut.checkWind(a,b));
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testCloudBase(String a, String expected){
        String result = sut.checkCloudsBase(a);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> source(){
        return Stream.of(Arguments.of("30", "900 метров"));
    }



}
