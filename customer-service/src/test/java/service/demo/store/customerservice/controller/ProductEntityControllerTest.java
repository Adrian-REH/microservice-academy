package service.demo.store.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;
import service.demo.store.customerservice.constants.AppConstants;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProductEntityControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ProductEntityControllerTest.class);

    public String TOKEN_SUPADMIN_ACTUAL;
    public String TOKEN_ATM_ACTUAL;
    public String TOKEN_ADMIN_ACTUAL;
    public String TOKEN_VIEW_ACTUAL;
    @Autowired
    public MockMvc mockMvc;

    @Test
    void whenGetProducts_returnListProduct() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get(AppConstants.URL_FIND_ALL_PRODUCTS)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(AppConstants.PRODUCT_ID))
                .andReturn();
        printResponse(mockResponse);
    }


    public static void printResponse(MvcResult result) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> headers = new HashMap<>();
        result.getResponse().getHeaderNames().forEach(resHeader->headers.put(resHeader, result.getResponse().getHeader(resHeader)));

        log.info("header  : \n{}", mapper.writeValueAsString(headers));
        log.info("body  : \n{}", result.getResponse().getContentAsString());
    }

}
