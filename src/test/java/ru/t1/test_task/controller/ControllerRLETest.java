package ru.t1.test_task.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ControllerRLETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnConvertedString_whenStringOfEnglishCharactersNumberCharactersIsNotRepeated_thenStatus200() {

        String string = "aaaaabcccc";
        String expected = "\"a\": 5, \"c\": 4, \"b\": 1";

        ResponseEntity<String> response = restTemplate.postForEntity("/rle", string, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expected));
    }

    @Test
    void shouldReturnConvertedString_whenStringOfEnglishCharactersNumberCharactersRepeated_thenStatus200() {

        String string = "AAAbHHHHHKLLAAAAuOOPPPSSSff";
        String expected = "\"H\": 5, \"A\": 4, \"A\": 3, \"P\": 3, \"S\": 3, " +
                          "\"L\": 2, \"O\": 2, \"f\": 2, \"b\": 1, \"K\": 1, \"u\": 1";

        ResponseEntity<String> response = restTemplate.postForEntity("/rle", string, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expected));
    }

    @Test
    void shouldReturnConvertedString_whenStringOfEnglishAndRussianCharactersNumberCharactersRepeated_thenStatus200() {

        String string = "фффеееРРРТТТDDDDhhhiiiio";
        String expected = "\"D\": 4, \"i\": 4, \"ф\": 3, \"е\": 3, \"Р\": 3, \"Т\": 3, \"h\": 3, \"o\": 1";

        ResponseEntity<String> response = restTemplate.postForEntity("/rle", string, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(expected));
    }

    @Test
    void shouldReturnStatus400_whenStringIsEmptyOrNull() {

        ResponseEntity<String> response = restTemplate.postForEntity("/rle", null, String.class);

        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}