package fr.afpa.covoiturafpa.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;

import fr.afpa.covoiturafpa.Application;
import fr.afpa.covoiturafpa.model.Person;
import fr.afpa.covoiturafpa.repository.PersonRepository;
import fr.afpa.covoiturafpa.utils.security.CustomAuthenticationFilter;
import fr.afpa.covoiturafpa.utils.security.JwtUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void creating_jwt_token_verification() throws Exception {
        Integer userId = 51;
        ArrayList<String> roles = new ArrayList<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_TEACHER");
        roles.add( "ROLE_USER");
        try {
            String token = JwtUtil.createAccessToken("MohammadGreenfelder@mail.fr", userId, "http://127.0.0.1:8443/login", roles);
            Integer userIdToken = JwtUtil.parseToken(token).getIdUser();
            String rolesToken = JwtUtil.parseToken(token).getAuthorities().toString();
            assertEquals(userId, userIdToken);
            assertEquals(roles.toString(), rolesToken);
        }
        catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void authentication_user() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username","MohammadGreenfelder@mail.fr");
        requestBody.put("password","MohammadGreenfelder@mail.fr");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestBody))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
            Person userBdd = personRepository.findByEmail("MohammadGreenfelder@mail.fr").get();
            String jsonResultString = result.getResponse().getContentAsString();
            JsonNode jsonResult = objectMapper.readTree(jsonResultString);
            String rolesText = jsonResult.get("roles").asText();
            rolesText = rolesText.replace("[", "");
            rolesText = rolesText.replace("]", "");
            String[] roles = rolesText.split(",");
            System.err.println(Arrays.toString(roles));
            Integer userIdResult = jsonResult.get("userId").asInt();
            Integer userIdBdd = userBdd.getId();
            System.err.println(jsonResultString);
            boolean isJsonCorrect = true;
            if(Arrays.asList(roles).contains("ROLE_USER")) {
                isJsonCorrect = false;
            }
            if (!JwtUtil.verifyToken(jsonResult.get("token").asText())) {
                isJsonCorrect = false;
            }
            if (!userIdBdd.equals(userIdResult)) {
                isJsonCorrect = false;
            }
            if(isJsonCorrect) {
                assertTrue(true);
            }
            else {
                assertFalse(true);
            }
        }
        catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    public void emailChecked() {
        Optional<Person> user = personRepository.findByEmail("MohammadGreenfelder@mail.fr");
        if(user.isPresent()) {
            assertTrue(true);
        }else {
            assertFalse(true);
        }
    }


}
